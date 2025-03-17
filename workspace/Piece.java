
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.

    //takes square array of board and starting square of peice
    //POST Returns an array list with the squares that can be captured by the spy
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList <Square> controlledArrayList = new ArrayList<Square>();
      for (int col = start.getCol()+1; col<8;col++){
        if(board[start.getRow()][col].isOccupied()){
          controlledArrayList.add(board[start.getRow()][col]);
          break;
          
        }
        else{
          controlledArrayList.add(board[start.getRow()][col]);
        }
      }
      for (int colB = start.getCol()+1; colB>0;colB--){
        if(board[start.getRow()][colB].isOccupied()){
          controlledArrayList.add(board[start.getRow()][colB]);
          break;
        }
        else{
          controlledArrayList.add(board[start.getRow()][colB]);
        }
      }
      for (int rowB = start.getCol()+1; rowB>0;rowB--){
        if(board[rowB][start.getCol()].isOccupied()){
          controlledArrayList.add(board[rowB][start.getCol()]);
          break;
    
        }
        else{
          controlledArrayList.add(board[rowB][start.getCol()]);
        }
      }
      for (int row = start.getRow()+1; row<8;row++){
        if(board[row][start.getCol()].isOccupied()){
          controlledArrayList.add(board[row][start.getCol()]);
          break;
        }
        else{
          controlledArrayList.add(board[row][start.getCol()]);
        }
      }
      
    return controlledArrayList;
  }
     
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //PRE:  takes the current board as an input as well the peices current square 
    //POST: it retuns an array list that has every possible move for the spy
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      // the spy is a piece that looks like a pawn, but moves like a rook, aiming to confuse the enemy.
      
     Square [][] board = b.getSquareArray();
      ArrayList<Square> spaces  = new ArrayList<Square>();
      //right
        for (int col = start.getCol()+1; col<8;col++){
          if(board[start.getRow()][col].isOccupied()){
            if(board[start.getRow()][col].getOccupyingPiece().getColor() == color){
                break;
            }
            else{
            spaces.add(board[start.getRow()][col]);
            break;
            }
          }
          else{
            spaces.add(board[start.getRow()][col]);
          }
        }
        //left
        for (int colB = start.getCol()-1; colB>=0;colB--){
          if(board[start.getRow()][colB].isOccupied()){
            if(board[start.getRow()][colB].getOccupyingPiece().getColor() == color){
              break;
          }
        
            spaces.add(board[start.getRow()][colB]);
            break;
      
          }
          else{
            spaces.add(board[start.getRow()][colB]);
          }
        }
        //up
        for (int rowB = start.getRow()-1; rowB>=0;rowB--){
          if(board[rowB][start.getCol()].isOccupied()){
            if(board[rowB][start.getCol()].getOccupyingPiece().getColor() == color){
              break;
          }
        
            spaces.add(board[rowB][start.getCol()]);
            break;
      
          }
          else{
            spaces.add(board[rowB][start.getCol()]);
          }
        }
        //down
        for (int row = start.getRow()+1; row<8;row++){
          if(board[row][start.getCol()].isOccupied()){
            if(board[row][start.getCol()].getOccupyingPiece().getColor() == color){
              break;
          }
            spaces.add(board[row][start.getCol()]);
            break;
          }
          else{
            spaces.add(board[row][start.getCol()]);
          }
        }
        
    	return spaces;
    }
  }