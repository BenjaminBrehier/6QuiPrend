package fr.benjaminbrehier._6quiprend.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Partie {
    private ArrayList<Card> pioche;
    private ArrayList<Player> players;
    private Board board;
    private HashMap<Player, Card> cartesJouees;
    private int nbRealPlayer;
    private Player playerPlaying;

    public Partie() {
        pioche = new ArrayList<>();
        players = new ArrayList<>();
        board = new Board(new ArrayList<ArrayList<Card>>());
        cartesJouees = new HashMap<>();
        nbRealPlayer = 0;
    }

    /**
     * Lance la partie
     */
    public void start() {
        GameLogic.musicSelection();
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        Label titre = new Label("Le 6quiPrend!");
        titre.setStyle("-fx-font-size: 60px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: " + "red" + "; -fx-font-weight: bold;");
        vbox.getChildren().add(titre);

        Image backgroundImage = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taureaux-transformed.jpeg").toURI().toString());
        BackgroundImage bgImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1920,1080, true, true, true, true));
        Background bg = new Background(bgImg);
        hbox.setBackground(bg);

        ImageView logo = new ImageView(new Image(
                new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString()));
        logo.setFitWidth(250);
        logo.setFitHeight(250);
        vbox.getChildren().add(logo);

        Label nbJoueurLbl = new Label("Combien de joueurs? : 6");
        nbJoueurLbl.setStyle("-fx-font-size: 20px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: " + "red" + "; -fx-font-weight: bold;");
        vbox.getChildren().add(nbJoueurLbl);


        HBox nombreCharacterBox = new HBox();
        nombreCharacterBox.setSpacing(10);
        nombreCharacterBox.setAlignment(Pos.CENTER);
        nombreCharacterBox.setPadding(new Insets(10));

        HBox nbJoueurBox = new HBox();
        Label nbHumain = new Label("Joueurs");
        nbHumain.setStyle("-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: " + "blue" + "; -fx-font-weight: bold;");
        nbJoueurBox.setSpacing(10);
        nbJoueurBox.setAlignment(Pos.CENTER);
        nbJoueurBox.setPadding(new Insets(10));
        Button btnMoins = new Button("-");
        Button btnPlus = new Button("+");
        TextField nbPlayer = new TextField();
        nbPlayer.setText("1");
        nbPlayer.setEditable(false);
        nbPlayer.setMaxWidth(30);
        nbJoueurBox.getChildren().add(btnMoins);
        nbJoueurBox.getChildren().add(nbPlayer);
        nbJoueurBox.getChildren().add(btnPlus);
        nbJoueurBox.getChildren().add(nbHumain);

        HBox nbIABox = new HBox();
        Label nbPlayerIA = new Label("IA");
        nbPlayerIA.setStyle("-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: " + "green" + "; -fx-font-weight: bold;");
        nbIABox.setSpacing(10);
        nbIABox.setAlignment(Pos.CENTER);
        nbIABox.setPadding(new Insets(10));
        Button btnMoinsIA = new Button("-");
        Button btnPlusIA = new Button("+");
        TextField nbIA = new TextField();
        nbIA.setText("0");
        nbIA.setEditable(false);
        nbIA.setMaxWidth(30);
        nbIABox.getChildren().add(btnMoinsIA);
        nbIABox.getChildren().add(nbIA);
        nbIABox.getChildren().add(btnPlusIA);
        nbIABox.getChildren().add(nbPlayerIA);

        btnMoins.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbPlayer.getText()) > 0) {
                GameLogic.musicSelectionNbJoueursBoup();
                nbPlayer.setText(String.valueOf(Integer.parseInt(nbPlayer.getText()) - 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnPlus.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbPlayer.getText()) + Integer.parseInt(nbIA.getText()) < 10) {
                GameLogic.musicSelectionNbJoueursBip();
                nbPlayer.setText(String.valueOf(Integer.parseInt(nbPlayer.getText()) + 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnMoinsIA.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbIA.getText()) > 0) {
                GameLogic.musicSelectionNbJoueursBoup();
                nbIA.setText(String.valueOf(Integer.parseInt(nbIA.getText()) - 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnPlusIA.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText()) < 10) {
                GameLogic.musicSelectionNbJoueursBip();
                nbIA.setText(String.valueOf(Integer.parseInt(nbIA.getText()) + 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        nombreCharacterBox.getChildren().add(nbJoueurBox);
        nombreCharacterBox.getChildren().add(nbIABox);
        vbox.getChildren().add(nombreCharacterBox);

        HBox modeBox = new HBox();
        modeBox.setSpacing(10);
        modeBox.setAlignment(Pos.CENTER);
        modeBox.setPadding(new Insets(10));
        Button btnLocal = new Button("Mode Local");
        btnLocal.setStyle("-fx-background-color: #00ff00");
        modeBox.getChildren().add(btnLocal);
        Button btnReseau = new Button("Mode Réseau");
        modeBox.getChildren().add(btnReseau);

        btnLocal.setOnAction(actionEvent -> {
            GameLogic.musicLocalReseau();
            btnLocal.setStyle("-fx-background-color: #00ff00");
            btnReseau.setStyle("-fx-background-color: #ffffff");
        });

        btnReseau.setOnAction(actionEvent -> {
            GameLogic.musicLocalReseau();
            btnLocal.setStyle("-fx-background-color: #ffffff");
            btnReseau.setStyle("-fx-background-color: #00ff00");
        });

        vbox.getChildren().add(modeBox);

        Button btn = new Button("Jouer");
        btn.setOnAction(actionEvent -> {
            GameLogic.musicValider();
            setup(nbPlayer.getText(), nbIA.getText());
            if (GameLogic.mediaPlayerSelection != null) {
                GameLogic.mediaPlayerSelection.stop();
            }
            GameLogic.musicJeu();
            board.initBoard();
            jouer();
        });
        vbox.getChildren().add(btn);

        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 0, 50, 0));
        vbox.setSpacing(20);

        hbox.getChildren().add(vbox);

        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0, 0, 50, 0));


        Label resultLabel = new Label("Résultats parties solo précédentes :");
        resultLabel.setStyle("-fx-font-size: 20px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: " + "red" + "; -fx-font-weight: bold;");
        vbox.getChildren().add(resultLabel);

        HBox resultBox = new HBox();
        resultBox.setSpacing(5);
        resultBox.setAlignment(Pos.CENTER);
        for (int result : GameLogic.results) {
            Pane pane = new Pane();
            Rectangle rect = new Rectangle(30, 30);
            if (result == 1) {
                rect.setFill(Color.GOLD);
            } else if (result == 2) {
                rect.setFill(Color.SILVER);
            } else if (result == 3) {
                rect.setFill(Color.BURLYWOOD);
            } else {
                rect.setFill(Color.BLACK);
            }
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(2);
            pane.getChildren().add(rect);


            Label label = new Label(String.valueOf(result));
            label.setStyle("-fx-font-size: 20px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: red; -fx-font-weight: bold;");
            label.setAlignment(Pos.CENTER);
            if (result < 10) {
                label.setPadding(new Insets(5, 0, 0, 10));
            } else {
                label.setPadding(new Insets(5, 0, 0, 4));
            }

            pane.getChildren().add(label);
            resultBox.getChildren().add(pane);
        }

        resultBox.setAlignment(Pos.CENTER);
        resultBox.setPadding(new Insets(0, 0, 50, 0));
        vbox.getChildren().add(resultBox);

        Scene scene = new Scene(hbox, 1440, 855);

        GameLogic.stage.setTitle("6QuiPrend !");
        GameLogic.stage.setScene(scene);
        GameLogic.stage.show();

        nbJoueurLbl.setText("Combien de joueurs ? : "
                + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
    }

    /**
     * Initialise les cartes et les distribue en fonction du nombre de joueurs
     * @param nbPlayer
     * @param nbIA
     */
    public void setup(String nbPlayer, String nbIA) {
        nbRealPlayer = Integer.parseInt(nbPlayer);
        for (int i = 1; i <= 104; i++) {
            if (i % 11 == 0) {
                if (i == 55) {
                    pioche.add(new Card(i, 7));
                } else {
                    pioche.add(new Card(i, 5));
                }
            } else if (i % 10 == 0) {
                pioche.add(new Card(i, 3));
            } else if (i % 5 == 0) {
                pioche.add(new Card(i, 2));
            } else {
                pioche.add(new Card(i, 1));
            }
        }

        Collections.shuffle(pioche);
        ArrayList<Card> ligne1 = new ArrayList<>();
        ligne1.add(pioche.get(0));
        pioche.remove(0);

        ArrayList<Card> ligne2 = new ArrayList<>();
        ligne2.add(pioche.get(0));
        pioche.remove(0);

        ArrayList<Card> ligne3 = new ArrayList<>();
        ligne3.add(pioche.get(0));
        pioche.remove(0);

        ArrayList<Card> ligne4 = new ArrayList<>();
        ligne4.add(pioche.get(0));
        pioche.remove(0);

        board.getLignes().add(ligne1);
        board.getLignes().add(ligne2);
        board.getLignes().add(ligne3);
        board.getLignes().add(ligne4);

        for (int i = 0; i < Integer.parseInt(nbPlayer); i++) {
            ArrayList<Card> characterHand = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                characterHand.add(pioche.get(0));
                pioche.remove(0);
            }

            characterHand.sort(Comparator.comparing(Card::getNumber));
            Player player = new Player("Player " + (i + 1), characterHand);
            players.add(player);
        }

        playerPlaying = players.get(0);

        for (int i = 0; i < Integer.parseInt(nbIA); i++) {
            ArrayList<Card> characterHand = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                characterHand.add(pioche.get(0));
                pioche.remove(0);
            }

            characterHand.sort(Comparator.comparing(Card::getNumber));
            IA ia = new IA("IA " + (i + 1), characterHand);
            players.add(ia);
        }
    }

    /**
     * Logique du jeu, fonction appelée pour lancer le tour expliqué dans un schéma sur figma
     */
    public void jouer() {
        board.reloadPanel(false);
        if (cartesJouees.size() == nbRealPlayer) {
            for (Player c : players) {
                if (c instanceof IA) {
                    IA ia = (IA) c;
                    ia.play();
                }
            }

            board.addAction("");
            board.addAction("====== Tour " + (10 - players.get(0).getHand().size()) + " ======");

            board.reloadPanel(true);

            // Afficher l'état des lignes
            for (int i = 0; i < board.getLignes().size(); i++) {
                System.out.println("Ligne " + (i + 1) + " : " + board.getLignes().get(i));
            }

            // Trie des cartes dans la HashMap
            List<Map.Entry<Player, Card>> list = new ArrayList<>(cartesJouees.entrySet());
            list.sort(Comparator.comparingInt(e -> e.getValue().getNumber()));
            LinkedHashMap<Player, Card> cartesJoueesTriees = new LinkedHashMap<>();
            for (Map.Entry<Player, Card> entry : list) {
                cartesJoueesTriees.put(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<Player, Card> testEntry : cartesJoueesTriees.entrySet()) {
                ArrayList<Card> ligne = null;
                Card card = (Card) testEntry.getValue();
                Player joueur = (Player) testEntry.getKey();

                for (int j = 0; j < board.getLignes().size(); j++) {
                    Card lastCard = board.getLignes().get(j).get(board.getLignes().get(j).size() - 1);

                    if (ligne == null) {
                        if (card.getNumber() > lastCard.getNumber()) {
                            ligne = board.getLignes().get(j);
                        }
                    } else {
                        if (card.getNumber() > lastCard.getNumber() && card.getNumber() - lastCard.getNumber() < card.getNumber() - ligne.get(ligne.size() - 1).getNumber()) {
                            ligne = board.getLignes().get(j);
                        }
                    }
                }

                if (ligne != null) {
                    board.addAction(joueur.getName() + " pose la carte " + card.getNumber() + " sur la ligne " + (board.getLignes().indexOf(ligne) + 1) + ".");
                    System.out.println("La carte " + card.toString() + " est ajoutée à la ligne " + ligne);
                    if (ligne.size() == 5) {
                        board.addAction("-> La ligne " + (board.getLignes().indexOf(ligne) + 1) + " est pleine, " + joueur.getName() + " ramasse la ligne, "+ GameLogic.nbBullLigne(ligne) +" têtes.");
                        System.out.println("La ligne contenant la carte inférieure la plus proche est full, vous ramassez donc la ligne et vous posez votre carte en première position");
                        joueur.getPoints().addAll(ligne);
                        ligne.clear();
                    }
                    ligne.add(card);
                } else {
                    board.addAction(joueur.getName() + " ne peut pas poser la carte " + card.getNumber() + ".");
                    System.out.println("Aucune ligne ne peut accueillir la carte " + card.toString() + " !");
                    if (testEntry.getKey() instanceof IA) {
                        int min = 0;
                        for (int i = 0; i < board.getLignes().size(); i++) {
                            if (GameLogic.nbBullLigne(board.getLignes().get(i)) < GameLogic.nbBullLigne(board.getLignes().get(min))) {
                                min = i;
                            }
                        }
                        joueur.getPoints().addAll(board.getLignes().get(min));
                        board.addAction("-> " + joueur.getName() + " choisi de ramasser la ligne " + (min + 1) + ", " + GameLogic.nbBullLigne(board.getLignes().get(min)) + " têtes.");
                        board.removeCards(board.getLignes().get(min));
                        board.getLignes().get(min).clear();
                        board.getLignes().get(min).add(card);
                    } else {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Sélection de ligne");
                        alert.setHeaderText("Veuillez choisir une ligne " + playerPlaying.getName() + ".");
                        alert.setContentText("Aucune ligne ne peut accueillir cette carte : " + card.getNumber());

                        ButtonType buttonType1 = new ButtonType("1");
                        ButtonType buttonType2 = new ButtonType("2");
                        ButtonType buttonType3 = new ButtonType("3");
                        ButtonType buttonType4 = new ButtonType("4");
                        alert.getButtonTypes().setAll(buttonType1, buttonType2, buttonType3, buttonType4);

                        alert.showAndWait().ifPresent(buttonType -> {
                            int ligneARamasser = 0;
                            if (buttonType == buttonType1) {
                                ligneARamasser = 1;
                            } else if (buttonType == buttonType2) {
                                ligneARamasser = 2;
                            } else if (buttonType == buttonType3) {
                                ligneARamasser = 3;
                            } else if (buttonType == buttonType4) {
                                ligneARamasser = 4;
                            }

                            joueur.getPoints().addAll(board.getLignes().get(ligneARamasser - 1));
                            board.getLignes().get(ligneARamasser - 1).clear();
                            board.getLignes().get(ligneARamasser - 1).add(card);
                            System.out.println(
                                    "Nouvelle ligne : " + board.getLignes().get(ligneARamasser - 1).toString());
                            board.addAction(joueur.getName() + " ramasse la ligne " + (ligneARamasser) + ".");
                        });
                    }
                }
                board.reloadPanel(true);
                board.reloadLignes();

            }
            cartesJouees.clear();
            cartesJoueesTriees.clear();

            board.reloadBoard();

            if (players.get(0).getHand().size() == 0) {
                if (GameLogic.mediaPlayerJeu != null) {
                    GameLogic.mediaPlayerJeu.stop();
                }
                int joueurGagnant = -1; // Indice du joueur gagnant (-1 pour l'initialiser)
                int minimumPoints = Integer.MAX_VALUE; // Initialise avec la plus grande valeur possible
                for (int p = 0; p < players.size(); p++) {
                    int totalPoints = 0; // Initialise le total de points du joueur
                    for (int gp = 0; gp < players.get(p).getPoints().size(); gp++) {
                        totalPoints += players.get(p).getPoints().get(gp).getBullHead();
                    }
                    if (totalPoints < minimumPoints) {
                        minimumPoints = totalPoints;
                        joueurGagnant = p;
                    }
                }
                System.out.println("Le joueur " + players.get(joueurGagnant).getName() + " a le moins de points avec un total de " + minimumPoints + " têtes de taureau");
                board.finJeu(players.get(joueurGagnant), minimumPoints);
                if (players.get(joueurGagnant) == players.get(0)){
                    GameLogic.musicVictory();
                }
                else{
                    GameLogic.musicWasted();
                }
            } else {
                board.addCardsEvent();
                board.reloadBoard();
            }
        }
    }

    public void switchPlayerPlaying() {
        int index = players.indexOf(playerPlaying);
        for (int i = 0; i < players.size(); i++) {
            if (i!= index && i>index && !(players.get(i) instanceof IA)) {
                playerPlaying = players.get(i);
                break;
            }
        }

        if (index == players.indexOf(playerPlaying)) {
            for (Player player : players) {
                if (!(player instanceof IA)) {
                    playerPlaying = player;
                    break;
                }
            }
        }
        board.reloadLignes();
    }

    public Board getBoard() {
        return board;
    }

    public HashMap<Player, Card> getCartesJouees() {
        return cartesJouees;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getPioche() {
        return pioche;
    }

    public int getNbRealPlayer() {
        return nbRealPlayer;
    }

    public Player getPlayerPlaying() {
        return playerPlaying;
    }
}
