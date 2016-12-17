package com.usul.training.javaslang;

import javaslang.Tuple2;
import javaslang.collection.Queue;
import javaslang.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * QueueTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class QueueTest {

    @Test
    public void enqueue() {
        Queue<Integer> queue = Queue.of(1, 2, 3);
        Queue<Integer> anotherQueue = queue.enqueue(4, 5);

        assertThat(anotherQueue).contains(1, 2, 3, 4, 5);
        assertThat(queue).doesNotContain(4, 5);
    }

    @Test
    public void dequeue() {
        Queue<Integer> queue = Queue.of(1, 2, 3);

        Tuple2<Integer, Queue<Integer>> dequeue = queue.dequeue();

        assertThat(dequeue._1).isEqualTo(1);
        assertThat(dequeue._2).isEqualTo(Queue.of(2, 3));
        assertThat(queue).contains(1, 2, 3);
    }

    @Test
    public void dequeueToEmpty() {
        Option<Tuple2<Integer, Queue<Integer>>> option = Queue.of(1).dequeueOption();
        assertThat(option.isDefined()).isTrue();
        assertThat(option.get()._1).isEqualTo(1);
        assertThat(option.get()._2).isEmpty();


        Option<Tuple2<Object, Queue<Object>>> option2 = Queue.empty().dequeueOption();
        assertThat(option2.isDefined()).isFalse();
    }

    @Test
    public void emptyDequeueToOptions()
    {
        Queue<Integer> queue = Queue.of(1);

        //Some(1, queue())
        Option<Tuple2<Integer, Queue<Integer>>> dequeued = queue.dequeueOption();

        //Some(1)
        Option<Integer> integerOption = dequeued.map(Tuple2::_1);
        assertThat(integerOption).isEqualTo(Option.of(1));

        //Some(queue)
        Option<Queue<Integer>> remaining = dequeued.map(Tuple2::_2);
        assertThat(remaining.get()).isEmpty();
    }
}
