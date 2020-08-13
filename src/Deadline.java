import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Deadline {

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

    static InputReader sc;
    static PrintWriter pw;

    static int n;
    static Task[] tasks;
    static Task[] orig;
    static Stack<Integer> stack;
    static boolean[] completed;
    static boolean works;
    static LinkedList<Integer>[] linkedLists;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();

        tasks = new Task[n];
        orig = new Task[n];
        linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            int d = sc.nextInt();
            int c = sc.nextInt();
            int r = sc.nextInt();

            ArrayList<Integer> arr = new ArrayList<>();

            for (int j = 0; j < r; j++) {
                int node = sc.nextInt();
                arr.add(node);
                linkedLists[i].add(node - 1);
            }

            tasks[i] = new Task(d, c, arr, i + 1);
            orig[i] = new Task(d, c, arr, i + 1);
        }

        Arrays.sort(tasks);
        for (int i = 0; i < n; i++) {
            Task t = orig[i];
            t.setSorted();
        }

        works = true;

        if (isCyclic()) {
            works = false;
        }

        if (works) {
            completed = new boolean[n];
            stack = new Stack<>();

            long time = 0;

            for (int i = 0; i < n; i++) {
                Task t = tasks[i];
                if (completed[t.id - 1]) continue;

                stack.add(t.id);
                rec(t.id);

                while (!stack.isEmpty()) {
                    int cur = stack.pop();
                    Task curTask = orig[cur - 1];

                    if (completed[curTask.id - 1]) continue;

                    time += curTask.c;

                    if (time > curTask.d) {
                        works = false;
                        break;
                    }

                    completed[curTask.id - 1] = true;
                }

                if (!works) break;
            }
        }

        if (works) {
            pw.println("YES");
            for (int i = 0; i < n; i++) {
                pw.print(tasks[i].id);
                pw.print(" ");
            }
        }

        else {
            pw.println("NO");
        }

        pw.close();
    }

    private static boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack)
    {

        // Mark the current node as visited and
        // part of recursion stack
        if (recStack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;

        recStack[i] = true;
        List<Integer> children = linkedLists[i];

        for (Integer c: children)
            if (isCyclicUtil(c, visited, recStack))
                return true;

        recStack[i] = false;

        return false;
    }

    private static boolean isCyclic()
    {

        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];


        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < n; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;

        return false;
    }

    static void rec(int curNode) {
        for (Task task: orig[curNode - 1].sorted) {
            if (completed[task.id - 1]) continue;
            stack.add(task.id);
            rec(task.id);
        }
    }

    static class Task implements Comparable<Task> {
        int d, c, id;
        ArrayList<Integer> depends;
        ArrayList<Task> sorted;

        Task(int d, int c, ArrayList<Integer> depends, int id) {
            this.d = d;
            this.c = c;
            this.depends = depends;
            this.id = id;
        }

        void setSorted() {
            sorted = new ArrayList<>();
            for (int i : depends) {
                Task t = orig[i - 1];
                sorted.add(t);
            }

            Collections.sort(sorted);
            Collections.reverse(sorted);
        }

        @Override
        public int compareTo(Task o) {
            return (this.d) - (o.d);
        }
    }
}