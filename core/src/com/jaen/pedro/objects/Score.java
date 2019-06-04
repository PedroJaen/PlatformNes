package com.jaen.pedro.objects;

public class Score {
    private int score;
    private int previusScore;
    private Hero heroe;

    public Score() {
        score=0;
    }

    public void increAseScore(int number){
        previusScore=score;
        score+=number;
        if(score-previusScore>=10000){
            aumentaVidas();
        }
    }

    private void aumentaVidas() {
        int nLives=(score-previusScore)/10000;
        if(nLives!=0){
            int previusLives=heroe.getLives();
            heroe.setLives(previusLives+nLives);
        }
    }

    public int getScore() {
        return score;
    }

    public void setHeroe(Hero heroe) {
        this.heroe = heroe;
    }
}
