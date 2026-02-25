import java.util.*;

class Solution {
    int[] mats;
    String[][] park;
    int N;
    int M;
    public int solution(int[] mats, String[][] park) {
        this.mats = mats;
        this.park = park;
        N = park.length;
        M = park[0].length;

        Arrays.sort(mats);
        int max = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(park[i][j].equals("-1")){
                    max = Math.max(max, findSize(i,j));
                }
            }
        }

        return max;
    }

    public int findSize(int r, int c){
        int max = -1;
        L : for(int size : mats){

            for(int i=r; i<r+size; i++){
                for(int j=c; j<c+size; j++){
                    if(i>=N || j>=M) continue L;
                    if(!park[i][j].equals("-1")) continue L;
                }
            }
            max = size;
        }
        return max;
    }
}