package de.kozdemir.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.kozdemir.client.App;
import de.kozdemir.client.model.Product;
import de.kozdemir.client.model.ProductRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Label fehler;
	@FXML
	private TableView<Product> tblProducts;

	private ProductRepository management = new ProductRepository();

	@FXML
	private void switchToNext() throws IOException {
		App.setRoot("next");
	}

	@FXML
	private void save() throws IOException {
		Product product = new Product();

		product.setName(name.getText());
		product.setDescription(description.getText());
		try {
			product.setAmount(Integer.parseInt(amount.getText()));
			product.setPrice(Double.parseDouble(price.getText()));
			fehler.setText("");
			management.add(product);
			clearForm();
			show();
		} catch (NumberFormatException e) {
			fehler.setText("invalid amount and/or price !");
		}
	}

	@FXML
	private void delete() {
		// TODO: Exception fangen
		Product p = tblProducts.getSelectionModel().getSelectedItem();
		management.delete(p);
		show();
	}

	private void clearForm() {
		name.clear();
		description.clear();
		amount.clear();
		price.clear();
	}

	private void show() {
		try {
			tblProducts.setItems(FXCollections.observableList(management.getAll())); // table print
		} catch (NullPointerException e) {
			System.out.println("Null liste...");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		show();

	}
}
