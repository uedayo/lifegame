
package com.uedayo.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static Context mApplicationContext;
    public static SharedPreferences mSharedPreferences;

    /**
     * 初期化処理
     * 
     * @param context
     */
    public static void initialize(Context context) {
        if (mApplicationContext == null) {
            mApplicationContext = context.getApplicationContext();
        }
    }

    /**
     * SharedPreferencesを返す
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
     * boolean値の保存
     * 
     * @param key
     * @param value
     * @param editor
     * @return 成功時にtrue
     */
    public static boolean putBoolean(String key, Boolean value, SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * int値の保存
     * 
     * @param key
     * @param value
     * @param editor
     * @return 成功時にtrue
     */
    public static boolean putInt(String key, int value, SharedPreferences.Editor editor) {
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 値の削除
     * 
     * @param key
     * @param editor
     * @return 成功時にtrue
     */
    public static boolean remove(String key, SharedPreferences.Editor editor) {
        editor.remove(key);
        return editor.commit();
    }

    /**
     * すべての情報を削除
     * 
     * @param editor
     * @return 成功時はtrue
     */
    public static boolean clear(SharedPreferences.Editor editor) {
        return editor.clear().commit();
    }
}
