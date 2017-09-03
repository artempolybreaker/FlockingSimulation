/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package verhalten;

import aktoeren.Kreis;
import exceptions.VektorOverflowException;
import objektManager.KreiseManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import sun.security.jgss.krb5.Krb5InitCredential;
import vektorPackage.LineareAlgebra;
import vektorPackage.Vektor2D;
import vektorPackage.Vektor3D;


public class Steuerung {

    public Vektor2D steuerungForce;
    private KreiseManager kreiseManager = KreiseManager.getExemplar();
    private double separationDist = 400.0;

    public Steuerung() {
        steuerungForce = new Vektor2D();
    }

    public void anwendeForce(Vektor2D force, Kreis kreis) throws VektorOverflowException {
        this.steuerungForce.add(LineareAlgebra.div(force, kreis.getMass()));
    }

    public Vektor2D folgeMouse(Kreis kreis) {
        Vektor2D target = new Vektor2D((float) Mouse.getX(), (float) (Display.getDisplayMode().getHeight() - Mouse.getY()));
        try {
            target.sub(kreis.getPosition());
        } catch (VektorOverflowException e) {
            e.printStackTrace();
        }
        return target;
    }

    public Vektor2D separation(Kreis kreis) throws VektorOverflowException {
        Vektor2D steeringForce = new Vektor2D(0, 0);
        Vektor2D help = new Vektor2D(0, 0);
        for (int i = 0; i < kreiseManager.getAnzahlAllerKreise(); i++) {
            if (kreis.getId() == i)
                continue;
            Kreis bObj = kreiseManager.getKreis(i);
            if (LineareAlgebra.euklDistance(kreis.getPosition(), bObj.getPosition()) < separationDist) {
                help.setPosition((Vektor2D) LineareAlgebra.sub(kreis.getPosition(), bObj.getPosition()));
                double length = help.length();
                help.normalize();
                help.div(length);
                steeringForce.add(help);
            }
        }
        return steeringForce;
    }


    public Vektor2D alignment(Kreis kreis) throws VektorOverflowException {
        Vektor2D steeringForce = new Vektor2D(0, 0);
        int count = 0;
        for (int i = 0; i < kreiseManager.getAnzahlAllerKreise(); i++) {
            if (kreis.getId() == i)
                continue;
            Kreis bObj = kreiseManager.getKreis(i);
            if (LineareAlgebra.euklDistance(kreis.getPosition(), bObj.getPosition()) < separationDist) {
                steeringForce.add(bObj.getVelocity());
                count++;
            }
        }
        if (count > 0) {
            steeringForce.div(count);

            steeringForce.sub(kreis.getVelocity());
        }
        return steeringForce;
    }

    public Vektor2D cohesion(Kreis kreis) throws VektorOverflowException {
        Vektor2D steeringForce = new Vektor2D(0, 0);
        int count = 0;
        for (int i = 0; i < kreiseManager.getAnzahlAllerKreise(); i++) {
            if (kreis.getId() == i) continue;
            Kreis bObj = kreiseManager.getKreis(i);
            steeringForce.add(bObj.getPosition());
            count++;
        }
        steeringForce.div(count);
        steeringForce.sub(kreis.getPosition());
        return steeringForce;
    }

}
