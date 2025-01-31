package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestAll {
	private Validator v;
	private StringSchema schema;
	private Boolean expected;

	@Test
	void main() {
		v= new Validator();
		schema = v.string();

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
		expected = true;
		var res11 = schema1.minLength(10).minLength(4).isValid("Het");
		assertTrue(expected.equals(res11));
	}
}
