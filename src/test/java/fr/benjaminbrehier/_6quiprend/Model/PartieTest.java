package fr.benjaminbrehier._6quiprend.Model;

import fr.benjaminbrehier._6quiprend.GameLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartieTest {

    @Test
    void setup() {
        Partie partie = new Partie();
        partie.setup("1", "0");
    }

    @Test
    void jouer() {
    }
}