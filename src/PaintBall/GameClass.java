/**
 * Class Game responsible to manage all the game's mechanics.
 * @author Francisco Correia & Sérgio Garrido
 */


package PaintBall;
import DataStructures.*;

import java.util.StringTokenizer;

public class GameClass implements Game {
	
	private static final int NOT_FOUND = -1;
	private static final int MINIMUM_TEAMS = 2;
    private static final char FIELD_FREE = '.';
    private static final char BUNKER_FREE = 'B';
    private static final char BUNKER_OCCUPIED = 'O';
    
    private static final String player_RED = "RED";
    private static final String player_BLUE = "BLUE";
    private static final String player_GREEN = "GREEN";

    private boolean status;
    private Team currentTeam;
    private Map mapE;
    private ArrayClass<Team> teams;
    private ArrayClass<Bunker> bunkers;
    private ArrayClass<Move> lastMovements;

    // PlaceHolder
    public GameClass(){
        status = false;
    }

    public void initGame(int width, int height, int teamsNr, int bunkersNr){
        status = true;
        mapE = new MapClass(width, height);
        bunkers = new ArrayClass<>(bunkersNr);
        teams = new ArrayClass<>(teamsNr);
    }

    @Override
    public int getBunkersNr() {
        return bunkers.size();
    }

    @Override
    public int activeTeamsNr() {
        int activeTeams = 0;
        Iterator<Team> ite = teams.iterator();
        while (ite.hasNext()){
            activeTeams++;
            ite.next();
        }
        return activeTeams;
    }

    @Override
    public Iterator<Bunker> bunkerIterator() {
        return bunkers.iterator();
    }

    @Override
    public Iterator<Team> activeTeamsIterator() {
        return teams.iterator();
    }

    @Override
    public void cancelGame() {
        status = false;
    }

    @Override
    public int getCols() {
        return mapE.getWidth();
    }

