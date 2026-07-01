package Algorithm.Company.Remitly;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRU<K,V> {

    final ReentrantLock lock = new ReentrantLock();
    final int TTL = 5;

    class Node {
        K key;
        V value;
        int expireTime;
        Node next;
        Node prev;
        Node(K key, V value, int expire) {
            this.key = key;
            this.value = value;
            this.expireTime = expire;
        }
    }

    Node start;
    Node end;
    int capacity;

    Map<K, Node> map;

    public LRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        start = new Node(null, null, Integer.MAX_VALUE);
        end = new Node(null, null, Integer.MAX_VALUE);
        start.next = end;
        end.prev = start;
    }


    public V get(K key, int timestamp) {
        lock.lock();
        try {
            if (!map.containsKey(key)) {
                return null;
            }

            Node res = map.get(key);
            detachFromList(res);

            if (res.expireTime < timestamp) {
                map.remove(res.key);
                return null;
            }

            moveAfterStart(res);
            return res.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value, int timestamp) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                node.expireTime = timestamp + TTL;
                detachFromList(node);
                moveAfterStart(node);
                return;
            }

            if (map.size() == capacity) {
                Node lru = end.prev;
                map.remove(lru.key);
                detachFromList(lru);
            }

            Node node = new Node(key, value, timestamp + TTL);
            map.put(key, node);
            moveAfterStart(node);
        } finally {
            lock.unlock();
        }

    }


    private void detachFromList(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void moveAfterStart(Node node) {
        node.next = start.next;
        start.next.prev = node;
        start.next = node;
        node.prev = start;
    }

}
