package at.spengergasse.dontgetangry.classes;

import java.util.ArrayList;
import java.util.List;

public class Spiellogik {
    //Variabeln
    public static Wuerfel wuerfel = new Wuerfel();

    private List<Spieler> Spielerliste = new ArrayList<Spieler>();




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


}
