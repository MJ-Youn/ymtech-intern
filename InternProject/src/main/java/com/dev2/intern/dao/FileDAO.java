package com.dev2.intern.dao;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.util.UuidUtil;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

@Repository
public class FileDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("#{query['file.saveFile']}")
	private String QUERY_SAVEFILE;
	
	@Value("#{query['file.getFileByPostId']}")
	private String QUERY_GETFILEBYPOSTID;
	
	@Value("#{query['file.getFileByFileId']}")
	private String QUERY_GETFILEBYFILEID;
	
	@Value("#{query['file.modifyFile']}")
	private String QUERY_MODIFYFILE;
	
	@Value("#{query['file.deleteFile']}")
	private String QUERY_DELETEFILE;
	
	@Value("#{query['file.gotoTrash']}")
	private String QUERY_GOTOTRASH;
	
	private static final String FILE_DIRECTORY = "E:\\01. 인턴\\files\\";
	
	public int saveFile(int postId, MultipartFile multipartFile) throws IllegalStateException, IOException {
		FileVO fileVO = uploadFile(postId, multipartFile);
		
		return jdbcTemplate.update(QUERY_SAVEFILE, fileVO.getPostId(), fileVO.getLocation(), fileVO.getOriginalFileName(), fileVO.getSize(), fileVO.getType());
	}
	
	private FileVO uploadFile(int postId, MultipartFile multipartFile) throws IllegalStateException, IOException {
		checkExistDirectory();
		
		String originalFileName = multipartFile.getOriginalFilename();
		String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		String storedFileName = FILE_DIRECTORY + UuidUtil.createUuidWithoutHyphen();
		
		File file = new File(storedFileName);
		multipartFile.transferTo(file);
		
		return new FileVO().setPostId(postId)
							.setLocation(storedFileName)
							.setOriginalFileName(originalFileName)
							.setSize(multipartFile.getSize())
							.setType(originalFileExtension);
	}
	
	private void checkExistDirectory() {
		File directory = new File(FILE_DIRECTORY);
		
		if (directory.exists() == false) {
			directory.mkdirs();
		}
	}
	
	public FileVO getFileByPostId(String postId) {
		FileVO fileVO;
		
		try {
			fileVO = (FileVO)jdbcTemplate.queryForObject(QUERY_GETFILEBYPOSTID, new RowMapper<FileVO>() {
				public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					FileVO fileVO = new FileVO();
					
					if (rs != null) {
						fileVO.setId(rs.getInt("id"))
						.setPostId(rs.getInt("post_id"))
						.setLocation(rs.getString("location"))
						.setOriginalFileName(rs.getString("original_file_name"))
						.setSize(rs.getInt("size"))
						.setType(rs.getString("type"));
					}
					
					return fileVO;
				}
			}, postId);
		} catch(EmptyResultDataAccessException erdae) {
			return null;
		}
		
		return fileVO;
	}
	
	public FileVO getFileByFileId(String fileId) {
		FileVO fileVO = (FileVO)jdbcTemplate.queryForObject(QUERY_GETFILEBYFILEID, new RowMapper<FileVO>() {
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileVO fileVO = new FileVO();
				fileVO.setId(rs.getInt("id"))
						.setPostId(rs.getInt("post_id"))
						.setLocation(rs.getString("location"))
						.setOriginalFileName(rs.getString("original_file_name"))
						.setSize(rs.getInt("size"))
						.setType(rs.getString("type"));
				
				return fileVO;
			}
		}, fileId);
		
		return fileVO;
	}
	
	public int modifyFile(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException {
		gotoTrash(modifyPostVO.getId());
		FileVO fileVO = uploadFile(modifyPostVO.getId(), modifyPostVO.getFile());
		
		return 1;
	}
	
	public int deleteFile(int postId) {
		gotoTrash(postId);
		
		return jdbcTemplate.update(QUERY_DELETEFILE, postId);
	}
	
	private void gotoTrash(int postId) {
		jdbcTemplate.update(QUERY_GOTOTRASH, postId);
	}
}
