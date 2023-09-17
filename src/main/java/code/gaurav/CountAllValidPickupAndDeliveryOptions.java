package code.gaurav;

//LeetCode 1359
public class CountAllValidPickupAndDeliveryOptions {
    public int countOrders(int n) {
        long ans = 1;
        int mod = 1000000007;
        for (int i = 2; i <= n; i++) {
            // i = 2 then 2*i-1 = 3 = 2*3;
            ans = (ans * (i * (2 * i - 1))) % mod;
        }
        return (int)ans;
    }
}
