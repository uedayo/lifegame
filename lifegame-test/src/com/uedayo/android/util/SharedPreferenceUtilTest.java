
package com.uedayo.android.util;

import com.uedayo.android.lifegame.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.test.ActivityInstrumentationTestCase2;

public class SharedPreferenceUtilTest extends ActivityInstrumentationTestCase2<MainActivity> {

    // プリファレンス名
    private static final String PREF_NAME = "PREF_NAME";

    // キー名
    private static final String KEY_TEST_BOOLEAN = "KEY_TEST_BOOLEAN";
    private static final String KEY_TEST_INT = "KEY_TEST_INT";

    Context mContext;
    SharedPreferences mSharedPreferences;

    public SharedPreferenceUtilTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = getActivity().getApplicationContext();
        SharedPreferencesUtil.initialize(mContext);
        String name = PREF_NAME;
        mSharedPreferences = SharedPreferencesUtil.getSharedPreferences(name);
    }

    /**
     * boolean値を保存するテスト
     */
    public void testPutBoolean() {
        // SetUp
        boolean expected = true;
        Editor editor = mSharedPreferences.edit();

        // Execute
        SharedPreferencesUtil.putBoolean(KEY_TEST_BOOLEAN, true, editor);
        boolean actual = mSharedPreferences.getBoolean(KEY_TEST_BOOLEAN, false);

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * int値を保存するテスト
     */
    public void testPutInt() {
        // SetUp
        int expected = 1;
        Editor editor = mSharedPreferences.edit();

        // Execute
        SharedPreferencesUtil.putInt(KEY_TEST_INT, 1, editor);
        int actual = mSharedPreferences.getInt(KEY_TEST_INT, 0);

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 値を削除するテスト
     */
    public void testRemove() {
        // SetUp
        boolean expected = false;
        Editor editor = mSharedPreferences.edit();
        SharedPreferencesUtil.putBoolean(KEY_TEST_BOOLEAN, true, editor);

        // Execute
        SharedPreferencesUtil.remove(KEY_TEST_BOOLEAN, editor);
        boolean actual = mSharedPreferences.getBoolean(KEY_TEST_BOOLEAN, false);

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 値を削除するテスト
     */
    public void testClear() {
        // SetUp
        int expected = 0;
        Editor editor = mSharedPreferences.edit();
        SharedPreferencesUtil.putInt(KEY_TEST_INT, 1, editor);

        // Execute
        SharedPreferencesUtil.clear(editor);
        int actual = mSharedPreferences.getInt(KEY_TEST_INT, 0);

        // Verify
        assertEquals(expected, actual);
    }
}
