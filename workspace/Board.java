

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.Position;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //stores whos turn. is True if whites turn.
    private boolean whiteTurn;

    //variable that holds if a piece is currently selected
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //tracks position of mouse on screen
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {

        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
     
      for (int i = 0; i<8;i++)  {
        if (i%2==0){
            for (int j = 0; j<8;j++){
                board[i][j]=new Square(this,(j%2==0),i,j);
                this.add(board[i][j]);
            }
        }
        else if (i%2==1){
            for (int j = 0; j<8;j++){
                board[i][j]=new Square(this,(j%2==1),i,j);
                this.add(board[i][j]);
            }
        }
        }
        
      
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;
        

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    private void initializePieces() {
    	for (int i = 0; i<8; i++){
            board[1][i].put(new Piece(false,RESOURCES_BPAWN_PNG));
        }
        for (int i = 0; i<8; i++){
            board[6][i].put(new Piece(true,RESOURCES_WPAWN_PNG));
        }
        
        board[0][1].put(new Piece(false,RESOURCES_BKNIGHT_PNG));
        board[0][6].put(new Piece(false,RESOURCES_BKNIGHT_PNG));
        board[0][2].put(new Piece(false,RESOURCES_BBISHOP_PNG));
        board[0][5].put(new Piece(false,RESOURCES_BBISHOP_PNG));
        board[0][3].put(new Piece(false,RESOURCES_BQUEEN_PNG));
        board[0][4].put(new Piece(false,RESOURCES_BKING_PNG));
        board[7][1].put(new Piece(true,RESOURCES_WKNIGHT_PNG));
        board[7][6].put(new Piece(true,RESOURCES_WKNIGHT_PNG));
        board[7][2].put(new Piece(true,RESOURCES_WBISHOP_PNG));
        board[7][5].put(new Piece(true,RESOURCES_WBISHOP_PNG));
        board[7][3].put(new Piece(true,RESOURCES_WQUEEN_PNG));
        board[7][4].put(new Piece(true,RESOURCES_WKING_PNG));
        board[7][0].put(new Piece(true,RESOURCES_WROOK_PNG));
        board[7][7].put(new Piece(true,RESOURCES_WROOK_PNG));
        board[0][0].put(new Piece(false,RESOURCES_BROOK_PNG));
        board[0][7].put(new Piece(false,RESOURCES_BROOK_PNG));
        

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override
    public void mouseReleased(MouseEvent e) {
        if (currPiece != null) {
            Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            boolean able = false;
    
            if (currPiece.getColor() == whiteTurn) {
                for (Square legalMove : currPiece.getLegalMoves(this, fromMoveSquare)) {
                    if (endSquare == legalMove) {
                        // Simulate move
                        Piece capturedPiece = endSquare.getOccupyingPiece(); // Store any captured piece
                        fromMoveSquare.removePiece();
                        endSquare.put(currPiece);
    
                        // Check if move leaves the king in check
                        if (!isInCheck(whiteTurn)) {
                            able = true;
                        }
    
                        // Undo the move if illegal
                        endSquare.removePiece();
                        fromMoveSquare.put(currPiece);
                        if (capturedPiece != null) {
                            endSquare.put(capturedPiece);
                        }
    
                        break;
                    }
                }
    
                // If move is legal, execute it
                if (able) {
                    endSquare.removePiece();
                    fromMoveSquare.removePiece();
                    endSquare.put(currPiece);
                    whiteTurn = !whiteTurn; // Switch turns
    
                    // Ensure the player does not remain in check
                    if (isInCheck(whiteTurn)) {
                        // Undo move if it does not escape check
                        endSquare.removePiece();
                        fromMoveSquare.put(currPiece);
                        if (endSquare.getOccupyingPiece() != null) {
                            endSquare.put(endSquare.getOccupyingPiece());
                        }
                    }
                } else {
                    fromMoveSquare.put(currPiece);
                }
            }
    
            // Reset borders after move attempt
            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(null);
            }
        }
    
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;
        System.out.println(currPiece.getLegalMoves(this, fromMoveSquare));
        if(currPiece!= null) {
        	for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)) {
        		s.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        	}
        	
        }
        
        

        repaint();
    }

//precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
          //postcondition - returns true of the king is in check and false otherwise.
          public boolean isInCheck(boolean kingColor){
            Square king = null;
            ArrayList <Square> currMoves = new ArrayList<>();
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    Square piece = board[i][j];
                    if(piece instanceof Square King && piece.getColor() == kingColor) {
                        king = piece;
                        break;
                    }
                }
            }
            int x;
         int y;
         int kingX= -1;
         int kingY= -1;
 
 		for (int i= 0; i<8;i++){
            for (int j=0; j<8; j++){
                if (board[i][j].getOccupyingPiece()!= null && board[i][j].getOccupyingPiece().getColor() != kingColor){
                    currMoves = board[i][j].getOccupyingPiece().getControlledSquares(board, board[i][j]);
                    for (int k=0; k<currMoves.size(); k++){
                        y = currMoves.get(k).getRow();
                        x = currMoves.get(k).getCol();
                        if (kingX == x && kingY == y){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}