package com.uedayo.android.lifegame;

public class Life {

    private boolean death = true;
    private boolean deathNext = true;
    
    /**
     * ���Ɏ��ʂ��ǂ������Z�b�g����
     * 
     * @param deathNext ���Ɏ��ʂȂ�true
     */
    public void setDeathNext(boolean deathNext) {
        this.deathNext = deathNext;
    }
    
    /**
     * �������X�V����
     */
    public boolean refresh() {
        death = deathNext;
        return death;
    }

    /**
     * �����𔽓]����
     */
    public void changeDeath() {
        death = death ? false : true;
    }
    
    /**
     * ���ɐ����邩���ʂ����m�F����(�e�X�g�p)
     */
    boolean isDeathNext() {
        return deathNext;
    }

    /**
     * ���̐�����ݒ肷��(�e�X�g�p)
     */
    void setDeath(boolean death) {
        this.death = death;
    }
    
    /**
     * ���̐������m�F����(�e�X�g�p)
     */
    boolean isDeath() {
        return death;
    }
}
