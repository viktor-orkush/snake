package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "viktor.orkush@gmail.com";
    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        this.board = board;
        board.toString();
//        Point point = board.getApples().get(0);
//        point.getX()
//        point.getY()
        if (board.isGameOver()) System.exit(0);

        char[][] field = board.getField();
        String result ="";

        // found snake
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();
        Direction snakeDirection = board.getSnakeDirection();
        List<Point> allSnake = board.getSnake();

        // нашли яблуко
        Point cordinatApple = board.getApples().get(0);
        int appleX = cordinatApple.getX();
        int appleY = cordinatApple.getY();
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                char ch = field[x][y];
                if (ch == Elements.GOOD_APPLE.ch()) {
                    appleX = x;
                    appleY = y;
                    break;

                }
            }
            if (appleX != -1) {
                break;
            }
        }

        int dx = snakeHeadX - appleX;
        int dy = snakeHeadY - appleY;

        /*if(dx == 0){
            if (dy < 0) {
                return Direction.DOWN.toString();
            }
            if (dy > 0) {
                return Direction.UP.toString();
            }
        }
        if(dy == 0){
            if (dx < 0) {
                return Direction.RIGHT.toString();
            }
            if (dx > 0) {
                return Direction.LEFT.toString();
            }
        }*/
        //List<Point> a = board.countNear();


        if (snakeDirection.name().equals("UP")) {
            if (dy > 0) {
//                return result = Direction.UP.toString();
                return verifyNextStep("UP");
            }


            if (dx < 0) {
//                return Direction.RIGHT.toString();
                return verifyNextStep("RIGHT");
            }
            if (dx > 0) {
//                return Direction.LEFT.toString();
                return verifyNextStep("LEFT");
            }
        }
        if (snakeDirection.name().equals("DOWN")) {
            if (dy < 0) {
//                return Direction.DOWN.toString();
                return verifyNextStep("DOWN");
            }

            if (dx < 0) {
//                return Direction.RIGHT.toString();
                return verifyNextStep("RIGHT");
            }
            if (dx > 0) {
//                return Direction.LEFT.toString();
                return verifyNextStep("LEFT");
            }
        }

        if (snakeDirection.name().equals("RIGHT")) {
            if (dx < 0) {
//                return Direction.RIGHT.toString();
                return verifyNextStep("RIGHT");
            }
            if (dy < 0) {
                //return Direction.DOWN.toString();
                return verifyNextStep("DOWN");
            }
            if (dy > 0) {
//                return Direction.UP.toString();
                return verifyNextStep("UP");
            }
        }


        if (snakeDirection.name().equals("LEFT")) {
            if (dx > 0) {
//                return Direction.LEFT.toString();
                return verifyNextStep("LEFT");
            }
            if (dy < 0) {
//                return Direction.DOWN.toString();
                return verifyNextStep("DOWN");
            }
            if (dy > 0) {
//                return Direction.UP.toString();
                return verifyNextStep("UP");
            }
        }

        if (snakeDirection.name().equals("UP") || snakeDirection.name().equals("DOWN")) {
            //only left or right
            if (dx == 0) {
                if (snakeHeadX == 1) {
//                    return Direction.RIGHT.toString();
                    return verifyNextStep("RIGHT");
                }
                if (snakeHeadX == board.size() - 1) {
//                    return Direction.LEFT.toString();
                    return verifyNextStep("LEFT");
                }
            }
        }

        if (snakeDirection.name().equals("LEFT") || snakeDirection.name().equals("RIGHT")) {
            //only up or down
            if (dy == 0) {
                if (snakeHeadY == 1) {
//                    return Direction.DOWN.toString();
                    return verifyNextStep("DOWN");
                }
                if (snakeHeadY == board.size() - 1) {
//                    return Direction.UP.toString();
                    return verifyNextStep("DOWN");
                }
                if (snakeHeadY > 1 && snakeHeadY < board.size() - 1) {
//                    return Direction.UP.toString();
                    return verifyNextStep("DOWN");
                }
            }
        }
        return Direction.UP.toString();
    }

    public String verifyNextStep (String result){
        //{"UP", "DOWN", "LEFT", "RIGHT"};
        ArrayList<String> arrDirect = new ArrayList<String>();
        arrDirect.add("UP");
        arrDirect.add("DOWN");
        arrDirect.add("LEFT");
        arrDirect.add("RIGHT");

        List<Point> allSnake = board.getSnake();
        List<Point> badAppale = board.getStones();
        List<Point> walls = board.getWalls();
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();
        String snakeDirection = board.getSnakeDirection().name();

        //arrDirect.remove(snakeDirection);
        //ставим на первое место предпологаемое направление
        arrDirect.remove(result);
        arrDirect.add(0, result);

        for(String direct : arrDirect) {
            int nextSnakeStepX = snakeHeadX;
            int nextSnakeStepY = snakeHeadY;

            result = direct;

            if (result.equals("UP")) {
                nextSnakeStepY = snakeHeadY - 1;
            }
            if (result.equals("DOWN")) {
                nextSnakeStepY = snakeHeadY + 1;
            }
            if (result.equals("LEFT")) {
                nextSnakeStepX = snakeHeadX - 1;
            }
            if (result.equals("RIGHT")) {
                nextSnakeStepX = snakeHeadX + 1;
            }
            Point pointNextSnakeStep= new PointImpl(nextSnakeStepX, nextSnakeStepY);
            //TODO find [nextSnakeStepX, nextSnakeStepY] in allSnake
            if (!allSnake.contains(pointNextSnakeStep) && !walls.contains(pointNextSnakeStep) && !badAppale.contains(pointNextSnakeStep)){
                return Direction.valueOf(result).toString();
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        WebSocketRunner.runOnServer("192.168.1.1:8080", // to use for local server
        WebSocketRunner.run(WebSocketRunner.Host.REMOTE,  // to use for codenjoy.com server
                USER_NAME,
                new YourSolver(new RandomDice()),
                new Board());
    }

}
