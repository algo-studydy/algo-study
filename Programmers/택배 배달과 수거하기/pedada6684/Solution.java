import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int didx = deliveries.length-1;
        int pidx = pickups.length-1;
        while(didx >= 0 && deliveries[didx] == 0) didx--;
        while(pidx >= 0 && pickups[pidx] == 0) pidx--;
        while(pidx >= 0 || didx >= 0){
            answer += Math.max(pidx+1, didx+1)*2;
            int dcap = cap;
            while(didx >= 0 && dcap>0){
                if(deliveries[didx] > dcap){
                    deliveries[didx] -= dcap;
                    dcap = 0;
                }else{
                    dcap -= deliveries[didx];
                    deliveries[didx] = 0;
                    while(didx >= 0 && deliveries[didx] == 0) didx--;
                }
            }
            int pcap = 0;
            while(pidx >= 0 && pcap<cap){
                if(pickups[pidx] > cap-pcap){
                    pickups[pidx] -= cap-pcap;
                    pcap = cap;
                }else{
                    pcap += pickups[pidx];
                    pickups[pidx] = 0;
                    while(pidx >= 0 && pickups[pidx] == 0) pidx--;
                }
            }
        }

        return answer;
    }
}