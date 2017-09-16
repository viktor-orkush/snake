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
import java.util.Arrays;
import java.util.List;

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
        String result = "";

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
//        return Direction.UP.toString();
        return verifyNextStep(snakeDirection.name());
    }

    public String directionReverseToCurent(String direction) {
        if (direction.equals("UP")) return "DOWN";
        if (direction.equals("DOWN")) return "UP";
        if (direction.equals("LEFT")) return "RIGHT";
        if (direction.equals("RIGHT")) return "LEFT";
        return "";
    }

    public void removeNotAllowedDirection(ArrayList<String> arrDirect) {
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();


        String directionWithBadApple = "";

        List<Point> allSnake = board.getSnake();
        List<Point> badAppale = board.getStones();
        List<Point> walls = board.getWalls();

        String[] delDirections = new String[3];
        int indexDelDirection = 0;

        for (String typeDirect : arrDirect) {
            int nextSnakeStepX = snakeHeadX;
            int nextSnakeStepY = snakeHeadY;
            if (typeDirect.equals("UP")) {
                nextSnakeStepY = snakeHeadY - 1;
            }
            if (typeDirect.equals("DOWN")) {
                nextSnakeStepY = snakeHeadY + 1;
            }
            if (typeDirect.equals("LEFT")) {
                nextSnakeStepX = snakeHeadX - 1;
            }
            if (typeDirect.equals("RIGHT")) {
                nextSnakeStepX = snakeHeadX + 1;
            }
            Point pointNextSnakeStep = new PointImpl(nextSnakeStepX, nextSnakeStepY);
            Point pointSnakeHead = new PointImpl(snakeHeadX, snakeHeadY);

            //если впереди припяцтвие
            if (allSnake.contains(pointNextSnakeStep) || walls.contains(pointNextSnakeStep)) {
                //надо удалить направление
                delDirections[indexDelDirection++] = typeDirect;
                //arrDirect.remove(typeDirect);
            }

            //если впереди яблоко надо удалить и запомнить направление
            if (badAppale.contains(pointNextSnakeStep)) {
                //надо удалить направление
                delDirections[indexDelDirection++] = typeDirect;
                //arrDirect.remove(typeDirect);
                directionWithBadApple = typeDirect;
            }
        }
        for (String delDirec : delDirections) {
            arrDirect.remove(delDirec);
        }
        if (arrDirect.isEmpty() && !directionWithBadApple.equals("")) {
            arrDirect.add(directionWithBadApple);
        }
    }

    public String bestWay(ArrayList<String> arrDirection) {
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();

        List<Point> allSnake = board.getSnake();
        DirectionDistance[] arrDtDistance = new DirectionDistance[arrDirection.size()];
        int index = 0;

        if (arrDirection.size() == 1) return arrDirection.get(0);

        for (String direction : arrDirection) {
            arrDtDistance[index++] = new DirectionDistance(direction, 0,0,0);
        }

        for (String direction : arrDirection) {
            //смотрим совпадения по Х
            if (direction.equals("DOWN")) {
                int dtDistance = 0;
                for (Point partSnake : allSnake) {
                    if (snakeHeadX == partSnake.getX() && snakeHeadY < partSnake.getY()) {
                        int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("DOWN");
                        if (idElem == -1) continue;
                        dtDistance = partSnake.getY() - snakeHeadY;
                        arrDtDistance[idElem].setDtToSnake(dtDistance);
                    }

                }
                dtDistance = new DirectionDistanceManager(arrDtDistance).getDtByDirection("DOWN");
                if (dtDistance == 0) {
                    int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("DOWN");
                    if (idElem == -1) continue;
                    dtDistance = board.size() - snakeHeadY + 1;
                    arrDtDistance[idElem].setDtToSnake(dtDistance);
                }

            }
            if (direction.equals("UP")) {
                int dtDistance = 0;
                for (Point partSnake : allSnake) {
                    if (snakeHeadX == partSnake.getX() && snakeHeadY > partSnake.getY()) {
                        int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("UP");
                        if (idElem == -1) continue;
                        dtDistance = snakeHeadY - partSnake.getY();
                        arrDtDistance[idElem].setDtToSnake(dtDistance);
                    }
                }
                dtDistance = new DirectionDistanceManager(arrDtDistance).getDtByDirection("UP");
                if (dtDistance == 0) {
                    int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("UP");
                    if (idElem == -1) continue;
                    dtDistance = snakeHeadY + 1;
                    arrDtDistance[idElem].setDtToSnake(dtDistance);
                }
            }
            //смотрим совпадения по Y
            if (direction.equals("RIGHT")) {
                int dtDistance = 0;
                for (Point partSnake : allSnake) {
                    if (snakeHeadY == partSnake.getY() && snakeHeadX < partSnake.getX()) {
                        int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("RIGHT");
                        if (idElem == -1) continue;
                        dtDistance = partSnake.getX() - snakeHeadX;
                        arrDtDistance[idElem].setDtToSnake(dtDistance);
                    }
                }
                dtDistance = new DirectionDistanceManager(arrDtDistance).getDtByDirection("RIGHT");
                if (dtDistance == 0) {
                    int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("RIGHT");
                    if (idElem == -1) continue;
                    dtDistance = board.size() - snakeHeadX + 1;
                    arrDtDistance[idElem].setDtToSnake(dtDistance);
                }
            }
            if (direction.equals("LEFT")) {
                int dtDistance = 0;
                for (Point partSnake : allSnake) {
                    if (snakeHeadY == partSnake.getY() && snakeHeadX > partSnake.getX()) {
                        int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("LEFT");
                        if (idElem == -1) continue;
                        dtDistance = snakeHeadX - partSnake.getX();
                        arrDtDistance[idElem].setDtToSnake(dtDistance);
                    }
                }
                dtDistance = new DirectionDistanceManager(arrDtDistance).getDtByDirection("LEFT");
                if (dtDistance == 0) {
                    int idElem = new DirectionDistanceManager(arrDtDistance).getIdElementByDirection("LEFT");
                    if (idElem == -1) continue;
                    dtDistance = snakeHeadX + 1;
                    arrDtDistance[idElem].setDtToSnake(dtDistance);
                }
            }
        }
        if (arrDtDistance.length != 0) Arrays.sort(arrDtDistance);

        return arrDtDistance[0].getDirection();
    }

    public String verifyNextStep(String result) {

        //{"UP", "DOWN", "LEFT", "RIGHT"};
        ArrayList<String> arrDirect = new ArrayList<String>();
        writeArrAllDirection(arrDirect);

        //удаляем обратное направление
        String snakeReverseDirection = directionReverseToCurent(board.getSnakeDirection().name());
        arrDirect.remove(snakeReverseDirection);
        //ставим на первое место предпологаемое направление
        arrDirect.remove(result);
        arrDirect.add(0, result);
        //удаляем все пути где есть столкновения и плохое яблоко
        removeNotAllowedDirection(arrDirect);

        List<Point> allSnake = board.getSnake();
        List<Point> badAppale = board.getStones();
        List<Point> walls = board.getWalls();
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();


        String lastChoise = "";
        String directionEatBadApple = "";


        int nextSnakeStepX = snakeHeadX;
        int nextSnakeStepY = snakeHeadY;

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
        Point pointNextSnakeStep = new PointImpl(nextSnakeStepX, nextSnakeStepY);
        Point pointSnakeHead = new PointImpl(snakeHeadX, snakeHeadY);

        //следуйщий шаг будет буз столкноверния
        if (!allSnake.contains(pointNextSnakeStep) && !walls.contains(pointNextSnakeStep) && !badAppale.contains(pointNextSnakeStep)) {
            //*************************************************************************************************
            String batterWay = verifyFuterMovement(result);
            //*************************************************************************************************

            return Direction.valueOf(batterWay).toString();
        }

        //если впереди припяцтвие выбираем лучший вариант
        if (allSnake.contains(pointNextSnakeStep) || walls.contains(pointNextSnakeStep) || badAppale.contains(pointNextSnakeStep)) {
            String bestWay = bestWay(arrDirect);
            return bestWay;
        }
        return result;
    }

    private String verifyFuterMovement(String result) {
        /*int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();

        Point cordinatApple = board.getApples().get(0);
        int appleX = cordinatApple.getX();
        int appleY = cordinatApple.getY();

        List<Point> allSnake = board.getSnake();*/

        ArrayList<String> arrDirection = takeAlloweDirection(result);

        DirectionDistance[] arrDeltaDistance = new DirectionDistance[arrDirection.size()];
        int index = 0;

        if (arrDirection.size() == 1) return arrDirection.get(0);

        for (String direction : arrDirection) {
            arrDeltaDistance[index++] = getInformOfDirection(direction);
        }

        for (DirectionDistance directionDistance : arrDeltaDistance){

        }
        //если иду к цели безпрепятствий
//        если есть припяцтвие но оно за яблоком
        //если припяцтвие перед яблоком надо обойти даный маршрут отпадает
        //провиряем оставшие два направления
            //надо выбрать лучшее
            //1) то что без змейки на пути

        return result;
    }


    public static void main(String[] args) {
//        WebSocketRunner.runOnServer("192.168.1.1:8080", // to use for local server
        WebSocketRunner.run(WebSocketRunner.Host.REMOTE,  // to use for codenjoy.com server
                USER_NAME,
                new YourSolver(new RandomDice()),
                new Board());
    }


