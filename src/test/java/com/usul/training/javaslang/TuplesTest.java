package com.usul.training.javaslang;

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.control.Option;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TuplesTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class TuplesTest {

    @Test
    public void groupBy()
    {
        Map<Integer, List<Integer>> mapOfTuples = List.of(1, 2, 3, 4).groupBy(i -> i % 2);

        assertThat(mapOfTuples.get(0)).isEqualTo(Option.of(List.of(2,4)));
        assertThat(mapOfTuples.get(1)).isEqualTo(Option.of(List.of(1,3)));
    }

    @Test
    public void zip()
    {
        List<Tuple2<Character, Long>> tuple2List = List.of('a', 'b', 'c').zipWithIndex();


        assertThat(tuple2List.get(0)).isEqualTo(Tuple.of('a', 0L));
        assertThat(tuple2List.get(1)).isEqualTo(Tuple.of('b', 1L));
        assertThat(tuple2List.get(2)).isEqualTo(Tuple.of('c', 2L));
    }

    @Test
    public void mapByComponent()
    {
        Tuple2<Integer, Integer> tuple2 = Tuple.of(1, 2);
        Tuple2<Integer, Integer> mapped = tuple2.map(i -> i + 1, j -> j + 1);

        assertThat(mapped._1).isEqualTo(2);
        assertThat(mapped._2).isEqualTo(3);
    }

    @Test
    public void mapWithBiFunction()
    {
        Tuple2<Integer, Integer> tuple2 = Tuple.of(1, 2);
        Tuple2<Integer, Integer> mapped = tuple2.map((i,j) -> Tuple.of(i + 1,  j + 1));

        assertThat(mapped._1).isEqualTo(2);
        assertThat(mapped._2).isEqualTo(3);
    }

    @Test
    public void transform()
    {
        Tuple2<Integer, Integer> tuple2 = Tuple.of(1, 2);
        String transform = tuple2.transform((i, j) -> i + " -> " + j);

        assertThat(transform).isEqualTo("1 -> 2");
    }

}
