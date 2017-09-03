/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package verhalten;

import aktoeren.Kreis;
import exceptions.VektorOverflowException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import vektorPackage.Vektor2D;


public class VerhaltenSchwarm implements Verhalten {

    private Kreis kreis;
    private Steuerung steuerung;

    public VerhaltenSchwarm(Kreis kreis) {
        this.kreis = kreis;
        this.steuerung = new Steuerung();
    }


    @Override
    public void update() {
        try {
            //folge
            if(Mouse.isButtonDown(0))
                steuerung.anwendeForce(steuerung.folgeMouse(kreis), kreis);
            //fliehe
            if(Mouse.isButtonDown(1))
                steuerung.anwendeForce((Vektor2D) steuerung.folgeMouse(kreis).negiere(), kreis);
            //steuerung.anwendeForce(new Vektor2D(0, 0.1 * kreis.getMass()), kreis);

            steuerung.anwendeForce(steuerung.cohesion(kreis), kreis);
            steuerung.anwendeForce(steuerung.alignment(kreis), kreis);
            steuerung.anwendeForce(steuerung.separation(kreis),kreis);
            steuerung.steuerungForce.normalize();
            kreis.getVelocity().add(steuerung.steuerungForce.div(kreis.getMass()));
            if (kreis.getVelocity().length() > kreis.getMaxVelocity()) {
                kreis.getVelocity().normalize().mult(kreis.getMaxVelocity());
            }
            kreis.getPosition().add(kreis.getVelocity());
            steuerung.steuerungForce.mult(0.0);

            if (kreis.getPosition().getY() >= 600)
                kreis.setPosition(Display.getDisplayMode().getWidth() - kreis.getPosition().getX(), kreis.getPosition().getY() % 600);
            if (kreis.getPosition().getY() <= 0)
                kreis.setPosition(Display.getDisplayMode().getWidth() - kreis.getPosition().getX(), 600);

            if (kreis.getPosition().getX() >= 800)
                kreis.setPosition(kreis.getPosition().getX() % 800, Display.getDisplayMode().getHeight() - kreis.getPosition().getY());
            if (kreis.getPosition().getX() <= 0)
                kreis.setPosition(800, Display.getDisplayMode().getHeight() - kreis.getPosition().getY());

            /*for (int i = 0; i < kreis.tail.length; i++) {
                if (i == kreis.tail.length - 1)
                    kreis.tail[i] = kreis.getPosition();
                else
                    kreis.tail[i] = kreis.tail[i + 1];
            }*/
        } catch (VektorOverflowException e) {
            e.printStackTrace();
        }
    }

}
