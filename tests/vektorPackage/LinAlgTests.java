/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package vektorPackage;

import exceptions.VektorOverflowException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinAlgTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testeAdditionWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        LineareAlgebra.add(new Vektor2D(Double.MAX_VALUE, 1), new Vektor2D(2, 2));
    }

    @Test
    public void testeAddition() throws VektorOverflowException {
        double[] exp = {34, 3, -32.543};
        assertArrayEquals(exp, (LineareAlgebra.add(new Vektor3D(32, 1, -0.543), new Vektor3D(2, 2, -32))).getVek(), 0);
    }

    @Test
    public void testeSubtrWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        LineareAlgebra.sub(new Vektor2D(-Double.MAX_VALUE, 1), new Vektor2D(2, 2));
    }

    @Test
    public void testeSubtr() throws VektorOverflowException {
        Vektor2D vek = (Vektor2D) LineareAlgebra.sub(new Vektor2D(43, 1), new Vektor2D(2, 2));
        Vektor2D vek2 = new Vektor2D(41, -1);
        assertEquals(vek.getX(), vek2.getX(), 0);
        assertEquals(vek.getY(), vek2.getY(), 0);
    }

    @Test
    public void testeMultWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        double wert = -1.01;
        Vektor2D vek = (Vektor2D) LineareAlgebra.mult(new Vektor2D(Double.MAX_VALUE, 1), wert);
    }

    @Test
    public void testeMult() throws VektorOverflowException {
        double wert = -3;
        Vektor3D vek = (Vektor3D) LineareAlgebra.mult(new Vektor3D(43, 1, -18), wert);
        Vektor3D vek2 = new Vektor3D(-129, -3, 54);
        assertEquals(vek.getX(), vek2.getX(), 0);
        assertEquals(vek.getY(), vek2.getY(), 0);
        assertEquals(vek.getZ(), vek2.getZ(), 0);
    }

    @Test
    public void testeDividWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        double wert = -Double.MIN_VALUE;
        Vektor2D vek = (Vektor2D) LineareAlgebra.div(new Vektor2D(Double.MAX_VALUE, 1), wert);
        LineareAlgebra.show(vek);
    }

    @Test
    public void testeDividWithException2() throws VektorOverflowException {
        double wert = -Double.MAX_VALUE;
        Vektor2D vek = (Vektor2D) LineareAlgebra.div(new Vektor2D(0.356745, 1), wert);
        LineareAlgebra.show(vek);
    }

    @Test
    public void test() throws VektorOverflowException {
        double wert = 12;
        double wert2 = 0.12;
        System.out.println(wert + wert2);
    }

    @Test
    public void testeDivid() throws VektorOverflowException {
        double wert = -3;
        Vektor3D vek = (Vektor3D) LineareAlgebra.div(new Vektor3D(45, 3, -18), wert);
        Vektor3D vek2 = new Vektor3D(-15, -1, 6);
        assertEquals(vek.getX(), vek2.getX(), 0);
        assertEquals(vek.getY(), vek2.getY(), 0);
        assertEquals(vek.getZ(), vek2.getZ(), 0);
    }

    @Test
    public void testeIsEqual() {
        Vektor2D vek1 = new Vektor2D(2, 3);
        Vektor3D vek2 = new Vektor3D(2, 3, 4);
        assertFalse(LineareAlgebra.isEqual(vek2, vek1));
        Vektor2D vek3 = new Vektor2D(2, -3);
        Vektor2D vek4 = new Vektor2D(2, 3);
        assertFalse(LineareAlgebra.isEqual(vek1, vek3));
        assertTrue(LineareAlgebra.isEqual(vek1, vek4));
    }

    @Test
    public void testeIsNotEqual() {
        Vektor2D vek1 = new Vektor2D(2, 3);
        Vektor3D vek2 = new Vektor3D(2, 3, 4);
        assertTrue(LineareAlgebra.isNotEqual(vek2, vek1));
        Vektor2D vek3 = new Vektor2D(2, -3);
        Vektor2D vek4 = new Vektor2D(2, 3);
        assertTrue(LineareAlgebra.isNotEqual(vek1, vek3));
        assertFalse(LineareAlgebra.isNotEqual(vek1, vek4));
    }

    @Test
    public void testLengthWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 2, 1);
        LineareAlgebra.length(vek);
    }

    @Test
    public void testeLength() throws VektorOverflowException {
        Vektor2D vek1 = new Vektor2D(5, 3);
        Vektor3D vek2 = new Vektor3D(1, -4, 6);
        double exp = Math.sqrt(34);
        double exp2 = Math.sqrt(53);
        assertEquals(exp, LineareAlgebra.length(vek1), 0);
        assertEquals(exp2, LineareAlgebra.length(vek2), 0);
    }

    @Test
    public void testeNormalize() throws VektorOverflowException {
        Vektor2D vek1 = new Vektor2D(12, 23);
        Vektor2D vek2 = (Vektor2D) LineareAlgebra.normalize(vek1);
        assertEquals(1, vek2.length(), 0);
    }

    @Test
    public void testeEuklDistanceWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor2D vek1 = new Vektor2D(2, -3.5433);
        Vektor3D vek2 = new Vektor3D(2.5, 3, 3);
        double res = LineareAlgebra.euklDistance(vek1, vek2);
        System.out.println(res);
    }

    @Test
    public void testeManhattanlDistance() throws VektorOverflowException {
        Vektor2D vek1 = new Vektor2D(5, -4);
        Vektor2D vek2 = new Vektor2D(Double.MIN_VALUE, 3);
        double res = LineareAlgebra.manhattanDistance(vek1, vek2);
        assertEquals(12, res, 0);
        System.out.println(res);
    }

    @Test
    public void testeManhattanlDistanceWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor2D vek1 = new Vektor2D(Double.MAX_VALUE, -4);
        Vektor2D vek2 = new Vektor2D(-3, 3);
        double res = LineareAlgebra.manhattanDistance(vek1, vek2);
        System.out.println(res);
    }

    @Test
    public void testeAbs() {
        Vektor2D vek1 = new Vektor2D(-Double.MAX_VALUE, -4.212);
        LineareAlgebra.abs(vek1);
        assertEquals(Double.MAX_VALUE, vek1.getX(), 0);
        assertEquals(4.212, vek1.getY(), 0);
        System.out.println(vek1.toString());
    }

    @Test
    public void testeCrossProdukt2D() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(1, 2);
        Vektor2D vek1 = new Vektor2D(-7, 8);
        double expected = 22;
        double res = LineareAlgebra.crossProduct(vek, vek1);
        System.out.println(res);
        assertEquals(expected, res, 0);
    }

    @Test
    public void testeCrossProdukt3D() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(1, 2, 3);
        Vektor3D vek1 = new Vektor3D(-7, 8, 9);
        Vektor3D expected = new Vektor3D(-6, -30, 22);
        Vektor3D res = LineareAlgebra.crossProduct(vek, vek1);
        System.out.println(res.toString());
        assertArrayEquals(expected.getVek(), res.getVek(), 0);
    }

    @Test
    public void testeDotProductWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor2D vek = new Vektor2D(2, -3);
        Vektor3D vek1 = new Vektor3D(-4, 2, 6);
        double res = LineareAlgebra.dotProduct(vek1, vek);
    }

    @Test
    public void testeDotProductWithException2() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor3D vek = new Vektor3D(2, -3, 5);
        Vektor3D vek1 = new Vektor3D(-Double.MAX_VALUE, 2, 6);
        double res = LineareAlgebra.dotProduct(vek1, vek);
    }

    @Test
    public void testeDotProduct() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(2, -3);
        Vektor2D vek1 = new Vektor2D(-4, 2);
        double res = LineareAlgebra.dotProduct(vek1, vek);
        assertEquals(-14, res, 0);
    }

    @Test
    public void testecosEquation() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(20, 8, 4);
        Vektor3D vek1 = new Vektor3D(6, 3, 2);
        double res = LineareAlgebra.cosEquation(vek, vek1);
        System.out.println(res);
    }

    @Test
    public void testeSinEquation() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(20, 8, 4);
        Vektor3D vek1 = new Vektor3D(6, 3, 2);
        double res = LineareAlgebra.sinEquation(vek, vek1);
        System.out.println(res);
    }

    @Test
    public void testAngleRad() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(2, 4, -5);
        Vektor3D vek1 = new Vektor3D(-1, 3, -2);
        double res = Math.floor(LineareAlgebra.angleRad(vek, vek1) * 1e3) / 1e3;
        double exp = 0.648;
        assertEquals(exp, res, 0);
    }

    @Test
    public void testAngleDegree() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(2, 2);
        Vektor2D vek1 = new Vektor2D(0, 3);
        double res = Math.floor(LineareAlgebra.angleDegree(vek, vek1) * 1e3) / 1e3;
        assertEquals(45, res, 0);
    }

    @Test
    public void testeDegreeToRad() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(2, 2);
        Vektor2D vek1 = new Vektor2D(0, 3);
        double res = Math.floor(LineareAlgebra.angleDegree(vek, vek1) * 1e3) / 1e3;
        res = LineareAlgebra.degreeToRad(res);
        assertEquals(Math.PI/4, res, 0);
    }

    @Test
    public void testDeterminate() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(0, 3, 1);
        Vektor3D vek1 = new Vektor3D(1, 2, 1);
        Vektor3D vek2 = new Vektor3D(2, 1, 0);
        double res = LineareAlgebra.determinate(vek, vek1, vek2);
        double exp = 3;
        assertEquals(exp, res, 0);
    }

    @Test
    public void testOrthogonal() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(6, 2, -1);
        Vektor3D vek1 = new Vektor3D(2, -7, -2);
        boolean res = LineareAlgebra.areOrthogonal(vek1, vek);
        assertTrue(res);
    }
}