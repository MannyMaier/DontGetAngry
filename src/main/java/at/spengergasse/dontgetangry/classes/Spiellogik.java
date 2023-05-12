package at.spengergasse.dontgetangry.classes;

import java.util.ArrayList;
import java.util.List;

public class Spiellogik {
    //Variabeln
    public static Wuerfel wuerfel = new Wuerfel();
    private List<Square> slSquares = new ArrayList<>();

    public void setSlSquares(List<Square> slSquares) {
        this.slSquares = slSquares;
        System.out.println("SL:"+slSquares);
    }

    private List<Spieler> Spielerliste = new ArrayList<Spieler>();

    //public static List<Square> allSquares = new ArrayList<Square>();


    public void spielstarten(int spieleranz) throws InterruptedException {
        if(spieleranz < 2){
            spieleranz = 2;
        } else if (spieleranz > 4) {
            spieleranz = 4;
        }

        for(int i = 1; i <= spieleranz; i++ ){
            Spieler spieler = new Spieler(i);

            this.Spielerliste.add(spieler);
        }

        System.out.println(Spielerliste);
    }

    public void spielsteinClicked(Spielstein spielstein, int gewuerfeltezahl, Square aktSquare){
        int aktId = aktSquare.get_id();
        int neueId = aktId + gewuerfeltezahl;
        int zugfeleruebrig = 0;
        Square newSqaure;

        if(aktId > 0){
            if(neueId > 40) {
                neueId -= 40;
                spielstein.setRundegemacht(Boolean.TRUE);
            }

            //Zielfeld befüllen
            if(neueId >= spielstein.getStartSquare().get_id() && spielstein.getRundegemacht() == Boolean.TRUE){
                zugfeleruebrig = neueId - spielstein.getStartSquare().get_id();

                if(spielstein.getColor() == TypeColor.BLUE){
                    neueId = -310 - zugfeleruebrig;
                } else if (spielstein.getColor() == TypeColor.RED) {
                    neueId = -410 - zugfeleruebrig;
                }else if (spielstein.getColor() == TypeColor.YELLOW) {
                    neueId = -210 - zugfeleruebrig;
                }else if (spielstein.getColor() == TypeColor.GREEN) {
                    neueId = -110 - zugfeleruebrig;
                }
            }
        }

        if(aktId < 0 && gewuerfeltezahl == 6 && aktId > -110){
            if(spielstein.getColor() == TypeColor.BLUE){
                neueId = 1;
            } else if (spielstein.getColor() == TypeColor.RED) {
                neueId = 11;
            }else if (spielstein.getColor() == TypeColor.YELLOW) {
                neueId = 21;
            }else if (spielstein.getColor() == TypeColor.GREEN) {
                neueId = 31;
            }
        }

        newSqaure = searchSquare(neueId);
        System.out.println(newSqaure);
        double neuX = newSqaure.getXkor();
        double neuY = newSqaure.getYkor();

        spielstein.setSquare(newSqaure);
        newSqaure.setSpielstein(spielstein);
        spielstein.move(neuX, neuY);

    }

    public Square searchSquare(int gesId){

        Square square;
            for(Square squarefor : slSquares){
                if(squarefor.get_id() == gesId){
                    square = squarefor;
                    return square;
                }
            }
        return null;

    }

}
