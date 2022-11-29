package lc13;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int romanToInt(String s) {
        int ans = 0;
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        for(int i=0; i< s.length(); i++){
            if(i+1 < s.length() && s.charAt(i) < s.charAt(i+1))
                ans -= symbolValues.get(s.charAt(i));
            else
                ans += symbolValues.get(s.charAt(i));
        }
        return ans;
    }
}
