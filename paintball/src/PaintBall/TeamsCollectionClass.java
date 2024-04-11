package PaintBall;

public class TeamsCollectionClass implements TeamsCollection{
    private static final int NOT_FOUND = -1;
    private int size;
    private int currentTeamIndex;
    private Team[] teams;

    public TeamsCollectionClass(int teamsNr){
        size = 0;
        currentTeamIndex = 0;
        teams = new Team[teamsNr];
    }

    @Override
    public boolean hasTeam(String name) {
        return findIndexOf(name) != NOT_FOUND;
    }

    @Override
    public void addTeam(String name, String bunker) {
        teams[size++] = new TeamClass(name, bunker);
    }

    @Override
    public String getCurrentTeam() {
        return teams[currentTeamIndex].getName();
    }

    @Override
    public void changeCurrentTeam() {
        while (currentTeamIndex < size && !teams[currentTeamIndex].isActive()) {
            currentTeamIndex++;
            if (currentTeamIndex >= size) {
                currentTeamIndex = 0;
            }
        }
    }

    private int findIndexOf(String name){
        int i = 0;
        while(i < size && !teams[i].getName().equals(name)){
            i++;
        }
        if (i < size){
            return i;
        } else {
            return NOT_FOUND;
        }
    }

	@Override
	public int getSize() {
		return size;
	}
}
