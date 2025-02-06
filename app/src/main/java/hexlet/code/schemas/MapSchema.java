package hexlet.code.schemas;

import hexlet.code.Validator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    private Map schemas;
    private Integer shapeEnabled = 0;
    private Integer sizeof;

    public static MapSchema number() {
        return new MapSchema();
    }

    public BaseSchema<Map> required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    @Override
    public Boolean isValid(Object obj) {
        var objToMap = new HashMap<Object, Object>();
        try {
            objToMap = (HashMap) obj;
        } catch (Exception e) {
            throw e;
        }

        if ((obj == null || obj == "") && shapeEnabled == 0) {
            return getState() == null;
        }

        if (shapeEnabled == 0) {
            if (sizeof != null) {
                return (objToMap.size() >= sizeof);
            } else {
                return true;
            }
        }

        if (shapeEnabled == 1 && obj != null) {
            return nesting(objToMap);
        } else {
            return false;
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

    @Override
    public BaseSchema<Map> contains(String str) {
        return this;
    }

    public Boolean nesting(Map objToMap) {
        var pairOfBoolean = new ArrayList<Boolean>();
        var keys = objToMap.keySet();
        for (var key : keys) {
            var schema = schemas.get(key);
            var value = objToMap.get(key);
            var v = new Validator();
            try {
                Method isValid = v.string().getClass().getDeclaredMethod("isValid", Object.class);
                pairOfBoolean.add((Boolean) isValid.invoke(schema, value));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (pairOfBoolean.get(0) && pairOfBoolean.get(1)) {
            return true;
        }
        return false;
    }
}
