// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class SlidingCost {

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    static Element med;
    static TreeSet<Element> top, bottom;
    static long st, sb;

    public static void main(String[] args) throws Exception {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        int k = sc.nextInt();

        Element[] elements = new Element[n];
        top = new TreeSet<>();
        bottom = new TreeSet<>();

        for (int i = 0; i < k-1; i ++) {
            elements[i] = new Element(sc.nextInt(), i);
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
            elements[i] = new Element(sc.nextInt(), i);
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


