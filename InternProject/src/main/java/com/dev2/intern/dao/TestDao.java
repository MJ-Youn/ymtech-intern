package com.dev2.intern.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.BoardVo;

@Repository
public class TestDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void test() {
		String query = "SELECT * FROM board";
		ArrayList<BoardVo> boardVo = (ArrayList<BoardVo>)jdbcTemplate.query(query, new BeanPropertyRowMapper<BoardVo>(BoardVo.class)); 

		boardVo.stream()
				.forEach(System.out::println);
	}
}
