package com.dev2.intern.vo;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain=true)
public class BoardVO {
	int id;
	String name;
}
