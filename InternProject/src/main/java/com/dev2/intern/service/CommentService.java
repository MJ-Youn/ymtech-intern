package com.dev2.intern.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.CommentDAO;
import com.dev2.intern.vo.CommentVO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	public ArrayList<CommentVO> listUpComment(String postId) {
		return commentDAO.listUpComment(postId);
	}
}
