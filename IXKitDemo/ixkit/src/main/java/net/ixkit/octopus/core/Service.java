/**
 * Service use wrap HTTP request information, eg: url, parameters
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

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import net.ixkit.octopus.exception.DataException;


public abstract class Service    {
	private String mRoute;
	private String mMethod;
	private String mServiceId;
	private Map<?, ?> mParameters;
	private String mRequestDataType; //String | Json | Image
	 
	private Event<?> mEvent;
	
	public static class RequestDataType{
		public static String StringRequest = "String";
		public static String JsonRequest = "Json";
		public static String ImageRequest = "Image";
	}
	
	public String getRoute() {
		return mRoute;
	}

	public String getMethod() {
		return mMethod;
	}

	public String getServiceId() {
		return mServiceId;
	}

	public Map getParameters() {
		return mParameters;
	}

	public String getRequestDataType() {
		return mRequestDataType;
	}

	public void setRoute(String route) {
		this.mRoute = route;
	}

	public void setMethod(String method) {
		this.mMethod = method;
	}

	public void setServiceId(String serviceId) {
		this.mServiceId = serviceId;
	}

	public void setParameters(Map<?, ?> parameters) {
		this.mParameters = parameters;
	}

	public void setRequestDataType(String requestType) {
		this.mRequestDataType = requestType;
	}

 
	
	public Event<?> getEvent() {
		return mEvent;
	}

	public void setEvent(Event<?> event) {
		this.mEvent = event;
	}

	public String getUrl(){
		return null;
	}
	
	public Request<?> execute(Listener<?>  onSuccess, ErrorListener onError){
		 throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static boolean isSuccessOperation(Object response){
		  throw new UnsupportedOperationException("Not yet implemented");
    }
	
	public static DataException  dataExceptionWithResponse(Object response){
		  throw new UnsupportedOperationException("Not yet implemented");
	}	
	 
}
