package de.kozdemir.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("controller/start-screen"));
		stage.setScene(scene);
		 stage.setResizable(false);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
//		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//		return fxmlLoader.load();

//		Locale lang = Locale.GERMAN;
//		Locale lang = Locale.UK;
	
//		FXMLLoader fxmlLoader = new FXMLLoader();
		ResourceBundle bundle = ResourceBundle.getBundle("de.kozdemir.client.lang.ui", Locale.getDefault());
		return FXMLLoader.load(App.class.getResource(fxml + ".fxml"), bundle);

	}

	public static void main(String[] args) {
		launch();
	}

}