/*
ID: sruthi.2
LANG: JAVA
TASK: sort3
*/

import java.util.*;
import java.io.*;

public class sort3 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("sort3.in")));
        int n = nextInt();

        int[] sequence = new int[n];
        int num1s = 0;
        int num2s = 0;
        int num3s = 0;

        for (int i = 0; i < n; i++) {
            sequence[i] = nextInt();
            if (sequence[i] == 1) num1s++;
            else if (sequence[i] == 2) num2s++;
            else num3s++;
        }

        // now we need to find how many of the wrong number are in each boundary

        int num2in1 = 0;
        int num3in1 = 0;

        for (int i = 0; i < num1s; i++) {
            if (sequence[i] == 2) num2in1++;
            else if (sequence[i] == 3) num3in1++;
        }

        int num3in2 = 0;
        int num1in2 = 0;

        for (int i = num1s; i < num1s+num2s; i++) {
            if (sequence[i] == 1) num1in2++;
            else if (sequence[i] == 3) num3in2++;
        }

        int num2in3 = 0;
        int num1in3 = 0;

        for (int i = num1s+num2s; i < n; i++) {
            if (sequence[i] == 2) num2in3++;
            else if (sequence[i] == 1) num1in3++;
        }


        int result = 0;

        int minof12 = Math.min(num2in1, num1in2);

        result += minof12;

        num2in1 -= minof12;
        num1in2 -= minof12;

        int minof13 = Math.min(num1in3, num3in1);

        result += minof13;

        num1in3 -= minof13;
        num3in1 -= minof13;

        int minof23 = Math.min(num2in3, num3in2);

        result += minof23;

        num2in3 -= minof23;
        num3in2 -= minof23;

        int remain = num2in1 + num1in2 + num1in3 + num3in1 + num2in3 + num3in2;

        result += remain / 3 * 2;


        PrintWriter out = new PrintWriter(new File("sort3.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


