/**
 * ServiceEvent wrap the Service Execute events
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */
package net.ixkit.octopus.core;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class ServiceEvent<T> implements Event<T> {

	@Override
	public Object beforeSend(T sender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object dataFilter(T response) {
	 
		return response;
	}

	@Override
	public Object success(T response) {
		 
		return response;
	}

	@Override
	public Exception error(Exception error) {
		 
		return error;
	}

	@Override
	public Object complete(T response) {
		 
		return response;
	}
	
	public static <T> Listener<?> wrapSuccessListener(
			final Event listener, final Listener<?> onSuccess) {
		Listener<?> result = new Listener<T>() {
			private Event <T> mEvent = listener;
			private final Listener<T> mListener = (Listener<T>) onSuccess;

			@Override
			public void onResponse(T response) {

				Object filterResponse = this.mEvent.dataFilter(response);
				Object successResponse = this.mEvent.success((T) filterResponse);
				this.deliverResponse(successResponse);
				this.mEvent.complete((T) successResponse);
			}

			private void deliverResponse(Object filterResponse) {
				mListener.onResponse((T) filterResponse);
			}
		};
		return result;
	}
	public static   ErrorListener  wrapErrorListener(
			final Event listener, final ErrorListener  onError) {
		ErrorListener result = new ErrorListener () {
			private Event   mEvent = listener;
			private final ErrorListener  mListener = (ErrorListener ) onError;

			@Override
			 public void onErrorResponse(VolleyError error) {

				Exception exception = this.mEvent.error(error);
			 
				this.deliverError((VolleyError)exception);
				this.mEvent.complete( exception);
			}

			private void deliverError(VolleyError error) {
				mListener.onErrorResponse( error);
			}
		};
		return result;
	}	
}
