package at.spengergasse.dontgetangry.classes;


import at.spengergasse.dontgetangry.Spiel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Spielstand {



    String jpql = "SELECT s FROM Spiellogik s";
    String foreignkeychecks = "SET FOREIGN_KEY_CHECKS = 0 ;";
    String deleteAllspielsteine = "DELETE  FROM Spielstein";
    String deleteAllfelder = "DELETE FROM Square";
    String deleteAllwuerfel = "DELETE FROM Wuerfel";

    public  void save(Wuerfel wuerfel, List<Square> squares, List<Spielstein> spielsteine){
        Spiel.getManager().getTransaction().begin();

        Spiel.getManager().createNativeQuery(foreignkeychecks).executeUpdate();
        Spiel.getManager().createQuery(deleteAllfelder).executeUpdate();

        Spiel.getManager().createQuery(deleteAllspielsteine).executeUpdate();
        Spiel.getManager().createQuery(deleteAllwuerfel).executeUpdate();
        Spiel.getManager().getTransaction().commit();

        Spiel.getManager().getTransaction().begin();
        Spiel.getManager().merge(wuerfel);


        Spiel.getManager().getTransaction().commit();


        for (Square sq : squares){
            Spiel.getManager().getTransaction().begin();

            try{
                Spiel.getManager().persist(sq);
            }catch (Exception e){
                Spiel.getManager().merge(sq);
            }

            Spiel.getManager().getTransaction().commit();
        }






        Spiel.getManager().getTransaction().begin();
        Spiel.getManager().getTransaction().commit();





        for (Spielstein st : spielsteine){
            Spiel.getManager().getTransaction().begin();
            Spiel.getManager().merge(st);
            Spiel.getManager().persist(st);
            Spiel.getManager().getTransaction().commit();
        }




    }
}
