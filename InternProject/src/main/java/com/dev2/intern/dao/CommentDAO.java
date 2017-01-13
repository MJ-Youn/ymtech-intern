package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Repository
public class CommentDAO extends GenericDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * postId에 해당하는 comment를 가져오기 위한 함수
	 * 
	 * @param postId
	 * 			대상 post id
	 * @return comment의 list
	 */
	public ArrayList<CommentVO> listUpComment(String postId) {
		String query = getQuery("comment.listUpComment");
		
		ArrayList<CommentVO> unSortCommentList = (ArrayList<CommentVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<CommentVO>(CommentVO.class), postId);
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
	
	/**
	 * comment를 추가해주는 함수
	 * 
	 * @param writeCommentVO
	 * 			입력받은 comment의 데이터. parentCommentId, postId, userId, depth, contents를 가지고 있다.
	 * @return 성공적으로 삽입하면 1, 아니면 0
	 */
	public int writeComment(WriteCommentVO writeCommentVO) {
		String query = getQuery("comment.writeComment");
		
		return jdbcTemplate.update(query, writeCommentVO.getParentCommentId(), writeCommentVO.getPostId(), writeCommentVO.getUserId(), writeCommentVO.getDepth(), writeCommentVO.getContents());
	}
	
	/**
	 * 일반 유저가 comment를 삭제하기 위한 함수 
	 * 
	 * @param commentId
	 * 			삭제하기 위한 comment의 id
	 * @return 성공적으로 삭제하면 1, 아니면 0
	 */
	public int deleteComment(int commentId) {
		String query = getQuery("comment.deleteComment");
		
		gotoTrash(commentId);
		
		return jdbcTemplate.update(query, commentId);
	}
	
	/**
	 * 일반 유저가 comment를 삭제할때 데이터를 trash로 이전할때 사용하는 함수
	 * 
	 * @param commentId
	 * 			이전할 comment의 id
	 */
	private void gotoTrash(int commentId) {
		String query = getQuery("comment.gotoTrash");
		
		jdbcTemplate.update(query, commentId);
	}
	
	/**
	 * comment를 수정하기 위한 함수
	 * 
	 * @param modifyCommentVO
	 * 			수정하려고 하는 comment의 데이터. id, content를 가지고 있다.
	 * @return 성공적으로 수정하면 1, 아니면 0
	 */
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		String query = getQuery("comment.modifyComment");
		
		return jdbcTemplate.update(query, modifyCommentVO.getContents(), modifyCommentVO.getId());
	}
	
	/**
	 * 일반 유저가 post 삭제시 post에 달려있는 comment를 같이 삭제하기 위한 함수
	 * 
	 * @param postId
	 * 			삭제하는 post의 id
	 */
	public void deleteCommentByPostId(int postId) {
		String query = getQuery("comment.deleteCommentByPostId");
		
		gotoTrashByPostId(postId);
		jdbcTemplate.update(query, postId);
	}
	
	/**
	 * 일반 유저가 comment를 삭제할때 데이터를 trash로 이전할때 사용하는 함수
	 * 
	 * @param postId
	 * 			삭제하는 post의 id
	 */
	private void gotoTrashByPostId(int postId) {
		String query = getQuery("comment.gotoTrashByPostId");
		
		jdbcTemplate.update(query, postId);
	}
}
