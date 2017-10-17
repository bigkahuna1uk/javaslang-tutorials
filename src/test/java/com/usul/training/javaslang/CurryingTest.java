package com.usul.training.javaslang;

import javaslang.Function1;
import javaslang.Function2;
import javaslang.Function4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * CurryingTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class CurryingTest {

    private Function2<Integer, Integer, Integer> sum;

    private Function4<Integer, Integer, Integer, Integer, Integer> sum4;


    @BeforeEach
    public void setup() {
        sum = (a, b) -> a + b;
        sum4 = (a, b, c, d) -> a + b + c + d;
    }

    @Test
    public void currying() {
        Function1<Integer, Integer> curried = sum.curried().apply(2);

        then(curried.apply(4)).isEqualTo(6);
    }

    @Test
    public void curryingHalf() {
        Function2<Integer, Integer, Integer> curried2 = sum4.apply(2, 2);

        then(curried2.apply(3,3)).isEqualTo(10);
    }


}
