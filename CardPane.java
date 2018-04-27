//Cameron Clark
//CS110
//CardPane.java, visualizes one square in the game
import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardPane extends StackPane
{
   private BoardSquare bSquare;
   private int row, col;
   private boolean selected;
   
   public CardPane(BoardSqare bSquare, int row, int col)
   {
      this.bSquare = bSquare;
      this.col = col;
      this.row = row;
      this.selected = false;
   }
   
}