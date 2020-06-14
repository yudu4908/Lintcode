package Kwai;
/**
 * Given a positive integer N, you need to factorize all integers between (1, N].
Then you have to count the number of the total prime numbers.
1<N<=100000
 */
/**
 * input：6
output：7
explain：2=2, 3=3, 4=2*2, 5=5, 6=2*3, the number of prime number : 1+1+2+1+2=7
 */
public class PrimeFactorStatisticsSolution {
    /**
     * @param N: a number
     * @return: the number of prime numbers
     */
    public int Count_PrimeNum(int N) {
        int ans = 0;
        boolean[] vis = new boolean[100001]; //visited
        int[] prime = new int[100001];
        for (int i = 2; i <= N; i++) {
            prime[i] = 1;
            vis[i] = false;
        }
        for (int i = 2; i <= N; i++) {
            ans += prime[i];
            for (int j = 2; i * j <= N && j <= i; j++) {
                if (vis[i * j]) {
                    continue;
                }
                vis[i * j] = true;
                prime[i * j] = prime[i] + prime[j];
            } 
        }
        return ans;
    }
}