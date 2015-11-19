package com.ixkit.ixkitdemo;

/**
 * Created by icoco on 9/22/15.
 */

import android.test.AndroidTestCase;

import com.iapp.cart.AppConfig;
import net.ixkit.octopus.core.VolleyMaster;
import net.ixkit.octopus.core.WebServiceConfig;

public class BaseUnitTestCase extends AndroidTestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.out.print("setup");
        String apiRootUrl = AppConfig.WebServiceDef.getBaseUrl();
        WebServiceConfig.getInstance().setApiRootUrl(apiRootUrl);

        VolleyMaster.mContext = this.getContext();
        VolleyMaster.getMainRequestQueue();


    }

    protected void tearDown() throws Exception {
        System.out.print("tearDown");
    }
}
