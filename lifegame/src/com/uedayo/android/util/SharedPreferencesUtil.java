
package com.uedayo.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static Context mApplicationContext;
    public static SharedPreferences mSharedPreferences;

    /**
     * ����������
     * 
     * @param context
     */
    public static void initialize(Context context) {
        if (mApplicationContext == null) {
            mApplicationContext = context.getApplicationContext();
        }
    }

    /**
     * SharedPreferences��Ԃ�
     * 
     * @param name
     * @return SharedPreferences
     */
    public static SharedPreferences getSharedPreferences(String name) {
        if (mApplicationContext != null) {
            mSharedPreferences = mApplicationContext.getSharedPreferences(name,
                    Context.MODE_PRIVATE);
            return mSharedPreferences;
        } else {
            throw new IllegalStateException("mApplicationContext is not initialized.");
        }
    }

    /**
     * boolean�l�̕ۑ�
     * 
     * @param key
     * @param value
     * @param editor
     * @return ��������true
     */
    public static boolean putBoolean(String key, Boolean value, SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * int�l�̕ۑ�
     * 
     * @param key
     * @param value
     * @param editor
     * @return ��������true
     */
    public static boolean putInt(String key, int value, SharedPreferences.Editor editor) {
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * �l�̍폜
     * 
     * @param key
     * @param editor
     * @return ��������true
     */
    public static boolean remove(String key, SharedPreferences.Editor editor) {
        editor.remove(key);
        return editor.commit();
    }

    /**
     * ���ׂĂ̏����폜
     * 
     * @param editor
     * @return ��������true
     */
    public static boolean clear(SharedPreferences.Editor editor) {
        return editor.clear().commit();
    }
}
