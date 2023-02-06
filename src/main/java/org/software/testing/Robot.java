package org.software.testing;

import java.util.*;


public class Robot {

    int floorSize = 10;
    int[] robotPosition = {0,0};
    String direction = "north";
    String pen = "up";
    int[][] floor;
    //Method to Initialize
    public void initializeFloor(int size){
        floorSize = size;
        floor = new int[floorSize][floorSize];
        direction = "north";
        pen = "up";
        robotPosition[0] = 0;
        robotPosition[1]=0;
    }

    //Method to Change Pen to Down
    public int setPenDown(){
        pen = "down";
        return 1;
    };
    //Method to Change Pen to Up
    public int setPenUp(){
        pen = "up";
        return 1;
    };
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
    public int moveForwardPenUp(int spaces){
        //If pen was down --> Replace 1 in the path traced
        switch (direction) {
            case "north":
                robotPosition[1] =  ((floorSize - robotPosition[1]) - spaces - 1)>=0 ? robotPosition[1] + spaces: robotPosition[1];
                break;
            case "south":
                robotPosition[1] =  (robotPosition[1]- spaces - 1)>=0 ? robotPosition[1] - spaces : robotPosition[1];
                break;
            case "east":
                robotPosition[0] = (floorSize - robotPosition[0] - spaces -1 )>=0 ? robotPosition[0] + spaces: robotPosition[0];
                break;
            case "west":
                robotPosition[0] =  (robotPosition[0]- spaces - 1)>=0 ? robotPosition[0] - spaces : robotPosition[0];
                break;
        }
        return 1;
    }
    public int moveForward(int spaces){
        int pos1 = robotPosition[1];
        int pos0 = robotPosition[0];
        switch (direction) {
            case "north":
                robotPosition[1] =  ((floorSize - robotPosition[1]) - spaces - 1)>=0 ? robotPosition[1] + spaces: robotPosition[1];
                if(pen == "down"){
                    for (int i = pos1; i < robotPosition[1]; i++){
                        floor[robotPosition[0]][i] = 1;
                    };
                }
                break;
            case "south":
                robotPosition[1] =  (robotPosition[1]- spaces - 1)>=0 ? robotPosition[1] - spaces : robotPosition[1];
                if(pen == "down"){
                    for (int i = pos1; i < robotPosition[1]; i--){
                        floor[robotPosition[0]][i] = 1;
                    };
                }
                break;
            case "east":
                robotPosition[0] = (floorSize - robotPosition[0] - spaces -1 )>=0 ? robotPosition[0] + spaces: robotPosition[0];
                if(pen == "down"){
                    for (int i = pos0; i < robotPosition[0]; i++){
                        floor[i][robotPosition[1]] = 1;
                    };
                }
                break;
            case "west":
                robotPosition[0] =  (robotPosition[0]- spaces - 1)>=0 ? robotPosition[0] - spaces : robotPosition[0];
                if(pen == "down"){
                    for (int i = pos0; i < robotPosition[0]; i--){
                        floor[i][robotPosition[1]] = 1;
                    };
                }
                break;
        }
        return 10;
    }
    public void displayMatrix(){
        for (int i = floorSize-1; i>=0; i--){
            System.out.print(String.format("%-1s%-1d","",i));
            for (int j = 0; j<floorSize; j++){
                if(floor[j][i] == 1)
                {
                    System.out.print(String.format("%-1s%-1s","","*"));
                }
                else
                {
                    System.out.print(String.format("%-1s%-1s",""," "));
                }
            }
            System.out.println();
        }
        System.out.print(String.format("%-1s",""));
        for(int i=0;i<floorSize;i++){
            System.out.print(String.format("%-1s%-1d","",i));
        }
        System.out.println();
    }
}

