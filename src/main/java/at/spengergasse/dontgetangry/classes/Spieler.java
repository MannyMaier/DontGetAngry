package at.spengergasse.dontgetangry.classes;

public class Spieler {
    private String name;
    private int spielerNr;

    public Spieler(int nr){
        spielerNr = nr;
        name = "Spieler "+spielerNr;
    }

    @Override
    public String toString() {
        return "Spieler{" +
                "name='" + name + '\'' +
                ", spielerNr=" + spielerNr +
                '}';
    }
}
