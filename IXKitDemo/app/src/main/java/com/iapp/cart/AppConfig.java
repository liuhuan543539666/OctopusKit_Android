package com.iapp.cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppConfig {
	/**
	 *  
	 */
	public static final String SETTING_INFO = "SETTINGInfo";

	public static String getConfig(Context context, String key,
			String defaultValue) {
		SharedPreferences settings = context.getSharedPreferences(SETTING_INFO,
				0);
		String value = settings.getString(key, defaultValue);
		return value;

	}

	public static void setConfig(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(SETTING_INFO,
				0);
		Editor editor = settings.edit();

		editor.putString(key, value);

		editor.commit();

	}

	public static class WebServiceDef {
		private static String root =  "http://i2cart.com";

		public static String getRootCategoriesUrl() {
			return root + "route=common/home";
		}
		public static String getCategoriesUrl() {
			return root + "route=product/category";
		}
		
		public static String getBaseUrl() {
			return root;
		}
		public static class Params{
			public final static String path = "path";
		}
		
		
	}
	public static class FieldDef{
		public final static  String CategoryId ="category_id";
		
		
	}
}
