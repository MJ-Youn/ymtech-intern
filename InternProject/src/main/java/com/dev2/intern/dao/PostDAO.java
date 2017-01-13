package com.dev2.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;
import com.mysql.jdbc.Statement;

@Repository
public class PostDAO {

	private final int LIMIT_POST_COUNT_BY_PAGE = 15;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("#{query['post.countPageNumber']}")
	private String QUERY_COUNTPAGENUMBER;
	
	@Value("#{query['post.listUpPost']}")
	private String QUERY_LISTUPPOST;
	
	@Value("#{query['post.getPostById']}")
	private String QUERY_GETPOSTBYID;
	
	@Value("#{query['post.countUpHitCount']}")
	private String QUERY_COUNTUPHITCOUNT;
	
	@Value("#{query['post.postPost']}")
	private String QUERY_POSTPOST;
	
	@Value("#{query['post.calculateLastPostNumber']}")
	private String QUERY_CALCUALELASTPOSTNUMBER;
	
	@Value("#{query['post.deletePost']}")
	private String QUERY_DELETEPOST;
	
	@Value("#{query['post.gotoTrash']}")
	private String QUERY_GOTOTRASH;
	
	@Value("#{query['post.modifyPost']}")
	private String QUERY_MODIFYPOST;
	
	/**
	 * 해당 게시판에 page 개수를 구하기 위한 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판의 id
	 * @return "pageCount"를 key로 갖고 page 개수를 value로 갖는 map
	 * 			ResponseVO를 가지고 넘기기 위해 key, value 쌍을 갖는 object 형태로 return
	 */
	public Map<Object, Object> countPageNumber(String boardNumber) {
		int postCount = jdbcTemplate.queryForObject(QUERY_COUNTPAGENUMBER, Integer.class, boardNumber);
		int pageCount = (postCount-1) / LIMIT_POST_COUNT_BY_PAGE + 1;
		
		Map<Object, Object> mapPageCount = new HashMap<Object, Object>();
		
		mapPageCount.put("pageCount", pageCount);
		
		return mapPageCount;
	}
	
	/**
	 * 해당 게시판의 현재 페이지에 있는 게시글을 찾기 위한 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판 id
	 * @param pageNumber
	 * 			현재 페이지 번호
	 * @return 게시글 리스트
	 */
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber) {
		int startIndex = (Integer.parseInt(pageNumber)-1) * LIMIT_POST_COUNT_BY_PAGE;
		
		ArrayList<PostVO> postList = (ArrayList<PostVO>)jdbcTemplate.query(QUERY_LISTUPPOST, new BeanPropertyRowMapper<PostVO>(PostVO.class), boardNumber, startIndex, LIMIT_POST_COUNT_BY_PAGE);
		
		return postList;
	}
	
	/**
	 * 게시글 하나를 화면에 보여주기 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글의 id
	 * @return 하나의 게시글
	 */
	public PostVO getPostById(String postId) {
		countUpHitCount(postId);
		
		PostVO postVO = (PostVO)jdbcTemplate.queryForObject(QUERY_GETPOSTBYID, new RowMapper<PostVO>() {
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
	
	/**
	 * 게시글을 눌렀을 때 조회수를 늘려주기 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글
	 */
	private void countUpHitCount(String postId) {
		jdbcTemplate.update(QUERY_COUNTUPHITCOUNT, postId);
	}
	
	/**
	 * 새로운 글을 DB에 넣어주기 위한 함수
	 * 
	 * @param writePostVO
	 * 			새로 작성된 게시글. 유저 id, 게시판 id, 제목, 내용을 가지고 있음
	 * @return 정상적으로 INSERT가 됬으면 1, 아니면 0
	 */
	public int postPost(final WritePostVO writePostVO) {
		final int postNumber = calculateLastPostNumber(writePostVO.getBoardId());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(QUERY_POSTPOST, Statement.RETURN_GENERATED_KEYS);
				
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
	
	/**
	 * 새로운 글을 작성하기 위해 해당 게시판의 마지막 게시글의 번호에 1을 더해 출력해주는 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판의 id
	 * @return 마지막 게시글 번호
	 * 			NullPointerException이 나면, 즉, 게시글이 없으면 1
	 */
	private int calculateLastPostNumber(int boardNumber) {
		try {
			return jdbcTemplate.queryForObject(QUERY_CALCUALELASTPOSTNUMBER, Integer.class, boardNumber) + 1;
		} catch (NullPointerException npe) {
			return 1;
		}
	}
	
	/**
	 * 일반 유저가 게시글을 삭제 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글의 id
	 * @return 정상적으로 DELETE가 됬으면 1, 아니면 0
	 */
	public int deletePost(int postId) {
		gotoTrash(postId);
		
		return jdbcTemplate.update(QUERY_DELETEPOST, postId);
	}
	
	/**
	 * 일반 유저가 게시글을 삭제 할 경우, 휴지통으로 옮기기 위한 함수
	 * 
	 * @param id
	 * 			옮겨갈 게시글의 id
	 */
	private void gotoTrash(int postId) {
		jdbcTemplate.update(QUERY_GOTOTRASH, postId);
	}
	
	/**
	 * 게시글 수정을 위한 함수
	 * 
	 * @param modifyPostVO
	 * 			수정할 게시글의 정보. 제목, 내용, 게시글의 id를 가지고 있음
	 * @return 정상적으로 UPDATE가 됬으면 1, 아니면 0
	 */
	public int modifyPost(ModifyPostVO modifyPostVO) {
		return jdbcTemplate.update(QUERY_MODIFYPOST, modifyPostVO.getTitle(), modifyPostVO.getContents(), modifyPostVO.getId());
	}
}
