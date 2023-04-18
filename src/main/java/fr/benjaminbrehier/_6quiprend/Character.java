package fr.benjaminbrehier._6quiprend;
import java.util.ArrayList;

public abstract class Character{
    private ArrayList<Card> hand;
    private ArrayList<Card> points;

    public Character (ArrayList<Card> hand, ArrayList<Card> points){
        this.hand = hand;
        this.points = points;
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
