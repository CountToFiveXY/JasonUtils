package Algorithm.LC.Stack_Queue.P716;

import java.util.*;

/**
 * LeetCode 716: Max Stack
 *
 * Design a max stack that supports push, pop, top, peekMax, and popMax.
 *
 * Implement the MaxStack class:
 * - MaxStack() Initializes the stack object.
 * - void push(int x) Pushes element x onto the stack.
 * - int pop() Removes the element on top of the stack and returns it.
 * - int top() Gets the top element of the stack.
 * - int peekMax() Retrieves the maximum element in the stack.
 * - int popMax() Retrieves the maximum element in the stack and removes it.
 *   If there is more than one maximum element, remove the top-most one.
 *
 * You must implement each operation in O(1) or O(log n) time on average.
 */
public class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> max;

    // Constructor
    public MaxStack() {
        // TODO: initialize your data structures
        stack = new Stack<>();
        max = new Stack<>();
    }

    public void push(int x) {
        // TODO: implement push
        stack.push(x);
        if(max.isEmpty() || max.peek() <= x) {
            max.push(x);
        }
    }

    public int pop() {
        // TODO: implement pop
        int val = stack.pop();
        if (val == max.peek()) {
            max.pop();
        }
        return val;
    }

    public int top() {
        // TODO: implement top
        return stack.peek();
    }

    public int peekMax() {
        // TODO: implement peekMax
        return max.peek();
    }

    public int popMax() {

        Stack<Integer> buffer = new Stack<>();

        while (peekMax() != stack.peek()) {
            buffer.push(stack.pop());
        }

        stack.pop();

        while(!buffer.isEmpty()) {
            stack.push(buffer.pop());
        }

        // TODO: implement popMax
        return max.pop();
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        // Example test calls
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top());      // 5
        System.out.println(stack.popMax());   // 5
        System.out.println(stack.top());      // 1
        System.out.println(stack.peekMax());  // 5
        System.out.println(stack.pop());      // 1
        System.out.println(stack.top());      // 5
    }
}
