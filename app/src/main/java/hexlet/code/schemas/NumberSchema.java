package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private Integer isPositive;
    private Integer intStart;
    private Integer intEnd;

    public NumberSchema required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    public NumberSchema positive() {
        if (isPositive == null) {
            this.isPositive = 1;
        } else {
            this.isPositive = -this.isPositive;
        }
        addRules(obj -> obj != null && obj / isPositive > 0);
        return this;
    }

    public NumberSchema range(Integer intStartNew, Integer intEndNew) {
        this.intStart = intStartNew;
        this.intEnd = intEndNew;
        addRules(obj -> obj != null && obj <= intEnd && obj >= intStart);
        return this;
    }
}
