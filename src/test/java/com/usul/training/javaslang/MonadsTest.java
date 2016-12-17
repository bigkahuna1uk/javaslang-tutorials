package com.usul.training.javaslang;

import javaslang.Lazy;
import javaslang.concurrent.Future;
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.control.Try;
import org.junit.jupiter.api.Test;



import java.util.concurrent.*;

import static com.usul.training.javaslang.concurrent.Concurrent.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.BDDAssertions.then;

/**
 * MonadsTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class MonadsTest {

    private Either<String, Integer> either = compute(true).right().map(i -> i * 2).toEither();

    @Test
    public void optionMonad() {
        then(Option.of(null).isEmpty());
        then(Option.of("value")).isNotEmpty();
    }

    @Test
    public void tryMonad() {
        Integer result = Try.of(() -> bunchOfWork()).getOrElse(-1);
        then(result).isEqualTo(0);

        result = Try.of(() -> failedBunchOfWork()).getOrElse(-1);
        then(result).isEqualTo(-1);
    }

    @Test
    public void lazyMonad() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        then(lazy.isEvaluated()).as("lazy is not evaluated before use").isFalse();

        Double value = lazy.get();
        then(value).isNotNull();
        then(lazy.isEvaluated()).as("lazy is evaluated after first use").isTrue();

        Double valueAgain = lazy.get();
        then(valueAgain).isNotNull();
        then(value).as("lazy value should be memoized").isEqualTo(valueAgain);
    }

    @Test
    public void lazyMonadToValue() {
        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
        then(chars).isEqualTo("Yay!");
    }

    @Test
    public void eitherMonad() {

        then(either.right().get()).isEqualTo(2 * 10);
        then(either.left().isEmpty());

        either = compute(false).right().map(i -> i * 2).toEither();
        then(either.right()).isEmpty();
        then(either.left().get()).isEqualTo("Error");
    }

    @Test
    public void futureMonad() throws InterruptedException {
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
        ExecutorService service = new ThreadPoolExecutor(1, 1 , 0L, MILLISECONDS, queue);

        try {
            javaslang.concurrent.Future<Boolean> future = Future
                    .of(service, () -> {
                        MILLISECONDS.sleep(100);
                        return true;
                    });

            future.onComplete(b -> System.err.println("Completed"))
                    .onFailure(b -> System.err.println("Failed result=" + b))
                    .onSuccess(b -> System.err.println("Success result=" + b));

            waitUntil(future::isCompleted);

            then(future.get()).isTrue();
        } finally
        {
            service.shutdown();
        }
    }

    private Either<String, Integer> compute(boolean isSuccess) {
        if (isSuccess) {
            return Either.right(10);
        } else {
            return Either.left("Error");
        }

    }


    private int bunchOfWork() {
        return 0;
    }

    private int failedBunchOfWork() {
        throw new RuntimeException();
    }
}
