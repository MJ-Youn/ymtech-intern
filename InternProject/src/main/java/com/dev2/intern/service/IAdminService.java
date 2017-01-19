package com.dev2.intern.service;

import java.util.ArrayList;
import java.util.Map;

public interface IAdminService {
	
	/**
	 * 테이블에 존재하는 모든 정보를 가져오기 위한 함수
	 * 테이블의 이름을 가지고 다른 DAO함수를 오출하여 데이터를 가져온다.
	 * 
	 * @param tableName
	 * 			찾으려는 테이블의 이름
	 * @return 테이블에 존재하는 파일의 리스트.
	 * 			파일 확장자는 테이블이름에 따라 달라진다.
	 */
	public <T> ArrayList<T> getDBData(String tableName);
	
	/**
	 * 테이블에 있는 테이터를 삭제 하기 위한 함수
	 * 
	 * @param tableName
	 * 			테이블 이름
	 * @param object
	 * 			삭제하려는 데이터의 정보
	 * @return 정상적으로 삭제가 되면 1, 아니면 0
	 */
	public int deleteDBData(String tableName, Map<?, ?> object);
	
	public int modifyUserLevel(String userId, String level);
}
