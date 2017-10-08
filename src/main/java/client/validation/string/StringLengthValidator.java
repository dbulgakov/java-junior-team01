package client.validation.string;

public class StringLengthValidator extends AbstractStringValidator {
    int maxStringLength;

    protected StringLengthValidator(String failExplanation) {
        super(failExplanation);
    }

    public StringLengthValidator(int maxStringLength, String failExplanation) {
        super(failExplanation);
        this.maxStringLength = maxStringLength;
    }

    @Override
    public boolean test(String stringToCheck) {
        return stringToCheck != null && stringToCheck.length() <= maxStringLength;
    }
}
