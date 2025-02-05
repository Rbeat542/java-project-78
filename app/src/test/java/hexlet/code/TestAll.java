package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertTrue;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("checkstyle:MagicNumber")
public final class TestAll {
    private Validator v;
    private Boolean expected;

    @BeforeEach
    public void preparation() {
        v = new Validator();
    }

    @Test
    public void stringTest() {
        var schema = v.string();

        expected = true;
        var res1 = schema.isValid("");
        assertTrue(expected.equals(res1));

        expected = true;
        var res2 = schema.isValid(null);
        assertTrue(expected.equals(res2));

        schema.required();

        expected = false;
        var res3 = schema.isValid(null);
        assertTrue(expected.equals(res3));

        expected = false;
        var res4 = schema.isValid("");
        assertTrue(expected.equals(res4));

        expected = true;
        var res5 = schema.isValid("what does the fox say");
        assertTrue(expected.equals(res5));

        expected = true;
        var res6 = schema.isValid("hexlet");
        assertTrue(expected.equals(res6));

        expected = true;
        var res7 = schema.contains("wh").isValid("what does the fox say");
        assertTrue(expected.equals(res7));

        expected = true;
        var res8 = schema.contains("what").isValid("what does the fox say");
        assertTrue(expected.equals(res8));

        expected = false;
        var res9 = schema.contains("whatthe").isValid("what does the fox say");
        assertTrue(expected.equals(res9));

        expected = false;
        var res10 = schema.isValid("what does the fox say");
        assertTrue(expected.equals(res10));

        var schema1 = v.string();
        expected = false;
        var res11 = schema1.minLength(10).minLength(4).isValid("Het");
        assertTrue(expected.equals(res11));

        expected = true;
        var res12 = schema1.minLength(10).minLength(4).isValid("Hexl");
        assertTrue(expected.equals(res12));
    }

    @Test
    public void numberTest() {
        var schema2 = v.number();

        expected = true;
        var res1 = schema2.isValid(5);
        assertTrue(expected.equals(res1));

        expected = true;
        var res2 = schema2.isValid(null);
        assertTrue(expected.equals(res2));

        expected = true;
        var res3 = schema2.positive().isValid(null);
        assertTrue(expected.equals(res3));

        schema2.required();

        expected = false;
        var res4 = schema2.isValid(null);
        assertTrue(expected.equals(res4));

        expected = true;
        var res5 = schema2.isValid(10);
        assertTrue(expected.equals(res5));

        expected = false; // false
        var res6 = schema2.isValid(-10);
        assertTrue(expected.equals(res6));

        expected = false; // false
        var res7 = schema2.isValid(-10);
        assertTrue(expected.equals(res7));

        expected = false; // false
        var res8 = schema2.isValid(0);
        assertTrue(expected.equals(res8));

        schema2.range(5, 10);

        expected = true;
        var res10 = schema2.isValid(5);
        assertTrue(expected.equals(res10));

        expected = true;
        var res11 = schema2.isValid(10);
        assertTrue(expected.equals(res11));

        expected = false;
        var res12 = schema2.isValid(4);
        assertTrue(expected.equals(res12));

        expected = false;
        var res13 = schema2.isValid(11);
        assertTrue(expected.equals(res13));
    }

    @Test
    public void mapTest() {
        var schema3 = v.map();

        expected = true;
        var res1 = schema3.isValid(null);
        assertTrue(expected.equals(res1));

        schema3.required();

        expected = false;
        var res2 = schema3.isValid(null);
        assertTrue(expected.equals(res2));

        expected = true;
        var res3 = schema3.isValid(new HashMap<>());
        assertTrue(expected.equals(res3));

        expected = true;
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var res4 = schema3.isValid(data);
        assertTrue(expected.equals(res4));

        expected = false;
        schema3.sizeof(2);
        var res5 = schema3.isValid(data);
        assertTrue(expected.equals(res5));

        expected = true;
        data.put("key2", "value2");
        var res6 = schema3.isValid(data);
        assertTrue(expected.equals(res6));
    }

    @Test
    public void extendedMapTest() {
        var schema4 = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema4.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        expected = true;
        var res1 = schema4.isValid(human1);
        assertTrue(expected.equals(res1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        expected = false;
        var res2 = schema4.isValid(human2);
        assertTrue(expected.equals(res2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        expected = false;
        var res3 = schema4.isValid(human3);
        assertTrue(expected.equals(res3));
    }
}
