package com.dev2.intern.dao;

import java.util.ArrayList;

import com.dev2.intern.vo.BoardVO;

public interface IBoardDAO {
	
	/**
	 * 첫 페이지를 결정하기 위해 board_id의 최소값을 가져오는 함수
	 * 
	 * @return board table에서 가장 작은 id
	 */
	public int calculateBoardId();
	
	/**
	 * header에 표시할 board의 list를 가져오는 함수
	 * 
	 * @return board의 list
	 */
	public ArrayList<BoardVO> listUpBoard();
	
}
