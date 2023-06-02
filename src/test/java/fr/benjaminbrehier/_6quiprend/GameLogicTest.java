package fr.benjaminbrehier._6quiprend;

import fr.benjaminbrehier._6quiprend.Model.Card;
import fr.benjaminbrehier._6quiprend.Model.Partie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void nbBullLigne() {
        ArrayList<Card> ligne = new ArrayList<Card>();
        ligne.add(new Card(20));
        ligne.add(new Card(30));
        ligne.add(new Card(40));
        ligne.add(new Card(50));

        assertEquals(3*ligne.size(), GameLogic.nbBullLigne(ligne));
    }

    @Test
    void musicSelection() {
        GameLogic.musicSelection();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerSelection.stop();
        }
    }

    @Test
    void musicJeu() {
        GameLogic.musicJeu();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerJeu.stop();
        }
    }

    @Test
    void musicSelectionNbJoueursBip() {
        GameLogic.musicSelectionNbJoueursBip();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerSelectionNbJoueursBip.stop();
        }
    }

    @Test
    void musicSelectionNbJoueursBoup() {
        GameLogic.musicSelectionNbJoueursBoup();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerSelectionNbJoueursBoup.stop();
        }
    }

    @Test
    void musicLocalReseau() {
        GameLogic.musicLocalReseau();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerLocalReseau.stop();
        }
    }

    @Test
    void musicValider() {
        GameLogic.musicValider();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerValider.stop();
        }
    }

    @Test
    void musicFlipCard() {
        GameLogic.musicFlipCard();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerFlipCard.stop();
        }
    }

    @Test
    void musicVictory() {
        GameLogic.musicVictory();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerVictory.stop();
        }
    }

    @Test
    void musicWasted() {
        GameLogic.musicWasted();
        if (GameLogic.mediaPlayerWasted != null) {
            GameLogic.mediaPlayerWasted.stop();
        }
    }
}