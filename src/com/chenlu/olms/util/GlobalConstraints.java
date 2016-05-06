package com.chenlu.olms.util;

/**
 * 全局常量
 */
public final class GlobalConstraints {
	
	private GlobalConstraints() {};
 
	/**
	 * 一些常用路径的配置
	 */
	public static interface REQUEST_URL {
		public static final String TOLOGIN = "/login.do";
		public static final String LOGIN = "/verify.do";
		public static final String AUTH = "/noauth.do";
		public static final String LOGOUT = "/logout.do";
	}

	/**
	 * Session Key
	 */
	public static interface SESSION_KEY {
		public static final String USER = "OLMS_USER_INFO";
	}

	/**
	 * 数据枚举
	 */
	public static interface Data_ENUM {
		public static final String IS_USED_KEY = "isUsed";
		public static final short IS_USED = 1;
		public static final short IS_NOT_USED = 0;
		
		public static final short USER_ROLE_ADMINISTRATOR = 0;
		public static final short USER_ROLE_TEACHER = 1;
		public static final short USER_ROLE_STUDENT = 2;

		public static final String[] USER_AUTHS_ALL = {"main", "laboratory", "review", "notice", "lab-mag", "stu-mag", "tea-mag", "blk-mag"};
		
		public static final short OCCUPY_REVIEW_WAIT = 0;
		public static final short OCCUPY_REVIEW_SUCCESSS = 1;
		public static final short OCCUPT_REVIEW_FAILED = -1;
		
		
	}
	
	/**
	 * 系统配置
	 */
	public static interface SYS_PARAM {
		/** 每天课程数 */
		public static final int MAX_SCHEDULE = 5;
	}
}
