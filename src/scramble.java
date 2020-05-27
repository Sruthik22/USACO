import java.util.*;
import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=206

public class scramble {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("scramble.in"));
        int n = in.nextInt();
        Cow[] orig = new Cow[n];
        for (int i = 0; i < n; i++) orig[i] = new Cow(in);

        Cow[] list = Arrays.copyOf(orig, n); // same references as original
        in.close();

        for (Cow c: list) c.name = c.lateName;
        Arrays.sort(list);
        for (Cow c: orig) {
            Cow earlyCopy = new Cow (c);
            earlyCopy.name = c.earlyName;

            c.earliestPos = Arrays.binarySearch(list, earlyCopy);

            if (c.earliestPos < 0) {
                c.earliestPos = - c.earliestPos-1;
            }
        }

        for (Cow c:list) c.name = c.earlyName;
        Arrays.sort(list);

        for (Cow c: orig) {
            Cow lateCopy = new Cow(c);
            lateCopy.name = c.lateName;
            c.latestPos = Arrays.binarySearch(list,  lateCopy);

            if (c.latestPos < 0) {
                c.latestPos = -c.latestPos - 1;
                c.latestPos--; // duplicate copy of cow's name
            }
        }

        for (Cow c: list) c.name = c.earlyName;
        Arrays.sort(list);

        PrintWriter out = new PrintWriter(new File("scramble.out"));
        for (Cow c:orig) {
            String str = "";
            str += c.earliestPos + 1;
            str += " ";
            str += c.latestPos + 1;
            System.out.println(str);
            out.println(str);
        }

        out.close();
    }

    private static class Cow implements Comparable <Cow>{
        String lateName , earlyName, name;
        // name is for the sorting function to look at

        int earliestPos, latestPos;

        private Cow(Scanner in) {
            this.name = in.next();

            char[] letters = this.name.toCharArray();
            Arrays.sort(letters);

            StringBuilder sb = new StringBuilder();

            for (char i : letters) {
                sb.append(i);
            }

            this.earlyName = sb.toString();

            this.lateName = sb.reverse().toString();
        }

        Cow(Cow other) {
            this.lateName = other.lateName;
            this.earlyName = other.earlyName;
        }

        @Override
        public int compareTo(Cow other) {
            return this.name.compareTo(other.name);
        }
    }
}
