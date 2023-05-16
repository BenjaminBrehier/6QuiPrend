package fr.benjaminbrehier._6quiprend.Model;

import java.io.File;
import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
            //Events on cards
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



        // HBox hBox = (HBox) ((VBox) board.getChildren().get(0)).getChildren().get(4);
        // for (int j = 0; j < hBox.getChildren().size(); j++) {
        //     Pane pane = (Pane) hBox.getChildren().get(j);
        //     pane.setOnMouseEntered(mouseEvent -> {
        //         ((Rectangle) pane.getChildren().get(0)).setStroke(Color.RED);
        //     });

        //     pane.setOnMouseExited(mouseEvent -> {
        //         ((Rectangle) pane.getChildren().get(0)).setStroke(Color.BLACK);
        //     });

        //     Card card = GameLogic.players.get(0).getHand().get(j);
        //     pane.setOnMouseClicked(mouseEvent -> {
        //         removeCardsEvent();
        //         System.out.println("Carte jouée : " + card);
        //         GameLogic.players.get(0).getHand().remove(card);
        //         GameLogic.cartesJouees.put(GameLogic.players.get(0), card);
        //         GameLogic.players.get(0).setHasChoose(true);
        //         // card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() + 10);
        //         ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
        //         GameLogic.jouer();
        //     });
        // }
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
        reloadBoard();
        Scene scene = new Scene(board, 1440, 855);
        GameLogic.stage.setScene(scene);
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
            lignesVBox.getChildren().add(vBox2);
        }

        HBox playerHand = new HBox();
        playerHand.setTranslateY(playerHand.getTranslateY() + 20);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setSpacing(10);

        for (Card card : GameLogic.players.get(0).getHand()) {
            playerHand.getChildren().add(card.getGraphicCard());
            // card.getGraphicCard().setOnMouseEntered(mouseEvent -> {
            // // card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY()
            // - 10);
            // ((Rectangle)
            // card.getGraphicCard().getChildren().get(0)).setStroke(Color.RED);
            // });

            // card.getGraphicCard().setOnMouseExited(mouseEvent -> {
            // // card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY()
            // + 10);
            // ((Rectangle)
            // card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
            // });

            // // Remove events on click
            // card.getGraphicCard().setOnMouseClicked(mouseEvent -> {
            // System.out.println("Carte jouée : " + card);
            // GameLogic.players.get(0).getHand().remove(card);
            // GameLogic.cartesJouees.put(GameLogic.players.get(0), card);
            // GameLogic.players.get(0).setHasChoose(true);
            // // card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY()
            // + 10);
            // ((Rectangle)
            // card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
            // card.getGraphicCard().setOnMouseEntered(null);
            // card.getGraphicCard().setOnMouseExited(null);
            // card.getGraphicCard().setOnMouseClicked(null);
            // GameLogic.jouer();
            // });
        }
        lignesVBox.getChildren().add(playerHand);

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

        Pane points = new Pane();
        points.setLayoutX(1300);
        points.setLayoutY(670);
        points.setPrefWidth(100);
        points.setPrefHeight(100);
        board.getChildren().add(points);

        for (Card card : GameLogic.players.get(0).getPoints()) {
            // Random rotate between -10 and 10
            card.getGraphicCard().setRotate(Math.random() * 30 - 10);
            card.getGraphicCard().setLayoutX(0);
            card.getGraphicCard().setLayoutY(0);
            card.getGraphicCard().toFront();
            points.getChildren().add(card.getGraphicCard());
            // board.getChildren().add(card.getGraphicCard());
        }

        addCardsEvent();
    }
}
