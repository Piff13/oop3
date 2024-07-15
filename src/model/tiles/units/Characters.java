package model.tiles.units;

import control.BoardGame;
import model.tiles.units.enemies.Monster;
import model.tiles.units.enemies.Trap;
import model.tiles.units.players.Mage;
import model.tiles.units.players.Rogue;
import model.tiles.units.players.warrior;
import model.utils.BoardHelper;
import model.utils.callbacks.MessageCallback;

public class Characters {
    private MessageCallback messageCallback;
    private BoardHelper boardHelper;
    public Characters(MessageCallback call, BoardHelper boardHelper){
        this.messageCallback = call;
        this.boardHelper = boardHelper;
    }
    public warrior Create_Jon_Snow(){
        return new warrior("Jon Snow",300,30,4,3, boardHelper, messageCallback);
    }
    public warrior Create_The_Hound (){
        return new warrior("The Hound ",400,20,6,5,boardHelper, messageCallback);
    }
    public Mage Create_Melisandre (){
        return new Mage("Melisandre",400,5,1,300,30,15,5,6, boardHelper, messageCallback);
    }
    public Mage Create_Thoros_of_Myr(){
        return new Mage("Thoros of Myr",250,25,4,150,20,20,3,4, boardHelper, messageCallback);
    }
    public Rogue Create_Arya_Stark(){
        return new Rogue("Arya Stark",150,40,2,40, boardHelper, messageCallback);
    }
    public Rogue Create_Bronn(){
        return new Rogue("Bronn",250,35,3,50, boardHelper, messageCallback);
    }
    public Monster Create_Lannister_Solider(){
        return new Monster('s',"Lannister Solider",80,8,3,25,3, boardHelper, messageCallback);
    }
    public Monster Create_Lannister_Knight (){
        return new Monster('k',"Lannister_Knight",200,14,8,50,4, boardHelper, messageCallback);
    }
    public Monster Create_Queens_Guard(){
        return new Monster('q',"Queen’s Guard",400,20,15,100,5, boardHelper, messageCallback);
    }
    public Monster Create_Wright(){
        return new Monster('z',"Wright",600,30,15,100,3, boardHelper, messageCallback);
    }
    public Monster Create_Bear_Wright(){
        return new Monster('b',"Bear-Wright",1000,75,30,250,4, boardHelper, messageCallback);
    }
    public Monster Create_Giant_Wright(){
        return new Monster('g',"Giant-Wright",1500,100,40,500,5, boardHelper, messageCallback);
    }
    public Monster Create_White_Walker(){
        return new Monster('w',"White Walker",2000,150,50,1000,6, boardHelper, messageCallback);
    }
    public Monster Create_The_Mountain(){
        return new Monster('M',"The Mountain",1000,60,25,500,6, boardHelper, messageCallback);
    }
    public Monster Create_Queen_Cersei(){
        return new Monster('C',"Queen Cersei",100,10,10,1000,1, boardHelper, messageCallback);
    }
    public Monster Create_Nights_King(){
        return new Monster('K',"Night’s King",5000,300,150,5000,8, boardHelper, messageCallback);
    }
    public Trap Create_Bonus_Trap(){
        return new Trap(1,5,'B',"Bonus Trap",1,1,1,250,boardHelper, messageCallback);
    }
    public Trap Create_Queens_Trap(){
        return new Trap(3,7,'Q',"Queen’s Trap",250,50,10,100,boardHelper, messageCallback);
    }
    public Trap Create_Death_Trap(){
        return new Trap(1,10,'D',"Death Trap",500,100,20,250,boardHelper, messageCallback);
    }

}
