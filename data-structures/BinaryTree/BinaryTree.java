import java.util.*;

public class BinaryTree<T> {
    private Node<T> root;
    private int size;

    public void add(T data) {
        Node<T> node = new Node<>(data);
        if(root==null) {
            root=node;
            root.parent=root;//root is its own parent
            size++;
            return;
        }
        //find parent node where insertion will happen
        Node<T> parent = findNextInsertionPosition(root);
        //no need for null check as we'll always find a position to insert
        if(parent.left==null)//insert on left first
            parent.left=node;
        else parent.right=node;
        node.parent=parent;
        size++;
    }
    public boolean remove(T data) throws Exception {
        /*
            removal only supported for leaf nodes in this example
            assumes there are no duplicates in tree
         */
        Node<T> node = findNode(data);
        if(node==null)
            throw new NoSuchElementException();
        if(node.left!=null || node.right!=null)
            throw new Exception("Deletion of non-leaf nodes not supported");
        if(node==root) {
            root=null;
            root.parent=null;
            root.data=null;
            size--;
            node=null;
            return true;
        }
        if(node.parent.left==node)
            node.parent.left=null;
        else
            node.parent.right=null;
        node.parent=null;
        node.data=null;
        node=null;
        size--;
        return true;
    }

    private Node<T> findNode(T data) {
        if(root==null)
            return null;
        Queue<Node<T>> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node<T> tmp = q.remove();
            if(tmp.data.equals(data))
                return tmp;
            if(tmp.left!=null) q.add(tmp.left);
            if(tmp.right!=null) q.add(tmp.right);
        }
        return null;
    }

    private Node<T> findNextInsertionPosition(Node<T> root) {
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        q.add(root);
        while(!q.isEmpty()) {
            Node<T> tmp = q.remove();
            if(tmp.left==null || tmp.right==null)
                return tmp;
            if(tmp.left!=null)  q.add(tmp.left); //will always be true
            if(tmp.right!=null) q.add(tmp.right);//will always be true
        }
        return null;
    }
    private List<T> getLeftView() {
        if(root==null) return Collections.emptyList();
        List<T> ret = new ArrayList<>();
        Queue<Node<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<T> tmp = q.remove();
            ret.add(tmp.data);
            if(tmp.left!=null)
                q.add(tmp.left);
            else if(tmp.right!=null)
                q.add(tmp.right);
        }
        return ret;
    }
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        Node(T data) {
            this.data=data;
            this.left=this.right=this.parent=null;
        }
        /*Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
            this.data = data;
            this.left = left;
            this.right=right;
            this.parent=parent;
        }*/
    }
    public static void main(String[] args) throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1); tree.add(2); tree.add(3); tree.add(4);
        tree.add(5); tree.remove(4);
        List<Integer> leftView = tree.getLeftView();
        System.out.println("Size="+tree.size());
        System.out.println("Left View = "+leftView);
    }
    private int size() {
        return this.size;
    }
}
