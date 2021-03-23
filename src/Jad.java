import java.util.Random;

public class Jad {
    private int hp;
    private int defenceLevel;
    private Player killedBy;

    public Jad(int hp, int defence){
        this.hp = hp;
        this.defenceLevel = defence;
    }

    public static Jad defaultJad(){
        return new Jad(350,480);
    }
    //A simple method that reduces the jad's defence level by 30% if the player lands a  dragon warhammer on it.
    public void hammerSpec(){
        if(killedBy.doesAttackHit(killedBy.getMaxMeleeAttackRoll(),this.getMaxDefenceRoll(), true)) {
            this.defenceLevel *= 0.7;
        }
    }

    public void printJadInfo(){
        System.out.printf("\nJad's max defence roll is %s.",this.getMaxDefenceRoll());
    }

    public int getDefenceRoll() {
        Random random = new Random();
        return random.nextInt(this.getMaxDefenceRoll());
    }
    //The game calculates a max defence roll based on the jad's defence level and defence bonuses.
    //This max roll is calculated here. A number rolled from 0 - maxRoll is  then used in the accuracy check
    //to check if you hit on the jad or not.
    public int getMaxDefenceRoll(){
        int effectiveDefenceLevel = Math.round(defenceLevel) + 9;
        return effectiveDefenceLevel * 64;
    }
}