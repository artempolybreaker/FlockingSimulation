/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package aktoeren;

import vektorPackage.Vektor2D;

public abstract class BasisObjekt {

    private int id;
    protected Vektor2D position;

    public Vektor2D getPosition() {
        return position;
    }

    public void setPosition(Vektor2D position) {
        this.position.setPosition(position.getX(), position.getY());
    }

    public void setPosition(double x, double y) {
        this.position.setPosition(x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasisObjekt() {
        this(0, new Vektor2D(0, 0));
    }

    public BasisObjekt(int id, Vektor2D position) {
        this.id = id;
        this.position = new Vektor2D(position);
    }

    public abstract void render();

}
