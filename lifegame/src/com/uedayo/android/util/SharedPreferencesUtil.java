
package com.uedayo.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static Context mApplicationContext;
    public static SharedPreferences mSharedPreferences;

    /**
     * ‰Šú‰»ˆ—
     * 
     * @param context
     */
    public static void initialize(Context context) {
        if (mApplicationContext == null) {
            mApplicationContext = context.getApplicationContext();
        }
    }

    /**
     * SharedPreferences‚ğ•Ô‚·
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
     * boolean’l‚Ì•Û‘¶
     * 
     * @param key
     * @param value
     * @param editor
     * @return ¬Œ÷‚Étrue
     */
    public static boolean putBoolean(String key, Boolean value, SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * int’l‚Ì•Û‘¶
     * 
     * @param key
     * @param value
     * @param editor
     * @return ¬Œ÷‚Étrue
     */
    public static boolean putInt(String key, int value, SharedPreferences.Editor editor) {
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * ’l‚Ìíœ
     * 
     * @param key
     * @param editor
     * @return ¬Œ÷‚Étrue
     */
    public static boolean remove(String key, SharedPreferences.Editor editor) {
        editor.remove(key);
        return editor.commit();
    }

    /**
     * ‚·‚×‚Ä‚Ìî•ñ‚ğíœ
     * 
     * @param editor
     * @return ¬Œ÷‚Ítrue
     */
    public static boolean clear(SharedPreferences.Editor editor) {
        return editor.clear().commit();
    }
}
