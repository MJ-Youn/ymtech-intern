package com.dev2.intern.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PostVO {
	private int id;
	private int userId;
	private int postNumber;
	private String title;
	private String contents;
	private int hitCount;
	private Date createDate;
	private Date modifyDate;
}
