package com.cn.div.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cn.div.baseui.R;


/**
 * 加载等到对话框
 */

public class ABProgressDialog extends ProgressDialog {

    TextView tv_load;
    View _view;

    public ABProgressDialog(Context context){
        super(context,R.style.CustomProgressDialog);
        _view = LayoutInflater.from(context).inflate(
                R.layout.load_dialog, null);
        tv_load = _view.findViewById(R.id.tv_load);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(_view);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    public ABProgressDialog setTextProgress(CharSequence msg) {
        tv_load.setText(msg);
        return this;
    }

}

