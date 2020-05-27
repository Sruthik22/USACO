private static int[] player_skill = new int[12];

    private static int[] player_team = new int[12];
    private static int[] team_count = new int[4];

    private static int answer = -1;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("bteams.in"));

        for (int i = 0; i < 12; i++) {
            player_skill[i] = in.nextInt();
        }

        in.close();

        recurse(0);

        int result = answer;
        PrintWriter out = new PrintWriter(new File("bteams.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static void recurse(int player) {
        if (player == 12) {
            int[] team_skill = {0,0,0,0};
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