package hexlet.code.schemas;

public class StringSchema {
	public State state;
	public Integer minlength = 0;
	public String str;
	public String obj;
	public Integer numbersOfValidatorCall;

	public StringSchema() {
		this.state = new RequiredOff(this);
	}

	public void setState(State state) {
		this.state = state;
	}

	public void required() {
		state.required();
	}

	public StringSchema minLength(Integer minlength) {
		return state.minLength(minlength);
	}

	public StringSchema contains(String str) {
		return state.contains(str);
	}

	public Boolean isValid(Object obj) { //
		return state.isValid(obj);
	}
}
