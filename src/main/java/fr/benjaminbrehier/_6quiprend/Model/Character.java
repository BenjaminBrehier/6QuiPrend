package fr.benjaminbrehier._6quiprend.Model;
import fr.benjaminbrehier._6quiprend.Model.Card;

import java.util.ArrayList;

public abstract class Character{
    private ArrayList<Card> hand;
    private ArrayList<Card> points;

    public Character (ArrayList<Card> hand){
        this.hand = hand;
        this.points = new ArrayList<Card>();
    }


    public ArrayList<Card> getHand(){
        return hand;
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

    public ArrayList<Card> getPoints(){
        return points;
    }

    public void setPoints(ArrayList<Card> points){
        this.points = points;
    }

    /*public Card play(){
        return;
    }*/
}
