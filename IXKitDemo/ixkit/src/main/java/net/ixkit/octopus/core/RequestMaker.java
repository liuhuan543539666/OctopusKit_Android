/**
 * RequestMaker is helper class provide static method that can be use wrap
 * HTTP Request
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */

package net.ixkit.octopus.core;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;


public class RequestMaker {
	/**
	 * Log or RequestMaker TAG
	 */
	public static final String TAG = "RequestMaker";


	public static String params2String(Map params) {
		String result = "";
		if (null == params) {
			return result;
		}

		Set set = params.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value =  params.get(key);
			result = result + (TextUtils.isEmpty(result) ? "&" : "&") + key
					+ "=" + value;
		}
		//Log.d(TAG, " params2String done, prams:"+ params);
		return result;
	}

	public static String assembleUrl(String url, Map params) {
		
		String result = url;
		if (null != params) {

			String paramStr = RequestMaker.params2String(params);
			try {

				//paramStr = paramStr.replace("*", "*").replace("~", "~").replace("+", " ");
			}catch (Exception e){
				Log.e(TAG,e.getLocalizedMessage(),e);
			}
				result = url + paramStr ;
		}

		Log.d(TAG, " assembleUrl:" +url);
		return result;
	}

	 

	public static <T> Request makeRequest(String url, int method, Map params,
			String requestDataType, Listener<?> onSuccess, ErrorListener onError) {

		Request<T> request = null;
		if (null == requestDataType
				|| Service.RequestDataType.StringRequest
						.equals(requestDataType)) {
			request = (Request) RequestMaker.makeStringRequest(url, method,
					params, onSuccess, onError);
		}
		
		return request;
	}

	public static StringRequest makeStringRequest(String url, int method,
			final Map params, Listener<?> onSuccess, ErrorListener onError) {
		String newUrl = url;
		if ( true || Request.Method.GET == method)
		{
			newUrl = RequestMaker.assembleUrl(newUrl, params);
		}
		 
		 
		StringRequest request = new StringRequest(method, newUrl,
				(Listener<String>) onSuccess, onError) {
			@Override
			protected Map<String, String> getParams() {
				return params;
			}

		};
		
		return request;
	}
}
