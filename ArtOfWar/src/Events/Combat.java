package Events;

import Capital.*;

import java.util.Random;
import java.util.Scanner;

/**
 * Combat: A class that creates and simulates a battle controlled by the player
 *
 * @author Nathan Potraz
 * @version  1.0
 */

public class Combat {

    private Army playerArmy;
    private Enemy enemyArmy;
    private int playerSize;
    private int enemySize;
    private int playerAS; // 0: Melee, 1: Archery, 2: Cavalry, 3: Full Block
    private int enemyAS;
    private int attackLevel;
    private int defenseLevel;
    private int terrain; //0: Plains, 1: Valley, 2: Hill
    private boolean hasCover = false; //True allows army to ambush, False prevents it
    private boolean inCover = false;
    private boolean failedCover = false;
    private int ambushTreshhold;

    public Combat(Army playerArmy, Enemy enemyArmy, int terrain, boolean hasCover, int ambushTreshhold) {
        this.playerArmy = playerArmy;
        this.enemyArmy = enemyArmy;
        this.terrain = terrain;
        this.hasCover = hasCover;
        this.ambushTreshhold = ambushTreshhold;
        playerSize = playerArmy.getSize();
        enemySize = enemyArmy.size;

        //Default values of attack and defense should be 50-50
        attackLevel = playerSize / 2;
        defenseLevel = playerSize - attackLevel;
    }

