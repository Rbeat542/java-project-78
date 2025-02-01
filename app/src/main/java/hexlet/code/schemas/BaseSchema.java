package hexlet.code.schemas;

public abstract class BaseSchema {
    public Integer state;
    public Integer minlength;
    public String str;
    public String obj;
    public Integer isPositive;
    public Integer intStart;
    public Integer intEnd;

    public BaseSchema(Integer state, Integer minlength, String str, String obj,
                      Integer isPositive, Integer intStart, Integer intEnd) {
        this.state = state;
        this.minlength = minlength;
        this.str = str;
        this.obj = obj;
        this.isPositive = isPositive;
        this.intStart = intStart;
        this.intEnd = intEnd;
    }

    public void required() {
        if (null != state) {
            state = -state;
        }
        state = 1;
    }

    public Boolean isValid(Object obj) {
        if (this instanceof StringSchema) {
            return isStringValid(obj);
        }
        if (this instanceof NumberSchema) {
            return isNumberValid(obj);
        }
        return false; // need exception
    }

    public Boolean isStringValid(Object obj) {
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
            if (minlength == null) {
                return objToString.contains(str);
            }
            return (objToString.contains(str) && objToString.length() >= minlength);
        }
    }

    public Boolean isNumberValid(Object obj) {
        Integer valueOfObj = (Integer) obj;
        if (state != null) {
            if (obj == null) {
                return false;
            }
            if (intEnd == null && intEnd == null && isPositive == null) {
                return true;
            }
            if (intEnd == null || intEnd == null) {
                return (valueOfObj / isPositive > 0);
            }
            return ((valueOfObj <= intEnd && valueOfObj >= intStart) && (valueOfObj / isPositive > 0));
        }
        return true;
    }

    public abstract StringSchema minLength(Integer newLength);
    public abstract StringSchema contains(String str);
    public abstract NumberSchema range(Integer intStart, Integer intEnd);
    public abstract NumberSchema positive();
}
