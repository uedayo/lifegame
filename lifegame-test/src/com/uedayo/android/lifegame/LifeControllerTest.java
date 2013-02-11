
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
     * 周りに生きているLifeが0だと次に死ぬ
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
     * 周りに生きているLifeが1だと次に死ぬ
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
     * 事前に死んでおり、周りに生きているLifeが2だと死んだまま
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
     * 事前に生きており、周りに生きているLifeが2だと生きたまま
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
     * 事前に死んでおり、周りに生きているLifeが3だと生き返る
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
     * 事前に生きており、周りに生きているLifeが3だと生きたまま
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
     * 事前に死んでおり、周りに生きているLifeが3だと死んだまま
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
     * 事前に生きており、周りに生きているLifeが4だと死ぬ
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
     * 事前に死んでおり、周りに生きているLifeが8だと死んだまま
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
     * 事前に生きており、周りに生きているLifeが8だと死ぬ
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