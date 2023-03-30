package at.spengergasse.dontgetangry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Spiel extends Application {


    public static final double WindowHeight = 1000;
    public static final double WindowWidth = WindowHeight*1.5;
    //globale variable für größe eines Spielfeldes
    public static final double TileSize = (WindowHeight/11)-((WindowHeight*0.01)*2);
    public static final double GridPadding = WindowWidth*0.01;

    private Group squareGroup = new Group();

    //zeichnet das komplette Spielfeld
    private Parent erstelleSpielfeld(){
        Pane pane = new Pane();
        for(double x = TileSize/2+GridPadding; x<WindowHeight; x += TileSize+2*GridPadding){
            for(double y = TileSize/2+GridPadding; y<WindowHeight; y += TileSize+2*GridPadding){
               // if()
                Square square = new Square(x, y);
                squareGroup.getChildren().add(square);
            }
        }
        pane.getChildren().addAll(squareGroup);
        return pane;

    }

    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(Spiel.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(erstelleSpielfeld());
        stage.setTitle("Dont get angry");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //Wenn man das lesen kann hat's funktioniert!

    //Hoffetlichsehtihrdas

    //Versuch Stude 2 lorant
}