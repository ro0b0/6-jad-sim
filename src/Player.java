import java.util.Random;
import java.util.Scanner;

public class Player {
    private int crushBonus;
    private int maxHitDwh;
    private int rangedBonus;
    private int attackLevel;
    private int rangedLevel;
    private boolean hasRigour;
    private boolean hasTwistedBow;

    public Player(int crushBonus,int rangedBonus,int rangedLevel,int attackLevel,int maxHitDwh,boolean hasRigour,boolean hasTwistedBow){
        this.crushBonus =  crushBonus;
        this.rangedBonus = rangedBonus;
        this.rangedLevel = rangedLevel;
        this.attackLevel = attackLevel;
        this.maxHitDwh = maxHitDwh;
        this.hasRigour = hasRigour;
        this.hasTwistedBow = hasTwistedBow;
    }

    public static Player defaultPlayerTbowRigour(){
        return new Player(190,188,112,118,69,true,true);
    }

    public static Player createPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your crush bonus: ");
        int crushBonus = scanner.nextInt();
        System.out.print("Enter your ranged bonus: ");
        int rangedBonus = scanner.nextInt();
        System.out.print("Enter your ranged level: ");
        int baseRangedLevel = scanner.nextInt();
        int pottedRangedLevel = (int) (baseRangedLevel * 1.1 +4);
        System.out.print("Enter your attack level: ");
        int baseAttackLevel = scanner.nextInt();
        int pottedAttackLevel = (int) (baseAttackLevel * 1.15 + 5);
        System.out.print("Enter your max hit with the dragon warhammer: ");
        int maxHitDwh = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Are you using rigour? (Y/N): ");
        String answer = scanner.nextLine();
        boolean hasRigour = answer.equals("Y");
        System.out.print("Are you using a twisted bow? (Y/N): ");
        answer = scanner.nextLine();
        boolean hasTwistedBow = answer.equals("Y");

        return new Player(crushBonus,rangedBonus,pottedRangedLevel,pottedAttackLevel,maxHitDwh,hasRigour,hasTwistedBow);
    }

    public void printPlayerInfo(){
        System.out.printf("\nYour crush bonus has been set at %s and your ranged bonus has been set at %s.",this.crushBonus,this.rangedBonus);
        System.out.printf("\nYour ranged level while potted has been set to %s.",this.rangedLevel);
        System.out.printf("\nYour attack level while potted has been set to %s.",this.attackLevel);
        System.out.printf("\nYour max hit with the dragon warhammer has been set to %s.",this.maxHitDwh);
        System.out.printf("\nUsing rigour: %s.",this.hasRigour);
        System.out.printf("\nUsing a twisted bow: %s.",this.hasTwistedBow);
        System.out.printf("\nYour max attack roll with the twisted bow is %s.",this.getMaxRangedAttackRoll());
        System.out.printf("\nYour max attack roll with the dragon warhammer in inquisitor's is %s.",this.getMaxMeleeAttackRoll());
    }

    //The game calculates a max attack roll based on your attack bonuses, stats, prayers and set bonuses.
    //This max roll is calculated here. A number rolled from 0 - maxRoll is  then used in the accuracy check
    //to check if you hit on the jad or not.
    public int getMaxRangedAttackRoll() {
        double rigour = 1;
        double twistedBow = 1;
        if(this.hasRigour){
            rigour = 1.2;
        }

        if(this.hasTwistedBow){
            twistedBow = 1.4;
        }
        int effectiveRangedLevel = (int) Math.floor(this.rangedLevel * rigour) + 8;
        double attackRoll = effectiveRangedLevel * (this.rangedBonus + 64) * twistedBow;
        return (int) attackRoll;
    }

    public int getMaxMeleeAttackRoll(){
        int effectiveAttackLevel = (int) Math.floor(this.attackLevel * 1.2) + 11;
        double attackRoll = effectiveAttackLevel * (this.crushBonus + 64) * 1.025;
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
                int dwhHit = random.nextInt(this.maxHitDwh + 1);
                if(dwhHit == 0){
                    return false;
                }
            }
        }else{
            accuracy = (double) maxAttackRoll / (2 * ((double) maxDefenceRoll + 1));
            if(dwh){
                int dwhHit = random.nextInt(this.maxHitDwh + 1);
                if(dwhHit == 0){
                    return false;
                }
            }
        }
        return Math.random() < accuracy;
    }
}
