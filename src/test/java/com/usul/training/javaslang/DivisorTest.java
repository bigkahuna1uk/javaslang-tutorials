package com.usul.training.javaslang;

import org.junit.Test;

/**
 * DivisorTest
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class DivisorTest {


    @Test(expected = RuntimeException.class)
    public void divideByZeroGivesError()
    {
       Divisor.divide(10,0);
    }

}