package com.uedayo.android.lifegame;

public class Life {

    private boolean death = true;
    private boolean deathNext = true;
    
    /**
     * 次に死ぬかどうかをセットする
     * 
     * @param deathNext 次に死ぬならtrue
     */
    public void setDeathNext(boolean deathNext) {
        this.deathNext = deathNext;
    }
    
    /**
     * 生死を更新する
     */
    public boolean refresh() {
        death = deathNext;
        return death;
    }

    /**
     * 生死を反転する
     */
    public void changeDeath() {
        death = death ? false : true;
    }
    
    /**
     * 次に生きるか死ぬかを確認する(テスト用)
     */
    boolean isDeathNext() {
        return deathNext;
    }

    /**
     * 今の生死を設定する(テスト用)
     */
    void setDeath(boolean death) {
        this.death = death;
    }
    
    /**
     * 今の生死を確認する(テスト用)
     */
    boolean isDeath() {
        return death;
    }
}
