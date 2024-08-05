package model.tiles.units;

public interface HeroicUnit {
    public void castAbility();
    public void attackOtherAbility(Unit target, int damage);
}
