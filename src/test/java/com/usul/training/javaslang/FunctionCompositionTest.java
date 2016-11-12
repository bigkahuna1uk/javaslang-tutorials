package com.usul.training.javaslang;

import javaslang.Function1;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * FunctionCompositionTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class FunctionCompositionTest {


    private Function1<Integer, Integer> plusOne;
    private Function1<Integer, Integer> multiplyBy2;

    @Before
    public void setup()
    {
        plusOne = a -> a + 1;
        multiplyBy2 = a -> a * 2;
    }

    @Test
    public void andThen()
    {
        Function1<Integer,Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyBy2);

        Integer result = add1AndMultiplyBy2.apply(1);

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void compose()
    {
        Function1<Integer, Integer> compose = multiplyBy2.compose(plusOne);

        Integer result = compose.apply(1);

        assertThat(result).isEqualTo(4);
    }
}
