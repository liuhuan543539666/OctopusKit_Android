/*
*
*  {{ClassName}}
*  {{AppName}}
*
*  Created by {{Author}} on {{CreateDate}}.
*  Copyright (c) {{CopyrightDate}} {{Organize}}. All rights reserved.
*
*  {{ Purpose }}
*
*/
package com.iapp.service;
 
import java.util.HashMap;

import android.util.Log;

import net.ixkit.octopus.core.ServiceEvent;
import net.ixkit.octopus.lang.Argument;

public class CategoryService extends OpenCartWebService {
	public static final String TAG = "CategoryService";
	
	public static CategoryService getCategories(String categoryId){
		
		 CategoryService service = new CategoryService();
		 //@step
		 HashMap params = 
		   Argument.toMap("route","common/home",
				   "path", categoryId,
				   "json", "1",
				   "int", 100,
				   "double",100.999);
		 
		 service.setParameters(params);
		 //@step
		 ServiceEvent event = new ServiceEvent(){
			 @Override
				public Object dataFilter(Object response) {
				 Log.d(TAG, "dataFilter->" + response);
					return response;
				}
		 };
		 service.setEvent(event);
		 return service;
	}
	
	public static CategoryService getCategories_(String categoryId){
		
		CategoryService service = new CategoryService();
		//@step 
		 HashMap params = new HashMap();
		// params.put("route","product/category" );
		 params.put("route", "common/home");
		 params.put("path", categoryId);
		 params.put("json", "1");
		 service.setParameters(params);
		 return service;
	}
	
	

 
}
