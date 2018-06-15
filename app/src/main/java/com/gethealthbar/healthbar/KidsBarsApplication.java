package com.gethealthbar.healthbar;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Alexandr.
 */
public class KidsBarsApplication extends Application {

//    @Override
//    public void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        try {
//            MultiDex.install(this);
//        } catch (RuntimeException multiDexException) {
//            multiDexException.printStackTrace();
//        }
//    }


    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());

    }
}
