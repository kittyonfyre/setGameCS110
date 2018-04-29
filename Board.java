//Cameron Clark
//CS110
//Board.java is a representation of the board of a game of Set, with 2d arraylists to represent the board.
import java.util.ArrayList;
public class Board
{
   ArrayList<ArrayList<BoardSquare>> board = new ArrayList<ArrayList<BoardSquare>>();
   public final static int ROW_COUNT = 3;
   int colCount = 0;
   int rowCount = 0;
   
   //The Default constructor will populate a 3x4 grid with cards from the SetDeck class.
   public Board(SetDeck deck)
   {
      board = new ArrayList<>(3);
      for (int row = 0; row < ROW_COUNT; row++)
      {
         ArrayList<BoardSquare> thisRow = new ArrayList<>(4);
         for (int col = 0; col < 4; col++)
         {
            BoardSquare bSquare = new BoardSquare(deck.getTopCard(), row, col);
            thisRow.add(bSquare);
         }
         board.add(thisRow);
      }
   }
   
   /*
   add3 will add one card to the end of each row.
   @param d Deck used to deal cards.
   */
   public void add3(SetDeck d)
   {
      for (ArrayList<BoardSquare> boardSquareArray : board)
      {
         boardSquareArray.add(new BoardSquare(d.getTopCard(), colCount, rowCount));
      }
   }
   
   /*
   getBoardSquare will return the boardsquare at the specified coordinates.
   @returns BoardSquare Returns the boardsquare at the specificed coordinates
   @param int row The row in which the boardsquare will be found.
   @param int col The row in which the boardsquare will be found.
   */
   public BoardSquare getBoardSquare(int row, int col)
   {
      return board.get(row).get(col);
   }
   
   /*
   getCard will return the card at the specified coordinates.
   @returns SetCard Returns the card at the specificed coordinates
   @param int row The row in which the boardsquare will be found.
   @param int col The row in which the boardsquare will be found.
   */
   public SetCard getCard(int row, int col)
   {
      return board.get(row).get(col).getCard();
   }

   /*
   numcols will return the number of vertical columns of the board.
   @returns int Returns the number of columns
   */
   public int numCols()
   {
      return board.get(0).size();
   }
   
   /*
   numrows returns the number of horizontal rows of the board.
   @returns int returns the number of rows
   */
   public int numRows()
   {
      return board.size();
   }
   
   /*
   replaceCard replaces the card at the specified coordinates with the one input.
   @param SetCard card that will replace the current one.
   @param int row The row in which the new card will be found.
   @param int col The row in which the new card will be found.
   */
   public void replaceCard(SetCard card, int row, int col)
   {
      BoardSquare bSquareTemp = new BoardSquare(card, row, col);
      board.get(row);
      board.get(col);
      board.get(row).set(col, bSquareTemp);
   }
   
   /*
   toString returns the board in a human readable form.
   @returns String returns the board in a human readable form.
   */
   @Override
   public String toString()
   {
      String returnString = "";
      for (ArrayList<BoardSquare> BoardSquareArray : board)
      {
         for (BoardSquare boardSquare : BoardSquareArray)
         {
            returnString += boardSquare.toString() + "    ";
         }
         returnString += "\n";
      }
      return returnString;
   }
}