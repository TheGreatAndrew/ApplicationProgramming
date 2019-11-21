package Chess.model;

public class KingPiece  extends Piece {
	
    public KingPiece(String team, int x, int y) {
        super(PieceType.King, team, x, y);
    }
    
    /* Determine wether the player is making a valid move */
    @Override
    public boolean isMoveValid(int x, int y, Block blocks[][]){
		int thisX = this.getX();
		int thisY = this.getY();

		/* If there is currently a */
    	if(blocks[x][y].getPiece() != null) {
    		if(blocks[x][y].getPiece().getTeam().equalsIgnoreCase(super.getTeam())) {
    			System.out.println("False");
    			return false;
    		}
    	}

    	// thisX = cur pos
		// x = move to pos

		if((Math.abs(thisX - x) == 1 && Math.abs(thisY - y) == 0) || (Math.abs(thisX - x) == 0 && Math.abs(thisY - y) == 1)
				|| (Math.abs(thisX - x) == 1 && (Math.abs(thisX - x) == Math.abs(thisY - y)))){
    		this.setX(x);
    		this.setY(y);
    		return true;
    	}
		return false;
    }
}