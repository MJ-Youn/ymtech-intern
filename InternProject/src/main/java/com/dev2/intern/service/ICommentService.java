package com.dev2.intern.service;

import java.util.ArrayList;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

public interface ICommentService {

	/**
	 * 해당하는 게시글의 정렬된 댓글을 가져오기위한 함수
	 * 
	 * @param postId
	 * 			대상 게시글 번호
	 * @return 정렬된 댓글의 리스트
	 */
	public ArrayList<CommentVO> listUpComment(String postId);
	
	public int writeComment(WriteCommentVO writeCommentVO);
	
	public int deleteComment(int commentId);
	
	public int modifyComment(ModifyCommentVO modifyCommentVO);
}
