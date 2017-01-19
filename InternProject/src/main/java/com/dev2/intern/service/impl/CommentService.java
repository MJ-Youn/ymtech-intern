package com.dev2.intern.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.CommentDAO;
import com.dev2.intern.service.ICommentService;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.WriteCommentVO;

@Service(CommentService.BEAN_QUALIFIER)
public class CommentService implements ICommentService {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.CommentService";
	
	@Autowired
	@Qualifier(CommentDAO.BEAN_QUALIFIER)
	private CommentDAO commentDAO;
	
	@Override
	public ArrayList<CommentVO> listUpComment(String postId) {
		ArrayList<CommentVO> unSortedCommentList = commentDAO.getCommentByPost(postId);
		ArrayList<CommentVO> sortedCommentList = sortComment(unSortedCommentList);
		
		return sortedCommentList;
	}
	
	/**
	 * DB에서 가져온 commentList들을 parent, child 순서에 맞게 정렬하기 위한 함수
	 * 
	 * @param unsortCommentList
	 * 			정렬이 되지 않은 commentList
	 * @return parent, child 순서대로 정렬이된 commentList
	 */
	private ArrayList<CommentVO> sortComment(ArrayList<CommentVO> unsortedCommentList) {
		ArrayList<CommentVO> sortedCommentList = new ArrayList<CommentVO>();
		
		for (int i = 0 ; i < unsortedCommentList.size() ; i++) {
			if (unsortedCommentList.get(i).getParentCommentId() < 0) {
				sortedCommentList.add(unsortedCommentList.get(i));
			} else {
				int parentCommentId = unsortedCommentList.get(i).getParentCommentId();
				int parentCommentIndex = searchNextParentCommentIndex(sortedCommentList, parentCommentId);
				
				sortedCommentList.add(parentCommentIndex, unsortedCommentList.get(i));
			}
		}
		
		return sortedCommentList;
	}
	
	/**
	 * commentList에서 parentCommentId의 index값을 구하기 위한 함수
	 * 
	 * @param commentList
	 * 			대상이 되는 commentList
	 * @param parentCommentId
	 * 			찾으려고 하는 parentCommentId
	 * @return parentCommentId가 위치해 있는 index
	 */
	private int searchNextParentCommentIndex(ArrayList<CommentVO> commentList, int parentCommentId) {
		int nextParentCommentIndex = -1;
		int i;
		
		for (i = 0 ; i < commentList.size() ; i++) {
			if (commentList.get(i).getId() == parentCommentId) {
				break;
			}
		}
		
		for (i = i + 1 ; i < commentList.size() ; i++) {
			if (commentList.get(i).getParentCommentId() < parentCommentId) {
				nextParentCommentIndex = i;
				break;
			}
		}
		
		if (nextParentCommentIndex < 0) {
			return commentList.size();
		} else {
			return nextParentCommentIndex;
		}
	} 
	
	@Override
	public int writeComment(WriteCommentVO writeCommentVO) {
		return commentDAO.writeComment(writeCommentVO);
	}
	
	@Override
	public int deleteComment(int commentId) {
		commentDAO.gotoTrash(commentId);
		return commentDAO.deleteComment(commentId);
	}
	
	@Override
	public int modifyComment(ModifyCommentVO modifyCommentVO) {
		return commentDAO.modifyComment(modifyCommentVO);
	}
}
