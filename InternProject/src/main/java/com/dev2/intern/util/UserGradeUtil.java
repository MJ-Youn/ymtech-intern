package com.dev2.intern.util;

public enum UserGradeUtil {
	SUPER_ADMIN(9, "ROLE_SUPER_ADMIN"),
	REGULAR_MEMBER(1, "ROLE_REGULAR_MEMBER"),
	ASSOCIATE_MEMBER(0, "ROLE_ASSOCIATE_MEMBER"),
	INVALID_LOGIN(-1, "INVALID_LOGIN");
	
	public final int level;
	public final String gradeName;
	
	UserGradeUtil(int level, String gradeName) {
		this.level = level;
		this.gradeName = gradeName;
	}
	
	public static String getGradeNameByLevel(int level) {
		switch (level) {
		case 9:
			return SUPER_ADMIN.gradeName;
		case 1:
			return REGULAR_MEMBER.gradeName;
		case 0:
			return ASSOCIATE_MEMBER.gradeName;
		case -1:
			return INVALID_LOGIN.gradeName;
		default:
			return "";
		}
	}
}
