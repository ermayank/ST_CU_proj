package st.proj;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Robot r = new Robot();
        System.out.println("Welcome to the Robot Program.");
        Scanner sc = new Scanner(System.in);
        String command;

        do{
            System.out.print("Enter command : ");
            command = sc.nextLine();
            try{
                char c = command.toLowerCase().charAt(0);
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Please provide command in specified format displayed above");
                continue;
            }
            r.runRobot(command);
        }
        while(!command.toLowerCase().equals("q"));
//
    }
}
//