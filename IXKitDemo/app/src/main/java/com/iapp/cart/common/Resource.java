package com.iapp.cart.common;

public class Resource {
	
	//http://i2cart.com/index.php?route=product/category&amp;path=59
	public static String retrieveCategoryId(String url){
		String [] values = url.split("=");
		int length = values.length;
		return values[length-1];
	}
}
