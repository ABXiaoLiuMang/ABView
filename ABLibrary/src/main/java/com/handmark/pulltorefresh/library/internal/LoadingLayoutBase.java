package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.handmark.pulltorefresh.library.ILoadingLayout;

/**
 * Created by Administrator on 2018/2/26 0026.
 * 自己添加类，修改下拉刷新的样式
 */

public abstract class LoadingLayoutBase extends FrameLayout implements ILoadingLayout {

    public LoadingLayoutBase(Context context) {
        super(context);
    }

    public LoadingLayoutBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingLayoutBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final void setHeight(int height) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.height = height;
        requestLayout();
    }

    public final void setWidth(int width) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.width = width;
        requestLayout();
    }

    @Override
    public void setLoadingDrawable(Drawable drawable) {

    }

    @Override
    public void setTextTypeface(Typeface tf) {

    }

    /**
     * 获取头部（底部）高度，下拉（上拉）到这个高度才触发刷新，可以乘系数
     * @return size
     */
    public abstract int getContentSize();

    /**
     * 开始下拉时的回调
     */
    public abstract void pullToRefresh();

    /**
     * "加载头部"完全显示时的回调
     */
    public abstract void releaseToRefresh();

    /**
     * 下拉拖动时的回调
     * @param scaleOfLayout scaleOfLayout 系数
     */
    public abstract void onPull(float scaleOfLayout);

    /**
     * 释放后刷新时的回调
     */
    public abstract void refreshing();

    /**
     * 刷新成功时的回调
     */
    public abstract void onRefreshSucc();

    /**
     * 初始化到未刷新状态
     */
    public abstract void reset();

    public void hideAllViews(){
        hideAllViews(this);
    }

    public void showInvisibleViews() {
        showAllViews(this);
    }

    private void hideAllViews(View view) {
        if(view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                hideAllViews(((ViewGroup)view).getChildAt(i));
            }
        } else {
            if(View.VISIBLE == view.getVisibility()) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void showAllViews(View view) {
        if(view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                showAllViews(((ViewGroup) view).getChildAt(i));
            }
        } else {
            if(View.INVISIBLE == view.getVisibility()) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

}