public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head=tail=null;
        size=0;
    }

    public void add(T data) {
        addLast(data);
    }
    public void add(int index, T data) throws Exception {
        if(index<0 || index>size) throw new Exception("Invalid index value");
        if(index==0) {
            addFirst(data);
            return;
        }
        if(index==size) {
            addLast(data);
            return;
        }
        Node<T> tmp = head;
        for(int i=0;i<index-1;i++) {
            tmp=tmp.next;
        }
        Node<T> node = new Node<T>(data);
        node.next = tmp.next;
        tmp.next = node;
        size++;
    }
    public void addLast(T data) {
        if(isEmpty()) {
            head=tail=new Node<T>(data,null);
        } else {
            tail.next = new Node<T>(data,null);
            tail = tail.next;
        }
        size++;
    }
    public void addFirst(T data) {
        if(isEmpty()) {
            head=tail=new Node<T>(data,null);
        } else {
            Node node = new Node<T>(data);
            node.next = head;
            head = node;
        }
        size++;
    }
    public T removeAt(int index) throws Exception {
        if(isEmpty()) throw new Exception("cannot remove from empty list");
        if(index==0) return removeFirst();
        if(index==size-1) return removeLast();
        if(index>=size) throw new Exception("Invalid index");
        Node<T> prev = head;
        for(int i=0;i<index-1;i++) {
            prev=prev.next;
        }
        Node<T> tmp = prev.next;
        prev.next = tmp.next;
        tmp.next = null;
        T data = tmp.data;
        tmp.data=null;
        size--;
        return data;
    }

    public T removeLast() throws Exception {
        if(isEmpty()) throw new Exception("cannot remove from empty list");
        Node<T> tmp = head;
        for(int i=0; i<size-2;i++) {
            tmp=tmp.next;
        }
        T data = tail.data;
        tail.data=null;
        tail.next=null;

        tmp.next=null;
        tail=tmp;
        size--;
        return data;
    }
    public T removeFirst() throws Exception {
        if(isEmpty()) throw new Exception("cannot remove from empty list");
        Node<T> tmp = head;
        T data = tmp.data;
        head = head.next;
        tmp.next = null;
        tmp.data = null;
        size--;
        return data;
    }
    public boolean isEmpty() {
        return size()<=0;
    }

    public int size() {
        return this.size;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> tmp = head;
        while(tmp!=null) {
            sb.append(tmp.data.toString()+" ");
            tmp=tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }
    private static class Node<T> {
        private T data;
        private Node<T> next;
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}