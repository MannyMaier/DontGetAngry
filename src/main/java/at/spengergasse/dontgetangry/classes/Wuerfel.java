package at.spengergasse.dontgetangry.classes;

import javafx.scene.image.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.Random;

@Entity
public class Wuerfel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int gewuerfelteZahl;

    public int wuerfeln() throws InterruptedException {


        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int i = 0; i < 15; i++) {
                        Random random = new Random();
                        int rolledInt = random.nextInt(6) + 1;
                        File image = new File("disrc/main/java/at/spengergasse/dontgetangry/resource/ce" + rolledInt + ".png");
                        //diceimage.setImage(new Image(image.toURI().toString()));
                        //System.out.println(rolledInt);
                        Thread.sleep(50);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();


        Random r = new Random();
        gewuerfelteZahl = r.nextInt(1, 7);
        System.out.println("gewuefelt" + gewuerfelteZahl);
        //Thread.sleep(1000);
        return gewuerfelteZahl;
    }

    public int getGewuerfelteZahl() {
        return gewuerfelteZahl;
    }
}
