package model.tiles.units;

import control.BoardGame;
import model.tiles.units.enemies.Monster;
import model.tiles.units.enemies.Trap;
import model.tiles.units.players.Mage;
import model.tiles.units.players.Rogue;
import model.tiles.units.players.warrior;

public class Characters {
    public warrior Create_Jon_Snow(){
        return new warrior("Jon Snow",300,30,4,new BoardGame(),3);
    }
    public warrior Create_The_Hound (){
        return new warrior("The Hound ",400,20,6,new BoardGame(),5);
    }
    public Mage Create_Melisandre (){
        return new Mage("Melisandre",400,5,1,new BoardGame(),300,30,15,5,6);
    }
    public Mage Create_Thoros_of_Myr(){
        return new Mage("Thoros of Myr",250,25,4,new BoardGame(),150,20,20,3,4);
    }
    public Rogue Create_Arya_Stark(){
        return new Rogue("Arya Stark",150,40,2,new BoardGame(),40);
    }
    public Rogue Create_Bronn(){
        return new Rogue("Bronn",250,35,3,new BoardGame(),50);
    }
    public Monster Create_Lannister_Solider(){
        return new Monster('s',"Lannister Solider",80,8,3,25,new BoardGame(),3);
    }
    public Monster Create_Lannister_Knight (){
        return new Monster('k',"Lannister_Knight",200,14,8,50,new BoardGame(),4);
    }
    public Monster Create_Queens_Guard(){
        return new Monster('q',"Queen’s Guard",400,20,15,100,new BoardGame(),5);
    }
    public Monster Create_Wright(){
        return new Monster('z',"Wright",600,30,15,100,new BoardGame(),3);
    }
    public Monster Create_Bear_Wright(){
        return new Monster('b',"Bear-Wright",1000,75,30,250,new BoardGame(),4);
    }
    public Monster Create_Giant_Wright(){
        return new Monster('b',"Giant-Wright",1500,100,40,500,new BoardGame(),5);
    }
    public Monster Create_White_Walker(){
        return new Monster('w',"White Walker",2000,150,50,1000,new BoardGame(),6);
    }
    public Monster Create_The_Mountain(){
        return new Monster('M',"The Mountain",1000,60,25,500,new BoardGame(),6);
    }
    public Monster Create_Queen_Cersei(){
        return new Monster('C',"Queen Cersei",100,10,10,1000,new BoardGame(),1);
    }
    public Monster Create_Nights_King(){
        return new Monster('K',"Night’s King",5000,300,150,5000,new BoardGame(),8);
    }
    public Trap Create_Bonus_Trap(){
        return new Trap(1,5,'B',"Bonus Trap",1,1,1,250,new BoardGame());
    }
    public Trap Create_Queens_Trap(){
        return new Trap(3,7,'Q',"Queen’s Trap",250,50,10,100,new BoardGame());
    }
    public Trap Create_Death_Trap(){
        return new Trap(1,10,'D',"Death Trap",500,100,20,250,new BoardGame());
    }

}
