import java.util.HashMap;
import java.util.Map;

public class CountWays {
    private static Map<Integer,Integer> cache = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(countWaysRecursive(3));
        System.out.println(countWaysRecursive(4));

        System.out.println(countWaysMemoized(3));
        System.out.println(countWaysMemoized(4));

        System.out.println(countWaysTabulated(3));
        System.out.println(countWaysTabulated(4));
    }

    private static int countWaysTabulated(int n) {
        int ways[] = new int[n+1];
        ways[0] = 1;
        for(int i=1; i<=n; i++) {
            if(i-1>=0)
                ways[i]+=ways[i-1];
            if(i-2>=0)
                ways[i]+=ways[i-2];
            if(i-3>=0)
                ways[i]+=ways[i-3];
        }
        return ways[n];
    }

    private static int countWaysMemoized(int n) {
        if(n==0) return 1;
        if(n<0) return 0;
        if(cache.containsKey(n))
            return cache.get(n);
        int ways = countWaysMemoized(n-1)+countWaysMemoized(n-2)+countWaysMemoized(n-3);
        cache.put(n,ways);
        return ways;
    }

    private static int countWaysRecursive(int n) {
        if(n==0) return 1;
        if(n<0) return 0;

        return countWaysRecursive(n-1)+countWaysRecursive(n-2)+countWaysRecursive(n-3);
    }


}
