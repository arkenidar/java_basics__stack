package com.example;

class StackApp {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(2);
        try {
            stack.push(1);
            stack.push(2);
            stack.push(3);
        } catch (ArrayIndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }
        System.out.println("popped:" + stack.pop());
        System.out.println("popped:" + stack.pop());
        System.out.println("popped:" + stack.pop());
        System.out.println("popped:" + stack.pop());
    }
}

class Stack<T> {
    private T[] array;
    private int count;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        array = (T[]) new Object[size];
        count = 0;
    }

    public void push(T item) {
        if (count >= array.length)
            throw new ArrayIndexOutOfBoundsException("full stack, then 'push' op fails!");
        array[count++] = item;
        // count++;
    }

    public T pop() throws ArrayIndexOutOfBoundsException {
        if (count <= 0)
            throw new ArrayIndexOutOfBoundsException("empty stack, then 'pop' op fails!");
        // --count;
        return array[--count];
    }
}
