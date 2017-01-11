package com.dev2.intern.vo;

import lombok.Data;


@Data
public class WritePostVO {
	private int boardId;
	private int userId;
	private String title;
	private String contents;
}
