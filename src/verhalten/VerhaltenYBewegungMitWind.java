/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package verhalten;

import aktoeren.BewegendesObjekt;
import exceptions.VektorOverflowException;
import vektorPackage.Vektor2D;

public class VerhaltenYBewegungMitWind extends VerhaltenYBewegung implements Verhalten {
    protected Vektor2D wind;
    private Vektor2D yBewegung;

    public VerhaltenYBewegungMitWind(BewegendesObjekt objekt) {
        super(objekt);
        this.wind = new Vektor2D(0.5, 0);
        this.yBewegung = new Vektor2D(0, 0.5);
    }

    public void update() {
        try {
            objekt.setPosition((Vektor2D) objekt.getPosition().add(objekt.getVelocity()));
            objekt.setPosition((Vektor2D) objekt.getPosition().add(yBewegung));
            objekt.setPosition((Vektor2D) objekt.getPosition().add(this.wind));
        } catch (VektorOverflowException e) {
            e.printStackTrace();
        }
        if (objekt.getPosition().getY() >= 600 || objekt.getPosition().getY() <= 0) {
            yBewegung.setY(yBewegung.getY() * -1);
        }
        if (objekt.getPosition().getX() >= 800 || objekt.getPosition().getX() <= 0) {
            this.wind.setX(this.wind.getX() * -1);
        }
    }
}