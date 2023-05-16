package fr.benjaminbrehier._6quiprend;

import fr.benjaminbrehier._6quiprend.Model.Board;
import fr.benjaminbrehier._6quiprend.Model.Card;
import fr.benjaminbrehier._6quiprend.Model.IA;
import fr.benjaminbrehier._6quiprend.Model.Player;
import fr.benjaminbrehier._6quiprend.Model.Character;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameLogic extends Application {
    public static ArrayList<Card> pioche = new ArrayList<>();
    public static ArrayList<Character> players = new ArrayList<>();
    Board board = new Board(new ArrayList<ArrayList<Card>>());
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        ImageView logo = new ImageView(new Image(
                new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString()));
        logo.setFitWidth(250);
        logo.setFitHeight(250);

        vbox.getChildren().add(logo);

        Label nbJoueurLbl = new Label("Combien de joueurs? : 6");
        vbox.getChildren().add(nbJoueurLbl);

        HBox nombreCharacterBox = new HBox();
        nombreCharacterBox.setSpacing(10);
        nombreCharacterBox.setAlignment(Pos.CENTER);
        nombreCharacterBox.setPadding(new Insets(10));

        HBox nbJoueurBox = new HBox();
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

        HBox nbIABox = new HBox();
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

        btnMoins.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbPlayer.getText()) > 0) {
                nbPlayer.setText(String.valueOf(Integer.parseInt(nbPlayer.getText()) - 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnPlus.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbPlayer.getText()) + Integer.parseInt(nbIA.getText()) < 10) {
                nbPlayer.setText(String.valueOf(Integer.parseInt(nbPlayer.getText()) + 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnMoinsIA.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbIA.getText()) > 0) {
                nbIA.setText(String.valueOf(Integer.parseInt(nbIA.getText()) - 1));
                nbJoueurLbl.setText("Combien de joueurs ? : "
                        + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");
            }
        });

        btnPlusIA.setOnAction(actionEvent -> {
            if (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText()) < 10) {
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
            btnLocal.setStyle("-fx-background-color: #00ff00");
            btnReseau.setStyle("-fx-background-color: #ffffff");
        });

        btnReseau.setOnAction(actionEvent -> {
            btnLocal.setStyle("-fx-background-color: #ffffff");
            btnReseau.setStyle("-fx-background-color: #00ff00");
        });

        vbox.getChildren().add(modeBox);

        Button btn = new Button("Jouer");
        btn.setOnAction(actionEvent -> {
            setup(nbPlayer.getText(), nbIA.getText());
            board.reloadBoard();
        });
        vbox.getChildren().add(btn);

        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0, 0, 50, 0));
        vbox.setSpacing(40);

        hbox.getChildren().add(vbox);

        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0, 0, 50, 0));

        Scene scene = new Scene(hbox, 1440, 855);

        stage.setTitle("6QuiPrend !");
        stage.setScene(scene);
        stage.show();

        nbJoueurLbl.setText("Combien de joueurs ? : "
                + (Integer.parseInt(nbIA.getText()) + Integer.parseInt(nbPlayer.getText())) + " sur 10 max");

    }

    private void setup(String nbPlayer, String nbIA) {
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
        pioche.sort(Comparator.comparing(Card::getNumber));
        ArrayList<Card> ligne1 = new ArrayList<>();
        ligne1.add(pioche.get(0));
        pioche.remove(0);
        /*ligne1.add(pioche.get(0));
        pioche.remove(0);
        ligne1.add(pioche.get(0));
        pioche.remove(0);
        ligne1.add(pioche.get(0));
        pioche.remove(0);
        ligne1.add(pioche.get(0));
        pioche.remove(0);*/
        ArrayList<Card> ligne2 = new ArrayList<>();
        ligne2.add(pioche.get(0));
        pioche.remove(0);
        /*ligne2.add(pioche.get(0));
        pioche.remove(0);
        ligne2.add(pioche.get(0));
        pioche.remove(0);
        ligne2.add(pioche.get(0));
        pioche.remove(0);
        ligne2.add(pioche.get(0));
        pioche.remove(0);*/
        ArrayList<Card> ligne3 = new ArrayList<>();
        ligne3.add(pioche.get(0));
        pioche.remove(0);
        /*ligne3.add(pioche.get(0));
        pioche.remove(0);
        ligne3.add(pioche.get(0));
        pioche.remove(0);
        ligne3.add(pioche.get(0));
        pioche.remove(0);
        ligne3.add(pioche.get(0));
        pioche.remove(0);*/
        ArrayList<Card> ligne4 = new ArrayList<>();
        ligne4.add(pioche.get(0));
        pioche.remove(0);
        /*ligne4.add(pioche.get(0));
        pioche.remove(0);
        ligne4.add(pioche.get(0));
        pioche.remove(0);
        ligne4.add(pioche.get(0));
        pioche.remove(0);
        ligne4.add(pioche.get(0));
        pioche.remove(0);*/

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
        jouer();
    }

    private void jouer() {

        for (int i = 0; i < 1; i++) {
            System.out.println("État des 4 lignes :");
            System.out.println(board.getLignes().get(0).toString());
            System.out.println(board.getLignes().get(1).toString());
            System.out.println(board.getLignes().get(2).toString());
            System.out.println(board.getLignes().get(3).toString());

            for (Character player : players) {
                System.out.println("Joueur : " + player + " || Cartes :");
                System.out.println(player.getHand().toString());
            }

            HashMap<Character, Card> cartesJouees = new HashMap<>();
            for (Character player : players) {
                // Random card from player hand
                int randomCard = (int) (Math.random() * player.getHand().size());
                Card card = player.getHand().get(randomCard);
                cartesJouees.put(player, card);
            }
            
            // Trie des cartes dans la HashMap
            cartesJouees.forEach((k, v) -> System.out.println(k + "=" + v));
            System.out.println("After sorting by value");
            List<Map.Entry<Character, Card>> list = new ArrayList<>(cartesJouees.entrySet());
            list.sort(Comparator.comparingInt(e -> e.getValue().getNumber()));
            LinkedHashMap<Character, Card> cartesJoueesTriees = new LinkedHashMap<>();
            for (Map.Entry<Character, Card> entry : list) {
                cartesJoueesTriees.put(entry.getKey(), entry.getValue());
            }
            System.out.println("Sorted Map:");
            cartesJoueesTriees.forEach((k, v) -> System.out.println(k + "=" + v));

            if (cartesJoueesTriees.size() > 0) {
                for (Map.Entry<Character, Card> testEntry : cartesJoueesTriees.entrySet()) {
                    ArrayList<Card> ligne = null;
                    Card card = (Card) testEntry.getValue();
                    for (int j = 0; j < board.getLignes().size(); j++) {
                        Card lastCard = board.getLignes().get(j).get(board.getLignes().get(j).size() - 1);

                        System.out.println("Carte : " + card.toString() + " | Dernière carte de la ligne : "
                                + lastCard.toString());
                        if (ligne == null) {
                            if (card.getNumber() > lastCard.getNumber()) {
                                ligne = board.getLignes().get(j);
                            }
                        } else {
                            System.out.println(card.getNumber() > lastCard.getNumber() && card.getNumber() - lastCard.getNumber() < card.getNumber() - ligne.get(ligne.size() - 1).getNumber());
                            if (card.getNumber() > lastCard.getNumber() && card.getNumber() - lastCard.getNumber() < card.getNumber() - ligne.get(ligne.size() - 1).getNumber()) {
                                ligne = board.getLignes().get(j);
                            }
                        }
                    }

                    if (ligne != null) {
                        System.out.println("La carte " + card.toString() + " est ajoutée à la ligne " + ligne);
                        Character joueur = (Character) testEntry.getKey();
                        joueur.getHand().remove(card);
                        if (ligne.size() == 5){
                            System.out.println("La ligne contenant la carte inférieure la plus proche est full, vous ramassez donc la ligne et vous posez votre carte en première position");
                            joueur.getPoints().addAll(ligne);
                            ligne.removeAll(ligne);
                        }
                        ligne.add(card);
                    } else {
                        System.out.println("Aucune ligne ne peut accueillir cette carte : " + card.toString());
                        System.out.println("Choisissez une ligne à ramasser (1, 2, 3 ou 4)");
                        Scanner scanner = new Scanner(System.in);
                        int ligneARamasser = scanner.nextInt();
                        Character joueur = (Character) testEntry.getKey();
                        joueur.getHand().remove(card);
                        joueur.getPoints().addAll(board.getLignes().get(ligneARamasser - 1));
                        board.getLignes().get(ligneARamasser - 1).removeAll(board.getLignes().get(ligneARamasser - 1));
                        board.getLignes().get(ligneARamasser - 1).add(card);
                        System.out.println("Nouvelle ligne : " + board.getLignes().get(ligneARamasser - 1).toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}