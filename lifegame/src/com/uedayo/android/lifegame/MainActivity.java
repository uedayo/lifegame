package com.uedayo.android.lifegame;

import java.util.Timer;
import java.util.TimerTask;

import com.uedayo.android.lifegame.dao.SettingDAO;
import com.uedayo.android.util.SharedPreferencesUtil;
import com.uedayo.lib.lifegame.LifeMap;
import com.uedayo.lib.lifegame.LifeMap.RefreshListener;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * Mainアクティビティ
 */
public class MainActivity extends Activity implements OnClickListener, RefreshListener{

    // Lifeボタンのリソースidを格納する配列
    static final int[][] lifeButtonIds = {
        { R.id.btn_life11, R.id.btn_life12, R.id.btn_life13, R.id.btn_life14, R.id.btn_life15 },
        { R.id.btn_life21, R.id.btn_life22, R.id.btn_life23, R.id.btn_life24, R.id.btn_life25 },
        { R.id.btn_life31, R.id.btn_life32, R.id.btn_life33, R.id.btn_life34, R.id.btn_life35 },
        { R.id.btn_life41, R.id.btn_life42, R.id.btn_life43, R.id.btn_life44, R.id.btn_life45 },
        { R.id.btn_life51, R.id.btn_life52, R.id.btn_life53, R.id.btn_life54, R.id.btn_life55 }
    };

    // 配列の行、列のインデックスの最大値
    static final int maxRowIndex = lifeButtonIds.length - 1;
    static final int maxColumnIndex = lifeButtonIds[0].length - 1;

    // ビュー
    Chronometer chronometer;
    TextView txtNumLife;
    Button btnStartstop;
    Button btnStep;
    Button btnRanndom;
    Button btnReset;

    // 状態
    private boolean isStarted = false;

    // タイマー
    private Timer timer;
    private TimerTask timerTask;
    private Handler mHandler;

    // 更新間隔
    private int refreshInterval;

    // LifeButtonを格納する配列
    Button[][] lifeButtons = new Button[lifeButtonIds.length][lifeButtonIds[0].length];

    // LifeMap
    LifeMap lifeMap = new LifeMap(lifeButtonIds.length, lifeButtonIds[0].length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        SharedPreferencesUtil.initialize(getApplicationContext());
        refreshInterval = SettingDAO.getRefreshInterval();
        mHandler = new Handler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * ビューを初期化する
     */
    public void setView() {
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        txtNumLife = (TextView) findViewById(R.id.num_life);
        setButtons();
        setLifeController();
    }

    /**
     * ボタンを初期化する
     */
    public void setButtons() {
        btnStartstop = (Button) findViewById(R.id.startstop);
        btnStep = (Button) findViewById(R.id.step);
        btnRanndom = (Button) findViewById(R.id.random);
        btnReset = (Button) findViewById(R.id.reset);
        btnStartstop.setOnClickListener(this);
        btnStep.setOnClickListener(this);
        btnRanndom.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    /**
     * LifeControllerをセットする
     */
    private void setLifeController() {
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                lifeButtons[i][j] = (Button) findViewById(lifeButtonIds[i][j]);
                lifeButtons[i][j].setOnClickListener(this);
                lifeMap.init(this);
            }
        }
    }

    /**
     * 一定間隔おきに起こる更新処理
     */
    private void refresh() {
        lifeMap.setNextLivingState();
        lifeMap.updateLivingStatus();
        updateNumLife();
    }

