package org.software.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
TODO
- moveForward
- displayMatrix

 */
class RobotTest {
    Robot r = new Robot();
    @Test
    void initializeFloorNotNullOrZeroTest() {
        //This should check that all the necessary variables not java default after running function.
        int desiredFloorSize = 20;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;
        int[] robotPositionAfterInitialize = r.robotPosition;
        int[][] floorAfterInitialize = r.floor;

        assertNotEquals(null, penAfterInitialize, "Desired Pen Status");
        assertNotEquals(null,directionAfterInitialize,  "Desired Direction");
        assertNotEquals(0,floorSizeAfterInitialize,  "Desired Floor Size");
    }

    @Test
    void initializeFloorSetToDesiredValuesTest() {
        //This should check that all the necessary variables are as Desired.

        String directionBeforeInitialize = r.direction;
        int floorSizeBeforeInitialize = r.floorSize;
        String penBeforeInitialize = r.pen;
        int[] robotPositionBeforeInitialize = r.robotPosition;
        int[][] floorBeforeInitialize = r.floor;

        int desiredFloorSize = 20;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;
        int[] robotPositionAfterInitialize = r.robotPosition;
        int[][] floorAfterInitialize = r.floor;

        assertEquals("up", penAfterInitialize, "Desired Pen Status");
        assertEquals("north",directionAfterInitialize,  "Desired Direction");
        assertEquals(desiredFloorSize,floorSizeAfterInitialize,  "Desired Floor Size");
        assertEquals(0,robotPositionAfterInitialize[0],  "Robot Position at X axis");
        assertEquals(0,robotPositionAfterInitialize[1],  "Robot Position at Y axis");
    }
    @Test
    void initializeFloorNotOnInvalidValuesTest() {
        //This should check that all the necessary variables should not be set if invalid input.
        int desiredFloorSize = -3;
        boolean res =  r.initializeFloor(desiredFloorSize);
        assertEquals(false, res, "Does not Initialize");

    }

    @Test
    void setPenDownTest() {
        var penD = r.setPenDown();
        assertEquals("down", penD);
        assertNotEquals("up", penD);
    }

    @Test
    void setPenUpTest() {
        var penU = r.setPenUp();
        assertEquals("up", penU);
        assertNotEquals("down", penU);
    }

    @Test
    void turnRightTest() {
        r.initializeFloor(10);
        String dir = r.turnRight();
        assertEquals("east", dir, "Direction After Turning Right");

        r.direction = "east";
        String dir2 = r.turnRight();
        assertEquals("south", dir2, "Direction After Turning Right");

        r.direction = "south";
        String dir3 = r.turnRight();
        assertEquals("west", dir3, "Direction After Turning Right");

        r.direction = "west";
        String dir4 = r.turnRight();
        assertEquals("north", dir4, "Direction After Turning Right");
    }

    @Test
    void turnLeftTest() {
        r.initializeFloor(10);
        String dir = r.turnLeft();
        assertEquals("west", dir, "Direction After Turning Left");

        r.direction = "east";
        String dir2 = r.turnLeft();
        assertEquals("north", dir2, "Direction After Turning Left");

        r.direction = "south";
        String dir3 = r.turnLeft();
        assertEquals("east", dir3, "Direction After Turning Left");

        r.direction = "west";
        String dir4 = r.turnLeft();
        assertEquals("south", dir4, "Direction After Turning Left");
    }

    @Test
    void showCurrentPositionStatusWithoutInitializedTest() {
        //Should display message that program is not initialized
        String res = r.showCurrentPositionStatus();
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            String pos = res.substring(res.indexOf(":") + 1, res.indexOf("-") - 1);
        });

    }
    @Test
    void showCurrentPositionStatusWithCertainCommandsFirstTest() {
        //Should display message that program is not initialized

        //Series of Commands
        r.initializeFloor(20);
        r.moveForward(12);
        r.turnRight();
        r.turnRight();

        String res = r.showCurrentPositionStatus();
        String[] arrOfStr = res.split(":", 4);
        String pos = arrOfStr[1].substring(0,  arrOfStr[1].indexOf("-")-1).trim();
        String pen = arrOfStr[2].substring(0,  arrOfStr[2].indexOf("-")-1).trim();
        String dir = arrOfStr[3].trim();

        assertEquals("up", pen, "Pen Status");
        assertEquals("south", dir, "Direction Facing");
        assertEquals("0, 12", pos, "Robot Position");

    }
    @Test
    void showCurrentPositionStatusWithCertainCommandsSecondTest() {
        //Should display message that program is not initialized

        //Series of Commands
        r.initializeFloor(25);
        r.moveForward(10);
        r.turnRight();
        r.moveForward(4);
        r.turnRight();
        r.turnRight();

        String res = r.showCurrentPositionStatus();
        String[] arrOfStr = res.split(":", 4);
        String pos = arrOfStr[1].substring(0,  arrOfStr[1].indexOf("-")-1).trim();
        String pen = arrOfStr[2].substring(0,  arrOfStr[2].indexOf("-")-1).trim();
        String dir = arrOfStr[3].trim();

        assertEquals("up", pen, "Pen Status");
        assertEquals("west", dir, "Direction Facing");
        assertEquals("4, 10", pos, "Robot Position");

    }
    @Test
    void moveForwardTest() {
        r.moveForward(4);
    }

//    @Test
//    void displayMatrixTest() {
//    }
}