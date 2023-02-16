import java.util.HashMap;

public class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> keyToNode;
    private DLL list;

    LRUCache(int capacity) {
        this.capacity = capacity;
        keyToNode = new HashMap<>();
        list = new DLL();
    }
    
    int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        Node node = keyToNode.get(key);
        list.remove(node);
        list.add(node);
        return node.getValue();
    }
    
    void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.setValue(value);
            get(key);
            return;
        }
        if (keyToNode.size() == capacity) {
            int keyRemoved = list.remove();
            keyToNode.remove(keyRemoved);
        }
        Node node = new Node(key, value);
        keyToNode.put(key, node);
        list.add(node);
    }
};
