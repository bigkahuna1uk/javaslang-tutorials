package com.usul.training.javaslang;

import javaslang.control.Try;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * DivisorWithTryTest
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class DivisorWithTryTest {

    @Test
    public void divideByZero()
    {
        Try<Integer> result = DivisorWithTry.divide(10, 0);

        assertThat(result.isFailure()).isTrue();
    }

}