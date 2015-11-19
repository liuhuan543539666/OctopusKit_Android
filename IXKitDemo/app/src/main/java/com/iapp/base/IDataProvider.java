package com.iapp.base;

import java.util.ArrayList;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public interface IDataProvider {

	public ArrayList<?> loadData();
	
	public ArrayList<?> loadData(Listener<String> onSuccessCallback,
			ErrorListener onErrorCallBack);
	
	public Object getData(Listener<String> onSuccessCallback,
			ErrorListener onErrorCallBack);
	
}
