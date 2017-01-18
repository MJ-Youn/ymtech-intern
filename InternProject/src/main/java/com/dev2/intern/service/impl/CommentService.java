package com.dev2.intern.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.CommentDAO;
import com.dev2.intern.service.ICommentService;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Service(CommentService.BEAN_QUALIFIER)
public class CommentService implements ICommentService {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.CommentService";
	
	@Autowired
	@Qualifier(CommentDAO.BEAN_QUALIFIER)
	private CommentDAO commentDAO;
	
	@Override
	public ArrayList<CommentVO> listUpComment(String postId) {
		return commentDAO.listUpComment(postId);
	}
	
	@Override
	public int writeComment(WriteCommentVO writeCommentVO) {
		return commentDAO.writeComment(writeCommentVO);
	}
	
	@Override
	public int deleteComment(int commentId) {
		return commentDAO.deleteComment(commentId);
	}
	
	@Override
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		return commentDAO.modifyComment(modifyCommentVO);
	}
}
