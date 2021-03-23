import java.util.Scanner;

public class Simulation {
    public static void Sim(){
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Jad jad = new Jad(350,480);
        System.out.print("Enter your crush bonus: ");
        player.setCrushBonus(scanner.nextInt());
        System.out.print("Enter your ranged bonus: ");
        player.setRangedBonus((scanner.nextInt()));
        System.out.print("Enter your ranged level: ");
        int baseRangedLevel = scanner.nextInt();
        int pottedRangedLevel = (int) (baseRangedLevel * 1.1 +4);
        player.setRangedLevel(pottedRangedLevel);
        System.out.print("Enter your attack level: ");
        int baseAttackLevel = scanner.nextInt();
        int pottedAttackLevel = (int) (baseAttackLevel * 1.15 + 5);
        player.setAttackLevel(pottedAttackLevel);
        System.out.print("Enter your max hit with the dragon warhammer: ");
        player.setMaxHit(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Are you using rigour? (Y/N): ");
        String answer = scanner.nextLine();
        if(answer.equals("Y")){
            player.setHasRigour(true);
            answer = "";
        }else{
            player.setHasRigour(false);
            answer = "not";
        }
        System.out.print("Are you using a twisted bow? (Y/N): ");
        String answer2 = scanner.nextLine();
        if(answer2.equals("Y")){
            player.setHasTwistedBow(true);
            answer2 = "";
        }else{
            player.setHasTwistedBow(false);
            answer2 = "not";
        }

        System.out.printf("Your crush bonus has been set at %s and your ranged bonus has been set at %s.",player.getCrushBonus(),player.getRangedBonus());
        System.out.printf("\nYour ranged level while potted has been set to %s.",player.getRangedLevel());
        System.out.printf("\nYour attack level while potted has been set to %s.",player.getAttackLevel());
        System.out.printf("\nYour max hit with the dragon warhammer has been set to %s.",player.getMaxHit());
        System.out.printf("\nYou are %s using rigour.",answer);
        System.out.printf("\nYou are %s using a twisted bow.",answer2);
        System.out.printf("\nYour max attack roll is %s.",player.getMaxRangedAttackRoll());
        System.out.printf("\nJad's max defence roll is %s.",jad.getMaxDefenceRoll());

        double counter = 0;
        double percentage = 0;
        int iterations = 1000000;

        for(int i = 0; i <= iterations; i++){
            if(player.doesAttackHit(player.getMaxRangedAttackRoll(),jad.getMaxDefenceRoll(), false)){
                counter++;
            }
        }
        percentage = counter/iterations;

        System.out.printf("\nYou hit %.2f percent of your attacks.",percentage*100);

        counter = 0;

        for (int i = 0; i < iterations; i++) {
            if(player.doesAttackHit(player.getMaxMeleeAttackRoll(),jad.getMaxDefenceRoll(),true)){
                counter++;
            }
        }
        percentage = counter/iterations;

        System.out.printf("\nYou hit %.2f percent of your hammers in inquisitor's.",percentage*100);
    }
}
