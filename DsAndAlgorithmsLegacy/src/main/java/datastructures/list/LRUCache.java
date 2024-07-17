package datastructures.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the
 * following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache
 * reached its capacity, it should invalidate the least recently used item before inserting a new
 * item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Algorithm:
 * Solution 1:
 * Using ordered hash map[Linked hash map]
 * <p>
 * Solution 2:
 * Using a Map and a doubly linked list, keep on updating the map and list based on the new element
 * <p>
 * One particularity about the double linked list implemented here is that there are pseudo head and
 * pseudo tail to mark the boundary, so that we don't need to check the null node during the
 * update.
 * <p>
 * <p>
 * Complexity:
 * Time - O(1)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/lru-cache/solution/
 */
public class LRUCache {

  //Solution 2
  private final int capacity;
  private int size;
  private final Map<Integer, DLinkedNode> map = new HashMap<>();
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    head = new DLinkedNode();
    tail = new DLinkedNode();

    head.next = tail;
    tail.prev = head;
  }


  public int get(int key) {
    DLinkedNode val = map.get(key);
    if (val == null) {
      return -1;
    }

    moveToHead(val);
    return val.value;
  }


  public void put(int key, int value) {

    DLinkedNode existingNode = map.get(key);

    if (existingNode == null) {
      DLinkedNode newNode = new DLinkedNode(key, value);
      addToHead(newNode);
      map.put(key, newNode);
      size++;

      //If we have reached the full capacity remove the least used element from the tail
      if (size > capacity) {
        DLinkedNode leastUsed = removeTail();
        map.remove(leastUsed.key);
        size--;
      }
    } else {
      existingNode.value = value;
      moveToHead(existingNode);
    }
  }

  //--------------------------------------------//

  public void moveToHead(DLinkedNode node) {
    removeNode(node);
    addToHead(node);
  }


  public void removeNode(DLinkedNode node) {
    DLinkedNode nodePrev = node.prev;
    DLinkedNode nodeNext = node.next;

    nodePrev.next = nodeNext;
    nodeNext.prev = nodePrev;
  }


  //Always insert at the beginning of the list
  public void addToHead(DLinkedNode newNode) {
    newNode.prev = head;
    newNode.next = head.next;

    head.next.prev = newNode;
    head.next = newNode;
  }


  public DLinkedNode removeTail() {
    DLinkedNode prev = tail.prev;
    tail.prev = prev.prev;
    return prev;
  }


  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));       // returns 1
    cache.put(3, 3);                        // evicts key 2
    System.out.println(cache.get(2));       // returns -1 (not found)
    cache.put(4, 4);                        // evicts key 1
    System.out.println(cache.get(1));       // returns -1 (not found)
    System.out.println(cache.get(3));       // returns 3
    cache.get(4);

  }

  static class DLinkedNode {

    private int key;
    private int value;
    private DLinkedNode prev;
    private DLinkedNode next;

    public DLinkedNode() {
    }

    public DLinkedNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  //------------------------------------------------------//

  //Solution 1 Using Linked hashmap

  /* extends LinkedHashMap<Integer, Integer> {

  private final int capacity;

  //Access order is imp. as by default LinkedHashMap ordered by insertion order, if true the access ordering is set
  public LRUCache(int capacity) {
    super(capacity, 0.75F, true);
    this.capacity = capacity;
  }


  public int get(int key) {
    return super.getOrDefault(key, -1);
  }

  public void put(int key, int val) {
    super.put(key, val);
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
    //If size is greater than the initial capacity remove the eldest entry
    return size() > capacity;
  }
*/
}
