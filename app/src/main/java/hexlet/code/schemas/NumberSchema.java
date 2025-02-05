package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public BaseSchema<Integer> required() {
        return super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
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
