package longong.atmapplication.controllers;

import java.io.*;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import longong.atmapplication.HelloApplication;
import longong.atmapplication.data.Customer;

public class LoginController implements Initializable {

    private Customer acceptedCustomer;
    @FXML
    private TextField cardNumberField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView loginImage;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorMessage;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button0;
    @FXML
    private Button backspaceButton;
    @FXML
    private Button dotButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set focus to the cardNumberField
        cardNumberField.requestFocus();

        // Add a listener to the pin field's focus property
        pinField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Set the focus to the pin field
                pinField.requestFocus();

                // Set the action for each number button to append the corresponding text to the pin field
                button1.setOnAction(event -> pinField.appendText(button1.getText()));
                button2.setOnAction(event -> pinField.appendText(button2.getText()));
                button3.setOnAction(event -> pinField.appendText(button3.getText()));
                button4.setOnAction(event -> pinField.appendText(button4.getText()));
                button5.setOnAction(event -> pinField.appendText(button5.getText()));
                button6.setOnAction(event -> pinField.appendText(button6.getText()));
                button7.setOnAction(event -> pinField.appendText(button7.getText()));
                button8.setOnAction(event -> pinField.appendText(button8.getText()));
                button9.setOnAction(event -> pinField.appendText(button9.getText()));
                button0.setOnAction(event -> pinField.appendText(button0.getText()));
                backspaceButton.setOnAction(event -> {
                    // Get the current text in the amount field
                    String currentText = pinField.getText();

                    // If the current text is not empty, remove the last character
                    if (!currentText.isEmpty()) {
                        pinField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                });
            }
        });
        cardNumberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Set the focus to the pin field
                cardNumberField.requestFocus();

                // Set the action for each number button to append the corresponding text to the pin field
                button1.setOnAction(event -> cardNumberField.appendText(button1.getText()));
                button2.setOnAction(event -> cardNumberField.appendText(button2.getText()));
                button3.setOnAction(event -> cardNumberField.appendText(button3.getText()));
                button4.setOnAction(event -> cardNumberField.appendText(button4.getText()));
                button5.setOnAction(event -> cardNumberField.appendText(button5.getText()));
                button6.setOnAction(event -> cardNumberField.appendText(button6.getText()));
                button7.setOnAction(event -> cardNumberField.appendText(button7.getText()));
                button8.setOnAction(event -> cardNumberField.appendText(button8.getText()));
                button9.setOnAction(event -> cardNumberField.appendText(button9.getText()));
                button0.setOnAction(event -> cardNumberField.appendText(button0.getText()));
                backspaceButton.setOnAction(event -> {
                    // Get the current text in the amount field
                    String currentText = cardNumberField.getText();

                    // If the current text is not empty, remove the last character
                    if (!currentText.isEmpty()) {
                        cardNumberField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                });
            }
        });

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
        File file = new File("src/main/java/longong/atmapplication/image/login.jpg");
        Image image = new Image(file.toURI().toString());
        loginImage.setImage(image);


    }


    @FXML
    void login(ActionEvent event) {
        // read the card number and pin values from the text file into a HashMap
        HashMap<String, Customer> customers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/longong/atmapplication/data/bankAccounts"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[0];
                String cardNumber = parts[1];
                String pin = parts[2];
                double balance = Double.parseDouble(parts[3]);

                Customer customer = new Customer(customerName, cardNumber, pin, balance);

                // add the customer object to the customers map using the card number as the key
                customers.put(cardNumber, customer);
            }
        } catch (IOException e) {
            System.err.println("Error reading bankAccounts.txt file: " + e.getMessage());
        }

        // check if the card number and PIN are correct
        String cardNumber = cardNumberField.getText();
        String pin = pinField.getText();
        if (customers.containsKey(cardNumber) && customers.get(cardNumber).getPin().equals(pin)) {
            // if the login is successful, go to the main menu window
            errorMessage.setText("");
            System.out.println("Login successful!");
            setAcceptedCustomer(customers.get(cardNumber));
            try {

                FXMLLoader mainMenuLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
                Parent mainMenuRoot = mainMenuLoader.load();
                MainMenuController mainMenuController = mainMenuLoader.getController();
                mainMenuController.setAcceptedCustomer(acceptedCustomer); // Pass in acceptedCustomer value
                mainMenuLoader.setController(mainMenuController);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(mainMenuRoot);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.err.println("Error loading mainMenu.fxml: " + ex.getMessage());
            }
        } else {
            // if the login is unsuccessful, show an error message
            errorMessage.setText("Invalid card number or PIN.");
            System.out.println("Login unsuccessful.");
        }
    }

    public Customer getAcceptedCustomer() {
        return acceptedCustomer;
    }

    public void setAcceptedCustomer(Customer acceptedCustomer) {
        this.acceptedCustomer = acceptedCustomer;
    }

    public void clear(ActionEvent actionEvent) {
        cardNumberField.setText("");
        pinField.setText("");
    }
    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void help(ActionEvent actionEvent) {
        // Show fail message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help Information");
        alert.setHeaderText("Contact Us");
        alert.setContentText("Please call (123)-456-7890 for more information");
        alert.showAndWait();
    }
}