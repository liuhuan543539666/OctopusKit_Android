package com.iapp.base;

import java.util.ArrayList;
import java.util.Observable;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

 

public class BaseModel extends Observable implements IDataProvider {

	public void forceNotifyObservers(Object value) {

		this.setChanged();
		this.notifyObservers(value);
		this.clearChanged();
	}

	@Override
	public ArrayList<?> loadData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<?> loadData(Listener<String> onSuccessCallback,
			ErrorListener onErrorCallBack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getData(Listener<String> onSuccessCallback,
			ErrorListener onErrorCallBack) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
	

}
