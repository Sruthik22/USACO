// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SlidingCost {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static Element med;
    static TreeSet<Element> top, bottom;
    static long st, sb;

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

        if (k == 1) {
            for (int i = 0; i < n; i++) {
                out.print(0 + " ");
            }
            out.close();
            return;
        }

        for (int i = k-1; i < n; i++) {
            elements[i] = new Element(nextInt(), i);
            add(elements[i]);
            out.print(st - top.size() * med.value + bottom.size() * med.value - sb + " ");
            remove(elements[i-k+1]);
        }

        out.close();
    }

    static void add(Element element) {
        if (med == null) {
            med = element;
            return;
        }
        else if (element.compareTo(med) < 0) {
            bottom.add(element);
            sb += element.value;
        }
        else {
            top.add(element);
            st += element.value;
        }
        fix();
    }

    static void remove(Element element) {
        if (element == med) {
            med = top.pollFirst();
            st -= med.value;
        }
        else if (element.compareTo(med) < 0) {
            bottom.remove(element);
            sb -= element.value;
        }
        else {
            top.remove(element);
            st -= element.value;
        }
        fix();
    }

    static void fix() {
        int m = 1 + bottom.size() + top.size();

        if (bottom.size() < (m-1)/2) {
            bottom.add(med);
            sb += med.value;
            med = top.pollFirst();
            st -= med.value;
        }

        if (bottom.size() > (m-1)/2) {
            top.add(med);
            st += med.value;;
            med = bottom.pollLast();
            sb -= med.value;
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


