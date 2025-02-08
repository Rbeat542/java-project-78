package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestMap {
    private Validator v;
    private Boolean expected;
    private MapSchema schema;

    @BeforeEach
    public void preparation() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    public void mapTest1() {
        var res1 = schema.isValid(null);

        expected = true;

        assertEquals(expected, res1);
    }

    @Test
    public void mapTest2() {
        schema.required();
        var res2 = schema.isValid(null);

        expected = false;

        assertEquals(expected, res2);
    }

    @Test
    public void mapTest3() {
        schema.required();
        var res3 = schema.isValid(new HashMap<>());

        expected = true;

        assertEquals(expected, res3);
    }

    @Test
    public void mapTest4() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var res4 = schema.isValid(data);

        expected = true;

        assertEquals(expected, res4);
    }

    @Test
    public void mapTest5() {
        schema.required();
        schema.sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var res5 = schema.isValid(data);

        expected = false;

        assertEquals(expected, res5);
    }

    @Test
    public void mapTest6() {
        schema.required();
        schema.sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        var res6 = schema.isValid(data);

        expected = true;

        assertEquals(expected, res6);
    }
}
