package fr.benjaminbrehier._6quiprend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class GameLogicController {
    @FXML
    private Slider slider;

    public void onPlayClick(ActionEvent actionEvent) {
        System.out.println("Start the game with " + (int) slider.getValue() + "players.");
    }
}