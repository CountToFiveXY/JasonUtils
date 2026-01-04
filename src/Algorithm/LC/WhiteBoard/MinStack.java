package Algorithm.LC.WhiteBoard;

import java.util.*;

/*
LeetCode 155: Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

1. MinStack()
   - Initializes the stack object.

2. void push(int val)
   - Pushes the element val onto the stack.

3. void pop()
   - Removes the element on the top of the stack.

4. int top()
   - Gets the top element of the stack.

5. int getMin()
   - Retrieves the minimum element in the stack.

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // returns -3
minStack.pop();
minStack.top();    // returns 0
minStack.getMin(); // returns -2
*/

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> min;

    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (min.isEmpty() || min.peek() >= val) {
            min.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if  (min.peek() == val) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println(minStack.getMin()); // -1024
        minStack.pop();
        System.out.println(minStack.getMin()); // -1024
        minStack.pop();
        System.out.println(minStack.getMin()); // 512
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */