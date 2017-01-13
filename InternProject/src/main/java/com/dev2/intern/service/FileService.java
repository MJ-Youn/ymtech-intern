package com.dev2.intern.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.dao.FileDAO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

@Service
public class FileService {

	@Autowired
	private FileDAO fileDAO;
	
	public int saveFile(int postId, MultipartFile multipartFile) {
		try {
			if (multipartFile != null) {
				return fileDAO.saveFile(postId, multipartFile);
			}
		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return -1;
	}
	
	public FileVO getFileByPostId(String postId) {
		return fileDAO.getFileByPostId(postId);
	}
	
	public FileVO getFileByFileId(String fileId) {
		return fileDAO.getFileByFileId(fileId);
	}
	
	public int modifyFileByPost(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException {
		fileDAO.deleteFile(modifyPostVO.getId());
		
		if (modifyPostVO.getFile() != null) {
			return fileDAO.saveFile(modifyPostVO.getId(), modifyPostVO.getFile());
		}
		
		return 0;
	}
}
