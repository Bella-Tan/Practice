package lc1350;

public class Solution {

    public static String convertToExcTitle(int n) {
        if (n == 0) return "";
        return convertToExcTitle((n - 1) / 26) + (char)((n - 1) % 26 + 'A');
    }

    public static void main(String[] args) {
        for (int i = 5000; i < 5030; i++) {
            System.out.println(convertToExcTitle(i));
        }
    }
}
