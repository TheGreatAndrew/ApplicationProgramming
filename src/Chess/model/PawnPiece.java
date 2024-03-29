package Chess.model;

public class PawnPiece  extends Piece {
	public PawnPiece(String team, int x, int y) {
	    super(PieceType.Pawn, team, x, y);
    }

	/**
	 * is move is valid and can be moved
	 * @param x x-location
	 * @param y y-location
	 * @param blocks chessboard
	 * @return whether we can move
	 */
    @Override
    public boolean isMoveValid(int x, int y, Block[][] blocks){
        int thisX = this.getX();
        int thisY = this.getY();

        String color = this.getTeam();
        
        if(canAttack(blocks, x, y)){
        	return true;
        }
        
        if(isCollision(y, blocks, color)) {
        	return false;
        }
        
        if (color.equalsIgnoreCase("black")){
            if(thisX - x == 0 && thisY - y == -1){
            	if(this.enPassant == true) {
                	this.setEnPassant(false);
                    blocks[x][y-2].setEnPasantB(false);
                }
            	
            	this.setX(x);
                this.setY(y);
                
                return true;
            }
            if(!hasMoved && (thisX - x == 0 && thisY - y == -2)) {
                this.setX(x);
                this.setY(y);
                blocks[x][y-1].setEnPasantB(true);
                this.hasMoved = true;
                this.setEnPassant(true);
                return true;
            }
        }

        if (color.equalsIgnoreCase("white")){
            if(thisX - x == 0 && thisY - y == 1){
            	if(this.enPassant == true) {
                	this.setEnPassant(false);
                    blocks[x][y+2].setEnPasantW(false);
                }
            	this.setX(x);
                this.setY(y);
                this.hasMoved = true;                
                return true;
            }
            if(!hasMoved && (thisX - x == 0 && thisY - y == 2)) {
                this.setX(x);
                this.setY(y);
                blocks[x][y+1].setEnPasantW(true);
                this.setEnPassant(true);
                this.hasMoved = true;
                return true;
            }
        }
        return false;
    }

	/**
	 * whether is the piece is in the path and we will collide
	 * @param y y-location
	 * @param blocks chessboard
	 * @param color black or white
	 * @return check whether a piece is collided
	 */
	public boolean isCollision(int y, Block[][] blocks, String color) {
    	int dist = Math.abs(this.getY() - y);
    	Piece piece;
    	Block block;

	        if (color.equalsIgnoreCase("black")) {
	        	for(int i = 1; i <= dist; i++) {
		    		piece = blocks[this.getX()][this.getY() + i].getPiece();  
		    		if(piece != null) {
		    			return true;
		    		}
	        	}
	        }
	        if (color.equalsIgnoreCase("white")) {
        		for(int i = 1; i <= dist; i++) {
		    		block = blocks[this.getX()][this.getY() - i]; 
		    		piece = block.getPiece();
		    		if(piece != null) {
		    			return true;
		    		}
        		}
	        }
    	return false;
    }

	/**
	 * whether you can attack
	 * @param blocks chessboard
	 * @param x x-location
	 * @param y y-location
	 * @return special function, whether Pawn can attack diagonal
	 */
    public boolean canAttack(Block[][] blocks, int x, int y) {
    	Piece piece;

    	if(this.getTeam().equalsIgnoreCase("white")) {
    		if(Math.abs(this.getX()-x) == 1 && this.getY() - y == 1) {

        		piece = blocks[x][y].getPiece();  
        		
        		if(piece != null) {
        			this.setX(x);
                    this.setY(y);
        			return true;
        		}
        		
        		
        		if(blocks[x][y].getEnPasantW()) {
        			if(this.getTeam().equalsIgnoreCase("black")) {
            			this.setX(x);
                        this.setY(y);
                        blocks[x][y-1].removeBlock();
                        blocks[x][y].setEnPasantW(false);
            			return true;
        			}
        		}
        		
        		
        		else if(blocks[x][y].getEnPasantB()) {   			
        			if(this.getTeam().equalsIgnoreCase("white")){
            			this.setX(x);
                        this.setY(y);
    	                blocks[x][y+1].removeBlock();
    	                blocks[x][y].setEnPasantB(false);
            			return true;
        			}
        			
        		}
        	}
        	
    	}
    	else if(this.getTeam().equalsIgnoreCase("black")) {
    		if(Math.abs(this.getX()-x) == 1 && this.getY() - y == -1) {

        		piece = blocks[x][y].getPiece();  
        		
        		if(piece != null) {
        			this.setX(x);
                    this.setY(y);
        			return true;
        		}
        		
        		
        		if(blocks[x][y].getEnPasantW()) {
        			if(this.getTeam().equalsIgnoreCase("black")) {
            			this.setX(x);
                        this.setY(y);
                        blocks[x][y-1].removeBlock();
                        blocks[x][y].setEnPasantW(false);
            			return true;
        			}
        		}
        		
        		
        		else if(blocks[x][y].getEnPasantB()) {   			
        			if(this.getTeam().equalsIgnoreCase("white")){
            			this.setX(x);
                        this.setY(y);
    	                blocks[x][y+1].removeBlock();
    	                blocks[x][y].setEnPasantB(false);
            			return true;
        			}
        			
        		}
        	}
        }
    	return false;
    }
}