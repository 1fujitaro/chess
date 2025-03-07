
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
    public ArrayList<Square> getControlledSquares(Board b, Square start) {
      ArrayList<Square> controlledSquares = new ArrayList<>();
    Square[][] squares = b.getSquareArray();
    int startX = start.getX();
    int startY = start.getY();

    int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    for (int[] dir : directions) {
        int x = startX;
        int y = startY;

        while (true) {
            x += dir[0];
            y += dir[1];

            if (x < 0 || x >= squares.length || y < 0 || y >= squares[0].length) {
                break; // makes sure piece cannot move off the board
            }

            Square nextSquare = squares[x][y];
            controlledSquares.add(nextSquare);

            if (nextSquare.isOccupied() == true) {
                break; // stop at first piece
            }
        }
    }
    return controlledSquares;

    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //the "spy" is a piece that appears as a pawn, but can move like a rook.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> moves = new ArrayList<>();
        for(int i = 0; i < 8; i++){
          for(int j = 0; j < 8; j++){
            if(start.isOccupied() == false){
              moves.add((b.getSquareArray()[start.getRow()][start.getCol()]));
            }


          }
        }
      
      return moves;
  }
}