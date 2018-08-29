package com.cn.div.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.div.baseui.R;
import com.cn.div.util.ABPixelUtil;


public class ValueView extends LinearLayout {

	private TextView tv_key;
	private TextView tv_value;
	private ImageView iv_value;
	private ImageView iv_icon;

	public ValueView(Context context) {
		this(context, null);
	}

	public ValueView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.HORIZONTAL);
		setClickable(true);
		setBackgroundResource(R.drawable.listview_item_bg);
		setPadding(ABPixelUtil.dp2px(13), 0, ABPixelUtil.dp2px(13), 0);
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ValueView);
		String keyText = mTypedArray.getString(R.styleable.ValueView_keyText);
		String valueText = mTypedArray.getString(R.styleable.ValueView_valueText);
		String valueHintText = mTypedArray.getString(R.styleable.ValueView_valueHintText);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mTypedArray.getResourceId(R.styleable.ValueView_img_icon, 0));
		boolean showIma = mTypedArray.getBoolean(R.styleable.ValueView_showValueIma, false);
		boolean showText = mTypedArray.getBoolean(R.styleable.ValueView_showValueText, true);
		View mlLinearLayout = LayoutInflater.from(context).inflate(R.layout.value_item_layout, this, true);
		tv_key = mlLinearLayout.findViewById(R.id.tv_key);
		tv_value = mlLinearLayout.findViewById(R.id.tv_value);
		iv_value = mlLinearLayout.findViewById(R.id.iv_value);
		iv_icon = mlLinearLayout.findViewById(R.id.iv_icon);

		tv_value.setVisibility(showText ? View.VISIBLE : View.GONE);
		iv_value.setVisibility(showIma ? View.VISIBLE : View.GONE);

		if (!TextUtils.isEmpty(keyText)) {
			tv_key.setText(keyText);
		}
		if (!TextUtils.isEmpty(valueText)) {
			tv_value.setText(valueText);
		}else if(!TextUtils.isEmpty(valueHintText)){
			tv_value.setHint(valueHintText);
		}

		if (bitmap != null) {
			iv_icon.setImageBitmap(bitmap);
		} else {
			iv_icon.setVisibility(View.GONE);
		}
		mTypedArray.recycle();
	}

	public void setTextKey(String key) {
		tv_key.setText(key);
	}

	public void setTextValue(String value) {
		tv_value.setText(value);
	}
	
	public String getValueText(){
		String value = tv_value.getText().toString().trim();
		if(TextUtils.isEmpty(value)){
			return "";
		}
		return value;
	}

}
