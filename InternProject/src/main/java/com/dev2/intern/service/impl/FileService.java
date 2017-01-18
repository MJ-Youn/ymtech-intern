package com.dev2.intern.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.dao.impl.FileDAO;
import com.dev2.intern.service.IFileService;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

@Service(FileService.BEAN_QUALIFIER)
public class FileService implements IFileService {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.FileService";
	
	@Autowired
	@Qualifier(FileDAO.BEAN_QUALIFIER)
	private FileDAO fileDAO;
	
	@Override
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
	
	@Override
	public FileVO getFileByPostId(String postId) {
		return fileDAO.getFileByPostId(postId);
	}
	
	@Override
	public FileVO getFileByFileId(String fileId) {
		return fileDAO.getFileByFileId(fileId);
	}
	
	@Override
	public int modifyFileByPost(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException {
		fileDAO.deleteFile(modifyPostVO.getId());
		
		if (modifyPostVO.getFile() != null) {
			return fileDAO.saveFile(modifyPostVO.getId(), modifyPostVO.getFile());
		}
		
		return 0;
	}
}
