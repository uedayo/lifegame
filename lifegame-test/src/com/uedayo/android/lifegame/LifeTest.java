
package com.uedayo.android.lifegame;

import android.test.AndroidTestCase;

/**
 * Life�N���X�̃e�X�g
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
     * ���ɐ����Ă���悤�ɃZ�b�g����
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
     * ���Ɏ��ʂ悤�ɃZ�b�g����
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
     * ��Ԃ��X�V���Ċm�F����Ɛ����Ă���
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
     * ��Ԃ��X�V���Ċm�F����Ǝ���ł���
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
     * ���̐����𔽓]���������ʐ�����
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
     * ���̐����𔽓]���������ʎ���
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
