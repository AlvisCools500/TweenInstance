module com.tweeninst.tweeninginstance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.tweeninst.tweeninginstance to javafx.fxml;
    exports com.tweeninst.tweeninginstance;
}