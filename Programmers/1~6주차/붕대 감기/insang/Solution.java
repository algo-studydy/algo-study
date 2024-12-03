class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;

        int hp = health; // 초기 체력
        int time = bandage[0];  // 시전 시간
        int secHp = bandage[1];  // 초당 회복량
        int bonusHp = bandage[2];  // 추가 회복량
        int curTime = 1;  // 현재 시간
        int success = 1;  // 연속 성공 시간
        // int attackIdx = 0;

        // 현재 시간이 마지막 공격 시간이 될 때까지 반복
        while(curTime <= attacks[attacks.length-1][0]){
            boolean attacked = false;  // 공격 당했는지 여부

            // attacks 배열을 순회하며 현재 시간과 공격 시간이 같을 경우 공격
            for(int i = 0; i < attacks.length; i++){
                if(attacks[i][0] == curTime){
                    hp -= attacks[i][1];

                    if(hp <= 0) return -1;  // 체력이 0 이하가 되어 사망
                    success = 0;
                    attacked = true;
                    break;
                }
            }

            if(!attacked && success < time){
                // 현재 체력 + 초당 회복량 < 최대 체력이면 체력이 초당 회복량 만큼 증가
                if(hp + secHp < health) hp += secHp;
                else hp = health;
                success++;
            }

            if(success == time){
                if(hp + bonusHp < health) hp += bonusHp;
                else hp = health;
                success = 0;
            }
            curTime++;
        }
        return hp;
    }
}