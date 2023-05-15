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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

        ImageView logo = new ImageView(new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString()));
        logo.setFitWidth(250);
        logo.setFitHeight(250);

        vbox.getChildren().add(logo);

        Label nbJoueurLbl = new Label("Combien de joueurs? : 6");
        vbox.getChildren().add(nbJoueurLbl);

        HBox nameBox = new HBox();
        nameBox.setSpacing(10);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPadding(new Insets(10));
        Label nameLbl = new Label("Nom du joueur : ");
        nameBox.getChildren().add(nameLbl);
        TextField nameJoueur = new TextField();
        nameJoueur.setPromptText("Nom du joueur");
        nameBox.getChildren().add(nameJoueur);
        vbox.getChildren().add(nameBox);

        Slider sliderNbJoueur = new Slider(2, 10, 6);
        sliderNbJoueur.setShowTickLabels(true);
        sliderNbJoueur.setShowTickMarks(true);
        sliderNbJoueur.setMajorTickUnit(1);
        sliderNbJoueur.setBlockIncrement(1);
        sliderNbJoueur.setSnapToTicks(true);
        sliderNbJoueur.setMinorTickCount(0);
        sliderNbJoueur.setPrefWidth(300);
        vbox.getChildren().add(sliderNbJoueur);


        Button btn = new Button("Jouer");
        btn.setOnAction(actionEvent -> {
            setup(sliderNbJoueur.getValue(), nameJoueur.getText());
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

        sliderNbJoueur.valueProperty().addListener((observable, oldValue, newValue) -> {
            nbJoueurLbl.setText("Combien de joueurs ? : " + newValue.intValue()); // Met à jour le label avec la nouvelle valeur du slider
        });
        nbJoueurLbl.setText("Combien de joueurs ? : 6");

        }

    private void setup(double nbJoueur, String name) {
        for (int i = 1; i<=104; i++){
            if (i%11 == 0){
                if (i == 55) {
                    pioche.add(new Card(i, 7));
                }
                else{
                    pioche.add(new Card(i, 5));
                }
            }
            else if (i%10 == 0){
                pioche.add(new Card(i, 3));
            }
            else if (i%5 == 0){
                pioche.add(new Card(i, 2));
            }
            else{
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

        for (int i = 0; i < nbJoueur; i++) {
            ArrayList<Card> characterHand = new ArrayList<>();
            for (int j = 0; j<10; j++){
                characterHand.add(pioche.get(0));
                pioche.remove(0);
            }

            Character character;
            if (i == 0) {
                character = new Player(name, characterHand);
            } else {
                character = new IA("IA", characterHand);
            }
            players.add(character);
        }

        
        // for (Character character : players) {
        //     System.out.println("Liste des cartes du joueur : ");
        //     for (int i = 0; i < character.getHand().size(); i++) {
        //         System.out.println("Nombre :" + character.getHand().get(i).getNumber() + " | " + "Tête :" + character.getHand().get(i).getBullHead());
        //     }
        // }
    }

    public static void main(String[] args) {
        launch();
    }
}