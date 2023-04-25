package fr.benjaminbrehier._6quiprend;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameLogic extends Application {

    @FXML
    private ImageView logo;

    @FXML
    private Label nbJoueurLbl;

    @FXML
    private Slider sliderNbJoueur;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameLogic.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 855);
        stage.setTitle("6QuiPrend !");
        stage.setScene(scene);
        stage.show();

        Image image = new Image(new File("src/main/resources/fr/benjaminbrehier/_6quiprend/img/logo.jpeg").toURI().toString());
        logo = (ImageView) scene.lookup("#logo");
        logo.setImage(image);

        nbJoueurLbl = (Label) scene.lookup("#nbJoueurLbl");

        sliderNbJoueur = (Slider) scene.lookup("#slider");

        sliderNbJoueur.valueProperty().addListener((observable, oldValue, newValue) -> {
            nbJoueurLbl.setText("Combien de joueurs ? : " + newValue.intValue()); // Met à jour le label avec la nouvelle valeur du slider
        });
        nbJoueurLbl.setText("Combien de joueurs ? : 6");

    }

    public static void main(String[] args) {
        launch();
    }
}