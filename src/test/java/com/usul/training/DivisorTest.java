package com.usul.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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