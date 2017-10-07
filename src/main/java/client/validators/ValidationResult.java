package client.validators;

import java.util.List;

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
}
