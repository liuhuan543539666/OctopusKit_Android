/*
*
*  Module11
*  AppName
*
*  Created by Author on 2015,09 09.
*  Copyright (c) 2015 Organize. All rights reserved.
*
*  {{ Purpose }}
*
*/
package com.iapp.service;
 
import java.util.HashMap;

import android.util.Log;

import net.ixkit.octopus.core.ServiceEvent;
import net.ixkit.octopus.core.WebService;
import net.ixkit.octopus.lang.Argument;

public class Module11 extends WebService {
	public static final String TAG = "Module11";
 
/**
 @function:api1
 @param: parameter1: Integer, 
 @param: name: String, 
 @param: password: String, 
 @param: date: String, 
 @param: amount: Integer, 
 @param: money: Decimal, 
 @method=POST
 @response:
 @return: Module11
 @ref:http://www.go.com
 */	
	public static Module11 api1( Integer parameter1          
            ,String name  
            ,String password  
            ,String date  
            ,Integer amount  
            ,Double money ){
		
		Module11 service = new Module11();
		//@step 
		 HashMap params = 
		   Argument.toMap( "parameter1",parameter1          
              ,"name",name  
              ,"password",password  
              ,"date",date  
              ,"amount",amount  
              ,"money",money );
		 
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
	
 
	
 
/**
 @function:logout purpose
 
 @method=POST
 @response:
 @return: Module11
 @ref:http://www.go.com/logout
 */	
	public static Module11 logout(        ){
		
		Module11 service = new Module11();
		//@step 
		 HashMap params = 
		   Argument.toMap(        );
		 
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
	
 
	
 
/**
 @function:
 @param: username: String, 
 @method=POST
 @response:
 @return: Module11
 @ref:
 */	
	public static Module11 login( String username         ){
		
		Module11 service = new Module11();
		//@step 
		 HashMap params = 
		   Argument.toMap( "username",username         );
		 
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
	
 
	



}
