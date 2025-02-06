package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer state) {
        this.state = state;
    }

    public abstract BaseSchema<T> required();

    public abstract Boolean isValid(Object obj);

    public abstract BaseSchema<T> minLength(Integer length);

    public abstract BaseSchema<T> contains(String someString);
}
