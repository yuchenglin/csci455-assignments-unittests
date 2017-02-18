import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolynomialTest {
    @Test
    public void addTerm() throws Exception {
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
    public void add() throws Exception {
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
    public void eval() throws Exception {
        Polynomial poly1 = new Polynomial(new Term(3, 3))
                .add(new Polynomial(new Term(2, 1)))
                .add(new Polynomial(new Term(7, 0)));

        double x = 2;
        assertEquals("eval", 35, poly1.eval(x), 0.01);

        x = 1.5;
        assertEquals("eval", 20.125, poly1.eval(x), 0.01);
    }

    @Test
    public void toFormattedString() throws Exception {
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
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void empty() {
//        new ArrayList<Object>().get(0);
//    }
}