package hexlet.code.schemas;

public interface State {
	void required();
	Boolean isValid(Object obj);
	StringSchema minLength(Integer newLength);
	StringSchema contains(String str);

}
// END