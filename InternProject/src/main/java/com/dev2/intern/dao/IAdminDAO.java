package com.dev2.intern.dao;

import java.util.ArrayList;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.UserVO;

public interface IAdminDAO {

	public ArrayList<PostVO> getAllPost();
	
	public ArrayList<FileVO> getAllFile();
	
	public ArrayList<CommentVO> getAllComment();
	
	public ArrayList<UserVO> getAllUser();
	
	public ArrayList<PostVO> getAllTrashPost();
	
	public ArrayList<FileVO> getAllTrashFile();
	
	public ArrayList<CommentVO> getAllTrashComment();
	
	public ArrayList<UserVO> getAllTrashUser();
	
	public int deleteTrashPost(PostVO postVO);
	
	public int deleteTrashFile(FileVO fileVO);
	
	public int deleteTrashComment(CommentVO commentVO);
	
	public int deleteTrashUser(UserVO userVO);
	
	public int modifyUserLevel(String userId, String level);
}
