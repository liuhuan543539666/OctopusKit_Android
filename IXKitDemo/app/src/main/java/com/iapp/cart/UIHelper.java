package com.iapp.cart;

 
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.iapp.cart.ui.CategoriesActivity;

public class UIHelper {

	public static void showCategoires(Context context, 
			String categoryId) {
		Intent intent = new Intent(context, CategoriesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle bundle = new Bundle();
		bundle.putString(AppConfig.FieldDef.CategoryId, categoryId);
		
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
