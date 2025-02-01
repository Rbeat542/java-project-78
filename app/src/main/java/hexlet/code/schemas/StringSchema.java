package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema (Integer state, Integer minlength, String str, String obj) {
        super(state, minlength, str, obj, null, null, null);
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
    public StringSchema minLength(Integer minlength)  {
        this.minlength = minlength;
        return this;
    }

    @Override
    public StringSchema contains(String str) {
        this.str = str;
        return this;
    }

    @Override
    public NumberSchema positive() {
        return null;
    }

    @Override
    public NumberSchema range(Integer intStart, Integer intEnd) {
        return null;
    }
}
