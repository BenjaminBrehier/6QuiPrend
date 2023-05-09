package fr.benjaminbrehier._6quiprend;

import fr.benjaminbrehier._6quiprend.Model.Card;
import fr.benjaminbrehier._6quiprend.Model.IA;
import fr.benjaminbrehier._6quiprend.Model.Player;
import fr.benjaminbrehier._6quiprend.Model.Character;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.floor;

public class GameLogic extends Application {

    @FXML
    private ImageView logo;

    @FXML
    private Label nbJoueurLbl;

    @FXML
    private Slider sliderNbJoueur;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameLogic.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 855);
        stage.setTitle("6QuiPrend !");
        stage.setScene(scene);
        stage.show();

        Image image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString());
        logo = (ImageView) scene.lookup("#logo");
        logo.setImage(image);

        nbJoueurLbl = (Label) scene.lookup("#nbJoueurLbl");

        sliderNbJoueur = (Slider) scene.lookup("#slider");

        sliderNbJoueur.valueProperty().addListener((observable, oldValue, newValue) -> {
            nbJoueurLbl.setText("Combien de joueurs ? : " + newValue.intValue()); // Met à jour le label avec la nouvelle valeur du slider
        });
        nbJoueurLbl.setText("Combien de joueurs ? : 6");
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
            double d = i / 10;
            double c = i/5;
            double o = i/11;

            if (i == 55) {
                pioche.add( new Card(i,7));
            }else if (o == floor(o)) {
               pioche.add( new Card(i,5));
            }else if (d == floor(d)) {
                pioche.add( new Card(i,3));
            }else if (c == floor(c)) {
                pioche.add( new Card(i,2));
            }else{
                pioche.add( new Card(i,1));
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