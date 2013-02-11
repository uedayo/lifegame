
package com.uedayo.android.lifegame.dao;

import com.uedayo.android.util.SharedPreferencesUtil;

import android.content.SharedPreferences;

public class SettingDAO {

    // �v���t�@�����X��
    private static final String SETTING_PREFERENCE_NAME = "SETTING_PREFERENCE_NAME";

    // �L�[��
    private static final String KEY_REFRESH_INTERVAL = "KEY_REFRESH_INTERVAL";
    private static final String KEY_ROW_NUM = "KEY_ROW_NUM";
    private static final String KEY_COLUMN_NUM = "KEY_COLUMN_NUM";

    // �f�t�H���g�l
    public static final int DEFAULT_REFRESH_INTERVAL = 200;
    public static final int DEFAULT_ROW_NUM = 5;
    public static final int DEFAULT_COLUMN_NUM = 5;

    private static SharedPreferences sStaticSharedPreferences;
    static {
        sStaticSharedPreferences = SharedPreferencesUtil
                .getSharedPreferences(SETTING_PREFERENCE_NAME);
    }

    /**
     * ��ʂ̍X�V�Ԋu
     * 
     * @return int �P�ʂ�ms
     */
    public static int getRefreshInterval() {
        return sStaticSharedPreferences.getInt(KEY_REFRESH_INTERVAL, DEFAULT_REFRESH_INTERVAL);
    }

    /**
     * LifeMap�̍s��
     * 
     * @return int �s��
     */
    public static int getRowNum() {
        return sStaticSharedPreferences.getInt(KEY_ROW_NUM, DEFAULT_ROW_NUM);
    }

    /**
     * LifeMap�̗�
     * 
     * @return int ��
     */
    public static int getColumnNum() {
        return sStaticSharedPreferences.getInt(KEY_COLUMN_NUM, DEFAULT_COLUMN_NUM);
    }
}