//*************************************************************************************************************************
//
    public DirectionDistance getInformOfDirection (String direct) {
        int snakeHeadX = board.getHead().getX();
        int snakeHeadY = board.getHead().getY();

        Point cordinatApple = board.getApples().get(0);
        int appleX = cordinatApple.getX();
        int appleY = cordinatApple.getY();

        int dtToGoodApple = 0;
        int dtToWall = 0;
        int dtToSnake = 0;

        List<Point> allSnake = board.getSnake();

        DirectionDistance directionDistance = new DirectionDistance(direct, 0,0,0);

        //проверяем возможное совпадение в будущем пробуем не зациклить ЗМЕЙКУ
        if (direct.equals("DOWN")) {
            for (Point partSnake : allSnake) {
                //на пути есть часть змейки
                if (snakeHeadX == partSnake.getX() && snakeHeadY < partSnake.getY()) {
                    dtToSnake = snakeHeadY - partSnake.getY();
                    directionDistance.setDtToSnake(dtToSnake);
                }
            }
            dtToGoodApple = appleY - snakeHeadY;
            directionDistance.setDtToApple(dtToGoodApple);
            dtToWall= board.size() - snakeHeadY;
            directionDistance.setDtToWall(dtToWall);
            return directionDistance;
        }

        if (direct.equals("UP")) {
            for (Point partSnake : allSnake) {
                //на пути есть часть змейки
                if (snakeHeadX == partSnake.getX() && snakeHeadY > partSnake.getY()) {
                    dtToSnake = snakeHeadY - partSnake.getY();
                    directionDistance.setDtToSnake(dtToSnake);
                }
            }
            dtToGoodApple = snakeHeadY - appleY;
            directionDistance.setDtToApple(dtToGoodApple);
            dtToWall= snakeHeadY;
            directionDistance.setDtToWall(dtToWall);
            return directionDistance;
        }

        //смотрим совпадения по Y
        if (direct.equals("RIGHT")) {
            for (Point partSnake : allSnake) {
                //на пути есть часть змейки
                if (snakeHeadY == partSnake.getY() && snakeHeadX < partSnake.getX()) {
                    dtToSnake = partSnake.getX() - snakeHeadX;
                    directionDistance.setDtToSnake(dtToSnake);
                }
            }
            dtToGoodApple = appleX - snakeHeadX;
            directionDistance.setDtToApple(dtToGoodApple);
            dtToWall= board.size() - snakeHeadX;
            directionDistance.setDtToWall(dtToWall);
            return directionDistance;
        }

        if (direct.equals("LEFT")) {
            for (Point partSnake : allSnake) {
                //на пути есть часть змейки
                if (snakeHeadY == partSnake.getY() && snakeHeadX > partSnake.getX()) {
                    dtToSnake = snakeHeadX - partSnake.getX();
                    directionDistance.setDtToSnake(dtToSnake);
                }
            }
            dtToGoodApple = snakeHeadX - appleX;
            directionDistance.setDtToApple(dtToGoodApple);
            dtToWall= snakeHeadX;
            directionDistance.setDtToWall(dtToWall);
            return directionDistance;
        }
        return null;
    }



    private ArrayList<String> takeAlloweDirection(String result) {
        //{"UP", "DOWN", "LEFT", "RIGHT"};
        ArrayList<String> arrDirect = new ArrayList<String>();
        writeArrAllDirection(arrDirect);

        //удаляем обратное направление
        String snakeReverseDirection = directionReverseToCurent(board.getSnakeDirection().name());
        arrDirect.remove(snakeReverseDirection);

        //ставим на первое место предпологаемое направление
        arrDirect.remove(result);
        arrDirect.add(0, result);

        //удаляем все пути где есть столкновения и плохое яблоко
        removeNotAllowedDirection(arrDirect);
        return arrDirect;
    }


    public void writeArrAllDirection(ArrayList<String> arrDirect) {
        arrDirect.add("UP");
        arrDirect.add("DOWN");
        arrDirect.add("LEFT");
        arrDirect.add("RIGHT");
    }

}
