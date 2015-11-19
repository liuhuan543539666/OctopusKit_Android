package com.iapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class TimeHelper {

	// 一秒等于1000毫秒
	public static long getMillisecond(int second) {
		return second * 1000;
	}

	@SuppressLint("SimpleDateFormat")
	public static long differenceSecond(String dateString, Date now) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		// Date now = new Date();
		// int difference = now.compareTo(date);
		long difference = date.getTime() - now.getTime();
		// @long days = difference / (1000 * 60 * 60 * 24);

		return difference / (1000 * 60);
	}
}
