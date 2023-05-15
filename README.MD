### Sorted Linked List
1) Implementation of a sorted linked list in Java. Implements the List<E> interface with some missing functionality. 
2) Time complexity O(n) for add, remove, contains, O(1) for size, isEmpty, iterator. 
3) It is not thread safe.
4) It is implemented using a doubly linked list.


### Build
```bash
mvn clean install
```

### Test
```bash
mvn test
```

### Improvements
- If the number of add are more than the number of access then we can add the elements in the list not in order and then 
  sort the list when the iterator is called and use mergeSort to sort the list. That will require O(nlogn) time complexity
  to access the elements but O(1) time complexity to add the elements.
- A heuristic to improve the performance of the contains method is to keep track of the last accessed element and start
  the search from there. If the element is not found then start the search from the beginning. This will improve the 
  performance of the contains method if the elements are accessed in order.
- We can keep track of the middle element of the list and start the search from there. If the element is not found then
  we can start the search from the beginning or the end depending on the value of the element. Time complexity will be
  the same but the number of comparisons will be less.