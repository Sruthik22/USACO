// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class CandyBox {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int q = nextInt();

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        for (int i = 0; i < q; i++) {
            int n = nextInt();

            HashMap<Integer, Integer> candyNums = new HashMap<>();

            for (int j = 0; j < n; j++) {
                int candy = nextInt();

                if (candyNums.containsKey(candy)) {
                    candyNums.put(candy, candyNums.get(candy) + 1);
                }

                else {
                    candyNums.put(candy, 1);
                }
            }

            Map<Integer, Integer> candyNumsSorted = new LinkedHashMap<>();
            candyNums.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                    .forEachOrdered(x -> candyNumsSorted.put(x.getKey(), x.getValue()));


            int lowest = Integer.MAX_VALUE;

            int result = 0;

            for (Map.Entry<Integer, Integer> entry : candyNumsSorted.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();

                if (val < lowest) {
                    result += val;
                    lowest = val;
                }

                else {
                    val = Math.max(0, lowest -1);
                    lowest = val;
                    result += val;
                }
            }

            out.println(result);
    }

        out.close();
    }
}


