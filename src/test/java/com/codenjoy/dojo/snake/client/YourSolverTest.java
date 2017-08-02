package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.RandomDice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr.baglai on 01.12.2015.
 */
public class YourSolverTest {
    @Test
    public void testSameDirection() {
        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ▼  ☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼☼☼☼☼☼", "DOWN");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼► ☺ ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "RIGHT");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼    ☼" +
                "☼ ▲  ☼" +
                "☼☼☼☼☼☼", "UP");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼☺  ◄☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "LEFT");

    }

    @Test
    public void testEatMe() {
/*        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼╔═════════►  ☼" +
                "☼╙            ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼        ☺    ☼" +
                "☼          ☻  ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");


        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼☺            ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼╔╕           ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼▼            ☼" +
                "☼     ☻       ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");*/


        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                            "☼        ◄══╗ ☼" +
                            "☼     ╘═════╝ ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼            ☻☼" +
                            "☼        ☺    ☼" +
                            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");

        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                            "☼        ◄══╗ ☼" +
                            "☼     ╘═════╝ ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼            ☺ ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼             ☼" +
                            "☼            ☻☼" +
                            "☼            ☼" +
                            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");



    }

    @Test
    public void testNotSameDirection() {
        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ►  ☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼☼☼☼☼☼", "DOWN");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼◄ ☺ ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "RIGHT");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼    ☼" +
                "☼ ►  ☼" +
                "☼☼☼☼☼☼", "UP");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼☺  ►☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "LEFT");

    }


    @Test
    public void testSnakeInCircle() {

        /*assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼     ╔══╗    ☼" +
                "☼     ║  ║    ☼" +
                "☼     ║  ║    ☼" +
                "☼     ║  ║    ☼" +
                "☼     ║  ║    ☼" +
                "☼     ║ ╔╝    ☼" +
                "☼     ║◄╝     ☼" +
                "☼     ╚═══╗☻  ☼" +
                "☼      ╘══╝   ☼" +
                "☼        ☺    ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");*/


        /*assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼   ☻    ╔═╕  ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼       ╔╝  ☺ ☼" +
                "☼       ║     ☼" +
                "☼       ║     ☼" +
                "☼       ╚═══╗ ☼" +
                "☼         ╔═╝ ☼" +
                "☼         ╚═╗ ☼" +
                "☼           ▼ ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");*/

        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼             ☼" +
                "☼        ╔═╗  ☼" +
                "☼        ║ ║  ☼" +
                "☼        ║ ║  ☼" +
                "☼        ║ ║  ☼" +
                "☼        ║ ║  ☼" +
                "☼        ║ ║  ☼" +
                "☼        ║ ║  ☼" +
                "☼    ☺   ║◄╝  ☼" +
                "☼        ╙    ☼" +
                "☼             ☼" +
                "☼  ☻          ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");


    }

    @Test
    public void testSnakeMakeBateCirculeDontEatMe() {

        /*assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼     ╔══════►☼\n" +
                "☼     ╚═╗     ☼\n" +
                "☼       ║     ☼\n" +
                "☼     ╔═╝     ☼\n" +
                "☼     ║       ☼\n" +
                "☼     ║   ☻   ☼\n" +
                "☼     ║       ☼\n" +
                "☼     ║       ☼\n" +
                "☼     ║       ☼\n" +
                "☼     ║☺      ☼\n" +
                "☼     ║       ☼\n" +
                "☼     ║ ╔════╕☼\n" +
                "☼     ╚═╝     ☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");*/

        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼     ▲       ☼" +
                "☼     ║       ☼" +
                "☼   ╔═╝      ╓☼" +
                "☼   ║        ║☼" +
                "☼   ║        ║☼" +
                "☼   ║        ║☼" +
                "☼   ║        ║☼" +
                "☼   ║        ║☼" +
                "☼   ║ ☻      ║☼" +
                "☼   ║        ║☼" +
                "☼   ║ ☺      ║☼" +
                "☼   ║        ║☼" +
                "☼   ╚════════╝☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");

assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                    "☼             ☼\n" +
                    "☼             ☼\n" +
                    "☼           ☻ ☼\n" +
                    "☼             ☼\n" +
                    "☼    ╔═╗      ☼\n" +
                    "☼    ║ ║    ╓ ☼\n" +
                    "☼    ║ ║    ║ ☼\n" +
                    "☼    ║ ║    ║ ☼\n" +
                    "☼    ║ ║    ║ ☼\n" +
                    "☼    ║ ║    ║ ☼\n" +
                    "☼    ║ ▼    ║ ☼\n" +
                    "☼    ╚══════╝ ☼\n" +
                    "☼         ☺   ☼\n" +
                    "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");



    }@Test
    public void testSnakeDontEatBadApple() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼  ╔══►☻    ☺ ☼\n" +
                "☼  ║          ☼\n" +
                "☼  ║          ☼\n" +
                "☼  ║          ☼\n" +
                "☼  ╙          ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }



    private void assertB(String boardString, String expected) {
        // given
        YourSolver solver = new YourSolver(new RandomDice());

        // when
        String direction = solver.get((Board)new Board().forString(
                boardString));

        // then
        assertEquals(expected, direction);
    }
}
