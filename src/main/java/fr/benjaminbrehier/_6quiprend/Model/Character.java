package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

public class Character{
    private ArrayList<Card> hand;
    private ArrayList<Card> points;
    private String name;

    public Character (String name, ArrayList<Card> hand){
        this.name = name;
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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /*public Card play(){
        return;
    }*/
}
