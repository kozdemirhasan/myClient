package de.kozdemir.client.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.kozdemir.client.App;
import de.kozdemir.client.model.Product;
import de.kozdemir.client.model.ProductDbRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductController implements Initializable {

	@FXML
	private TextField name;
	@FXML
	private TextArea description;
	@FXML
	private TextField amount;
	@FXML
	private TextField price;
	@FXML
	private TextField search;
	@FXML
	private Label fehler;
	@FXML
	private TableView<Product> tblProducts;

	private ProductDbRepository management;

	@FXML
	private void switchToNext() throws IOException {
		App.setRoot("next");
	}
	
	@FXML
	private void search() throws SQLException {
		tblProducts.setItems(FXCollections.observableList(management.search(search.getText()))); // search print
	}
	

	@FXML
	private void insert() {
		save(true);
	}

	@FXML
	private void update() {
		save(false);
	}

	private void save(boolean insert) {
		Product product = null;
		if (insert) {
			product = new Product();
		} else {
			product = tblProducts.getSelectionModel().getSelectedItem();
		}
		product.setName(name.getText());
		product.setDescription(description.getText());
		product.setAmount(Integer.parseInt(amount.getText()));
		product.setPrice(Double.parseDouble(price.getText()));

		try {
			management.save(product);
			clearForm();
			show();
		} catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			e.printStackTrace();
		}
	}

	@FXML
	private void delete() {
		// TODO: Exception fangen
		Product p = tblProducts.getSelectionModel().getSelectedItem();
		try {
			management.delete(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show();
	}

	private void clearForm() {
		name.clear();
		description.clear();
		amount.clear();
		price.clear();
	}

	@FXML
	public void showInForm() {

		Product p = tblProducts.getSelectionModel().getSelectedItem();

		if (p != null) {
			name.setText(p.getName());
			description.setText(p.getDescription());
			amount.setText(p.getAmount() + "");
			price.setText(p.getPrice() + "");
		}
	}

	private void show() {
		try {
			tblProducts.setItems(FXCollections.observableList(management.find())); // table print
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			management = ProductDbRepository.getInstance();
		} catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			throw new RuntimeException(e);
		}

		tblProducts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showInForm();
		} // select tabPerson and person details printed
		);

		show();

	}
}
