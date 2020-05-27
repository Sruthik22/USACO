// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class assign {

    private static int n;
    private static char[][] mat; // relationship matrix
    private static int[] breed; // the breeds of each of the cows

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("assign.in"));
        n = in.nextInt();
        mat = new char[16][16];
        breed = new int[16];
        int k = in.nextInt();

        for (int i=0; i<k; i++)
        {
            char c = in.next().charAt(0);
            int x = in.nextInt();
            int y = in.nextInt();

            mat[x][y]=c;
            mat[y][x]=c;
        }

        in.close();

        int result = rec(1);
        PrintWriter out = new PrintWriter(new File("assign.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static int rec(int x)
    {
        if (x>n) return 1;

        int cnt=0;
        // try all breeds for cow x
        for (int i=1; i<=3; i++)
        {
            // check if there is a conflict with the breed assignments
            //		of the cows before x
            boolean conflict=false;
            for (int j=1; j<x; j++)
                if ((mat[x][j]=='S' && breed[j]!=i) ||
                        (mat[x][j]=='D' && breed[j]==i))
                {
                    conflict=true;
                    break;
                }
            // assign breed i to cow x if there is no conflict
            if (!conflict)
            {
                breed[x]=i;
                cnt+=rec(x+1);
                breed[x]=0;
            }
        }

        return cnt;
    }
}