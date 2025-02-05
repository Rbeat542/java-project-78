package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Integer minlength;

    public static StringSchema string() { //cg
        return new StringSchema();
    }

    @Override
    public BaseSchema<String> required() {
        return super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
        String objToString = "";
        try {
            objToString = (String) obj;
        } catch (Exception e) {
            throw e;
        }
        if (obj != null && obj != "") {
            if (str != null && minlength != null) {
                return (objToString.contains(str) && objToString.length() >= minlength);
            } else if (str != null) {
                return objToString.contains(str);
            } else if (minlength != null) {
                return (objToString.length() >= minlength);
            } else {
                return true;
            }
        } else {
            return state == null;
        }
    }

    @Override
    public BaseSchema<String> minLength(Integer newlength) {
        this.minlength = newlength;
        return this;
    }

    public StringSchema contains(String str) {
        this.str = str;
        return this;
    }
}