    @Override
    public int getRows() {
        return mapE.getHeight();
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public String getCurrentTeam() {
        return currentTeam.getName();
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return mapE.isValidPosition(x, y);
    }

    @Override
    public boolean isInside(int x, int y) {
        return mapE.isInside(x, y);
    }

    @Override
    public boolean hasBunker(String name) {
    	return findBunkerIdx(name) != NOT_FOUND;
    }
    
    private int findBunkerIdx(String name){
        int i = 0;
        Iterator<Bunker> ite = bunkers.iterator();
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    @Override
    public void addBunker(int x, int y, String name, int treasury) {
        Bunker bunker = new BunkerClass (x,y,name,treasury);
        bunkers.insertLast(bunker);
        mapE.addElement(bunker, x, y);
    }

    @Override
    public void addTeam(String teamName, String bunkerName) {
        Bunker bunker = bunkers.get(findBunkerIdx(bunkerName));
    	Team team = new TeamClass(teamName);
    	if (teams.size() == 0){
             currentTeam = team;
        }
        teams.insertLast(team);
        conquerBunker(team, bunker);
        
    }
    
    private void conquerBunker(Team team, Bunker bunker) {
    	bunker.changeTeam(team);
        team.conquerBunker(bunker);
    }


    @Override
    public boolean hasTeam(String name) {
        return findTeamIdx(name) != NOT_FOUND;
    }
    
    private int findTeamIdx(String name){
        int i = 0;
        Iterator<Team> ite = teams.iterator();
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    @Override
    public boolean isAbandonedBunker(String name) {
        return bunkers.get(findBunkerIdx(name)).isAbandoned();
    }

    //@Override
    //public void setCurrentTeam() {
    //   currentTeam = teams.getCurrentTeam();
    //}

	@Override
	public boolean hasEnoughTeams() {
		return teams.size() >= MINIMUM_TEAMS;
	}
	
	public Iterator<MapElement> mapIterator() {
		return mapE.iterator(currentTeam);
	}

	@Override
	public boolean addPlayer(String playerType, String bunkerName) {
			boolean result = false;
			if(bunkers.get(findBunkerIdx(bunkerName)).createPlayer(playerType)) {
				result = true;
			}
			return result;
	}
	public void nextTurn() {
		changeCurrentTeam();
		incrementTreasury();
	}
	
    private void changeCurrentTeam(){
        int i = findTeamIdx(currentTeam.getName());
        do {
            i = (i + 1) % teams.size();
        } while (!teams.get(i).isActive());
        currentTeam = teams.get(i);
        
    }
    
    private void incrementTreasury() {
    	Iterator<Bunker> ite = bunkers.iterator();
        while (ite.hasNext()){
            ite.next().incrementTreasury();     
        }
    }
    

	/*public boolean isExistingType(String playerType) {
		return(playerType.equalsIgnoreCase(player_RED) || 
				playerType.equalsIgnoreCase(player_BLUE) || 
				playerType.equalsIgnoreCase(player_GREEN));
	}*/
	
	public boolean isExistingType(String playerType) {
		boolean exists = true;
		try{
			PlayerType.valueOf(playerType);
		}catch (IllegalArgumentException e) {
			exists = false;
		}
		return exists;
	}

	@Override
	public boolean isBunkerFromCurrentTeam(String name) {
		return currentTeam.equals(bunkers.get(findBunkerIdx(name)).getTeam());
	}

	@Override
	public boolean isBunkerFree(String name) {
		return bunkers.get(findBunkerIdx(name)).isFree();
	}
	
	public Iterator<Player> playerIterator(){
		return currentTeam.playerIterator();
	}

    @Override
    public Iterator<Bunker> currentTeamBunkerIterator() {
        return currentTeam.bunkerIterator();
    }

    @Override
    public int getCurrentTeamPlayersNumber() {
		return currentTeam.numberPlayer();
	}

    @Override
    public int getCurrentTeamBunkerNumber() {
        return currentTeam.numberBunkers();
    }

    @Override
    public Iterator<Move> move(int x, int y, String nextLine) {
        StringTokenizer token = new StringTokenizer(nextLine);
        int nrMoves = token.countTokens();
        lastMovements = new ArrayClass<>(nrMoves);

        if (!isInside(x, y)) {
            lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_POSITION));
        } else if (!isPlayer(x, y)) {
            lastMovements.insertLast(new MoveClass(MoveOutput.NO_PLAYER));
        } else if (!isValidMovement(x, y, nrMoves)){
            lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_MOVE));
        } else {
            Player p = findPlayer(x, y);
            do {
                String dir = token.nextToken();
                if (!isValidDirection(dir)){
                    lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_DIRECTION));
                } else if (!isMovementPossible(dir, p.getX(), p.getY())){
                    lastMovements.insertLast(new MoveClass(MoveOutput.OFF_MAP));
                } else if (!canMove(p.getX(), p.getY(), dir)){
                    lastMovements.insertLast(new MoveClass(MoveOutput.CANNOT_MOVE));
                } else {
                    lastMovements.insertLast(movePlayer(p, dir, p.getX(), p.getY()));
                }
            } while (token.hasMoreTokens());
        }
        return lastMovements.iterator();
    }

    private Move movePlayer(Player player, String dir, int x, int y){
       Move newPos = player.move(dir);
       return mapE.moveElem(x, y, newPos);
    }
    

    public boolean isMovementPossible(String dir, int x, int y){
        boolean result = switch(Directions.valueOf(dir)){
            case NORTH -> isInside(x, y-1);
            case SOUTH -> isInside(x, y+1);
            case EAST -> isInside(x+1, y);
            case WEST -> isInside(x-1, y);
        };
        return result;
    }

    public boolean isValidDirection(String dir){
        boolean exists = true;
        try{
            Directions.valueOf(dir);
        }catch (IllegalArgumentException e) {
            exists = false;
        }
        return exists;
    }

    private Player findPlayer(int x, int y){
        Iterator<Player> ite = currentTeam.playerIterator();
        Player player = null;
        boolean found = false;
        while (ite.hasNext() && !found){
            Player p = ite.next();
            if (p.getX() == x && p.getY() == y){
                player = p;
                found = true;
            }
        }
        return player;
    }

	@Override
	public boolean isValidMovement(int x, int y, int nrMoves) {
		Player player = findPlayer(x, y);
		if(player instanceof RedPlayerClass && nrMoves == RedPlayerClass.MAX_MOVES)
			return true;
		else if(player instanceof RedPlayerClass)
			return false;
		else 
			return true;
	}

	@Override
	public boolean isPlayer(int x, int y) {
		return findPlayer(x,y) != null;
	}

	@Override
	public boolean canMove(int x, int y, String dir) {
		return !mapE.nextCellOccupied(x, y, dir);
	}

	@Override
	public Iterator<Move> moveIterator() {
		return null;
	}
}
