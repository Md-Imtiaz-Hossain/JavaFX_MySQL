package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class CreateInternetBankingAccountController {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();


    @FXML
    private TextField accountNumber,userName, confirmUserName, password, confirmPassword;

    @FXML
    private Button cancel,submit;

    @FXML
    private Label warning;



    public void submitOnAction(ActionEvent event) throws IOException {

        Connection connection = createConnectionDemo.createConnection();


        if (!accountNumber.getText().isEmpty() && !userName.getText().isEmpty()
                && !confirmUserName.getText().isEmpty() && !password.getText().isEmpty()
                && !confirmPassword.getText().isEmpty()){


                    if (confirmUserName.getText().equals(userName.getText()) && password.getText().equals(confirmPassword.getText())){
                                if (isExiecute()){
                                    try{
                                        Parent root = FXMLLoader.load(getClass().getResource("Photo&Signature.fxml"));
                                        Stage primaryStage = new Stage();
                                        primaryStage.setTitle("Upload Photo And Signature..");
                                        Scene scene = new Scene(root, 600, 601); //"/image/login.png"
                                        scene.getStylesheets().add("/Style/style.css");
                                        primaryStage.resizableProperty().setValue(false);
                                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                                        primaryStage.setScene(scene);
                                        primaryStage.show();
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }finally {
                                                //get a handle to the stage
                                                Stage stage = (Stage) cancel.getScene().getWindow();
                                                //do what you have to do
                                                stage.close();

                                                accountNumber.setText("");
                                                userName.setText("");
                                                confirmUserName.setText("");
                                                password.setText("");
                                                confirmPassword.setText("");


                                        System.out.println("Try A Different AccountNumber or Email or Phone or NidPassportNo");
                                        TrayNotification t = new TrayNotification();
                                        //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                                        AnimationType a = AnimationType.POPUP;
                                        t.setAnimationType(a);
                                        t.setTitle("Success !!!");
                                        t.setMessage("Successfully Created Internet Banking Account.");
                                        //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                                        t.setNotificationType(NotificationType.SUCCESS);
                                        t.showAndDismiss(Duration.millis(4000));
                                            }

                                }else {
                                    warning.setText("Try a Different UserName...");
                                }
                    }else if (!confirmUserName.getText().equals(userName.getText())){
                        warning.setText("User Name Must be Same...");
                    }else if (!password.getText().equals(confirmPassword.getText())){
                        warning.setText("Password Must be Same...");
                    }
        }else{
        warning.setText("Please Fill Up All Field");
        }


    }



    public void cancelOnAction(ActionEvent event) {
        //get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        //do what you have to do
        stage.close();
    }




    public boolean isExiecute(){
        Connection connection = createConnectionDemo.createConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            statement.execute("update abcd_bank.users set userName = '"+userName.getText()+"' , password = '"+password.getText()+"' where Account_Number = '"+accountNumber.getText()+"' ;  ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
