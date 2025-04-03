import java.util.*;
import java.io.*;

public class Main {
    static class Truck {
        int weight, exitTime;
        Truck(int weight, int exitTime){
            this.weight = weight;
            this.exitTime = exitTime;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Truck> bridge = new ArrayDeque<>();
        int time = 0, index = 0, load = 0;
        while (index < n || !bridge.isEmpty()) {
            time++;
            if (!bridge.isEmpty() && bridge.peek().exitTime == time) {
                load -= bridge.poll().weight;
            }
            if (index < n && bridge.size() < w && load + trucks[index] <= L) {
                load += trucks[index];
                bridge.add(new Truck(trucks[index], time + w));
                index++;
            }
        }
        System.out.println(time);
    }
}
