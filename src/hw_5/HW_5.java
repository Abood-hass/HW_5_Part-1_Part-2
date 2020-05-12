package hw_5;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AboodHassKov
 */
public class HW_5 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane Student_Pane = FXMLLoader.load(getClass().getResource("The_GUI.fxml"));
        
        Scene S = new Scene(Student_Pane);
        primaryStage.setTitle("Student Control Panel");
        primaryStage.setScene(S);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
