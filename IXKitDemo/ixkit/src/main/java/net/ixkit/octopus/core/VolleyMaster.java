/**
 * VolleyMaster provide HTTP request feature based on Volley
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */
package net.ixkit.octopus.core;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;



public class VolleyMaster {
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	public  static Context mContext;
	
	private VolleyMaster() {
		// no instances
	}

	static void init(Context context) {
		if (null != mRequestQueue) {
			return ;
			//throw new IllegalStateException("RequestQueue has been initialized");

		}
		 mContext = context;
		mRequestQueue = Volley.newRequestQueue(context);

		int memClass = ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		// Use 1/8th of the available memory for this memory cache.
		int cacheSize = 1024 * 1024 * memClass / 8;
		// @ mImageLoader = new ImageLoader(mRequestQueue, new
		// BitmapLruCache(cacheSize));
	}

	public static RequestQueue getMainRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} 
		synchronized (DataManager.class) {
			if (null != mContext){
				mRequestQueue = Volley.newRequestQueue(mContext);
				return mRequestQueue;
			}
			throw new IllegalStateException("RequestQueue not initialized");
		}
		 
	}

	/**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
			return mImageLoader;
		} else {
			throw new IllegalStateException("ImageLoader not initialized");
		}
	}
}
