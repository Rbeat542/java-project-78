package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema(Integer state, Integer isPositive, Integer intStart, Integer intEnd) {
        super(null, null, "", "", isPositive, intStart, intEnd);
    }

    @Override
    public void required() {
        super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
        return super.isValid(obj);
    }

    public StringSchema minLength(Integer emptyNum) {
        return null;
    }

    public StringSchema contains(String emptyStr) {
        return null;
    }

    public NumberSchema positive() {
        if (isPositive == null) {
            this.isPositive = 1;
        } else {
            this.isPositive = -this.isPositive;
        }
        return this;
    }

    public NumberSchema range(Integer intStart, Integer intEnd) {
        this.intStart = intStart;
        this.intEnd = intEnd;
        return this;
    }
}
