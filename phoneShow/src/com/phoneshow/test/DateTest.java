package com.phoneshow.test;

import java.util.Date;

import com.phoneshow.util.MyDateUtil;

public class DateTest {
	public static void main(String[] args) {
		String date="2017-11-01";
		Date stringToDate = MyDateUtil.StringDateToDate(date);
		System.out.println(MyDateUtil.DateToString(stringToDate));
	}
}
