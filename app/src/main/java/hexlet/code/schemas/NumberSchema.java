package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private Integer isPositive;
    private Integer intStart;
    private Integer intEnd;


    public BaseSchema<Integer> required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    @Override
    public Boolean isValid(Object obj) {
        Integer objToInteger;
        try {
            objToInteger = (Integer) obj;
        } catch (Exception e) {
            throw e;
        }

        if (obj == null || obj == "") {
            return getState() == null;
        }
        if (isPositive != null && intStart != null && intEnd != null) {
            return ((objToInteger <= intEnd && objToInteger >= intStart) && (objToInteger / isPositive > 0));
        } else if (isPositive != null) {
            return (objToInteger / isPositive > 0);
        } else if (intStart != null && intEnd != null) {
            return (objToInteger <= intEnd && objToInteger >= intStart);
        } else {
            return true;
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

    public NumberSchema range(Integer intStartNew, Integer intEndNew) {
        this.intStart = intStartNew;
        this.intEnd = intEndNew;
        return this;
    }

    @Override
    public BaseSchema<Integer> minLength(Integer length) {
        return this;
    }

    @Override
    public BaseSchema<Integer> contains(String str) {
        return this;
    }
}
