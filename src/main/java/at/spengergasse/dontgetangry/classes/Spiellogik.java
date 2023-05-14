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
        Integer startfeldId = null;
        Square newSqaure;

        if(aktId != -113 && aktId != -213 && aktId != -313 && aktId != -413) {

            if (aktId > 0) {
                //Runde gemacht (oben vorbei gekommen)
                if (neueId > 40) {
                    neueId -= 40;
                    spielstein.setRundegemacht(Boolean.TRUE);
                }

                //Zielfeld befüllen
                if (neueId >= spielstein.getStartSquare().get_id() && spielstein.getRundegemacht() == Boolean.TRUE) {
                    zugfeleruebrig = neueId - spielstein.getStartSquare().get_id();

                    if (spielstein.getColor() == TypeColor.BLUE) {
                        neueId = -310 - zugfeleruebrig;
                    } else if (spielstein.getColor() == TypeColor.RED) {
                        neueId = -410 - zugfeleruebrig;
                    } else if (spielstein.getColor() == TypeColor.YELLOW) {
                        neueId = -210 - zugfeleruebrig;
                    } else if (spielstein.getColor() == TypeColor.GREEN) {
                        neueId = -110 - zugfeleruebrig;
                    }
                }
            }

            //Aus dem Wartefeld gehen

            if (aktId < 0 && gewuerfeltezahl == 6) {
                if (aktId > -110 && aktId <= -100 && spielstein.getColor() == TypeColor.GREEN) {
                    neueId = 31;
                    startfeldId = neueId;
                } else if (aktId > -210 && aktId <= -200 && spielstein.getColor() == TypeColor.YELLOW) {
                    neueId = 21;
                    startfeldId = neueId;
                } else if (aktId > -310 && aktId <= -300 && spielstein.getColor() == TypeColor.BLUE) {
                    neueId = 1;
                    startfeldId = neueId;
                } else if (aktId > -410 && aktId <= -400 && spielstein.getColor() == TypeColor.RED) {
                    neueId = 11;
                    startfeldId = neueId;
                }

                //aktSquare.setSpielstein(null);

            } else if ((aktId <= -100 && aktId >= -113) || (aktId <= -200 && aktId >= -213) || (aktId <= -300 && aktId >= -313) || (aktId <= -400 && aktId >= -413)) {
                neueId = aktId;
            }


            if (aktId <= -110 && aktId >= -113) {
                if (aktId - gewuerfeltezahl >= -113) {
                    neueId = aktId - gewuerfeltezahl;
                }
            } else if (aktId <= -210 && aktId >= -213) {
                if (aktId - gewuerfeltezahl >= -213) {
                    neueId = aktId - gewuerfeltezahl;
                }
            } else if (aktId <= -310 && aktId >= -313) {
                if (aktId - gewuerfeltezahl >= -313) {
                    neueId = aktId - gewuerfeltezahl;
                }
            } else if (aktId <= -410 && aktId >= -413) {
                if (aktId - gewuerfeltezahl >= -413) {
                    neueId = aktId - gewuerfeltezahl;
                }
            }
            //System.out.println(aktId + "| " + gewuerfeltezahl);
            System.out.println(neueId);

            newSqaure = searchSquare(neueId);

            boolean fahren = Boolean.TRUE;

            if (newSqaure.hatSpielstein() == Boolean.TRUE) {
                if(newSqaure.getSpielstein().getColor() != spielstein.getColor()){
                    newSqaure.getSpielstein().GoToWarteSquare();
                    newSqaure.setSpielstein(null);
                    fahren = Boolean.TRUE;
                }else if(newSqaure.getSpielstein().getColor() == spielstein.getColor()) {

                    fahren = Boolean.FALSE;}
            }
            if(fahren == Boolean.TRUE) {
                aktSquare.setSpielstein(null);
                double neuX = newSqaure.getXkor();
                double neuY = newSqaure.getYkor();
                spielstein.setSquare(newSqaure);
                if (startfeldId != null) {
                    spielstein.setStartSquare(newSqaure);
                }
                newSqaure.setSpielstein(spielstein);
                System.out.println(newSqaure);
                spielstein.move(neuX, neuY);
            }
        }

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
