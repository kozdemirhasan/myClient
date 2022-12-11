package de.kozdemir.client.controller;

import java.io.IOException;

import de.kozdemir.client.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnLogin;

	@FXML
	private void login() {

		String userName = txtUserName.getText();
		String password = txtPassword.getText();

		if (userName.equals("1") && password.equals("1")) {
			try {
				App.setRoot("controller/standard");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			alertError( "Warnung!",  "Incorrect username and/or password");
		}

	}

	@FXML
	private void singUp() {

	}
	
	private void alertError(String titel, String info) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titel);
		alert.setHeaderText(info);
//		alert.setContentText(alert.toString());
		alert.showAndWait();
	}

}
