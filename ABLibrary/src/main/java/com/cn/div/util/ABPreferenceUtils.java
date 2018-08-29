package com.cn.div.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

public class ABPreferenceUtils {

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor shareEditor;

    private static ABPreferenceUtils preferenceUtils = null;

    private ABPreferenceUtils(Context context, String path) {
        sharedPreferences = context.getSharedPreferences(path,
                Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    public static ABPreferenceUtils getInstance(Context context) {
        if (preferenceUtils == null) {
            synchronized (ABPreferenceUtils.class) {
                if (preferenceUtils == null) {
                    preferenceUtils = new ABPreferenceUtils(context, ABConfig.PREFERENCE_CACHE_NAME);
                }
            }
        }
        return preferenceUtils;
    }

    public static String getStringParam(String key) {
        return getStringParam(key, "");
    }

    public static String getStringParam(String key, String defaultString) {
        return sharedPreferences.getString(key, defaultString);
    }

    public static void saveParam(String key, String value) {
        shareEditor.putString(key, value).commit();
    }

    public static boolean getBooleanParam(String key) {
        return getBooleanParam(key, false);
    }

    public static boolean getBooleanParam(String key, boolean defaultBool) {
        return sharedPreferences.getBoolean(key, defaultBool);
    }

    public static void saveParam(String key, boolean value) {
        shareEditor.putBoolean(key, value).commit();
    }

    public static int getIntParam(String key) {
        return getIntParam(key, 0);
    }

    public static int getIntParam(String key, int defaultInt) {
        return sharedPreferences.getInt(key, defaultInt);
    }

    public static void saveParam(String key, int value) {
        shareEditor.putInt(key, value).commit();
    }

    public static long getLongParam(String key) {
        return getLongParam(key, 0);
    }

    public static long getLongParam(String key, long defaultInt) {
        return sharedPreferences.getLong(key, defaultInt);
    }

    public static void saveParam(String key, Object obj) {
        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(obj);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            shareEditor.putString(key, objectVal).commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T getObjectParam(String key, Class<T> cls) {
        if (sharedPreferences.contains(key)) {
            String objectVal = sharedPreferences.getString(key, null);
            if (objectVal == null || objectVal.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public static void saveParam(String key, long value) {
        shareEditor.putLong(key, value).commit();
    }

    public static void removeKey(String key) {
        shareEditor.remove(key).commit();
    }

    public static void clear() {
        shareEditor.clear().commit();
    }
}
