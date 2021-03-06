package com.usul.training.javaslang;

import javaslang.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ListExamplesTest
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class ListExamplesTest {

    private List<Integer> list;

    @BeforeEach
    public void setup() {
        list = List.of(1, 2, 3);
    }

    @Test
    public void prepend() {

        List<Integer> list2 = list.tail().prepend(0);

        assertThat(list2).containsExactly(0, 2, 3);
    }

    @Test
    public void clear()
    {
        List clearedList = list.clear();

        assertThat(clearedList).isEmpty();
        assertThat(list).isNotEmpty();
    }

    @Test
    public void drop()
    {
        List dropped = list.drop(2);

        assertThat(dropped).containsOnly(3);
    }

    @Test
    public void dropRight()
    {
        List dropped = list.dropRight(2);

        assertThat(dropped).containsOnly(1);
    }

}
