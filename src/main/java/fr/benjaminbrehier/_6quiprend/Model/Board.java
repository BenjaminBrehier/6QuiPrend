package fr.benjaminbrehier._6quiprend.Model;

import java.io.File;
import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    private ArrayList<ArrayList<Card>> lignes;

    public Board(ArrayList<ArrayList<Card>> lignes) {
        this.lignes = lignes;
    }

    public ArrayList<ArrayList<Card>> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<ArrayList<Card>> lignes) {
        this.lignes = lignes;
    }

    @Override
    public String toString() {
        return "Board{" +
                "lignes=" + lignes +
                '}';
    }

    public void reloadBoard() {
        VBox lignesVBox = new VBox();
        lignesVBox.setLayoutX(0);
        lignesVBox.setLayoutY(0);
        lignesVBox.setSpacing(10);
        for (int i = 0; i < lignes.size(); i++) {
            HBox vBox2 = new HBox();
            vBox2.setSpacing(10);
            for (int j = 0; j < lignes.get(i).size(); j++) {
                vBox2.getChildren().add(lignes.get(i).get(j).getGraphicCard());
            }
            vBox2.setOnMouseEntered(mouseEvent -> {
                for (int j = 0; j < vBox2.getChildren().size(); j++) {
                    ((Rectangle) ((Pane) vBox2.getChildren().get(j)).getChildren().get(0)).setStroke(Color.RED);
                }
            });

            vBox2.setOnMouseExited(mouseEvent -> {
                for (int j = 0; j < vBox2.getChildren().size(); j++) {
                    ((Rectangle) ((Pane) vBox2.getChildren().get(j)).getChildren().get(0)).setStroke(Color.BLACK);
                }
            });
            lignesVBox.getChildren().add(vBox2);
        }

        HBox playerHand = new HBox();
        playerHand.setTranslateY(playerHand.getTranslateY() + 20);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setSpacing(10);

        for (Card card : GameLogic.players.get(0).getHand()) {
            playerHand.getChildren().add(card.getGraphicCard());
            card.getGraphicCard().setOnMouseEntered(mouseEvent -> {
                card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() - 10);
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.RED);
            });

            card.getGraphicCard().setOnMouseExited(mouseEvent -> {
                card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() + 10);
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
            });

            // // Remove events on click
            // card.getGraphicCard().setOnMouseClicked(mouseEvent -> {
            // card.getGraphicCard().setOnMouseEntered(null);
            // card.getGraphicCard().setOnMouseExited(null);
            // card.getGraphicCard().setOnMouseClicked(null);
            // });
        }
        lignesVBox.getChildren().add(playerHand);

        Pane board = new Pane();
        lignesVBox.setPadding(new Insets(10, 0, 0, 10));
        board.getChildren().add(lignesVBox);
        board.setStyle(
                "-fx-background-size: 1440 855; -fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");

        Pane playersPanel = new Pane();
        playersPanel.setPrefWidth(440);
        playersPanel.setPrefHeight(610);
        playersPanel.setLayoutX(1150);
        playersPanel.setLayoutY(20);
        playersPanel.setStyle(
                "-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 0, 0, 10));
        for (int i = 0; i < GameLogic.players.size(); i++) {

            Label label = new Label(GameLogic.players.get(i).getName());
            label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            ImageView icon = new ImageView(new Image(
                    new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/perso.png").toURI().toString()));
            icon.setFitHeight(50);
            icon.setFitWidth(50);
            icon.setPreserveRatio(true);

            // Placer l'icon à gauche du label, s'arranger pour que ce soit aligné à gauche
            gridPane.add(icon, 0, i);
            gridPane.add(label, 1, i);
        }

        playersPanel.getChildren().add(gridPane);
        board.getChildren().add(playersPanel);


        for (Card card : GameLogic.players.get(0).getPoints()) {
            card.getGraphicCard().setTranslateX(1300);
            card.getGraphicCard().setTranslateY(670);

            // Random rotate between -10 and 10
            card.getGraphicCard().setRotate(Math.random() * 20 - 10);
            card.getGraphicCard().toFront();
            board.getChildren().add(card.getGraphicCard());
        }

        Scene scene = new Scene(board, 1440, 855);

        GameLogic.stage.setScene(scene);
    }
}
