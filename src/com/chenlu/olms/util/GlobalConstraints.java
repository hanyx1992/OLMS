package com.chenlu.olms.util;

/**
 * 全局常量
 */
public class GlobalConstraints {

	public static interface REQUEST_URL {
		public static final String TOLOGIN = "";
		public static final String LOGIN = "";
	}

	public static interface SESSION_KEY {
		public static final String USER = "OLMS_USER_INFO";
	}

	public static interface Data_ENUM {
		public static final String IS_USED_KEY = "isUsed";
		public static final short IS_USED = 1;
		public static final short IS_NOT_USED = 0;
	}
}
