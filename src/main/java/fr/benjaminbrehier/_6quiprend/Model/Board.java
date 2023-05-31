package fr.benjaminbrehier._6quiprend.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Board {
    Pane board = new Pane();
    public static ArrayList<Card> choosenArray = new ArrayList<Card>();
    private ArrayList<ArrayList<Card>> lignes;
    private Scene scene;
    private ArrayList<String> actions = new ArrayList<String>();

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
                GameLogic.musicFlipCard();
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
            for (int j = 0; j < 6; j++) {
                if (lignes.get(i).size() > j && lignes.get(i).get(j) != null) {
                    vBox2.getChildren().add(lignes.get(i).get(j).getGraphicCard());
                } else {
                    Pane pane = new Pane();
                    Rectangle emplacement = new Rectangle();
                    emplacement.setFill(Color.TRANSPARENT);
                    emplacement.setWidth(100);
                    emplacement.setHeight(150);
                    emplacement.setStroke(Color.BLACK);
                    emplacement.setOpacity(0.5);
                    emplacement.setStrokeWidth(4);
                    emplacement.setStrokeLineCap(StrokeLineCap.ROUND);
                    emplacement.setStrokeLineJoin(StrokeLineJoin.ROUND);
                    emplacement.setStrokeDashOffset(0);
                    emplacement.setArcWidth(20);
                    emplacement.setArcHeight(20);
                    emplacement.setRotate(0);
                    emplacement.setVisible(true);
                    emplacement.toFront();

                    pane.getChildren().add(emplacement);

                    if (j == 5) {
                        ImageView algo = new ImageView();
                        algo.setImage(new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxRouge.png").toURI().toString()));
                        algo.setFitWidth(90);
                        algo.setFitHeight(80);
                        algo.setLayoutX(5);
                        algo.setLayoutY(35);
                        algo.setVisible(true);
                        algo.toFront();
    
                        pane.getChildren().add(algo);
                    }

                    pane.setPrefWidth(100);
                    pane.setPrefHeight(150);
                    vBox2.getChildren().add(pane);
                }
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
        board.setStyle("-fx-background-size: 1440 950; -fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");

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
            rectangle.setOpacity(0.5);
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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(600);
        scrollPane.setPrefHeight(100);
        scrollPane.setLayoutX(800);
        scrollPane.setLayoutY(470);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        for (String action : actions) {
            Text text = new Text(action);
            text.setFont(new Font("Arial", 20));
            text.setFill(Color.BLACK);
            vBox.getChildren().add(text);
        }

        scrollPane.setContent(vBox);
        scrollPane.setVvalue(1);
        board.getChildren().add(scrollPane);

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

        board.setStyle("-fx-background-size: 1440 950; -fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");

        reloadPanel(false);

        reloadActions();

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
            for (int j = 0; j < 6; j++) {
                if (lignes.get(i).size() > j && lignes.get(i).get(j) != null) {
                    vBox2.getChildren().add(lignes.get(i).get(j).getGraphicCard());
                } else {
                    Pane pane = new Pane();
                    Rectangle emplacement = new Rectangle();
                    emplacement.setFill(Color.TRANSPARENT);
                    emplacement.setWidth(100);
                    emplacement.setHeight(150);
                    emplacement.setStroke(Color.BLACK);
                    emplacement.setOpacity(0.5);
                    emplacement.setStrokeWidth(4);
                    emplacement.setStrokeLineCap(StrokeLineCap.ROUND);
                    emplacement.setStrokeLineJoin(StrokeLineJoin.ROUND);
                    emplacement.setStrokeDashOffset(0);
                    emplacement.setArcWidth(20);
                    emplacement.setArcHeight(20);
                    emplacement.setRotate(0);
                    emplacement.setVisible(true);
                    emplacement.toFront();

                    pane.getChildren().add(emplacement);

                    if (j == 5) {
                        ImageView algo = new ImageView();
                        algo.setImage(new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxRouge.png").toURI().toString()));
                        algo.setFitWidth(90);
                        algo.setFitHeight(80);
                        algo.setLayoutX(5);
                        algo.setLayoutY(35);
                        algo.setVisible(true);
                        algo.toFront();
    
                        pane.getChildren().add(algo);
                    }

                    pane.setPrefWidth(100);
                    pane.setPrefHeight(150);
                    vBox2.getChildren().add(pane);
                }
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
            rectangle.setOpacity(0.5);
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

    public void reloadActions() {
        ScrollPane scrollPane = (ScrollPane) board.getChildren().get(2);
        scrollPane.setPrefWidth(600);
        scrollPane.setPrefHeight(150);
        scrollPane.setLayoutX(800);
        scrollPane.setLayoutY(470);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(10, 0, 10, 10));

        for (String action : actions) {
            Text text = new Text(action);
            text.setFont(new Font("Arial", 20));
            text.setFill(Color.BLACK);
            vBox.getChildren().add(text);
        }

        scrollPane.setContent(vBox);
        scrollPane.setVvalue(1);
    }



    public void removeCard(Card card) {
        board.getChildren().remove(card.getGraphicCard());
    }

    public void removeCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            removeCard(card);
        }
    }

    public void finJeu(Character gagnant, int points) {
        Rectangle rect = new Rectangle(0, 0, 1440, 855);
        rect.setFill(Color.BLACK);
        rect.setOpacity(0.5);
        
        Text text = new Text("Le joueur " + gagnant.getName() + " a gagné \n avec un total de " + points + " têtes de taureau");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(1440 / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(555 / 2 - text.getLayoutBounds().getHeight() / 2);

        // Déterminer la classement des joueurs et les afficher dans l'ordre avec leur score
        HashMap<Character, Integer> listeJoueurScore = new HashMap<>();
        for (Character character : GameLogic.players) {
            int score = 0;
            for (Card card : character.getPoints()) {
                score += card.getBullHead();
            }
            listeJoueurScore.put(character, score);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(listeJoueurScore.entrySet());
        list.sort(Comparator.comparingInt(e -> e.getValue()));
        LinkedHashMap<Character, Integer> classement = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            classement.put(entry.getKey(), entry.getValue());
        }

        GridPane graphicClassement = new GridPane();
        graphicClassement.setHgap(10);
        graphicClassement.setVgap(10);
        graphicClassement.setAlignment(Pos.CENTER);
        graphicClassement.setPadding(new Insets(10, 0, 0, 10));
        
        int cpt = 0;
        for (Map.Entry<Character, Integer> entry : classement.entrySet()) {
            Text position = new Text(cpt + 1 + ".");
            position.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            Color color = Color.WHITE;
            if (cpt == 0) {
                color = Color.GOLD;
            } else if (cpt == 1) {
                color = Color.SILVER;
            } else if (cpt == 2) {
                color = Color.BURLYWOOD;
            }
            position.setFill(color);
            position.setTextAlignment(TextAlignment.CENTER);
            graphicClassement.add(position, 0, cpt);

            Text name = new Text(entry.getKey().getName());
            name.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            name.setFill(color);
            name.setTextAlignment(TextAlignment.CENTER);
            graphicClassement.add(name, 1, cpt);

            Text score = new Text(" : " + entry.getValue().toString());
            score.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            score.setFill(color);
            score.setTextAlignment(TextAlignment.CENTER);
            graphicClassement.add(score, 2, cpt++);
        }

        graphicClassement.setLayoutX(550);
        graphicClassement.setLayoutY(500 / 2 - graphicClassement.getLayoutBounds().getHeight() / 2 + 50);
        board.getChildren().addAll(rect, text, graphicClassement);

        //     Text text2 = new Text(character.getName() + " : " + classement.get(character));
        //     text2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        //     text2.setFill(Color.WHITE);
        //     text2.setTextAlignment(TextAlignment.CENTER);
        //     text2.setX(1500 / 2 - text2.getLayoutBounds().getWidth() / 2);
        //     text2.setY(650 / 2 - text2.getLayoutBounds().getHeight() / 2 + 100 + cpt * 50);
        //     cpt++;
        //     board.getChildren().add(text2);        
    }

    public void addAction(String action) {
        actions.add(action);
    }
}
