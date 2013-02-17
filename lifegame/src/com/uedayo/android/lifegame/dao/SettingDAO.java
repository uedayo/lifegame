
package com.uedayo.android.lifegame.dao;

import com.uedayo.android.util.SharedPreferencesUtil;

import android.content.SharedPreferences;

public class SettingDAO {

    // プリファレンス名
    private static final String SETTING_PREFERENCE_NAME = "SETTING_PREFERENCE_NAME";

    // キー名
    private static final String KEY_REFRESH_INTERVAL = "KEY_REFRESH_INTERVAL";
    private static final String KEY_ROW_NUM = "KEY_ROW_NUM";
    private static final String KEY_COLUMN_NUM = "KEY_COLUMN_NUM";

    // デフォルト値
    public static final int DEFAULT_REFRESH_INTERVAL = 200;
    public static final int DEFAULT_ROW_NUM = 5;
    public static final int DEFAULT_COLUMN_NUM = 5;

    private static SharedPreferences sStaticSharedPreferences;
    static {
        sStaticSharedPreferences = SharedPreferencesUtil
                .getSharedPreferences(SETTING_PREFERENCE_NAME);
    }

    /**
     * 画面の更新間隔
     * 
     * @return int 単位はms
     */
    public static int getRefreshInterval() {
        return sStaticSharedPreferences.getInt(KEY_REFRESH_INTERVAL, DEFAULT_REFRESH_INTERVAL);
    }

    /**
     * LifeMapの行数
     * 
     * @return int 行数
     */
    public static int getRowNum() {
        return sStaticSharedPreferences.getInt(KEY_ROW_NUM, DEFAULT_ROW_NUM);
    }

    /**
     * LifeMapの列数
     * 
     * @return int 列数
     */
    public static int getColumnNum() {
        return sStaticSharedPreferences.getInt(KEY_COLUMN_NUM, DEFAULT_COLUMN_NUM);
    }
}
