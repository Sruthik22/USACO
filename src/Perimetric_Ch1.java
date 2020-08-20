import java.util.*;
import java.io.*;

public class Perimetric_Ch1 {

    static class CPMath {
        static int add(long a, long b) {
            a += b;

            if (a >= mod) a -= mod;

            return (int) a;
        }

        static int sub(int a, int b) {
            a -= b;
            if (a < 0) a += mod;
            return a;
        }

        static int multiply(int a, long b) {
            b = a * b;
            return (int) (b % mod);
        }

        static int divide(int a, int b) {
            return multiply(a, inverse(b));
        }

        static int inverse(int a) {
            return power(a, mod - 2);
        }

        static int power(int a, int b) {
            int r = 1;

            while (b > 0) {
                if (b % 2 == 1) {
                    r = multiply(r, a);
                }

                a = multiply(a, a);
                b /= 2;
            }

            return r;
        }
    }

    static int mod = (int) (1e9 + 7);

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("Perimetric_Ch1.in")));
        PrintWriter out = new PrintWriter(new File("Perimetric_Ch1.out"));

        int t = nextInt();

        for (int tt = 0; tt < t; tt++) {
            int n = nextInt();
            int k = nextInt();
            int w = nextInt();

            int[] leftPoints = new int[k]; // L1 -- Lk

            for (int i = 0; i < k; i++) {
                leftPoints[i] = nextInt();
            }

            int aL = nextInt();
            int bL = nextInt();
            int cL = nextInt();
            int dL = nextInt();

            int[] heights = new int[k]; // H1 - Hk

            for (int i = 0; i < k; i++) {
                heights[i] = nextInt();
            }

            int aH = nextInt();
            int bH = nextInt();
            int cH = nextInt();
            int dH = nextInt();

            int result = 1;
            int lastH = 0;
            int lastL = 0;
            int lastH2 = 0;
            int lastL2 = 0;
            int perimeter = 0;

            HashMap<Integer, Integer> heightAtPoint = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                int curStartX = 0;
                int curHeight = 0;

                if (i <= k) {
                    curStartX = leftPoints[i-1];
                    curHeight = heights[i-1];
                }

                else {
                    mod = dL;
                    curStartX = CPMath.add(CPMath.add(CPMath.multiply(aL, lastL2), CPMath.multiply(bL, lastL)), cL) + 1;
                    mod = dH;
                    curHeight = CPMath.add(CPMath.add(CPMath.multiply(aH, lastH2), CPMath.multiply(bH, lastH)), cH) + 1;
                    mod = (int) (1e9 + 7);
                }

                for (int l = curStartX; l < curStartX + w; l++) {

                    int h = heightAtPoint.getOrDefault(l, 0);

                    if (h >= curHeight) continue;
                    if (h == 0) perimeter = CPMath.add(perimeter, 2);

                    int ex1 = heightAtPoint.getOrDefault(l-1, 0);
                    int ex2 = heightAtPoint.getOrDefault(l+1, 0);

                    perimeter = CPMath.sub(perimeter, Math.abs(ex1 - h));
                    perimeter = CPMath.sub(perimeter, Math.abs(ex2 - h));

                    heightAtPoint.put(l, curHeight);

                    h = curHeight;

                    perimeter = CPMath.add(perimeter, Math.abs(ex1 - h));
                    perimeter = CPMath.add(perimeter, Math.abs(ex2 - h));
                }

                result = CPMath.multiply(result, perimeter);
                lastH2 = lastH;
                lastH = curHeight;
                lastL2 = lastL;
                lastL = curStartX;
            }

            out.print("Case #" + (tt+1) + ": ");
            out.println(result);
        }

        out.close();
    }
}


