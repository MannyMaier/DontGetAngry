package at.spengergasse.dontgetangry.classes;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Wuerfel wuerfel = new Wuerfel();
        int zahl = wuerfel.wuerfeln();
        System.out.println(zahl);
    }
}
