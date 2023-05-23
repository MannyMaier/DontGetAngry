package at.spengergasse.dontgetangry.classes;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Spielstand {

    EntityManager em = Persistence.createEntityManagerFactory("spielstaende").createEntityManager();

    String jpql = "SELECT s FROM Spiellogik s";

    public void save(List<Spiellogik> spiellogiks){
        em.getTransaction().begin();

        for(Spiellogik sl : spiellogiks){
            em.persist(sl);
        }
        em.getTransaction().commit();
    }

    public Spielstand() {

    }
}
