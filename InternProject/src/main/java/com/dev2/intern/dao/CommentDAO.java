package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Repository
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("#{query['comment.listUpComment']}")
	private String QUERY_LISTUPCOMMENT;
	
	@Value("#{query['comment.writeComment']}")
	private String QUERY_WRITECOMMENT;
	
	@Value("#{query['comment.deleteComment']}")
	private String QUERY_DELETECOMMENT;
	
	@Value("#{query['comment.gotoTrash']}")
	private String QUERY_GOTOTRASH;
	
	@Value("#{query['comment.modifyComment']}")
	private String QUERY_MODIFYCOMMENT;
	
	@Value("#{query['comment.deleteCommentByPostId']}")
	private String QUERY_DELETECOMMENTBYPOSTID;
	
	@Value("#{query['comment.gotoTrashByPostId']}")
	private String QUERY_GOTOTRASHBYPOSTID;
	
	/**
	 * postId에 해당하는 comment를 가져오기 위한 함수
	 * 
	 * @param postId
	 * 			대상 post id
	 * @return comment의 list
	 */
	public ArrayList<CommentVO> listUpComment(String postId) {
		ArrayList<CommentVO> unSortCommentList = (ArrayList<CommentVO>)jdbcTemplate.query(QUERY_LISTUPCOMMENT, new BeanPropertyRowMapper<CommentVO>(CommentVO.class), postId);
		ArrayList<CommentVO> sortedCommentList = sortComment(unSortCommentList);
		
		return sortedCommentList;
	}
	
	/**
	 * DB에서 가져온 commentList들을 parent, child 순서에 맞게 정렬하기 위한 함수
	 * 
	 * @param unsortCommentList
	 * 			정렬이 되지 않은 commentList
	 * @return parent, child 순서대로 정렬이된 commentList
	 */
	private ArrayList<CommentVO> sortComment(ArrayList<CommentVO> unsortCommentList) {
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
	
	/**
	 * commentList에서 parentCommentId의 index값을 구하기 위한 함수
	 * 
	 * @param commentList
	 * 			대상이 되는 commentList
	 * @param parentCommentId
	 * 			찾으려고 하는 parentCommentId
	 * @return parentCommentId가 위치해 있는 index
	 */
	private int searchNextParentCommentIndex(ArrayList<CommentVO> commentList, int parentCommentId) {
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
	
	public int writeComment(WriteCommentVO writeCommentVO) {
		return jdbcTemplate.update(QUERY_WRITECOMMENT, writeCommentVO.getParentCommentId(), writeCommentVO.getPostId(), writeCommentVO.getUserId(), writeCommentVO.getDepth(), writeCommentVO.getContents());
	}
	
	public int deleteComment(int commentId) {
		gotoTrash(commentId);
		
		return jdbcTemplate.update(QUERY_DELETECOMMENT, commentId);
	}
	
	private void gotoTrash(int commentId) {
		jdbcTemplate.update(QUERY_GOTOTRASH, commentId);
	}
	
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		return jdbcTemplate.update(QUERY_MODIFYCOMMENT, modifyCommentVO.getContents(), modifyCommentVO.getId());
	}
	
	public void deleteCommentByPostId(int postId) {
		gotoTrashByPostId(postId);
		jdbcTemplate.update(QUERY_DELETECOMMENTBYPOSTID, postId);
	}
	
	private void gotoTrashByPostId(int postId) {
		jdbcTemplate.update(QUERY_GOTOTRASHBYPOSTID, postId);
	}
}
