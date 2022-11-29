package lc3;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int max = 0;
        int end = 0;
        for(;end < s.length();end ++){
            char c = s.charAt(end);
            while(set.contains(c)){
                set.remove(s.charAt(left));
                left ++;
            }
            set.add(c);
            max = Math.max(max, end - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("faeqrqrtrqwerqwrqwrqwrgbfb999"));
    }

}
