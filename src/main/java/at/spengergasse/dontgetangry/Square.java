package at.spengergasse.dontgetangry;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Eine Klasse von er Instnazen ein Spielfeld sind
 */
public class Square extends Circle {

    //Attribut das einen Spielstand speichern kann
    private Spielstein spielstein;
    //Nummerierung der Felder
    private int id;

    private SquareType type;

    //TODO: Finish class

    public boolean hatSpielstein(){
        return spielstein != null;
    }

    public Spielstein getSpielstein() {
        return spielstein;
    }

    public void setSpielstein(Spielstein spielstein) {
        this.spielstein = spielstein;
    }

    /**
     * Konstruktor zum instanzieren von einem Square
     * @param x x koordinate des Feldes
     * @param y y koordinate des Feldes
     */
    public Square(double x, double y, SquareType type, int id) {
        this.id = id;
        setStrokeWidth(10);
        setRadius(Spiel.TileSize/2);
        if (type == SquareType.NORMAL) {
            setFill(Color.GREY);
        }
        if (type == SquareType.RED) {
            setFill(Color.RED);
        }
        if (type == SquareType.GREEN) {
            setFill(Color.GREEN);
        }
        if (type == SquareType.YELLOW) {
            setFill(Color.YELLOW);
        }
        if (type == SquareType.BLUE) {
            setFill(Color.BLUE);
        }
        relocate(x, y);

    }
}
