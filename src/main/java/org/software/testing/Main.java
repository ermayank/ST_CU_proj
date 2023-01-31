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
        System.out.println("Hello to this robot vala program : ");
        boolean initFlag = false;
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.print("Enter command : ");
            command = sc.nextLine();
            char c = command.charAt(0);

            switch (c) {
                case 'i':
                    int num = splitArray(command);
                    r.initializeFloor(num);
                    break;
                case 'm':
                    int spaces = splitArray(command);
                    r.moveForward(spaces);
                    break;
                case 'c':
                    System.out.println(r.showCurrentPositionStatus());
                    break;
                case 'r':
                    r.turnRight();
                    break;
                case 'l':
                    r.turnLeft();
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