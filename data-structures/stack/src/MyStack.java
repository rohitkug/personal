import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {
    /*
        stack implementation using Java's LinkedList
     */
    private List<T> stack = new LinkedList<T>();
    //private int size = 0;

    public void push(T data) {
        /* add at first position */
        stack.add(0, data);
    }

    public T pop() {
        /* remove from first position */
        if(isEmpty()) throw new EmptyStackException();
        return stack.remove(0);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return stack.get(0);
    }

    public static void main(String[] args) {
        MyStack<Integer> s1 = new MyStack<>();
        s1.push(1); s1.push(2); s1.push(3); s1.push(4);
        while(!s1.isEmpty()) {
            System.out.print(s1.pop()+ " ");
        }
    }
}