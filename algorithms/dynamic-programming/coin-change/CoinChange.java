import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChange {
    private static Map<String,Integer> cache = new HashMap<>();
    public static void main(String[] args) {
        /*
        Expected o/p :
            4
            5
            4
            5
            5
            1
            15685693751
         */
        System.out.println(coinChangeRecursive(4, Arrays.asList(1,2,3)));
        System.out.println(coinChangeRecursive(10, Arrays.asList(2,3,5,6)));

        System.out.println(coinChangeMemoized(4, Arrays.asList(1,2,3)));
        System.out.println(coinChangeMemoized(10, Arrays.asList(2,3,5,6)));

        System.out.println(coinChangeTabulated(4, Arrays.asList(1,2,3)));
        System.out.println(coinChangeTabulated(10, Arrays.asList(2,3,5,6)));

        System.out.println(coinChangeTabulated(10, Arrays.asList(2,5,3,6)));
        System.out.println(coinChangeTabulated(2, Arrays.asList(2,3)));
        System.out.println(coinChangeTabulated(250, Arrays.asList(
                41,34,46,9,37,32,42,21,7,13,1,24,3,43,2,23,8,45,19,30,29,18,35,11)));
    }
    private static long coinChangeTabulated(int n, List<Integer> coins) {
        long[][] ways = new long[1+coins.size()][n+1];
        for(int i=0; i<=n; i++)
            ways[0][i] = 0;
        for(int i=0; i<=coins.size(); i++)
            ways[i][0] = 1;
        for(int coin=1; coin<=coins.size(); coin++) {
            for(int amount=1; amount<=n; amount++) {
                if(amount-coins.get(coin-1)>=0) {
                    ways[coin][amount]+= ways[coin][amount-coins.get(coin-1)];
                }
                ways[coin][amount]+=ways[coin-1][amount];
            }
        }
        return ways[coins.size()][n];
    }
    private static int coinChangeMemoized(int n, List<Integer> coins) {
        if(n==0) return 1;
        if(n<0) return 0;
        if(n>0 && coins.isEmpty()) return 0;
        if(cache.containsKey(""+n+coins)) return cache.get(""+n+coins);
        int ways = 0;
        ways =  coinChangeMemoized(n-coins.get(0), coins) +
                coinChangeMemoized(n, coins.subList(1, coins.size()));
        cache.put(""+n+coins,ways);
        return ways;
    }
    public static int coinChangeRecursive(int n, List<Integer> coins) {
        if(n==0) return 1;
        if(n<0) return 0;
        if(n>0 && coins.isEmpty()) return 0;
        int ways = 0;
        ways =  coinChangeRecursive(n-coins.get(0), coins) +
                coinChangeRecursive(n, coins.subList(1, coins.size()));
        return ways;
    }
}