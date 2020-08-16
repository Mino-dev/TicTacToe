import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();
        
        Map<Long, Integer> map = new HashMap<>();
        for (long i = a; i <= b; ++i) {
            Random random = new Random(i);
            int maxInner = Integer.MIN_VALUE;
            
            for (int j = 0; j < n; ++j) {
                maxInner = Math.max(maxInner, random.nextInt(k));    
            }
            map.put(i, maxInner);
        }
        
        long keyMin = Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println(keyMin);
        System.out.println(map.get(keyMin));
    }
}
