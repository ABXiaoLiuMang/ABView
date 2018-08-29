package com.cn.div.util;

import android.app.Application;

public class ABApplication extends Application {

	private static ABApplication instance;

	public static ABApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		ABPreferenceUtils.getInstance(getApplicationContext());
		Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(getApplicationContext()));
	}

}
