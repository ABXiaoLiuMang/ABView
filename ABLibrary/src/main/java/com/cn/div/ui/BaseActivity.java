package com.cn.div.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cn.div.baseui.R;
import com.cn.div.dialog.ABProgressDialog;
import com.cn.div.util.ExitUtils;
import com.cn.div.util.ScreenSwitch;
import com.cn.div.view.StatusBarSuperUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有界面基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mContext;
    public Bundle bundle;
    private ABProgressDialog progressDialog;
    protected boolean isFirst = true;
    public Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initSystemBar();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        ExitUtils.getInstance().addActivity(this);//记录打开的Act
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFirst){
            isFirst = false;
            eventOnClick();
        }
    }

    /**
     * @return 布局id
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * 事件
     */
    protected abstract void eventOnClick();

    protected void initSystemBar(){
        StatusBarSuperUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }


    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ABProgressDialog(mContext);
        }
        progressDialog.show();
    }

    protected void showProgressDialog(String string) {
        progressDialog = new ABProgressDialog(mContext).setTextProgress(string);
        progressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void goActivity(Class<?> descClass, Bundle bundle) {
        ScreenSwitch.switchActivity(mContext, descClass, bundle);
    }

    public void goActivity(Class<?> descClass) {
        goActivity(descClass, null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitUtils.getInstance().removeActivity(this);//关闭的Act
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
