package com.dev2.intern.service;

import java.util.ArrayList;

public interface IAdminService {
	
	public <T> ArrayList<T> getDBData(String tableName);
	
	public int deleteDBData(String tableName, Object object);
	
	public int modifyUserLevel(String userId, String level);
}
