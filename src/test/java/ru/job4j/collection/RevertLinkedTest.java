package ru.job4j.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.collection.linked.RevertLinked;

import java.util.Iterator;

public class RevertLinkedTest {

    @Test
    public void whenAddThenIter() {
        RevertLinked<Integer> linked = new RevertLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        RevertLinked<Integer> linked = new RevertLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        RevertLinked<Integer> emptyList = new RevertLinked<>();
        assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        RevertLinked<Integer> singleList = new RevertLinked<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }

}