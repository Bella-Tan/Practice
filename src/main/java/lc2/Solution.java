package lc2;

public class Solution {

    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length();
        int n = num2.length();
        int[] ansArray = new int[m + n];
        for(int i=m-1; i>=0; i--){
            int x = num1.charAt(i) - '0';
            for(int j = n-1; j>=0; j--){
                int y = num2.charAt(j) - '0';
                ansArray[j+i+1] += x * y;
            }
        }
        for(int k = m+n-1; k > 0; k--){
            ansArray[k-1] += ansArray[k] /10;
            ansArray[k] = ansArray[k] % 10;
        }
        StringBuffer result = new StringBuffer();
        int index = ansArray[0] == 0 ? 1:0;
        for( int i = index; i<m+n;i++){
            result.append(ansArray[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("999", "999"));
    }

}
