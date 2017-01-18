package com.dev2.intern.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.BoardDAO;
import com.dev2.intern.service.IBoardService;
import com.dev2.intern.vo.BoardVO;

@Service(BoardService.BEAN_QUALIFIER)
public class BoardService implements IBoardService {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.BoardService";
	
	@Autowired
	@Qualifier(BoardDAO.BEAN_QUALIFIER)
	private BoardDAO boardDAO;
	
	@Override
	public int calculateBoardId() {
		return boardDAO.calculateBoardId();
	}
	
	@Override
	public ArrayList<BoardVO> listUpBoard() {
		return boardDAO.listUpBoard();
	}
}
