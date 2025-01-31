package hexlet.code.schemas;

public class RequiredOff implements State{
	private StringSchema schema;
	public Integer minlength;
	public String str;
	public String obj;
	public Integer numbersOfValidatorCall;

	public RequiredOff(StringSchema schema) {
		this.schema = schema;
	}

	@Override
	public void required() {
		this.schema.setState(new RequiredOn(this.schema));
	}

	@Override
	public StringSchema minLength(Integer minlength) {
		schema.minlength = minlength;
		return schema;
	}

	@Override
	public StringSchema contains(String str) {
		schema.str = str;
		return schema;
	}

	@Override
	public Boolean isValid(Object obj) {
		return true;
	}
}
