package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestNested {
    private Validator v;
    private Boolean expected;
    private MapSchema schema;
    private Map<String, BaseSchema<String>> schemas;

    @BeforeEach
    public void preparation() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    public void testNestedMapWithCorrectValuesAndLength() {
        Map<String, BaseSchema<String>> schemas1 = new HashMap<>();
        schemas1.put("firstName", v.string().required());
        schemas1.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas1);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        var res1 = schema.isValid(human1);

        assertEquals(true, res1);
    }

    @Test
    public void testNestedMapWithNullValue() {
        Map<String, BaseSchema<String>> schemas2 = new HashMap<>();
        schemas2.put("firstName", v.string().required());
        schemas2.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas2);
        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        var res2 = schema.isValid(human2);

        assertEquals(false, res2);
    }

    @Test
    public void testNestedMapWithIncorrectLength() {
        Map<String, BaseSchema<String>> schemas3 = new HashMap<>();
        schemas3.put("firstName", v.string().required());
        schemas3.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas3);
        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        var res3 = schema.isValid(human3);

        assertEquals(false, res3);
    }

    @Test
    public void testNestedMapWithCorrectValuesAndContains() {
        Map<String, BaseSchema<String>> schemas4 = new HashMap<>();
        schemas4.put("firstName", v.string().required().contains("ya"));
        schemas4.put("lastName", v.string().required().contains("ov"));
        schema.shape(schemas4);
        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Valya");
        human4.put("lastName", "Iakov");

        var res4 = schema.isValid(human4);

        assertEquals(true, res4);
    }
}
