package com.dev2.intern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.PostVO;

@Repository
public class PostDAO {

	private final int LIMIT_POST_COUNT_BY_PAGE = 15;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public Map<Object, Object> countPageNumber(String boardNumber) {
		String sql = "SELECT COUNT(*) FROM post WHERE board_id = ?";
		
		int postCount = jdbcTemplate.queryForObject(sql, Integer.class, boardNumber);
		int pageCount = (postCount-1) / LIMIT_POST_COUNT_BY_PAGE + 1;
		
		Map<Object, Object> mapPageCount = new HashMap<Object, Object>();
		
		mapPageCount.put("pageCount", pageCount);
		
		return mapPageCount;
	}
	
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber) {
		int startIndex = (Integer.parseInt(pageNumber)-1) * LIMIT_POST_COUNT_BY_PAGE;
		String sql = "SELECT p.*, u.name user_name FROM post p, user u"
						+ " WHERE board_id = ? AND p.user_id = u.id"
						+ " ORDER BY post_number DESC"
						+ " LIMIT ?, ?";
		
		ArrayList<PostVO> postList = (ArrayList<PostVO>)jdbcTemplate.query(sql, new BeanPropertyRowMapper<PostVO>(PostVO.class), boardNumber, startIndex, LIMIT_POST_COUNT_BY_PAGE);
		
		return postList;
	}
	
	public PostVO getPostById(String postId) {
		countUpHitCount(postId);
		
		String sql = "SELECT p.*, u.name user_name FROM post p, user u WHERE p.id = ?";
		
		PostVO postVO = (PostVO)jdbcTemplate.queryForObject(sql, new RowMapper<PostVO>() {
			public PostVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostVO postVO = new PostVO();
				postVO.setId(rs.getInt("id"))
						.setUserId(rs.getInt("user_id"))
						.setPostNumber(rs.getInt("post_number"))
						.setTitle(rs.getString("title"))
						.setContents(rs.getString("contents"))
						.setHitCount(rs.getInt("hit_count"))
						.setCreateDate(rs.getDate("create_date"))
						.setModifyDate(rs.getDate("modify_date"))
						.setUserName(rs.getString("user_name"));
				
				return postVO;
			}
		}, postId);
		
		return postVO;
	}
	
	private void countUpHitCount(String postId) {
		String sql = "UPDATE post SET hit_count = hit_count + 1 WHERE id = ?";
		
		jdbcTemplate.update(sql, postId);
	}
}
