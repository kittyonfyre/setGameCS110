//Cameron Clark
//CS110
//VBoxCardGUI.java, visualizes one square in the game
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Stop;

public class VBoxCardGUI extends VBox
{
   //init class vars
   private BoardSquare bSquare;
   private int row, col;
   
   public VBoxCardGUI(BoardSquare bSquare)
   {
      //construct method vars
      super();
      this.bSquare = bSquare;
      this.col = bSquare.getCol();
      this.row = bSquare.getRow();
      
      //init method vars
      String[] boardSquareCardData;
      Color cardColor;
      int cardFill, shapeNumber;
      Node shape;
      
      //this will extract all the information from the card inside the boardsqaure,
      //for use when drawing the card.
      boardSquareCardData = bSquare.getCard().toString().split("_");
      
      //construct the VBox representing cards
      switch (boardSquareCardData[1])
      {
         case "ONE":       shapeNumber = 1; break;
         case "TWO":       shapeNumber = 2; break;
         case "THREE":     shapeNumber = 3; break;
         default:          shapeNumber = 1; break;
      }
      switch (boardSquareCardData[2])
      {
         case "RED":       cardColor = Color.FIREBRICK; break;
         case "BLUE":      cardColor = Color.DARKCYAN; break;
         case "GREEN":     cardColor = Color.DARKGREEN; break;
         default:          cardColor = Color.BLACK; break;
      }
      switch (boardSquareCardData[3])
      {
         case "STRIPED":   cardFill = 1; break;
         case "SOLID":     cardFill = 0; break;
         case "OUTLINED":  cardFill = 2; break;
         default:          cardFill = 0; break;
      }
      for (int i = 1; i <= shapeNumber; i++)
      {
         switch (boardSquareCardData[0])
         {
            case "OVALS":     shape = createEllipse(cardColor, cardFill); break;
            case "DIAMONDS":  shape = createDiamond(cardColor, cardFill); break;
            case "SQUIGGLES": shape = createCurve(cardColor,   cardFill); break;
            default:          shape = createEllipse(cardColor, cardFill); break;
         }
      this.getChildren().add(shape);
      
      //basic styling of the card we just made
      this.setAlignment(Pos.CENTER);
      this.setStyle("-fx-background-color: lightgray;" + "-fx-border-width: 4;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
      this.setPadding(new Insets(5));
      this.setMinSize(145, 220);
      }
   }
   
   //returns the row position of the boardsquare represented by this VBox
   public int row()
   {
      return row;
   }
   //returns the col position of the boardsquare represented by this VBox
   public int col()
   {
      return col;
   }
   
   //returns the boardsquare represented by this VBox
   public BoardSquare boardSquare()
   {
      return bSquare;
   }
   
   //createCurve will create and return a curve for use on the cards
   public CubicCurve createCurve(Color chosenColor, int fillType)
   {
      CubicCurve curve = new CubicCurve();
      curve.setStartX(0);
      curve.setStartY(50);
      curve.setControlX1(50);
      curve.setControlY1(0);
      curve.setControlX2(50);
      curve.setControlY2(100);
      curve.setEndX(100);
      curve.setEndY(50);
      curve.setStrokeWidth(25);
      curve.setStrokeLineCap(StrokeLineCap.ROUND);
      curve.setFill(null);
      //this section determines fill type - solid by default, otherwise it is hatched or hollow
      if (fillType == 0)
      {
         curve.setStroke(chosenColor);
      }
      if (fillType == 1)
      {
         curve.setStrokeWidth(20);
         //if you dont think this dropshadow stuff is genius you're a peroni.
         DropShadow curveOutline = new DropShadow(BlurType.GAUSSIAN, chosenColor, 5 /*radius*/, Double.MAX_VALUE/*spread*/, 0/*offsetx*/, 0/*offsety*/);
         curve.setStroke(hatchColorize(chosenColor));
         curve.setEffect(curveOutline);
      }
      if (fillType == 2)
      {
         curve.setStrokeWidth(20);
         curve.setStroke(Color.WHITE);
         //if you dont think this dropshadow stuff is genius you're a peroni.
         DropShadow curveOutline = new DropShadow(BlurType.GAUSSIAN, chosenColor, 5 /*radius*/, Double.MAX_VALUE/*spread*/, 0/*offsetx*/, 0/*offsety*/);
         curve.setEffect(curveOutline);
      }
      return curve;
   }
   
   //createEllipse will create and return a curve for use on cards
   public Ellipse createEllipse(Color chosenColor, int fillType)
   {
      Ellipse ellipse = new Ellipse(25, 25, 50, 25);
      ellipse.setFill(chosenColor);
      ellipse.setStrokeWidth(5);
      ellipse.setStroke(chosenColor);
      //this section determines fill type - solid by default, otherwise it is hatched or hollow
      if (fillType == 1)
      {
         ellipse.setFill(hatchColorize(chosenColor));
      }
      if (fillType == 2)
      {
         ellipse.setFill(null);
      }
      return ellipse;
   }
   
   //createDiamond will create and return a curve for use on cards
   public Polygon createDiamond(Color chosenColor, int fillType)
   {
      Polygon diamond = new Polygon();
      diamond.getPoints().addAll(new Double[]{
         0.0, 25.0,
         50.0, 0.0,
         100.0, 25.0,
         50.0, 50.0});
      diamond.setFill(chosenColor);
      diamond.setStrokeWidth(5);
      diamond.setStroke(chosenColor);
      //this section determines fill type - solid by default, otherwise it is hatched or hollow
      if (fillType == 1)
      {
         diamond.setFill(hatchColorize(chosenColor));
      }
      if (fillType == 2)
      {
         diamond.setFill(null);
      }
      return diamond;
   }
   
   //hatchColorize will output a LinearGradient object used to fill in the hatch pattern on the card symbols.
   //I did this rather than colorize the given hatch.png because it allowed for greater control.
   public LinearGradient hatchColorize(Color hatchColor)
   {
      Stop stop1 = new Stop(.4, hatchColor);
      Stop stop2 = new Stop(.6, Color.WHITE);
      LinearGradient hatchGradient = new LinearGradient(0, 0, 3, 3, false, CycleMethod.REFLECT, stop1, stop2);
      return hatchGradient;
   }
   
}