
package com.uedayo.android.lifegame;

import java.util.EventListener;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LifeController {

    Life life;
    Context context;
    boolean nextLivingState;
    
    private RefreshListener listener = null;

    /**
     * コンストラクタ
     * 
     * @param button
     * @param life
     */
    public LifeController(Life life, Context context) {
        this.life = life;
        this.context = context;
    }

    /**
     * 現在の生死を返す
     */
    public boolean isLiving() {
        return life.isLiving();
    }
    
    /**
     * 次の生死をセットする
     * 
     * @param liveNum 現在の周囲の生きているLifeの数
     */
    public void setNextLivingState(int liveNum) {
        switch (liveNum) {
            // 0,1の場合、過疎により死滅
            case 0:
            case 1:
                nextLivingState = false;
                break;
            // 2の場合、現在の状態が継続
            case 2:
                nextLivingState = life.isLiving();
                break;
            // 3の場合、死んでいる場合でも新たに生成
            case 3:
                nextLivingState = true;
                break;
            // 4以上の場合、過密により死滅
            default:
                nextLivingState = false;
                break;
        }
        life.setNextLivingState(nextLivingState);
    }

    /**
     * 次の状態に遷移する
     */
    public void update() {
        life.updateLivingState();
        listener.refreshLife();
    }

    /**
     * 現在の生死をランダムに設定する
     */
    public void random() {
        int random = (int) Math.round(Math.random());
        boolean isLiving = random == 1 ? true : false;
        life.setLive(isLiving);
        listener.refreshLife();
    }

    /**
     * 現在の生死をリセットする(死に設定する)
     */
    public void reset() {
        life.setLive(false);
        listener.refreshLife();
    }
    
    /**
     * Lifeの状態更新のリスナー
     */
    public interface RefreshListener extends EventListener {
        
        /**
         * 更新の要求を通知する
         */
        public void refreshLife();
    }
    
    /**
     * リスナーを追加する
     * @param listener
     */
    public void setListener(RefreshListener listener) {
        this.listener = listener;
    }

    /**
     * リスナーを削除する
     * @param listener
     */
    public void removeListener(RefreshListener listener) {
        this.listener = null;
    }
}
