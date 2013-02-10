
package com.uedayo.android.lifegame;

import android.test.AndroidTestCase;

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
     * 次に生きるようにセットする
     */
    public void testSetLiveNextToLive() {
        // SetUp
        boolean expected = true;

        // Execute
        life.setNextLivingState(true);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 次に死ぬようにセットする
     */
    public void testSetLivingStateToDeath() {
        // SetUp
        boolean expected = false;

        // Execute
        life.setNextLivingState(false);
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
        life.setNextLivingState(true);

        // Execute
        boolean actual = life.updateLivingState();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 状態を更新して確認すると死んでいる
     */
    public void testRefreshToDeath() {
        // SetUp
        boolean expected = false;
        life.setNextLivingState(false);

        // Execute
        boolean actual = life.updateLivingState();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 今の生死を反転させた結果生きる
     */
    public void testChangeLivingStateToLive() {
        // SetUp
        boolean expected = true;
        life.setLive(false);

        // Execute
        life.reverseLivingState();

        // Verify
        boolean actual = life.isLiving();
        assertEquals(expected, actual);
    }

    /**
     * 次の生死を反転させた結果死ぬ
     */
    public void testChangeLivingStateToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(true);

        // Execute
        life.reverseLivingState();

        // Verify
        boolean actual = life.isLiving();
        assertEquals(expected, actual);
    }
}
