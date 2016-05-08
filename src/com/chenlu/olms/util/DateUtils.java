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
	
	public static Date parseDateWithILikeString(String str) {
		Date now;
		try {
			now = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			now = new Date();
		}
		return now;
	}
	
	/**
	 * ��ȡCalendar
	 * @return
	 */
	public static Calendar getCalByILikeString(String str) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDateWithILikeString(str));
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
	
	/**
	 * �����ڸ�ʽ��Ϊ yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDateToILikeymdhmsStr(Date date) {
		return getFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	
	public static SimpleDateFormat getILikeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public static SimpleDateFormat getFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	
	/**
	 * ���ַ���yyyy-MM-dd������֮��ĵڼ���
	 * @param date
	 * @param dayNum
	 * @return
	 */
	public static Date getDateAfterDayNumByDateSting(String date, int dayNum) {
		Calendar cal = getCalByILikeString(date);
		cal.add(Calendar.DAY_OF_YEAR, dayNum);
		return cal.getTime();
	}
	
	/**
	 * �Ƚ�ʱ���Ƿ�������ʱ��֮��,������ʼ�ͽ�����һ��Ϊnull;
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isBetweenTwoDate(Date date, Date start, Date end) {
		if (date == null || (start==null && end==null)) {
			return false;
		}
		if (start == null && end != null && date.getTime() <= end.getTime()) {
			return true;
		}
		if (end == null && start != null && date.getTime() >= start.getTime()) {
			return true;
		}
		return date.getTime() <= end.getTime() && date.getTime() >= start.getTime();
	}
	
	/**
	 * �Ƚ�����ʱ���Ƿ�������ʱ��֮��,������ʼ�ͽ�����һ��Ϊnull;
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isNowBetweenTwoDate(Date start, Date end) {
		return isBetweenTwoDate(new Date(), start, end);
	}
}
