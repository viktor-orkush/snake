package com.codenjoy.dojo.snake.client;


public class DirectionDistance implements Comparable{
    public String direction;
    public int dt;

    public DirectionDistance(String direction, int dt){
        this.direction = direction;
        this.dt = dt;
    }

    @Override
    public int compareTo(Object o) {
        return ((DirectionDistance) o).dt - dt;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }
}
