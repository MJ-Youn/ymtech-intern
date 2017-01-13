package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.CommentDAO;
import com.dev2.intern.dao.PostDAO;
import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;

@Service
public class PostService {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	public Map<Object, Object> countPageNumber(String boardNumber) {
		return postDAO.countPageNumber(boardNumber);
	}
	
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber) {
		return postDAO.listUpPost(boardNumber, pageNumber);
	}
	
	public PostVO getPostById(String postId) {
		return postDAO.getPostById(postId);
	}
	
	public int postPost(WritePostVO writePostVO) {
		return postDAO.postPost(writePostVO);
	}
	
	public int deletePost(int postId) {
		commentDAO.deleteCommentByPostId(postId);
		return postDAO.deletePost(postId);
	}
	
	public int modifyPost(ModifyPostVO modifyPostVO) {
		return postDAO.modifyPost(modifyPostVO);
	}
}
