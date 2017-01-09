package com.dev2.intern.vo;

import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ResponseVO {
	private Map<String, Object> header;
	private Object body;
}
