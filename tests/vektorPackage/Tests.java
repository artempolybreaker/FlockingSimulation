/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package vektorPackage;

import exceptions.VektorOverflowException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testeSetVektor() {
        Vektor2D vek = new Vektor2D();
        double[] a = {5, 5};
        vek.setPosition(a);
        assertEquals(a[0], vek.getX(), 0);
        assertEquals(a[1], vek.getY(), 0);
        Vektor3D vek3 = new Vektor3D();
        double[] b = {-8, 5, 6};
        vek3.setPosition(b);
        assertEquals(b[0], vek3.getX(), 0);
        assertEquals(b[1], vek3.getY(), 0);
        assertEquals(b[2], vek3.getZ(), 0);
    }

    @Test
    public void testeIsNullVektor() {
        Vektor2D vek = new Vektor2D();
        Vektor3D vek3 = new Vektor3D();
        vek.setPosition(0, 0);
        vek3.setPosition(0, 0, 0);
        assertTrue(vek.isNullVektor());
        assertTrue(vek3.isNullVektor());
        vek.setPosition(1, 0);
        vek3.setPosition(65, 34, 0);
        assertFalse(vek.isNullVektor());
        assertFalse(vek3.isNullVektor());
    }

    @Test
    public void testAdditionWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 0, 0);
        Vektor3D vek2 = new Vektor3D(Double.MAX_VALUE, 1, 2);
        vek.add(vek2);
        Vektor2D vek1 = new Vektor2D(1, 2);
        vek1.add(vek);
    }

    @Test
    public void testAddition() throws VektorOverflowException {
        Vektor3D vek = new Vektor3D(12, 0, 0);
        Vektor3D vek2 = new Vektor3D(0, 1, 2);
        vek.add(vek2);
        System.out.println(vek.toString());
    }

    @Test
    public void testSubbtraktion() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(2, 3);
        Vektor2D vek2 = new Vektor2D(1, 3.5);
        vek.sub(vek2);
        Vektor2D expected = new Vektor2D(1, -0.5);
        assertEquals(expected.getX(), vek.getX(), 0.001);
        assertEquals(expected.getY(), vek.getY(), 0.001);
    }

    @Test
    public void testMultiplikation() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(6, 0);
        vek.mult(0);
        assertEquals(0, vek.getX(), 0);
        assertEquals(0, vek.getY(), 0);
        Vektor3D vek2 = new Vektor3D(Double.MAX_VALUE, Double.MIN_VALUE, 9);
        vek2.mult(1);
        assertEquals(Double.MAX_VALUE, vek2.getX(), 0);
        assertEquals(Double.MIN_VALUE, vek2.getY(), 0);
        assertEquals(9, vek2.getZ(), 0);
        vek2.mult(0);
        assertEquals(0, vek2.getX(), 0);
        assertEquals(0, vek2.getY(), 0);
        assertEquals(0, vek2.getZ(), 0);
        vek2.mult(Double.MIN_VALUE);
        assertEquals(0, vek2.getX(), 0);
        assertEquals(0, vek2.getY(), 0);
        assertEquals(0, vek2.getZ(), 0);
        vek2.setPosition();
    }

    @Test
    public void testDivisionWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor2D vek = new Vektor2D(5, 5);
        vek.div(Double.MIN_VALUE);
    }

    @Test
    public void testDivision() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(5, -5);
        vek.div(-2);
        assertEquals(-2.5, vek.getX(), 0);
        assertEquals(2.5, vek.getY(), 0);
        Vektor3D vek2 = new Vektor3D(5, -5, 0.1);
        vek2.div(0.1);
        assertEquals(50, vek2.getX(), 0);
        assertEquals(-50, vek2.getY(), 0);
        assertEquals(1, vek2.getZ(), 0);
    }

    @Test
    public void testIsEqual() {
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 5, 5);
        Vektor3D vek2 = new Vektor3D(Double.MAX_VALUE, 5, 5);
        assertTrue(vek.isEqual(vek2));
        Vektor2D vek3 = new Vektor2D(5, 5);
        Vektor2D vek4 = new Vektor2D(5, 5.001);
        assertFalse(vek3.isEqual(vek4));
    }

    @Test
    public void testIsNotEqual() {
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 5, 3);
        Vektor2D vek2 = new Vektor2D(Double.MAX_VALUE, 5);
        assertTrue(vek.isNotEqual(vek2));
        Vektor2D vek3 = new Vektor2D(5, 5);
        Vektor2D vek4 = new Vektor2D(5, 5.001);
        assertTrue(vek3.isNotEqual(vek4));
    }

    @Test
    public void testIsSameDimension() {
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 5, 3);
        Vektor2D vek2 = new Vektor2D(Double.MAX_VALUE, 5);
        assertFalse(vek.isSameDimension(vek2));
        Vektor3D vek3 = new Vektor3D(Double.MAX_VALUE, 5, 3);
        assertTrue(vek.isSameDimension(vek3));
    }

    @Test
    public void testLengthWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 2, 1);
        vek.length();
    }

    @Test
    public void testLength() throws VektorOverflowException {
        Vektor2D vek = new Vektor2D(5, 3);
        Vektor3D vek2 = new Vektor3D(1, -4, 6);
        double exp = Math.sqrt(34);
        double exp2 = Math.sqrt(53);
        assertEquals(exp, vek.length(), 0);
        assertEquals(exp2, vek2.length(), 0);
    }

    @Test
    public void testNormalizeWithException() throws VektorOverflowException {
        expectedException.expect(VektorOverflowException.class);
        Vektor3D vek = new Vektor3D(Double.MAX_VALUE, 2, 1);
        vek.normalize();
    }

    @Test
    public void testNormalize() throws VektorOverflowException {
        Vektor2D vek2 = new Vektor2D(Double.MIN_VALUE, -34);
        Vektor3D vek3 = new Vektor3D(2, -34, 3);
        vek2.normalize();
        vek3.normalize();
        assertEquals(1, vek2.length(), 0);
        assertEquals(1, vek3.length(), 0);
    }

    @Test
    public void testNegation() {
        Vektor2D vek2 = new Vektor2D(-1, -34);
        vek2.negiere();
        assertEquals(1, vek2.getX(), 0);
        assertEquals(34, vek2.getY(), 0);
        System.out.println(vek2);
    }

    @Test
    public void testAddCoords() throws VektorOverflowException {
        Vektor2D vek2 = new Vektor2D(-1, -34);
        double wert = vek2.addCoords();
        double exp = -35;
        assertEquals(exp, wert, 0);
    }

}
