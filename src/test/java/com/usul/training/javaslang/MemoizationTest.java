package com.usul.training.javaslang;

import javaslang.Function0;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * MemoizationTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class MemoizationTest {

    private Function0<Double> memoized;

    @BeforeEach
    public void setup()
    {
        memoized = Function0.of(Math::random).memoized();
    }

    @Test
    public void memoized()
    {
        Double value1 = memoized.apply();
        Double value2 = memoized.apply();

        then(value1).isEqualTo(value2);
    }


}
