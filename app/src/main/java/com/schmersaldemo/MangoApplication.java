package com.schmersaldemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.schmersaldemo.mango.localization.LocaleManager;
import com.schmersaldemo.mango.localization.Utility;

public class MangoApplication extends Application {

    public static LocaleManager localeManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Utility.bypassHiddenApiRestrictions();
    }

    @Override
    protected void attachBaseContext(Context base) {
        localeManager = new LocaleManager(base);
        super.attachBaseContext(localeManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
    }
}
