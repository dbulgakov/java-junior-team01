package client.validators;

import java.util.function.Predicate;

public interface ExplainedPredicate<T> extends Predicate<T> {
    String getExplanation();
}
