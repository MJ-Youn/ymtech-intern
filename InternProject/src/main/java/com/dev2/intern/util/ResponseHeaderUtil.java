package com.dev2.intern.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseHeaderUtil {
	
	private static final int SUCCESS_CODE = 200;
	private static final String SUCCESS_MESSAGE = "success";
	private static final boolean SUCCESS_BOOLEAN = true;
	
	private static final int FAIL_CODE = 400;
	private static final String EXIST_EMAIL_MESSAGE = "존재하는 이메일입니다.";
	
	public static final Map<String, Object> RESPONSE_SUCCESS_HEADER = createHeader(SUCCESS_CODE, SUCCESS_MESSAGE, SUCCESS_BOOLEAN);
	public static final Map<String, Object> RESPONSE_EXIST_EMAIL_MESSAGE = createHeader(FAIL_CODE, EXIST_EMAIL_MESSAGE, SUCCESS_BOOLEAN);
	
	public static Map<String, Object> createHeader(int resultCode, String resultMessage, boolean isSuccessful) {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("resultCode", resultCode);
		header.put("resultMessage", resultMessage);
		header.put("isSuccessful", isSuccessful);
		return header;
	}
}
