import java.util.Random;

public class Jad {
    private int hp;
    private int defenceLevel;
    private int defenceRoll;
    private int attackRoll;
    private Player killedBy;

    public Jad(int hp, int defence){
        this.hp = hp;
        this.defenceLevel = defence;
    }

    public void hammerSpec(){
        if(killedBy.doesAttackHit(killedBy.getMaxMeleeAttackRoll(),this.getMaxDefenceRoll(), true)) {
            this.defenceLevel *= 0.7;
        }
    }

    public int getDefenceRoll() {
        Random random = new Random();
        return random.nextInt(this.getMaxDefenceRoll());
    }

    public int getMaxDefenceRoll(){
        int effectiveDefenceLevel = (int) Math.round(defenceLevel) + 9;
        return effectiveDefenceLevel * 64;
    }
}