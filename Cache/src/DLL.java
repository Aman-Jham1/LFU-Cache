public class DLL {
    private int size;
    private Node head;
    private Node tail;

    public DLL() {
        this.size = 0;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.tail.setPrev(this.head);
        this.head.setNext(this.tail);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }


    public int remove(Node node) {
        System.out.println("Removing: " + node.getKey());
        int removed = node.getKey();
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
        this.size--;
        return removed;
    }

    public int remove() {
        return this.remove(this.tail.getPrev());
    }

    public boolean add(Node node) {
        //System.out.println("Adding");
        node.setNext(this.head.getNext());
        this.head.getNext().setPrev(node);
        node.setPrev(this.head);
        this.head.setNext(node);
        this.size++;
        return true;
    }

    public void printDLL() {
        Node temp = this.head;
        while (temp != null) {
            System.out.print("(" + temp.getKey() + ")" + "----->");
            temp = temp.getNext();
        }
        System.out.println();
    }

}