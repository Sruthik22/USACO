// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SlidingMedian {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static Element med;
    static TreeSet<Element> top, bottom;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        Element[] elements = new Element[n];
        top = new TreeSet<>();
        bottom = new TreeSet<>();

        for (int i = 0; i < k-1; i ++) {
            elements[i] = new Element(nextInt(), i);
            add(elements[i]);
        }

        for (int i = k-1; i < n; i++) {
            elements[i] = new Element(nextInt(), i);
            add(elements[i]);
            out.print(med.value);
            if (i != n-1) out.print(" ");
            remove(elements[i-k+1]);
        }


        out.close();
    }

    static void add(Element element) {
        if (med == null) {
            med = element;
            return;
        }
        else if (element.compareTo(med) < 0) bottom.add(element);
        else top.add(element);
        fix();
    }

    static void remove(Element element) {
        if (element == med) med = top.pollFirst();
        else if (element.compareTo(med) < 0) bottom.remove(element);
        else top.remove(element);
        fix();
    }

    static void fix() {
        int m = 1 + bottom.size() + top.size();

        if (bottom.size() < (m-1)/2) {
            bottom.add(med);
            med = top.pollFirst();
        }

        if (bottom.size() > (m-1)/2) {
            top.add(med);
            med = bottom.pollLast();
        }
    }

    static class Element implements Comparable<Element>{
        int value, index;

        Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            if (this.value == o.value) return this.index - o.index;
            return this.value - o.value;
        }
    }
}


