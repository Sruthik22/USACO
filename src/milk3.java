/*
ID: sruthi.2
LANG: JAVA
TASK: milk3
*/

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class milk3 {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int aCapacity;
    static int bCapacity;
    static int cCapacity;

    static boolean[][][] visited;
    static TreeSet<Integer> milkAmounts;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("milk3.in")));

        aCapacity = nextInt();
        bCapacity = nextInt();
        cCapacity = nextInt();

        visited = new boolean[21][21][21];
        milkAmounts = new TreeSet<>();
        bfs(0,0, cCapacity);

        PrintWriter out = new PrintWriter(new File("milk3.out"));

        for (int i : milkAmounts) {
            if (i == milkAmounts.last()) {
                System.out.println(i);
                out.println(i);
            }
            else {
                System.out.print(i + " ");
                out.print(i + " ");
            }
        }
        out.close();
    }

    public static void bfs (int a, int b, int c) {
        // still need to go to other viable options before done

        if (visited[a][b][c]) return;
        visited[a][b][c] = true;

        int aMoveB = a - (bCapacity - b);
        if (aMoveB < 0) bfs(0, a + b, c);
        else bfs(aMoveB, bCapacity, c);

        int aMoveC = a - (cCapacity - c);
        if (aMoveC < 0) bfs(0, b, a + c);
        else bfs(aMoveC, b, cCapacity);

        int bMoveA = b - (aCapacity - a);
        if (bMoveA < 0) bfs(b + a, 0, c);
        else bfs(aCapacity, bMoveA, c);

        int bMoveC = b - (cCapacity - c);
        if (bMoveC < 0) bfs(a, 0, b + c);
        else bfs(a, bMoveC, cCapacity);

        int cMoveA = c - (aCapacity - a);
        if (cMoveA < 0) bfs(c+a, b, 0);
        else bfs(aCapacity, b, cMoveA);

        int cMoveB = c - (bCapacity - b);
        if (cMoveB < 0) bfs(a, b + c, 0);
        else bfs(a, bCapacity, cMoveB);

        if (a == 0) milkAmounts.add(c);
    }
}


