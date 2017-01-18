package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;

public interface IPostService {

	public Map<Object, Object> countPageNumber(String boardNumber);
	
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber);
	
	public PostVO getPostById(String postId);
	
	public int postPost(WritePostVO writePostVO);
	
	public int deletePost(int postId);
	
	public int modifyPost(ModifyPostVO modifyPostVO);
}
