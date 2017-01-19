package com.dev2.intern.dao;

import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.UserVO;

public interface IUserDAO {

	/**
	 * 유저정보를 가져와 DB에 저장하는 함수
	 * 
	 * @param createUserVO
	 * 			유저정보가 담긴 VO. email, password, name 정보가 들어있다.
	 * @return 정상적으로 끝나면 1, 아니면 0
	 */
	public int createUser(CreateUserVO createUserVO);
	
	/**
	 * 사용중인 이메일인지 확인하는 함수
	 * 
	 * @param email
	 * 			확인하려는 email
	 * @return 사용죽인 메일이라면 1, 아니면 0
	 */
	public int checkExistUser(String email);
	
	/**
	 * email과 password가 서로 pair인지 확인하는 함수
	 * 
	 * @param email
	 * 			로그인하는 유저의 email
	 * @param password
	 * 			로그인하는 유저의 password
	 * @return 성공적으로 끝나면 1, 아니면 0
	 */
	public int checkValidLogin(String email, String password);
	
	/**
	 * email을 가지고 유저의 level을 가져오기 위한 함수
	 * 
	 * @param email
	 * 			확인하려고하는 유저의 email
	 * @return 성공적으로 끝나면 해당 유저의 level, 아니면 -1
	 */
	public int extractLevel(String email);
	
	/**
	 * email을 가지고 유저정보를 가져오기 위한 함수
	 * 
	 * @param email
	 * 			가져오려는 유저의 email
	 * @return 유저의 정보가 담긴 UserVO. password를 제외하고 모든 정보를 가지고 있다.
	 */
	public UserVO getUserByEmail(String email);
	
	/**
	 * 유저정보를 수정하기 위한 함수
	 * 
	 * @param password
	 * 			수정할 패스워드
	 * @param name
	 * 			수정할 이름
	 * @param userId
	 * 			수정할 대상이 되는 유저의 id
	 * @return 정상적으로 수정되면 1, 아니면 0
	 */
	public int modifyUser(String password, String name, int userId);
	
	/**
	 * 유저를 삭제하기 위한 함수
	 * 실제로 table에 있는 정보를 지우는 것은 아니라, 지워진 유저라는 의미에 데이터로 덮어씌운다.
	 * 
	 * @param email
	 * 			삭제하려고 하는 유저의 email
	 * @return 정상적으로 삭제하면 1, 아니면 0
	 */
	public int deleteUser(String email);
	
	/**
	 * 삭제된 유저를 trash에 저장하기 위한 함수
	 * 
	 * @param email
	 * 			이동시키려는 유저의 email
	 * @return 정상적으로 옮겨지면 1, 아니면 0
	 */
	public int gotoTrash(String email);
}
