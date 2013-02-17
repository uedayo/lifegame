
package com.uedayo.android.lifegame;

import android.test.ActivityInstrumentationTestCase2;

/**
 * LifeManagerのテスト
 */
public class LifeManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public LifeManagerTest() {
        super(MainActivity.class);
    }

    LifeManager lifeManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lifeManager = new LifeManager();
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
        lifeManager.setNextLivingState(0);
        boolean actual = lifeManager.isLiveNext();

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
        lifeManager.setNextLivingState(1);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に死んでおり、周りに生きているLifeが2だと死んだまま
     */
    public void testSetLiveNext2ToDeath() {
        // SetUp
        boolean expected = false;
        lifeManager.setLive(false);

        // Execute
        lifeManager.setNextLivingState(2);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に生きており、周りに生きているLifeが2だと生きたまま
     */
    public void testSetLiveNext2ToLive() {
        // SetUp
        boolean expected = true;
        lifeManager.setLive(true);

        // Execute
        lifeManager.setNextLivingState(2);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に死んでおり、周りに生きているLifeが3だと生き返る
     */
    public void testSetLiveNext3FromDeathToLive() {
        // SetUp
        boolean expected = true;
        lifeManager.setLive(false);

        // Execute
        lifeManager.setNextLivingState(3);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に生きており、周りに生きているLifeが3だと生きたまま
     */
    public void testSetLiveNext3FromLiveToLive() {
        // SetUp
        boolean expected = true;
        lifeManager.setLive(true);

        // Execute
        lifeManager.setNextLivingState(3);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に死んでおり、周りに生きているLifeが3だと死んだまま
     */
    public void testSetLiveNext4FromDeathToDeath() {
        // SetUp
        boolean expected = false;
        lifeManager.setLive(false);

        // Execute
        lifeManager.setNextLivingState(4);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に生きており、周りに生きているLifeが4だと死ぬ
     */
    public void testSetLiveNext4FromLiveToDeath() {
        // SetUp
        boolean expected = false;
        lifeManager.setLive(true);

        // Execute
        lifeManager.setNextLivingState(4);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に死んでおり、周りに生きているLifeが8だと死んだまま
     */
    public void testSetLiveNext8FromDeathToDeath() {
        // SetUp
        boolean expected = false;
        lifeManager.setLive(false);

        // Execute
        lifeManager.setNextLivingState(8);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }

    /**
     * 事前に生きており、周りに生きているLifeが8だと死ぬ
     */
    public void testSetLiveNext8FromLiveToDeath() {
        // SetUp
        boolean expected = false;
        lifeManager.setLive(true);

        // Execute
        lifeManager.setNextLivingState(8);
        boolean actual = lifeManager.isLiveNext();

        // Verify
        assertEquals(expected, actual);
    }
}
