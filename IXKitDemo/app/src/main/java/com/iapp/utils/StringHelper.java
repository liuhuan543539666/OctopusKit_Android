package com.iapp.utils;

public class StringHelper {
	public static boolean isEmptyString(String value) {
		return null == value || "" == value;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
