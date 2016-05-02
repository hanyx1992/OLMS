package com.chenlu.olms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils extends org.apache.commons.lang.time.DateUtils{
	
	private DateUtils() {}
	
	/**
	 * ��ȡ���ܵ�һ���������
	 * @return
	 */
	public static String getFirstDayOfThisWeek() {
		Calendar now = getNowCal();
		now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return formatDateToILikeymdStr(now.getTime());
	}
	
	/**
	 * ��ȡ�����յ�������
	 * @return
	 */
	public static String getLastDayOfThisWeek() {
		Calendar now = getNowCal();
		now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		now.add(Calendar.WEEK_OF_MONTH, 1);
		return formatDateToILikeymdStr(now.getTime());
	}
	
	/**
	 * ��ȡĳ�ܵ�һ���������
	 * @return
	 */
	public static String getFirstDayOfTheWeek(Calendar cal) {
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return formatDateToILikeymdStr(cal.getTime());
	}
	
	/**
	 * ��ȡĳ���յ�������
	 * @return
	 */
	public static String getLastDayOfTheWeek(Calendar cal) {
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		return formatDateToILikeymdStr(cal.getTime());
	}
	
	/**
	 * ��ȡĳ�ܵ�һ���������
	 * @return
	 */
	public static String getFirstDayOfTheWeek(String str) {
		Calendar cal = getCalByILikeString(str);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return formatDateToILikeymdStr(cal.getTime());
	}
	
	/**
	 * ��ȡĳ���յ�������
	 * @return
	 */
	public static String getLastDayOfTheWeek(String str) {
		Calendar cal = getCalByILikeString(str);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		return formatDateToILikeymdStr(cal.getTime());
	}
	
	/**
	 * ��ȡ���ڵ�Calendar
	 * @return
	 */
	public static Calendar getNowCal() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal;
	}
	
	/**
	 * ��ȡCalendar
	 * @return
	 */
	public static Calendar getCalByILikeString(String str) {
		Date now;
		try {
			now = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			now = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal;
	}
	
	/**
	 * ��ȡ��һ��
	 * @param str
	 * @return
	 */
	public static String getLastWeekStrByILike(String str) {
		Calendar cal = getCalByILikeString(str);
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		return getILikeFormat().format(cal.getTime());
	}
	
	/**
	 * ��ȡ��һ��
	 * @param str
	 * @return
	 */
	public static String getNextWeekStrByILike(String str) {
		Calendar cal = getCalByILikeString(str);
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		return getILikeFormat().format(cal.getTime());
	}
	
	/**
	 * �����ڸ�ʽ��Ϊ yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDateToILikeymdStr(Date date) {
		return getILikeFormat().format(date);
	}
	
	public static SimpleDateFormat getILikeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
}