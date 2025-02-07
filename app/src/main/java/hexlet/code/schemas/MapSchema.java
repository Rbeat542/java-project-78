package hexlet.code.schemas;

import hexlet.code.Validator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {
    private Map schemas = new HashMap<>();
    private Integer shapeEnabled = 0;
    private Integer sizeof;

    public Integer getShapeEnabled() {
        return shapeEnabled;
    }

    public void setShapeEnabled(Integer newValue) {
        this.shapeEnabled = newValue;
    }

    public static MapSchema number() {
        return new MapSchema();
    }

    public Map getSchemas() {
        return schemas;
    }

    public MapSchema required() {
        if (null != getState()) {
            setState(-getState());
        }
        setState(1);
        return this;
    }

    public MapSchema sizeof(Integer newSizeOf) {
        this.sizeof = newSizeOf;
        addRules(obj -> obj != null && obj.size() >= sizeof);
        return this;
    }

    public void shape(Map newMap) {
        if (getShapeEnabled() == 0) {
            setShapeEnabled(1);
            this.schemas = newMap;
            addRules(obj -> obj != null && (nesting(obj)));
        }
    }

    public Boolean nesting(Map obj) {
        var pairOfBoolean = new ArrayList<Boolean>();
        var keys = obj.keySet();
        try {
            for (var key : keys) {
               // MapSchema mapSchema = new MapSchema();
                var xx = this.getSchemas();
                var schema = xx.get(key);
                var value = obj.get(key);
                var v = new Validator();
                Method isValid = BaseSchema.class.getDeclaredMethod("isValid", Object.class);
                pairOfBoolean.add((Boolean) isValid.invoke(schema, value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pairOfBoolean.get(0) && pairOfBoolean.get(1)) {
            return true;
        }
        return false;
    }
}
