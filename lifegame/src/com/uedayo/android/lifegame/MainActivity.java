package com.uedayo.android.lifegame;

import java.util.Timer;
import java.util.TimerTask;

import com.uedayo.android.lifegame.LifeManager.RefreshListener;
import com.uedayo.android.lifegame.dao.SettingDAO;
import com.uedayo.android.util.SharedPreferencesUtil;

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

    // LifeControllerを格納する配列
    LifeManager[][] lifeManagers = new LifeManager[lifeButtonIds.length][lifeButtonIds[0].length];

    // 周囲の生きているLifeの数を格納する配列
    int[][] lifeCounter = new int[lifeButtonIds.length][lifeButtonIds[0].length];

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
                lifeManagers[i][j] = new LifeManager();
                lifeManagers[i][j].setListener(this);
            }
        }
    }

    /**
     * 一定間隔おきに起こる更新処理
     */
    private void refresh() {
        setNextLivingState();
        updateLivingStatus();
        updateNumLife();
    }

    /**
     * 次の生死をセットする
     */
    private void setNextLivingState() {
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                updateLifeCounter(i, j);
                lifeManagers[i][j].setNextLivingState(lifeCounter[i][j]);
            }
        }
    }

    /**
     * 次の状態に移る
     */
    private void updateLivingStatus() {
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                lifeManagers[i][j].update();
            }
        }
    }

    /**
     * 周りの生きているLifeの数を加算する
     * @param i
     * @param j
     */
    private void updateLifeCounter(int i, int j) {
        lifeCounter[i][j] = 0;
        addTopLeft(i, j);
        addTop(i, j);
        addTopRight(i, j);
        addLeft(i, j);
        addRight(i, j);
        addBottomLeft(i, j);
        addBottom(i, j);
        addBottomRight(i, j);
    }

    /**
     * 左上が生きていればカウント
     * @param i
     * @param j
     */
    private void addTopLeft(int i, int j) {
        // 1行目、1列目の左上に他のLifeは無い
        if(i != 0 && j != 0) {
            lifeCounter[i][j] += lifeManagers[i-1][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * 真上が生きていればカウント
     * @param i
     * @param j
     */
    private void addTop(int i, int j) {
        // 1行目の上に他のLifeは無い
        if(i != 0) {
            lifeCounter[i][j] += lifeManagers[i-1][j].isLiving() ? 1 : 0;
        }
    }

    /**
     * 右上が生きていればカウント
     * @param i
     * @param j
     */
    private void addTopRight(int i, int j) {
        // 1行目、末尾の列の右上に他のLifeは無い
        if(i != 0 && j != maxColumnIndex) {
            lifeCounter[i][j] += lifeManagers[i-1][j+1].isLiving() ? 1 : 0;
        }
    }

    /**
     * 真左が生きていればカウント
     * @param i
     * @param j
     */
    private void addLeft(int i, int j) {
        // 1列目の左に他のLifeは無い
        if(j != 0) {
            lifeCounter[i][j] += lifeManagers[i][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * 真右が生きていればカウント
     * @param i
     * @param j
     */
    private void addRight(int i, int j) {
        // 末尾の列の右に他のLifeは無い
        if(j != maxColumnIndex) {
            lifeCounter[i][j] += lifeManagers[i][j+1].isLiving() ? 1 : 0;
        }
    }

    /**
     * 左下が生きていればカウント
     * @param i
     * @param j
     */
    private void addBottomLeft(int i, int j) {
        // 末尾の行、1列目の左下に他のLifeは無い
        if(i != maxRowIndex && j != 0) {
            lifeCounter[i][j] += lifeManagers[i+1][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * 真下が生きていればカウント
     * @param i
     * @param j
     */
    private void addBottom(int i, int j) {
        // 末尾の行の下に他のLifeは無い
        if(i != maxRowIndex) {
            lifeCounter[i][j] += lifeManagers[i+1][j].isLiving() ? 1 : 0;
        }
    }

    /**
     * 右下が生きていればカウント
     * @param i
     * @param j
     */
    private void addBottomRight(int i, int j) {
        // 末尾の行、末尾の列の右下に他のLifeは無い
        if(i != maxRowIndex && j != maxColumnIndex) {
            lifeCounter[i][j] += lifeManagers[i+1][j+1].isLiving() ? 1 : 0;
        }
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
                lifeManagers[0][0].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life12:
                lifeManagers[0][1].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life13:
                lifeManagers[0][2].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life14:
                lifeManagers[0][3].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life15:
                lifeManagers[0][4].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life21:
                lifeManagers[1][0].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life22:
                lifeManagers[1][1].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life23:
                lifeManagers[1][2].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life24:
                lifeManagers[1][3].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life25:
                lifeManagers[1][4].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life31:
                lifeManagers[2][0].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life32:
                lifeManagers[2][1].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life33:
                lifeManagers[2][2].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life34:
                lifeManagers[2][3].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life35:
                lifeManagers[2][4].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life41:
                lifeManagers[3][0].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life42:
                lifeManagers[3][1].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life43:
                lifeManagers[3][2].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life44:
                lifeManagers[3][3].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life45:
                lifeManagers[3][4].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life51:
                lifeManagers[4][0].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life52:
                lifeManagers[4][1].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life53:
                lifeManagers[4][2].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life54:
                lifeManagers[4][3].reverseLivingState();
                refreshButtonStatus();
                break;
            case R.id.btn_life55:
                lifeManagers[4][4].reverseLivingState();
                refreshButtonStatus();
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
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                lifeManagers[i][j].random();
            }
        }
        resetChronometer();
        updateNumLife();
    }

    /**
     * 画面を初期状態にする
     */
    private void onClickReset() {
        stopLifecycle();
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                lifeManagers[i][j].reset();
            }
        }
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
        txtNumLife.setText(Integer.toString(getNumLife()));
    }

    /**
     * 生きている数をカウントする
     */
    private int getNumLife() {
        int livingLifeNum = 0;
        for(int i = 0; i < lifeButtonIds.length; i++) {
            for(int j = 0; j < lifeButtonIds[i].length; j++) {
                livingLifeNum += lifeManagers[i][j].isLiving() ? 1 : 0;
            }
        }
        return livingLifeNum;
    }
    
    /**
     * 生死に応じてLifeの表示を更新する
     */
    private void refreshButtonStatus() {
        for(int i=0; i < lifeButtonIds.length; i++) {
            for(int j=0; j < lifeButtonIds[i].length; j++) {
                boolean living = lifeManagers[i][j].isLiving();
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
    public void refreshLife() {
        refreshButtonStatus();
    }
}
