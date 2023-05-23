package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;

public class IA extends Character {

    public IA (String name, ArrayList<Card> hand){
        super (name, hand);
    }

    public void play(){
        // int aléatoire entre 0 et ia.getHand().size()
        int random = (int) (Math.random() * (this.getHand().size() - 1));
        GameLogic.cartesJouees.put(this, this.getHand().get(random));
        this.getHand().remove(random);
    }
}
