import ShapesApp.*;
import java.util.Scanner;

public class Main {
    private static final String EXIT = "EXIT";
    private static final String ADD = "ADD";
    private static final String LIST = "LIST";
    private static final String MOVE = "MOVE";
    private static final String MINAREA = "MINAREA";
    private static final String CIRCLE = "CIRCLE";
    private static final String RECTANGLE = "RECTANGLE";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        interpreter(in);
    }

    private static void interpreter(Scanner in) {
        String command;
        do {
            command = in.next().toUpperCase();
            ShapesApp app = new ShapesAppClass();
            switch (command) {
                case ADD -> processAdd(in, app);
                case LIST -> processList(in, app);
                case MOVE -> processMove(in, app);
                case MINAREA -> processMinArea(in, app);
            }
        } while (!command.equals(EXIT));
        in.close();
    }

    private static void processAdd(Scanner in, ShapesApp app){
        String shape = in.next().toUpperCase();
        in.nextLine();
        String shapeId = in.next();

        if (!app.isValidType(shape)){
            System.out.println("Type does not exist.");
        } else if (app.hasShape(shapeId)){
            System.out.println("Identifier already exists.");
        } else {
            switch(shape){
                case CIRCLE -> app.addCircle(shapeId, in.nextInt(), in.nextInt(), in.nextInt());
                case RECTANGLE -> app.addRectangle(shapeId, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }
            System.out.printf("A new %s was added\n", shape);
            in.nextLine();
        }
    }

    private static void processList(Scanner in, ShapesApp app){

    }

    private static void processMove(Scanner in, ShapesApp app){

    }

    private static void processMinArea(Scanner in, ShapesApp app){

    }
}