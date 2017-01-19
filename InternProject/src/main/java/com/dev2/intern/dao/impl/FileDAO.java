package com.dev2.intern.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dev2.intern.dao.IFileDAO;
import com.dev2.intern.vo.FileVO;

@Repository(FileDAO.BEAN_QUALIFIER)
public class FileDAO extends GenericDAO implements IFileDAO {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.dao.impl.FileDAO";
	
	@Override
	public int saveFile(FileVO fileVO) throws IllegalStateException, IOException {
		String query = getQuery("file.saveFile");
		
		return jdbcTemplate.update(query, fileVO.getPostId(), fileVO.getLocation(), fileVO.getOriginalFileName(), fileVO.getSize(), fileVO.getType());
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public int deleteFile(int postId) {
		String query = getQuery("file.deleteFile");
		
		return jdbcTemplate.update(query, postId);
	}
	
	@Override
	public void gotoTrash(int postId) {
		String query = getQuery("file.gotoTrash");
		
		jdbcTemplate.update(query, postId);
	}
}
