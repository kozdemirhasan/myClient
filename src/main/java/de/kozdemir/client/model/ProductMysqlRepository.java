package de.kozdemir.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.kozdemir.client.utils.ViewHelper;


public class ProductMysqlRepository {

	private static final String TABLE = "products";

	List<Product> products;

	private static ProductMysqlRepository instance;

	private ProductMysqlRepository() throws SQLException {
		createTable();
	}

	public static ProductMysqlRepository getInstance() throws SQLException {
		if (instance == null) {
			instance = new ProductMysqlRepository(); // LAZY
		}
		return instance;
	}

	public List<Product> getAll() throws SQLException {

		final String SQL = "SELECT * FROM " + TABLE;

		products = new ArrayList<>();

		try (Connection con = DatabaseUtils.getConnection(); Statement stmt = con.createStatement()) {

			if (stmt.execute(SQL)) {
				ResultSet results = stmt.getResultSet();
				while (results.next())
					products.add(create(results));
			}
		}

		return products;
	}

	public Product findById(int id) {
		throw new UnsupportedOperationException("Noch nicht implementiert");
	}

	public List<Product> search(String str) {

		List<Product> searchList = new ArrayList<>();

		for (Product p : products) {
			if (p.getName().toLowerCase().indexOf(str.toLowerCase()) >= 0)
				searchList.add(p);
			else if (p.getDescription().toLowerCase().indexOf(str.toLowerCase()) >= 0)
				searchList.add(p);

		}
		return searchList;

	}

	public boolean save(Product p) throws SQLException {
		if (p.getId() > 0) {
			return update(p);
		}
		return insert(p);
	}

	private boolean insert(Product p) throws SQLException {

		final String SQL = "INSERT INTO " + TABLE
				+ " (id, name, description, amount, price, created_at) VALUES(null, ?, ?, ?, ?, ?)";

		try (Connection con = DatabaseUtils.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {

			stmt.setString(1, p.getName());
			stmt.setString(2, p.getDescription());
			stmt.setInt(3, p.getAmount());
			stmt.setDouble(4, p.getPrice());
			// stmt.setDate(5, Date.valueOf(p.getCreatedAt()));
			stmt.setString(5, p.getCreatedAt().toString());
//			stmt.setString(5, p.getCreatedAtDE().toString());

			if (stmt.execute()) {
				return true;
			}

//			if (stmt.execute()) {
//				// Lösung für SQLite
//				ResultSet keys = con.createStatement().executeQuery("SELECT last_insert_rowid()");
//				if (keys.next()) {
//					p.setId(keys.getInt("last_insert_rowid()"));
//					return true;
//				}
//			}
		}
		return false;
	}

	private boolean update(Product p) throws SQLException {

		// TODO: Update für created_at einbauen
		final String SQL = "UPDATE " + TABLE + " SET name = ?, description = ?, amount = ?, price = ? WHERE id = ?";

		try (Connection con = DatabaseUtils.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {

			stmt.setString(1, p.getName());
			stmt.setString(2, p.getDescription());
			stmt.setInt(3, p.getAmount());
			stmt.setDouble(4, p.getPrice());
			stmt.setInt(5, p.getId());

			if (stmt.execute()) {
				return stmt.getUpdateCount() > 0;
			}
		}
		return false;

	}

	public boolean delete(Product p) throws SQLException {
		return delete(p.getId());
	}

	public boolean delete(int id) throws SQLException {

		final String SQL = "DELETE FROM " + TABLE + " WHERE id = " + id;

		try (Connection con = DatabaseUtils.getConnection(); Statement stmt = con.createStatement()) {
			stmt.execute(SQL);
			return stmt.getUpdateCount() > 0;
		}
	}

	private Product create(ResultSet result) throws SQLException {
		Product p = new Product();
		p.setId(result.getInt("id"));
		p.setName(result.getString("name"));
		p.setDescription(result.getString("description"));
		p.setAmount(result.getInt("amount"));
		p.setPrice(result.getDouble("price"));
		// p.setCreatedAt(result.getDate("created_at").toLocalDate());
		p.setCreatedAt(LocalDate.parse(result.getString("created_at")));

		return p;
	}

	private boolean createTable() throws SQLException {
		try(Connection dbh = DatabaseUtils.getConnection(); Statement stmt = dbh.createStatement()) {
			
			final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
					+ "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "name VARCHAR(50),"
					+ "description TEXT,"
					+ "amount INTEGER, " 
					+ "price REAL, "
					+ "created_at TEXT" 
					+" )";
			return stmt.executeUpdate(SQL) > 0;
		}
	}
	


}
