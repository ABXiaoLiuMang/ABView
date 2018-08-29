package com.cn.div.view;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cn.div.baseui.R;
import com.cn.div.util.ABApplication;



public class ABToast {

	private static Toast toast;
	private static TextView tv_toast;

	public static void show(String text) {
		if(toast == null){
			toast = new Toast(ABApplication.getInstance());
			View view = LayoutInflater.from(ABApplication.getInstance()).inflate(R.layout.view_toast, null);
			tv_toast = view.findViewById(R.id.tv_toast);
			tv_toast.setTextColor(ABApplication.getInstance().getResources().getColor(android.R.color.white));
			toast.setGravity(Gravity.BOTTOM, 0,60);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(view);
		}
		tv_toast.setText(text);
		toast.show();
	}
	
	public static void cancel() {
		if(toast != null){
			toast.cancel();
		}
	}

}
