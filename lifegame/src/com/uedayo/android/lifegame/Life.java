package com.uedayo.android.lifegame;

public class Life {

    // ���݂̐���
    private boolean currentLivingState = false;
    // ���̐���
    private boolean nextLivingState = false;
    
    /**
     * ���̐������Z�b�g����
     * 
     * @param nextLivingState ���ɐ����Ă���Ȃ�true
     */
    public void setNextLivingState(boolean nextLivingState) {
        this.nextLivingState = nextLivingState;
    }
    
    /**
     * �������X�V����
     */
    public boolean updateLivingState() {
        currentLivingState = nextLivingState;
        return currentLivingState;
    }

    /**
     * �����𔽓]����
     */
    public void reverseLivingState() {
        currentLivingState = currentLivingState ? false : true;
    }
    
    /**
     * ���̐������m�F����(�e�X�g�p)
     */
    boolean isLiveNext() {
        return nextLivingState;
    }

    /**
     * ���̐�����ݒ肷��
     */
    void setLive(boolean live) {
        this.currentLivingState = live;
    }
    
    /**
     * ���̐������m�F����
     */
    public boolean isLiving() {
        return currentLivingState;
    }
}
