package fr.benjaminbrehier._6quiprend.Model;

import java.util.ArrayList;

import fr.benjaminbrehier._6quiprend.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
                vBox2.getChildren().add(lignes.get(i).get(j).getGraphicCard());
            }
            vBox.getChildren().add(vBox2);
        }
        
        
        HBox playerHand = new HBox();
        playerHand.setTranslateY(playerHand.getTranslateY()+20);
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setSpacing(10);

    
        for (Card card : GameLogic.players.get(0).getHand()) {
            playerHand.getChildren().add(card.getGraphicCard());
            card.getGraphicCard().setOnMouseEntered(mouseEvent -> {
                card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() - 10);
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.ORANGE);
            });

            card.getGraphicCard().setOnMouseExited(mouseEvent -> {
                card.getGraphicCard().setTranslateY(card.getGraphicCard().getTranslateY() + 10);
                ((Rectangle) card.getGraphicCard().getChildren().get(0)).setStroke(Color.BLACK);
            });

            // // Remove events on click
            // card.getGraphicCard().setOnMouseClicked(mouseEvent -> {
            //     card.getGraphicCard().setOnMouseEntered(null);
            //     card.getGraphicCard().setOnMouseExited(null);
            //     card.getGraphicCard().setOnMouseClicked(null);
            // });
        }
        
        vBox.getChildren().add(playerHand);
        vBox.setStyle("-fx-background-image: url('https://img.freepik.com/vecteurs-libre/table-manger-bois-vue-dessus-vecteur-realiste_107791-13011.jpg?w=1380&t=st=1683874217~exp=1683874817~hmac=4a55c4c2786ec3d84229b244ac8af4ad194f73d72b5687727a81fb6b482a77e5')");
        Scene scene = new Scene(vBox, 1440, 855);
        GameLogic.stage.setScene(scene);
    }
}
