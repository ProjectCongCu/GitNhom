package com.example.administrator.ssd;

/**
 * Created by Administrator on 6/3/2016.
 */
public class DataGame_DTO {
    int Level;
    int Star;
    int BestMove;



    public DataGame_DTO() {
    }

    public DataGame_DTO(int level, int star, int bestMove) {
        Level = level;
        Star = star;
        BestMove = bestMove;
    }

    public int getLevel(){
        return Level;
    }

    public int getStar(){
        return Star;
    }

    public int getBestMove(){
        return BestMove;
    }

    public void setLevel(int Level){
        this.Level = Level;
    }

    public void setStar(int Star){
        this.Star = Star;
    }

    public void setBestMove(int BestMove){
        this.BestMove = BestMove;
    }
}
