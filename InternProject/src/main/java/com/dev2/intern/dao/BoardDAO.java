package com.dev2.intern.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.PostVO;

@Repository
public class BoardDAO {

	private final int LIMIT_POST_COUNT_BY_PAGE = 15;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<Object, Object> getPageCount(String boardNumber) {
		String sql = "SELECT COUNT(*) FROM post WHERE board_id = " + boardNumber;
		
		int postCount = jdbcTemplate.queryForObject(sql, Integer.class);
		int pageCount = (postCount-1) / LIMIT_POST_COUNT_BY_PAGE + 1;
		
		Map<Object, Object> mapPageCount = new HashMap<Object, Object>();
		
		mapPageCount.put("pageCount", pageCount);
		
		return mapPageCount;
	}
	
	public ArrayList<PostVO> getPostList(String boardNumber, String pageNumber) {
		int startIndex = (Integer.parseInt(pageNumber)-1) * LIMIT_POST_COUNT_BY_PAGE;
		String sql = "SELECT * FROM post "
						+ "WHERE board_id = " + boardNumber
						+ " LIMIT " + startIndex + ", " + LIMIT_POST_COUNT_BY_PAGE;
		ArrayList<PostVO> postList = (ArrayList<PostVO>)jdbcTemplate.query(sql, new BeanPropertyRowMapper<PostVO>(PostVO.class));
		
		return postList;
	}
	
}
