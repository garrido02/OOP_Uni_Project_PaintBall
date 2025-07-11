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


    private boolean status;
    private Team currentTeam;
    private String winner;
    private Map mapE;
    private ArrayClass<Team> teams;
    private ArrayClass<Bunker> bunkers;
    private ArrayClass<Move> lastMovements;

    /**
     * Default constructor for when the game is not running.
     */
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

    /**
     * Returns the bunker index of the bunker with a given name
     * @param name The name of the bunker
     * @return th index of the bunker
     */
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

    /**
     * Conquers a given bunker changing its owner to a given team.
     * @param team The teams who will conquer the bunker
     * @param bunker The bunker who will change owners
     */
    private void conquerBunker(Team team, Bunker bunker) {
    	bunker.changeTeam(team);
        team.conquerBunker(bunker);
    }


    @Override
    public boolean hasTeam(String name) {
        return findTeamIdx(name) != NOT_FOUND;
    }


    /**
     * Returns the index of a team with a given name.
     * @param name The name of a given team
     * @return the index of the team
     */
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


    /**
     * Changes the current team to the next active team by order of creation.
     */
    private void changeCurrentTeam(){
        int i = findTeamIdx(currentTeam.getName());
        do {
            i = (i + 1) % teams.size();
        } while (!teams.get(i).isActive());
        currentTeam = teams.get(i);
        
    }

    /**
     * Increments the amount of coins of each bunker by 1.
     */
    private void incrementTreasury() {
    	Iterator<Bunker> ite = bunkers.iterator();
        while (ite.hasNext()){
            ite.next().incrementTreasury();     
        }
    }

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

    	if(this.checkGenericMoveConditions(x, y, nrMoves)) {
    		Player player = findPlayer(x, y, currentTeam);
    		do {
    			String dir = token.nextToken();
    			if(this.checkEachMoveConditions(player, dir)) {
    				lastMovements.insertLast(movePlayer(player, dir, player.getX(), player.getY()));
    			}
    		} while (token.hasMoreTokens());
    	}
    	return lastMovements.iterator();
    }

    /**
     * Checks if the movement in a certain direction is possible
     * @param player The player who is about to move
     * @param direction The direction the player is about to move
     * @return true if player moved with success. Otherwise, false.
     * @pre checkGenericMoveConditions = true
     */
    private boolean checkEachMoveConditions(Player player, String direction) {
    	boolean result = false;
    	if (!isValidDirection(direction)){
    		lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_DIRECTION));
    	} else if (!isMovementPossible(direction, player.getX(), player.getY())){
    		lastMovements.insertLast(new MoveClass(MoveOutput.OFF_MAP));
    	} else if (!canMove(player.getX(), player.getY(), direction)){
    		lastMovements.insertLast(new MoveClass(MoveOutput.CANNOT_MOVE));
    	} else if(player.isAlive()){
    		result = true;
    	}	
    	return result;	
    }

    /**
     * Checks if the move command is possible on a given position and number of moves
     * @param x The horizontal position
     * @param y The vertical position
     * @param moves The number of moves
     * @return true if movement is possible. Otherwise, false.
     */
    private boolean checkGenericMoveConditions(int x, int y, int moves) {
    	boolean result = false; 
    	if (!isInside(x, y)) {
             lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_POSITION));
         } else if (!isPlayer(x, y)) {
             lastMovements.insertLast(new MoveClass(MoveOutput.NO_PLAYER));
         } else if (!isValidMovement(x, y, moves)){
             lastMovements.insertLast(new MoveClass(MoveOutput.INVALID_MOVE));
         } else {
        	 result = true;
         }
    	return result;
    }

    /**
     *
     * @param player The player who is about to move
     * @param dir The direction we want to move
     * @param x The horizontal position
     * @param y The vertical Position
     * @return a Move object with all the information regarding the movement
     */
    private Move movePlayer(Player player, String dir, int x, int y){
       Move newPos = player.move(dir);
       Move move = processMove(newPos,x,y);
       eliminateTeam();
       return move;
    }


    /**
     * Process the move command, processing fights and seizing bunkers.
     * @param newPos The move object with the information about the player movement to a new position
     * @param x The pre-movement horizontal position
     * @param y The pre-movement vertical position
     * @return a Move object with all the information regarding the movement
     */
    private Move processMove(Move newPos, int x, int y) {
    	Player player = newPos.getPlayer();
    	MapElement currentCell = mapE.getElement(x, y);
        MapElement nextCell = mapE.getElement(newPos.getX(), newPos.getY());
    	
        Move result;
    	if(nextCell instanceof EmptyElementClass) {
    		result = moveToEmptyCell(player,newPos.getX(),newPos.getY());
    	}else if (nextCell instanceof Bunker){
    		Bunker bunker = findBunker(nextCell.getX(),nextCell.getY());
    		if(player.getTeam().equals(bunker.getTeam())){
    			result = moveToFreeBunker(player, bunker);
    		}else if(bunker.isFree()) {
    			result = seizeFreeBunker(player, bunker);
    		}else if(player.attack(bunker.getPlayer())){
    			result = this.winAndSeizeBunker(player, bunker);
    		}else {
    			result = this.lostFight(player);
    		}	
    	}else {
    		Player otherPlayer = findPlayer(nextCell.getX(),nextCell.getY(),nextCell.getTeam());
    		if(player.attack(otherPlayer)) {
    			result = this.wonFight(player, otherPlayer);
    		}else
    			result = this.lostFight(player);
    	}
    	updateMap(currentCell,x,y);
    	return result;
    }

    /**
     * Processes the movement when the player wins a fight.
     * @param player The player who is attacking
     * @param otherPlayer The player who is being attacked
     * @return a Move object with all the information regarding the movement
     */
    private Move wonFight(Player player, Player otherPlayer) {
    	otherPlayer.getTeam().removePlayer(otherPlayer);
    	mapE.addElement(player, otherPlayer.getX(), otherPlayer.getY());
    	return new MoveClass(player, MoveOutput.WON_FIGHT);
    }

    /**
     * Processes the movement when the player loses a fight.
     * @param player The player who is attacking
     * @return a Move object with all the information regarding the movement
     */
    private Move lostFight(Player player) {
    	currentTeam.removePlayer(player);
    	return new MoveClass(player, MoveOutput.PLAYER_ELIMINATED);
    }

    /**
     * Processes the movement when the player wins a fight and seizes a bunker.
     * @param player The player attacking
     * @param bunker The bunker being seized
     * @return a Move object with all the information regarding the movement
     */
    private Move winAndSeizeBunker(Player player,Bunker bunker) {
    	bunker.getTeam().removePlayer(bunker.getPlayer());
    	conquerBunker(player,bunker);
    	return new MoveClass(player, MoveOutput.WON_FIGHT_AND_BUNKER_SEIZED);
    }

    /**
     * Processes the movement when the player seizes a free bunker.
     * @param player The player who is moving to the bunker
     * @param bunker The bunker being seized
     * @return a Move object with all the information regarding the movement
     */
    private Move seizeFreeBunker(Player player, Bunker bunker) {
    	conquerBunker(player,bunker);
    	return new MoveClass(player, MoveOutput.BUNKER_SEIZED);
    	
    }

    /**
     * Conquers a bunker, changing the owner to the team of the player who is seizing it.
     * @param player The player who is conquering the bunker
     * @param bunker The bunker being conquered
     */
    private void conquerBunker(Player player, Bunker bunker) {
    	if(!bunker.isAbandoned()) {
    		bunker.getTeam().removeBunker(bunker);
    	}
    	currentTeam.conquerBunker(bunker);
    	bunker.changeTeam(currentTeam);
    	bunker.playerIn(player);
    }

    /**
     * Moves the player who is moving to a free bunker.
     * @param player The player who is moving
     * @param bunker The bunker in which the player is entering
     * @return a Move object with all the information regarding the movement
     */
    private Move moveToFreeBunker(Player player, Bunker bunker) {
    	bunker.playerIn(player);
        return new MoveClass(player, MoveOutput.SUCCESS);
    }

    /**
     * Moves the player to an empty cell on the map.
     * @param player The player who is moving
     * @param newX The new horizontal position
     * @param newY The new vertical position
     * @return a Move object with all the information regarding the movement
     */
    private Move moveToEmptyCell(Player player,int newX, int newY) {
    	mapE.addElement(player, newX, newY);
		return new MoveClass(player, MoveOutput.SUCCESS);
    }

    /**
     * Updates the map after a movement.
     * @param element The cell of the map to be updated
     * @param x the horizontal position
     * @param y the vertical position
     */
    private void updateMap(MapElement element, int x, int y) {
        if (element instanceof Player){
            mapE.removeElement(x, y);
        } else if (element instanceof Bunker){
            Bunker b = findBunker(element.getX(),element.getY());
            b.playerOut();
        }
    }

    @Override
    public boolean attack() {
        boolean playerDie = false;
        Iterator<Player> playersIte = currentTeam.playerIterator();
        while (playersIte.hasNext() && !isGameOver()){
            Player p = playersIte.next();
            playerDie = false;
            Iterator<Coords> coordsIte = p.attackPattern(mapE.getHeight(), mapE.getWidth()).iterator();
            while (coordsIte.hasNext() && !isGameOver() && !playerDie){
                Coords c = coordsIte.next();
                MapElement m = mapE.getElement(c.getX(), c.getY());
                if ((m instanceof Player && !p.getTeam().equals(m.getTeam()))){
                    if(p.attack(m)){
                        m.getTeam().removePlayer((Player)m);
                        mapE.removeElement(m.getX(), m.getY());
                    } else {
                        if (mapE.getElement(p.getX(), p.getY()) instanceof Bunker){
                            ((Bunker) mapE.getElement(p.getX(), p.getY())).playerOut();
                        } else {
                            mapE.removeElement(p.getX(), p.getY());
                        }
                        currentTeam.removePlayer(p);
                        playerDie = true;
                    }
                } else if (m instanceof Bunker && !((Bunker) m).isFree() && !p.getTeam().equals(m.getTeam())){
                    if (p.attack(((Bunker) m).getPlayer())){
                        m.getTeam().removePlayer(((Bunker) m).getPlayer());
                        m.getTeam().removeBunker((Bunker) m);
                        ((Bunker) m).playerOut();
                        ((Bunker) m).changeTeam(p.getTeam());
                        p.getTeam().conquerBunker((Bunker) m);
                    } else {
                        if (mapE.getElement(p.getX(), p.getY()) instanceof Bunker){
                            ((Bunker) mapE.getElement(p.getX(), p.getY())).playerOut();
                        } else {
                            mapE.removeElement(p.getX(), p.getY());
                        }
                        currentTeam.removePlayer(p);
                        playerDie = true;
                    }
                } else if ((m instanceof Bunker) && ((Bunker) m).isFree() && (!p.getTeam().equals(m.getTeam()))){
                	if(!((Bunker) m).isAbandoned()) 
                		m.getTeam().removeBunker((Bunker) m);
                	((Bunker) m).changeTeam(p.getTeam());
                	p.getTeam().conquerBunker((Bunker) m);
                }
            }
        }
        eliminateTeam();
        return currentTeam.isActive();
    }

    /**
     * Eliminates all not active teams.
     */
    private void eliminateTeam() {
    	Iterator<Team> ite = teams.iterator();
    	Team[] team = new Team[teams.size()];
    	int counter = 0;
    	while (ite.hasNext()) {
    		Team t = ite.next();
    		if(!t.isActive())
    			team[counter++] = t;
    	}
    	int i = 0;
    	while(i < counter)
    		teams.removeAt(teams.searchIndexOf(team[i++]));
    }

    @Override
    public boolean isGameOver() {
        boolean result = teams.size() < MINIMUM_TEAMS;
        if (result){
            winner = teams.get(0).getName();
            status = false;
        }
        return result;
    }

    @Override
    public String getWinner() {
        return winner;
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

    /**
     * Finds the player located in position x,y of a given team.
     * @param x the horizontal position
     * @param y the vertical position
     * @param team the team to which the player belongs to
     * @return a Player object
     */
    private Player findPlayer(int x, int y, Team team){
        Iterator<Player> ite = team.playerIterator();
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

    /**
     * Finds the player located in position x,y.
     * @param x the horizontal position
     * @param y the vertical position
     * @return a Bunker object
     */
    private Bunker findBunker(int x, int y) {
    	Iterator<Bunker> ite = bunkers.iterator();
        Bunker bunker = null;
        boolean found = false;
        while (ite.hasNext() && !found){
            Bunker b = ite.next();
            if (b.getX() == x && b.getY() == y){
                bunker = b;
                found = true;
            }
        }
        return bunker;
    }

	@Override
	public boolean isValidMovement(int x, int y, int nrMoves) {
		Player player = findPlayer(x, y, currentTeam);
		return player.getPossibleMoves() >= nrMoves;
	}

	@Override
	public boolean isPlayer(int x, int y) {
		return findPlayer(x,y,currentTeam) != null;
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
