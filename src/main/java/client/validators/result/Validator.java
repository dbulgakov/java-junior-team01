package client.validators.result;

import client.validators.result.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
