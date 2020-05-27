// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class meeting {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;

    static ArrayList<Integer> bessieTimes;
    static ArrayList<Integer> elsieTimes;
    static LinkedList<Edge>[] adjacencyList;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("meeting.in")));

        n = nextInt();
        int m = nextInt();

        adjacencyList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            int c = nextInt(); // bessie time
            int d = nextInt(); // elsie time

            //a is higher than b in terms of elevation
            // so it is a directed graph a can go to b

            adjacencyList[a].add(new Edge(b, c, d));
        }

        String result = "";

        bessieTimes = new ArrayList<>();
        dfsBessie(0, 0);
        elsieTimes= new ArrayList<>();
        dfsElsie(0, 0);

        Collections.sort(bessieTimes);
        Collections.sort(elsieTimes);

        for (int i = 0; i < bessieTimes.size(); i++) {
            int time = bessieTimes.get(i);

            int ip = Collections.binarySearch(elsieTimes, time);

            if (ip >= 0) {
                result = "" + time;
                break;
            }
        }

        if (result.length() == 0) result = "IMPOSSIBLE";

        PrintWriter out = new PrintWriter(new File("meeting.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void dfsBessie(int curNode, int curTime) {
        if (curNode == n-1) {
            bessieTimes.add(curTime);
            return;
        }

        for (Edge e : adjacencyList[curNode]) {
            dfsBessie(e.connectedNode, curTime+e.bessieTime);
        }
    }

    static void dfsElsie(int curNode, int curTime) {
        if (curNode == n-1) {
            elsieTimes.add(curTime);
            return;
        }

        for (Edge e : adjacencyList[curNode]) {
            dfsElsie(e.connectedNode, curTime+e.elsieTime);
        }
    }

    static class Edge {
        int connectedNode, bessieTime, elsieTime;

        Edge(int connectedNode, int bessieTime, int elsieTime) {
            this.connectedNode = connectedNode;
            this.bessieTime = bessieTime;
            this.elsieTime = elsieTime;
        }
    }
}


