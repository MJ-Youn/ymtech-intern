package com.dev2.intern.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class FileVO {
	private int id;
	private int postId;
	private String location;
	private String originalFileName;
	private long size;
	private String type;
}
