class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int count = 0;
        int atkCount = 0;
        int maxHealth = health;
        for(int i = 0; i <= attacks[attacks.length-1][0] ; i++){
            //공격
            if(i == attacks[atkCount][0]){
                health-=attacks[atkCount][1];
                //체력 0이하 되면 죽음(종료)
                if(health<=0){
                    return -1;
                }
                count = 0;
                atkCount++;
            }else{
                count++;
                //추가회복 성공
                if(count == bandage[0]){
                    health=health+bandage[1]+bandage[2];
                    count = 0;
                }else{
                    health+=bandage[1];
                }
                //최대체력 보다 높이 회복 불가
                if(health>=maxHealth) health=maxHealth;
            }

        }
        answer = health;
        return answer;
    }
}