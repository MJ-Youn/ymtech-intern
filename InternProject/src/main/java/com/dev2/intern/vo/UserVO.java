package com.dev2.intern.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UserVO {
	private int id;
	private String email;
	private String password;
	private String name;
	private int level;
	private int isDeleted;
}
