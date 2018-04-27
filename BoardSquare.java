//Cameron Clark
//CS110
//BoardSquare is one "Square" on the board Set, which contains a card and a position identifier.
public class BoardSquare
{
   private SetCard card;
   private int col;
   private int row;
   private boolean selected;
   
   /**
   The Default constructor will take the input card, row, and column and set them as the col and row of that card.
   It also sets the "selected" boolean to false by default.
   */
   public BoardSquare(SetCard card, int row, int col)
   {
      this.card = card;
      this.col = col;
      this.row = row;
      this.selected = false;
   }
   
   /**
   getCard will return the value of card
   @returns SetCard returns the card 
   */
   public SetCard getCard()
   {
      return card;
   }
   
   /**
   getCol will return the value of col
   @returns Int returns the col 
   */
   public int getCol()
   {
      return col;
   }
   
   /**
   getRow will return the value of row
   @returns Int returns the row
   */
   public int getRow()
   {
      return row;
   }
   
   /**
   setCard will set the value of card
   @param SetCard holds the SetCard that sets card
   */
   public void setCard(SetCard card)
   {
      this.card = card;
   }
   
   /**
   setCol will set the value of col
   @param int holds the SetCol that sets col
   */
   public void setCol(int col)
   {
      this.col = col;
   }
   
   /**
   setRow will set the value of row
   @param int holds the int that sets row
   */
   public void setRow(int row)
   {
      this.row = row;
   }
   
   /**
   setSelected will set the value of selected
   @param boolean holds the boolean that sets selected
   */
   public void setSelected(boolean select)
   {
      this.selected = select;
   }
   
   /**
   getSelected will return the value of Selected
   @returns boolean returns Selected
   */
   public boolean getSelected()
   {
      return selected;
   }
   
   /**
   toString returns the card in a human readable form.
   @returns String returns the card in a human readable form.
   */
   @Override
   public String toString()
   {
      return this.card.toString();
   }
}