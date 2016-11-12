package com.usul.training.javaslang;

import javaslang.control.Try;

/**
 * DivisorWithTry
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class DivisorWithTry {

    public static Try<Integer>divide(Integer dividend, Integer divisor)
    {
        return Try.of(() -> dividend/divisor);
    }

}
