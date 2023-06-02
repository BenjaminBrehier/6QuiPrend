package fr.benjaminbrehier._6quiprend.Model;

public interface IAPlayer {

    /**
     * Algo de choix d'une carte pour une IA
     * @return Card choisie
     */
    public abstract Card strategie();

    /**
     * Fais jouer l'IA
     */
    public abstract void play();
}
