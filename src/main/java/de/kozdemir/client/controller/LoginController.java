package de.kozdemir.client.controller;

import java.io.IOException;
import java.sql.SQLException;

import de.kozdemir.client.App;
import de.kozdemir.client.model.ProductMysqlRepository;
import de.kozdemir.client.model.User;
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

		if (!userName.trim().equals("") || !password.trim().equals("")) {

			try {
				User user = ProductMysqlRepository.getInstance().userController(userName, password);

				if (user == null) {
					System.out.println("user name ve/veya parola hatali");
				} else if (user != null && user.getStatus() == 0) {
					System.out.println("Kullanici onayli degil...");
				} else if (user != null && user.getStatus() == 1) {
					App.setRoot("controller/standard");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			alertError("Warnung!", "Incorrect username and/or password");
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
