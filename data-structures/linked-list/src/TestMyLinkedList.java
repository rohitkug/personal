public class TestMyLinkedList {
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.addFirst(0);
        list.add(1,4);

        System.out.println(list.toString() + " { size = " + list.size()+" }");
        System.out.println(list.removeFirst());
        System.out.println(list.toString() + " { size = " + list.size()+" }");
        System.out.println(list.removeLast());
        System.out.println(list.toString() + " { size = " + list.size()+" }");
        System.out.println(list.removeAt(2));
        System.out.println(list.toString() + " { size = " + list.size()+" }");
    }
}
