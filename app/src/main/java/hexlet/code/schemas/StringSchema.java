package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Integer minlength;
    private String str;

    public static StringSchema string() {
        return new StringSchema();
    }

    public StringSchema required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    public StringSchema minLength(Integer newlength) {
        this.minlength = newlength;
        addRules(obj -> obj != null && obj != "" && obj.length() >= minlength);
        return this;
    }

    public StringSchema contains(String strNew) {
        this.str = strNew;
        addRules(obj -> obj != null && obj != null && obj.contains(str));
        return this;
    }
}
