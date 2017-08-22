package com.gmonetix.truthdare;

import android.app.Application;

import com.gmonetix.truthdare.utils.SharedPref;

/**
 * @author Gmonetix
 */

public class App extends Application {

    private static SharedPref sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPref = new SharedPref(this);
    }

    public static SharedPref getSharedPref() {
        return sharedPref;
    }

}
