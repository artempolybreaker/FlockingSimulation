/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

import aktoeren.Kreis;
import frame.BasisFenster;
import objektManager.KreiseManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import vektorPackage.Vektor2D;
import verhalten.VerhaltenSchwarm;
import java.util.concurrent.ThreadLocalRandom;

public class WeltMehrererKreise extends BasisFenster {

    public static final int ANZAHL_DER_KREISE = 300;
    public static final int MAX_VELOCITY = 4;
    public KreiseManager kreiseManager;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    public WeltMehrererKreise() {
        super();
        kreiseManager = KreiseManager.getExemplar();
        generiereKreise(ANZAHL_DER_KREISE);
        initialisiereVerhaltenFuerAlle();
    }

    private void initialisiereVerhaltenFuerAlle() {
        for (int i = 0; i < kreiseManager.getAnzahlAllerKreise(); i++) {
            kreiseManager.getKreis(i).setVerhalten(new VerhaltenSchwarm(kreiseManager.getKreis(i)));
        }
    }

    private void generiereKreise(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            this.kreiseManager.registriereKreis(new Kreis(i, (float) random.nextDouble(1, 10.0),
                    new Vektor2D(random.nextDouble(0.0, 800.0), random.nextDouble(0.0, 600.0)),
                    new Vektor2D(0, 0), MAX_VELOCITY));
        }
    }

    public void renderLoop() {
        while (!Display.isCloseRequested()) {
            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glClear(16384);
            GL11.glMatrixMode(5889);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, 800.0D, 600.0D, 0.0D, 0.0D, 1.0D);
            GL11.glMatrixMode(5888);
            GL11.glDisable(2929);
            for (int i = 0; i < kreiseManager.getAnzahlAllerKreise(); i++) {
                Kreis aktKreis = kreiseManager.getKreis(i);
                aktKreis.render();
                aktKreis.update();
            }
            Display.update();
        }
    }

    public static void main(String[] args) {
        (new WeltMehrererKreise()).start();
    }

}
