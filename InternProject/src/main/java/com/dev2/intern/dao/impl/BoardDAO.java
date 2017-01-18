package com.dev2.intern.dao.impl;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.dev2.intern.dao.IBoardDAO;
import com.dev2.intern.vo.BoardVO;

@Repository(BoardDAO.BEAN_QUALIFIER)
public class BoardDAO extends GenericDAO implements IBoardDAO {
	
	public static final String BEAN_QUALIFIER = "com.dev2.intern.dao.impl.BoardDAO";
	
	@Override
	public int calculateBoardId() {
		String query = getQuery("board.calculateBoardId");
		
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	@Override
	public ArrayList<BoardVO> listUpBoard() {
		String query = getQuery("board.listUpBoard");
		
		return (ArrayList<BoardVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}
}
