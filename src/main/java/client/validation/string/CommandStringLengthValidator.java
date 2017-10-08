package client.validation.string;
/**
 * Проверка длинны сообщения
 * @autor Team-01
 * @version 1.0
 */
public class CommandStringLengthValidator extends AbstractStringValidator {
    int maxStringLength;

    protected CommandStringLengthValidator(String failExplanation) {
        super(failExplanation);
    }

    public CommandStringLengthValidator(int maxStringLength, String failExplanation) {
        super(failExplanation);
        this.maxStringLength = maxStringLength;
    }

    @Override
    public boolean test(String stringToCheck) {
        if (stringToCheck != null) {
            String[] args = stringToCheck.split(" ");
            return args.length >= 2 && args[1].length() <= maxStringLength;
        } else {
            return false;
        }
    }
}
