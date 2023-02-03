package org.software.testing;
import java.util.*;


public class Robot {

    int floorSize = 10;
    int[] robotPosition = {0,0};
    int size;
    String direction = "north";
    String pen = "down";
    int[][] floor;
    //Method to Initialize
    public void initializeFloor(int size){
        this.size = size;
        floor = new int[size][size];
    }
    //Method to Turn Right
    public int turnRight(){
        switch (direction) {
            case "north":
                direction = "east";
                break;
            case "south":
                direction = "west";
                break;
            case "east":
                direction = "south";
                break;
            case "west":
                direction = "north";
                break;
        }
        return 0;
    };
    //Method to Turn Left
    public int turnLeft(){
        switch (direction) {
            case "north":
                direction = "west";
                break;
            case "south":
                direction = "east";
                break;
            case "east":
                direction = "north";
                break;
            case "west":
                direction = "south";
                break;
        }
        return 0;
    };
    //Method to display current position
    public String showCurrentPositionStatus(){
        String position = "Position: " + Integer.toString(robotPosition[0]) + ", " + Integer.toString(robotPosition[1]);
        String penStatus = "Pen: " + pen;
        String face = "Facing: " + direction;

        return position + " - " + penStatus + " - " + face;
    }
    public int moveForward(int spaces){
        int availableSpaces = 0;
        switch (direction) {
            case "north":
                availableSpaces = floorSize - robotPosition[1];
                break;
            case "south":
                availableSpaces =  robotPosition[1];
                break;
            case "east":
                availableSpaces =  floorSize - robotPosition[0];
                break;
            case "west":
                availableSpaces =  robotPosition[0];
                break;
        }
        if(availableSpaces - spaces-1 >=0){
            switch (direction) {
                case "north":
                    robotPosition[1] += spaces;
                    break;
                case "south":
                    robotPosition[1] -= spaces;
                    break;
                case "east":
                    robotPosition[0] += spaces;
                    break;
                case "west":
                    robotPosition[0] -= spaces;
                    break;
            }
        }else{
            return 0;
        }
        //Check Direction
        //Get length remaining in that direction
        //if pen down then modify array
        //otherwise modify location
        //if possible then move

        //Check if that spaces are possible
        if(robotPosition[0] + spaces < floorSize){

        }
        //Move those spaces
        //Check if pen is up or down
        //If pen down do 1 in array
        //Modify the final position
        return 1;
    }
    public void displayMatrix(){
        for (int i = 0; i<size; i++){
            System.out.print(String.format("%-5s%-5d","",size-i-1));
            for (int j = 0; j<size; j++){
                System.out.print(String.format("%-5s%-5d","",floor[i][j]));
            }
            System.out.println();
        }
        System.out.print(String.format("%-10s",""));
        for(int i=0;i<size;i++){
            System.out.print(String.format("%-5s%-5d","",i));
        }
        System.out.println();
    }
}