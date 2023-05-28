package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "spielsteine")
public class Spielstein extends Circle {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private TypeColor color;
    @OneToOne(targetEntity = Square.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "spielstein_aktSquare")
    private Square square;
    @OneToOne(targetEntity = Square.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "spielstein_startSquare")
    private Square startSquare;
    @OneToOne(targetEntity = Square.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "spielstein_warteSquare")
    private Square warteSquare;

    public Spielstein() {

    }

    public Square getWarteSquare() {
        return warteSquare;
    }

   /* @OneToOne(targetEntity = Spiellogik.class)
    @JoinColumn(name = "spielsteine_spiellogik")
    private Spiellogik spiellogik;*/
    public Square getStartSquare(){
        return startSquare;
    }

    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
    }


    private Boolean rundegemacht = Boolean.FALSE;

    public Boolean getRundegemacht() {
        return rundegemacht;
    }

    public void setRundegemacht(Boolean rundegemacht) {
        this.rundegemacht = rundegemacht;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public TypeColor getColor() {
        return color;
    }

    public Square getAktSquare(){
        return this.square;
    }


    public Spielstein(TypeColor color, double x, double y, Square aktSquare){
        this.color = color;
        this.square = aktSquare;
        this.warteSquare = aktSquare;

        setRadius(Spiel.TileSize/2.5);
        setFill(Color.BLACK);
        relocate(x, y);


        setOnMouseClicked(event -> {
            Spiellogik.spielsteinClicked(this, this.square);


        });
    }
    public void move(double x, double y){
        relocate(x, y);
    }

    public String getImage(){
        if(color == TypeColor.BLUE){
            return "disrc/main/java/at/spengergasse/dontgetangry/resource/fig_blue.png";
        }
        if(color == TypeColor.RED){
            return "disrc/main/java/at/spengergasse/dontgetangry/resource/fig_red.png";
        }
        if(color == TypeColor.YELLOW){
            return "disrc/main/java/at/spengergasse/dontgetangry/resource/fig_yellow.png";
        }
        if(color == TypeColor.GREEN){
            return "disrc/main/java/at/spengergasse/dontgetangry/resource/fig_green.png";
        }
        return getImage();
    }

    public void GoToWarteSquare()
    {
        this.square = warteSquare;
        double x = warteSquare.getXkor();
        double y = warteSquare.getYkor();
        relocate(x, y);
    }

    @Override
    public String toString() {
        return "Spielstein{" +
                "color=" + color +
                ", square=" + square.get_id()+
                '}';
    }
}
