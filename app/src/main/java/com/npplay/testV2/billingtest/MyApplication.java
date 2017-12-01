package com.npplay.testV2.billingtest;

import android.app.Application;
import android.content.Context;

import com.niceplay.authclient_three.NPPlayGameSDK;
import com.niceplay.niceplaygcm.NicePlayGCMRegister;

public class MyApplication extends Application {

    private String appid = "DEMO";

    private String apikey = "daf964f8d22c46d7ce4fb15a555aeece";




    @Override
    public void onCreate() {

        super.onCreate();

        NPPlayGameSDK.setSSL(true);

        NPPlayGameSDK.getInstance().initPlayGameServices(this, appid, apikey , NPPlayGameSDK.ZH_TW, "com.npplay.testV2.billingtest.MainActivity");

        //NPPlayGameSDK.getInstance().initPlayGameServices(this, appid, apikey);

        //NPPlayGameSDK.getInstance().initPlayGameServices(this, appid, apikey , language);

        //NPPlayGameSDK.getInstance().initPlayGameServices(this, appid, apikey , MainActivityFullPath);

        //NPPlayGameSDK.getInstance().initPlayGameServices(this, appid, apikey , language , MainActivityFullPath);

    }
}
