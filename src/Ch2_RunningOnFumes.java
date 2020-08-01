// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Ch2_RunningOnFumes {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("Ch2_RunningOnFumes.in")));
        PrintWriter out = new PrintWriter(new File("Ch2_RunningOnFumes.out"));

        Thread t=new Thread(null, null, "", 1<<28) {
            public void run() {
                if (true) {
                    try {
                        int T=nextInt();
                        for (int tt=0; tt<T; tt++) {
                            int n=nextInt();
                            int tankCap=nextInt();
                            int fromI=nextInt()-1, toI=nextInt()-1;

                            Node[] nodes=new Node[n];
                            for (int i=0; i<n; i++) nodes[i]=new Node();
                            for (int i=0; i<n; i++) {
                                int par=nextInt()-1, cost=nextInt();
                                if (par!=-1) {
                                    nodes[par].adj.add(nodes[i]);
                                    nodes[i].adj.add(nodes[par]);
                                }
                                nodes[i].gasCost=cost;
                                if (cost!=0) nodes[i].canBuy=true;
                            }
                            Node from=nodes[fromI], to=nodes[toI];
                            from.canBuy=true;
                            from.gasCost=0;
                            to.canBuy=false;

                            from.dfs(null, 0);
                            for (Node nn:nodes) nn.distA=nn.dist;
                            to.dfs(null, 0);
                            for (Node nn:nodes) nn.distB=nn.dist;

                            for (Node nn:nodes)
                                nn.onPath=nn.distA+nn.distB==from.distB;

                            SegTree st=new SegTree(0, from.distB);
                            st.update(from.distB, 0);
                            Arrays.sort(nodes);
                            for (Node nn:nodes) {
                                if (!nn.canBuy) continue;
                                int l=from.distB-nn.distB;
                                int r=l+tankCap;
                                long ans=st.rangeQuery(l, r)+nn.gasCost;
                                st.update(nn.distA, ans);
                                nn.dpAns=ans;
                            }

                            long realAns=from.dpAns>=oo?-1:from.dpAns;
                            out.println("Case #"+(tt+1)+": "+realAns);
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        t.join();
    }

    static long oo=(long)1e18;

    static class SegTree {
        int leftmost, rightmost;
        long min;
        SegTree lChild, rChild;
        public SegTree(int leftmost, int rightmost) {
            this.leftmost=leftmost;
            this.rightmost=rightmost;
            if (leftmost!=rightmost) {
                int mid=(leftmost+rightmost)/2;
                lChild=new SegTree(leftmost, mid);
                rChild=new SegTree(mid+1, rightmost);
            }
            min=oo;
        }
        public void update(int index, long value) {
            if (index<leftmost||index>rightmost) return;
            this.min=Math.min(min, value);
            if (leftmost==rightmost) return;
            if (index<=lChild.rightmost) lChild.update(index, value);
            else rChild.update(index, value);
        }

        public long rangeQuery(int l, int r) {
            if (l<=leftmost && r>=rightmost) return min;
            if (l>rightmost || r<leftmost) return oo;
            return Math.min(lChild.rangeQuery(l, r), rChild.rangeQuery(l, r));
        }

    }

    static class Node implements Comparable<Node> {
        ArrayList<Node> adj=new ArrayList<>();
        int dist, distA, distB;
        boolean onPath;
        boolean canBuy=false;
        int gasCost;
        long dpAns;

        public void dfs(Node par, int dist) {
            this.dist=dist;
            for (Node nn:adj) {
                if (nn!=par)
                    nn.dfs(this, dist+1);
            }
        }

        public int compareTo(Node o) {
            return Integer.compare(distB, o.distB);
        }
    }

}

