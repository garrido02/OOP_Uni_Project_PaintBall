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
                command = in.next().toUpperCase();
                switch(command){
                    case GAME -> processGame(in,game);
                    case MOVE -> processMove();
                    case CREATE -> processCreate(in,game);
                    case ATTACK-> processAttack();
                    case STATUS -> processStatus(game);
                    case MAP -> processMap(game);
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

    private static void processCreate(Scanner in, Game game){
    	String type = in.next().toUpperCase();
    	String bunker = in.nextLine().trim();
    	
    	
    	if(game.isExistingType(type)) {
    		
    		if(game.hasBunker(bunker)) {
    			
    			if(game.isBunkerFromCurrentTeam(bunker)) {
    				
    				if(game.isBunkerFree(bunker)) {
    					
    					if(game.addPlayer(type, bunker)) {
    						System.out.printf("%s player created in %s\n",type,bunker);
    					}
    					else {
    						System.out.println("Insufficient coins for recruitment.");
    					}
    				}else {
    					System.out.println("Bunker not free");
    				}
    			}else {
    				System.out.println("Bunker illegally invaded.");
    			}
    		}else {
    			System.out.println(NON_EXISTING_BUNKER_MSG);
    		}	
    	}else {
    		System.out.println("Non-existent player type.");
    	}

    }
    
    private static void processCreate2(Scanner in, Game game){
    	String type = in.next().toUpperCase();
    	String bunker = in.nextLine().trim();
    	
    	boolean result  = game.isExistingType(type);
    	
    	if(!result)
    		System.out.println("Non-existent player type.");
    		
    	if(!(result && game.hasBunker(bunker))) {
    		System.out.println(NON_EXISTING_BUNKER_MSG);
    		result = result && game.hasBunker(bunker);
    	}
    	
    	if(!(result && game.isBunkerFromCurrentTeam(bunker))) {
    		System.out.println("Bunker illegally invaded.");
    		result = result && game.isBunkerFromCurrentTeam(bunker);
    	}
    		
    	if(!(result && game.isAbandonedBunker(bunker))) {
    		System.out.println("Bunker not free.");
    		result = result && game.isAbandonedBunker(bunker);
    	}
    		
    	if(result && game.addPlayer(type, bunker))
    		System.out.printf("%s player created in %s\n",type,bunker);
    	else
    		System.out.println("Insufficient coins for recruitment.");
    }

    private static void processAttack(){

    }

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

    private static void processMap(Game game){
    	Iterator<MapElement> mapIte = game.mapIterator();
    	System.out.printf("%d %d\n", game.getRows(), game.getCols());
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
            game.cancelGame();
        }
    }

    private static void processQuit(){
        System.out.println(BYE_MSG);
    }

    private static void processInvalid(){
        System.out.println(INVALID_COMMAND_MSG);
    }
}
