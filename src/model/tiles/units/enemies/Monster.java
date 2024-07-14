package model.tiles.units.enemies;

import control.BoardGame;
import model.tiles.units.players.Player;
import model.utils.Position;

import java.util.Random;

public class Monster extends Enemy {
    protected final  int vision;
    public Monster(char tile, String name, int hitPoints, int attack, int defense, int experienceValue, BoardGame board, int vision) {
        super(tile, name, hitPoints, attack, defense, experienceValue, board);
        this.vision = vision;
    }
    public void OnTick(){
        Player p=board.player;
        Position pos= p.getPosition();
        if (pos.range(position)<vision){
            int x= position.getX()-pos.getX();
            int y= position.getY()-pos.getY();
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
                    move(position.getX(),position.getY()+1);
                }
                else {
                    move(position.getX(),position.getY()-1);
                }
            }
        }
        else{
            Random rand =new Random();
            int step= rand.nextInt(0,4);
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
            }
        }
    }


    @Override
    public void SpecialAbility() {

    }


}
