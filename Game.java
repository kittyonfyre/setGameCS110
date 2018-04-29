//Cameron Clark
//CS 110
//Game class handles front end integration of the back end of the game of set.
import java.util.ArrayList;
public class Game
{
   //init vars
   private SetDeck d;
   private Board b;
   private ArrayList<BoardSquare> selected;
   
   /**
   The Default constructor creates a deck, shuffles it, and makes the board out of it.
   */
   public Game()
   {
      d = new SetDeck();
      d.shuffle();
      b = new Board(d);
   }
   
   /**
   add3 will add one card to the end of each row.
   */
   public void add3()
   {
      b.add3(d);
   }
   
   /**
   addToSelected will set the BoardSquare at the input row and col to be selected.
   @param int row Row of card to select
   @param int col Column of card to select
   */
   public void addToSelected(int row, int col)
   {
      BoardSquare bSquare = b.getBoardSquare(row, col);
      bSquare.setSelected(true);
      selected.add(bSquare);
   }
   
   /**
   getSelected will return the BoardSquares with selected == true
   @returns ArrayList<BoardSquare> ArrayList containing the selected boardsquares
   */
   public ArrayList<BoardSquare> getSelected()
   {
      return selected;
   }
   
   /**
   numSelected will search through each row and column for the number of selected BoardSquares
   @returns int Returns the number of selected BoardSquares
   */
   public int numSelected()
   {
      int numberSelected = 0;
      for (int c = 0; c < b.numCols(); c++)
      {
         for (int r = 0; r < b.numRows(); r++)
         {
            if (b.getBoardSquare(r,c).getSelected())
            {
               numberSelected++;
            }
         }
      }
      return numberSelected;
   }
   
   /**
   determines if the deck is empty or not
   @returns boolean Returns whether the deck is out of cards or not.
   */
   public boolean outOfCards()
   {
      return d.isEmpty();
   }
   
   /**
   removeSelected will set the BoardSquare at row, col to selected == false
   @param int row Row of card to deselect
   @param int col Column of card to deselect
   */
   public void removeSelected(int row, int col)
   {
      b.getBoardSquare(row, col).setSelected(false);
   }
   
   /**
   testSelected will test to see if the three selected cards are a set or not.
   */
   public void testSelected()
   {
      BoardSquare[] selectedBoardSquaresArray = new BoardSquare[3];
      int numSelected = 0;
      for (int c = 0; c < b.numCols(); c++)
      {
         for (int r = 0; r < b.numRows(); r++)
         {
            if (b.getBoardSquare(r,c).getSelected())
            {
               selectedBoardSquaresArray[numSelected] = b.getBoardSquare(r,c);
               numSelected++;
            }
         }
      }
      BoardSquare card1 = selectedBoardSquaresArray[0];
      BoardSquare card2 = selectedBoardSquaresArray[1];
      BoardSquare card3 = selectedBoardSquaresArray[2];
      if (SetCard.shapeSet(card1.getCard(), card2.getCard(), card3.getCard()))
      {
         b.replaceCard(d.getTopCard(), card1.getRow(), card1.getCol());
         b.replaceCard(d.getTopCard(), card2.getRow(), card2.getCol());
         b.replaceCard(d.getTopCard(), card3.getRow(), card3.getCol());
      }
      else
      {
         card1.setSelected(false);
         card2.setSelected(false);
         card3.setSelected(false);
      }
   }
   
   /*
   numCardsLeft returns the number of cards in the deck as a string
   @returns count Number of cards left
   */
   public int numCardsLeft()
   {
      return d.numCardsLeft();
   }
   
   /*
   getBoard will return the board.
   @return the board object
   */
   public Board getBoard()
   {
      return b;
   }
   
   /**
   toString will return the board as a human readable string.
   @returns String string representation of the current game's board.
   */
   @Override
   public String toString()
   {
      return b.toString();
   }
}