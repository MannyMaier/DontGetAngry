package at.spengergasse.dontgetangry.classes;


import at.spengergasse.dontgetangry.Spiel;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Spielstand {

    String jpql = "SELECT s FROM Spiellogik s";
    String foreignkeychecks = "SET FOREIGN_KEY_CHECKS = 0 ;";
    String deleteAllspielsteine = "DELETE  FROM Spielstein";
    String deleteAllfelder = "DELETE FROM Square";
    String deleteAllwuerfel = "DELETE FROM Wuerfel";
    private boolean schon_gespeichert = Boolean.FALSE;

    public  void save(Wuerfel wuerfel, List<Square> squares, List<Spielstein> spielsteine){

        Spiel.getManager().getTransaction().begin();

        try {
            Spiel.getManager().createNativeQuery(foreignkeychecks).executeUpdate();
        }catch (Exception e){}


        Spiel.getManager().createQuery(deleteAllfelder).executeUpdate();
        System.out.println("deletefelder");
        Spiel.getManager().createQuery(deleteAllspielsteine).executeUpdate();
        System.out.println("delete spielsteine");
        Spiel.getManager().createQuery(deleteAllwuerfel).executeUpdate();
        System.out.println("delete wuerfel");

        Spiel.getManager().getTransaction().commit();


        Spiel.getManager().getTransaction().begin();

        Spiel.getManager().persist(wuerfel);

        Spiel.getManager().getTransaction().commit();

        Spiel.getManager().getTransaction().begin();

        for(Square sq : squares){
            Spiel.getManager().persist(sq);
        }

        System.out.println("SPEICHERN Squares: "+squares);
        Spiel.getManager().getTransaction().commit();

        Spiel.getManager().getTransaction().begin();

        for(Spielstein st : spielsteine){
            Spiel.getManager().persist(st);
        }

        Spiel.getManager().getTransaction().commit();


        /*if(schon_gespeichert == Boolean.FALSE) {

            Spiel.getManager().getTransaction().begin();

            try {

                Spiel.getManager().createNativeQuery(foreignkeychecks).executeUpdate();
                System.out.println("foreignkey");
            } catch (Exception e) {
            }


            Spiel.getManager().createQuery(deleteAllfelder).executeUpdate();
            System.out.println("deletefelder");
            Spiel.getManager().createQuery(deleteAllspielsteine).executeUpdate();
            System.out.println("delete spielsteine");
            Spiel.getManager().createQuery(deleteAllwuerfel).executeUpdate();
            System.out.println("delete wuerfel");

            Spiel.getManager().getTransaction().commit();

            Spiel.getManager().getTransaction().begin();
            Spiel.getManager().merge(wuerfel);
            System.out.println("mergewuefel");
            Spiel.getManager().getTransaction().commit();

            for (Square sq : squares) {
                Spiel.getManager().getTransaction().begin();

                Spiel.getManager().persist(sq);
                System.out.println("persist square");

            *//*try{
                Spiel.getManager().persist(sq);
                System.out.println("persist square");
            }catch (Exception e){
                Spiel.getManager().merge(sq);
                System.out.println("merge square");
            }*//*

                Spiel.getManager().getTransaction().commit();
            }

            System.out.println("Spielsteine Speichern" + spielsteine);
       *//* for (Spielstein st : spielsteine){
            Spiel.getManager().getTransaction().begin();

            Spiel.getManager().merge(st);
            System.out.println("merge spielstein");

           *//**//* try{
                Spiel.getManager().persist(st);
                System.out.println("persist spielstein");
            }catch (Exception e){
                Spiel.getManager().merge(st);
                System.out.println("merge spielstein");
            }*//**//*

            Spiel.getManager().getTransaction().commit();
        }*//*
            schon_gespeichert = Boolean.TRUE;
        }else {
            Spiel.getManager().refresh(wuerfel);

            for(Square square : squares){
                Spiel.getManager().merge(square);
            }

            for(Spielstein st : spielsteine){
                Spiel.getManager().merge(st);
            }
        }*/
    }

    public List<Spielstein> loadSpielsteine(){
        try {
            TypedQuery<Spielstein> query = Spiel.getManager().createQuery("select sp from Spielstein sp", Spielstein.class);
            List<Spielstein> spielsteins = query.getResultList();
            System.out.println(spielsteins);
            return spielsteins;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ein Fehler ist aufgetreten");
            alert.setContentText("Fehler: "+e.getMessage());
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return null;
        }
    }
    public List<Square> loadSquares(){
        try {
            TypedQuery<Square> query = Spiel.getManager().createQuery("select sq from Square sq", Square.class);
            List<Square> squareList = query.getResultList();
            System.out.println("GELADETE SQUARES: "+squareList);
            return squareList;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ein Fehler ist aufgetreten");
            alert.setContentText("Fehler: "+e.getMessage());
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return null;
        }
    }

    public Wuerfel loadWuerfel(){
        try {
            TypedQuery<Wuerfel> query = Spiel.getManager().createQuery("select w from Wuerfel w", Wuerfel.class);
            Wuerfel wuerfel = query.getSingleResult();
            return wuerfel;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ein Fehler ist aufgetreten");
            alert.setContentText("Fehler: "+e.getMessage());
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return null;
        }
    }


}
