package com.usul.training.javaslang;

import javaslang.Function0;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Memoization
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class Memoization {

    private Function0<Double> memoized;

    @Before
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
