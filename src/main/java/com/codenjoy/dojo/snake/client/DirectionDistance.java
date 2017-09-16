package com.codenjoy.dojo.snake.client;


public class DirectionDistance implements Comparable{
    public String direction;
    public int dtToSnake;
    public int dtToWall;
    public int dtToApple;

    public DirectionDistance(String direction, int dtToSnake, int dtToWall, int dtToApple){
        this.direction = direction;
        this.dtToSnake = dtToSnake;
        this.dtToWall = dtToWall;
        this.dtToApple = dtToApple;
    }

    public int getDtToWall() {
        return dtToWall;
    }

    public void setDtToWall(int dtToWall) {
        this.dtToWall = dtToWall;
    }

    public int getDtToApple() {
        return dtToApple;
    }

    public void setDtToApple(int dtToApple) {
        this.dtToApple = dtToApple;
    }

    @Override
    public int compareTo(Object o) {
        return ((DirectionDistance) o).dtToSnake - dtToSnake;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDtToSnake() {
        return dtToSnake;
    }

    public void setDtToSnake(int dtToSnake) {
        this.dtToSnake = dtToSnake;
    }
}
