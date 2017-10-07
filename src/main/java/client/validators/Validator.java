package client.validators;

import java.util.List;

public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
