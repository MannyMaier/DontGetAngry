package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Eine Klasse von er Instnazen ein Spielfeld sind
 */

@Entity
@Table(name = "felder")
public class Square extends Circle {

    //File figrot = new File("resource/fig_red.png");
//    @FXML
//    ImageView IMGfigrot = new ImageView("file:fig_red.png");


    //Attribut das einen Spielstand speichern kann!
    @OneToOne(targetEntity = Spielstein.class, orphanRemoval = true)
    @JoinColumn( name = "felder_spielstein")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Spielstein spielstein;

    //Nummerierung der Felder
    @Id
    private int id;

    public Square() {

    }


    public int get_id() {
        return id;
    }


    private double xkor;
    private double ykor;

    public double getXkor() {
        return xkor;
    }

    public double getYkor() {
        return ykor;
    }

    public String getkords()
    {
        return xkor + "+" + ykor;
    }

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

        this.xkor = x;
        this.ykor = y;

        setFill(null);
        setStrokeWidth(8);
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

       // System.out.println(id);

    }

    @Override
    public String toString() {
        return "Square{" +
                "spielstein=" + spielstein +
                ", id=" + id +
                ", xkor=" + xkor +
                ", ykor=" + ykor +
                ", type=" + type +
                '}';
    }
}
