/**
 * WebServiceConfig
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */

package net.ixkit.octopus.core;



public class WebServiceConfig {
	private static WebServiceConfig sInstance;

	private WebServiceConfig() {
		// initialize the singleton
		sInstance = this;
	}

	/**
	 * @return singleton instance
	 */
	public static WebServiceConfig getInstance() {
		if (null == sInstance) {
			synchronized (WebServiceConfig.class) {
				if (null == sInstance) {
					sInstance = new WebServiceConfig();
				}
			}

		}
		return sInstance;
	}
	
	private String mApiRootUrl;

	public String getApiRootUrl() {
		return mApiRootUrl;
	}

	public void setApiRootUrl(String apiRootUrl) {
		this.mApiRootUrl = apiRootUrl;
	}
}
