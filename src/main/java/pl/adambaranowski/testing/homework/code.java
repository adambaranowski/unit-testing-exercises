package pl.adambaranowski.testing.homework;

import java.util.Arrays;
import java.util.Collections;

public class code {
    public static int solution(int n){
        char[] numInChars = ("" + n).toCharArray();
        Arrays.sort(numInChars);
        StringBuilder sb = new StringBuilder();
        for (char c: numInChars
             ) {
            sb.append(c);
        }

        sb.reverse();
        return Integer.parseInt(sb.toString());
    }
    public static void main(String[] args) {

        System.out.println(solution(000001));
    }
}
