package com.dev2.intern.dao;

import java.util.ArrayList;
import java.util.Map;

import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;

public interface IPostDAO {

	/**
	 * 해당 게시판에 page 개수를 구하기 위한 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판의 id
	 * @return "pageCount"를 key로 갖고 page 개수를 value로 갖는 map
	 * 			ResponseVO를 가지고 넘기기 위해 key, value 쌍을 갖는 object 형태로 return
	 */
	public Map<Object, Object> countPageNumber(String boardNumber);
	
	/**
	 * 해당 게시판의 현재 페이지에 있는 게시글을 찾기 위한 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판 id
	 * @param pageNumber
	 * 			현재 페이지 번호
	 * @return 게시글 리스트
	 */
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber);
	
	/**
	 * 게시글 하나를 화면에 보여주기 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글의 id
	 * @return 하나의 게시글
	 */
	public PostVO getPostById(String postId);
	
	/**
	 * 게시글을 눌렀을 때 조회수를 늘려주기 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글
	 */
	public void countUpHitCount(String postId);

	/**
	 * 새로운 글을 DB에 넣어주기 위한 함수
	 * 
	 * @param writePostVO
	 * 			새로 작성된 게시글. 유저 id, 게시판 id, 제목, 내용을 가지고 있음
	 * @return 정상적으로 INSERT가 됬으면 1, 아니면 0
	 */
	public int postPost(final WritePostVO writePostVO);
	
	/**
	 * 새로운 글을 작성하기 위해 해당 게시판의 마지막 게시글의 번호에 1을 더해 출력해주는 함수
	 * 
	 * @param boardNumber
	 * 			해당 게시판의 id
	 * @return 마지막 게시글 번호
	 * 			NullPointerException이 나면, 즉, 게시글이 없으면 1
	 */
	public int calculateLastPostNumber(int boardNumber);
	
	/**
	 * 일반 유저가 게시글을 삭제 위한 함수
	 * 
	 * @param postId
	 * 			해당 게시글의 id
	 * @return 정상적으로 DELETE가 됬으면 1, 아니면 0
	 */
	public int deletePost(int postId);
	
	/**
	 * 일반 유저가 게시글을 삭제 할 경우, 휴지통으로 옮기기 위한 함수
	 * 
	 * @param id
	 * 			옮겨갈 게시글의 id
	 */
	public void gotoTrash(int postId);
	
	/**
	 * 게시글 수정을 위한 함수
	 * 
	 * @param modifyPostVO
	 * 			수정할 게시글의 정보. 제목, 내용, 게시글의 id를 가지고 있음
	 * @return 정상적으로 UPDATE가 됬으면 1, 아니면 0
	 */
	public int modifyPost(ModifyPostVO modifyPostVO);
}
