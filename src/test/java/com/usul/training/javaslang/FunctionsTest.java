package com.usul.training.javaslang;

import javaslang.Function2;
import javaslang.Function3;
import javaslang.Function8;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FunctionsTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class FunctionsTest {

    @Test
    public void function2() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        assertThat(sum.apply(2).apply(2)).isEqualTo(4);
        assertThat(sum.apply(2, 2)).isEqualTo(4);
    }

    @Test
    public void function8() {
        Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> sum =
                (a, b, c, d, e, f, g, h) -> a + b + c + d + e + f + g + h;

        assertThat(sum
                .apply(2)
                .apply(2)
                .apply(2)
                .apply(2)
                .apply(2)
                .apply(2)
                .apply(2)
                .apply(2)
        ).isEqualTo(16);

        assertThat(sum.apply(2, 2, 2, 2, 2, 2, 2, 2)).isEqualTo(16);
    }

    @Test
    public void functionFromMethodReference()
    {
        Function3<String, String, String, String> function3 =
                Function3.of(this::joinThreeStrings);

        String apply = function3.apply("Hello", "World", "Chris");

        assertThat(apply).isEqualTo("HelloWorldChris");
    }


    private String joinThreeStrings(String s1, String s2, String s3)
    {
        return s1 + s2 + s3;
    }

}
