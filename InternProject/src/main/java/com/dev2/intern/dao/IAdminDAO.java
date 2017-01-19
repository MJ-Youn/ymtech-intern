package com.dev2.intern.dao;

import java.util.ArrayList;

import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.UserVO;

/**
 * admin page에서 사용되는 DAO
 * 
 * @author MJYoun
 *
 */
public interface IAdminDAO {

	/**
	 * 모든 게시글을 가져오기 위한 함수
	 * 
	 * @return 모든 게시글
	 */
	public ArrayList<PostVO> getAllPost();
	
	/**
	 * 모든 파일을 가져오기 위한 함수
	 * 
	 * @return 모든 파일
	 */
	public ArrayList<FileVO> getAllFile();
	
	/**
	 * 모든 댓글을 가져오기 위한 함수
	 * 
	 * @return 모든 댓글
	 */
	public ArrayList<CommentVO> getAllComment();
	
	/**
	 * 모든 유저를 가져오기 위한 함수
	 * 
	 * @return 모든 유저
	 */
	public ArrayList<UserVO> getAllUser();
	
	/**
	 * trash 테이블에 있는 모든 게시글을 가져오기 위한 함수
	 * 
	 * @return trash 테이블의 모든 게시글
	 */
	public ArrayList<PostVO> getAllTrashPost();
	
	/**
	 * trash 테이블에 있는 모든 파일을 가져오기 위한 함수
	 * 
	 * @return trash 테이블의 모든 파일
	 */
	public ArrayList<FileVO> getAllTrashFile();
	
	/**
	 * trash 테이블에 있는 모든 댓글을 가져오기 위한 함수
	 * 
	 * @return trash 테이블의 모든 댓글
	 */
	public ArrayList<CommentVO> getAllTrashComment();
	
	/**
	 * trash 테이블에 있는 모든 유저를 가져오기 위한 함수
	 * 
	 * @return trash 테이블의 모든 유저
	 */
	public ArrayList<UserVO> getAllTrashUser();
	
	/**
	 * trash post 테이블에 있는 정보를 삭제 하기 위한 함수
	 * 
	 * @param postVO
	 * 			삭제하려고 하는 게시글의 정보
	 * @return 정상적으로 삭제되면 1, 아니면 0
	 */
	public int deleteTrashPost(PostVO postVO);
	
	/**
	 * trash file 테이블에 있는 정보를 삭제 하기 위한 함수
	 * 
	 * @param fileVO
	 * 			삭제하려고 하는 파일의 정보
	 * @return 정상적으로 삭제되면 1, 아니면 0
	 */
	public int deleteTrashFile(FileVO fileVO);
	
	/**
	 * trash comment 테이블에 있는 정보를 삭제 하기 위한 함수
	 * 
	 * @param commentVO
	 * 			삭제하려고 하는 댓글의 정보
	 * @return 정상적으로 삭제되면 1, 아니면 0
	 */
	public int deleteTrashComment(CommentVO commentVO);
	
	/**
	 * trash user 테이블에 있는 정보를 삭제 하기 위한 함수
	 * 
	 * @param userVO
	 * 			삭제하려고 하는 유저의 정보
	 * @return 정상적으로 삭제되면 1, 아니면 0
	 */
	public int deleteTrashUser(UserVO userVO);
	
	/**
	 * 유저의 레벨을 수정하기 위한 함수
	 * 
	 * @param userId
	 * 			수정하려는 유저의 id
	 * @param level
	 * 			수정하려는 레벨
	 * @return 정상적으로 수정되면 1, 아니면 0
	 */
	public int modifyUserLevel(String userId, String level);
}
