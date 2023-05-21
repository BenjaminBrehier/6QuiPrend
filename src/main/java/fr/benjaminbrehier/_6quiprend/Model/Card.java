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
    private Pane graphicCard;
    private Pane backCard;

    public Card(int number, int bullHead) {
        this.number = number;
        this.bullHead = bullHead;
        this.graphicCard = new Pane();
        this.backCard = new Pane();

        ImageView dos = new ImageView();
        dos.setImage(new Image(
                new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/backCard.png").toURI().toString()));
        dos.setFitWidth(90);
        dos.setFitHeight(140);
        dos.setLayoutX(5);
        dos.setLayoutY(5);
        dos.setVisible(true);
        dos.toFront();

        Rectangle fondDos = new Rectangle();
        fondDos.setFill(Color.WHITE);
        fondDos.setWidth(100);
        fondDos.setHeight(150);
        fondDos.setStroke(Color.BLACK);
        fondDos.setStrokeWidth(4);
        fondDos.setStrokeLineCap(StrokeLineCap.ROUND);
        fondDos.setStrokeLineJoin(StrokeLineJoin.ROUND);
        fondDos.setStrokeDashOffset(0);
        fondDos.setArcWidth(20);
        fondDos.setArcHeight(20);
        fondDos.setRotate(0);
        fondDos.setVisible(true);
        fondDos.toFront();

        this.backCard.getChildren().add(fondDos);
        this.backCard.getChildren().add(dos);
        this.backCard.setPrefWidth(100);
        this.backCard.setPrefHeight(150);
        this.backCard.setVisible(true);
        this.backCard.toFront();


        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setWidth(100);
        rectangle.setHeight(150);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(4);
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        rectangle.setStrokeDashOffset(0);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setRotate(0);
        rectangle.setVisible(true);
        rectangle.toFront();

        // Afficher au centre du rectangle le number
        Label mainLabel = new Label(String.valueOf(number));
        // Centrer le mainLabel
        mainLabel.setPrefWidth(100);
        mainLabel.setPrefHeight(150);
        if (number < 10) {
            mainLabel.setLayoutX(35);
        } else if (number > 99) {
            mainLabel.setLayoutX(7);
        } else {
            mainLabel.setLayoutX(20);
        }
        mainLabel.setVisible(true);
        mainLabel.toFront();

        Rectangle rectCouleur = new Rectangle();
        rectCouleur.setWidth(80);
        rectCouleur.setHeight(90);
        rectCouleur.setVisible(true);
        rectCouleur.setLayoutX(10);
        rectCouleur.setLayoutY(30);
        rectCouleur.toFront();

        Image image = new Image(
                new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxViolet.png").toURI().toString());
        rectCouleur.setFill(Color.WHITE);

        Label cornerLeftHaut = new Label(String.valueOf(number));
        cornerLeftHaut.setLayoutX(5);
        cornerLeftHaut.setLayoutY(5);
        cornerLeftHaut.setVisible(true);
        cornerLeftHaut.toFront();

        Label cornerLeftBas = new Label(String.valueOf(number));
        cornerLeftBas.setLayoutX(5);
        cornerLeftBas.setLayoutY(130);
        cornerLeftBas.setVisible(true);
        cornerLeftBas.setRotate(180); // ! A garder ?
        cornerLeftBas.toFront();

        Label cornerRightHaut = new Label(String.valueOf(number));
        cornerRightHaut.setLayoutX(70);
        cornerRightHaut.setLayoutY(5);
        cornerRightHaut.setVisible(true);
        cornerRightHaut.toFront();

        Label cornerRightBas = new Label(String.valueOf(number));
        cornerRightBas.setLayoutX(70);
        cornerRightBas.setLayoutY(130);
        cornerRightBas.setVisible(true);
        cornerRightBas.setRotate(180); // ! A garder ?
        cornerRightBas.toFront();

        ImageView miniTetes = new ImageView();
        miniTetes.setImage(
                new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/miniTaurauxViolet.png").toURI()
                        .toString()));
        ImageView miniTetesBas = new ImageView();

        String colorMain = "#b69abd";
        String colorCorner = "#b69abd";
        miniTetes.setFitWidth(15);
        miniTetes.setFitHeight(15);
        miniTetes.setLayoutX(40);
        miniTetes.setLayoutY(5);
        miniTetesBas.setLayoutY(miniTetes.getLayoutY() + 128);
        if (bullHead == 2) {
            rectCouleur.setFill(Color.BLUE);
            image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxBleu.png").toURI()
                    .toString());
            colorMain = "#269d99";
            colorCorner = "#269d99";
            miniTetes.setImage(
                    new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/miniTaurauxBleu.png")
                            .toURI().toString()));
            miniTetes.setFitWidth(23);
            miniTetes.setFitHeight(12);
            miniTetes.setLayoutX(37);
            miniTetes.setLayoutY(8);
        } else if (bullHead == 3) {
            rectCouleur.setFill(Color.GREEN);
            image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxVert.png").toURI()
                    .toString());
            colorMain = "#f1cb39";
            colorCorner = "#96c354";
            miniTetes.setImage(
                    new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/miniTaurauxJaune.png")
                            .toURI().toString()));
            miniTetes.setFitWidth(30);
            miniTetes.setFitHeight(13);
            miniTetes.setLayoutX(34);
            miniTetes.setLayoutY(8);

            miniTetesBas.setLayoutY(miniTetes.getLayoutY() + 126);

        } else if (bullHead == 5) {
            rectCouleur.setFill(Color.RED);
            image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxBleu.png").toURI()
                    .toString());
            colorMain = "#96c354";
            colorCorner = "#FF0F00";
            miniTetes.setImage(
                    new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/miniTaurauxVerts.png")
                            .toURI().toString()));
            miniTetes.setFitWidth(33);
            miniTetes.setFitHeight(21);
            miniTetes.setLayoutX(32);
            miniTetes.setLayoutY(5);
            miniTetesBas.setLayoutY(miniTetes.getLayoutY() + 120);
        } else if (bullHead == 7) {
            rectCouleur.setFill(Color.YELLOW);
            rectCouleur.setWidth(80);
            rectCouleur.setHeight(125);
            rectCouleur.setLayoutX(10);
            rectCouleur.setLayoutY(13);

            image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/taurauxBleu.png").toURI()
                    .toString());
            colorMain = "#ff2857";
            colorCorner = "#2540c1";
            miniTetes.setImage(
                    new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/miniTaurauxRouge.png")
                            .toURI().toString()));
            miniTetes.setFitWidth(33);
            miniTetes.setFitHeight(21);
            miniTetes.setLayoutX(32);
            miniTetes.setLayoutY(5);
            miniTetesBas.setLayoutY(miniTetes.getLayoutY() + 120);
        }
        mainLabel.setStyle(
                "-fx-font-size: 50px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: "
                        + colorMain + "; -fx-font-weight: bold;");
        cornerRightHaut.setStyle(
                "-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: "
                        + colorCorner + "; -fx-font-weight: bold;");
        cornerLeftBas.setStyle(
                "-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: "
                        + colorCorner + "; -fx-font-weight: bold;");
        cornerLeftHaut.setStyle(
                "-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: "
                        + colorCorner + "; -fx-font-weight: bold;");
        cornerRightBas.setStyle(
                "-fx-font-size: 15px; -fx-font-family: monospace; -fx-background-color: transparent; -fx-text-fill: "
                        + colorCorner + "; -fx-font-weight: bold;");

        // Ajouter le logo au centre du rectangle
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(90);
        imageView.setFitHeight(80);
        imageView.setLayoutX(5);
        imageView.setLayoutY(35);
        imageView.setVisible(true);
        imageView.toFront();

        miniTetes.setVisible(true);
        miniTetes.toFront();

        miniTetesBas.setImage(miniTetes.getImage());
        miniTetesBas.setFitWidth(miniTetes.getFitWidth());
        miniTetesBas.setFitHeight(miniTetes.getFitHeight());
        miniTetesBas.setLayoutX(miniTetes.getLayoutX());
        miniTetesBas.setVisible(true);
        miniTetesBas.toFront();
        miniTetesBas.setRotate(180); // ! A garder?

        // HBox hbox = new HBox(10); // 10 est l'espacement entre les éléments
        // hbox.setAlignment(Pos.CENTER); // centre les éléments horizontalement
        // hbox.setPadding(new Insets(10)); // 10 est la marge autour de la HBox
        // hbox.getChildren().addAll(cornerLeftHaut, miniTetes, cornerRightHaut);

        // // configure la mise en page pour la HBox
        // hbox.setHgrow(cornerLeftHaut, Priority.ALWAYS);
        // hbox.setFillHeight(true);

        this.graphicCard.getChildren().add(rectangle);
        this.graphicCard.getChildren().add(rectCouleur);
        // this.graphicCard.getChildren().add(hbox);
        this.graphicCard.getChildren().add(miniTetes);
        this.graphicCard.getChildren().add(miniTetesBas);
        this.graphicCard.getChildren().add(cornerLeftHaut);
        this.graphicCard.getChildren().add(cornerLeftBas);
        this.graphicCard.getChildren().add(cornerRightHaut);
        this.graphicCard.getChildren().add(cornerRightBas);
        this.graphicCard.getChildren().add(imageView);
        this.graphicCard.getChildren().add(mainLabel);
        this.graphicCard.setPrefWidth(100);
        this.graphicCard.setPrefHeight(150);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBullHead() {
        return bullHead;
    }

    public void setBullHead(int bullHead) {
        this.bullHead = bullHead;
    }

    public Pane getGraphicCard() {
        return graphicCard;
    }

    public Pane getBackCard() {
        return backCard;
    }

    public void setBackCard(Pane backCard) {
        this.backCard = backCard;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", bullHead=" + bullHead +
                '}';
    }

}
