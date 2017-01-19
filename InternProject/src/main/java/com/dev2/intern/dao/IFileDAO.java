package com.dev2.intern.dao;

import java.io.IOException;

import com.dev2.intern.vo.FileVO;

public interface IFileDAO {

	/**
	 * 파일 정보를 DB에 저장하기 위한 함수
	 * 
	 * @param fileVO
	 * 			저장할 파일의 정보
	 * @return	정상적으로 저장되면 1, 아니면 0
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public int saveFile(FileVO fileVO) throws IllegalStateException, IOException;
	
	/**
	 * 해당 게시글에 존재하는 파일을 가져오기 위한 함수
	 * 
	 * @param postId
	 * 			게시글의 id
	 * @return 게시글에 파일이 존재할 경우 파일 정보가 담긴 FileVO, 없을 경우 null
	 */
	public FileVO getFileByPostId(String postId);
	
	/**
	 * 파일을 다운로드 받을 때, 선택한 파일의 정보를 가져오기 위한 함수
	 * 
	 * @param fileId
	 * 			다운로드 받을 파일의 id
	 * @return 다운로드 받을 파일의 정보가 담긴 FileVO
	 */
	public FileVO getFileByFileId(String fileId);
	
	/**
	 * 일반유저가 파일을 삭제할 때 사용하는 함수
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 * @return 정상적으로 삭제가 되면 1, 아니면 0
	 */
	public int deleteFile(int postId);
	
	/**
	 * 일반유저가 파일을 삭제할 때, 해당 파일을 trash로 보내는 함수
	 * 
	 * @param postId
	 * 			대상 게시글의 id
	 */
	public void gotoTrash(int postId);
}
