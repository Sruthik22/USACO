// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class TwoTeams {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        int[] teams = new int[n];
        TreeSet<Integer> indexes = new TreeSet<>();
        HashSet<Integer> assigned = new HashSet<>(); // holds indexes of players
        PriorityQueue<Player> skills = new PriorityQueue<>(); // holds player class

        for (int i = 0; i < n; i++) {
            int skill = nextInt();
            skills.add(new Player(skill, i));
            indexes.add(i);
        }

        int team = 1;

        while (assigned.size() < n) {
            while (assigned.contains(skills.peek().index)) skills.poll();

            int in = skills.poll().index;

            teams[in] = team;
            assigned.add(in);
            indexes.remove(in);

            if(indexes.ceiling(in+1) != null)
            {
                int last=in;
                int cntr=0;
                while(indexes.ceiling(last+1) != null && cntr < k)
                {
                    last = indexes.ceiling(last+1);
                    teams[last] = team;
                    assigned.add(last);
                    indexes.remove(last);
                    cntr++;
                }
            }
            if(indexes.floor(in-1)!=null)
            {
                int last=in;
                int cntr=0;
                while(indexes.floor(last-1) !=null && cntr < k)
                {
                    last = indexes.floor(last-1);
                    teams[last] = team;
                    assigned.add(last);
                    indexes.remove(last);
                    cntr++;
                }
            }

            team++;
            team %= 2;
        }

        for (int i = 0; i < n; i++) {
            int assigned_team = teams[i];
            if (assigned_team == 0) assigned_team = 2;
            out.print(assigned_team);
        }

        out.close();
    }

    static class Player implements Comparable<Player>{
        int skill, index;

        Player(int skill, int index) {
            this.skill = skill;
            this.index = index;
        }

        @Override
        public int compareTo(Player o) {
            return -(this.skill - o.skill);
        }
    }
}


