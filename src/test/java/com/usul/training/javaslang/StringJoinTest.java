package com.usul.training.javaslang;

import javaslang.collection.List;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * StringJoinTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class StringJoinTest {


    @Test
    public void joinWithFold()
    {
        String join = joinWithFold("Hello", "World");

        assertThat(join).isEqualTo("HelloWorld");
    }

    @Test
    public void joinWithMkString()
    {
        String join = joinWithMkString("Hello", "World");

        assertThat(join).isEqualTo("HelloWorld");
    }

    @Test
    public void joinOtherTypes()
    {
        String join = joinInts(1, 2, 3 ,4 ,5);

        assertThat(join).isEqualTo("12345");
    }


    private String joinWithFold(String... words)
    {
        return List.of(words)
                .fold("", String::concat);
    }

    private String joinWithMkString(String... words) {
        return List.of(words).mkString();
    }

    private String joinInts(Integer... numbers)
    {
        return List.of(numbers).mkString();
    }

}
