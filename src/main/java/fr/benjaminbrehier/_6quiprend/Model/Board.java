package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board {
    private ArrayList<ArrayList<Card>> lignes;

    public Board(ArrayList<ArrayList<Card>> lignes){
        this.lignes = lignes;
    }

    public ArrayList<ArrayList<Card>> getLignes(){
        return lignes;
    }

    public void setLignes(ArrayList<ArrayList<Card>> lignes){
        this.lignes = lignes;
    }

    @Override
    public String toString() {
        return "Board{" +
                "lignes=" + lignes +
                '}';
    }


    public void reloadBoard(){
        VBox vBox = new VBox();
        vBox.setLayoutX(10);
        vBox.setLayoutY(10);
        vBox.setSpacing(10);
        for (int i = 0; i < lignes.size(); i++) {
            HBox vBox2 = new HBox();
            vBox2.setSpacing(10);
            for (int j = 0; j < lignes.get(i).size(); j++) {
                vBox2.getChildren().add(lignes.get(i).get(j).getRectangle());
            }
            vBox.getChildren().add(vBox2);
        }
        // Couleur de fond en blanc
        vBox.setStyle("-fx-background-color: white;");
        // Ajout de la VBox à la scène
        Scene scene = new Scene(vBox, 1440, 855);
        GameLogic.stage.setScene(scene);
    }
}
