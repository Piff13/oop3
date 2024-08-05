package model.tiles.units.enemies;

import control.BoardGame;
import model.tiles.units.players.Player;
import model.utils.BoardHelper;
import model.utils.Position;
import model.utils.callbacks.MessageCallback;

import java.util.Random;

public class Monster extends Enemy {
    protected final int vision;
    public Monster(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, int vision, BoardHelper boardHelper, MessageCallback callback) {
        super(tile, name, hitPoints, attack, defense, experienceValue,boardHelper, callback);
        this.vision = vision;
    }
    public void chasePlayer(Position posPlayer){
        int x= position.getX()-posPlayer.getX();
        int y= position.getY()-posPlayer.getY();
        int AbsX=Math.abs(x);
        int AbsY=Math.abs(y);
        if(AbsX>AbsY){
            if(x>0){
                move(position.getX()-1,position.getY());
            }
            else {
                move(position.getX()+1,position.getY());
            }
        }
        else {
            if(y>0){
                move(position.getX(),position.getY()-1);
            }
            else {
                move(position.getX(),position.getY()+1);
            }
        }
    }
    public void randomStep(){
        Random rand =new Random();
        int step= rand.nextInt(0,5);
        if(step==0){
            move(position.getX(),position.getY()+1);
        }
        else if(step==1){
            move(position.getX(),position.getY()-1);
        }
        else if(step==2){
            move(position.getX()+1,position.getY());
        }
        else if(step==3){
            move(position.getX()-1,position.getY());
        } else
            return;//do nothing
    }
    public boolean isPlayerInVision(Position player){
        return player.range(position)<vision;
    }
    public void OnTick(){
        Position pos= boardHelper.getPlayerPosition();
        if (isPlayerInVision(pos)){
            chasePlayer(pos);
        }
        else{
            randomStep();
        }
    }

    public Monster(Monster other) {
        super(other.tile, other.name, other.health.getCapacity(), other.attack, other.defense, other.experienceValue, other.boardHelper, other.callBack);
        this.vision = other.vision;
    }

    @Override
    public String toString() {
        return super.toString() + " ,vision range: " + vision;
    }

    public Enemy getCopy(){
        return new Monster(this);
    }
}
