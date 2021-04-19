import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyQueue<T> {
    /*
        Queue implementation using Java's LinkedList class
     */
    private List<T> queue = new LinkedList<T>();

    public boolean isEmpty() {
        return size()==0;
    }
    public int size() {
        return queue.size();
    }
    public void queue(T data) {
        /* queue at end */
        queue.add(data);
    }
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        /* dequeue from front */
        return queue.remove(0);
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.queue(1); queue.queue(2);
        queue.queue(3); queue.queue(4);
        while(!queue.isEmpty()) {
            System.out.print(queue.dequeue()+ " ");
        }
    }
}
