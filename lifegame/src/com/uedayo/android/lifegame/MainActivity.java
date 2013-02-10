package com.uedayo.android.lifegame;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {

    // Lifeボタンのリソースidを格納する配列
    static final int[][] lifeButtons = {
        { R.id.btn_life11, R.id.btn_life12, R.id.btn_life13, R.id.btn_life14, R.id.btn_life15 },
        { R.id.btn_life21, R.id.btn_life22, R.id.btn_life23, R.id.btn_life24, R.id.btn_life25 },
        { R.id.btn_life31, R.id.btn_life32, R.id.btn_life33, R.id.btn_life34, R.id.btn_life35 },
        { R.id.btn_life41, R.id.btn_life42, R.id.btn_life43, R.id.btn_life44, R.id.btn_life45 },
        { R.id.btn_life51, R.id.btn_life52, R.id.btn_life53, R.id.btn_life54, R.id.btn_life55 }
    };
    // LifeControllerを格納する配列
    LifeController[][] lifeControllers = new LifeController[lifeButtons.length][lifeButtons[0].length];
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLifeController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * LifeControllerをセットする
     */
    private void setLifeController() {
        for(int i = 0; i < lifeButtons.length; i++) {
            for(int j = 0; j < lifeButtons[i].length; j++) {
                Button btn = (Button) findViewById(lifeButtons[i][j]);
                Life life = new Life();
                lifeControllers[i][j] = new LifeController(btn, life, this);
            }
        }
    }
}
