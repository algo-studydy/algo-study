import java.util.Scanner;

public class Main {
    static int n; //트럭수
    static int w; //다리길이
    static int L; //최대하중
    public static class Truck{
        int w; //무게
        int t; //남은시간
        public Truck(int w, int t){
            this.w = w;
            this.t = t;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = sc.nextInt();
        L = sc.nextInt();
        Truck[] trucks = new Truck[n];
        for(int i=0; i<n; i++){
            int weight = sc.nextInt();
            trucks[i] =new Truck(weight, w);
        }
        int num = 0;
        int front = 0;
        int rear = 0;
        int l=0;  // 현재 다리위의무게
        int time=1;
        while(true){
            time++;
            //다리위로 트럭하나 올라감
            if(num < n && l+trucks[num].w <= L){
                l+=trucks[num].w;
                rear=num++;
            }
            //다리위의 트럭 한칸씩 이동
            for(int i=front; i<=rear; i++){
                trucks[i].t--;
                if(trucks[i].t==0){
                    front++;
                    l-=trucks[i].w;
                }
            }
            //종료조건
            if(trucks[n-1].t==0)break;
        }

        System.out.println(time);
    }
}
