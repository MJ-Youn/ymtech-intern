package com.dev2.intern.vo;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseVO {
	private Map<String, Object> header;
	private Object body;
}
