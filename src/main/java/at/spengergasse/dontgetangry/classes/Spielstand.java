package at.spengergasse.dontgetangry.classes;

import javax.persistence.*;

@Entity
public class Spielstand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public Spielstand(String string){
    }

    public Spielstand() {

    }
}
