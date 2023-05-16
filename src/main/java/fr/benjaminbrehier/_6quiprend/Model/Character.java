package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

public abstract class Character{
    private ArrayList<Card> hand;
    private ArrayList<Card> points;
    private String name;
    private boolean hasChoose;

    public Character (String name, ArrayList<Card> hand){
        this.name = name;
        this.hand = hand;
        this.points = new ArrayList<Card>();
        this.hasChoose = false;
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

    public boolean getHasChoose(){
        return hasChoose;
    }

    public void setHasChoose(boolean hasChoose){
        this.hasChoose = hasChoose;
    }

    /*public Card play(){
        return;
    }*/
}
