package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int calculateBoardId() {
		String sql = "SELECT MIN(id) FROM board";
		
		int firstBoardId = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return firstBoardId;
	}
	
	public ArrayList<BoardVO> listUpBoard() {
		String sql = "SELECT * FROM board ORDER BY id";
		
		ArrayList<BoardVO> boardList = (ArrayList<BoardVO>)jdbcTemplate.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
		
		return boardList;
	}
}
