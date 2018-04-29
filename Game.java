//Cameron Clark
//CS 110
//Game class handles front end integration of the back end of the game of set.
import java.util.ArrayList;
public class Game
{
   //init vars
   private SetDeck d;
   private Board b;
   private BoardSquare[] selected;
   private int numSelected;
   
   /**
   The Default constructor creates a deck, shuffles it, and makes the board out of it.
   */
   public Game()
   {
      d = new SetDeck();
      d.shuffle();
      b = new Board(d);
      selected = new BoardSquare[10];
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
      numSelected++;
      selected[numSelected] = bSquare;
   }
   
   /**
   getSelected will return the BoardSquares with selected == true
   @returns ArrayList<BoardSquare> ArrayList containing the selected boardsquares
   *//*
   public BoardSquare[] getSelected()
   {
      return selected;
   }*/
   
   /**
   numSelected will search through each row and column for the number of selected BoardSquares
   @returns int Returns the number of selected BoardSquares
   */
   public int numSelected()
   {
      return numSelected;
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
      if (SetCard.shapeSet(selected[0].getCard(), selected[1].getCard(), selected[2].getCard()))
      {
         b.replaceCard(d.getTopCard(), selected[0].getRow(), selected[0].getCol());
         b.replaceCard(d.getTopCard(), selected[1].getRow(), selected[1].getCol());
         b.replaceCard(d.getTopCard(), selected[2].getRow(), selected[2].getCol());
      }
      else
      {
         selected[0].setSelected(false);
         selected[1].setSelected(false);
         selected[2].setSelected(false);
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