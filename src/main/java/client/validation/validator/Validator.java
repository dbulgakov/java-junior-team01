package client.validation.validator;

import client.validation.result.ValidationResult;
/** Интерфейс для гибкого расширения валидации сообщений
 * @autor Team-01
 * @version 1.0
 */
public interface Validator<T> {
    ValidationResult validate(T valueToValidate);
}
