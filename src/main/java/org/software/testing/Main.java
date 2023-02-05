package org.software.testing;

import java.util.Scanner;

public class Main {
    public static int splitArray(String cmd){
        String[] commandArray= cmd.split(" ");
        int s = Integer.parseInt(commandArray[1]);
        return s;
    };
    public static void main(String[] args) {
        Robot r = new Robot();
        System.out.println("Welcome to the Robot Program. The following Commands are Available : \n ");
        System.out.print("i x : Initialize the Program \np : Display the Floor \nu: Pen Up \nd: Pen Down \nr: Turn Right \nl : Turn Left \nm x : Move Forward in that Direction \nc: Print Current Position\n");
        boolean initFlag = false;
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.print("Enter command : ");
            command = sc.nextLine();
            char c = command.toLowerCase().charAt(0);

            switch (c) {
                //Initialise
                case 'i':
                    int num = splitArray(command);
                    r.initializeFloor(num);
                    break;
                // Move eg m 4
                case 'm':
                    int spaces = splitArray(command);
                    r.moveForward(spaces);
                    break;
                //Print Current Position
                case 'c':
                    System.out.println(r.showCurrentPositionStatus());
                    break;
                //Turn Right
                case 'r':
                    r.turnRight();
                    break;
                //Turn Left
                case 'l':
                    r.turnLeft();
                    break;
                //Display Matrix
                case 'p':
                    r.displayMatrix();
                    break;
                case 'u':
                    r.setPenUp();
                    break;
                case 'd':
                    r.setPenDown();
                    break;
                default:
                    System.out.println("Enter valid command");
                    break;

            }

        }
        while(!command.toLowerCase().equals("q"));

        int m =  r.moveForward(10);
        System.out.println(m);

        String l = r.showCurrentPositionStatus();
        System.out.println(l);

    }
}
//0 Failed
//1 Success