package com.iapp.cart.adapter;

import com.iapp.cart.model.CategoryModel;

public class CategoriesAdapter<T> extends JsonDataAdapter {

	public static final java.lang.String LOG_TAG = "CategoriesAdapter";

	private CategoryModel mModel; 
	public void loadData(String categoryId){
		if (null== this.mModel){
			mModel = new CategoryModel();
		}
		
		mModel.getCategories(categoryId, this.onSuccess, this.onError);
	}


}
