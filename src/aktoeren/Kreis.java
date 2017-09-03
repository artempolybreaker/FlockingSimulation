/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package aktoeren;

import org.lwjgl.opengl.GL11;
import vektorPackage.Vektor2D;

import java.util.concurrent.ThreadLocalRandom;

import static org.lwjgl.opengl.GL11.*;

public class Kreis extends BewegendesObjekt {

    private double radius;
    private double r, g, b;
    private double mass;
    //public Vektor2D [] tail;

    public double getMass() {
        return mass;
    }

    public Kreis() {
        super(0, new Vektor2D(50, 50), new Vektor2D(0, 1), 0.1);
        this.radius = 10;
        this.mass = this.radius;
        this.r = 1;
        this.g = 0;
        this.b = 0;
    }

    public Kreis(int id, double radius, Vektor2D position, Vektor2D velocity, double maxVelocity) {
        super(id, position, velocity, maxVelocity);
        this.radius = radius;
        this.mass = radius;
        this.r = ThreadLocalRandom.current().nextDouble(0, 1);
        this.g = ThreadLocalRandom.current().nextDouble(0, 1);
        this.b = ThreadLocalRandom.current().nextDouble(0, 1);
        /*this.tail = new Vektor2D[50];
        for (int i = 0; i < this.tail.length; i++) {
            this.tail[i] = this.getPosition();
        }*/
    }

    @Override
    public void render() {
        GL11.glBegin(6);
        /*for (int i = 0; i < this.tail.length; i++) {
            glVertex2d(tail[i].getX(), tail[i].getY());
        }*/
        for (int angle = 0; angle < 360; angle += 5) {
            glVertex2d(this.getPosition().getX() + Math.sin(angle) * this.radius, this.getPosition().getY() + Math.cos(angle) * this.radius);
        }
        GL11.glEnd();
    }
}
