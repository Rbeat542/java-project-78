package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestString {
    private Validator v;
    private Boolean expected;
    private StringSchema schema;

    @BeforeEach
    public void preparation() {
        v = new Validator();
        schema = v.string();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "what does the fox say", "hexlet"})
    public void testNotNullStringAndRequiredIsOff(String input) {
        assertTrue(schema.isValid(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"what does the fox say", "hexlet"})
    public void testNotNullStringAndRequiredIsOn(String input) {
        schema.required();

        assertTrue(schema.isValid(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testShouldReturnTrueForNullAndEmptyStrings(String input) {
        assertTrue(schema.isValid(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testShouldReturnFalseForNullAndEmptyStrings(String input) {
        schema.required();

        assertFalse(schema.isValid(input));
    }


    @ParameterizedTest
    @CsvSource({"wh,what does the fox say", "what,what does the fox say"})
    void testShouldReturnFalseForCorrectWord(ArgumentsAccessor argumentsAccessor) {
        String word = argumentsAccessor.getString(0);
        String string = (String) argumentsAccessor.get(1);

        var res = schema.contains(word).isValid(string);

        assertEquals(true, res);
    }

    @ParameterizedTest
    @CsvSource({"whatth,what does the fox say", "sss,what does the fox say"})
    void testShouldReturnFalseForIncorrectWord(ArgumentsAccessor argumentsAccessor) {
        String word = argumentsAccessor.getString(0);
        String string = (String) argumentsAccessor.get(1);

        var res = schema.contains(word).isValid(string);

        assertEquals(false, res);
    }

    @ParameterizedTest
    @CsvSource({"10, 3, Het", "10, 3, Warlord"})
    void testShouldReturnTrueForCorrectMinLength(ArgumentsAccessor argumentsAccessor) {
        Integer firstSet = argumentsAccessor.getInteger(0);
        Integer secondSet = argumentsAccessor.getInteger(1);
        String string = (String) argumentsAccessor.get(2);

        var res = schema.minLength(firstSet).minLength(secondSet).isValid(string);

        assertEquals(true, res);
    }

    @ParameterizedTest
    @CsvSource({"10, 4, Het", "10, 9, Warlord"})
    void testShouldReturnFalseForWrongMinLength(ArgumentsAccessor argumentsAccessor) {
        Integer firstSet = argumentsAccessor.getInteger(0);
        Integer secondSet = argumentsAccessor.getInteger(1);
        String string = (String) argumentsAccessor.get(2);

        var res = schema.minLength(firstSet).minLength(secondSet).isValid(string);

        assertEquals(false, res);
    }
}
