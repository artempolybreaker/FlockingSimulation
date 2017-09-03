/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package vektorPackage;

import exceptions.VektorOverflowException;
import java.util.Arrays;

public abstract class Vektor {

    private double[] vek;

    /*---------------------------------------Konstruktoren*/

    public Vektor() {
    }

    public Vektor(int dimension) {
        this.vek = new double[dimension];
    }

    /*----------------------------------------------------*/

    /*---------------------------------- eigene Funktionen*/
    abstract public Vektor getClone();

    @Override
    public String toString() {
        return "vektorPackage.Vektor{" +
                "vek=" + Arrays.toString(this.vek) +
                '}';
    }

    public double[] getVek() {
        return this.vek;
    }

    public void setVek(double[] vek) {
        this.vek = vek;
    }

    public double getX() {
        return this.vek[0];
    }

    public double getY() {
        return this.vek[1];
    }

    public void setX(double x) {
        this.vek[0] = x;
    }

    public void setY(double y) {
        this.vek[1] = y;
    }

    public boolean isSameDimension(Vektor zweiterVektor) {
        return vek.length == zweiterVektor.vek.length;
    }

    public Vektor negiere() {
        for (int i = 0; i < this.vek.length; i++) {
            this.vek[i] = -this.vek[i];
        }
        return this;
    }

    public double addCoords() throws VektorOverflowException {
        double res = 0;
        for (double i :
                this.getVek()) {
            res = add2Doubles(res, i);
        }
        return res;
    }

    /*----------------------------------------------------*/

    /*------------------------------------Beleg-Funktionen*/

    public void setPosition(double... koordinaten) {
        for (int i = 0; i < koordinaten.length; i++) {
            this.vek[i] = koordinaten[i];
        }
    }

    public boolean isNullVektor() {
        for (int i = 0; i < this.vek.length; i++) {
            if (this.vek[i] != 0) return false;
        }
        return true;
    }

    public Vektor add(Vektor summand) throws VektorOverflowException {
        if (!isSameDimension(summand)) throw new VektorOverflowException();
        for (int i = 0; i < this.vek.length; i++) {
            this.vek[i] = add2Doubles(this.vek[i], summand.vek[i]);
        }
        return this;
    }

    public Vektor sub(Vektor subtrahend) throws VektorOverflowException {
        return add(subtrahend.getClone().negiere());
    }

    public Vektor mult(double multiplikand) throws VektorOverflowException {
        for (int i = 0; i < this.vek.length; i++) {
            this.vek[i] = mult2Doubles(vek[i], multiplikand);
        }
        return this;
    }

    public Vektor div(double divisor) throws VektorOverflowException {
        return mult(1 / divisor);
    }

    public boolean isEqual(Vektor zweiterVektor) {
        if (!isSameDimension(zweiterVektor)) return false;
        for (int i = 0; i < this.vek.length; i++) {
            if (this.vek[i] != zweiterVektor.vek[i]) return false;
        }
        return true;
    }

    public boolean isNotEqual(Vektor zweiterVektor) {
        return !isEqual(zweiterVektor);
    }

    public double length() throws VektorOverflowException {
        double laenge = 0;
        for (int i = 0; i < this.vek.length; i++) {
            laenge = add2Doubles(laenge, mult2Doubles(this.vek[i], this.vek[i]));
        }
        return Math.sqrt(laenge);
    }

    public Vektor normalize() throws VektorOverflowException {
        double laenge = length();
        return div(laenge);
    }

    public Vektor truncate(double max) throws VektorOverflowException {
        if(this.length() > max){
            this.normalize();
            this.mult(max);
        }
        return this;
    }
    /*----------------------------------------------------*/

    /*-----------------------------------static Funktionen*/

    public static double mult2Doubles(double aktuelleZahl, double multiplikand) throws VektorOverflowException {
        if (Math.abs(aktuelleZahl) != 0 && (Double.MAX_VALUE / Math.abs(aktuelleZahl) < Math.abs(multiplikand))) {
            throw new VektorOverflowException();
        }
        if (Math.abs(1 / multiplikand) < 1 && (Math.abs(aktuelleZahl) / Double.MAX_VALUE > Math.abs(multiplikand))) {
            throw new VektorOverflowException();
        }
        return aktuelleZahl * multiplikand;
    }

    public static double add2Doubles(double aktuelleZahl, double summand) throws VektorOverflowException {
        if ((summand > Double.MAX_VALUE - aktuelleZahl) || (summand < -Double.MAX_VALUE - aktuelleZahl)) {
            throw new VektorOverflowException();
        }
        return aktuelleZahl + summand;
    }
    /*----------------------------------------------------*/

}