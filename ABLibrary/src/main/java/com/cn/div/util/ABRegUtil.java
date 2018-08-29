package com.cn.div.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/1/14.
 */
public class ABRegUtil {

    /**
     * 手机号码正则
     */



    public static final String REG_PHONE_CHINA = "^[1][3-8]+\\d{9}";
//    public static final String REG_PHONE_CHINA = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 邮箱正则
     */
    public static final String REG_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    public static boolean isRegiest(CharSequence text, String reg) {
        if (TextUtils.isEmpty(text))
            return false;
        return Pattern.matches(reg, text);
    }

}
