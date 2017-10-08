package client.validation.predicate;

import java.util.function.Predicate;
/**
 * Интерфейс для расширения трактовок предикатов (команд)
 * @autor Team-01
 * @version 1.1
 */
public interface ExplainedPredicate<T> extends Predicate<T> {
    String getExplanation();
}
