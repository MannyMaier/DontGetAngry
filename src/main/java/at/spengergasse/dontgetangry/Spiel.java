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

    private Group squareGroup = new Group();

    //zeichnet das komplette Spielfeld
    private Parent erstelleSpielfeld(){

        int[][] normalSquarePositions = {{7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {8, 5}, {9, 5}, {10, 5}, {11, 5}, {11, 6}, {11, 7}, {10, 7}, {9, 7}, {8, 7}, {7, 7}, {7, 8}, {7, 9}, {7, 10}, {7, 11},
                {6, 11}, {5, 11}, {5, 10}, {5, 9}, {5, 8}, {5, 7}, {4, 7}, {3, 7}, {2, 7}, {1, 7}, {1, 6}, {1, 5}, {2, 5}, {3, 5}, {4, 5}, {5, 5}, {5, 4}, {5, 3}, {5, 2}, {5, 1}, {6, 1}};

        Pane pane = new Pane();
        //Hilfsvariablen für das Koordinatensystem
        int xGrid = 1;
        int yGrid = 1;
        int id = 1;
        for(double x = GridPadding; x<WindowHeight; x += TileSize+2*GridPadding){
            for(double y = GridPadding; y<WindowHeight; y += TileSize+2*GridPadding){
                Square square = null;
                int[] position = {xGrid, yGrid};
                if(ArrayContains(normalSquarePositions, position)){
                     square = new Square(x, y, SquareType.NORMAL, id++);

                }
                if(xGrid == 6){
                    if(yGrid == 2 || yGrid == 3 || yGrid == 4 || yGrid ==5){
                         square = new Square(x, y, SquareType.BLUE, 0);

                    }

                }
                if(xGrid == 6){
                    if(yGrid == 7 || yGrid == 8 || yGrid == 9 || yGrid ==10){
                        square = new Square(x, y, SquareType.YELLOW, 0);

                    }

                }
                if(yGrid == 6){
                    if(xGrid == 7 || xGrid == 8 || xGrid == 9 || xGrid ==10){
                        square = new Square(x, y, SquareType.RED, 0);

                    }

                }
                if(yGrid == 6){
                    if(xGrid == 2 || xGrid == 3 || xGrid == 4 || xGrid ==5){
                        square = new Square(x, y, SquareType.GREEN, 0);

                    }

                }
                if (square != null) {
                    squareGroup.getChildren().add(square);
                }

                yGrid ++;
            }
            xGrid ++;
            yGrid = 1;
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