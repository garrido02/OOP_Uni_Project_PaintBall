package PaintBall;
import DataStructures.*;

import java.util.Objects;

import DataStructures.Iterator;

public class TeamClass implements Team {
    private String name;
    private boolean status;
    private ArrayClass<Bunker> bunkers;
    private int size;
    private ArrayClass<Player> players;

    public TeamClass(String name){
        this.name = name;
        status = true;
        size = 0;
        bunkers = new ArrayClass<>();
        players = new ArrayClass<>();
    }

    public void addPlayer(Player player) {
    	players.insertLast(player);
    }
    
    public void removeBunker(Bunker bunker) {
        bunkers.removeAt(bunkers.searchIndexOf(bunker));
        isEliminated();
    }
    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return status;
    }

    @Override
    public void changeStatus() {
        status = false;
    }

    @Override
    public void conquerBunker(Bunker bunker) {
        bunkers.insertLast(bunker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamClass teamClass = (TeamClass) o;
        return Objects.equals(name, teamClass.name);
    }
    
    public Iterator<Player> playerIterator(){
    	return players.iterator();
    }

    @Override
    public Iterator<Bunker> bunkerIterator() {
        return bunkers.iterator();
    }

    @Override
	public int numberPlayer() {
		return players.size();
	}

    @Override
    public int numberBunkers() {
        return bunkers.size();
    }

    @Override
    public void attack() {
        Iterator<Player> ite = players.iterator();
        while (ite.hasNext()){
            Player p = ite.next();

            //p.attack(Object e);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.removeAt(players.searchIndexOf(player));
        isEliminated();
    }

    private void isEliminated(){
        if (players.size() == 0 && bunkers.size() == 0){
            status = false;
        }
    }
}
