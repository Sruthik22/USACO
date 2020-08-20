import java.util.*;
import java.io.*;

public class TwoTypesOfSpells {

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Float.parseFloat(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class CPMath {
        static int add(int a, int b) {
            a += b;

            if (a >= mod) a -= mod;

            return a;
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

    static InputReader sc;
    static PrintWriter pw;

    static int mod = (int) (1e9 + 7);

    static TreeSet<Spell> maxes;
    static TreeSet<Spell> mins;
    static long maxesSum;
    static long totalSum;
    static int totalLightnings;
    static int lightningsInMax;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        maxes = new TreeSet<>();
        mins = new TreeSet<>();

        totalLightnings = 0;
        lightningsInMax = 0;
        maxesSum = 0;
        totalSum = 0;

        for (int i = 0; i < n; i++) {
            int tp = sc.nextInt();
            int d = sc.nextInt();

            int orig_d = d;

            if (tp == 0) {
                // fire spell

                if (d > 0) {
                    Spell s = new Spell(d, false);

                    if (maxes.size() > 0 && s.damage > maxes.first().damage) {
                        Spell minMax = maxes.pollFirst();
                        if (minMax.isLightning) {
                            lightningsInMax--;
                        }
                        mins.add(minMax);
                        maxes.add(s);
                        maxesSum -= minMax.damage;
                        maxesSum += s.damage;
                    }

                    else {
                        mins.add(s);
                    }
                }

                else {
                    // forgets
                    d = -d;

                    Spell searching = new Spell(d, false);

                    if (maxes.contains(searching)) {
                        maxes.remove(searching);
                        maxesSum -= searching.damage;
                        Spell toAdd = mins.pollLast();
                        maxes.add(toAdd);
                        maxesSum += toAdd.damage;
                        if (toAdd.isLightning) lightningsInMax++;
                    }

                    else {
                        mins.remove(searching);
                    }
                }
            }
            else {
                // lightning spell

                if (d > 0) {
                    // learns
                    totalLightnings++;
                    Spell s = new Spell(d, true);

                    if (mins.size() > 0 && s.damage < mins.last().damage) {
                        Spell maxMin = mins.pollFirst();
                        if (maxMin.isLightning) {
                            lightningsInMax++;
                        }
                        maxes.add(maxMin);
                        mins.add(s);
                        maxesSum += maxMin.damage;
                    }

                    else {
                        maxes.add(s);
                        maxesSum += s.damage;
                        lightningsInMax++;
                    }
                }

                else {
                    // forgets
                    totalLightnings--;
                    d = -d;

                    Spell searching = new Spell(d, true);

                    if (maxes.contains(searching)) {
                        maxes.remove(searching);
                        maxesSum -= searching.damage;
                        lightningsInMax--;
                    }

                    else {
                        mins.remove(searching);
                        Spell minInMax = maxes.pollFirst();
                        mins.add(minInMax);
                        if (minInMax.isLightning) lightningsInMax--;
                        maxesSum -= minInMax.damage;
                    }
                }
            }

            totalSum += orig_d;

            long result = totalSum;
            result += maxesSum;

            if (lightningsInMax == totalLightnings && totalLightnings != 0) {
                result -= maxes.first().damage;
                if (!mins.isEmpty()) result += mins.last().damage;
            }

            pw.println(result);
        }

        pw.close();
    }

    static class Spell implements Comparable<Spell> {
        boolean isLightning;
        long damage;

        Spell(int damage, boolean isLightning) {
            this.damage = damage;
            this.isLightning = isLightning;
        }

        @Override
        public int compareTo(Spell o) {
            return Long.compare(this.damage, o.damage);
        }
    }
}


