import java.util.*;

class Solution {
    static int answer;
    static int sAP, sCP;
    static int mAP, mCP;
    static List<Problem> list;
    static int[][] memo;

    static void initializeData(int ap, int cp, int[][] problems) {
        mAP = 0;
        mCP = 0;
        sAP = ap;
        sCP = cp;
        list = new ArrayList<>();

        list.add(new Problem(0, 0, 1, 0, 1));
        list.add(new Problem(0, 0, 0, 1, 1));

        for (int[] problem : problems) {
            int alp = problem[0];
            int cop = problem[1];
            int alp_rwd = problem[2];
            int cop_rwd = problem[3];
            int cost = problem[4];

            mAP = Math.max(mAP, alp);
            mCP = Math.max(mCP, cop);
            list.add(new Problem(alp, cop, alp_rwd, cop_rwd, cost));
        }

        memo = new int[151][151];
        for (int i = 0; i <= 150; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }

        answer = Integer.MAX_VALUE;
    }

    static void solve() {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.add(new State(sAP, sCP, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int alp = Math.min(current.alp, 150);
            int cop = Math.min(current.cop, 150);
            int time = current.time;

            if (alp >= mAP && cop >= mCP) {
                answer = Math.min(answer, time);
                continue;
            }

            if (memo[alp][cop] <= time) {
                continue;
            }

            memo[alp][cop] = time;

            for (Problem p : list) {
                if (p.alp <= alp && p.cop <= cop) {
                    int nextAlp = alp + p.alp_rwd;
                    int nextCop = cop + p.cop_rwd;
                    int nextTime = time + p.cost;
                    pq.add(new State(nextAlp, nextCop, nextTime));
                }
            }
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        initializeData(alp, cop, problems);
        solve();
        return answer;
    }

    static class Problem {
        int alp, cop, alp_rwd, cop_rwd, cost;

        Problem(int alp, int cop, int alp_rwd, int cop_rwd, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.alp_rwd = alp_rwd;
            this.cop_rwd = cop_rwd;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "alp&cop(" + alp + ", " + cop + ")" +
                   " rA&rC(" + alp_rwd + ", " + cop_rwd + ")" +
                   " cost: " + cost;
        }
    }

    static class State {
        int alp, cop, time;

        State(int alp, int cop, int time) {
            this.alp = alp;
            this.cop = cop;
            this.time = time;
        }
    }
}
