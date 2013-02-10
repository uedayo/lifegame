
package com.uedayo.android.lifegame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LifeController {

    Button button;
    Life life;
    Context context;

    /**
     * コンストラクタ
     * 
     * @param button
     * @param life
     */
    public LifeController(Button button, Life life, Context context) {
        this.button = button;
        this.life = life;
        this.context = context;
        setOnClick();
    }

    /**
     * クリックした際の動作をセットする
     * 
     * @param button
     */
    private void setOnClick() {
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LifeController.this.life.changeDeath();
                refresh();
            }
        });
    }

    /**
     * 生死に応じてボタンの表示を更新する
     */
    private void refresh() {
        boolean death = life.isDeath();
        int drawableId = death ? R.drawable.white : R.drawable.black;
        Drawable background = context.getResources().getDrawable(drawableId);
        button.setBackgroundDrawable(background);
    }
}
