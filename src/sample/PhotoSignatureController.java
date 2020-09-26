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
import javafx.scene.image.ImageView;
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
    File file;
    Image image;
    String getPhotoPath,getSignPath;


    @FXML
    private TextField accountNumber;

    @FXML
    private Button cancel,submit;

    @FXML
    private Label photoPath,signPAth,warning;

    @FXML
    private ImageView imageView,signView;




    public void submitOnAction(ActionEvent event) throws IOException, SQLException {

        Connection connection = createConnectionDemo.createConnection();


        if (!accountNumber.getText().isEmpty() && accountNumber.getText().length() == 16 &&
                !getPhotoPath.isEmpty() && !getSignPath.isEmpty()){

                if (isExecute()){

                    Stage stage = (Stage) submit.getScene().getWindow();
                    //do what you have to do
                    stage.close();


                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Upload Photo And Signature..");
                        Scene scene = new Scene(root, 500, 500); //"/image/login.png"
                        scene.getStylesheets().add("/Style/style.css");
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        System.out.println("Registration  Success...");

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        TrayNotification t = new TrayNotification();
                        //AnimationType a = AnimationType.FADE;//AnimationType a = AnimationType.SLIDE;
                        AnimationType a = AnimationType.POPUP;
                        t.setAnimationType(a);
                        t.setTitle("Success !!!");
                        t.setMessage("Successfully Upload Photo And Signature.");
                        //t.setNotificationType(NotificationType.ERROR);//t.setNotificationType(NotificationType.NOTICE);  //t.setNotificationType(NotificationType.WARNING);
                        t.setNotificationType(NotificationType.SUCCESS);
                        t.showAndDismiss(Duration.millis(4000));


                    }

                }else {
                    warning.setText("Invalid Account Number. Try Again.");
                    System.out.println("Invalid Account Number...");
                }

        }else{
            warning.setText("Enter Your 16 Digit Account Number & SElect Photo and Sign");
            System.out.println("Enter Your 16 Digit Account Number");
        }


    }



    public void cancelButtonOnAction(ActionEvent event) {
        //get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        //do what you have to do
        stage.close();
    }



    public boolean isExecute(){
        Connection connection = createConnectionDemo.createConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            statement.execute("update abcd_bank.users set image = '"+getPhotoPath+"' , sign = '"+getSignPath+"' where Account_Number = '"+accountNumber.getText()+"' ;  ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void photoSelectOnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                (new FileChooser.ExtensionFilter("Jpg File", "*.jpg")),
                (new FileChooser.ExtensionFilter("Png File", "*.png")),
                (new FileChooser.ExtensionFilter("Gif File", "*.gif")) );

        file = fileChooser.showOpenDialog(null);

        if (file != null){
            photoPath.setText("Selected File--> " + file.getAbsolutePath());
            image = new Image(file.toURI().toString());
            getPhotoPath=file.getAbsolutePath();
            System.out.println("Selected File--> " + getPhotoPath);
            imageView.setImage(image);
        }

    }

    public void signSelectOnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                (new FileChooser.ExtensionFilter("Jpg File", "*.jpg")),
                (new FileChooser.ExtensionFilter("Png File", "*.png")),
                (new FileChooser.ExtensionFilter("Gif File", "*.gif")) );

        file = fileChooser.showOpenDialog(null);

        if (file != null){
            signPAth.setText("Selected File--> " + file.getAbsolutePath());
            image = new Image(file.toURI().toString());
            getSignPath=file.getAbsolutePath();
            System.out.println("Selected File--> " + getSignPath);
            signView.setImage(image);
        }

    }
}
