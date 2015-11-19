/**
 * WebService
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */

package net.ixkit.octopus.core;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;


public class WebService extends Service {
	public static final String TAG = "WebService";
	public static class Method {
		public static String GET = "GET";
		public static String POST = "POST";
	}
	public WebService(){
		this.setMethod(Method.GET);
		this.setRequestDataType(Service.RequestDataType.StringRequest);
		
		Log.i(TAG,"Create:"+this);
		 
	}
	
	public String getUrl() {
		String root = WebServiceConfig.getInstance().getApiRootUrl();
		String url = root + "/" + this.getRoute() + "?";
		return url;
	}

	public Request<?> execute(Listener<?> onSuccess, ErrorListener onError) {

		return DataManager.getInstance().invokeService(this, onSuccess,
				onError);

	}
 
}
