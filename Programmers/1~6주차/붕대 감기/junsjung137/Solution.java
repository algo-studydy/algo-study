import java.util.*;

class Solution {
    static int ans;
    static int[] gameTime;
    static int hpMax;
    static int requireTime, heal, bonusHeal;
    static Character user;
    
    static void initializeData(int[] bandage, int health, int[][] attacks) {
        hpMax = health;
        requireTime = bandage[0];
        heal = bandage[1];
        bonusHeal = bandage[2];
        user = new Character(0, health);
        gameTime = new int[attacks[attacks.length - 1][0] + 1];
        for (int i = 0; i < attacks.length; i++) {
            gameTime[attacks[i][0]] = attacks[i][1];
        }
        ans = 0;
    }
    
    static void solve() {
        for (int t = 0; t < gameTime.length; t++) {
            int effect = user.hp + (gameTime[t] > 0 ? -gameTime[t] : heal);
            
            if (gameTime[t] > 0) {
                user.healTime = 0;
            } else if (user.healTime == requireTime) {
                user.healTime = 0;
                effect += bonusHeal;
            }

            user.hp = calcHp(effect);
            user.healTime++;
            if (user.hp <= 0) break;
        }
        
        ans = user.hp > 0 ? user.hp : -1;
    }
    
    static int calcHp(int hp) {
        return hp > hpMax ? hpMax : hp;
    }
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        initializeData(bandage, health, attacks);
        solve();
        return ans;
    }
    
    static class Character {
        int healTime;
        int hp;
        
        Character(int healTime, int hp) {
            this.healTime = healTime;
            this.hp = hp;
        }
        
        @Override
        public String toString() {
            return "healTime: " + healTime
                + " hp: " + hp
                ;
        }
    }
}