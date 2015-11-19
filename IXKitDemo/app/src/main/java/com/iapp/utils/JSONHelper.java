package com.iapp.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {

	public static String getString(JSONObject json, String name) {
		if (null == json) return null;
		
		String value = null;
		try {
			value = json.getString(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
