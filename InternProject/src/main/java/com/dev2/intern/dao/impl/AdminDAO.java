package com.dev2.intern.dao.impl;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dev2.intern.dao.IAdminDAO;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.UserVO;

@Repository(AdminDAO.BEAN_QUALIFIER)
public class AdminDAO extends GenericDAO implements IAdminDAO {

	public static final String BEAN_QUALIFIER = "com.dev.intern.dao.impl.AdminDAO";
	
	@Override
	public ArrayList<PostVO> getAllPost() {
		String query = getQuery("admin.getAllPost");
		
		return (ArrayList<PostVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<PostVO>(PostVO.class));
	}

	@Override
	public ArrayList<FileVO> getAllFile() {
		String query = getQuery("admin.getAllFile");
		
		return (ArrayList<FileVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<FileVO>(FileVO.class));
	}

	@Override
	public ArrayList<CommentVO> getAllComment() {
		String query = getQuery("admin.getAllComment");
		
		return (ArrayList<CommentVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<CommentVO>(CommentVO.class));
	}

	@Override
	public ArrayList<UserVO> getAllUser() {
		String query = getQuery("admin.getAllUser");
		
		return (ArrayList<UserVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<UserVO>(UserVO.class));
	}

	@Override
	public ArrayList<PostVO> getAllTrashPost() {
		String query = getQuery("admin.getAllTrashPost");
		
		return (ArrayList<PostVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<PostVO>(PostVO.class));
	}

	@Override
	public ArrayList<FileVO> getAllTrashFile() {
		String query = getQuery("admin.getAllTrashFile");
		
		return (ArrayList<FileVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<FileVO>(FileVO.class));
	}

	@Override
	public ArrayList<CommentVO> getAllTrashComment() {
		String query = getQuery("admin.getAllTrashComment");
		
		return (ArrayList<CommentVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<CommentVO>(CommentVO.class));
	}

	@Override
	public ArrayList<UserVO> getAllTrashUser() {
		String query = getQuery("admin.getAllTrashUser");
		
		return (ArrayList<UserVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<UserVO>(UserVO.class));
	}

	@Override
	public int deleteTrashPost(PostVO postVO) {
		String query = getQuery("admin.deleteTrashPost");
		
		return jdbcTemplate.update(query, postVO.getId());
	}

	@Override
	public int deleteTrashFile(FileVO fileVO) {
		String query = getQuery("admin.deleteTrashFile");
		
		return jdbcTemplate.update(query, fileVO.getId());
	}

	@Override
	public int deleteTrashComment(CommentVO commentVO) {
		String query = getQuery("admin.deleteTrashComment");
		
		return jdbcTemplate.update(query, commentVO.getId());
	}

	@Override
	public int deleteTrashUser(UserVO userVO) {
		String query = getQuery("admin.deleteTrashUser");
		
		return jdbcTemplate.update(query, userVO.getId());
	}

	@Override
	public int modifyUserLevel(String userId, String level) {
		String query = getQuery("admin.modifyUserLevel");
		
		return jdbcTemplate.update(query, level, userId);
	}

}
