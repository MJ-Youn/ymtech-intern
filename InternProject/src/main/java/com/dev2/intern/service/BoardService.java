package com.dev2.intern.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.BoardDAO;
import com.dev2.intern.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public int calculateBoardId() {
		return boardDAO.calculateBoardId();
	}
	
	public ArrayList<BoardVO> listUpBoard() {
		return boardDAO.listUpBoard();
	}
}
