package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema(Integer state, Integer isPositive, Integer intStart, Integer intEnd) {
        super(null, null, "", "", isPositive, intStart, intEnd, null);
    }

    @Override
    public void required() {
        super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
        return super.isValid(obj);
    }

    @Override
    public StringSchema minLength(Integer emptyNum) {
        return null;
    }

    @Override
    public StringSchema contains(String emptyStr) {
        return null;
    }

    @Override
    public NumberSchema positive() {
        if (isPositive == null) {
            this.isPositive = 1;
        } else {
            this.isPositive = -this.isPositive;
        }
        return this;
    }

    @Override
    public NumberSchema range(Integer intStart, Integer intEnd) {
        this.intStart = intStart;
        this.intEnd = intEnd;
        return this;
    }

    @Override
    public MapSchema sizeof(Integer newSizeOf) {
        return null;
    }
}
