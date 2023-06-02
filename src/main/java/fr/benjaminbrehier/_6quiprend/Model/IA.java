package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import fr.benjaminbrehier._6quiprend.GameLogic;

public class IA extends Character {

    public IA(String name, ArrayList<Card> hand) {
        super(name, hand);
    }

    /**
     * Stratégie de l'IA
     * */
    public Card strategie1() {
        ArrayList<Card> listeCartesDansGetPoints = new ArrayList<>();
        //Ajoute dans une seule et même liste toutes les cartes des listes GetPoints de tous les joueurs, y compris les IA
        for (int i = 0; i < GameLogic.partie.getPlayers().size(); i++) {
            for (Card cartesDansGetPoints : GameLogic.partie.getPlayers().get(i).getPoints()) {
                listeCartesDansGetPoints.add(cartesDansGetPoints);
            }
        }


        //AU SECOURS C'EST TROP COMPLEXE
        //50 sur le board, 60 dans la main = écart de 10, on check si 51, 52, 53, 54, 56, 57, 58 et 59 ont été prises / posées
        for (int c=0; c<GameLogic.partie.getBoard().getLignes().size(); c++){
            int ecart = 0;
            for (int d=0; d<GameLogic.partie.getBoard().getLignes().get(c).size(); d++) {
                for (int m=0; m<this.getHand().size(); m++){
                    if (this.getHand().get(m).getNumber() > GameLogic.partie.getBoard().getLignes().get(c).get(d).getNumber()){
                        ecart = GameLogic.partie.getBoard().getLignes().get(c).get(d).getNumber() - this.getHand().get(m).getNumber() - 1;
                        int test = 0;
                        //Pour toutes les cartes dans listecartesDansGetPoints
                        for (Card carteDansGetPoints : listeCartesDansGetPoints){
                            for (int e=1; e<=ecart; e++){
                                //Si le numéro de la carte dans listecartesDansGetPoints = la d 0ernière carte
                                //de la ligne + l'écart actuel
                                if(carteDansGetPoints.getNumber() == GameLogic.partie.getBoard().getLignes().get(c).get(d).getNumber() + e){
                                    test ++;
                                }
                            }
                        }
                        if (test == ecart){
                            System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!! Test = " + test + " | ecart = " + ecart);
                            return this.getHand().get(m);
                        }
                    }
                }
            }
        }


        //Peu importe le nombre de joueurs !!!!
        //Check si une ligne a 4 cartes et si la dernière carte de cette ligne a un nombre très
        //proche (1 de différence) du nombre d'une carte dans la main de l'IA (dans ce cas, la jouer)
        for (int c=0; c<GameLogic.partie.getBoard().getLignes().size(); c++){
            if (GameLogic.partie.getBoard().getLignes().get(c).size() <= 4){
                for (int d=0; d<this.getHand().size(); d++) {
                    //Check si les deux cartes ont 1 de différence
                    if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size() - 1).getNumber() == 1) {
                        return this.getHand().get(d);
                    }
                    /* AU SECOURS C'EST TROP COMPLEXE
                    //50 sur le board, 60 dans la main = écart de 10, on check si 51, 52, 53, 54, 56, 57, 58 et 59 ont été prises / posées
                    ecart = GameLogic.partie.getBoard().getLignes().get(c).get(d).getNumber() - this.getHand().get(d).getNumber();
                    for (int e=0; e<ecart; e++){
                        //Pour toutes les cartes dans listecartesDansGetPoints
                        for (Card carte : listeCartesDansGetPoints){
                            //Si le numéro de la carte dans listecartesDansGetPoints = la dernière carte
                            //de la ligne + l'écart actuel
                            if(carte.getNumber() == GameLogic.partie.getBoard().getLignes().get(c).get(d).getNumber() + ecart){

                                if(this.getHand().get(d).getNumber() - carte.getNumber() == ecart){

                                }
                            }
                        }
                    }*/
                }
            }
        }


        //Check si le nombre total de joueur <= 3, si c'est le cas check si une ou plusieurs lignes ont 3 cartes,
        //si c'est le cas check si la dernière carte des lignes a une valeur de -3 par rapport à une ou plusieurs
        //cartes de la main de l'IA. Puis check si les 2 cartes entre la troisième carte du board et celle que je peux jouer
        //ont déjà été jouées. Si c'est le cas, on est bon.
        //Par exemple, il y la carte 50 sur le board, ma carte la + proche est 53
        //Check si les cartes 51 / 52 ont été jouées (au moins une), car puisqu'il y a 3 cartes sur la ligne, l'IA est safe dans ce cas
        if (GameLogic.partie.getPlayers().size() <= 3){
            ArrayList<Card> cartesDifferenceInferieureA3Jouables = new ArrayList<>();
            //Check si une ligne a 3 cartes
            for (int c=0; c<GameLogic.partie.getBoard().getLignes().size(); c++){
                if (GameLogic.partie.getBoard().getLignes().get(c).size() == 3){
                    //Check si l'IA a une carte qui a une valeur de +3 par rapport à la dernière carte de la ligne
                    for (int d=0; d<this.getHand().size(); d++) {
                        if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size()-1).getNumber() == 3) {
                            //Initialise les valeurs cibles qui correspondent à la valeur de la dernière carte +1 et +2
                            int targetValue1 = GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size()-1).getNumber() + 1;
                            int targetValue2 = GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size()-1).getNumber() + 2;
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


        //Calcul si le nombre de têtes de chaque ligne est inférieur ou égal à 2
        //Si c'est le cas, alors l'IA choisit volontairement une carte faible
        //pour la faire sauter, en échange de la ligne trouvée (cf. GameLogic)
        for (int i=0; i<GameLogic.partie.getBoard().getLignes().size(); i++){
            int totalTetes2 = 0;
            for (int j=0; j<GameLogic.partie.getBoard().getLignes().get(i).size(); j++){
                totalTetes2 += GameLogic.partie.getBoard().getLignes().get(i).get(j).getBullHead();
            }
            if (totalTetes2 <= 2){
                for (Card cartesMainIa : this.getHand()){
                    //Si l'IA possède une carte faible (valeur <= 5)
                    if (cartesMainIa.getNumber() <= 5){
                        return cartesMainIa;
                    }
                }
            }
        }


        /*
        //A laisser ici pour l'instant, peut petre utile après
        //Check de la ligne qui a le plus de têtes
        int ligneMaxDeTete = -1;
        int maxValeur = Integer.MIN_VALUE;
        for (int a=0; a<=GameLogic.partie.getBoard().getLignes().size(); a++){
            int totalTete = 0;
            for (int b=0; b<=GameLogic.partie.getBoard().getLignes().get(a).size(); b++){
                totalTete += GameLogic.partie.getBoard().getLignes().get(a).get(b).getBullHead();
            }
            System.out.println("Le nombre total de têtes de la ligne " + a + "est de " + totalTete);

            if (totalTete > maxValeur) {
                maxValeur = totalTete;
                ligneMaxDeTete = a;
            }
        }
        System.out.println("La meilleure ligne est la ligne " + ligneMaxDeTete);
        return null;
        */


        //TEMPORAIRE
        //Méthode random : l'IA tire une carte au hasard
        int random = (int) (Math.random() * (this.getHand().size() - 1));
        return this.getHand().get(random);
    }

    /**
     * Stratégie de l'IA
     */
    public Card strategie2() {
        for (int c = 0; c < GameLogic.partie.getBoard().getLignes().size(); c++) {
            if (GameLogic.partie.getBoard().getLignes().get(c).size() == 4) {
                for (int d = 0; d < this.getHand().size(); d++) {
                    //Check si les deux cartes ont 1 de différence
                    if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size() - 1).getNumber() == 1) {
                        return this.getHand().get(d);
                    }
                }
            } else if (GameLogic.partie.getBoard().getLignes().get(c).size() == 3) {
                for (int d = 0; d < this.getHand().size(); d++) {
                    //Check si les deux cartes ont 1 de différence
                    if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size() - 1).getNumber() <= 2) {
                        return this.getHand().get(d);
                    }
                }
            } else if (GameLogic.partie.getBoard().getLignes().get(c).size() == 2) {
                for (int d = 0; d < this.getHand().size(); d++) {
                    //Check si les deux cartes ont 1 de différence
                    if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size() - 1).getNumber() <= 3) {
                        return this.getHand().get(d);
                    }
                }
            }
        }
        //Calcul si le nombre de têtes de chaque ligne est inférieur ou égal à 2
        //Si c'est le cas, alors l'IA choisit volontairement une carte faible
        //pour la faire sauter, en échange de la ligne trouvée (cf. GameLogic)
        for (int i = 0; i < GameLogic.partie.getBoard().getLignes().size(); i++) {
            int totalTetes2 = 0;
            for (int j = 0; j < GameLogic.partie.getBoard().getLignes().get(i).size(); j++) {
                totalTetes2 += GameLogic.partie.getBoard().getLignes().get(i).get(j).getBullHead();
            }
            if (totalTetes2 <= 2) {
                for (Card cartesMainIa : this.getHand()) {
                    //Si l'IA possède une carte faible (valeur <= 5)
                    if (cartesMainIa.getNumber() <= 12) {
                        return cartesMainIa;
                    }
                }
            }
        }
        for (int c = 0; c < GameLogic.partie.getBoard().getLignes().size(); c++) {
            if (GameLogic.partie.getBoard().getLignes().get(c).size() <= 4) {
                int ecart = 0;
                for (int e = 0; e < 104; e++)
                    for (int d = 0; d < this.getHand().size(); d++) {
                        //Check si les deux cartes ont 2 ou moins de différence
                        if (this.getHand().get(d).getNumber() - GameLogic.partie.getBoard().getLignes().get(c).get(GameLogic.partie.getBoard().getLignes().get(c).size() - 1).getNumber() == e) {
                            return this.getHand().get(d);
                        }

                    }
            }
        }
        boolean isFull = GameLogic.partie.getBoard().getLignes().get(0).size() == 5 && GameLogic.partie.getBoard().getLignes().get(1).size() == 5 && GameLogic.partie.getBoard().getLignes().get(2).size() == 5 && GameLogic.partie.getBoard().getLignes().get(3).size() == 5;
        if (isFull == true){
            int min = 0;
            for (int i = 0; i < GameLogic.partie.getBoard().getLignes().size(); i++) {
                if (GameLogic.nbBullLigne(GameLogic.partie.getBoard().getLignes().get(i)) < GameLogic.nbBullLigne(GameLogic.partie.getBoard().getLignes().get(min))) {
                    min = i;
                }
                for (int j = GameLogic.partie.getBoard().getLignes().get(i).get(4).getNumber() + 20; j <= GameLogic.partie.getBoard().getLignes().get(i).get(4).getNumber(); j-- ) {
                    for (int k = 0; k < this.getHand().size(); k++) {
                        if (this.getHand().get(k).getNumber() > j) {
                            return this.getHand().get(k);

                        }
                    }
                }
            }
        }
        int random = (int) (Math.random() * (this.getHand().size() - 1));
        return this.getHand().get(random);
    }

    /**
     * Fais jouer l'IA
     */
    public void play () {
        // int aléatoire entre 0 et ia.getHand().size()
        Card carte = strategie2();
        GameLogic.partie.getCartesJouees().put(this, carte);
        this.getHand().remove(carte);

    }
}


