package com.dev2.intern.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
	private int id;
	private int parentCommentId;
	private int postId;
	private int userId;
	private String userName;
	private String contents;
	private Date createDate;
	private Date modifyDate;
	private int depth;
}
