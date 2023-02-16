import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

class LFUCache {

    private int capacity;
    private int removeFrom;

    // private TreeSet<Integer> freqs;
    private HashMap<Integer, Node> keyToNode;

    private HashMap<Integer, DLL> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.removeFrom = 1;
        // this.freqs = new TreeSet<>();
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
            Node node = keyToNode.get(key);
            int cnt = node.getCount();

            // remove from cnt

            freqToList.get(cnt).remove(node);

            node.setValue(value);
            node.setCount(node.getCount() + 1);
            if (freqToList.get(cnt + 1) == null) {
                freqToList.put(cnt + 1, new DLL());
            }

            freqToList.get(cnt + 1).add(node);
        } else {
            // deal with eviction here
            if (keyToNode.size() < capacity) {
                Node node = new Node(key, value);
                keyToNode.put(key, node);
                if (freqToList.get(1) == null) {
                    freqToList.put(1, new DLL());
                }
                freqToList.get(1).add(node);
                // System.out.println(freqToList.get(1).getSize());
                // freqToList.get(1).printDLL();
            } else {
                // remove tail from least frequency list
                // get to know which is the smallest frequency.

                // lets brute force this for now
                int remove = -1;
                for (int i = 1; i <= 100; ++i) {
                    if (freqToList.get(i).getSize() > 0) {
                        remove = i;
                        break;
                    }
                }
                //
                // System.out.print("Removing from: ");
                // System.out.println(remove);
                // freqToList.get(remove).printDLL();
                int keyRemoved = freqToList.get(remove).remove();

                // add this to new

                Node node = new Node(key, value);
                keyToNode.put(key, node);
                freqToList.get(1).add(node);

                keyToNode.remove(keyRemoved);
            }
        }
    }
}