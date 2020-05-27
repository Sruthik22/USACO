public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("${NAME}.in"));
        int n = in.nextInt(); // number of nodes
        int k = in.nextInt(); // number of edges

        // nodes are labeled with ids: 0, 1, 2, 3... n-1
        // adjacency matrix (2D array)
        // matrix[i][j] == true if there exists an edge between node i and node j
        // matrix[i][j] == matrix[j][i]
//    boolean[][] matrix = new boolean[n][n];
//    for(int i = 0; i < k; i++) {
//      int a = in.nextInt();
//      int b = in.nextInt();
//      matrix[a][b] = true;
//      matrix[b][a] = true;
//    }
        // 1000 nodes => matrix size: 1000^2
        // 10^5 nodes => matrix size: 10^10

        // adjacency list
        /*  0: [1, 2]
         *  1: [0, 3, 4]
         */
        // graph[i] is an ArrayList of neighbors of node i
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        // System.out.println(Arrays.toString(graph));
        int[] ret = bfs_recursive(graph, 0);
        System.out.println(Arrays.toString(ret));

        System.out.println(sum(new int[]{3, 5, 1, 2}));

        ret = dfs(graph, 0);
        System.out.println(Arrays.toString(ret));

        ret = new int[n];
        dfs_recursive(graph, new boolean[n], 0, ret);
        System.out.println(Arrays.toString(ret));

        PrintWriter out = new PrintWriter(new File("${NAME}.out"));
        in.close();
        out.close();
    }

    // perform a BFS starting that the start node
    // return int[] where array[i] denotes the parent of node i in the min-path from start to i
    // 0: [-1]
    // 1: [0]
    // 2: [0]
    // 3: [1]
    // 4: [1]
    // 5: [2]
    // 6: [2]
    // 7: [5]
    static int[] bfs(ArrayList<Integer>[] graph, int start) {
        int n = graph.length;

        int[] ret = new int[n];
        Arrays.fill(ret,  -1);

        // visited[i] denotes whether I have queued node i to be visited
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(queue.size() > 0) {
            int node = queue.remove();

            for(int neighbor : graph[node]) {
                if(!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    ret[neighbor] = node;
                }
            }
        }
        return ret;
    }

    // perform a dfs from the given start node onto nodes that are not marked as visited
    // int[] ret
    // ret[i] denotes the min dist to leaf of node i
    static void dfs_recursive(ArrayList<Integer>[] graph, boolean[] visited, int start, int[] ret) {
        visited[start] = true;
        boolean isLeaf = true;
        ret[start] = Integer.MAX_VALUE;
        for(int neighbor : graph[start]) {
            if(!visited[neighbor]) {
                dfs_recursive(graph, visited, neighbor, ret);
                isLeaf = false;
                ret[start] = Math.min(ret[start], ret[neighbor] + 1);
            }
        }

        if(isLeaf) {
            ret[start] = 0;
        }
    }

    static int[] dfs(ArrayList<Integer>[] graph, int start) {
        int n = graph.length;

        int[] ret = new int[n];
        Arrays.fill(ret,  -1);

        // visited[i] denotes whether I have queued node i to be visited
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        visited[start] = true;

        while(stack.size() > 0) {
            int node = stack.pop();

            for(int neighbor : graph[node]) {
                if(!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                    ret[neighbor] = node;
                }
            }
        }
        return ret;
    }


    static int[] bfs_recursive(ArrayList<Integer>[] graph, int start) {
        int n = graph.length;

        int[] ret = new int[n];
        Arrays.fill(ret,  -1);

        // visited[i] denotes whether I have queued node i to be visited
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        return bfs_recursive(graph, start, visited, queue, ret);
    }

    static int[] bfs_recursive(ArrayList<Integer>[] graph,
                               int start,boolean[] visited, Deque<Integer> queue, int[] ret) {
        if(queue.size() > 0) {
            int node = queue.remove();

            for(int neighbor : graph[node]) {
                if(!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    ret[neighbor] = node;
                }
            }
            return bfs_recursive(graph, start, visited, queue, ret);
        } else {
            return ret;
        }
    }

    static int sum(int[] array) {
        return sum(array, 0, 0);
    }

    static int sum(int[] array, int i, int sum) {
        if(i < array.length) { // recursive case
            sum += array[i];
            i++;
            return sum(array, i, sum);
        } else {
            return sum;
        }
    }
}
