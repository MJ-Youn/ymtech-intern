package com.dev2.intern.vo;

import lombok.Data;

@Data
public class WriteCommentVO {
	private int parentCommentId;
	private int postId;
	private int userId;
	private int depth;
	private String contents;
}
