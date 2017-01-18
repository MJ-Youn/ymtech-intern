package com.dev2.intern.service;

import java.util.ArrayList;

import com.dev2.intern.vo.BoardVO;

public interface IBoardService {

	public int calculateBoardId();
	
	public ArrayList<BoardVO> listUpBoard();
	
}
