package at.spengergasse.dontgetangry.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Spielstand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String testString;

    public Spielstand(String string){
        testString = string;
    }

    public Spielstand() {

    }
}
