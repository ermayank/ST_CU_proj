package org.software.testing;

import java.util.Scanner;


public class Main {
    public static int splitArray(String cmd){
        String commandArray="";
        if(cmd.length()>1)
        {
            if(cmd.contains(" ")){
                if(cmd.split(" ").length>2){
                    System.out.println("Please provide command in specified format displayed above");
                    return -1;
                }
                else{
                    commandArray= cmd.split(" ")[1];
                }

            }
            else {
                commandArray = cmd.substring(1);
            }
        }
        int s = 0;
        try{
            s = Integer.parseInt(commandArray);
        }
        catch(NumberFormatException e){
            System.out.println(("Enter a valid integer"));
            return -1;
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please provide command in specified format displayed above");
            return -1;
        }
        return s;
    };
    public static void main(String[] args) {
        Robot r = new Robot();
        char c;
        int result;
        System.out.println("Welcome to the Robot Program. The following Commands are Available : \n ");
        System.out.print("i x : Initialize the Program \np : Display the Floor \nu: Pen Up \nd: Pen Down \nr: Turn Right \nl : Turn Left \nm x : Move Forward in that Direction \nc: Print Current Position\n");
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.print("Enter command : ");
            command = sc.nextLine();
            try{
                c = command.toLowerCase().charAt(0);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println("Please provide command in specified format displayed above");
                continue;
            }

            result = -1;
            switch (c) {
                //Initialise
                case 'i':
                    result = splitArray(command);
                    if (result != -1) {
                        r.initializeFloor(splitArray(command));
                    }
                    break;
                // Move eg m 4
                case 'm':
                    result = splitArray(command);
                    if (result != -1) {
                        r.moveForward(result);
                    }
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
                // Pen Up command
                case 'u':
                    r.setPenUp();
                    break;
                // Pen Down command
                case 'd':
                    r.setPenDown();
                    break;
                // Invalid command
                default:
                    System.out.println("Enter valid command");
                    break;
            }

        }
        while(!command.toLowerCase().equals("q"));
    }
}