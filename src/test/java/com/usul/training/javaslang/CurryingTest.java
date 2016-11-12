package com.usul.training.javaslang;

import javaslang.Function1;
import javaslang.Function2;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * CurryingTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class CurryingTest {

    private Function2<Integer, Integer, Integer> sum;

    @Before
    public void setup()
    {
        sum = (a, b) -> a + b;
    }

    @Test
    public void currying()
    {
        Function1<Integer, Integer> curried = sum.curried().apply(2);

        then(curried.apply(4)).isEqualTo(6);
    }


}
