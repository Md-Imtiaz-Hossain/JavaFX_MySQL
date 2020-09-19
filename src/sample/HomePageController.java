package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class HomePageController  {
    @FXML
    private Button signIn;
    @FXML
    private Button showPasswordButton;
    @FXML
    private Button signUp;
    @FXML
    private Button signUpForBankAccount;
    @FXML


    private PasswordField passwordField;
    @FXML
    private TextField usernameTextField;


    @FXML
    private Label showPass;


    public void showPasswordButtonOnAction(ActionEvent event){
        String password = passwordField.getText();
        showPass.setText("Password- " + password);
    }


    public void signInOnAction(javafx.event.ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SignIn");
            Scene scene = new Scene(root, 700, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void signUpForBankAccountOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("CreateBankAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create A Bank Account");
            Scene scene = new Scene(root, 550, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/CreateBankAccount.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void signUpOnAction(javafx.event.ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SignUp");
            Scene scene = new Scene(root, 355, 392); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
