package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public BaseSchema<Integer> required() {
        return super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
        Integer objToInteger;
        try {
            objToInteger = (Integer) obj;
        } catch (Exception e) {
            throw e;
        }
        if (obj != null && obj != "") {
            if (isPositive != null && intStart != null && intEnd != null) {
                return ((objToInteger <= intEnd && objToInteger >= intStart) && (objToInteger / isPositive > 0));
            } else if (isPositive != null) {
                return (objToInteger / isPositive > 0);
            } else if (intStart != null && intEnd != null) {
                return (objToInteger <= intEnd && objToInteger >= intStart);
            } else {
                return true;
            }
        } else {
            return state == null;
        }
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

    @Override
    public BaseSchema<Integer> minLength(Integer length) {
        return this;
    }
}
