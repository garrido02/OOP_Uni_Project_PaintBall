import ShapesApp.*;

import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static final String EXIT = "EXIT";
    private static final String ADD = "ADD";
    private static final String LIST = "LIST";
    private static final String MOVE = "MOVE";
    private static final String MINAREA = "MINAREA";
    private static final String CIRCLE = "CIRCLE";
    private static final String RECTANGLE = "RECTANGLE";
    private static final String QUIT = "EXIT";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        interpreter(in);
    }

    private static void interpreter(Scanner in) {
        String command;
        ShapesApp app = new ShapesAppClass();
        do {
            command = in.next().toUpperCase();
            switch (command) {
                case ADD -> processAdd(in, app);
                case LIST -> processList(in, app);
                case MOVE -> processMove(in, app);
                case MINAREA -> processMinArea(in, app);
                case QUIT -> processQuit();
            }
        } while (!command.equals(QUIT));
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
            System.out.printf("A new %s was added.\n", shape);
            in.nextLine();
        }
    }

    private static void processList(Scanner in, ShapesApp app){
        StringTokenizer token = new StringTokenizer(in.nextLine());
        if (app.isEmpty()) {
            System.out.println("Without geometric shapes.");
        } else if (token.hasMoreTokens()){
            String shape = token.nextToken().toUpperCase();
            if (!app.isValidType(shape)){
                System.out.println("Type does not exist.");
            } else {
                Iterator ite = app.allShapesIterator(shape);
                System.out.println("All shapes:");
                while(ite.hasNext()){
                    Shape s = ite.next();
                    System.out.printf("%s (%d, %d) %s\n", s.getShapeId(), s.getX(), s.getY(), s.getType());
                }
            }
        } else {
            Iterator ite = app.allShapesIterator();
            System.out.println("All shapes:");
            while(ite.hasNext()){
                Shape s = ite.next();
                System.out.printf("%s (%d, %d) %s\n", s.getShapeId(), s.getX(), s.getY(), s.getType());
            }
        }
    }

    private static void processMove(Scanner in, ShapesApp app){
        String shapeId = in.next();
        if (!app.hasShape(shapeId)){
            System.out.println("Identifier does not exist.");
        } else {
            app.move(shapeId, in.nextInt(), in.nextInt());
            System.out.println("Shape was moved.");
        }
    }

    private static void processMinArea(Scanner in, ShapesApp app){
        if(app.isEmpty()){
            System.out.println("Without geometric shapes.");
        } else {
            Shape s = app.smallestArea();
            System.out.printf("%s (%d, %d) %s\n", s.getShapeId(), s.getX(), s.getY(), s.getType());
        }
    }

    private static void processQuit(){
        System.out.println("Exiting...");
    }
}