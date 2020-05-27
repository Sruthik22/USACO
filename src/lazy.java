import java.util.*;
import java.io.*;

public class lazy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("lazy.in"));
        int n = in.nextInt(); // # of patches of grass
        int k = in.nextInt(); // # of max patches in a photo

        Patch[] patches = new Patch[n];

        for (int i = 0; i < n; i++) {
            patches[i] = new Patch(in);
        }

        in.close();

        Arrays.sort(patches);

        int maxResult = 0; // bessie's position

        int left = 0;
        int right = 0;

        while (right < n && patches[right].x - patches[left].x <= 2*k) {
            maxResult += patches[right].g;
            right++;
        }

        int cur = maxResult;

        while (right < n) {
            cur -= patches[left].g;
            left++;
            while (right < n && patches[right].x - patches[left].x <= 2*k) {
                cur += patches[right].g;
                right++;
            }

            maxResult = Math.max(maxResult, cur);
        }

        PrintWriter out = new PrintWriter(new File("lazy.out"));
        System.out.println(maxResult);
        out.println(maxResult);
        out.close();
    }

    private static class Patch implements Comparable<Patch> {
        int g, x;

        private Patch (Scanner in) {
            g = in.nextInt();
            x = in.nextInt();
        }

        @Override
        public int compareTo(Patch arg0) {
            // TODO Auto-generated method stub
            return this.x - arg0.x;
        }
    }
}
