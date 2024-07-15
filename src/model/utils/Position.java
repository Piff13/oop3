package model.utils;

import java.util.Objects;

public class Position implements Comparable<Position>{
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double range(Position other){
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return  y;
    }
    @Override
    public int compareTo(Position other) {
        if (this.x != other.x) {
            return Integer.compare(this.x, other.x);
        } else {
            return Integer.compare(this.y, other.y);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