    /**
     * ボタンが押された時の動作
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startstop:
                onClickStartStop();
                break;
            case R.id.step:
                onClickStep();
                break;
            case R.id.random:
                onClickRandom();
                break;
            case R.id.reset:
                onClickReset();
                break;
            case R.id.btn_life11:
                lifeMap.reverseLivingState(0,0);
                break;
            case R.id.btn_life12:
                lifeMap.reverseLivingState(0,1);
                break;
            case R.id.btn_life13:
                lifeMap.reverseLivingState(0,2);
                break;
            case R.id.btn_life14:
                lifeMap.reverseLivingState(0,3);
                break;
            case R.id.btn_life15:
                lifeMap.reverseLivingState(0,4);
                break;
            case R.id.btn_life21:
                lifeMap.reverseLivingState(1,0);
                break;
            case R.id.btn_life22:
                lifeMap.reverseLivingState(1,1);
                break;
            case R.id.btn_life23:
                lifeMap.reverseLivingState(1,2);
                break;
            case R.id.btn_life24:
                lifeMap.reverseLivingState(1,3);
                break;
            case R.id.btn_life25:
                lifeMap.reverseLivingState(1,4);
                break;
            case R.id.btn_life31:
                lifeMap.reverseLivingState(2,0);
                break;
            case R.id.btn_life32:
                lifeMap.reverseLivingState(2,1);
                break;
            case R.id.btn_life33:
                lifeMap.reverseLivingState(2,2);
                break;
            case R.id.btn_life34:
                lifeMap.reverseLivingState(2,3);
                break;
            case R.id.btn_life35:
                lifeMap.reverseLivingState(2,4);
                break;
            case R.id.btn_life41:
                lifeMap.reverseLivingState(3,0);
                break;
            case R.id.btn_life42:
                lifeMap.reverseLivingState(3,1);
                break;
            case R.id.btn_life43:
                lifeMap.reverseLivingState(3,2);
                break;
            case R.id.btn_life44:
                lifeMap.reverseLivingState(3,3);
                break;
            case R.id.btn_life45:
                lifeMap.reverseLivingState(3,4);
                break;
            case R.id.btn_life51:
                lifeMap.reverseLivingState(4,0);
                break;
            case R.id.btn_life52:
                lifeMap.reverseLivingState(4,1);
                break;
            case R.id.btn_life53:
                lifeMap.reverseLivingState(4,2);
                break;
            case R.id.btn_life54:
                lifeMap.reverseLivingState(4,3);
                break;
            case R.id.btn_life55:
                lifeMap.reverseLivingState(4,4);
                break;
            default:
                break;
        }
    }

    /**
     * 開始と中断を行う
     */
    private void onClickStartStop() {
        if(!isStarted) {
            startLifecycle();
            restartChronometer();
        } else {
            stopLifecycle();
            resetChronometer();
        }
    }

    /**
     * 開始する
     */
    private void startLifecycle() {
        if(isStarted) {
            return;
        }
        startTimer();
        isStarted = true;
        btnStartstop.setText(R.string.stop);
    }

    /**
     * タイマーをセットする
     */
    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                });
            }
        };
        timer.schedule(timerTask, refreshInterval, refreshInterval);
    }

    /**
     * 終了する
     */
    private void stopLifecycle() {
        stopTimer();
        isStarted = false;
        btnStartstop.setText(R.string.start);
    }

    /**
     * タイマーを停止する
     */
    private void stopTimer() {
        if (timer == null || timerTask == null) {
            return;
        }
        timer.cancel();
        timer = null;
        isStarted = false;
    }
    
    /**
     * ステップ実行する
     */
    private void onClickStep() {
        isStarted = false;
        stopLifecycle();
        chronometer.stop();
        refresh();
    }

    /**
     * 各Lifeの現在の生死をランダムで設定する
     */
    private void onClickRandom() {
        stopLifecycle();
        lifeMap.random();
        resetChronometer();
        updateNumLife();
    }

    /**
     * 画面を初期状態にする
     */
    private void onClickReset() {
        stopLifecycle();
        lifeMap.reset();
        resetChronometer();
        updateNumLife();
    }

    /**
     * タイマーをリセットする
     */
    private void resetChronometer() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    /**
     * タイマーを再実行する
     */
    private void restartChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 生きている数を更新する
     */
    private void updateNumLife() {
        txtNumLife.setText(Integer.toString(lifeMap.getNumLife()));
    }

    
    /**
     * 生死に応じてLifeの表示を更新する
     */
    private void refreshButtonStatus() {
        for(int i=0; i < lifeButtonIds.length; i++) {
            for(int j=0; j < lifeButtonIds[i].length; j++) {
                boolean living = lifeMap.isLiving(i, j);
                int drawableId = living ? R.drawable.black : R.drawable.white;
                Drawable background = getResources().getDrawable(drawableId);
                lifeButtons[i][j].setBackgroundDrawable(background);
            }
        }
    }

    /**
     * Lifeの画面状態を更新
     */
    @Override
    public void refreshLifes() {
        refreshButtonStatus();
    }
}
