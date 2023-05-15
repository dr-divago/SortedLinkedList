import com.drdivago.list.SortedLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ListIterator;

public class SortedLinkedListTest {
    @Test
    void when_list_is_empty_add_element() {
        List<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(1);
        List<Integer> expected = List.of(1);
        Assertions.assertEquals(expected, sortedLinkedList);
    }

    @Test
    void when_list_is_not_empty_add_element_at_beginning() {
        List<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(2);
        sortedLinkedList.add(1);
        sortedLinkedList.add(0);

        List<Integer> expected = List.of(0, 1, 2);
        Assertions.assertEquals(expected, sortedLinkedList);
    }

    @Test
    void when_list_is_not_empty_add_element_at_end() {
        List<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(1);
        sortedLinkedList.add(2);
        sortedLinkedList.add(3);

        List<Integer> expected = List.of(1,2,3);
        Assertions.assertEquals(expected, sortedLinkedList);
    }
    @Test
    void when_list_is_not_empty_add_element_at_middle() {
        List<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(1);
        sortedLinkedList.add(3);
        sortedLinkedList.add(2);

        List<Integer> expected = List.of(1, 2, 3);
        Assertions.assertEquals(expected, sortedLinkedList);
    }

    @Test
    void when_list_is_not_empty_contains_node_return_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        Assertions.assertTrue(list.contains(1));
        Assertions.assertFalse(list.contains(4));
    }

    @Test
    void when_list_is_empty_contains_return_false() {
        List<Integer> list = new SortedLinkedList<>();
        Assertions.assertFalse(list.contains(1));
    }

    @Test
    void when_list_to_array_then_array_is_valid() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        Object[] expected = {1, 2, 3};
        Assertions.assertArrayEquals(expected, list.toArray());
    }

    @Test
    void when_list_is_empty_remove_return_false() {
        List<Integer> list = new SortedLinkedList<>();
        Assertions.assertFalse(list.remove(Integer.valueOf(1)));
    }

    @Test
    void when_list_one_element_remove_return_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        Assertions.assertTrue(list.remove(Integer.valueOf(1)));
        Assertions.assertTrue(list.isEmpty());
    }
    @Test
    void when_list_is_not_empty_remove_beginning_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(2, 3);

        Assertions.assertTrue(list.remove(Integer.valueOf(1)));
        Assertions.assertEquals(expected, list);
    }

    @Test
    void when_list_remove_middle_remove_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 3);
        list.remove(Integer.valueOf(2));

        Assertions.assertEquals(expected, list);
    }

    @Test
    void when_remove_last_node_then_element_is_removed() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 2);
        list.remove(Integer.valueOf(3));

        Assertions.assertEquals(expected, list);
    }
    @Test
    void when_remove_index_beginning_node_then_element_is_removed() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(2, 3);
        list.remove(0);

        Assertions.assertEquals(expected, list);
    }

    @Test
    void when_remove_index_middle_node_then_element_is_removed() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 3);
        list.remove(1);

        Assertions.assertEquals(expected, list);
    }

    @Test
    void when_remove_index_end_node_then_element_is_removed() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 2);
        list.remove(2);

        Assertions.assertEquals(expected, list);
    }

    @Test
    void when_clear_list_then_list_is_empty() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        list.clear();

        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void when_iterator_has_next_then_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 2, 3);
        ListIterator<Integer> iterator = list.listIterator();
        int i = 0;
        while(iterator.hasNext()) {
            Assertions.assertEquals(expected.get(i++), iterator.next());
        }
    }

    @Test
    void when_iterator_has_previous_then_true() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        List<Integer> expected = List.of(1, 2, 3);
        ListIterator<Integer> iterator = list.listIterator(list.size());
        int i = expected.size()-1;
        while(iterator.hasPrevious()) {
            Assertions.assertEquals(expected.get(i--), iterator.previous());
        }
    }

    @Test
    void when_iterator_middle_iterate_forward_and_backward() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);

        ListIterator<Integer> iterator = list.listIterator(2);
        int value = iterator.next();
        Assertions.assertEquals(3, value);
        value = iterator.previous();
        Assertions.assertEquals(3, value);
    }
    @Test
    void when_last_index_of_element_return_index() {
        List<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(3);

        int idx = list.lastIndexOf(2);
        Assertions.assertEquals(1, idx);
    }

    @Test
    void when_add_string_list_then_list_is_sorted() {
        List<String> list = new SortedLinkedList<>();
        list.add("a");
        list.add("c");
        list.add("b");
        list.add("d");

        List<String> expected = List.of( "a", "b", "c", "d");
        Assertions.assertEquals(expected, list);
    }

    @Test
    void performance_add() {
        List<Integer> sortedLinkedList = new SortedLinkedList<>();
        long beginning = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            sortedLinkedList.add(i);
        }
        long end = System.nanoTime();
        System.out.println("Time for add: " + (end - beginning)/1_000_000 + " ms");
    }
}
