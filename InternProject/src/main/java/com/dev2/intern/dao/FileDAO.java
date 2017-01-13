package com.dev2.intern.dao;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.util.UuidUtil;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

@Repository
public class FileDAO extends GenericDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FILE_DIRECTORY = "E:\\01. 인턴\\files\\";
	
	/**
	 * 글작성시 파일을 저장하기 위한 함수
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 * @param multipartFile
	 * 			저장할 파일 정보
	 * @return 정상적으로 삽입됬으면 1, 아니면 0
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public int saveFile(int postId, MultipartFile multipartFile) throws IllegalStateException, IOException {
		String query = getQuery("file.saveFile");
		FileVO fileVO = uploadFile(postId, multipartFile);
		
		return jdbcTemplate.update(query, fileVO.getPostId(), fileVO.getLocation(), fileVO.getOriginalFileName(), fileVO.getSize(), fileVO.getType());
	}
	
	/**
	 * 실제 파일을 storage에 저장하기 위한 함수 
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 * @param multipartFile
	 * 			저장할 파일 정보
	 * @return 저장된 파일의 정보가 담긴 FileVO
	 * @throws IllegalStateException
	 * @throws IOException
	 */
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
	
	/**
	 * 저장할 Directory가 없을 경우 생성하기 위한 함수
	 */
	private void checkExistDirectory() {
		File directory = new File(FILE_DIRECTORY);
		
		if (directory.exists() == false) {
			directory.mkdirs();
		}
	}
	
	/**
	 * 해당 게시글에 존재하는 파일을 가져오기 위한 함수
	 * 
	 * @param postId
	 * 			게시글의 id
	 * @return 게시글에 파일이 존재할 경우 파일 정보가 담긴 FileVO, 없을 경우 null
	 */
	public FileVO getFileByPostId(String postId) {
		String query = getQuery("file.getFileByPostId");
		FileVO fileVO;
		
		try {
			fileVO = (FileVO)jdbcTemplate.queryForObject(query, new RowMapper<FileVO>() {
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
	
	/**
	 * 파일을 다운로드 받을 때, 선택한 파일의 정보를 가져오기 위한 함수
	 * 
	 * @param fileId
	 * 			다운로드 받을 파일의 id
	 * @return 다운로드 받을 파일의 정보가 담긴 FileVO
	 */
	public FileVO getFileByFileId(String fileId) {
		String query = getQuery("file.getFileByFileId");
		FileVO fileVO = (FileVO)jdbcTemplate.queryForObject(query, new RowMapper<FileVO>() {
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
	
	/**
	 * 파일을 수정하기 위한 함수. 원래 있던 파일을 trash로 옮기고 새로 upload한다.
	 * 
	 * @param modifyPostVO
	 * 			수정할 게시글의 정보가 담긴 VO
	 * @return 정상적으로 끝이 났을 경우 1
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public int modifyFile(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException {
		gotoTrash(modifyPostVO.getId());
		FileVO fileVO = uploadFile(modifyPostVO.getId(), modifyPostVO.getFile());
		
		return 1;
	}
	
	/**
	 * 일반유저가 파일을 삭제할 때 사용하는 함수
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 * @return 정상적으로 삭제가 되면 1, 아니면 0
	 */
	public int deleteFile(int postId) {
		String query = getQuery("file.deleteFile");
		gotoTrash(postId);
		
		return jdbcTemplate.update(query, postId);
	}
	
	/**
	 * 일반유저가 파일을 삭제할 때, 해당 파일을 trash로 보내는 함수
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 */
	private void gotoTrash(int postId) {
		String query = getQuery("file.gotoTrash");
		jdbcTemplate.update(query, postId);
	}
}
