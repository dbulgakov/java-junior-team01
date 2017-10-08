package client.validation.string;

import client.validation.predicate.ExplainedPredicate;

public abstract class AbstractStringValidator implements ExplainedPredicate<String> {
    private String failExplanation;

    protected AbstractStringValidator(String failExplanation) {
        this.failExplanation = failExplanation;
    }

    @Override
    public String getExplanation() {
        return failExplanation;
    }
}
