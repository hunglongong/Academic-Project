module longong.atmapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens longong.atmapplication to javafx.fxml;
    exports longong.atmapplication;
    exports longong.atmapplication.controllers;
    opens longong.atmapplication.controllers to javafx.fxml;
    opens longong.atmapplication.data to javafx.base;
}