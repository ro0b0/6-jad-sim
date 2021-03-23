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
        }
        return Math.random() < accuracy;
    }
}
