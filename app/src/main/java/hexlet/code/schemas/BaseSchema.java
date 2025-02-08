package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private ArrayList<Predicate<T>> rules = new ArrayList<Predicate<T>>();
    private Integer state;

    public final Integer getState() {
        return state;
    }

    public final void setState(Integer stateNew) {
        this.state = stateNew;
    }

    public abstract BaseSchema<T> required();

    public final void addRules(Predicate<T> newRule) {
        rules.add(newRule);
    }

    public final Boolean isValid(T obj) {
        if ((obj == null || obj == "") && state != null) {
            return false;
        }
        if (obj == null || obj == "") {
            return true;
        } else {
            return this.rules.stream().allMatch(rule -> rule.test(obj));
        }
    }
}
