package com.usul.training;

import javaslang.collection.List;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ListExamples
 *
 * @author bigkahuna
 * @since 30/10/2016
 */
public class ListExamples {

    private List<Integer> list;

    @Before
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
        List cleardList = list.clear();

        assertThat(cleardList).isEmpty();
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
