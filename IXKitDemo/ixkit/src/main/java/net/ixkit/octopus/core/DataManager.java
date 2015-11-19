/**
 * DataManager is a Singleton class encapsulate HTTP Request mechanism provide RESTful functionality,
 * Base Volley now.
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */

package net.ixkit.octopus.core;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;


public class DataManager {

	/**
	 * Log or request TAG
	 */
	public static final String TAG = "DataManager";

	private Context context; // Application
	/**
	 * A singleton instance of the application class for easy access in other
	 * places
	 */
	private static DataManager sInstance;

	private DataManager() {

		// initialize the singleton
		sInstance = this;
	}

	/**
	 * @return singleton instance
	 */
	public static DataManager getInstance() {
		if (null == sInstance) {
			synchronized (DataManager.class) {
				if (null == sInstance) {
					sInstance = new DataManager();
				}
			}

		}
		return sInstance;
	}

	public void registerContext(Context value) {
		this.context = value;
		VolleyMaster.init(this.context);
	}

	public Request performRequest(String url, int method, Map params,
			String requestDataType, Listener<?> onSuccess, ErrorListener onError) {

		Request request = RequestMaker.makeRequest(url, method, params,
				requestDataType, onSuccess, onError);
		if (null == request) {
			return request;
		}
		try {
			Log.d(TAG,
					"performRequest-> url:[" + request.getUrl() + "],prams:["
							+ params + "]" + ",headers:["
							+ request.getHeaders() + "]");
			Log.d(TAG, "performRequest-> headers:[" + request.getHeaders()
					+ "]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Log.d(TAG, e.getLocalizedMessage());
		}

		RequestQueue queue = VolleyMaster.getMainRequestQueue();

		queue.add(request);
		return request;
	}

	private static int toReqeustMethod(String methodString) {
		int result = Request.Method.GET;
		if (WebService.Method.POST.equals(methodString)) {
			result = Request.Method.POST;
		}
		return result;
	}
 

	public Request<?> invokeService(Service service, Listener<?> onSuccess,
									ErrorListener onError) {
		String url = service.getUrl();
		Map params = service.getParameters();
		String strMethod = service.getMethod();
		int method = DataManager.toReqeustMethod(strMethod);
		// @step
		Listener<?> successListener = onSuccess;
		ErrorListener errorListener = onError;
 
		Event  listener = service.getEvent();
		if (null != listener) {
			successListener = ServiceEvent.wrapSuccessListener(listener, onSuccess);
			errorListener = ServiceEvent.wrapErrorListener(listener, onError);
		}
	 		
		//@step
		Request<?> request = this.performRequest(url, method, params,
				service.getRequestDataType(), successListener, errorListener);
		//@step
		request.setTag(service.getServiceId());

		return request;
	}
	
}
