package at.spengergasse.dontgetangry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Spiel extends Application {


    public static final double WindowHeight = 720;
    public static final double WindowWidth = WindowHeight*1.5;
    //globale variable für größe eines Spielfeldes
    public static final double TileSize = (WindowHeight/11)-((WindowHeight*0.01)*2);
    public static final double GridPadding = WindowHeight*0.01;



    //zeichnet das komplette Spielfeld


    @Override
    public void start(Stage stage) throws IOException {


        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Dont get angry");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static boolean ArrayContains(int[][] twoDArray, int[] contains){
        for(int[] array:twoDArray){
            if(array[1] == contains[1] && array[0] == contains[0]){
                return true;
            }
        }
        return false;
    }

    //Wenn man das lesen kann hat's funktioniert!

    //Hoffetlichsehtihrdas

    //Versuch Stude 2 lorant
}