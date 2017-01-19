package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;

public interface IPostService {

	/**
	 * 게시판에 페이지 수를 계산하기 위한 함수
	 * 
	 * @param boardNumber
	 * 			대상 게시판 번호
	 * @return 게시판의 총 페이지수를 map형태로 반환
	 */
	public Map<Object, Object> countPageNumber(String boardNumber);
	
	/**
	 * 게시판의 현재페이지에 존재하는 게시글을 가져오기 위한 함수
	 * 
	 * @param boardNumber
	 * 			대상 게시판 번호
	 * @param pageNumber
	 * 			현재 페이지 번호
	 * @return 현재 표시되어야 할 게시글들의 리스트
	 */
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber);
	
	public PostVO getPostById(String postId);
	
	public int postPost(WritePostVO writePostVO);
	
	public int deletePost(int postId);
	
	public int modifyPost(ModifyPostVO modifyPostVO);
}
