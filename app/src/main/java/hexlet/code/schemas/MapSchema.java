package hexlet.code.schemas;

import hexlet.code.Validator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    private Map objToMap;
    private Map schemas;

    public static MapSchema number() {
        return new MapSchema();
    }

    @Override
    public BaseSchema<Map> required() {
        return super.required();
    }

    @Override
    public Boolean isValid(Object obj) {
        if (obj == null && state != null) {
            return false;
        }
        if (obj != null && !(obj instanceof Map)) {
            return false;
        }

        if (obj instanceof Map) {
            objToMap = (Map) obj;
        }

        if (shapeEnabled == 1 && obj != null) {
            return nesting();
        }

        if (sizeof != null) {
            if (sizeof == objToMap.size()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public MapSchema sizeof(Integer newSizeOf) {
        this.sizeof = newSizeOf;
        return this;
    }

    public void shape(Map newMap) {
        if (shapeEnabled == 0) {
            shapeEnabled = 1;
            schemas = newMap;
        }
    }

    @Override
    public BaseSchema<Map> minLength(Integer length) {
        return this;
    }

    public Boolean nesting() {
        try {
            var pairOfBoolean = new ArrayList<Boolean>();
            var keys = objToMap.keySet();
            for (var key : keys) {
                var schema = schemas.get(key);
                var value = objToMap.get(key); //make local variable value
                var v = new Validator();
                var tempSchema = v.string();
                try {
                    Method isValid = tempSchema.getClass().getDeclaredMethod("isValid", Object.class);
                    pairOfBoolean.add((Boolean) isValid.invoke(schema, value));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (pairOfBoolean.get(0) && pairOfBoolean.get(1)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }
}
