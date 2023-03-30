package at.spengergasse.dontgetangry;

import javafx.scene.shape.Circle;

/**
 * Eine Klasse von er Instnazen ein Spielfeld sind
 */
public class Square extends Circle {

    //Attribut das einen Spielstand speichern kann
    private Spielstein spielstein;
    //Nummerierung der Felder
    private int id;

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
    public Square(double x, double y) {
        setRadius(Spiel.TileSize/2);
        relocate(x, y);

    }
}
