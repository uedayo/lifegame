
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
     * ���ɐ�����悤�ɃZ�b�g����
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
     * ���Ɏ��ʂ悤�ɃZ�b�g����
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
     * ��Ԃ��X�V���Ċm�F����Ɛ����Ă���
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
     * ��Ԃ��X�V���Ċm�F����Ǝ���ł���
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
     * ���̐����𔽓]���������ʐ�����
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
     * ���̐����𔽓]���������ʎ���
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
