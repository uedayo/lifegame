
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
        boolean expected = false;

        // Execute
        life.setDeathNext(false);
        boolean actual = life.isDeathNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���Ɏ��ʂ悤�ɃZ�b�g����
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
     * ��Ԃ��X�V���Ċm�F����Ɛ����Ă���
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
     * ��Ԃ��X�V���Ċm�F����Ǝ���ł���
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
     * ���̐����𔽓]���������ʐ�����
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
     * ���̐����𔽓]���������ʎ���
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
