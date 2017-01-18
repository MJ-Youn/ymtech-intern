package com.dev2.intern.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dev2.intern.dao.impl.UserDAO;
import com.dev2.intern.util.UserGradeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier(UserDAO.BEAN_QUALIFIER)
	private UserDAO userDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();

		log.info("{} user is log in", email);

		int checkValidLogin = userDAO.checkValidLogin(email, password);

		if (checkValidLogin != 1) {
			String failGrade = UserGradeUtil.getGradeNameByLevel(-1);
			Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(failGrade));
			
			return new UsernamePasswordAuthenticationToken(null, null, authorities);
		} else {
			int userLevel = userDAO.extractLevel(email);
			String userGrade = UserGradeUtil.getGradeNameByLevel(userLevel);
			Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(userGrade));
			
			return new UsernamePasswordAuthenticationToken(email, password, authorities);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return Authentication.class.isAssignableFrom(authentication);
	}

}
