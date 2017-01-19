package com.dev2.intern.dao.impl;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dev2.intern.dao.ICommentDAO;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Repository(CommentDAO.BEAN_QUALIFIER)
public class CommentDAO extends GenericDAO implements ICommentDAO {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.dao.impl.CommentDAO";
	
	@Override
	public ArrayList<CommentVO> getCommentByPost(String postId) {
		String query = getQuery("comment.listUpComment");
		
		return (ArrayList<CommentVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<CommentVO>(CommentVO.class), postId);
	}
	
	@Override
	public int writeComment(WriteCommentVO writeCommentVO) {
		String query = getQuery("comment.writeComment");
		
		return jdbcTemplate.update(query, writeCommentVO.getParentCommentId(), writeCommentVO.getPostId(), writeCommentVO.getUserId(), writeCommentVO.getDepth(), writeCommentVO.getContents());
	}
	
	@Override
	public int deleteComment(int commentId) {
		String query = getQuery("comment.deleteComment");
		
		return jdbcTemplate.update(query, commentId);
	}
	
	@Override
	public void gotoTrash(int commentId) {
		String query = getQuery("comment.gotoTrash");
		
		jdbcTemplate.update(query, commentId);
	}
	
	@Override
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		String query = getQuery("comment.modifyComment");
		
		return jdbcTemplate.update(query, modifyCommentVO.getContents(), modifyCommentVO.getId());
	}
	
	@Override
	public void deleteCommentByPostId(int postId) {
		String query = getQuery("comment.deleteCommentByPostId");
		
		jdbcTemplate.update(query, postId);
	}
	
	@Override
	public void gotoTrashByPostId(int postId) {
		String query = getQuery("comment.gotoTrashByPostId");
		
		jdbcTemplate.update(query, postId);
	}
}
