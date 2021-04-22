import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyPriorityQueue<T extends Comparable<T>> {
    private List<T> heap;

    public MyPriorityQueue() {
        this.heap = new ArrayList<>(7);
    }
    public boolean isEmpty() {
        return size()==0;
    }
    public int size() {
        return heap.size();
    }
    public void add(T elem) {
        //add at last position
        heap.add(elem);
        //now we need to swim it up if required
        int index = heap.size()-1;
        swim(index);
    }
    public T remove() {
        return removeAt(0);
    }
    private T removeAt(int i) {
        if(isEmpty() || i>=size()) throw new NoSuchElementException();
        T ret = heap.get(i);
        int lastIndex = size()-1;
        swap(i,lastIndex);
        heap.remove(lastIndex);
        if(i==lastIndex) return ret;
        T tmp = heap.get(i);
        sink(i);
        if(heap.get(i).equals(tmp)) swim(i);
        return ret;
    }
    private void swim(int i) {
        int parent = (i-1)/2;
        while(i>0 && less(i,parent)) {
            swap(i,parent);
            i=parent;
            parent=(i-1)/2;
        }
    }
    private void sink(int i) {
        while(true) {
            if(i>=size()-1)
                return; //stay within tree
            int left  = 2*i+1;
            int right = 2*i+2;
            if(left>=size()-1 && right>=size()-1)
                return;//we are at a root node
            int smallest = left; //start at left since we maintain full tree as far as possible
            if(right<size()-1)
                smallest=less(right,left)?right:smallest;
            if(!less(i,smallest)) {
                //heap invariant is not maintained so swap and recur
                swap(smallest, i);
                sink(smallest);
            }
            return;
        }
    }
    /*
    swaps element at position i and j
     */
    private void swap(int i, int j) {
        if(i==j) return;
        T tmp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,tmp);
    }

    /*
    return true if i <= j, else false
     */
    private boolean less(int i, int j) {
        return heap.get(i).compareTo(heap.get(j))<=0?true:false;
    }

    public static void main(String[] args) {
        MyPriorityQueue<Integer> heap = new MyPriorityQueue<>();
        for(int i=10; i>=1;i--)
            heap.add(ThreadLocalRandom.current().nextInt(1,101));
        for(int i=1;i<=10;i++)
            System.out.print(heap.remove()+" ");
    }
}