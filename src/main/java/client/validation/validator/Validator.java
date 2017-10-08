package client.validation.validator;

import client.validation.result.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
