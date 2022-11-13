module de.kozdemir.myClient {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.kozdemir.client to javafx.fxml;
    opens de.kozdemir.client.controller to javafx.fxml;
    opens de.kozdemir.client.model to javafx.fxml, javafx.base;
    exports de.kozdemir.client;
}
