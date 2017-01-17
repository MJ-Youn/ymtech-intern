package com.dev2.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.UserDAO;
import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.UserVO;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	public int createUser(CreateUserVO createUserVO) throws ExistEmailException {
		return userDAO.createUser(createUserVO);
	}

	@Override
	public UserVO loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("hello");
		
		return null;
	}
}
