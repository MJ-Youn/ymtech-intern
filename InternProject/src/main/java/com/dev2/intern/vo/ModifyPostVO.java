package com.dev2.intern.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class ModifyPostVO {
	private int id;
	private String title;
	private String contents;
	private MultipartFile file;
}
