package longong.atmapplication.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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

public class MainMenuController extends LoginController{

    // Instance variable to hold the accepted customer object
    private Customer loginCustomer;
    @FXML
    private Label customerInfoLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView mainmenuImage;
    @FXML
    private Button balanceButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button depositButton;
    @FXML
    private Button logoutButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button transactionHistoryButton;

    // Method to set the accepted customer object
    public void setAcceptedCustomer(Customer customer) {
        loginCustomer = customer;
        System.out.println("customer pass" + customer);
        System.out.println("Accepted customer in MainMenuController: " + loginCustomer);
        // if there is an accepted customer, display their name
        if (loginCustomer != null) {
            String customerName = loginCustomer.getName();
            userNameLabel.setText("Welcome, " + customerName);
        } else {
            // if there is no accepted customer, show an error message
            System.out.println("Error: no accepted customer.");
        }
    }
    @FXML
    private void debug(ActionEvent event) {
        if (loginCustomer != null) {
            customerInfoLabel.setText("Name: " + loginCustomer.getName() + ", Account Number: " + loginCustomer.getCardNumber() + ", Balance: " + loginCustomer.getBalance());
        }
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

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
        File file = new File("src/main/java/longong/atmapplication/image/mainmenuImage.jpg");
        Image image = new Image(file.toURI().toString());
        mainmenuImage.setImage(image);

        //set button property
        logoutButton.setStyle("-fx-background-color: #BA0F0F;-fx-text-fill: #FFFFFF;-fx-border-color: transparent;");
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle("-fx-background-color: #8e0b0b;-fx-text-fill: #FFFFFF;-fx-border-color: transparent;"));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle("-fx-background-color: #BA0F0F;-fx-text-fill: #FFFFFF;-fx-border-color: transparent;"));

        balanceButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        balanceButton.setOnMouseEntered(e -> balanceButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        balanceButton.setOnMouseExited(e -> balanceButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        withdrawButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        withdrawButton.setOnMouseEntered(e -> withdrawButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        withdrawButton.setOnMouseExited(e -> withdrawButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        depositButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        depositButton.setOnMouseEntered(e -> depositButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        depositButton.setOnMouseExited(e -> depositButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        transferButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        transferButton.setOnMouseEntered(e -> transferButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        transferButton.setOnMouseExited(e -> transferButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));

        transactionHistoryButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;");
        transactionHistoryButton.setOnMouseEntered(e -> transactionHistoryButton.setStyle("-fx-background-color: #bfbfbf;-fx-border-color: transparent;"));
        transactionHistoryButton.setOnMouseExited(e -> transactionHistoryButton.setStyle("-fx-background-color: #D9D9D9;-fx-border-color: transparent;"));
    }
    @FXML
    void showBalanceView(ActionEvent event) {
        try {
            // Load the FXML file for the balance window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("balance.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            BalanceController BalanceController = mainMenuLoader.getController();
            BalanceController.initData(loginCustomer); // pass the user object to the balance screen

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showWithdrawView(ActionEvent event) {
        try {
            // Load the FXML file for the withdraw window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("withdraw.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            WithdrawController withdrawController = mainMenuLoader.getController();
            withdrawController.initData(loginCustomer); // pass the user object to the withdraw screen

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showDepositView(ActionEvent event) {
        try {
            // Load the FXML file for the deposit window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("deposit.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            DepositController depositController = mainMenuLoader.getController();
            depositController.initData(loginCustomer); // pass the user object to the deposit screen

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showTransferView(ActionEvent event) {
        try {
            // Load the FXML file for the transfer window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("etransfer.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ETransferController etransferController = mainMenuLoader.getController();
            etransferController.initData(loginCustomer); // pass the user object to the etransfer screen

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showTransactionHistoryView(ActionEvent event) {
        try {
            // Load the FXML file for the transaction history window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("transactionHistory.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            TransactionHistoryController transactionHistoryController = mainMenuLoader.getController();
            transactionHistoryController.initData(loginCustomer); // pass the user object to the transactionHistory screen

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginView(ActionEvent event) {
        try {
            // Load the FXML file for the transaction history window
            FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Parent mainMenuRoot = mainMenuLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene and show the stage
            Scene scene = new Scene(mainMenuRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
