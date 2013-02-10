
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
    boolean nextLivingState;

    /**
     * �R���X�g���N�^
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
     * �N���b�N�����ۂ̓�����Z�b�g����
     * 
     * @param button
     */
    private void setOnClick() {
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LifeController.this.life.reverseLivingState();
                refresh();
            }
        });
    }

    /**
     * �����ɉ����ă{�^���̕\�����X�V����
     */
    private void refresh() {
        boolean death = life.isLiving();
        int drawableId = death ? R.drawable.white : R.drawable.black;
        Drawable background = context.getResources().getDrawable(drawableId);
        button.setBackgroundDrawable(background);
    }
    
    /**
     * ���݂̐�����Ԃ�
     */
    public boolean isLiving() {
        return life.isLiving();
    }
    
    /**
     * ���̐������Z�b�g����
     * 
     * @param liveNum ���݂̎��͂̐����Ă���Life�̐�
     */
    public void setNextLivingState(int liveNum) {
        switch (liveNum) {
            // 0,1�̏ꍇ�A�ߑa�ɂ�莀��
            case 0:
            case 1:
                nextLivingState = false;
                break;
            // 2�̏ꍇ�A���݂̏�Ԃ��p��
            case 2:
                nextLivingState = life.isLiving();
                break;
            // 3�̏ꍇ�A����ł���ꍇ�ł��V���ɐ���
            case 3:
                nextLivingState = true;
                break;
            // 4�ȏ�̏ꍇ�A�ߖ��ɂ�莀��
            default:
                nextLivingState = false;
                break;
        }
        life.setNextLivingState(nextLivingState);
    }

    /**
     * ���̏�ԂɑJ��
     */
    public void update() {
        life.updateLivingState();
    }
}
