package de.kozdemir.client.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.kozdemir.client.App;
import de.kozdemir.client.model.Product;
import de.kozdemir.client.model.ProductSqliteRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductController implements Initializable { // Initializable bietet die Möglichkeit Tätigkeiten beim Starten der GUI auszuführen
	
	@FXML
	private TextField name;
	
	@FXML
	private TextArea description;
	
	@FXML
	private TextField amount;
	
	@FXML
	private TextField price;
	
	@FXML
	private TableView<Product> tblProducts;

	@FXML
	private TableColumn<Product, String> colPrice;

	@FXML
	private TableColumn<Product, Integer> colCount;

	private ProductSqliteRepository management;

	private int count = 0;
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}




}
