//Cameron Clark
//CS110
//gameGUI class. just puts a visual interface onto game.java for a game of set.
import javafx.application.Application; 
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameGUI extends Application
{
   public static void main(String [] args) {
      launch(args);
   }
   @Override
   public void start(Stage primaryStage)
   {
      //yeah... listen, I understand that this is an absolute terrible design choice.
      //But I am out of time. So I do what I must. I did this because I couldn't access
      //the BoardSquare class from handleSelectCard.
      
      //***desperate hack, here be dragons***
      Dragons.g = new Game();
      
      primaryStage.setTitle("Game of Set");
      BorderPane entireBoardPane = new BorderPane();
      
      GridPane cardGrid = new GridPane();
      cardGrid.setAlignment(Pos.CENTER);
      cardGrid.setHgap(10);
      cardGrid.setVgap(10);
      cardGrid.setPadding(new Insets(25, 25, 25, 25));
      
      VBox leftSidePanelVBox = new VBox();
      leftSidePanelVBox = leftSidePanel(Dragons.g.numCardsLeft());
      
      //sets info/buttons to the left side, and card grids to the right.
      entireBoardPane.setLeft(leftSidePanelVBox);
      entireBoardPane.setCenter(cardGrid);
      
      drawCards(cardGrid);
      
      //generic scene stuff that needs to happen
      Scene scene = new Scene(entireBoardPane);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   public GridPane drawCards(GridPane cardGrid)
   {
      cardGrid.getChildren().clear();
      
      Board b = Dragons.g.getBoard();
      for (int r = 0; r < b.numRows(); r++)
      {
         for (int c = 0; c < b.numCols(); c++)
         {
            VBox cardVBox = cardPaneCreator(b.getBoardSquare(r, c));
            cardVBox.setOnMouseClicked(this::handleSelectCardPane);
            cardGrid.add(cardVBox, c, r);
         }
      }
      return cardGrid;
   }
   
   public VBox leftSidePanel(String numCardsLeft)
   {
      VBox leftSidePanelVBox = new VBox();
      leftSidePanelVBox.setPadding(new Insets(10));
      leftSidePanelVBox.setSpacing(8);
      
      //cards remaining counter: text plus the counter
      HBox cardsRemainingCounter = new HBox();
      Text cardsText = new Text("Cards Remaining: ");
      cardsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      cardsRemainingCounter.getChildren().add(cardsText);
      
      //number of cards left itself
      Text cardCount = new Text(numCardsLeft);
      cardsRemainingCounter.getChildren().add(cardCount);
      
      //add the HBox that holds cards remaining to the left side info
      leftSidePanelVBox.getChildren().add(cardsRemainingCounter);
      return leftSidePanelVBox;
   }
   
   public VBox cardPaneCreator(BoardSquare bs)
   {
      String[] boardSquareCardData = bs.getCard().toString().split("_");
      //shape number color shade
      VBox cardVBox = new VBox(10);
      Color cardColor;
      int cardFill, shapeNumber;
      Node shape;
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
      switch (boardSquareCardData[1])
      {
         case "ONE":       shapeNumber = 1; break;
         case "TWO":       shapeNumber = 2; break;
         case "THREE":     shapeNumber = 3; break;
         default:          shapeNumber = 1; break;
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
         cardVBox.getChildren().add(shape);
      }
      cardVBox.setAlignment(Pos.CENTER);
      cardVBox.setStyle("-fx-background-color: lightgray;" + "-fx-border-width: 4;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
      cardVBox.setPadding(new Insets(5));
      cardVBox.setMinSize(145, 220);
      return cardVBox;
   }
   private void handleSelectCardPane(MouseEvent e)
   {
      VBox cardVBox = (VBox) e.getSource();
      int gridCol = GridPane.getColumnIndex(cardVBox).intValue();
      int gridRow = GridPane.getRowIndex(cardVBox).intValue();
      //this is why i "needed" to (essentially) set Game g to a global var.
      //this is where there be dragons.
      if (!Dragons.g.getBoard().getBoardSquare(gridRow, gridCol).getSelected())
      {
         Dragons.g.getBoard().getBoardSquare(gridRow, gridCol).setSelected(true);
         cardVBox.setStyle("-fx-background-color: darkblue;" + "-fx-border-width: 3;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
         Dragons.g.addToSelected(gridRow, gridCol);
      }
      
      else if (Dragons.g.getBoard().getBoardSquare(gridRow, gridCol).getSelected())
      {
         cardVBox.setStyle("-fx-background-color: lightgray;" + "-fx-border-width: 4;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
         Dragons.g.removeSelected(gridRow, gridCol);
      }
      
      if (Dragons.g.numSelected()==3)
      {
         Dragons.g.testSelected();
      }
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
   //I did this rather than colorize the given hatch.png because it allowed greater control over colors.
   public LinearGradient hatchColorize(Color hatchColor)
   {
      Stop stop1 = new Stop(.4, hatchColor);
      Stop stop2 = new Stop(.6, Color.WHITE);
      LinearGradient hatchGradient = new LinearGradient(0, 0, 3, 3, false, CycleMethod.REFLECT, stop1, stop2);
      return hatchGradient;
   }
}