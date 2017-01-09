package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.BoardDAO;
import com.dev2.intern.vo.PostVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public Map<Object, Object> getPageCount(String boardNumber) {
		Map<Object, Object> pageCount = boardDAO.getPageCount(boardNumber);
		
		return pageCount;
	}
	
	public ArrayList<PostVO> getPostList(String boardNumber, String pageNumber) {
		ArrayList<PostVO> postList = boardDAO.getPostList(boardNumber, pageNumber);
		
		return postList;
	}
	
}
