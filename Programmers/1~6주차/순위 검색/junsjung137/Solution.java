import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] ans;
    static String[] query;
    static List<String[]> list;
    static Applicant root;
    static HashSet<Applicant> hSet;
    
    static void initializeData(String[] info, String[] query) {
        N = query.length;
        Solution.query = query;
        list = new ArrayList<>();
        list.add(new String[] {"root"});
        list.add(new String[] {"cpp", "java", "python"});
        list.add(new String[] {"backend", "frontend"});
        list.add(new String[] {"junior", "senior"});
        list.add(new String[] {"chicken", "pizza"});
        root = new Applicant("root", new ArrayList<>());
        buildHierarchy(root, 0);
        for (int i = 0; i < info.length; i++) {
            saveData(info[i].split(" "), root, 0);
        }
        hSet = new HashSet<>();
        ans = new int[N];
    }
    
    static void saveData(String[] applicant, Applicant a, int depth) {
        if (depth == 4) {
            int score = Integer.parseInt(applicant[depth]);
            if (a.scores == null) a.scores = new ArrayList<>();
            a.scores.add(score);
            return;
        }
        
        String[] types = list.get(depth + 1);
        for (int i = 0; i < types.length; i++) {
            
            if (applicant[depth].equals(a.sub.get(i).type)) {
                saveData(applicant, a.sub.get(i), depth + 1);
                break;
            }
        }
    }
    
    static void buildHierarchy(Applicant a, int depth) {
        if (depth == 4) return;
        
        String[] types = list.get(depth + 1);
        for (int i = 0; i < types.length; i++) {
            Applicant b = new Applicant(types[i], new ArrayList<>());
            
            a.sub.add(b);
        
            buildHierarchy(b, depth + 1);
        }
    }
    
    static void solve() {
        for (int i = 0; i < N; i++) {
            ans[i] = match(query[i].split(" and "), root, 0);
        }
    }
    
    static int match(String[] query, Applicant a, int depth) {
        int sum = 0;
        
        switch (depth) {
            case 0:
            case 1:
            case 2:
            case 3:
                String str = query[depth];
                if (depth == 3) str = str.split(" ")[0];
                if ("-".equals(str)) {
                    for (Applicant b : a.sub) {
                        sum += match(query, b, depth + 1);
                    }
                } else {
                    for (Applicant b : a.sub) {
                        if (str.equals(b.type)) {
                            sum = match(query, b, depth + 1);
                            break;
                        }
                    }
                }
                return sum;
            case 4:
                if (a.scores == null) return 0;
                int value = Integer.valueOf(query[3].split(" ")[1]);
                if (!hSet.contains(a)) {
                    Collections.sort(a.scores, (s1, s2) -> {
                        return s2 - s1;
                    });
                    hSet.add(a);
                }
            
                int head = a.scores.size() - 1;
                int tail = 0;
                if (a.scores.get(head) >= value) return head + 1;
                if (a.scores.get(tail) < value) return 0;
                
                while (head - tail > 1) {
                    int point = tail + (head - tail) / 2;
                    if (a.scores.get(point) >= value) {
                        tail = point;
                        continue;
                    }
                    
                    head = point;
                }
                
                if (a.scores.get(head) < value) return head;
        }
        
        return 0;
    }
    
    public int[] solution(String[] info, String[] query) {
        initializeData(info, query);
        solve();
        
        return ans;
    }
    
    static class Applicant {
        String type;
        List<Applicant> sub;
        List<Integer> scores;
        
        Applicant(String type, List<Applicant> sub) {
            this.type = type;
            this.sub = sub;
        }
        
        @Override
        public String toString() {
            return "type: " + this.type
                + " sub size: " + sub.size();
        }
    }
}