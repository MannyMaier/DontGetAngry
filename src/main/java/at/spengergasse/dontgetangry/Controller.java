package at.spengergasse.dontgetangry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Random;

//TODO: Id bestimmen für positionen; Spielfiguren einfügen als Buttons (mit Bild als Hintergrund)
//Vorschlag: Eine Klasse Spieler; jeder Spieler hat 4 Figuren (Liste) -> über die können die Spielfiguren angezeigt werden usw., Spielstein hat x, y koordinate und Spieler als Attrbiut

public class Controller {
    Parent spielfeld = erstelleSpielfeld();
    @FXML
    private Pane spielfeldpane;

    @FXML
    private ImageView diceimage;

    private ImageView spielfigur;

    @FXML
    private Button rollButton;

    private int rolledInt;

    Random random = new Random();


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public Controller() {

    }

    @FXML
    public void initialize() {
        spielfeldpane.getChildren().add(erstelleSpielfeld());
    }

    @FXML
    void roll(ActionEvent event) {

        rollButton.setDisable(true);
        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int i = 0; i < 15; i++) {
                        rolledInt = random.nextInt(6) + 1;
                        File image = new File("disrc/main/java/at/spengergasse/dontgetangry/resource/ce" + rolledInt + ".png");
                        diceimage.setImage(new Image(image.toURI().toString()));
                        System.out.println(rolledInt);
                        Thread.sleep(50);
                    }
                    rollButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    @FXML
    void onClickSavedb (ActionEvent Actionevent){
        System.out.println("Das Spiel wurde in der Datenbank gespeichert");

    }

    @FXML
    void regelnlesen (ActionEvent Actionevent){
        System.out.println("Hier erscheinen die regeln");
    }


    private Parent erstelleSpielfeld() {
        Group squareGroup = new Group();
        Group spielsteinGroup = new Group();

        int[][] normalSquarePositions = { {7, 2}, {7, 3}, {7, 4}, {7, 5}, {8, 5}, {9, 5}, {10, 5}, {11, 5}, {11, 6}, {10, 7}, {9, 7}, {8, 7}, {7, 7}, {7, 8}, {7, 9}, {7, 10}, {7, 11},
                {6, 11}, {5, 10}, {5, 9}, {5, 8}, {5, 7}, {4, 7}, {3, 7}, {2, 7}, {1, 7}, {1, 6}, {2, 5}, {3, 5}, {4, 5}, {5, 5}, {5, 4}, {5, 3}, {5, 2}, {5, 1}, {6, 1}};

        Pane pane = new Pane();

        //Hilfsvariablen für das Koordinatensystem
        int xGrid = 1;
        int yGrid = 1;
        int id = 1;
        for (double x = Spiel.GridPadding; x < Spiel.WindowHeight; x += Spiel.TileSize + 2 * Spiel.GridPadding) {
            for (double y = Spiel.GridPadding; y < Spiel.WindowHeight; y += Spiel.TileSize + 2 * Spiel.GridPadding) {
                Square square = null;
                Spielstein spielstein = null;
                int[] position = {xGrid, yGrid};
                if (Spiel.ArrayContains(normalSquarePositions, position)) {
                    square = new Square(x, y, SquareType.NORMAL, id++);

                }
                //Zielfeld
                if (xGrid == 6) {
                    if (yGrid == 2 || yGrid == 3 || yGrid == 4 || yGrid == 5) {
                        square = new Square(x, y, SquareType.BLUE, 0);
                    }

                }
                //Wartefelder
                if (xGrid == 10) {
                    if (yGrid == 1 || yGrid == 2) {
                        square = new Square(x, y, SquareType.BLUE, 0);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.BLUE);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }


                }
                if (xGrid == 11) {
                    if (yGrid == 1 || yGrid == 2) {
                        square = new Square(x, y, SquareType.BLUE, 0);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.BLUE);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }


                }
                //Einstiegsfeld
                if (xGrid == 7) {
                    if (yGrid == 1) {
                        square = new Square(x, y, SquareType.BLUE, 0);

                    }


                }
                //Zielfeld
                if (xGrid == 6) {
                    if (yGrid == 7 || yGrid == 8 || yGrid == 9 || yGrid == 10) {
                        square = new Square(x, y, SquareType.YELLOW, 0);

                    }

                }
                //Wartefelder
                if (xGrid == 1) {
                    if (yGrid == 10 || yGrid == 11) {
                        square = new Square(x, y, SquareType.YELLOW, 0);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.YELLOW);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 2) {
                    if (yGrid == 10 || yGrid == 11) {
                        square = new Square(x, y, SquareType.YELLOW, 0);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.YELLOW);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                //Einstiegsfeld
                if (xGrid == 5) {
                    if (yGrid == 11) {
                        square = new Square(x, y, SquareType.YELLOW, 0);

                    }


                }
                //Zielfeld
                if (yGrid == 6) {
                    if (xGrid == 7 || xGrid == 8 || xGrid == 9 || xGrid == 10) {
                        square = new Square(x, y, SquareType.RED, 0);

                    }

                }
                //Wartefelder
                if (xGrid == 10) {
                    if (yGrid == 10 || yGrid == 11) {
                        square = new Square(x, y, SquareType.RED, 0);
                        spielstein = new Spielstein(TypeColor.RED, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.RED);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 11) {
                    if (yGrid == 10 || yGrid == 11) {
                        square = new Square(x, y, SquareType.RED, 0);
                        spielstein = new Spielstein(TypeColor.RED, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.RED);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                //Einstiegsfeld
                if (xGrid == 11) {
                    if (yGrid == 7) {
                        square = new Square(x, y, SquareType.RED, 0);

                    }


                }
                //Zielfeld
                if (yGrid == 6) {
                    if (xGrid == 2 || xGrid == 3 || xGrid == 4 || xGrid == 5) {
                        square = new Square(x, y, SquareType.GREEN, 0);

                    }

                }
                //Wartefelder
                if (xGrid == 1) {
                    if (yGrid == 1 || yGrid == 2) {
                        square = new Square(x, y, SquareType.GREEN, 0);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 2) {
                    if (yGrid == 1 || yGrid == 2) {
                        square = new Square(x, y, SquareType.GREEN, 0);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                //Einstiegsfeld
                if (xGrid == 1) {
                    if (yGrid == 5) {
                        square = new Square(x, y, SquareType.GREEN, 0);

                    }


                }
                if (square != null) {
                    squareGroup.getChildren().add(square);
                }
                if (spielstein != null) {
                    spielsteinGroup.getChildren().add(spielstein);
                }

                yGrid++;
            }
            xGrid++;
            yGrid = 1;
        }
        pane.getChildren().addAll(squareGroup);
        pane.getChildren().addAll(spielsteinGroup);
        return pane;

    }
}