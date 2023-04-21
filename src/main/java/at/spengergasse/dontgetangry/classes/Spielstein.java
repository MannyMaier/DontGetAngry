package at.spengergasse.dontgetangry.classes;

import at.spengergasse.dontgetangry.Spiel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Spielstein extends Circle {

    //


    private TypeColor color;
    private Square square;

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public TypeColor getColor() {
        return color;
    }

    public Spielstein(TypeColor color, double x, double y){
        this.color = color;

        setRadius(Spiel.TileSize/2.5);
        setFill(Color.BLACK);
        relocate(x, y);

        setOnMouseClicked(event -> {
            System.out.println("click");
        });
    }
    public void move(double x, double y){

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


}
