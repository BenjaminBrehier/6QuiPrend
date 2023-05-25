package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;

public class Board {
    Pane board = new Pane();
    public static ArrayList<Card> choosenArray = new ArrayList<Card>();
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

    public void removeCardsEvent() {
        for (Card card : GameLogic.players.get(0).getHand()) {
            card.getGraphicCard().setOnMouseEntered(mouseEvent -> {
            });
            card.getGraphicCard().setOnMouseExited(mouseEvent -> {
            });
            card.getGraphicCard().setOnMouseClicked(mouseEvent -> {
            });
        }


        // for (int i = 4; i < ((VBox) board.getChildren().get(0)).getChildren().size(); i++) {
        //     HBox hBox = (HBox) ((VBox) board.getChildren().get(0)).getChildren().get(i);
        //     for (int j = 0; j < hBox.getChildren().size(); j++) {
        //         Pane pane = (Pane) hBox.getChildren().get(j);
        //         pane.setOnMouseEntered(mouseEvent -> {
        //         });
        //         pane.setOnMouseExited(mouseEvent -> {
        //         });
        //     }
        // }
    }

    public void addCardsEvent() {
        for (Card card : GameLogic.players.get(0).getHand()) {
            card.getGraphicCard().setOnMouseEntered(mouseEvent -> {
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.RED);
            });
            card.getGraphicCard().setOnMouseExited(mouseEvent -> {
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
            });
            card.getGraphicCard().setOnMouseClicked(mouseEvent -> {
                removeCardsEvent();
                System.out.println("Carte jouée : " + card);
                GameLogic.players.get(0).getHand().remove(card);
                GameLogic.cartesJouees.put(GameLogic.players.get(0), card);
                GameLogic.players.get(0).setHasChoose(true);
                // card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() + 10);
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
                GameLogic.jouer();
            });
        }
    }

    public void addLignesEvent() {
        for (int i = 0; i < ((VBox) board.getChildren().get(0)).getChildren().size() - 1; i++) {
            HBox hBox = (HBox) ((VBox) board.getChildren().get(0)).getChildren().get(i);
            hBox.setOnMouseEntered(mouseEvent -> {
                for (int j = 0; j < hBox.getChildren().size(); j++) {
                    ((Rectangle) ((Pane) hBox.getChildren().get(j)).getChildren().get(0)).setStroke(Color.RED);
                }
            });

            hBox.setOnMouseExited(mouseEvent -> {
                for (int j = 0; j < hBox.getChildren().size(); j++) {
                    ((Rectangle) ((Pane) hBox.getChildren().get(j)).getChildren().get(0)).setStroke(Color.BLACK);
                }
            });

            hBox.setOnMouseClicked(mouseEvent -> {
                removeLignesEvent();
                choosenArray = lignes.get(((VBox) board.getChildren().get(0)).getChildren().indexOf(hBox));
                System.out.println("Ligne choisie : " + choosenArray);
            });
        }
    }

    public void removeLignesEvent() {
        for (int i = 0; i < ((VBox) board.getChildren().get(0)).getChildren().size() - 1; i++) {
            HBox hBox = (HBox) ((VBox) board.getChildren().get(0)).getChildren().get(i);
            hBox.setOnMouseEntered(mouseEvent -> {
            });
            hBox.setOnMouseExited(mouseEvent -> {
            });
        }
    }

    public void initBoard() {
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
            lignesVBox.getChildren().add(vBox2);
        }

        HBox playerHand = new HBox();
        playerHand.setTranslateY(playerHand.getTranslateY() + 20);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setSpacing(10);

        for (Card card : GameLogic.players.get(0).getHand()) {
            playerHand.getChildren().add(card.getGraphicCard());
        }
        lignesVBox.getChildren().add(playerHand);

        lignesVBox.setPadding(new Insets(10, 0, 0, 10));
        board.getChildren().add(lignesVBox);
        board.setStyle("-fx-background-size: 1440 855; -fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");

        GridPane gridPane = new GridPane();
        gridPane.getChildren().clear();
        gridPane.setPrefWidth(640);
        gridPane.setPrefHeight(610);
        gridPane.setLayoutX(800);
        gridPane.setLayoutY(-100);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 0, 0, 10));

        int cptCol = 0;
        int cptLigne = 0;
        for (int i = 0; i < GameLogic.players.size(); i++) {
            VBox playerBox = new VBox();
            playerBox.setSpacing(10);
            playerBox.setAlignment(Pos.CENTER);

            Label label = new Label(GameLogic.players.get(i).getName());
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);

            playerBox.getChildren().add(label);
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setWidth(100);
            rectangle.setHeight(150);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(4);
            rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
            rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
            rectangle.setStrokeDashOffset(0);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            rectangle.setRotate(0);
            rectangle.setVisible(true);
            rectangle.toFront();

            playerBox.getChildren().add(rectangle);

            if (cptCol == 5) {
                cptCol = 0;
                gridPane.add(playerBox, cptCol++, ++cptLigne);
            } else {
                gridPane.add(playerBox, cptCol++, cptLigne);
            }
        }

        board.getChildren().add(gridPane);

        Pane points = new Pane();
        points.setLayoutX(1300);
        points.setLayoutY(670);
        points.setPrefWidth(100);
        points.setPrefHeight(100);
        board.getChildren().add(points);


        // On fait disparaitre les cartes les cartes ramassées par les autres joueurs
        for (int i = 1; i < GameLogic.players.size(); i++) {
            for (Card card : GameLogic.players.get(i).getPoints()) {
                card.getGraphicCard().setVisible(false);
            }
        }

        for (Card card : GameLogic.players.get(0).getPoints()) {
            card.getGraphicCard().setRotate(Math.random() * 30 - 10);
            card.getGraphicCard().setLayoutX(0);
            card.getGraphicCard().setLayoutY(0);
            card.getGraphicCard().toFront();
            points.getChildren().add(card.getGraphicCard());
        }

        addCardsEvent();
        Scene scene = new Scene(board, 1440, 855);
        GameLogic.stage.setScene(scene);
    }

    public void reloadBoard(boolean wait) {
        reloadLignes();

        board.setStyle("-fx-background-size: 1440 855; -fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");

        reloadPanel(false);

        Pane points = new Pane();
        points.setLayoutX(1300);
        points.setLayoutY(670);
        points.setPrefWidth(100);
        points.setPrefHeight(100);
        board.getChildren().add(points);


        // On fait disparaitre les cartes les cartes ramassées par les autres joueurs
        for (int i = 1; i < GameLogic.players.size(); i++) {
            for (Card card : GameLogic.players.get(i).getPoints()) {
                card.getGraphicCard().setVisible(false);
            }
        }

        for (Card card : GameLogic.players.get(0).getPoints()) {
            card.getGraphicCard().setRotate(Math.random() * 30 - 10);
            card.getGraphicCard().setLayoutX(0);
            card.getGraphicCard().setLayoutY(0);
            card.getGraphicCard().toFront();
            points.getChildren().add(card.getGraphicCard());
        }

        addCardsEvent();
    }

    public void reloadLignes() {
        VBox lignesVBox = (VBox) board.getChildren().get(0);
        lignesVBox.getChildren().clear();
        
        lignesVBox.setLayoutX(0);
        lignesVBox.setLayoutY(0);
        lignesVBox.setSpacing(10);
        for (int i = 0; i < lignes.size(); i++) {
            HBox vBox2 = new HBox();
            vBox2.setSpacing(10);
            for (int j = 0; j < lignes.get(i).size(); j++) {
                vBox2.getChildren().add(lignes.get(i).get(j).getGraphicCard());
            }
            lignesVBox.getChildren().add(vBox2);
        }

        HBox playerHand = new HBox();
        playerHand.setTranslateY(playerHand.getTranslateY() + 20);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setSpacing(10);

        for (Card card : GameLogic.players.get(0).getHand()) {
            playerHand.getChildren().add(card.getGraphicCard());
        }
        lignesVBox.getChildren().add(playerHand);

        lignesVBox.setPadding(new Insets(10, 0, 0, 10));
    }

    public void reloadPanel(boolean showCard) {
        GridPane gridPane = (GridPane) board.getChildren().get(1);
        gridPane.getChildren().clear();
        gridPane.setPrefWidth(640);
        gridPane.setPrefHeight(610);
        gridPane.setLayoutX(800);
        gridPane.setLayoutY(-100);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 0, 0, 10));

        int cptCol = 0;
        int cptLigne = 0;
        for (int i = 0; i < GameLogic.players.size(); i++) {
            VBox playerBox = new VBox();
            playerBox.setSpacing(10);
            playerBox.setAlignment(Pos.CENTER);

            Label label = new Label(GameLogic.players.get(i).getName());
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);

            playerBox.getChildren().add(label);
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setWidth(100);
            rectangle.setHeight(150);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(4);
            rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
            rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
            rectangle.setStrokeDashOffset(0);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            rectangle.setRotate(0);
            rectangle.setVisible(true);
            rectangle.toFront();

            if (GameLogic.cartesJouees.containsKey(GameLogic.players.get(i))) {
                if (showCard) {
                    playerBox.getChildren().add(GameLogic.cartesJouees.get(GameLogic.players.get(i)).getGraphicCard());
                } else {
                    playerBox.getChildren().add(GameLogic.cartesJouees.get(GameLogic.players.get(i)).getBackCard());
                }
            } else {
                playerBox.getChildren().add(rectangle);
            }

            if (cptCol == 5) {
                cptCol = 0;
                gridPane.add(playerBox, cptCol++, ++cptLigne);
            } else {
                gridPane.add(playerBox, cptCol++, cptLigne);
            }
        }
    }



    public void removeCard(Card card) {
        board.getChildren().remove(card.getGraphicCard());
    }

    public void removeCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            removeCard(card);
        }
    }
}
