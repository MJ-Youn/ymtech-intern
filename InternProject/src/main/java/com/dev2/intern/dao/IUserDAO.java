package com.dev2.intern.dao;

import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.UserVO;

public interface IUserDAO {

	/**
	 * 유저정보를 가져와 DB에 저장하는 함수
	 * 
	 * @param createUserVO
	 * 			유저정보가 담긴 VO. email, password, name 정보가 들어있다.
	 * @return 정상적으로 끝나면 1, 아니면 0
	 * @throws ExistEmailException
	 * 			DB에 이미 저장되어있는 email일 경우 발생
	 */
	public int createUser(CreateUserVO createUserVO) throws ExistEmailException;
	
	/**
	 * email을 가지고 사용중인지 확인하는 함수
	 * 
	 * @param email
	 * 			검색하려고 하는 email
	 * @throws ExistEmailException
	 * 			DB에 이미 사용중인 email일 경우 발생
	 */
	public void checkExistUser(String email) throws ExistEmailException;
	
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
	 * email을 가지고 유저를 수정하기 위한 함수
	 * 
	 * @param email
	 * 			대상이되는 user의 email
	 * @param password
	 * 			수정하려고하는 password
	 * @param name
	 * 			수정하려고하는 name
	 * @return 성공적으로 끝나면 1, 아니면 0
	 */
	public int modifyUser(String email, String password, String name);
}
