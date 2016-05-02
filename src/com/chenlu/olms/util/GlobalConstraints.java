package com.chenlu.olms.util;

/**
 * ȫ�ֳ���
 */
public final class GlobalConstraints {
	
	private GlobalConstraints() {};
 
	/**
	 * һЩ����·��������
	 */
	public static interface REQUEST_URL {
		public static final String TOLOGIN = "";
		public static final String LOGIN = "";
	}

	/**
	 * Session Key
	 */
	public static interface SESSION_KEY {
		public static final String USER = "OLMS_USER_INFO";
	}

	/**
	 * ����ö��
	 */
	public static interface Data_ENUM {
		public static final String IS_USED_KEY = "isUsed";
		public static final short IS_USED = 1;
		public static final short IS_NOT_USED = 0;
	}
	
	/**
	 * ϵͳ����
	 */
	public static interface SYS_PARAM {
		/** ÿ��γ��� */
		public static final int MAX_SCHEDULE = 5;
	}
}
