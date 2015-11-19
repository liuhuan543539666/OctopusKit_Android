package com.iapp.cart.model;

import java.util.ArrayList;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.iapp.base.BaseModel;
import com.iapp.service.CategoryService;
 

public class CategoryModel extends BaseModel {
	public static final String TAG = "WebService";
	private Response.Listener<String> createMyReqSuccessListener(
			final Listener callback) {
		return new Response.Listener<String>() {
			Listener mCallback = callback;

			@Override
			public void onResponse(String response) {
				Log.d("Test", response);
				if (null != this.mCallback) {
					this.mCallback.onResponse(response);
				}
			}
		};
	}

	private Response.ErrorListener createMyReqErrorListener(
			final ErrorListener callback) {
		return new Response.ErrorListener() {
			ErrorListener mCallback = callback;

			@Override
			public void onErrorResponse(VolleyError error) {
				if (null!= error){
				Log.d("Test:", error.getMessage()+"patched error");
				error.printStackTrace();
				}
				if(null != this.mCallback) {
					this.mCallback.onErrorResponse(error);
				}
			}
		};
	}

	@Override
	public ArrayList<?> loadData(Listener<String> onSuccessCallback,
			ErrorListener onErrorCallBack) {
		super.loadData(onSuccessCallback, onErrorCallBack);
		Log.i(TAG, "loadData");
		Listener onSuccess = this.createMyReqSuccessListener(onSuccessCallback);
		ErrorListener onError = this.createMyReqErrorListener(onErrorCallBack);

		CategoryService service = CategoryService.getCategories(null);
		service.execute(onSuccess, onError);
		 return null;

	}
	
	public ArrayList<?> getCategories(String categoryId, Listener<String> onSuccess,
			ErrorListener onError) {
		
		CategoryService service = CategoryService.getCategories(categoryId);
		service.execute(onSuccess, onError);
 
		return null;	
	}
}
