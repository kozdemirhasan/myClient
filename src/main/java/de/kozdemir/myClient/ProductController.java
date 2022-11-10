package de.kozdemir.myClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductController implements Initializable  {

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
	

	private ProductManager management = new ProductManager();
	private Integer counter=0;

	@FXML
	private void save() throws IOException {		

		Product product = new Product();	
		
		letzteIDFind(); //letzte ID Find
		product.setId(++counter);
		
		product.setName(name.getText());
		product.setDescription(description.getText());
		try {
			product.setAmount(Integer.parseInt(amount.getText()));
			product.setPrice(Double.parseDouble(price.getText()));
			fehler.setText("");
			management.add(product);
			clearForm();
			show();
		}catch(NumberFormatException e) {
			fehler.setText("price and/or quantity part invalid!");
		}
		
	}

    private void clearForm() {
    	name.clear();
    	description.clear();
    	amount.clear();
    	price.clear();
    }
	
	private void show() {
		management.listProductsConcole(); // console
		try {
			tblProducts.setItems(FXCollections.observableList(management.getAll()));	//table print			
		}catch(NullPointerException e) {
			System.out.println("Null liste...");
		}
 
	}
	
	private void letzteIDFind() {
		for (Product t : management.getAll()) {
			if (t.getId() > counter) {
				counter = t.getId();

			}
		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		show();
		
	}
}
