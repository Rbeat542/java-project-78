package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("checkstyle:MagicNumber")
public final class TestInteger {
    private Boolean expected;
    private Validator v;
    private NumberSchema schema;


    @BeforeEach
    public void preparation() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public void numberTest() {
        var res1 = schema.isValid(5);

        expected = true;

        assertEquals(expected, res1);
    }

    @Test
    public void numberTest2() {
        var res2 = schema.isValid(null);

        expected = true;

		assertEquals(expected, res2);
    }

    @Test
    public void numberTest3() {
        var res3 = schema.positive().isValid(null);

        expected = true;

        assertTrue(expected.equals(res3));
    }

    @Test
    public void numberTest4() {
        schema.required();
        var res4 = schema.isValid(null);

        expected = false;

        assertEquals(expected, res4);
    }

    @Test
    public void numberTest5() {
        var res5 = schema.isValid(10);

        expected = true;

		assertEquals(expected, res5);
    }

    @Test
    public void numberTest6() {
        schema.positive();
        var res6 = schema.isValid(-10);

        expected = false;

		assertEquals(expected, res6);
    }

    @Test
    public void numberTest7() {
        schema.positive();
        var res8 = schema.isValid(0);

        expected = false;

		assertEquals(expected, res8);
    }

    @Test
    public void numberTest8() {
        schema.range(5, 10);
        var res10 = schema.isValid(5);

        expected = true;

        assertEquals(expected, res10);
    }

    @Test
    public void numberTest9() {
        schema.range(5, 10);
        var res11 = schema.isValid(10);

        expected = true;

		assertEquals(expected, res11);
    }

    @Test
    public void numberTest10() {
        schema.range(5, 10);
        var res12 = schema.isValid(4);

        expected = false;

		assertEquals(expected, res12);
    }

    @Test
    public void numberTest11() {
        schema.range(5, 10);
        var res13 = schema.isValid(11);

        expected = false;

        assertEquals(expected, res13);
    }

}
