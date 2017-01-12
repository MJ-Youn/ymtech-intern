package com.dev2.intern.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.CommentDAO;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	public ArrayList<CommentVO> listUpComment(String postId) {
		return commentDAO.listUpComment(postId);
	}
	
	public int writeComment(WriteCommentVO writeCommentVO) {
		return commentDAO.writeComment(writeCommentVO);
	}
	
	public int deleteComment(int commentId) {
		return commentDAO.deleteComment(commentId);
	}
	
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		return commentDAO.modifyComment(modifyCommentVO);
	}
}
