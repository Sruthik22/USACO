// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class AppleDivision {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();

        int[] arr = new int[n];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();

            sum += arr[i];
        }

        long result = Integer.MAX_VALUE;

        for(int i = 0; i < (1<<n); i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < n; j++){
                if((i & (1 << j)) > 0){
                    list.add(arr[j]);
                }
            }

            long subset1 = 0;

            for (int list_elem : list) {
                subset1 += list_elem;
            }

            long other = sum - subset1;

            result = Math.min(Math.abs(subset1 - other), result);
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        out.print(result);

        out.close();
    }
}




