package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

//TODO: Id bestimmen für positionen; Spielfiguren einfügen als Buttons (mit Bild als Hintergrund)
//Vorschlag: Eine Klasse Spieler; jeder Spieler hat 4 Figuren (Liste) -> über die können die Spielfiguren angezeigt werden usw., Spielstein hat x, y koordinate und Spieler als Attrbiut


public class Controller {


    Parent spielfeld = erstelleSpielfeld();

    private static Spiellogik spiellogik = new Spiellogik();
    @FXML
    private Pane spielfeldpane;

    public ArrayList<Square> allSquares;

    @FXML Label LgewuerfelteZahl;

    private ArrayList<Spielstein> spielsteine = new ArrayList<Spielstein>();


    @FXML
    private ImageView diceimage;

    private ImageView spielfigur;

    @FXML
    private Button rollButton;

    @FXML
    private Button regelwerkButton;

    private int rolledInt;

    Random random = new Random();


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public Controller() {
        spiellogik.laden(allSquares, spielsteine, this);
        System.out.println("CON: "+spielsteine);
        //LgewuerfelteZahl.setText(String.valueOf(Spiellogik.wuerfel.getGewuerfelteZahl()));
        this.aktualisiereGrafik();
    }


    @FXML
    public void initialize() throws InterruptedException {
        spielfeldpane.getChildren().add(erstelleSpielfeld());
        spiellogik.laden(allSquares, spielsteine, this);
        System.out.println("CON: "+spielsteine);
        LgewuerfelteZahl.setText(String.valueOf(Spiellogik.wuerfel.getGewuerfelteZahl()));
        this.aktualisiereGrafik();
    }


    public void setSpielsteine (ArrayList<Spielstein> spielSteinlist){this.spielsteine = spielSteinlist;}
    public void setAllSquares (ArrayList<Square> squareArrayList){this.allSquares = squareArrayList;}

    private void aktualisiereGrafik() {
        for(Spielstein st : spielsteine){
            System.out.println("AKTSQUARE: "+st.getAktSquare());

            double xkor = st.getAktSquare().getXkor();
            double ykor = st.getAktSquare().getYkor();

            st.move(xkor, ykor);
            System.out.println("--------------");
            System.out.println("AKTSQUARE: "+st.getAktSquare());
        }
    }

    @FXML
    void roll(ActionEvent event) throws InterruptedException {

        rollButton.setDisable(true);
        int tfgewuerfelteZahl = Spiellogik.wuerfel.wuerfeln();
        LgewuerfelteZahl.setText(String.valueOf(tfgewuerfelteZahl));
        rollButton.setDisable(false);

    }

