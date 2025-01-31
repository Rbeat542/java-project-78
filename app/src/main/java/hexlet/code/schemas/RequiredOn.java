package hexlet.code.schemas;

public class RequiredOn implements State{
	private StringSchema schema;
	public Integer minlength;
	public String str;
	public String obj;
	public Integer numbersOfValidatorCall;

	public RequiredOn(StringSchema schema) {
		this.schema = schema;
	}

	//public void setState(State state) {
//		this.state = state;
//	}

	@Override
	public void required() {
		this.schema.setState(new RequiredOff(this.schema));
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
	public Boolean isValid(Object obj) { //start validation
		String cToS; //if (numbersOfValidatorCall >= 1) {
		//	state.
		//}
		if (obj == null || "".equals(obj)) {
			return false;
		} else {
			cToS = obj.toString();
			if (schema.str == null) {
				if (schema.minlength != null) {
					return cToS.length() >= schema.minlength;
				} else {
					return true;
				}
			}
		}
		if (schema.minlength == null) {
			return cToS.contains(schema.str);
		}
		return (cToS.contains(schema.str) && cToS.length() >= schema.minlength);
	}
}
