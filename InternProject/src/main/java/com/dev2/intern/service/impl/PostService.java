package com.dev2.intern.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.CommentDAO;
import com.dev2.intern.dao.impl.PostDAO;
import com.dev2.intern.service.IPostService;
import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.WritePostVO;

@Service(PostService.BEAN_QUALIFIER)
public class PostService implements IPostService {
	
	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.PostService";
	public static final int LIMIT_POST_COUNT_BY_PAGE = 15;
	
	@Autowired
	@Qualifier(PostDAO.BEAN_QUALIFIER)
	private PostDAO postDAO;
	
	@Autowired
	@Qualifier(CommentDAO.BEAN_QUALIFIER)
	private CommentDAO commentDAO;
	
	@Override
	public Map<Object, Object> countPageNumber(String boardNumber) {
		int postCount = postDAO.countPost(boardNumber);
		int pageCount = (postCount-1) / LIMIT_POST_COUNT_BY_PAGE + 1;
		
		Map<Object, Object> mapPageCount = new HashMap<Object, Object>();
		
		mapPageCount.put("pageCount", pageCount);
		
		return mapPageCount;
	}
	
	@Override
	public ArrayList<PostVO> listUpPost(String boardNumber, String pageNumber) {
		int startIndex = (Integer.parseInt(pageNumber)-1) * LIMIT_POST_COUNT_BY_PAGE;
		
		return postDAO.listUpPost(boardNumber, pageNumber, startIndex, LIMIT_POST_COUNT_BY_PAGE);
	}
	
	@Override
	public PostVO getPostById(String postId) {
		postDAO.countUpHitCount(postId);
		return postDAO.getPostById(postId);
	}
	
	@Override
	public int postPost(WritePostVO writePostVO) {
		return postDAO.postPost(writePostVO);
	}
	
	@Override
	public int deletePost(int postId) {
		commentDAO.gotoTrashByPostId(postId);
		commentDAO.deleteCommentByPostId(postId);
		
		postDAO.gotoTrash(postId);
		return postDAO.deletePost(postId);
	}
	
	@Override
	public int modifyPost(ModifyPostVO modifyPostVO) {
		return postDAO.modifyPost(modifyPostVO);
	}
}
