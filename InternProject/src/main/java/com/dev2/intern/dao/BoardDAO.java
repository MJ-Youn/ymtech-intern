package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.BoardVO;

@Repository
public class BoardDAO extends GenericDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 첫 페이지를 결정하기 위해 board_id의 최소값을 가져오는 함수
	 * 
	 * @return board table에서 가장 작은 id
	 */
	public int calculateBoardId() {
		String query = getQuery("board.calculateBoardId");
		
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	/**
	 * header에 표시할 board의 list를 가져오는 함수
	 * 
	 * @return board의 list
	 */
	public ArrayList<BoardVO> listUpBoard() {
		String query = getQuery("board.listUpBoard");
		
		return (ArrayList<BoardVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}
}
