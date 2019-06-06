package com.jaen.pedro.objects;

import com.badlogic.gdx.Gdx;
import com.jaen.pedro.utils.Constants;

public class Score {
    private int score;
    private Hero heroe;
    private int liveCounter;

    public Score() {
        score=0;
        liveCounter=0;
    }

    public void increAseScore(int number){
        score+=number;
        liveCounter+=number;
        while(liveCounter - Constants.SCORE_LIVE>=0){
            liveCounter-=Constants.SCORE_LIVE;
            heroe.lives++;
        }

    }

    public int getScore() {
        return score;
    }

    public void setHeroe(Hero heroe) {
        this.heroe = heroe;
    }
}
