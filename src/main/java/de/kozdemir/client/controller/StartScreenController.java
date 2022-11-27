package de.kozdemir.client.controller;


import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.kozdemir.client.App;

public class StartScreenController implements Initializable {

    class StartScreen extends Thread {
 
        @Override
        public void run() {
            try {
                Thread.sleep(5000); // Wartet 5 Sekunden, bevor es wieter geht

                Platform.runLater(() -> {
                    try {                    	
                        App.setRoot("controller/standard");
                    }
                    catch (IOException e) {
                        // TODO: Exception richtig behandeln
                        e.printStackTrace();
                    }
                });
            }
            catch (InterruptedException e) {
                // TODO: Exception richtig behandeln
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new StartScreen().run();
    }
}
