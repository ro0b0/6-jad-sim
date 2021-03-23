public class Simulation {
    public static void Sim(){
        //Player player = Player.createPlayer();
        Player player = Player.defaultPlayerTbowRigour();
        Jad jad = Jad.defaultJad();

        player.printPlayerInfo();
        jad.printJadInfo();

        simTwistedBowHits(player,jad);
        simDwhHits(player,jad);
    }
    private static void simTwistedBowHits(Player player, Jad jad){
        double counter = 0;
        int iterations = 1000000;

        for(int i = 0; i <= iterations; i++){
            if(player.doesAttackHit(player.getMaxRangedAttackRoll(),jad.getMaxDefenceRoll(), false)){
                counter++;
            }
        }
        double percentage = (counter/iterations) * 100;

        System.out.printf("\nYou hit %.2f percent of your attacks.",percentage);
    }

    private static void simDwhHits(Player player, Jad jad) {
        double counter = 0;
        int iterations = 1000000;

        for (int i = 0; i < iterations; i++) {
            if(player.doesAttackHit(player.getMaxMeleeAttackRoll(),jad.getMaxDefenceRoll(),true)){
                counter++;
            }
        }
        double percentage = (counter/iterations) * 100;

        System.out.printf("\nYou hit %.2f percent of your hammers in inquisitor's.",percentage);
    }
}
