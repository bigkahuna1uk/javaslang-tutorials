package com.usul.training.javaslang;

import javaslang.Function2;
import javaslang.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * FunctionsLiftingTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class FunctionsLiftingTest {

    private Function2<Integer, Integer, Integer> divide;
    private Function2<Integer, Integer, Option<Integer>> safeDivide;

    @BeforeEach
    public void setup()
    {
        divide = (a, b) -> a / b;
        safeDivide = Function2.lift(divide);
    }

    @Test
    public void liftReturnsNoneForInvalidParameters()
    {
        Option<Integer> option = safeDivide.apply(1, 0);
        assertThat(option).isEmpty();
    }

    @Test
    public void liftReturnsSomeForValidParameters()
    {
        Option<Integer> option = safeDivide.apply(4, 2);
        assertThat(option).isNotEmpty();
    }

    @Test
    public void liftedFunctionFromMethodReference() throws Exception
    {
        Function2<Integer, Integer, Option<Integer>> lifted = Function2.lift(this::sumMustHaveAllPositiveParameters);

        Option<Integer> result = lifted.apply(-10, 4);

        assertAll("lifted" ,
                () -> assertThat(result).isEmpty(),
                () -> assertThat(lifted.apply(10,2)).isNotEmpty()
        );
    }


    private int sumMustHaveAllPositiveParameters(int first, int second) throws RuntimeException {
        if (first < 0 || second < 0) {
            throw new RuntimeException("Only positive integers are allowed");
        }

        return first + second;
    }

}
