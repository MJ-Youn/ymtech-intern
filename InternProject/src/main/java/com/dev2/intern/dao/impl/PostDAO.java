package com.dev2.intern.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev2.intern.dao.IPostDAO;
import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;
import com.mysql.jdbc.Statement;

@Repository(PostDAO.BEAN_QUALIFIER)
public class PostDAO extends GenericDAO implements IPostDAO {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.dao.impl.PostDAO";
	
	@Override
	public int countPost(String boardNumber) {
		String query = getQuery("post.countPageNumber");

		return jdbcTemplate.queryForObject(query, Integer.class, boardNumber);
	}
	
	@Override
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber, int startIndex, int limitPostCountByPage) {
		String query = getQuery("post.listUpPost");
		
		return (ArrayList<PostVO>)jdbcTemplate.query(query, new BeanPropertyRowMapper<PostVO>(PostVO.class), boardNumber, startIndex, limitPostCountByPage);
	}
	
	@Override
	public PostVO getPostById(String postId) {
		String query = getQuery("post.getPostById");
		
		PostVO postVO = (PostVO)jdbcTemplate.queryForObject(query, new RowMapper<PostVO>() {
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
	
	@Override
	public void countUpHitCount(String postId) {
		String query = getQuery("post.countUpHitCount");
		
		jdbcTemplate.update(query, postId);
	}
	
	@Override
	public int postPost(final WritePostVO writePostVO) {
		final String query = getQuery("post.postPost");
		final int postNumber = calculateLastPostNumber(writePostVO.getBoardId());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setInt(1, writePostVO.getUserId());
				preparedStatement.setInt(2, postNumber);
				preparedStatement.setInt(3, writePostVO.getBoardId());
				preparedStatement.setString(4, writePostVO.getTitle());
				preparedStatement.setString(5, writePostVO.getContents());
				
				return preparedStatement;
			}
		}, keyHolder);
		
		int postId = keyHolder.getKey().intValue();
		
		return postId;
	}
	
	@Override
	public int calculateLastPostNumber(int boardNumber) {
		String query = getQuery("post.calculateLastPostNumber");
		
		try {
			return jdbcTemplate.queryForObject(query, Integer.class, boardNumber) + 1;
		} catch (NullPointerException npe) {
			return 1;
		}
	}
	
	@Override
	public int deletePost(int postId) {
		String query = getQuery("post.deletePost");
		
		return jdbcTemplate.update(query, postId);
	}
	
	@Override
	public void gotoTrash(int postId) {
		String query = getQuery("post.gotoTrash");
		
		jdbcTemplate.update(query, postId);
	}
	
	@Override
	public int modifyPost(ModifyPostVO modifyPostVO) {
		String query = getQuery("post.modifyPost");
		
		return jdbcTemplate.update(query, modifyPostVO.getTitle(), modifyPostVO.getContents(), modifyPostVO.getId());
	}
}
