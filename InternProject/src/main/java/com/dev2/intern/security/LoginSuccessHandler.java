package com.dev2.intern.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.dev2.intern.util.ResponseHeaderUtil;
import com.dev2.intern.vo.ResponseVO;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static ResponseVO LOGIN_SUCCESS_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_LOGIN_SUCCESS);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, authentication);
		System.out.println("is success");
	}
}
