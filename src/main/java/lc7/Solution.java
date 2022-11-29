package lc7;

public class Solution {

    public static int reverseUint(int x) {
        int result = 0;
        while(x != 0){
            int odd = x % 10;
            if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && odd >7))
                return 0;
            if(result < Integer.MIN_VALUE/10 || result == Integer.MIN_VALUE/10 && odd < -8)
                return 0;
            result = result * 10 + odd;
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args){
        for(int i =10000; i< 20000;i++){
            System.out.print(reverseUint(i));
            System.out.print(" ");
        }
    }
}
