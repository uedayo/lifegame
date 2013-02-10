
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
        boolean expected = false;

        // Execute
        life.setDeathNext(false);
        boolean actual = life.isDeathNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 次に死ぬようにセットする
     */
    public void testSetDeathNextAsTrue() {
        // SetUp
        boolean expected = true;

        // Execute
        life.setDeathNext(true);
        boolean actual = life.isDeathNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 状態を更新して確認すると生きている
     */
    public void testRefreshToLive() {
        // SetUp
        boolean expected = false;
        life.setDeathNext(false);

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
        boolean expected = true;
        life.setDeathNext(true);

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
        boolean expected = false;
        life.setDeath(true);

        // Execute
        life.changeDeath();

        // Verify
        boolean actual = life.isDeath();
        assertEquals(expected, actual);
    }

    /**
     * 次の生死を反転させた結果死ぬ
     */
    public void testChangeDeathNextToDeath() {
        // SetUp
        boolean expected = true;
        life.setDeath(false);

        // Execute
        life.changeDeath();

        // Verify
        boolean actual = life.isDeath();
        assertEquals(expected, actual);
    }
}
