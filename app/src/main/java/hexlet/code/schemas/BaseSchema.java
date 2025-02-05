package hexlet.code.schemas;

@SuppressWarnings({"VisibilityModifier", "DesignForExtension"})
public abstract class BaseSchema<T> {
    public Integer state;
    public String str;
    public Integer isPositive;
    public Integer intStart;
    public Integer intEnd;
    public Integer sizeof;
    public Integer shapeEnabled = 0;
    public Integer minlength;

    public BaseSchema<T> required() {
        if (null != state) {
            state = -state;
        }
        state = 1;
        return this;
    }

    public Boolean isValid(Object obj) {
        return true;
    }

    public BaseSchema<T> minLength(Integer length) {
        return this;
    }

    public BaseSchema<T> contains(String str) {
        return this;
    }
}
