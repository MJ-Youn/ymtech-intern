package com.dev2.intern.dao;

import java.util.ArrayList;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

public interface ICommentDAO {

	/**
	 * postId에 해당하는 comment를 가져오기 위한 함수
	 * 
	 * @param postId
	 * 			대상 post id
	 * @return comment의 list
	 */
	public ArrayList<CommentVO> getCommentByPost(String postId);
	
	/**
	 * comment를 추가해주는 함수
	 * 
	 * @param writeCommentVO
	 * 			입력받은 comment의 데이터. parentCommentId, postId, userId, depth, contents를 가지고 있다.
	 * @return 성공적으로 삽입하면 1, 아니면 0
	 */
	public int writeComment(WriteCommentVO writeCommentVO);
	
	/**
	 * 일반 유저가 comment를 삭제하기 위한 함수 
	 * 
	 * @param commentId
	 * 			삭제하기 위한 comment의 id
	 * @return 성공적으로 삭제하면 1, 아니면 0
	 */
	public int deleteComment(int commentId);
	
	/**
	 * 일반 유저가 comment를 삭제할때 데이터를 trash로 이전할때 사용하는 함수
	 * 
	 * @param commentId
	 * 			이전할 comment의 id
	 */
	public void gotoTrash(int commentId);
	
	/**
	 * comment를 수정하기 위한 함수
	 * 
	 * @param modifyCommentVO
	 * 			수정하려고 하는 comment의 데이터. id, content를 가지고 있다.
	 * @return 성공적으로 수정하면 1, 아니면 0
	 */
	public int modifyComment(ModifyCommentVO modifyCommentVO);
	
	/**
	 * 일반 유저가 post 삭제시 post에 달려있는 comment를 같이 삭제하기 위한 함수
	 * 
	 * @param postId
	 * 			삭제하는 post의 id
	 */
	public void deleteCommentByPostId(int postId);
	
	/**
	 * 일반 유저가 comment를 삭제할때 데이터를 trash로 이전할때 사용하는 함수
	 * 
	 * @param postId
	 * 			삭제하는 post의 id
	 */
	public void gotoTrashByPostId(int postId);
}
