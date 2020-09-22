package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        primaryStage.setTitle("ABCD BANK");
        Scene scene = new Scene(root, 800, 800); //"/image/login.png"
        scene.getStylesheets().add("/Style/style.css");
        primaryStage.setScene(scene);
        //primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
        // primaryStage.initStyle(StageStyle.UTILITY); // Disable Mazimuise & Minimice Button
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
