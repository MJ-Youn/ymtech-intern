package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.PostDAO;
import com.dev2.intern.vo.PostVO;

@Service
public class PostService {
	
	@Autowired
	private PostDAO postDAO;

	public Map<Object, Object> countPageNumber(String boardNumber) {
		return postDAO.countPageNumber(boardNumber);
	}
	
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber) {
		return postDAO.listUpPost(boardNumber, pageNumber);
	}
	
	public PostVO getPostById(String postId) {
		return postDAO.getPostById(postId);
	}
}
