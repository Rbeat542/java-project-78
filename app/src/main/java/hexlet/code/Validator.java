package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public final BaseSchema string() {
        return new StringSchema(null, null, "", "");
    }

    public final BaseSchema number() {
        return new NumberSchema(null, null, null, null);
    }

    public final BaseSchema map() {
        return new MapSchema(null, null);
    }

}
