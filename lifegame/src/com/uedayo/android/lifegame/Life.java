package com.uedayo.android.lifegame;

public class Life {

    private boolean live = false;
    private boolean liveNext = false;
    
    /**
     * 次の生死をセットする
     * 
     * @param deathNext 次に生きているならtrue
     */
    public void setLiveNext(boolean liveNext) {
        this.liveNext = liveNext;
    }
    
    /**
     * 生死を更新する
     */
    public boolean refresh() {
        live = liveNext;
        return live;
    }

    /**
     * 生死を反転する
     */
    public void changeLive() {
        live = live ? false : true;
    }
    
    /**
     * 次に生きるか死ぬかを確認する(テスト用)
     */
    boolean isLiveNext() {
        return liveNext;
    }

    /**
     * 今の生死を設定する(テスト用)
     */
    void setLive(boolean live) {
        this.live = live;
    }
    
    /**
     * 今の生死を確認する(テスト用)
     */
    boolean isLive() {
        return live;
    }
}
