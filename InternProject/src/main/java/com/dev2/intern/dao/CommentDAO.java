package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.CommentVO;

@Repository
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("#{query['comment.listUpComment']}")
	private String QUERY_LISTUPCOMMENT;
	
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
				int parentCommentIndex = searchParentCommentIndex(sortedCommentList, parentCommentId);
				
				sortedCommentList.add(parentCommentIndex+1, unsortCommentList.get(i));
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
	private int searchParentCommentIndex(ArrayList<CommentVO> commentList, int parentCommentId) {
		int parentCommentIndex = -1;
		
		for (int i = 0 ; i < commentList.size() ; i++) {
			if (commentList.get(i).getId() == parentCommentId) {
				parentCommentIndex = i;
			}
		}
		
		return parentCommentIndex;
	}
}
