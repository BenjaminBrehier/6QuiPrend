package fr.benjaminbrehier._6quiprend;

import fr.benjaminbrehier._6quiprend.Model.Card;
import fr.benjaminbrehier._6quiprend.Model.IA;
import fr.benjaminbrehier._6quiprend.Model.Player;
import fr.benjaminbrehier._6quiprend.Model.Character;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.floor;

public class GameLogic extends Application {
    ArrayList<Card> pioche = new ArrayList();


    @Override
    public void start(Stage stage) throws IOException {
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        ImageView logo = new ImageView(new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString()));
        logo.setFitWidth(250);
        logo.setFitHeight(250);

        vbox.getChildren().add(logo);

        Label nbJoueurLbl = new Label("Combien de joueurs? : 6");
        vbox.getChildren().add(nbJoueurLbl);

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
            setup();
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

    private void setup() {
        // Méthode générique pour randomiser une liste en Java à l'aide du mélange Fisher-Yates

        ArrayList<Card> pioche = new ArrayList();
        ArrayList<ArrayList> lignes = new ArrayList<>();
        int NbJoueurs = 2;
        Player joueur = new Player(null,null);
        IA bot = new IA(null, null);

        List<Character> players = new ArrayList<>();
        players.add(joueur);
        players.add(bot);

        int i;
        System.out.println(pioche.size());
        for (i = 1; i<=104; i++){
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
        System.out.println(pioche.size());
        Collections.shuffle(pioche);
        ArrayList<Card> ligne1 = new ArrayList();
        ligne1.add(pioche.get(0));
        pioche.remove(0);
        ArrayList<Card> ligne2 = new ArrayList();
        ligne2.add(pioche.get(0));
        pioche.remove(0);
        ArrayList<Card> ligne3 = new ArrayList();
        ligne3.add(pioche.get(0));
        pioche.remove(0);
        ArrayList<Card> ligne4 = new ArrayList();
        ligne4.add(pioche.get(0));
        pioche.remove(0);

        lignes.add(ligne1);
        lignes.add(ligne2);
        lignes.add(ligne3);
        lignes.add(ligne4);

        ArrayList<Card> playerHand = new ArrayList();
        ArrayList<Card> botHand = new ArrayList();
        for (i = 0; i<10; i++){
            playerHand.add(pioche.get(0));
            pioche.remove(0);
            botHand.add(pioche.get(0));
            pioche.remove(0);
        }

        for (Character character : players){
            joueur.setHand(playerHand);
            bot.setHand(botHand);
        }

        for (i=0; i < playerHand.size(); i++){
            System.out.println("Liste des cartes du joueur : ");
            System.out.println("Nombre :" + playerHand.get(i).getNumber() + " | " + "Tête :" + playerHand.get(i).getBullHead() + "\n");
        }

        for (i=0; i < botHand.size(); i++){
            System.out.println("Liste des cartes de l'IA : ");
            System.out.println("Nombre :" + playerHand.get(i).getNumber() + " | " + "Tête :" + playerHand.get(i).getBullHead() + "\n");
        }


    }

    public static void main(String[] args) {
        launch();
    }
}