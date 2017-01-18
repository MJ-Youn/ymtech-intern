package com.dev2.intern.service;

import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.ModifyUserVO;
import com.dev2.intern.vo.UserVO;

public interface IUserService {

	public int createUser(CreateUserVO createUserVO) throws ExistEmailException;
	
	public UserVO getUserByEmail(String email);
	
	public int modifyUser(String email, ModifyUserVO modifyUserVO);
	
}
