module de.kozdemir.myClient {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.kozdemir.myClient to javafx.fxml;
    exports de.kozdemir.myClient;
}
