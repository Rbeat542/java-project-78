package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testNull() {
        var res2 = schema.isValid(null);

        assertEquals(true, res2);
    }

    @ParameterizedTest
    @NullSource
    void testNullAndRequiredOff(Integer input) {
        assertTrue(schema.isValid(input));
    }

    @ParameterizedTest
    @NullSource
    void testNullAndRequiredOn(Integer input) {
        schema.required();

        assertFalse(schema.isValid(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {61, 4})
    public void testPositive(Integer input) {
        schema.positive();

        var res = schema.isValid(input);

        assertEquals(true, res);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    public void testNegative(Integer input) {
        schema.positive();

        var res = schema.isValid(input);

        assertEquals(false, res);
    }

    @ParameterizedTest
    @CsvSource({"5, 10, 5", "5, 10, 10"})
    void testShouldReturnTrueForCorrectValue(ArgumentsAccessor argumentsAccessor) {
        Integer startRange = argumentsAccessor.getInteger(0);
        Integer endRange = argumentsAccessor.getInteger(1);
        Integer testingValue = argumentsAccessor.getInteger(2);
        schema.range(startRange, endRange);

        var res = schema.isValid(testingValue);

        assertEquals(true, res);
    }

    @ParameterizedTest
    @CsvSource({"5, 10, 11", "5, 10, 4"})
    void testShouldReturnFalseForWrongValue(ArgumentsAccessor argumentsAccessor) {
        Integer startRange = argumentsAccessor.getInteger(0);
        Integer endRange = argumentsAccessor.getInteger(1);
        Integer testingValue = argumentsAccessor.getInteger(2);
        schema.range(startRange, endRange);

        var res = schema.isValid(testingValue);

        assertEquals(false, res);
    }

    @ParameterizedTest
    @CsvSource({"-10, -5, -7", "-10, -5, -5"})
    void testShouldReturnTrueForCorrectValueAndWrongPositive(ArgumentsAccessor argumentsAccessor) {
        Integer startRange = argumentsAccessor.getInteger(0);
        Integer endRange = argumentsAccessor.getInteger(1);
        Integer testingValue = argumentsAccessor.getInteger(2);
        schema.range(startRange, endRange);
        schema.positive();

        var res = schema.isValid(testingValue);

        assertEquals(false, res);
    }

}
