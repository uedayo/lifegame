
package com.uedayo.android.lifegame;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class LifeControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public LifeControllerTest() {
        super(MainActivity.class);
    }

    Life life;
    Button button;
    Context context;
    LifeController lifeController;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        life = new Life();
        context = getActivity().getApplicationContext();
        button = new Button(context);
        lifeController = new LifeController(button, life, context);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * ����ɐ����Ă���Life��0���Ǝ��Ɏ���
     */
    public void testSetLiveNext0ToDeath() {
        // SetUp
        boolean expected = false;

        // Execute
        lifeController.setNextLivingState(0);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ����ɐ����Ă���Life��1���Ǝ��Ɏ���
     */
    public void testSetLiveNext1ToDeath() {
        // SetUp
        boolean expected = false;

        // Execute
        lifeController.setNextLivingState(1);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�Ɏ���ł���A����ɐ����Ă���Life��2���Ǝ��񂾂܂�
     */
    public void testSetLiveNext2ToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(false);

        // Execute
        lifeController.setNextLivingState(2);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�ɐ����Ă���A����ɐ����Ă���Life��2���Ɛ������܂�
     */
    public void testSetLiveNext2ToLive() {
        // SetUp
        boolean expected = true;
        life.setLive(true);

        // Execute
        lifeController.setNextLivingState(2);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�Ɏ���ł���A����ɐ����Ă���Life��3���Ɛ����Ԃ�
     */
    public void testSetLiveNext3FromDeathToLive() {
        // SetUp
        boolean expected = true;
        life.setLive(false);

        // Execute
        lifeController.setNextLivingState(3);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�ɐ����Ă���A����ɐ����Ă���Life��3���Ɛ������܂�
     */
    public void testSetLiveNext3FromLiveToLive() {
        // SetUp
        boolean expected = true;
        life.setLive(true);

        // Execute
        lifeController.setNextLivingState(3);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�Ɏ���ł���A����ɐ����Ă���Life��3���Ǝ��񂾂܂�
     */
    public void testSetLiveNext4FromDeathToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(false);

        // Execute
        lifeController.setNextLivingState(4);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�ɐ����Ă���A����ɐ����Ă���Life��4���Ǝ���
     */
    public void testSetLiveNext4FromLiveToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(true);

        // Execute
        lifeController.setNextLivingState(4);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�Ɏ���ł���A����ɐ����Ă���Life��8���Ǝ��񂾂܂�
     */
    public void testSetLiveNext8FromDeathToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(false);

        // Execute
        lifeController.setNextLivingState(8);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * ���O�ɐ����Ă���A����ɐ����Ă���Life��8���Ǝ���
     */
    public void testSetLiveNext8FromLiveToDeath() {
        // SetUp
        boolean expected = false;
        life.setLive(true);

        // Execute
        lifeController.setNextLivingState(8);
        boolean actual = life.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }
}