module de.kozdemir.myClientWithSqlite {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens de.kozdemir.client to javafx.fxml;
    opens de.kozdemir.client.controller;
    opens de.kozdemir.client.model;
    exports de.kozdemir.client;
    
    
}
