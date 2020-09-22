package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PhotoSignatureController {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();


    @FXML
    private TextField accountNumber;

    @FXML
    private Button cancel,submit;

    @FXML
    private Label photoPath,signPAth;




    public void submitOnAction(ActionEvent event) throws IOException, SQLException {

        Connection connection = createConnectionDemo.createConnection();


        if (!accountNumber.getText().isEmpty()){

                if (isExiecute()){
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Upload Photo And Signature..");
                        Scene scene = new Scene(root, 550, 550); //"/image/login.png"
                        scene.getStylesheets().add("/Style/style.css");
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        System.out.println("Registration  Success...");



                        //get a handle to the stage
                        Stage stage = (Stage) submit.getScene().getWindow();
                        //do what you have to do
                        stage.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        System.out.println("Try A Different AccountNumber or Email or Phone or NidPassportNo");
                        TrayNotification t = new TrayNotification();
                        //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                        AnimationType a = AnimationType.POPUP;
                        t.setAnimationType(a);
                        t.setTitle("Success !!!");
                        t.setMessage("Successfully Upload Photo And Signature.");
                        //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                        t.setNotificationType(NotificationType.SUCCESS);
                        t.showAndDismiss(Duration.millis(4000));

                        //get a handle to the stage
                        Stage stage = (Stage) submit.getScene().getWindow();
                        //do what you have to do
                        stage.close();

                        connection.close();
                    }

                }else {
                    System.out.println("Invalid Account Number...");
                }

        }else{
            System.out.println("Enter Your Account Number");
        }


    }



    public void cancelButtonOnAction(ActionEvent event) {
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
            statement.execute("update abcd_bank.users set image = '"+signPAth.getText()+"' , sign = '"+photoPath.getText()+"' where Account_Number = '"+accountNumber.getText()+"' ;  ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void photoSelectOnAction(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "*.jpg"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All File", "*.*"));
        List<File> f = fc.showOpenMultipleDialog(null);

        for (File file : f){
            photoPath.setText(String.valueOf(f.get(0)));
            System.out.println(file.getAbsolutePath());
        }
    }

    public void signSelectOnAction(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "*.jpg"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All File", "*.*"));
        List<File> f = fc.showOpenMultipleDialog(null);

        for (File file : f){
            signPAth.setText(String.valueOf(f.get(0)));
            System.out.println(file.getAbsolutePath());
        }

    }
}
