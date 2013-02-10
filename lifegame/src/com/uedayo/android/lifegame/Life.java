package com.uedayo.android.lifegame;

public class Life {

    private boolean live = false;
    private boolean liveNext = false;
    
    /**
     * ���̐������Z�b�g����
     * 
     * @param deathNext ���ɐ����Ă���Ȃ�true
     */
    public void setLiveNext(boolean liveNext) {
        this.liveNext = liveNext;
    }
    
    /**
     * �������X�V����
     */
    public boolean refresh() {
        live = liveNext;
        return live;
    }

    /**
     * �����𔽓]����
     */
    public void changeLive() {
        live = live ? false : true;
    }
    
    /**
     * ���ɐ����邩���ʂ����m�F����(�e�X�g�p)
     */
    boolean isLiveNext() {
        return liveNext;
    }

    /**
     * ���̐�����ݒ肷��(�e�X�g�p)
     */
    void setLive(boolean live) {
        this.live = live;
    }
    
    /**
     * ���̐������m�F����(�e�X�g�p)
     */
    boolean isLive() {
        return live;
    }
}
