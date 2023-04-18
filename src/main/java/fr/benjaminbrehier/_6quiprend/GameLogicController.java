package fr.benjaminbrehier._6quiprend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameLogicController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to 6 qui prend !");
    }
}