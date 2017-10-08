package client.validation.validator;

import client.validation.result.ValidationResult;
import client.validation.string.AbstractStringValidator;
import client.validation.string.PrefixStringValidator;
import client.validation.string.StringLengthValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandValidator implements Validator<String> {
    public static final int MAX_COMMAND_LENGTH = 150;

    private static final String ERROR_DESCRIPTION_FORMAT = "Error! %s";
    private static final String ERROR_COMMAND_LENGTH_FORMAT = "Input command must be shorter than %d symbols!";

    private final List<String> supportedCommands;

    private final List<AbstractStringValidator> rules;

    public CommandValidator(List<String> supportedCommands) {
        this.supportedCommands = supportedCommands;
        rules = initializeRules();
    }

    @Override
    public ValidationResult validate(String valueToValidate) {
        List<String> errors = rules.stream().filter(rule -> !rule.test(valueToValidate)).map(AbstractStringValidator::getExplanation).collect(Collectors.toList());
        return new ValidationResult(errors.isEmpty(), errors);
    }

    private List<AbstractStringValidator> initializeRules() {
        return Arrays.asList(
                new PrefixStringValidator(supportedCommands, String.format(ERROR_DESCRIPTION_FORMAT, "Unknown command. Please, try again.")),
                new StringLengthValidator(MAX_COMMAND_LENGTH, String.format(ERROR_DESCRIPTION_FORMAT, String.format(ERROR_COMMAND_LENGTH_FORMAT, MAX_COMMAND_LENGTH))));
    }
}
