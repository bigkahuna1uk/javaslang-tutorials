package com.usul.training.javaslang;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * DivisorTest
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class DivisorTest {

    @DisplayName("╯°□°）╯")
    @Test
    public void divideByZeroGivesError()
    {
        assertThrows(RuntimeException.class, () -> Divisor.divide(10,0));
    }

}