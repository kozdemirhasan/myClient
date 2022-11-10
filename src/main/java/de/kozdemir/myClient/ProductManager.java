package de.kozdemir.myClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ProductManager {

	private static final String FILE_NAME = "data.ser";
	private List<Product> products;

	public ProductManager() {
		readFromFile();
	}

	public void add(Product product) {
		products.add(product);
		saveToFile();

	}

	public List<Product> getAll() {
		// return products;
		return Collections.unmodifiableList(products); // Es wird nicht die Originalliste, sondern eine nuveränderbare
														// Kopie wietergegeben
	}

	public void listProductsConcole() {
		System.out.printf("%5s | %-15s | %-25s | %-30s | %s \n", "id", "Name", "Beschreibung", "Seit", "Preis");
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		for (Product product : products) {
			System.out.printf("%5s | %-15s | %-25s | %-30s | %.2f EUR \n", product.getId(), product.getName(),
					product.getDescription(), product.getCreatedAt(), product.getPrice());

		}
		System.out.println();

	}

	// IO, NIO
	// IO: Strings als Dateinamen oder File-Objekte
	// NIO: Strings als Dateinamen oder Path-Objekte

	private void readFromFile() {

		// Einheitlichen Standard wie z.B. XML, JSON oder CSV verwenden

		// Resource muss das Interface AutoClosable implementieren
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
			
			products = (List<Product>) in.readObject();
			// in wird automatisch geschlossen
		} catch (Exception e) {
			// ...oder eine neue leere Liste gebaut
			products = new ArrayList<>();
		}
	}

	private void saveToFile() {

		// Einheitlichen Standard wie z.B. XML, JSON oder CSV verwenden

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
	
			out.writeObject(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
