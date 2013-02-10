
package com.uedayo.android.lifegame;

import android.test.AndroidTestCase;

/**
 * Lifeクラスのテスト
 */
public class LifeTest extends AndroidTestCase {

    Life life;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        life = new Life();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * 次に生きているようにセットする
     */
    public void testSetDeathNextAsFalse() {
        // SetUp
        boolean expected = true;

        // Execute
        life.setLiveNext(true);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 次に死ぬようにセットする
     */
    public void testSetDeathNextAsTrue() {
        // SetUp
        boolean expected = false;

        // Execute
        life.setLiveNext(false);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 状態を更新して確認すると生きている
     */
    public void testRefreshToLive() {
        // SetUp
        boolean expected = true;
        life.setLiveNext(true);

        // Execute
        boolean actual = life.refresh();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 状態を更新して確認すると死んでいる
     */
    public void testRefreshToDeath() {
        // SetUp
        boolean expected = false;
        life.setLiveNext(false);

        // Execute
        boolean actual = life.refresh();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 今の生死を反転させた結果生きる
     */
    public void testChangeDeathNextToLive() {
        // SetUp
        boolean expected = true;
        life.setLive(false);

        // Execute
        life.changeLive();

        // Verify
        boolean actual = life.isLiveNow();
        assertEquals(expected, actual);
    }

    /**
     * 次の生死を反転させた結果死ぬ
     */
    public void testChangeDeathNextToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(true);

        // Execute
        life.changeLive();

        // Verify
        boolean actual = life.isLiveNow();
        assertEquals(expected, actual);
    }
}
