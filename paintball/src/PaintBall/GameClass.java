package PaintBall;

public class GameClass implements Game {
    private final char FIELD_FREE = '.';
    private final char BUNKER_FREE = 'B';
    private final char BUNKER_OCCUPIED = 'O';
    private final char PLAYER_CURRENT_TEAM = 'P';

    private boolean status;
    private String currentTeam;
    private char[][] map;
    private Teams teamCollection;
    private Bunkers bunkerCollection;

    // PlaceHolder
    public GameClass(){
        status = false;
        currentTeam = "placeHolder";
    }

    public GameClass(int width, int height){
        status = true;
        map = new char[width][height];
        fillMap();
    }

    private void fillMap(){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = FIELD_FREE;
            }
        }
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public String getCurrentTeam() {
        return currentTeam;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        boolean freePos = map[x][y] == FIELD_FREE;
        boolean xValid =  x >= 0 && x <= map[0].length;
        boolean yValid = y >= 0 && y <= map.length;
        return freePos && xValid && yValid;
    }

    @Override
    public boolean hasBunker(String name) {
        return false;
    }

    @Override
    public boolean hasTeam(String name) {
        return false;
    }

    @Override
    public boolean isAbandonedBunker(String name) {
        return false;
    }
}
