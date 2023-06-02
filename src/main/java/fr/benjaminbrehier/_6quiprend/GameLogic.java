package fr.benjaminbrehier._6quiprend;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.Model.Card;
import fr.benjaminbrehier._6quiprend.Model.Partie;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLogic extends Application {
    public static Stage stage;
    public static Partie partie;
    public static ArrayList<Integer> results;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.results = new ArrayList<>();
        partie = new Partie();
        partie.start();
    }

    public static void main(String[] args) {
        launch();
    }


    /**
     * Retourne le nombre de bullhead d'une ligne
     * @param ligne
     * @return nombre total de bullhead de la ligne
     */
    public static int nbBullLigne(ArrayList<Card> ligne) {
        int nbBull = 0;
        for (Card c : ligne) {
            nbBull += c.getBullHead();
        }
        return nbBull;
    }
    
    //PARTIE AMBIANCE MUSICALE
    public static MediaPlayer mediaPlayerSelection;
    public static MediaPlayer mediaPlayerJeu;
    static MediaPlayer mediaPlayerSelectionNbJoueursBip;
    static MediaPlayer mediaPlayerSelectionNbJoueursBoup;
    static MediaPlayer mediaPlayerLocalReseau;
    static MediaPlayer mediaPlayerValider;
    static MediaPlayer mediaPlayerFlipCard;
    static MediaPlayer mediaPlayerVictory;
    static MediaPlayer mediaPlayerWasted;
    
    /**
     * Permet de lancer la musique selection
     */
    public static void musicSelection(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/mario-kart-8-deluxe-music-extended.mp3";
            Media s = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerSelection = new MediaPlayer(s);
            mediaPlayerSelection.setOnEndOfMedia(() -> mediaPlayerSelection.seek(Duration.ZERO));
            mediaPlayerSelection.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayerSelection.play();
        }
    }
    /**
     * Permet de lancer la musique de jeu
     */
    public static void musicJeu(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/nintendo-casino-music-compilation.mp3";
            Media j = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerJeu = new MediaPlayer(j);
            mediaPlayerJeu.setOnEndOfMedia(() -> mediaPlayerJeu.seek(Duration.ZERO));
            mediaPlayerJeu.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayerJeu.play();
        }
    }
    /**
     * Permet de lancer le bip au click sur le bouton
     */
    public static void musicSelectionNbJoueursBip(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/bip.mp3";
            Media blip = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerSelectionNbJoueursBip = new MediaPlayer(blip);
            mediaPlayerSelectionNbJoueursBip.play();
        }
    }
    /**
     * Permet de lancer le bip au click sur le bouton
     */
    public static void musicSelectionNbJoueursBoup(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/boup.mp3";
            Media blip = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerSelectionNbJoueursBoup = new MediaPlayer(blip);
            mediaPlayerSelectionNbJoueursBoup.play();
        }
    }

    /**
     * Permet de lancer le bip au click sur le bouton
     */
    public static void musicLocalReseau(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/local_reseau.mp3";
            Media reseau = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerLocalReseau = new MediaPlayer(reseau);
            mediaPlayerLocalReseau.play();
        }
    }

    /**
     * Permet de lancer le bip au click sur le bouton
     */
    public static void musicValider(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/valider.mp3";
            Media valider = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerValider = new MediaPlayer(valider);
            mediaPlayerValider.play();
        }
    }

    /**
     * Permet de lancer le son au click sur une carte
     */
    public static void musicFlipCard(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/flipcard.mp3";
            Media flip = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerFlipCard = new MediaPlayer(flip);
            mediaPlayerFlipCard.play();
        }
    }

    /**
     * Permet de lancer la musique de victoire
     */
    public static void musicVictory(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/victory.mp3";
            Media victory = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerVictory = new MediaPlayer(victory);
            mediaPlayerVictory.play();
        }
    }
    
    /**
     * Permet de lancer la musique de défaite
     */
    public static void musicWasted(){
        if (System.getProperty("os.name").contains("Windows")) {
            String sound = "src/main/resources/fr/benjaminbrehier/_6quiprend/snd/wasted.mp3";
            Media wasted = new Media(Paths.get(sound).toUri().toString());
            mediaPlayerVictory = new MediaPlayer(wasted);
            mediaPlayerVictory.play();
        }
    }
}