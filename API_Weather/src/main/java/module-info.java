module com.mycompany.api_weather {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.api_weather to javafx.fxml;
    exports com.mycompany.api_weather;
    requires org.json;
}
