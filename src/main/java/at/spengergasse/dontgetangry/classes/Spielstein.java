package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Spielstein extends Circle {

    //


    private TypeColor color;
    private Square square;
    private Square startSquare;
    private Square warteSquare;

    public Square getWarteSquare() {
        return warteSquare;
    }

    private Spiellogik spiellogik;
    public Square getStartSquare(){
        return startSquare;
    }

    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
        //this.zielSquare = startSquare.get_id() + 39;
    }

    private Square zielSquare;

    public Square getZielSquare() {
        return zielSquare;
    }

    public void setZielSquare(Square zielSquare) {
        this.zielSquare = zielSquare;
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

    public Spielstein(TypeColor color, double x, double y, Square aktSquare, Spiellogik sl){
        this.color = color;
        this.square = aktSquare;
        this.spiellogik = sl;
        this.warteSquare = aktSquare;

        setRadius(Spiel.TileSize/2.5);
        setFill(Color.BLACK);
        relocate(x, y);


        setOnMouseClicked(event -> {
            spiellogik.spielsteinClicked(this, spiellogik.wuerfel.getGewuerfelteZahl(), this.square);


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

    @Override
    public String toString() {
        return "Spielstein{" +
                "color=" + color +
                ", square=" + //square +
                '}';
    }
}
