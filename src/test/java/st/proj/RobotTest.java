package st.proj;

import static com.google.common.base.Objects.equal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

class RobotTest {

    Robot r = new Robot();
    History h = new History();

    @Test
    void initializeFloorNotNullOrZeroTest() {
        //This should check that all the necessary variables not java default after running function.
        int desiredFloorSize = 20;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;

        assertNotEquals(null, penAfterInitialize, "Desired Pen Status");
        assertNotEquals(null,directionAfterInitialize,  "Desired Direction");
        assertNotEquals(0,floorSizeAfterInitialize,  "Desired Floor Size");
    }

    @Test
    void initializeFloorSetToDesiredValuesTest() {
        //This should check that all the necessary variables are as Desired.
        int desiredFloorSize = 20;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;
        int[] robotPositionAfterInitialize = r.robotPosition;

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
        assertEquals(r.turnRight(),"0");
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
        assertEquals(r.turnLeft(),"0");
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
    void testMoveForward() {
        assertFalse(r.moveForward(10));
        r.initializeFloor(10);
        r.moveForward(4);
        r.setPenDown();
        assertEquals("Position: 0, 4 - Pen: down - Facing: north",r.showCurrentPositionStatus());
        r.turnRight();
        r.moveForward(2);
        assertEquals("Position: 2, 4 - Pen: down - Facing: east",r.showCurrentPositionStatus());
        r.turnRight();
        r.moveForward(2);
        assertEquals("Position: 2, 2 - Pen: down - Facing: south",r.showCurrentPositionStatus());
        r.turnRight();
        r.moveForward(2);
        assertEquals("Position: 2, 2 - Pen: down - Facing: west",r.showCurrentPositionStatus());
    }

    @Test
    void testCommandFormat(){
        int valid = r.splitArray("I 4");
        int invalidInvalidInteger = r.splitArray("I 4.5");
        int invalidCommandFormat = r.splitArray("i 10 10");
        assertEquals(valid,4);
        valid = r.splitArray("I09");
        assertEquals(valid,9);
        assertEquals(invalidInvalidInteger,-1);
        assertEquals(invalidCommandFormat,-1);


    }
    @Test
    void testNoMovementPossible() {
        r.initializeFloor(10);
        String before = r.showCurrentPositionStatus();
        r.turnLeft();
        r.moveForward(4);
        String after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(4);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(11);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(11);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
    }

//    @Test
//    void testCallingCommand(){
//        String input = "i\ni 10\nm\nm4\nc \nr \nl \np \nu \nd \ns \nq";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        r.runRobot(in);
//        input = "\n\nq";
//        in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        r.runRobot();
//    }

    @Test
    void testDisplayMatrix(){
        r.displayMatrix();
        r.initializeFloor(3);
        r.setPenDown();
        r.moveForward(1);
        r.displayMatrix();
        int[][] floor = {{1,1,0},{0,0,0},{0,0,0}};
        int [][] rFloor = r.getFloor();
        assertTrue(Arrays.deepEquals(r.getFloor(),floor));
    }
@Test
    void testHistoryFunctionality(){
    String[] commands = {"i 10", "m 4", "d", "r", "m 2", "l", "u", "c", "p"};
    String[] expectedCommands = {"i 10", "m 4", "d", "r", "m 2", "l", "u", "c", "p","i 10", "m 4", "d", "r", "m 2", "l", "u", "c", "p"};

    //Run the Robot
    for (int i =0; i<= commands.length-1 ; i++){
        r.runRobot(commands[i]);
    }
    r.runRobot("h");
    assertEquals(Arrays.toString(expectedCommands),r.getHistory(), "Unexpected History Behaviour" );
}

}