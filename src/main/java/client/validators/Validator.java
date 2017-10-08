package client.validators;

public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
