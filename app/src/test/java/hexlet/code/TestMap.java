package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @ParameterizedTest
    @NullSource
    void testNullAndRequiredOff(Map input) {
        assertTrue(schema.isValid(input));
    }

    @ParameterizedTest
    @NullSource
    void testNullAndRequiredOn(Map input) {
        schema.required();

        assertFalse(schema.isValid(input));
    }


    @Test
    public void testEmptyHashMap() {
        schema.required();
        var res3 = schema.isValid(new HashMap<>());

        assertEquals(true, res3);
    }

    @Test
    public void testCorrectMap() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var res4 = schema.isValid(data);

        assertEquals(true, res4);
    }

    @Test
    public void testMapWithWrongSize() {
        schema.required();
        schema.sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var res5 = schema.isValid(data);

        assertEquals(false, res5);
    }

    @Test
    public void testMapWithCorrectSize() {
        schema.required();
        schema.sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        var res6 = schema.isValid(data);

        assertEquals(true, res6);
    }
}
