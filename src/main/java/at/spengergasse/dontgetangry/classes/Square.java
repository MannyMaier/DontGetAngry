package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Eine Klasse von er Instnazen ein Spielfeld sind
 */
public class Square extends Circle {

    //File figrot = new File("resource/fig_red.png");
    @FXML
    ImageView IMGfigrot = new ImageView("file:fig_red.png");


    //Attribut das einen Spielstand speichern kann!
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
        setFill(null);
        setStrokeWidth(5);
        setRadius(Spiel.TileSize/2-2.5);
        if (type == SquareType.NORMAL) {
            setStroke(Color.GREY);
        }
        if (type == SquareType.RED) {
            setStroke(Color.RED);
            //setFill(new ImageView(IMGfigrot));

        }
        if (type == SquareType.GREEN) {
            setStroke(Color.GREEN);
        }
        if (type == SquareType.YELLOW) {
            setStroke(Color.YELLOW);
        }
        if (type == SquareType.BLUE) {
            setStroke(Color.BLUE);
        }
        relocate(x, y);

    }
}
