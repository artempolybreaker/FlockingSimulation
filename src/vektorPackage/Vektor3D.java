/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package vektorPackage;

public class Vektor3D extends Vektor {

    public Vektor3D() {
        super(3);
    }

    public Vektor3D(double x, double y, double z) {
        super(3);
        super.setPosition(x, y, z);
    }

    public Vektor3D(Vektor3D vek) {
        super(3);
        this.setX(vek.getX());
        this.setY(vek.getY());
        this.setZ(vek.getZ());
    }

    public void setPosition(double x, double y, double z) {
        super.setPosition(x, y, z);
    }

    /*müsste nur in Vektor3D sein*/
    public double getZ() {
        return super.getVek()[2];
    }

    public void setZ(double z) {
        super.getVek()[2] = z;
    }

    public Vektor getClone() {
        return new Vektor3D(this.getX(), this.getY(), this.getZ());
    }

}
