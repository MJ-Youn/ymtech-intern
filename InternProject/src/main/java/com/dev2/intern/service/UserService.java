package com.dev2.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.UserDAO;
import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public int createUser(CreateUserVO createUserVO) throws ExistEmailException {
		return userDAO.createUser(createUserVO);
	}
	
}
