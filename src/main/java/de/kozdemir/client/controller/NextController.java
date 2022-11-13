package de.kozdemir.client.controller;

import java.io.IOException;

import de.kozdemir.client.App;
import javafx.fxml.FXML;

public class NextController {

	@FXML
	public void switchToStandard() throws IOException {
		App.setRoot("standard");
	}

}
