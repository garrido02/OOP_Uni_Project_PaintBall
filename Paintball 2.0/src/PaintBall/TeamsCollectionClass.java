package PaintBall;
import DataStructures.*;

public class TeamsCollectionClass implements TeamsCollection{
    private static final int NOT_FOUND = -1;
    private Team currentTeam;
    private Array<Team> teams;

    public TeamsCollectionClass(int teamsNr){
        teams = new ArrayClass<>(teamsNr);
    }

    @Override
    public boolean hasTeam(String name) {
        return findIndexOf(name) != NOT_FOUND;
    }

    @Override
    public void addTeam(Team team) {
        if (getSize() == 0){
            currentTeam = team;
        }
        teams.insertLast(team);
    }

    @Override
    public Iterator<Team> iterator() {
        return new ActiveTeamsIterator(teams);
    }

    @Override
    public int activeTeams() {
        int activeTeams = 0;
        Iterator<Team> ite = iterator();
        while (ite.hasNext()){
            activeTeams++;
            ite.next();
        }
        return activeTeams;
    }

    @Override
    public Team getCurrentTeam() {
        return teams.get(findIndexOf(currentTeam.getName()));
    }

    @Override
    public void changeCurrentTeam() {
        int i = 0;
        while (i < teams.size() && !teams.get(i).isActive()){
            i++;
            if (i >= teams.size()){
                i = 0;
            }
        }
        currentTeam = teams.get(i);
    }

    private int findIndexOf(String name){
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
	public int getSize() {
		return teams.size();
	}
}