    public void battle() {
        Scanner kb = new Scanner(System.in);
        int userInput = -1;
        String generalName = "General " + enemyArmy.general;
        int playerAttack = 0;
        int enemyAttack = 0;

        switch(terrain) {
            case 0:
                System.out.println("Your army arrives to the plains. The ground is very leveled, with hardly any " +
                        "changes to elevation. ");
                break;
            case 1:
                System.out.println("Your army arrives to the valley. High mountains surround you, leaving a tight " +
                        "path from where you came from to where the the enemy will be coming from. ");
                break;
            case 2:
                System.out.println("Your army arrives to the field. Hills of varying sizes are scattered throughout  " +
                        "the area. ");
                break;
        }

        if(hasCover) {
            System.out.println("You see bushes and trees throughout the region, perfect for a small army to prepare" +
                    " an ambush. ");
            System.out.println("\nWhat would you like to do?");
            System.out.println("------------------------------");
            System.out.println("\n1. Attempt to set up an Ambush");
            System.out.println("2. Stand your ground and fight the enemy head on");
            System.out.print(">>> ");
            userInput = kb.nextInt();

            if(userInput == 1) {
                System.out.println("You order your troops to take cover in the bushes and wait to strike the enemy " +
                        "as they pass");
                //If the player tries to ambush, but army is too big
                if(playerArmy.getSize() > ambushTreshhold)
                    failedCover = true;
                else
                    inCover = true;

            } else {
                System.out.println("You order your men to stand their ground and ready their weapons!");
            }

        } else
            System.out.println("The area is pretty barren and open. Looks like you'll have to attack the enemy head on! ");

        System.out.println("You see " + generalName + "'s army approaching. Prepare for battle!");

        if(userInput == 1) {
            if (failedCover) {
                System.out.println(generalName + " has spotted your troops and swiftly gives an attack order!");
                int troopsLost;
                troopsLost = enemyAttack();
                playerSize -= troopsLost;
                System.out.println("You loose " + troopsLost + " from the failed ambush!");
                attackLevel = playerSize / 2;
                defenseLevel = playerSize - attackLevel;
            } else {
                System.out.println(generalName + " doesn't notice your soldiers in the thicket. A perfect " +
                        "opportunity for an attack!");
                enemySize -= playerAttack();
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while(playerSize > 0 && enemySize > 0) {

            do {
                System.out.println("\n==================================");
                System.out.println("Your army size: " + playerSize);
                System.out.println(generalName + "'s army size: " + enemySize);
                System.out.println("==================================");

                System.out.println("\nWhat would you like to do?");
                System.out.println("1. Attack enemy army");
                System.out.println("2. Command your army");
                System.out.println("3. Flee");
                System.out.print(">>> ");
                userInput = kb.nextInt();

                switch (userInput) {
                    case 1:
                        System.out.println("\nHow would you like to attack?");
                        System.out.println("1. Warriors");
                        System.out.println("2. Archers");
                        System.out.println("3. Cavalry");
                        System.out.println("4. Full Defensive");
                        System.out.println("\n5. Go Back");
                        System.out.print(">>> ");
                        userInput = kb.nextInt();

                        switch(userInput) {
                            case 1:
                                playerAS = 0;
                                break;
                            case 2:
                                playerAS = 1;
                                break;
                            case 3:
                                playerAS = 2;
                                break;
                            case 4:
                                playerAS = 3;
                                break;
                            default:
                                userInput = 5;
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Current Army Setup:      Attacking: " + attackLevel + "       Defending: " + defenseLevel);
                        System.out.println("\n1. Change Attackers");
                        System.out.println("2. Change Defenders");
                        System.out.println("\n5. Go Back");
                        System.out.print(">>> ");
                        userInput = kb.nextInt();

                        switch(userInput) {
                            case 1:
                                System.out.println("How many troops would you like to allocate to attacking?");
                                System.out.print(">>> ");
                                userInput = kb.nextInt();

                                if(userInput > playerSize)
                                    System.out.println("You don't have the necessary number of troops!");
                                else {
                                    attackLevel = userInput;
                                    defenseLevel = playerSize - attackLevel;
                                }

                                userInput = 5;
                                break;
                            case 2:
                                System.out.println("How many troops would you like to allocate to defending?");
                                System.out.print(">>> ");
                                userInput = kb.nextInt();

                                if(userInput > playerSize)
                                    System.out.println("You don't have the necessary number of troops!");
                                else {
                                    defenseLevel = userInput;
                                    attackLevel = playerSize - defenseLevel;
                                }

                                userInput = 5;
                                break;
                            default:
                                userInput = 5;
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("You have cowardly fled from battle!");
                        playerArmy.setSize(playerSize);
                        return;
                }
            }while(userInput == 5);
            enemyAS = enemyArmy.setNextAttack();

            playerAttack = playerAttack();
            enemyAttack = enemyAttack();

            if(playerAttack > enemySize)
                playerAttack = enemySize;
            if(enemyAttack > playerSize)
                enemyAttack = playerSize;

            playerSize -= enemyAttack;
            enemySize -= playerAttack;

            System.out.println("Your army defeats " + playerAttack + " opponents! Although, " + enemyAttack + " " +
                    "of your own troops have fallen!");
        }

        playerArmy.setSize(playerSize);

        if(playerSize == 0)
            System.out.println(generalName + "'s army has defeated you in battle. You suck");
        else if(enemySize == 0)
            System.out.println("You have defeated " + generalName + " and his army in battle. Congratulations!");

    }

    public int roll() {
        int diceRoll;
        Random rand = new Random();
        diceRoll = rand.nextInt(20) + 1; //'Rolls' a d20
        return diceRoll;
    }

    public int playerAttack() {
        int dice, modifier=0, total;
        if(inCover) {
            inCover = false;
            return roll() * 2;
        }
        dice = roll();

        switch(playerAS) {
            case 0: //melee
                if(enemyAS == 1) //Melee has 'disadvantage' against Archers
                    modifier -= 10;
                else if(enemyAS == 2) //Melee has 'advantage' against Cavalry
                    modifier += 5;
                break;
            case 1: //archery
                if(enemyAS == 0) //Archers has 'advantage' against Melee
                    modifier += 5;
                else if(enemyAS == 2) //Archers has 'disadvantage' against Cavalry
                    modifier -= 10;
                break;
            case 2: //calvary
                if(enemyAS == 0) //Cavalry has 'disadvantage' against Melee
                    modifier -= 10;
                else if(enemyAS == 1) //Cavalry has 'advantage' against Archers
                    modifier += 5;
                break;
            case 3: //full block
                return 0;
        }

        switch(terrain) {
            case 0: //plains
                if(playerAS == 0) //Melee have 'disadvantage' on plains
                    modifier -= 5;
                else if(playerAS == 2) //Cavalry have 'advantage' on plains
                    modifier += 5;
                break;
            case 1: //valley
                if(playerAS == 2) //Cavalry have 'disadvantage' in Valleys
                    modifier -= 5;
                else if(playerAS == 1) //Archers have 'advantage' in Valleys
                    modifier += 5;
                break;
            case 2: //hills
                if(playerAS == 0) //Melee has 'advantage' on Hills
                    modifier += 5;
                else if(playerAS == 1) //Archers have 'disadvantage' on Hills
                    modifier -= 5;
                break;
        }

        if(attackLevel >= (playerSize * .75))
            modifier += 5;
        else if(attackLevel <= (playerSize *.25))
            modifier -= 10;

        total = dice + modifier;
        ;
        if(enemyAS == 3) { //If enemy uses a 'full block' the damage you do is halved
            System.out.println("Before block: " + total);
            total /= 2;
            System.out.println("After Block: " + total);
        }
        if(total < 0)
            total = 0;

        return total;
    }

    public int enemyAttack() {
        int dice, modifier=0, total;

        if(failedCover) { //Specific for the enemies opening attack if the player fails an ambush
            failedCover = false;
            return roll() * 2;
        }
        dice = roll();

        switch(enemyAS) {
            case 0: //melee
                if(playerAS == 1) //Melee has 'disadvantage' against Archers
                    modifier -= 5;
                else if(playerAS == 2) //Melee has 'advantage' against Cavalry
                    modifier += 10;
                break;
            case 1: //archery
                if(playerAS == 0) //Archers has 'advantage' against Melee
                    modifier += 10;
                else if(playerAS == 2) //Archers has 'disadvantage' against Cavalry
                    modifier -= 5;
                break;
            case 2: //calvary
                if(playerAS == 0) //Cavalry has 'disadvantage' against Melee
                    modifier -= 5;
                else if(playerAS == 1) //Cavalry has 'advantage' against Archers
                    modifier += 10;
                break;
            case 3: //full block
                return 0;
        }

        switch(terrain) {
            case 0: //plains
                if(enemyAS == 0) //Melee have 'disadvantage' on plains
                    modifier -= 5;
                else if(enemyAS == 2) //Cavalry have 'advantage' on plains
                    modifier += 5;
                break;
            case 1: //valley
                if(enemyAS == 2) //Cavalry have 'disadvantage' in Valleys
                    modifier -= 5;
                else if(enemyAS == 1) //Archers have 'advantage' in Valleys
                    modifier += 5;
                break;
            case 2: //hills
                if(enemyAS == 0) //Melee has 'advantage' on Hills
                    modifier += 5;
                else if(enemyAS == 1) //Archers have 'disadvantage' on Hills
                    modifier -= 5;
                break;
        }

        if(defenseLevel >= (playerSize * .75))
            modifier -= 5;
        else if(defenseLevel < (playerSize *.25))
            modifier += 10;
        else
            modifier += 5; //Just because I think the player would be incredibly overpowered otherwise

        total = dice + modifier;

        if(playerAS == 3) //If player uses a 'full block' the damage the enemy does is halved
            total /= 2;
        if(total < 0)
            total = 0;

        return total;
    }

    public void setPlayerAS(int a) {
        playerAS = a;
    }
    public int getPlayerAS() {
        return playerAS;
    }

    private static class Enemy {
        private int size;
        private int attackStyle;
        private String general;

        public Enemy(int size, int attackStyle, String general) {
            this.size = size;
            this.general = general;

            if(attackStyle < 0 || attackStyle > 2)
                throw new IllegalArgumentException("Attack Style must be 0, 1, or 2!");
            else
                this.attackStyle = attackStyle;
        }

        public int setNextAttack() {
            Random rand = new Random();
            int dice = rand.nextInt(10);
            int attack1, attack2;
            //This sets attack1 and attack2 to the attack styles that the default attack style isn't
            switch(attackStyle) {
                case 0:
                    attack1 = 1;
                    attack2 = 2;
                    break;
                case 1:
                    attack1 = 0;
                    attack2 = 2;
                    break;
                case 2:
                    attack1 = 0;
                    attack2 = 1;
                    break;
                default:
                    attack1 = 3;
                    attack2 = 3;
                    break;
            }

            switch(dice) {
                case 0:
                case 1:
                    System.out.println("Enemy uses Block");
                    return 3; //Returns 'All Block' if dice is 0 or 2
                case 2:
                case 3:
                    System.out.println("Enemy uses Archers");
                    return attack1; //Returns attack 1 if rolls 2 or 3;
                case 4:
                    System.out.println("Enemy uses Cavalry");
                    return attack2; //Returns attack2 if rolls 4
                default:
                    System.out.println("Enemy uses Melee");
                    return attackStyle; //Returns default attack style if rolls 5, 6, 7, 8, or 9
            }
        }
    }

    public static void main(String[] args) {
        //0: Flat, 1: Valley, 2: Hills
        // 0: Melee, 1: Archery, 2: Cavalry, 3: Full Block
        Enemy enemy = new Enemy(100, 0, "Nathan");
        Army player = new Army(100, 100, 0, 0);

        Combat combat = new Combat(player, enemy, 0, true, 50);
        combat.battle();

    }
}
