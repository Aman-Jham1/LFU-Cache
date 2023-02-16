import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        System.out.println("Cache taiyaar hia aapki");
 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the capacity:");
        int capacity = sc.nextInt();
 
        LFUCache cache = new LFUCache(capacity);
 
        // let's fucking do some ops
 
        cache.put(1, 1);
 
        cache.put(2, 2);
 
        System.out.println("cache.get(1) --- " + cache.get(1));
 
        cache.put(3, 3);
 
        System.out.println("cache.get(2) --- " + cache.get(2));
 
        System.out.println("cache.get(3) --- " + cache.get(3));
 
        cache.put(4, 4);
 
        System.out.println("cache.get(1) --- " + cache.get(1));
 
        System.out.println("cache.get(3) --- " + cache.get(3));
 
        System.out.println("cache.get(4) --- " + cache.get(4));
 
    }
 
}