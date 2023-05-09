package fr.benjaminbrehier._6quiprend.Model;

public class Card {
    private int number;
    private int bullHead;

    public Card(int number, int bullHead){
        this.number = number;
        this.bullHead = bullHead;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public int getBullHead(){
        return bullHead;
    }

    public void setBullHead(int bullHead){
        this.bullHead = bullHead;
    }


    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", bullHead=" + bullHead +
                '}';
    }

}
