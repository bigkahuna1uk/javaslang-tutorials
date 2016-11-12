package com.usul.training.javaslang;

import javaslang.collection.SortedSet;
import javaslang.collection.TreeSet;
import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SortedSetTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class SortedSetTest {


    @Test
    public void sorted() {
        SortedSet<Integer> set = TreeSet.of(6, 2, 4, 2, 3, 5, 6);

        assertThat(set).containsExactly(2, 3, 4, 5, 6);
    }

    @Test
    public void reversed() {
        TreeSet<Integer> set = TreeSet.of(6, 2, 4, 2, 3, 5, 6);
        Comparator<Integer> c = (a, b) -> b - a;

        SortedSet<Integer> reversed = TreeSet.ofAll(c, set);


        assertThat(set).containsExactly(2, 3, 4, 5, 6);
        assertThat(reversed).containsExactly(6,5,4,3,2);
    }

    @Test
    public void add()
    {
        TreeSet<Integer> set = TreeSet.of(6, 2, 4, 2, 3, 5, 6);

        TreeSet<Integer> newSet = set.add(10);

        assertThat(set).doesNotContain(10);
        assertThat(newSet)
                .containsAll(set)
                .contains(10);
    }


}
