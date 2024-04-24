/**
 * @author Francisco Correia & Sérgio Garrido
 */


import java.util.Scanner;
import PaintBall.*;
import DataStructures.*;


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
	private static final String NON_EXISTING_BUNKER_MSG = "Non-existent bunker.";
	private static final String NON_EXISTING_PLAYERTYPE_MSG = "Non-existent player type.";
	private static final String ILLEGALLY_BUNKER_MSG = "Bunker illegally invaded.";
	private static final String BUNKER_NO_FREE_MSG = "Bunker not free.";
	private static final String PLAYER_CREATED_MSG = "%s player created in %s\n";
	private static final String INSUFFICIENT_COINS_MSG = "Insufficient coins for recruitment.";


    public static void main(String[] args) {
        startApp();
    }

    /**
       Starts the paintball app. Allows the interpretation of multiple commands. The list of available commands depends on
     whether a game is running or not.
     */
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
                command = in.next().toUpperCase();
                switch(command){
                    case GAME -> processGame(in,game);
                    case MOVE -> processMove(game);
                    case CREATE -> processCreate(in,game);
                    case ATTACK-> processAttack();
                    case STATUS -> processStatus(game);
                    case MAP -> processMap(game);
                    case BUNKERS -> processBunker(game);
                    case PLAYERS -> processPlayers(game);
                    case HELP -> processHelp(game);
                    case QUIT -> processQuit();
                    default -> processInvalid();
                }
            }
        } while (!command.equals(QUIT)) ;
        in.close();
    }

    /**
     * Lists all the available commands depending on whether a game is running or not.
     * @param game Object game which allows access to all game functionalities
     */
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

    /**
     * Moves a player of the current team.
     * @param game Object game which allows access to all game functionalities
     */
    private static void processMove(Game game){

    }

    /**
     * Creates a player for the current team inside a specified bunker owned by that team.
     * @param in Scanner, allowing to access user input
     * @param game Object game which allows access to all game functionalities
     */
    private static void processCreate(Scanner in, Game game){
    	String type = in.next().toUpperCase();
    	String bunker = in.nextLine().trim();
    	
    	if(!game.isExistingType(type))
    		System.out.println(NON_EXISTING_PLAYERTYPE_MSG);
    	else if(!game.hasBunker(bunker)) 
    		System.out.println(NON_EXISTING_BUNKER_MSG);
    	else if(!game.isBunkerFromCurrentTeam(bunker)) 
    		System.out.println(ILLEGALLY_BUNKER_MSG);	
    	else if(!game.isBunkerFree(bunker)) 
    		System.out.println(BUNKER_NO_FREE_MSG);
    	else if(!game.addPlayer(type, bunker))
    		System.out.println(INSUFFICIENT_COINS_MSG);
    	else 
    		System.out.printf(PLAYER_CREATED_MSG,type,bunker);
    }


    private static void processAttack(){

    }

    /**
     * Returns the current game status, returning information such as map size, number of bunkers and their name and which team owns that bunker.
     * @param game Object game which allows access to all game functionalities
     */
    private static void processStatus(Game game){
        System.out.printf("%d %d\n", game.getRows(), game.getCols());
        System.out.printf("%d bunkers:\n", game.getBunkersNr());
        Iterator<Bunker> bunkerIte = game.bunkerIterator();
        while (bunkerIte.hasNext()){
            Bunker b = bunkerIte.next();
            System.out.printf("%s (%s)\n", b.getName(), b.getTeam().getName());
        }
        
        System.out.printf("%d teams:\n", game.activeTeamsNr());
        Iterator<Team> teamsIte = game.activeTeamsIterator();
        int i = 0;
        while (teamsIte.hasNext()){
            Team t = teamsIte.next();
            i++;
            if(i < game.activeTeamsNr())
            	System.out.printf("%s; ", t.getName());
            else
            	System.out.printf("%s ", t.getName());
        }
        System.out.println();
    }

    /**
     * Returns the current map of the game, with all current team's bunkers and players.
     * @param game Object game which allows access to all game functionalities
     */
    private static void processMap(Game game){
    	Iterator<MapElement> mapIte = game.mapIterator();
    	System.out.printf("%d %d\n", game.getCols(), game.getRows());
    	System.out.print("**");
    	for (int i = 1; i < game.getCols(); i++)
    		System.out.printf("%d ",i);
    	System.out.println(game.getCols());
    	for (int rows = 0; rows < game.getRows(); rows++) {
    		System.out.printf("%2d",rows+1);
    		for (int cols = 0; cols < game.getCols(); cols++) {
    			System.out.printf(" %s",mapIte.next().getChar());
    		}
    		System.out.println();
    	}
    }

    /**
     * Returns the information of all the current team's bunkers.
     * @param game Object game which allows access to all game functionalities
     */
    private static void processBunker(Game game){
        Iterator<Bunker> ite = game.currentTeamBunkerIterator();
        if (!ite.hasNext()){
            System.out.println("Without bunkers.");
        } else {
            System.out.printf("%d bunkers:\n", game.getCurrentTeamBunkerNumber());
        }
        while (ite.hasNext()){
                Bunker b = ite.next();
            System.out.printf("%s with %d coins in position (%d, %d)\n", b.getName(), b.getTreasury(), b.getX()+1, b.getY()+1);
        }
    }

    /**
     * Returns the information of all the current team's players.
     * @param game Object game which allows access to all game functionalities
     */
    private static void processPlayers(Game game){
    	if(game.getCurrentTeamPlayersNumber() == 0) {
    		System.out.println("Without players."); 
    	}else {
    		System.out.printf("%d players:\n", game.getCurrentTeamPlayersNumber());
    		Iterator<Player> playerIte = game.playerIterator();
    		while (playerIte.hasNext()){
    			Player p = playerIte.next();
    			System.out.printf("%s player in position (%d, %d)\n", p.getType(), p.getX()+1, p.getY()+1);
    		}
    	}
    }

    /**
     * Processes all the new game information, creating a new game.
     * @param in Scanner, allowing to access user input
     * @param game Object game which allows access to all game functionalities
     */
    private static void processGame(Scanner in, Game game){
        int width = in.nextInt();
        int height = in.nextInt();
        int teamsNr = in.nextInt();
        int bunkersNr = in.nextInt();
        in.nextLine();

        game.initGame(width, height, teamsNr, bunkersNr);

        readBunkerData(game, in, bunkersNr);
        readTeamData(game, in, teamsNr);
    }

    /**
     * Reads the user's input for all the information regarding the bunkers to be created after validation.
     * @param game Object game which allows access to all game functionalities
     * @param in Scanner, allowing to access user input
     * @param bunkersNr The number of bunkers to be created
     */
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

    /**
     * Reads the user's input for all the information regarding the teams to be created after validation.
     * @param game Object game which allows access to all game functionalities
     * @param in Scanner, allowing to access user input
     * @param teamsNr The number of bunkers to be created
     */
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
            game.cancelGame();
        }
    }

    /**
     * Quits the application.
     */
    private static void processQuit(){
        System.out.println(BYE_MSG);
    }

    /**
     * Warns the user of an invalid command usage.
     */
    private static void processInvalid(){
        System.out.println(INVALID_COMMAND_MSG);
    }
}
