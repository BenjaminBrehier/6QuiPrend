package fr.benjaminbrehier._6quiprend.Model;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class Card {
    private int number;
    private int bullHead;
    private Pane rectangle;

    public Card(int number, int bullHead){
        this.number = number;
        this.bullHead = bullHead;
        this.rectangle = new Pane();
        // Ajouter dans ce Pane, un rectangle symbolisant la carte et dans son centre un label indiquant le number de la carte
        Rectangle rectangle = new Rectangle();
        // mettre rectanvle en rouge
        rectangle.setFill(Color.WHITE);
        // mettre rectangle en 100x100
        rectangle.setWidth(100);
        rectangle.setHeight(150);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(5);
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        rectangle.setStrokeDashOffset(0);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setRotate(0);
        rectangle.setVisible(true);
        rectangle.toFront();
        // Afficher au centre du rectangle le number
        Label label = new Label(String.valueOf(number));
        label.setStyle("-fx-font-size: 50px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: grey; -fx-font-weight: bold;");
        // Centrer le label
        label.setPrefWidth(100);
        label.setPrefHeight(150);
        if (number < 10) {
            label.setLayoutX(35);
        } else if (number > 99) {
            label.setLayoutX(7);
        } else {
            label.setLayoutX(20);
        }
        label.setVisible(true);
        label.toFront();
        // Ajouter une ImageView au centre du rectangle
        ImageView imageView = new ImageView(new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/tauraux.png").toURI().toString()));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setLayoutX(10);
        imageView.setLayoutY(35);
        imageView.setVisible(true);
        imageView.toFront();
        
        // Ajouter le rectangle et le label au Pane
        this.rectangle.getChildren().add(rectangle);
        this.rectangle.getChildren().add(imageView);
        this.rectangle.getChildren().add(label);
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

    public Pane getRectangle(){
        return rectangle;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", bullHead=" + bullHead +
                '}';
    }

}
