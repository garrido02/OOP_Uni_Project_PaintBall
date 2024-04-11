package PaintBall;
import DataStructures.*;

public class TeamsCollectionClass implements TeamsCollection{
    private static final int NOT_FOUND = -1;
    private static final String DEFAULT_TEAM = "NONE";
    private String currentTeamName;
    private Array<Team> teams;

    public TeamsCollectionClass(int teamsNr){
        currentTeamName = DEFAULT_TEAM;
        teams = new ArrayClass<>(teamsNr);
    }

    @Override
    public boolean hasTeam(String name) {
        return findIndexOf(name) != NOT_FOUND;
    }

    @Override
    public void addTeam(String name, String bunker) {
        if (getSize() == 0){
            currentTeamName = name;
        }
        teams.insertLast(new TeamClass(name, bunker));
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
    public String getCurrentTeam() {
        return teams.get(findIndexOf(currentTeamName)).getName();
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
        currentTeamName = teams.get(i).getName();
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
