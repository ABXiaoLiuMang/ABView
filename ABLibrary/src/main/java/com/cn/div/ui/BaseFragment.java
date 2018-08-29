package com.cn.div.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.div.dialog.ABProgressDialog;
import com.cn.div.util.ScreenSwitch;
import com.cn.div.view.ABToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 所有界面基类
 */

public abstract class BaseFragment extends Fragment {
    public Activity mContext;
    public Bundle bundle;
    private ABProgressDialog progressDialog;
    public View view;
    Unbinder unbinder;
    protected boolean isFirst = true;
    protected CompositeDisposable mDisposables;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
//        if (view == null) {
        view = inflater.inflate(getLayoutId(), container, false);
//        } else {
//            View oldParent = (View) view.getParent();
//            if (oldParent != container) {
//                ((ViewGroup) oldParent).removeView(view);
//            }
//        }
        unbinder = ButterKnife.bind(this, view);
        eventOnClick();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst) {
            isFirst = false;
            sendHttpRequest();
        }
    }

    /**
     * @return 布局id
     */
    protected abstract int getLayoutId();


    protected abstract void sendHttpRequest();

    /**
     * 事件
     */
    protected abstract void eventOnClick();


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

    protected void addSubscription(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

    protected void onUnsubscribe() {
        if (mDisposables != null) {
//            mDisposables.dispose();
            mDisposables.clear();
            mDisposables = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        onUnsubscribe();
    }

}
