package PaintBall;

public class MoveClass implements Move{
	private static final int OFFSET_POSITIVE = 1;
	private static final int OFFSET_NEGATIVE = -1;
	private static final int NO_OFFSET = 0;
    private Player player;
    private boolean successfulMovement;
    private MoveOutput output;
    private int newX;
    private int newY;

    public MoveClass(Player player, MoveOutput output){
        this.player = player;
        this.output = output;
        this.newX = player.getX();
        this.newY = player.getY();
        this.successfulMovement = true;
    }

    public MoveClass(MoveOutput output){
        this.output = output;
        this.successfulMovement = false;
    }
    
    public static int [] calculateMove(int x, int y, String dir) {
    	int [] coords = switch(Directions.valueOf(dir)){
            case NORTH -> 
            	applyOffset(x,y,NO_OFFSET,OFFSET_NEGATIVE);
            case SOUTH -> 
            	applyOffset(x,y,NO_OFFSET,OFFSET_POSITIVE);      	
            case EAST -> 
            	applyOffset(x,y,OFFSET_POSITIVE,NO_OFFSET);
            case WEST -> 
            	applyOffset(x,y,OFFSET_NEGATIVE,NO_OFFSET);
        };
    	return coords;
    }
    
    private static int[] applyOffset(int x, int y, int offset_x,  int offset_y) {
    	int[] coords = {x + offset_x, y + offset_y};
    	return coords;
    }

    @Override
    public boolean isSuccessful() {
        return successfulMovement;
    }

    @Override
    public int getX() {
        return newX;
    }

    @Override
    public int getY() {
        return newY;
    }

    @Override
    public MoveOutput getOutput() {
        return output;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String playerType() {
        return player.getType();
    }
}
