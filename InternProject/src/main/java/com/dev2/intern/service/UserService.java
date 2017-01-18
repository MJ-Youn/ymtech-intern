package com.dev2.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.UserDAO;
import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.ModifyUserVO;
import com.dev2.intern.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public int createUser(CreateUserVO createUserVO) throws ExistEmailException {
		return userDAO.createUser(createUserVO);
	}

	public UserVO getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
	
	public int modifyUser(String email, ModifyUserVO modifyUserVO) {
		return userDAO.modifyUser(email, modifyUserVO.getPassword(), modifyUserVO.getName());
	}
}
