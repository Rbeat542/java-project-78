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
        String objToString;
        if (state != null) {
            if (obj == null || "".equals(obj)) {
                return false;
            } else {
                objToString = obj.toString();
                if (str == null) {
                    if (minlength != null) {
                        return objToString.length() >= minlength;
                    } else {
                        return true;
                    }
                }
            }
            if (minlength == null) {
                return objToString.contains(str);
            }
            return (objToString.contains(str) && objToString.length() >= minlength);
        }
        if (obj == null || "".equals(obj)) {
            return true;
        } else {
            objToString = obj.toString();
            if (minlength == null && str != null) {
                return objToString.contains(str);
            }
            return (objToString.length() >= minlength);
        }
    }

    @Override
    public BaseSchema<String> minLength(Integer newlength) {
        minlength = newlength;
        return this;
    }

    public StringSchema contains(String str) {
        this.str = str;
        return this;
    }
}
