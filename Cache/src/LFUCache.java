import java.util.HashMap;

public class LFUCache {

    private int capacity;
    private int minf;

    private HashMap<Integer, Node> keyToNode;

    private HashMap<Integer, DLL> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minf = 0;
        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }

        Node node = keyToNode.get(key);
        int cnt = node.getCount();
        int value = node.getValue();

        // remove from cnt
        freqToList.get(cnt).remove(node);
        if (freqToList.get(cnt).getSize() == 0) {
            if (minf == cnt) {
                minf++;
            }
        }

        // add in cnt + 1
        node.setCount(node.getCount() + 1);
        if (freqToList.get(cnt + 1) == null) {
            freqToList.put(cnt + 1, new DLL());
        }
        freqToList.get(cnt + 1).add(node);
        return value;
    }

    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            keyToNode.get(key).setValue(value);
            this.get(key);
        } else {
            // deal with eviction here
            if (keyToNode.size() < capacity) {
                Node node = new Node(key, value);
                keyToNode.put(key, node);
                if (freqToList.get(1) == null) {
                    freqToList.put(1, new DLL());
                }
                freqToList.get(1).add(node);
            } else {
                // remove tail from least frequency list
                // get to know which is the smallest frequency.
                int keyRemoved = freqToList.get(minf).remove();

                // add this to new
                Node node = new Node(key, value);
                keyToNode.put(key, node);
                freqToList.get(1).add(node);
                keyToNode.remove(keyRemoved);
            }
            minf = 1;
        }
    }
}
