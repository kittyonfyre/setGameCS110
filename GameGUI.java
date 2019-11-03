//Cameron Clark
//CS110
//gameGUI class. just puts a visual interface onto game.java for a game of set.

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameGUI extends Application {
    //init class vars
    private Game g;
    private GridPane cardGrid;
    private BorderPane entireWindow;
    private VBox leftSidePanel;
    private Text cardCount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Set");
        entireWindow = new BorderPane();
        g = new Game();

        cardGrid = new GridPane();
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(10);
        cardGrid.setVgap(10);
        cardGrid.setPadding(new Insets(25, 25, 25, 25));

        //adds a panel on the left side with buttons and a card remaining counter
        leftSidePanel = new VBox();
        leftSidePanel.setPadding(new Insets(10));
        leftSidePanel.setSpacing(8);

        //cards remaining counter: text plus the counter
        HBox cardsRemainingCounter = new HBox();
        Text cardsText = new Text("Cards Remaining: ");
        cardsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        cardsRemainingCounter.getChildren().add(cardsText);

        //number of cards left itself
        cardCount = new Text(Integer.toString(g.numCardsLeft()));
        cardsRemainingCounter.getChildren().add(cardCount);

        //buttons that will be added to the left side panel
        Button quitButton = new Button("Quit");
        Button newGameButton = new Button("New Game");
        Button add3Button = new Button("Add 3 Cards");

        add3Button.setOnAction(this::handleAdd3);
        newGameButton.setOnAction(this::handleNewGame);
        quitButton.setOnAction(this::handleQuit);

        leftSidePanel.getChildren().addAll(cardsRemainingCounter, quitButton, newGameButton, add3Button);

        //sets info/buttons to the left side, and card grids to the right.
        entireWindow.setLeft(leftSidePanel);
        entireWindow.setCenter(cardGrid);

        drawCards();

        //generic scene stuff that needs to happen
        Scene scene = new Scene(entireWindow);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //drawCards will draw or redraw the cardGrid, which contains all the playing cards.
    public void drawCards() {
        //clears current board
        cardGrid.getChildren().clear();

        //draws the card grid
        Board b = g.getBoard();
        for (int r = 0; r < b.numRows(); r++) {
            for (int c = 0; c < b.numCols(); c++) {
                VBoxCardGUI cardVBox = new VBoxCardGUI(b.getBoardSquare(r, c));
                cardVBox.setOnMouseClicked(this::handleSelectCardPane);
                cardGrid.add(cardVBox, c, r);
            }
        }
    }

    //handles add3 button
    private void handleAdd3(ActionEvent e) {
        int cardsLeft = g.numCardsLeft();
        if (cardGrid.getChildren().size() < 18 && cardsLeft >= 3) {
            g.add3();
            this.drawCards();
            cardCount.setText(Integer.toString(g.numCardsLeft()));
        }
    }

    //handles quitting the game
    private void handleQuit(ActionEvent e) {
        Platform.exit();
    }

    //handles starting a new game
    private void handleNewGame(ActionEvent e) {
        g = new Game();
        this.drawCards();
        cardCount.setText(Integer.toString(g.numCardsLeft()));
    }

    //handles when a card is clicked on
    private void handleSelectCardPane(MouseEvent e) {
        VBoxCardGUI cardVBox = (VBoxCardGUI) e.getSource();
        if (cardVBox.boardSquare().getSelected()) {
            cardVBox.setStyle("-fx-background-color: lightgray;" + "-fx-border-width: 4;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
            g.removeSelected(cardVBox.row(), cardVBox.col());
        } else if (!cardVBox.boardSquare().getSelected()) {
            cardVBox.setStyle("-fx-background-color: darkgray;" + "-fx-border-width: 3;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
            g.addToSelected(cardVBox.row(), cardVBox.col());
        }

        if (g.numSelected() == 3) {
            g.testSelected();
            drawCards();
        }
        cardCount.setText(Integer.toString(g.numCardsLeft()));
    }
}