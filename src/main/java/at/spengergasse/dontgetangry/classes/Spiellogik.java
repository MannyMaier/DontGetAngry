package at.spengergasse.dontgetangry.classes;

import java.util.ArrayList;
import java.util.List;

public class Spiellogik {
    //Variabeln
    public static Wuerfel wuerfel = new Wuerfel();

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

    public static void spielsteinClicked(Spielstein spielstein, int gewuerfeltezahl, Square aktSquare){
        int aktId = aktSquare.get_id();
        int neueId = aktId + gewuerfeltezahl;
        if(aktId > 0){
            if(aktId + gewuerfeltezahl < 40) {
                neueId = aktId + gewuerfeltezahl - 40;
                spielstein.setRundegemacht(Boolean.TRUE);
            }
            if(neueId >= spielstein.getStartSquare().get_id() && spielstein.getRundegemacht() == Boolean.TRUE){

            }
        }

    }

}
