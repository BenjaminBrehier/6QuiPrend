package fr.benjaminbrehier._6quiprend.Model;

import fr.benjaminbrehier._6quiprend.GameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadLocalRandom;

public class IA extends Character {

    public IA (String name, ArrayList<Card> hand){
        super (name, hand);
    }

    public Card algoIA(){
        System.out.println("test");

        //Peu importe le nombre de joueurs !!!!
        //Check si une ligne a 4 cartes et si la dernière carte de cette ligne a un nombre très
        //proche (1 de différence) du nombre d'une carte dans la main de l'IA (dans ce cas, la jouer)
        for (int c=1; c<=GameLogic.board.getLignes().size(); c++){
            if (GameLogic.board.getLignes().get(c).size() == 4){
                for (int d=1; d<=this.getHand().size(); d++){
                    if (this.getHand().get(d).getNumber() - GameLogic.board.getLignes().get(c).get(4).getNumber() == 1){
                        return this.getHand().get(d);
                    }
                }
            }
        }


        //Check si le nombre total de joueur <= 3, si c'est le cas check si une ou plusieurs lignes ont 3 cartes,
        //si c'est le cas check si la dernière carte des lignes a une valeur de -3 par rapport à une ou plusieurs
        //cartes de la main de l'IA. Puis check si les 2 cartes entre la troisième carte du board et celle que je peux jouer
        //ont déjà été jouées. Si c'est le cas, on est bon.
        //Par exemple, il y la carte 50 sur le board, ma carte la + proche est 53
        //Check si les cartes 51 / 52 ont été jouées (au moins une), car puisqu'il y a 3 cartes sur la ligne, l'IA est safe dans ce cas
        if (GameLogic.players.size() <= 3){
            ArrayList<Card> cartesDifferenceInferieureA3Jouables = new ArrayList<>();
            ArrayList<Card> listeCartesDansGetPoints = new ArrayList<>();
            //Ajoute dans une seule et même liste toutes les cartes des listes GetPoints de tous les joueurs, y compris les IA
            for (int i=0; i<GameLogic.players.size(); i++){
                for (Card cartesDansGetPoints : GameLogic.players.get(i).getPoints()){
                    listeCartesDansGetPoints.add(cartesDansGetPoints);
                }
            }
            //Check si une ligne a 3 cartes
            for (int c=1; c<=GameLogic.board.getLignes().size(); c++){
                if (GameLogic.board.getLignes().get(c).size() == 3){
                    //Check si l'IA a une carte qui a une valeur de +3 par rapport à la dernière carte de la ligne
                    for (int d=0; d<this.getHand().size(); d++) {
                        if (this.getHand().get(d).getNumber() - GameLogic.board.getLignes().get(c).get(3).getNumber() == 3) {
                            //Initialise les valeurs cibles qui correspondent à la valeur de la dernière carte +1 et +2
                            int targetValue1 = GameLogic.board.getLignes().get(c).get(3).getNumber() + 1;
                            int targetValue2 = GameLogic.board.getLignes().get(c).get(3).getNumber() + +2;
                            for (Card cartesDansListeGlobale : listeCartesDansGetPoints){
                                if (cartesDansListeGlobale.getNumber() == targetValue1 || cartesDansListeGlobale.getNumber() == targetValue2){
                                    //Alors on ajoute la carte de la main de l'IA dans la liste des cartes jouables,
                                    //et on return une carte aléatoire (POUR L'INSTANT !!!! car pas d'idée de comment choisir la carte à jouer)
                                    cartesDifferenceInferieureA3Jouables.add(this.getHand().get(d));
                                    Card randomCarte = cartesDifferenceInferieureA3Jouables.get(ThreadLocalRandom.current().nextInt(cartesDifferenceInferieureA3Jouables.size()));
                                    return randomCarte;
                                }
                            }
                        }
                    }
                }
            }
        }


        //A laisser ici pour l'instant, peut petre util après
        //Check de la ligne qui a le plus de têtes
        int ligneMaxDeTete = -1;
        int maxValeur = Integer.MIN_VALUE;
        for (int a=0; a<=GameLogic.board.getLignes().size(); a++){
            int totalTete = 0;
            for (int b=0; b<=GameLogic.board.getLignes().get(a).size(); b++){
                totalTete += GameLogic.board.getLignes().get(a).get(b).getBullHead();
            }
            System.out.println("Le nombre total de têtes de la ligne " + a + "est de " + totalTete);

            if (totalTete > maxValeur) {
                maxValeur = totalTete;
                ligneMaxDeTete = a;
            }
        }
        System.out.println("La meilleure ligne est la ligne " + ligneMaxDeTete);
        return null;
    }
}


