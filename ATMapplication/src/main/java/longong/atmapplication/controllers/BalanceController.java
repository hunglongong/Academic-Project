package longong.atmapplication.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import longong.atmapplication.HelloApplication;
import longong.atmapplication.data.Customer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BalanceController extends MainMenuController{
    private Customer customer;
    @FXML
    private Button backButton;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerCardNumberLabel;
    @FXML
    private ImageView balanceImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set time and date
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            timeLabel.setText(time);
            dateLabel.setText(currentDate.toString());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // get the image file from resources folder
        File file = new File("src/main/java/longong/atmapplication/image/balance.jpg");
        Image image = new Image(file.toURI().toString());
        balanceImage.setImage(image);


        //set button property
        backButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

    }
    public void initData(Customer customer) {
        this.customer = customer;
        balanceLabel.setText(String.format(" $%.2f", customer.getBalance()));
        customerNameLabel.setText(customer.getName());
        customerCardNumberLabel.setText(customer.getCardNumber());
    }

    public void goBack(ActionEvent event) {
        try {
            // Load the FXML file for the main menu window
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Parent root = loader.load();
            MainMenuController controller = loader.getController();
            controller.setAcceptedCustomer(customer);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the scene and show the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
