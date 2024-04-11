import java.util.Scanner;
import PaintBall.*;

public class Main {
    private static final String QUIT = "QUIT";
    private static final String HELP = "HELP";
    private static final String GAME = "GAME";
    private static final String MOVE = "MOVE";
    private static final String ATTACK = "ATTACK";
    private static final String CREATE = "CREATE";
    private static final String STATUS = "STATUS";
    private static final String MAP = "MAP";
    private static final String BUNKERS = "BUNKERS";
    private static final String PLAYERS = "PLAYERS";
    private static final String MOVE_DSC = "move - Move a player.";
    private static final String CREATE_DSC = "create - Create a player in a bunker.";
    private static final String ATTACK_DSC = "attack - Attack with all players of the current team.";
    private static final String STATUS_DSC = "status - List te current state of the game.";
    private static final String MAP_DSC = "map - List the map of the current team.";
    private static final String BUNKERS_DSC = "bunkers - List all bunkers of th current team, by the order they were seized.";
    private static final String PLAYERS_DSC = "players - List all active players of the current team, by the order they were created.";
    private static final String QUIT_DSC = "quit - Exit the application.";
    private static final String HELP_DSC = "help - Display all available commands.";
    private static final String GAME_DSC = "game - Start a new Game.";
    private static final String FATAL_ERROR = "FATAL ERROR: Insufficient number of teams.";
    private static final String TEAM_NOT_CREATED_MSG = "Team not created.";
    private static final String BUNKER_NOT_CREATED_MSG = "Bunker not created.";
    private static final String BYE_MSG = "Bye.";
    private static final String INVALID_COMMAND_MSG = "Invalid command.";


    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        Scanner in = new Scanner(System.in);
        Game game = new GameClass();
        String command;
        do {
            if (!game.getStatus()) {
                System.out.print("> ");
                command = in.next().toUpperCase();
                switch (command) {
                    case GAME -> processGame(in,game);
                    case HELP -> processHelp(game);
                    case QUIT -> processQuit();
                    default -> processInvalid();
                }
            } else {
                System.out.printf("%s> ", game.getCurrentTeam());
                command = in.nextLine().toUpperCase();
                switch(command){
                    case GAME -> processGame(in,game);
                    case MOVE -> processMove();
                    case CREATE -> processCreate();
                    case ATTACK-> processAttack();
                    case STATUS -> processStatus();
                    case MAP -> processMap();
                    case BUNKERS -> processBunker();
                    case PLAYERS -> processPlayers();
                    case HELP -> processHelp(game);
                    case QUIT -> processQuit();
                    default -> processInvalid();
                }
            }
        } while (!command.equals(QUIT)) ;
        in.close();
    }

    private static void processHelp(Game game){
        if (game.getStatus()){
            System.out.println(GAME_DSC);
            System.out.println(MOVE_DSC);
            System.out.println(CREATE_DSC);
            System.out.println(ATTACK_DSC);
            System.out.println(STATUS_DSC);
            System.out.println(MAP_DSC);
            System.out.println(BUNKERS_DSC);
            System.out.println(PLAYERS_DSC);
            System.out.println(HELP_DSC);
            System.out.println(QUIT_DSC);
        } else {
            System.out.println(GAME_DSC);
            System.out.println(HELP_DSC);
            System.out.println(QUIT_DSC);
        }
    }

    private static void processMove(){

    }

    private static void processCreate(){

    }

    private static void processAttack(){

    }

    private static void processStatus(){

    }

    private static void processMap(){

    }

    private static void processBunker(){

    }

    private static void processPlayers(){

    }

    private static void processGame(Scanner in, Game game){
        int width = in.nextInt();
        int height = in.nextInt();
        int teamsNr = in.nextInt();
        int bunkersNr = in.nextInt();
        in.nextLine();

        game.initGame(width, height, teamsNr, bunkersNr);

        readBunkerData(game, in, bunkersNr);
        readTeamData(game, in, teamsNr);
        game.setCurrentTeam();
    }

    private static void readBunkerData(Game game, Scanner in, int bunkersNr){
        System.out.printf("%d bunkers:\n", bunkersNr);

        for (int i = 0; i < bunkersNr; i++){
            int xCoord = in.nextInt();
            int yCoord = in.nextInt();
            int treasury = in.nextInt();
            String bunkerName = in.nextLine().trim();

            if (game.isValidPosition(xCoord-1, yCoord-1) && treasury > 0 && !game.hasBunker(bunkerName)){
                game.addBunker(xCoord-1, yCoord-1, bunkerName, treasury);
            } else {
                System.out.println(BUNKER_NOT_CREATED_MSG);
            }
        }
    }

    private static void readTeamData(Game game, Scanner in, int teamsNr){
        System.out.printf("%d teams:\n", teamsNr);
        for (int i = 0; i < teamsNr; i++){
            String teamName = in.next();
            String bunkerName = in.nextLine().trim();

            if (!game.hasTeam(teamName) && game.hasBunker(bunkerName) && game.isAbandonedBunker(bunkerName)){
                game.addTeam(teamName, bunkerName);
            } else {
                System.out.println(TEAM_NOT_CREATED_MSG);
            }
        }
        if(!game.hasEnoughTeams()) {
        	System.out.println(FATAL_ERROR);
        }
    }

    private static void processQuit(){
        System.out.println(BYE_MSG);
    }

    private static void processInvalid(){
        System.out.println(INVALID_COMMAND_MSG);
    }
}
