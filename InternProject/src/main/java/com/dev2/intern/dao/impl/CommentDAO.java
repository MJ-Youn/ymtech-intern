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
	public ArrayList<CommentVO> listUpComment(String postId) {
		String query = getQuery("comment.listUpComment");
		
		ArrayList<CommentVO> unSortCommentList = (ArrayList<CommentVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<CommentVO>(CommentVO.class), postId);
		ArrayList<CommentVO> sortedCommentList = sortComment(unSortCommentList);
		
		return sortedCommentList;
	}
	
	@Override
	public ArrayList<CommentVO> sortComment(ArrayList<CommentVO> unsortCommentList) {
		ArrayList<CommentVO> sortedCommentList = new ArrayList<CommentVO>();
		
		for (int i = 0 ; i < unsortCommentList.size() ; i++) {
			if (unsortCommentList.get(i).getParentCommentId() < 0) {
				sortedCommentList.add(unsortCommentList.get(i));
			} else {
				int parentCommentId = unsortCommentList.get(i).getParentCommentId();
				int parentCommentIndex = searchNextParentCommentIndex(sortedCommentList, parentCommentId);
				
				sortedCommentList.add(parentCommentIndex, unsortCommentList.get(i));
			}
		}
		
		return sortedCommentList;
	}
	
	@Override
	public int searchNextParentCommentIndex(ArrayList<CommentVO> commentList, int parentCommentId) {
		int nextParentCommentIndex = -1;
		int i;
		
		for (i = 0 ; i < commentList.size() ; i++) {
			if (commentList.get(i).getId() == parentCommentId) {
				break;
			}
		}
		
		for (i = i + 1 ; i < commentList.size() ; i++) {
			if (commentList.get(i).getParentCommentId() < parentCommentId) {
				nextParentCommentIndex = i;
				break;
			}
		}
		
		if (nextParentCommentIndex < 0) {
			return commentList.size();
		} else {
			return nextParentCommentIndex;
		}
	}
	
	@Override
	public int writeComment(WriteCommentVO writeCommentVO) {
		String query = getQuery("comment.writeComment");
		
		return jdbcTemplate.update(query, writeCommentVO.getParentCommentId(), writeCommentVO.getPostId(), writeCommentVO.getUserId(), writeCommentVO.getDepth(), writeCommentVO.getContents());
	}
	
	@Override
	public int deleteComment(int commentId) {
		String query = getQuery("comment.deleteComment");
		
		gotoTrash(commentId);
		
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
		
		gotoTrashByPostId(postId);
		jdbcTemplate.update(query, postId);
	}
	
	@Override
	public void gotoTrashByPostId(int postId) {
		String query = getQuery("comment.gotoTrashByPostId");
		
		jdbcTemplate.update(query, postId);
	}
}
