package client.validation;

import client.validation.result.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
