package model.tiles;

import model.tiles.units.Unit;

public class Empty extends Tile {
    private static final char EMPTY_TILE = '.';

    public Empty() {
        super(EMPTY_TILE);
    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public Tile getCopy(){
        return new Empty();
    }

}
