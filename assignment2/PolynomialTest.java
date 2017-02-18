import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PolynomialTest {
    @Test
    public void addTerm() {
        Polynomial p = new Polynomial(new Term(20, 0))
                .add(new Polynomial(new Term(3, 2)));
        assertEquals("term added in descending order", "3.0x^2 + 20.0", p.toFormattedString());

        p = new Polynomial(new Term(10, 10))
                .add(new Polynomial(new Term(1, 1)))
                .add(new Polynomial(new Term(5, 5)));
        assertEquals("new term size within old terms", "10.0x^10 + 5.0x^5 + x", p.toFormattedString());

        p = new Polynomial(new Term(-3, 10))
                .add(new Polynomial(new Term(0, 1)))
                .add(new Polynomial(new Term(52.3, 0)))
                .add(new Polynomial(new Term(5, 10)))
                .add(new Polynomial(new Term(12, 0)))
                .add(new Polynomial(new Term(-2, 10)));
        assertEquals("multiple terms with the same exponent", "64.3", p.toFormattedString());
    }

    @Test
    public void add() {
        Polynomial poly1 = new Polynomial(new Term(3, 3))
                .add(new Polynomial(new Term(2, 1)))
                .add(new Polynomial(new Term(7, 0)));

        Polynomial poly2 = new Polynomial(new Term(1, 5))
                .add(new Polynomial(new Term(-3, 3)))
                .add(new Polynomial(new Term(5, 0)));

        Polynomial sum = poly1.add(poly2);
        assertEquals("x^5 + 2.0x + 12.0", sum.toFormattedString());
    }

    @Test
    public void eval() {
        Polynomial poly1 = new Polynomial(new Term(3, 3))
                .add(new Polynomial(new Term(2, 1)))
                .add(new Polynomial(new Term(7, 0)));

        double x = 2;
        assertEquals("eval", 35, poly1.eval(x), 0.01);

        x = 1.5;
        assertEquals("eval", 20.125, poly1.eval(x), 0.01);
    }

    @Test
    public void toFormattedString() {
        Polynomial p = new Polynomial(new Term(1, 2))
                .add(new Polynomial(new Term(1, 1)))
                .add(new Polynomial(new Term(1, 0)));
        assertEquals("1 as coefficient", "x^2 + x + 1.0", p.toFormattedString());

        p = new Polynomial(new Term(-1, 100))
                .add(new Polynomial(new Term(-4, 2)))
                .add(new Polynomial(new Term(-24, 0)));
        assertEquals("-1 as coefficient", "-x^100 + -4.0x^2 + -24.0", p.toFormattedString());

        p = new Polynomial();
        assertEquals("empty", "0.0", p.toFormattedString());

        p = new Polynomial(new Term(3, 5));
        assertEquals("3.0x^5", p.toFormattedString());
    }

    @Test
    public void testConstructorSignatures() {
        try {
            // Polynomial()
            Class[] paras = new Class[0];
            Constructor ctor = Polynomial.class.getConstructor(paras);

            // Polynomial(Term)
            paras = new Class[1];
            paras[0] = Term.class;
            ctor = Polynomial.class.getConstructor(paras);
        } catch (NoSuchMethodException e) {
            fail(e.toString());
        }
    }

    @Test
    public void testPublicFunctionSignatures() {
        try {
            // Polynomial.add(Polynomial) -> Polynomial
            Class[] paras = new Class[1];
            paras[0] = Polynomial.class;
            Method method = Polynomial.class.getMethod("add", paras);
            assertEquals("Polynomial.add() returns Polynomial", Polynomial.class, method.getReturnType());

            // Polynomial.eval(double) -> double
            paras = new Class[1];
            paras[0] = double.class;
            method = Polynomial.class.getMethod("eval", paras);
            assertEquals("Polynomial.eval() returns double", double.class, method.getReturnType());

            // Polynomial.toFormattedString() -> String
            paras = new Class[0];
            method = Polynomial.class.getMethod("toFormattedString", paras);
            assertEquals("Polynomial.toFormattedString() returns String", String.class, method.getReturnType());
        } catch (NoSuchMethodException e) {
            fail(e.toString());
        }
    }
}