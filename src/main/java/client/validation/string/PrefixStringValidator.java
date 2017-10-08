package client.validation.string;

import java.util.List;

public class PrefixStringValidator extends AbstractStringValidator {
    private List<String> prefixesList;

    protected PrefixStringValidator(String failExplanation) {
        super(failExplanation);
    }

    public PrefixStringValidator(List<String> prefixesList, String failExplanation) {
        super(failExplanation);
        this.prefixesList = prefixesList;
    }

    @Override
    public boolean test(String stringToCheck) {
        return stringToCheck != null && prefixesList != null && prefixesList.stream().anyMatch(stringToCheck::startsWith);
    }
}
