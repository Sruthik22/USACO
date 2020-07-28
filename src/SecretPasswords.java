// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SecretPasswords {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        parent = new int[26];
        visited = new boolean[26];

        initialize(26);

        for (int i = 0; i < n; i++) {
            String s = next();

            for (int j = 0; j < s.length()-1; j++) {
                int c1 = s.charAt(j) - 'a';
                int c2 = s.charAt(j+1) - 'a';

                visited[c1] = true;

                union(c1, c2);
            }

            visited[s.charAt(s.length()-1) - 'a'] = true;
        }

        // now need to find the number of disjoint sets

        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < 26; i++) {
            if (!visited[i]) continue;
            hashSet.add(find(parent[i]));
        }

        out.println(hashSet.size());

        out.close();
    }

    static int[] parent; //stores the root of each set
    static boolean[] visited;

    static void initialize(int N) {
        for (int i = 0; i < N; i++) {
            parent[i] = i; //initially, the root of each set is the node itself }
        }
    }

    static int find(int x){
        if(x == parent[x]) {
            return x;
        }

        else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int a, int b) { //merges the sets of a and b
        int c = find(a); //find the root of a
        int d = find(b); //find the root of b
        if (c != d) {
            parent[d] = c; //merge the sets by setting the parent of d to c }
        }
    }
}


