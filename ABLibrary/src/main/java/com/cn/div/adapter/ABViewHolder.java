package com.cn.div.adapter;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class ABViewHolder {
	private SparseArray<View> viewArray;
	private int position;
	private View mConvertView;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 */
	public ABViewHolder(Context context, View convertView, ViewGroup parent,
                        int layoutId, int position) {
		this.position = position;
		viewArray = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	protected static ABViewHolder get(Context context, View convertView,
                                      ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			// 如果convertView为空，则实例化ABViewHolder
			return new ABViewHolder(context, convertView, parent, layoutId,
					position);
		} else {
			// 否则从convertView的Tag中取出ABViewHolder，避免重复创建
			ABViewHolder holder = (ABViewHolder) convertView.getTag();
			holder.position = position;
			return holder;
		}
	}

	protected View getConvertView() {
		return mConvertView;
	}

	public int getPosition() {
		return position;
	}

	/**
	 * 通过viewId获取控件对象
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = viewArray.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			viewArray.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * ------------------------------------华丽的分割线------------------------------
	 */
	/** 以下方法为额外封装的方法，只是简单几个，以后可以慢慢完善 */
	/**
	 * 设置TextView的内容
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ABViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * 设置TextView的内容
	 * 
	 * @param viewId
	 * @param text
	 *            ，Spanned类型，可设置部分字体变色
	 * @return
	 */
	public ABViewHolder setText(int viewId, Spanned text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ABViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}


	public ABViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	@Deprecated
	public ABViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
		View view = this.getView(viewId);
		view.setOnClickListener(listener);
		return this;
	}


}
