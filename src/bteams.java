// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class bteams {

    static int n = 12;
    static int[] player_skill;
    static int answer = Integer.MAX_VALUE;
    static int[] team_count;
    static int[] player_team;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("bteams.in"));

        player_skill = new int[n];

        for (int i = 0; i < n; i++) {
            player_skill[i] = in.nextInt();
        }

        in.close();

        team_count = new int[n/3];
        player_team = new int[n];

        recurse(0);

        int result = answer;
        PrintWriter out = new PrintWriter(new File("bteams.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void recurse(int player) {
        if (player == 12) {
            int[] team_skill = new int[] {0,0,0,0};
            for (int i = 0; i < 12; i++) {
                team_skill[player_team[i]] += player_skill[i];
            }
            int S = Math.max(Math.max(team_skill[0], team_skill[1]),
                    Math.max(team_skill[2], team_skill[3]));
            int s = Math.min(Math.min(team_skill[0], team_skill[1]),
                    Math.min(team_skill[2], team_skill[3]));

            if (answer == -1 || S - s < answer) {
                answer = S - s;
            }
            return;
        }

        for (int team = 0; team < 4; team++) {
            if (team_count[team] < 3) {
                player_team[player] = team;
                team_count[team]++;

                recurse(player+1);

                team_count[team]--;
            }
        }
    }
}


