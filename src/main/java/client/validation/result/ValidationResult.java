package client.validation.result;

import java.util.List;
/**
 * Содержит отказы на неправильные сообщения
 * @autor Team-01
 * @version 1.1
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errorDescriptions;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errorDescriptions = errors;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrors() {
        return errorDescriptions;
    }

    public void setErrors(List<String> errors) {
        this.errorDescriptions = errors;
    }

    @Override
    public String toString() {
        if (errorDescriptions != null && errorDescriptions.size() > 0) {
            return errorDescriptions.get(0);
        } else {
            return "";
        }
    }
}
