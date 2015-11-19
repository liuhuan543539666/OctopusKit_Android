package com.ixkit.ixkitdemo;

/**
 * Created by icoco on 9/22/15.
 */


import com.android.volley.Response;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import com.android.volley.VolleyError;
import com.iapp.service.CategoryService;


public class CategoryServiceTest extends BaseUnitTestCase {

    public void testGetCategories(){


        CategoryService service = CategoryService.getCategories("");

       Response.Listener<String> onSuccess = new Listener<String>() {
           @Override
           public void onResponse(String s) {
                System.out.print("Test->" + s);
               assertEquals("", s);
           }
       };

        Response.ErrorListener onError = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.print(volleyError);
            }
        };

        service.execute(onSuccess, onError);


    }
}
