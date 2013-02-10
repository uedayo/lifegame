package com.uedayo.android.lifegame;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {

    // Life�{�^���̃��\�[�Xid���i�[����z��
    static final int[][] lifeButtons = {
        { R.id.btn_life11, R.id.btn_life12, R.id.btn_life13, R.id.btn_life14, R.id.btn_life15 },
        { R.id.btn_life21, R.id.btn_life22, R.id.btn_life23, R.id.btn_life24, R.id.btn_life25 },
        { R.id.btn_life31, R.id.btn_life32, R.id.btn_life33, R.id.btn_life34, R.id.btn_life35 },
        { R.id.btn_life41, R.id.btn_life42, R.id.btn_life43, R.id.btn_life44, R.id.btn_life45 },
        { R.id.btn_life51, R.id.btn_life52, R.id.btn_life53, R.id.btn_life54, R.id.btn_life55 }
    };

    // �z��̍s�A��̃C���f�b�N�X�̍ő�l
    static final int maxRowIndex = lifeButtons.length - 1;
    static final int maxColumnIndex = lifeButtons[0].length - 1;
    
    // LifeController���i�[����z��
    LifeController[][] lifeControllers = new LifeController[lifeButtons.length][lifeButtons[0].length];

    // ���͂̐����Ă���Life�̐����i�[����z��
    int[][] lifeCounter = new int[lifeButtons.length][lifeButtons[0].length];

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
     * LifeController���Z�b�g����
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

    /**
     * ���Ԋu�����ɋN����X�V����
     */
    private void refresh() {
        setNextLivingState();
        updateLivingStatus();
    }

    /**
     * ���̐������Z�b�g����
     */
    private void setNextLivingState() {
        for(int i = 0; i < lifeButtons.length; i++) {
            for(int j = 0; j < lifeButtons[i].length; j++) {
                setLifeCounter(i, j);
                lifeControllers[i][j].setNextLivingState(lifeCounter[i][j]);
            }
        }
    }

    /**
     * ���̏�ԂɈڂ�
     */
    private void updateLivingStatus() {
        for(int i = 0; i < lifeButtons.length; i++) {
            for(int j = 0; j < lifeButtons[i].length; j++) {
                lifeControllers[i][j].update();
            }
        }
    }

    /**
     * ����̐����Ă���Life�̐������Z����
     * @param i
     * @param j
     */
    private void setLifeCounter(int i, int j) {
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
     * ���オ�����Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addTopLeft(int i, int j) {
        // 1�s�ځA1��ڂ̍���ɑ���Life�͖���
        if(i != 0 && j != 0) {
            lifeCounter[i][j] += lifeControllers[i-1][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * �^�オ�����Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addTop(int i, int j) {
        // 1�s�ڂ̏�ɑ���Life�͖���
        if(i != 0) {
            lifeCounter[i][j] += lifeControllers[i-1][j].isLiving() ? 1 : 0;
        }
    }

    /**
     * �E�オ�����Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addTopRight(int i, int j) {
        // 1�s�ځA�����̗�̉E��ɑ���Life�͖���
        if(i != 0 && j != maxColumnIndex) {
            lifeCounter[i][j] += lifeControllers[i-1][j+1].isLiving() ? 1 : 0;
        }
    }

    /**
     * �^���������Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addLeft(int i, int j) {
        // 1��ڂ̍��ɑ���Life�͖���
        if(j != 0) {
            lifeCounter[i][j] += lifeControllers[1][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * �^�E�������Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addRight(int i, int j) {
        // �����̗�̉E�ɑ���Life�͖���
        if(j != maxColumnIndex) {
            lifeCounter[i][j] += lifeControllers[1][j+1].isLiving() ? 1 : 0;
        }
    }

    /**
     * �����������Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addBottomLeft(int i, int j) {
        // �����̍s�A1��ڂ̍����ɑ���Life�͖���
        if(i != maxRowIndex && j != 0) {
            lifeCounter[i][j] += lifeControllers[i+1][j-1].isLiving() ? 1 : 0;
        }
    }

    /**
     * �^���������Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addBottom(int i, int j) {
        // �����̍s�̉��ɑ���Life�͖���
        if(i != maxRowIndex) {
            lifeCounter[i][j] += lifeControllers[i+1][j].isLiving() ? 1 : 0;
        }
    }

    /**
     * �E���������Ă���΃J�E���g
     * @param i
     * @param j
     */
    private void addBottomRight(int i, int j) {
        // �����̍s�A�����̗�̉E���ɑ���Life�͖���
        if(i != maxRowIndex && j != maxColumnIndex) {
            lifeCounter[i][j] += lifeControllers[i+1][j+1].isLiving() ? 1 : 0;
        }
    }
}
