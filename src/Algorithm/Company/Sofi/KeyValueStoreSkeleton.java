package Algorithm.Company.Sofi;

import java.util.*;

/**
 * ============================================================
 * Problem: Key-Value Store with getLast()
 * ============================================================
 *
 * Design an in-memory key-value store that supports the following operations:
 *
 *   - add(key, value): insert or update a key-value pair
 *   - get(key): retrieve the value for a key
 *       * IMPORTANT: calling get(key) must NOT affect "last"
 *   - remove(key): remove a key-value pair
 *   - getLast(): return the most recently added key-value pair
 *
 * Notes:
 *   - "last" refers to the most recent ADD operation,
 *     NOT the most recent access (this is NOT an LRU cache).
 *   - All operations should run in O(1) time.
 *
 * ============================================================
 * Expected Data Structure (Single Machine):
 *
 * ============================================================
 * Follow-up: Multiple Servers
 *
 *   - Keys are sharded across multiple servers.
 *   - Each server maintains its own local key-value store.
 *   - Challenge: how to define a GLOBAL getLast()?
 *
 * Expected discussion (not implemented here):
 *   - Global metadata store (Redis / DB / Zookeeper)
 *   - Monotonic timestamp or logical clock
 *   - getLast() requires routing to the correct server
 *
 * ============================================================
 */
public class KeyValueStoreSkeleton {

    /**
     * Node for doubly linked list.
     * Stores key-value pair and pointers.
     */
    static class Node {
        String value;
        Node next;
        Node prev;

        Node(String value) {
            this.value = value;
        }
    }

    // ------------------------------------------------------------
    // Core Data Structures
    // ------------------------------------------------------------

    // Map key -> Node
    private Map<String, Node> map;

    // Dummy head and tail for doubly linked list
    private Node head;
    private Node tail;

    public KeyValueStoreSkeleton() {
        // TODO: initialize map, head, tail
        head = new Node("head");
        tail = new Node("tail");
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    /**
     * Add or update a key-value pair.
     * This operation SHOULD update "last".
     */
    public void add(String key, String value) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            removeNode(node);
        } else {
            node = new Node(value);
            map.put(key, node);
        }
        addAfterHead(node);
    }

    /**
     * Get value by key.
     * This operation MUST NOT affect "last".
     */
    public String get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        return map.get(key).value;
    }

    /**
     * Remove a key-value pair.
     */
    public void remove(String key) {
        if (!map.containsKey(key)) {
            return;
        }
        Node node = map.get(key);
        removeNode(node);
        map.remove(key);
    }

    /**
     * Get the most recently added key-value pair.
     */
    public String getLast() {
        return head.next == tail? null : head.next.value;
    }

    private void addAfterHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // ============================================================
    // Test Cases (NO solution logic used here)
    // ============================================================
    public static void main(String[] args) {

        KeyValueStoreSkeleton store = new KeyValueStoreSkeleton();

        store.add("k1", "v1");
        store.add("k2", "v2");
        store.add("k3", "v3");

        // Expected: v3
        System.out.println("Last: " + store.getLast());

        // Expected: v1
        System.out.println("Get k1: " + store.get("k1"));

        store.remove("k3");

        // Expected: v2
        System.out.println("Last after remove: " + store.getLast());

        store.add("k4", "v4");

        // Expected: v4
        System.out.println("Last after add: " + store.getLast());
    }
}
