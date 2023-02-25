package st.proj;


import java.util.Scanner;
import java.util.SortedMap;

public class Robot {

    int floorSize;
    int[] robotPosition;
    String direction;
    String pen;
    int[][] floor;
    int initialized = 0;
    public Robot(){

    }

    public int splitArray(String cmd){
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
        return s;
    };

    public void runRobot(){
        char c;
        int result;

        System.out.print("i x : Initialize the Program \np : Display the Floor \nu: Pen Up \nd: Pen Down \nr: Turn Right \nl : Turn Left \nm x : Move Forward in that Direction \nc: Print Current Position\n");
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.print("Enter command : ");
            command = sc.nextLine();
            try{
                c = command.toLowerCase().charAt(0);
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Please provide command in specified format displayed above");
                continue;
            }
            switch (c) {
                //Initialise
                case 'i':
                    result = splitArray(command);
                    if (result != -1) {
                        this.initializeFloor(splitArray(command));
                    }
                    break;
                // Move eg m 4
                case 'm':
                    result = splitArray(command);
                    if (result != -1) {
                        this.moveForward(result);
                    }
                    break;
                //Print Current Position
                case 'c':
                    System.out.println(this.showCurrentPositionStatus());
                    break;
                //Turn Right
                case 'r':
                    this.turnRight();
                    break;
                //Turn Left
                case 'l':
                    this.turnLeft();
                    break;
                //Display Matrix
                case 'p':
                    this.displayMatrix();
                    break;
                // Pen Up command
                case 'u':
                    this.setPenUp();
                    break;
                // Pen Down command
                case 'd':
                    this.setPenDown();
                    break;
                case 'q':
                    System.out.println("Program ends");
                    break;
                // Invalid command
                default:
                    System.out.println("Enter valid command");
                    break;
            }

        }
        while(!command.toLowerCase().equals("q"));
    }
    //Method to Initialize
    public boolean initializeFloor(int size){
        if(size>0)
        {
            floorSize = size;
            floor = new int[floorSize][floorSize];
            direction = "north";
            pen = "up";
            initialized = 1;
            robotPosition = new int[]{0, 0};
            return true;
        }
        else{
            System.out.println("Size of array cannot be negative or zero");
            return false;
        }
    }

    //Method to Change Pen to Down
    public String setPenDown(){
        pen = "down";
        return pen;
    };
    //Method to Change Pen to Up
    public String setPenUp(){
        pen = "up";
        return pen;
    };


    //Method to Turn Right
    public String turnRight(){
        if(initialized==0){
            System.out.println("Please initialize the array first");
            return "0";
        }
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
        return direction;
    };
    //Method to Turn Left
    public String turnLeft(){
        if(initialized==0){
            System.out.println("Please initialize the array first");
            return "0";
        }
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
        return direction;
    };
    //Method to display current position
    public String showCurrentPositionStatus(){
        if(initialized==0){
//            System.out.print("");
            return "Please initialize the program first";
        }
        String position = "Position: " + robotPosition[0] + ", " + robotPosition[1];
        String penStatus = "Pen: " + pen;
        String face = "Facing: " + direction;
        return position + " - " + penStatus + " - " + face;

    }
    //Method to make the Robot move
    public boolean moveForward(int spaces){
        if(initialized==0){
            System.out.println("Please initialize the array first");
            return false;
        }
        int pos1 = robotPosition[1];
        int pos0 = robotPosition[0];
        //Check the direction in which robot is facing
        //Then check if desired motion is not exceeding available space, then move, else display error message
        switch (direction) {
            case "north":
                robotPosition[1] =  ((floorSize - robotPosition[1]) - spaces - 1)>=0 ? robotPosition[1] + spaces: robotPosition[1];
                if(pos1 == robotPosition[1])
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){
                    for (int i = pos1; i <= robotPosition[1]; i++){
                        floor[robotPosition[0]][i] = 1;
                    }
                }
                break;
            case "south":
                robotPosition[1] =  (robotPosition[1]- spaces - 1)>=0 ? robotPosition[1] - spaces : robotPosition[1];
                if(pos1 == robotPosition[1])
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){
                    for (int i = pos1; i >=robotPosition[1]; i--){
                        floor[robotPosition[0]][i] = 1;
                    }
                }
                break;
            case "east":
                robotPosition[0] = (floorSize - robotPosition[0] - spaces -1 )>=0 ? robotPosition[0] + spaces: robotPosition[0];
                if(pos0 == robotPosition[0])
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){
                    for (int i = pos0; i <= robotPosition[0]; i++){
                        floor[i][robotPosition[1]] = 1;
                    };
                }
                break;
            case "west":
                robotPosition[0] =  (robotPosition[0]- spaces - 1)>=0 ? robotPosition[0] - spaces : robotPosition[0];
                if(pos0 == robotPosition[0])
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){
                    for (int i = pos0; i >=robotPosition[0]; i--){
                        floor[i][robotPosition[1]] = 1;
                    };
                }
                break;
        }
        return true;
    }
    //Method to print Robot traversed path
    public void displayMatrix(){
        if(initialized==0){
            System.out.println("Please initialize the array first");
            return;
        }
        for (int i = floorSize-1; i>=0; i--){
            //Format the output, so we have equal spaces everywhere
            //Print the indices for y-axis
            System.out.print(String.format("%-3s%-3d","",i));
            for (int j = 0; j<floorSize; j++){
                if(floor[j][i] == 1)
                {
                    //If the array value is 1, print it as "*"
                    System.out.print(String.format("%-3s%-3s","","*"));
                }
                else
                {
                    //If the array value is 0, print it as " "
                    System.out.print(String.format("%-3s%-3s",""," "));
                }
            }
            System.out.println();
        }
        //Print the indices for x-axis
        System.out.print(String.format("%-6s",""));
        for(int i=0;i<floorSize;i++){
            System.out.print(String.format("%-2s%-2d","",i));
        }
        System.out.println();
    }
}

