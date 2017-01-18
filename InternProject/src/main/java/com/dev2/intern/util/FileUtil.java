package com.dev2.intern.util;

import java.io.File;

public class FileUtil {
	
	public static final String FILE_DIRECTORY = "E:\\01. 인턴\\files\\";
	
    /**
	 * 저장할 Directory가 없을 경우 생성하기 위한 함수
	 */
	public static void checkExistDirectory() {
		File directory = new File(FILE_DIRECTORY);
		
		if (directory.exists() == false) {
			directory.mkdirs();
		}
	}
}
