/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/


package aktoeren;

import vektorPackage.Vektor2D;
import verhalten.Verhalten;

public abstract class BewegendesObjekt extends BasisObjekt {

    private Verhalten verhalten = null;
    private double maxVelocity;
    protected Vektor2D velocity;

    public BewegendesObjekt() {
        super();
        this.maxVelocity = 5;
        setVelocity(new Vektor2D(1, 0));
    }

    public BewegendesObjekt(int id, Vektor2D position, Vektor2D velocity, double maxVelocity) {
        super(id, position);
        this.maxVelocity = maxVelocity;
        this.velocity = new Vektor2D(velocity);
    }

    public void setVerhalten(Verhalten verhalten) {
        this.verhalten = verhalten;
    }

    public Verhalten getVerhalten() {
        return this.verhalten;
    }

    public Vektor2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vektor2D velocity) {
        this.velocity = velocity;
    }

    public void update() {
        if (verhalten != null) {
            verhalten.update();
        }
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }
}
