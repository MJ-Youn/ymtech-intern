package com.dev2.intern.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

public interface IFileService {
	
	public int saveFile(int postId, MultipartFile multipartFile);
	
	public FileVO getFileByPostId(String postId);
	
	public FileVO getFileByFileId(String fileId);
	
	public int modifyFileByPost(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException;
}
