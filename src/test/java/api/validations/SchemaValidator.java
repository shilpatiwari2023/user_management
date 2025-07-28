package api.validations;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidator {
    public static void validateUserSchema() {
        matchesJsonSchemaInClasspath("schemas/userSchema.json");
    }
}
