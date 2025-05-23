import java.util.*;

public class Main {
    static class Snow{
        int size;
        int i;
        int j;

        public Snow(int size, int i, int j) {
            this.size = size;
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        ArrayList<Snow> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                list.add(new Snow(arr[i]+arr[j], i, j)); //가능한 모든 조합 생성
            }
        }

        list.sort((a,b)->{
            return a.size - b.size;
        });

        int min = Integer.MAX_VALUE;

        for(int i=0; i<list.size(); i++){
            for(int j=i+1; j<list.size(); j++){
                Snow a = list.get(i);
                Snow b = list.get(j);
                if(a.i!=b.i && a.i!=b.j && a.j!=b.i && a.j!=b.j){ //a,b 에 저장된 i,j가 각각 달라야 만들수 있는 조합
                    min = Math.min(min, Math.abs(b.size-a.size));
                    break;
                }
            }
        }
        System.out.println(min);
    }
}