    @FXML
    void regelwerkButtonclick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Regeln");
        alert.setHeaderText("Don't get angry Regeln");
        alert.setContentText("1. Jeder von wählt seine Lieblingsfarbe und nimmt sich die entsprechenden vier Figuren, die er auf die gleichfarbigen Felder in einer der Ecken stellt. \n" +
                "2. Würfelt alle einmal – wer die höchste Zahl hat, darf anfangen. \n" +
                "3.Um die Startfigur ins Spiel bringen zu können, braucht man eine 6 – ebenso, wenn man weitere Figuren ins Spiel bringen will. Jeder muss immer so lange (nacheinander) würfeln, bis man ein 6 hat. \n" +
                "4. Wer an der Reihe ist, würfelt und darf seine Figur, beginnend mit der festgelegten Startfigur, entsprechend der Würfelaugen ziehen. Danach ist der Nächste von euch an der Reihe\n" +
                "5. Wer auf ein Feld ziehen kann, auf dem bereits eine Figur des Mitspielers steht, sollte das tun. Dadurch schlägt man sie und derjenige muss von vorne anfangen. Steht auf dem Feld eine eigene Figur, muss man sich eine andere aussuchen, denn selbst schlagen kann man sich nicht.\n" +
                "6. Nach einer Runde ums Feld kann man seine Figuren ins Ziel bringen. Dazu braucht es die genauen Würfelaugen.\n" +
                "7. Wer zuerst alle seine vier Figuren im Ziel hat, gewinnt. Die anderen können entscheiden, ob sie noch um die letzten Plätze spielen.\n");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();


    }

    @FXML
    void onClickSavedb (ActionEvent Actionevent) {

        spiellogik.speichern(this.spielsteine);

        System.out.println("Das Spiel wurde in der Datenbank gespeichert");

   }

    @FXML
    void regelnlesen (ActionEvent Actionevent){
        System.out.println("Hier erscheinen die regeln");
    }


    private Parent erstelleSpielfeld() {
        Group squareGroup = new Group();
        Group spielsteinGroup = new Group();
        allSquares = new ArrayList<>();

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

                    //NormalSquares Id
                    if (xGrid == 7) {
                        if (yGrid == 2) {
                            square = new Square(x, y, SquareType.NORMAL, 2);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 3) {
                            square = new Square(x, y, SquareType.NORMAL, 3);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 4) {
                            square = new Square(x, y, SquareType.NORMAL, 4);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 5);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 15);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 8) {
                            square = new Square(x, y, SquareType.NORMAL, 16);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 9) {
                            square = new Square(x, y, SquareType.NORMAL, 17);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 10) {
                            square = new Square(x, y, SquareType.NORMAL, 18);
                        }
                    }
                    if (xGrid == 7) {
                        if (yGrid == 11) {
                            square = new Square(x, y, SquareType.NORMAL, 19);
                        }
                    }
                    if (xGrid == 8) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 6);
                        }
                    }
                    if (xGrid == 8) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 14);
                        }
                    }
                    if (xGrid == 9) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 7);
                        }
                    }
                    if (xGrid == 9) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 13);
                        }
                    }
                    if (xGrid == 10) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 8);
                        }
                    }
                    if (xGrid == 10) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 12);
                        }
                    }
                    if (xGrid == 11) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 9);
                        }
                    }
                    if (xGrid == 11) {
                        if (yGrid == 6) {
                            square = new Square(x, y, SquareType.NORMAL, 10);
                        }
                    }
                    if (xGrid == 6) {
                        if (yGrid == 11) {
                            square = new Square(x, y, SquareType.NORMAL, 20);
                        }
                    }
                    if (xGrid == 6) {
                        if (yGrid == 1) {
                            square = new Square(x, y, SquareType.NORMAL, 40);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 10) {
                            square = new Square(x, y, SquareType.NORMAL, 22);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 9) {
                            square = new Square(x, y, SquareType.NORMAL, 23);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 8) {
                            square = new Square(x, y, SquareType.NORMAL, 24);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 25);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 35);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 4) {
                            square = new Square(x, y, SquareType.NORMAL, 36);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 3) {
                            square = new Square(x, y, SquareType.NORMAL, 37);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 2) {
                            square = new Square(x, y, SquareType.NORMAL, 38);
                        }
                    }
                    if (xGrid == 5) {
                        if (yGrid == 1) {
                            square = new Square(x, y, SquareType.NORMAL, 39);
                        }
                    }
                    if (xGrid == 4) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 26);
                        }
                    }
                    if (xGrid == 4) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 34);
                        }
                    }
                    if (xGrid == 3) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 27);
                        }
                    }
                    if (xGrid == 3) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 33);
                        }
                    }
                    if (xGrid == 2) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 28);
                        }
                    }
                    if (xGrid == 2) {
                        if (yGrid == 5) {
                            square = new Square(x, y, SquareType.NORMAL, 32);
                        }
                    }
                    if (xGrid == 1) {
                        if (yGrid == 7) {
                            square = new Square(x, y, SquareType.NORMAL, 29);
                        }
                    }
                    if (xGrid == 1) {
                        if (yGrid == 6) {
                            square = new Square(x, y, SquareType.NORMAL, 30);
                        }
                    }


                }
                //Zielfeld
                if (xGrid == 6) {
                    if (yGrid == 2) {
                        square = new Square(x, y, SquareType.BLUE, -310);
                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 3) {
                        square = new Square(x, y, SquareType.BLUE, -311);
                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 4) {
                        square = new Square(x, y, SquareType.BLUE, -312);
                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 5) {
                        square = new Square(x, y, SquareType.BLUE, -313);
                    }

                }
                //Wartefelder
                if (xGrid == 10) {
                    if (yGrid == 1) {
                        square = new Square(x, y, SquareType.BLUE, -300);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.BLUE);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }


                }
                if (xGrid == 10) {
                    if (yGrid == 2) {
                        square = new Square(x, y, SquareType.BLUE, -301);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.BLUE);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }


                }
                if (xGrid == 11) {
                    if (yGrid == 1) {
                        square = new Square(x, y, SquareType.BLUE, -302);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.BLUE);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }


                }
                if (xGrid == 11) {
                    if (yGrid == 2) {
                        square = new Square(x, y, SquareType.BLUE, -303);
                        spielstein = new Spielstein(TypeColor.BLUE, x, y, square);
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
                        square = new Square(x, y, SquareType.BLUE, 1);

                    }


                }
                //Zielfeld
                if (xGrid == 6) {
                    if (yGrid == 7) {
                        square = new Square(x, y, SquareType.YELLOW, -213);

                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 8) {
                        square = new Square(x, y, SquareType.YELLOW, -212);

                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 9) {
                        square = new Square(x, y, SquareType.YELLOW, -211);

                    }

                }
                if (xGrid == 6) {
                    if (yGrid == 10) {
                        square = new Square(x, y, SquareType.YELLOW, -210);

                    }

                }
                //Wartefelder
                if (xGrid == 1) {
                    if (yGrid == 11) {
                        square = new Square(x, y, SquareType.YELLOW, -201);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.YELLOW);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 1) {
                    if (yGrid == 10) {
                        square = new Square(x, y, SquareType.YELLOW, -200);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.YELLOW);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 2) {
                    if (yGrid == 10) {
                        square = new Square(x, y, SquareType.YELLOW, -203);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.YELLOW);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 2) {
                    if (yGrid == 11) {
                        square = new Square(x, y, SquareType.YELLOW, -204);
                        spielstein = new Spielstein(TypeColor.YELLOW, x, y, square);
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
                        square = new Square(x, y, SquareType.YELLOW, 21);

                    }


                }
                //Zielfeld
                if (yGrid == 6) {
                    if (xGrid == 7) {
                        square = new Square(x, y, SquareType.RED, -413);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 8) {
                        square = new Square(x, y, SquareType.RED, -412);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 9) {
                        square = new Square(x, y, SquareType.RED, -411);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 10) {
                        square = new Square(x, y, SquareType.RED, -410);

                    }

                }
                //Wartefelder
                if (xGrid == 10) {
                    if (yGrid == 10) {
                        square = new Square(x, y, SquareType.RED, -400);
                        spielstein = new Spielstein(TypeColor.RED, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.RED);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 10) {
                    if (yGrid == 11) {
                        square = new Square(x, y, SquareType.RED, -401);
                        spielstein = new Spielstein(TypeColor.RED, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.RED);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 11) {
                    if (yGrid == 10) {
                        square = new Square(x, y, SquareType.RED, -402);
                        spielstein = new Spielstein(TypeColor.RED, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.RED);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                    }

                }
                if (xGrid == 11) {
                    if (yGrid == 11) {
                        square = new Square(x, y, SquareType.RED, -403);
                        spielstein = new Spielstein(TypeColor.RED, x, y, square);
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
                        square = new Square(x, y, SquareType.RED, 11);

                    }


                }
                //Zielfeld
                if (yGrid == 6) {
                    if (xGrid == 2) {
                        square = new Square(x, y, SquareType.GREEN, -110);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 3) {
                        square = new Square(x, y, SquareType.GREEN, -111);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 4) {
                        square = new Square(x, y, SquareType.GREEN, -112);

                    }

                }
                if (yGrid == 6) {
                    if (xGrid == 5) {
                        square = new Square(x, y, SquareType.GREEN, -113);

                    }

                }
                //Wartefelder
                if (xGrid == 1 && yGrid == 1) {
                        square = new Square(x, y, SquareType.GREEN, -100);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);



                }
                if (xGrid == 1 && yGrid == 2) {
                        square = new Square(x, y, SquareType.GREEN, -101);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);

                }
                if (xGrid == 2 && yGrid == 1) {

                        square = new Square(x, y, SquareType.GREEN, -102);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);



                }
                if (xGrid == 2 && yGrid == 2) {

                        square = new Square(x, y, SquareType.GREEN, -103);
                        spielstein = new Spielstein(TypeColor.GREEN, x, y, square);
                        square.setSpielstein(spielstein);
                        spielstein.setSquare(square);
                        spielstein.setFill(Color.GREEN);
                        spielstein.setStroke(Color.BLACK);
                        spielstein.setStrokeWidth(4);



                }
                //Einstiegsfeld
                if (xGrid == 1) {
                    if (yGrid == 5) {
                        square = new Square(x, y, SquareType.GREEN, 31);

                    }


                }
                if (square != null) {
                    squareGroup.getChildren().add(square);
                }
                if (spielstein != null) {
                    spielsteinGroup.getChildren().add(spielstein);
                    if(spielsteine == null){
                        spielsteine = new ArrayList<>();
                        System.out.println("In Schleife neue Liste");
                    }

                    spielsteine.add(spielstein);
                    System.out.println("Liste:"+spielsteine);
                }

                yGrid++;

                if(square != null) {
                    allSquares.add(square);
                }


            }
            xGrid++;
            yGrid = 1;


        }

        //Spielstein sp = new Spielstein(SquareType.RED)

        pane.getChildren().addAll(squareGroup);
        pane.getChildren().addAll(spielsteinGroup);


        System.out.println("Alle Squares: " + allSquares);
        System.out.println("Alle STEINE: " + spielsteine);

        spiellogik.setSlSquares(allSquares);
        return pane;

    }



}