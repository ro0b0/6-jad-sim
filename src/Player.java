import java.util.Random;

public class Player {
    private int crushBonus;
    private int maxHit;
    private int rangedBonus;
    private int attackLevel;
    private int rangedLevel;
    private int maxAttackRoll;
    private boolean hasRigour;
    private boolean hasTwistedBow;

    public int getCrushBonus() {
        return crushBonus;
    }

    public void setCrushBonus(int crushBonus) {
        this.crushBonus = crushBonus;
    }

    public int getRangedBonus() {
        return rangedBonus;
    }

    public void setRangedBonus(int rangedBonus) {
        this.rangedBonus = rangedBonus;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public int getRangedLevel() {
        return rangedLevel;
    }

    public void setRangedLevel(int rangedLevel) {
        this.rangedLevel = rangedLevel;
    }

    public boolean isHasRigour() {
        return hasRigour;
    }

    public void setHasRigour(boolean hasRigour) {
        this.hasRigour = hasRigour;
    }

    public int getMaxHit(){
        return maxHit;
    }

    public void setMaxHit(int maxHit){
        this.maxHit = maxHit;
    }

    public boolean isHasTwistedBow(){
        return hasTwistedBow;
    }

    public void setHasTwistedBow(boolean hasTwistedBow){
        this.hasTwistedBow =  hasTwistedBow;
    }

    //The game calculates a max attack roll based on your attack bonuses, stats, prayers and set bonuses.
    //This max roll is calculated here. A number rolled from 0 - maxRoll is  then used in the accuracy check
    //to check if you hit on the jad or not.
    public int getMaxRangedAttackRoll() {
        double rigour = 1;
        double twistedBow = 1;
        if(this.isHasRigour()){
            rigour = 1.2;
        }

        if(this.hasTwistedBow){
            twistedBow = 1.4;
        }
        int effectiveRangedLevel = (int) Math.floor(this.getRangedLevel() * rigour) + 8;
        double attackRoll = effectiveRangedLevel * (this.getRangedBonus() + 64) * twistedBow;
        return (int) attackRoll;
    }

    public int getMaxMeleeAttackRoll(){
        int effectiveAttackLevel = (int) Math.floor(this.getAttackLevel() * 1.2) + 11;
        double attackRoll = effectiveAttackLevel * (this.getCrushBonus() + 64) * 1.025;
        return (int) attackRoll;
    }

    //This part calculates whether or not your attack has hit. Because of the way the Old School Runescape
    //accuracy checks work, it first checks the maxAttackRoll of the player and the maxDefenceRoll of the jad
    //against eachother, and then chooses the formula to use based on this.
    //It also has a separate check to see if the Dragon warhammer landed a hit but rolled a 0, since the hammer
    //does not reduce defence on a 0 regardless of the accuracy roll.
    public boolean doesAttackHit(int maxAttackRoll, int maxDefenceRoll, boolean dwh){
        Random random = new Random();
        double accuracy;
        if(maxAttackRoll>maxDefenceRoll) {
            accuracy = 1 - (((double) maxDefenceRoll + 2) / (2 * ((double) maxAttackRoll + 1)));
            if(dwh){
                int dwhHit = random.nextInt(this.getMaxHit() + 1);
                if(dwhHit == 0){
                    return false;
                }
            }
        }else{
            accuracy = (double) maxAttackRoll / (2 * ((double) maxDefenceRoll + 1));
            if(dwh){
                int dwhHit = random.nextInt(this.getMaxHit() + 1);
                if(dwhHit == 0){
                    return false;
                }
            }
        }
        return Math.random() < accuracy;
    }
}
