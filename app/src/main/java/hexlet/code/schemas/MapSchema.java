package hexlet.code.schemas;

public class MapSchema extends BaseSchema {

    public MapSchema(Integer state, Integer newSizeOf) {
        super(null, null, "", "", null, null, null, null);
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
        return null;
    }

    @Override
    public StringSchema contains(String emptyStr) {
        return null;
    }

    @Override
    public NumberSchema positive() {
        return null;
    }

    @Override
    public NumberSchema range(Integer intStart, Integer intEnd) {
        return null;
    }

    @Override
    public MapSchema sizeof(Integer newSizeOf) {
        this.sizeof = newSizeOf;
        return this;
    }
}
