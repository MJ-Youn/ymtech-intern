package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.BoardVO;

@Repository
public class TestDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void test() {
		String query = "SELECT * FROM board";
		ArrayList<BoardVO> boardVo = (ArrayList<BoardVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<BoardVO>(BoardVO.class)); 

		boardVo.stream()
				.forEach(System.out::println);
	}
}
