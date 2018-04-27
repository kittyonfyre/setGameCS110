//Cameron Clark
//CS110
//SetDeck creates a deck from the game Set. 
import java.util.Random;
public class SetDeck
{
   //declare vars
   private int count;
   private SetCard deck[];
   public final static int DECK_CARD_COUNT = 81;
   
   /*
   The Default constructor will populate the deck with one of every kind of card.
   */
   public SetDeck()
   {
      deck = new SetCard[DECK_CARD_COUNT];
      count = 0;
      for (SetCard.Shape s : SetCard.Shape.values())
      {
         for (SetCard.Number n : SetCard.Number.values())
         {
            for (SetCard.Color c : SetCard.Color.values())
            {
               for (SetCard.Shade d : SetCard.Shade.values())
               {
                  deck[count]=new SetCard(s, n, c, d);
                  count++;
               }
            }
         }
      }
   }
   
   /*
   shuffle will mix the order of the cards in the deck.
   */
   public void shuffle()
   {
      int randNum;
      SetCard temp;
      Random r = new Random();
      for (int i = 0; i < DECK_CARD_COUNT; i++)
      {
         randNum = r.nextInt(DECK_CARD_COUNT);
         temp = deck[i];
         deck[i] = deck[randNum];
         deck[randNum] = temp;
      }
   }
   
   /*
   toString will return a human-readable form of the deck.
   @returns returnedString String that contains all of the information about the deck.
   */
   @Override
   public String toString()
   {
      String returnedString = "";
      for (SetCard card : deck)
      {
         returnedString += card.toString() + " ";
      }
      return returnedString;
   }
   
   /*
   getTopCard will look at the top card in the deck, then will decrement the count by one so that it does not get looked at again.
   @returns deck[count] Returns the card on the top of the deck.
   */
   public SetCard getTopCard()
   {
      count--;
      return deck[count];
   }
   
   /*
   isEmpty checks if the count of the deck is zero, which would mean the deck has no cards not dealt.
   @returns boolean true if deck is empty.
   */
   public boolean isEmpty()
   {
      return (count == 0);
   }
   
   /*
   numCardsLeft returns the number of cards in the deck as a string
   @returns count Number of cards left
   */
   public String numCardsLeft()
   {
      return Integer.toString(count);
   }
}