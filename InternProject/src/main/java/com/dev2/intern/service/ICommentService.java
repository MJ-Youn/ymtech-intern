package com.dev2.intern.service;

import java.util.ArrayList;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

public interface ICommentService {

	public ArrayList<CommentVO> listUpComment(String postId);
	
	public int writeComment(WriteCommentVO writeCommentVO);
	
	public int deleteComment(int commentId);
	
	public int modifyComment(ModifyCommentVO modifyCommentVO);
}
