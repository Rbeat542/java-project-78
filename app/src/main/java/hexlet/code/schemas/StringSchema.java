package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Integer minlength;
    private String str;

    public static StringSchema string() { //cg
        return new StringSchema();
    }

    public BaseSchema<String> required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    @Override
    public Boolean isValid(Object obj) {
        String objToString = "";
        try {
            objToString = (String) obj;
        } catch (Exception e) {
            throw e;
        }
        if (obj == null || obj == "") {
            return getState() == null;
        }
        if (str != null && minlength != null) {
            return (objToString.contains(str) && objToString.length() >= minlength);
        } else if (str != null) {

            return objToString.contains(str);
        } else if (minlength != null) {
            return (objToString.length() >= minlength);
        } else {
            return true;
        }
    }

    @Override
    public BaseSchema<String> minLength(Integer newlength) {
        this.minlength = newlength;
        return this;
    }

    @Override
    public StringSchema contains(String strNew) {
        this.str = strNew;
        return this;
    }
}
