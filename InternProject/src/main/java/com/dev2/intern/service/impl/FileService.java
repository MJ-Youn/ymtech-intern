package com.dev2.intern.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.dao.impl.FileDAO;
import com.dev2.intern.service.IFileService;
import com.dev2.intern.util.FileUtil;
import com.dev2.intern.util.UuidUtil;
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
				FileVO fileVO = uploadFile(postId, multipartFile);
				return fileDAO.saveFile(fileVO);
			}
		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	 * 실제 파일을 storage에 저장하기 위한 함수 
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 * @param multipartFile
	 * 			저장할 파일 정보
	 * @return 저장된 파일의 정보가 담긴 FileVO
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private FileVO uploadFile(int postId, MultipartFile multipartFile) throws IllegalStateException, IOException {
		FileUtil.checkExistDirectory();
		
		String originalFileName = multipartFile.getOriginalFilename();
		String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		String storedFileName = FileUtil.FILE_DIRECTORY + UuidUtil.createUuidWithoutHyphen();
		
		File file = new File(storedFileName);
		multipartFile.transferTo(file);
		
		return new FileVO().setPostId(postId)
							.setLocation(storedFileName)
							.setOriginalFileName(originalFileName)
							.setSize(multipartFile.getSize())
							.setType(originalFileExtension);
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
		fileDAO.gotoTrash(modifyPostVO.getId());
		fileDAO.deleteFile(modifyPostVO.getId());
		
		if (modifyPostVO.getFile() != null) {
			FileVO fileVO = uploadFile(modifyPostVO.getId(), modifyPostVO.getFile());
			return fileDAO.saveFile(fileVO);
		}
		
		return 0;
	}
}
