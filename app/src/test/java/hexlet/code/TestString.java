package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("checkstyle:MagicNumber")
public final class TestString {
    private Validator v;
    private Boolean expected;
    private StringSchema schema;

    @BeforeEach
    public void preparation() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void stringTest1() {
        var res1 = schema.isValid("");

        expected = true;

		assertEquals(expected, res1);
    }

    @Test
    public void stringTest2() {
        var res2 = schema.isValid(null);

        expected = true;

		assertEquals(expected, res2);
    }


    @Test
    public void stringTest3() {
        schema.required();
        var res3 = schema.isValid(null);

        expected = false;

		assertEquals(expected, res3);
    }

    @Test
    public void stringTest4() {
        schema.required();
        var res4 = schema.isValid("");

        expected = false;

		assertEquals(expected, res4);
    }

    @Test
    public void stringTest5() {
        schema.required();
        var res5 = schema.isValid("what does the fox say");

        expected = true;

		assertEquals(expected, res5);
    }

    @Test
    public void stringTest6() {
        schema.required();
        var res6 = schema.isValid("hexlet");

        expected = true;

		assertEquals(expected, res6);
    }

    @Test
    public void stringTest7() {
        schema.required();
        var res7 = schema.contains("wh").isValid("what does the fox say");

        expected = true;

		assertEquals(expected, res7);
    }

    @Test
    public void stringTest8() {
        schema.required();
        var res8 = schema.contains("what").isValid("what does the fox say");

        expected = true;

		assertEquals(expected, res8);
    }

    @Test
    public void stringTest9() {
        schema.required();

        expected = false;

        var res9 = schema.contains("whatthe").isValid("what does the fox say");
		assertEquals(expected, res9);
    }

    @Test
    public void stringTest10() {
        schema.required();
        schema.contains("whatthe");
        var res10 = schema.isValid("what does the fox say");

        expected = false;

		assertEquals(expected, res10);
    }

    @Test
    public void stringTest11() {
        schema.required();
        var res11 = schema.minLength(10).minLength(4).isValid("Het");

        expected = false;

		assertEquals(expected, res11);
    }

    @Test
    public void stringTest12() {
        schema.required();
        var res12 = schema.minLength(10).minLength(4).isValid("Hexl");

        expected = true;

		assertEquals(expected, res12);
    }
}
