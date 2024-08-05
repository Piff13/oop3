package model.tiles;

import model.tiles.units.Unit;
import model.utils.Position;

public abstract class Tile {
    public char tile;
    protected Position position;

    public Tile(char tile){
        this.tile = tile;
    }

    public void initialize(Position p){
        this.position = p;
    }
    public Position getPosition(){
        return position;
    }
    public void setTile(char tile){
        this.tile = tile;
    }
    public void swapPosition(Tile t) {
        Position temp = t.position;
        t.position = this.position;
        this.position = temp;
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public abstract void accept(Unit unit);
    public char view(){
        return tile;
    }
    public abstract Tile getCopy();
}
