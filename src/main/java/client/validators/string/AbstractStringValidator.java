package client.validators.string;

import client.validators.ExplainedPredicate;

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
