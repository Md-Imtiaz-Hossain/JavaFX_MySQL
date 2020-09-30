package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomePageController  {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();
    Statement statement;

    @FXML
    private Button signIn,signUpForBankAccount,signUp,showPasswordButton,forgotPassword;

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameTextField;

    @FXML
    private Label showPass,l1,l2,l3;




    public void showPasswordButtonOnAction(ActionEvent event){
        String password = passwordField.getText();
        showPass.setText("Password- " + password);
    }




    public void signInOnAction(ActionEvent event) {

        if (passwordField.getText().isEmpty() && usernameTextField.getText().isEmpty()) {
            l3.setText("");
            l2.setText("Must Enter Your Password");
            l1.setText("Must Enter Your UserName");
        } else if (usernameTextField.getText().isEmpty()) {
            l1.setText("Must Enter Your UserName");
            l3.setText("");
            l2.setText("");
        } else if (passwordField.getText().isEmpty()) {
            l3.setText("");
            l2.setText("Must Enter Your Password");
            l1.setText("");
        }else if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            Connection connection = createConnectionDemo.createConnection();


            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("  SELECT count(1) FROM abcd_bank.users where userName = '"+ usernameTextField.getText() +"' and password = '"+ passwordField.getText() +"';  ");

                while (resultSet.next()){

                        if (resultSet.getInt(1) == 1){
                            System.out.println("Congratulation !! Successfully LogIn !!");

                            Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                            Stage primaryStage = new Stage();
                            primaryStage.setTitle("SignIn");
                            primaryStage.getIcons().add(new Image("/image/3rd.jpg"));

                            Scene scene = new Scene(root, 500, 500); //"/image/login.png"
                            scene.getStylesheets().add("/Style/style.css");
                            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                            primaryStage.setScene(scene);
                            primaryStage.show();

                            usernameTextField.setText("");passwordField.setText("");

                            TrayNotification t = new TrayNotification();
                            //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                            AnimationType a = AnimationType.POPUP;
                            t.setAnimationType(a);
                            t.setTitle("Success !!!");
                            t.setMessage("Successfully Loged In. ");
                            //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                            t.setNotificationType(NotificationType.SUCCESS);
                            t.showAndDismiss(Duration.millis(4000));

                            l1.setText("");
                            l2.setText("");
                            l3.setText("");
                            showPass.setText("");

                        }else {
                            l1.setText("");
                            l2.setText("");
                            l3.setText("Invalid UserName or Password!!!");
                            System.out.println("Invalid UserName or Password!!!");
                        }
                    }
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }




    public void signUpForBankAccountOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("CreateBankAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create A Bank Account");
            primaryStage.getIcons().add(new Image("/image/3rd.jpg"));

            Scene scene = new Scene(root, 550, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/CreateBankAccount.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }


    public void signUpOnAction(javafx.event.ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SignUp");
            primaryStage.getIcons().add(new Image("/image/3rd.jpg"));

            Scene scene = new Scene(root, 355, 392); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }

    public void forgotPasswordOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Recover Account");
            primaryStage.getIcons().add(new Image("/image/3rd.jpg"));
            Scene scene = new Scene(root, 355, 392); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }

    public void refreshImage(MouseEvent mouseEvent) {
        l1.setText("");
        l2.setText("");
        l3.setText("");
        showPass.setText("");
    }
}
