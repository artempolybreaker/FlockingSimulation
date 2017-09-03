/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package vektorPackage;

import exceptions.VektorOverflowException;

public class LineareAlgebra {

    /*-----------------------------------static Funktionen*/

    public static Vektor add(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        return vektor1.getClone().add(vektor2);
    }

    public static Vektor sub(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        return vektor1.getClone().sub(vektor2);
    }

    public static Vektor mult(Vektor vektor, double multiplikand) throws VektorOverflowException {
        return vektor.getClone().mult(multiplikand);
    }

    public static Vektor div(Vektor vektor, double divisor) throws VektorOverflowException {
        return vektor.getClone().div(divisor);
    }

    public static boolean isEqual(Vektor vektor1, Vektor vektor2) {
        return vektor1.isEqual(vektor2);
    }

    public static boolean isNotEqual(Vektor vektor1, Vektor vektor2) {
        return vektor1.isNotEqual(vektor2);
    }

    public static double length(Vektor vektor) throws VektorOverflowException {
        return vektor.length();
    }

    public static Vektor normalize(Vektor vektor) throws VektorOverflowException {
        return vektor.normalize();
    }

    public static double euklDistance(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        return (sub(vektor1,vektor2).length());
    }

    public static double manhattanDistance(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        return (LineareAlgebra.abs(vektor1.sub(vektor2))).addCoords();
    }

    public static double crossProduct(Vektor2D vektor1, Vektor2D vektor2) throws VektorOverflowException {
        return Vektor.add2Doubles(Vektor.mult2Doubles(vektor1.getX(), vektor2.getY()), -Vektor.mult2Doubles(vektor1.getY(), vektor2.getX()));
    }

    public static Vektor3D crossProduct(Vektor3D vektor1, Vektor3D vektor2) throws VektorOverflowException {
        Vektor3D res = new Vektor3D();
        Vektor3D res1 = new Vektor3D();
        for (int i = 1; i < vektor1.getVek().length + 1; i++) {
            res.getVek()[i - 1] = Vektor.mult2Doubles(vektor1.getVek()[i % 3], vektor2.getVek()[(i + 1) % 3]);
            res1.getVek()[i - 1] = Vektor.mult2Doubles(vektor1.getVek()[(i + 1) % 3], vektor2.getVek()[i % 3]);
        }
        return (Vektor3D) res.sub(res1);
    }

    public static double dotProduct(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        if (!vektor1.isSameDimension(vektor2)) throw new VektorOverflowException();
        double res = 0;
        for (int i = 0; i < vektor1.getVek().length; i++) {
            res = Vektor.add2Doubles(res, Vektor.mult2Doubles(vektor1.getVek()[i], vektor2.getVek()[i]));
        }
        return res;
    }

    public static double cosEquation(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        if (!vektor1.isSameDimension(vektor2)) throw new VektorOverflowException();
        return Vektor.mult2Doubles(LineareAlgebra.dotProduct(vektor1, vektor2),
                1 / (Vektor.mult2Doubles(vektor1.length(), vektor2.length())));
    }

    public static double sinEquation(Vektor3D vektor1, Vektor3D vektor2) throws VektorOverflowException {
        if (!vektor1.isSameDimension(vektor2)) throw new VektorOverflowException();
        return Vektor.mult2Doubles((LineareAlgebra.crossProduct(vektor1, vektor2)).length(),
                1 / (Vektor.mult2Doubles(vektor1.length(), vektor2.length())));
    }

    public static double angleRad(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        if (!vektor1.isSameDimension(vektor2)) throw new VektorOverflowException();
        return Math.acos(LineareAlgebra.cosEquation(vektor1, vektor2));
    }

    public static double angleDegree(Vektor vektor1, Vektor vektor2) throws VektorOverflowException {
        if (!vektor1.isSameDimension(vektor2)) throw new VektorOverflowException();
        return radToDegree(LineareAlgebra.angleRad(vektor1, vektor2));
    }

    public static double degreeToRad(double degree) throws VektorOverflowException {
        return Vektor.mult2Doubles(degree, Math.PI / 180);
    }

    public static double radToDegree(double rad) throws VektorOverflowException {
        return Vektor.mult2Doubles(rad, 180 / Math.PI);
    }

    public static double determinate(Vektor2D vektor1, Vektor2D vektor2) throws VektorOverflowException {
        return crossProduct(vektor1, vektor2);
    }

    public static double determinate(Vektor3D vektor1, Vektor3D vektor2, Vektor3D vektor3) throws VektorOverflowException {
        double res1 = 0;
        double res2 = 0;
        for (int i = 1; i < vektor1.getVek().length + 1; i++) {
            res1 += Vektor.mult2Doubles(Vektor.mult2Doubles(vektor1.getVek()[(i - 1) % 3], vektor2.getVek()[i % 3]),
                    vektor3.getVek()[(i + 1) % 3]);
            res2 -= Vektor.mult2Doubles(Vektor.mult2Doubles(vektor1.getVek()[i % 3], vektor2.getVek()[(i + 2) % 3]),
                    vektor3.getVek()[(i + 1) % 3]);
        }
        return Vektor.add2Doubles(res1, res2);
    }

    public static Vektor abs(Vektor vektor) {
        for (int i = 0; i < vektor.getVek().length; i++) {
            vektor.getVek()[i] = Math.abs(vektor.getVek()[i]);
        }
        return vektor;
    }

    public static void show(Vektor vektor) {
        System.out.println(vektor.toString());
    }

    public static boolean areOrthogonal(Vektor3D vektor1, Vektor3D vektor2) throws VektorOverflowException {
        return LineareAlgebra.dotProduct(vektor1, vektor2) == 0;
    }

    /*----------------------------------------------------*/

}
