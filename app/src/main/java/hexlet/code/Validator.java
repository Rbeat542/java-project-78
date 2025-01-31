package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {
	StringSchema schema;
	Integer numbersOfValidatorCall = -1;

	public void Validator() {
		//this.numbersOfValidatorCall = -1;
	}

	public StringSchema string() {
		var schema = new StringSchema();
		schema.numbersOfValidatorCall = this.numbersOfValidatorCall + 1;
		return schema;
	}

}
