package com.phoneshow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDateUtil {
	static final String time_name="yyyy-MM-dd-HH-mm-ss";
	 static final String time_show="yyyy-MM-dd HH:mm";
	 static final String date_show="yyyy-MM-dd";
	/**
	 * Administrator
	 * TODO变成yyyy-MM-dd-HH-mm-ss的字符串
	 */
	public static String DateAndTime(){
		SimpleDateFormat format = new SimpleDateFormat(time_name);
		Date date = new Date();
		String format2 = format.format(date);
		return format2;
	}
	/**
	 * Administrator
	 * TODO 得到yyyy-MM-dd HH:mm格式的时间字符串
	 */
	public static String TimeShow(){
		SimpleDateFormat format = new SimpleDateFormat(time_show);
		Date date = new Date();
		String format2 = format.format(date);
		return format2;
	}
	
	/**
	 * Administrator
	 * TODO 转换yyyy-MM-dd HH:mm类型字符串
	 */
	public static Date StringTimeToDate(String date){
		SimpleDateFormat format = new SimpleDateFormat(time_show);
		Date parse=null;
		try {
			parse = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	/**
	 * Administrator
	 * TODO 转换yyyy-MM-dd类型字符串
	 */
	public static Date StringDateToDate(String date){
		SimpleDateFormat format = new SimpleDateFormat(date_show);
		Date parse=null;
		try {
			parse = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	/**
	 * Administrator
	 * TODO 将时间转换成yyyy-MM-dd HH:mm字符串
	 */
	public static String DateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(time_show);
		String format2 = format.format(date);
		return format2;
	}
	
	/**
	 * Administrator
	 * TODO给日期加指定的天数
	 */
	public static Date AddDay(Date date,int day){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(MyDateUtil.DateToString(date));
		Date addDay = MyDateUtil.AddDay(date, 1);
		System.out.println(MyDateUtil.DateToString(addDay));
	}
}
