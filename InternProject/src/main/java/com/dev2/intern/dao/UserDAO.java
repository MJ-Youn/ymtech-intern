package com.dev2.intern.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.vo.CreateUserVO;

@Repository
public class UserDAO extends GenericDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int createUser(CreateUserVO createUserVO) throws ExistEmailException {
		String query = getQuery("user.createUser");
		checkExistUser(createUserVO.getEmail());
		
		return jdbcTemplate.update(query, createUserVO.getEmail(), createUserVO.getName(), createUserVO.getPassword());
	}
	
	private void checkExistUser(String email) throws ExistEmailException {
		String query = getQuery("user.checkExistUser");
		int existionCheck = jdbcTemplate.queryForObject(query, Integer.class, email);

		if (existionCheck == 1) {
			throw new ExistEmailException();
		}
	}
	
}
