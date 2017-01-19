package com.dev2.intern.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyPostVO;

public interface IFileService {
	
	/**
	 * 파일을 저장하기 위한 함수
	 * 대상 게시글에 맞게 DB에 저장하고 LocalStorage에 파일을 저장한다.
	 * 
	 * @param postId
	 * 			대상 게시글의 번호
	 * @param multipartFile
	 * 			실질적인 파일 데이터
	 * @return 정상적으로 끝나면 1, 아니면 0
	 */
	public int saveFile(int postId, MultipartFile multipartFile);
	
	public FileVO getFileByPostId(String postId);
	
	public FileVO getFileByFileId(String fileId);
	
	/**
	 * 게시글에 해당하는 파일을 수정하기 위한 함수
	 * 원래 파일이 존재했다면 이를 삭제하고 새롭게 저장한다.
	 * 새로운 파일이 없을 경우 바로 끝이난다.
	 * 
	 * @param modifyPostVO
	 * 			수정할 게시글의 정보
	 * @return 정상적으로 끝나면 1, 실패했거나 새롭게 저장할 파일이 없을 경우 0
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public int modifyFileByPost(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException;
}
