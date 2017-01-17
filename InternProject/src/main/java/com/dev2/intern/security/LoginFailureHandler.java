package com.dev2.intern.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.dev2.intern.util.ResponseHeaderUtil;
import com.dev2.intern.vo.ResponseVO;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static ResponseVO LOGIN_FAILURE_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_LOGIN_FAILURE);

//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//		super.onAuthenticationFailure(request, response, exception);
//		System.out.println("is failure");
//	} 
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(LOGIN_FAILURE_RESPONSE.toString().getBytes());
	}

}
