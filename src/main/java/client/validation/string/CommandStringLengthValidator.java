package client.validation.string;

import java.util.List;

/**
 * Проверка длинны сообщения
 *
 * @version 1.1
 * @autor Team-01
 */
public class CommandStringLengthValidator extends AbstractStringValidator {
    private int maxStringLength;

    protected CommandStringLengthValidator(String failExplanation) {
        super(failExplanation);
    }

    private List<String> exludedCommands;

    public CommandStringLengthValidator(int maxStringLength, String failExplanation, List<String> exludedCommands) {
        super(failExplanation);
        this.maxStringLength = maxStringLength;
        this.exludedCommands = exludedCommands;
    }

    @Override
    public boolean test(String stringToCheck) {
        if (stringToCheck != null && exludedCommands != null) {
            return processString(stringToCheck);
        } else {
            return false;
        }
    }

    private boolean processString(String stringToCheck) {
        if (checkIfExcludedcommands(stringToCheck)) {
            return true;
        } else {
            String[] args = stringToCheck.split(" ");
            return args.length >= 2 && args[1].length() <= maxStringLength;
        }
    }

    private boolean checkIfExcludedcommands(String stringToCheck) {
        return exludedCommands.stream().anyMatch(stringToCheck::startsWith);
    }
}
