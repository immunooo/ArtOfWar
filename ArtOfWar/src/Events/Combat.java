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

    private Army playerArmy; //The player army
    private Enemy enemyArmy; //The enemy army
    private int morale; //The Army's morale;
    private String moraleLevel;
    private int playerSize; //The number of troops in the player army
    private int enemySize; // The number of troops in the enemy army
    private int playerAS; //Player's current attack style. 0: Melee, 1: Archery, 2: Cavalry, 3: Full Block
    private int enemyAS; //The enemies current attack style
    private int playerFormation; //0: Melee Attack, 1: Archer Attack, 2: Calvery Attack 3: Defence Stance
    private int terrain; //0: Plains, 1: Valley, 2: Hill, 3: Wetlands (avoid the word swamp)
    private boolean hasCover = false; //True allows army to ambush, False prevents it
    private boolean inCover = false; //True if army is attempting ambush
    private boolean failedCover = false; //True if army fails the ambush
    private int ambushThreshold; //The number the player army needs to be under in order for a successful
    private Scanner kb = new Scanner(System.in); //Keyboard scanner
    private int userInput = -1; //The current input of the user
    private String generalName; //The name of the General of the Enemy Army
    private int playerAttack = 0; //Damage player will do
    private int enemyAttack = 0; //Damage enemy will do
    private boolean hasFled = false;

    public Combat(Army playerArmy, Enemy enemyArmy, int terrain, boolean hasCover, int ambushThreshold) {
        this.playerArmy = playerArmy;
        this.enemyArmy = enemyArmy;
        this.terrain = terrain;
        this.hasCover = hasCover;
        this.ambushThreshold = ambushThreshold;
        playerSize = playerArmy.getSize();
        morale = playerArmy.getMorale();
        enemySize = enemyArmy.size;
        generalName = "General " + enemyArmy.general;
    }

    public void battle() {
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

        playerMorale();
        ambush();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while(playerSize > 0 && enemySize > 0 && !hasFled) {
            playRound();
        }

        if(hasFled)
            return;

        playerArmy.setSize(playerSize);
        playerArmy.setMorale(morale);

        if(playerSize <= 0)
            System.out.println(generalName + "'s army has defeated you in battle. He had the high ground!");
        else if(enemySize <= 0)
            System.out.println("You have defeated " + generalName + " and his army in battle. Congratulations!");
        else { //Both army = 0. Tie goes to player
            System.out.println("Your army is lost, but you miraculously by the skin of your teeth!");
        }

    }

    public void ambush() {
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
                if(playerSize > ambushThreshold)
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

                enemyAttack = enemyAttack();
                playerSize -= enemyAttack;
                playerMorale();
                System.out.println("Enemy Attack for Ambush: " + enemyAttack);

                System.out.println("You loose " + enemyAttack + " from the failed ambush!");
            } else {
                System.out.println(generalName + " doesn't notice your soldiers in the thicket. A perfect " +
                        "opportunity for an attack!");

                playerAttack = playerAttack();
                enemySize -= playerAttack;
                playerMorale();
                System.out.println("Your troops take out " + playerAttack + " in the ambush!");
            }
        }
    }

    public void playRound() {
        do {
            System.out.println("\n==================================");
            System.out.println("Your army size: " + playerSize);
            System.out.println(generalName + "'s army size: " + enemySize);
            System.out.println(moraleLevel + " (" + morale + ")");
            System.out.println("==================================");

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Attack enemy army");
            System.out.println("2. Command your army");
            System.out.println("3. Flee");
            System.out.print(">>> ");
            userInput = kb.nextInt();

            switch (userInput) {
                case 1:
                    chooseAttack();
                    break;
                case 2: //Formation Menu
                    changeFormation();
                    userInput = 4;
                    break;
                case 3:
                    flee();
            }

        }while(userInput > 3);

        if(userInput == 3)
            return;

        enemyAS = enemyArmy.setNextAttack();

        playerAttack = playerAttack();
        enemyAttack = enemyAttack();

        if(playerAttack > enemySize)
            playerAttack = enemySize;
        if(enemyAttack > playerSize)
            enemyAttack = playerSize;

        playerMorale();

        playerSize -= enemyAttack;
        enemySize -= playerAttack;

        System.out.println("Your army defeats " + playerAttack + " opponents! Although, " + enemyAttack + " " +
                "of your own troops have fallen!");
        //System.out.println("Your troops morale are at: " + morale + "%"); //For Testing
    }

    public void chooseAttack() {
        System.out.println("\nWhat attack would you like to do?");
        System.out.println("1. Melee");
        System.out.println("2. Archery");
        System.out.println("3. Calvary");
        System.out.println("\n5. Go Back");
        System.out.print(">>> ");
        playerAS = kb.nextInt() - 1;
    }

    public void changeFormation() {
        System.out.println("What formation would you like your troops to take?");
        System.out.println("1. Melee Attack Formation");
        System.out.println("2. Archery Bombardment Formation");
        System.out.println("3. Cavalry Charge Formation");
        System.out.println("4. Defensive Stance");
        System.out.println("\n5. Go Back");
        System.out.print(">>> ");
        userInput = kb.nextInt();

        switch(userInput) {
            case 1:
                System.out.println("Your troops are now in the Melee Attack Formation!");
                playerFormation = 0;
                userInput = 5;
                break;
            case 2:
                System.out.println("Your troops are now in the Archery Bombardment Formation!");
                playerFormation = 1;
                userInput = 5;
                break;
            case 3:
                System.out.println("Your troops are now in the Cavalry Charge Formation!");
                playerFormation = 2;
                userInput = 5;
                break;
            case 4:
                System.out.println("Your troops have taken a Defensive Stance!");
                playerFormation = 3;
                userInput = 5;
                break;
            default:
                userInput = 5;
                break;
        }
    }



    public void flee() {
        System.out.println("You have cowardly fled from battle!");
        userInput = 3; //To ensure that the playRound() method is stopped if the player flees
        hasFled = true;
        playerArmy.setSize(playerSize);
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

        switch(playerFormation) {
            case 0: //Melee Formation
                if(playerAS == 0)
                    modifier += 5; //Plus 5 to Melee attacks if in Melee Form
                break;
            case 1:
                if(playerAS == 1)
                    modifier += 5; //Plus 5 to Archery attacks if in Archery Form
                break;
            case 2:
                if(playerAS == 2)
                    modifier += 5; //Plus 5 to Cavalry attacks if in Cavalry Form
                break;
            case 3:
                modifier = 0; //If in Defense Stance, No modifier
                dice -= 5; //Max of 15 damage in Defensive stance
                break;
            default:
                break;
        }

        total = dice + modifier;

        if(enemyAS == 3) { //If enemy uses a 'full block' the damage you do is halved
            total /= 2;
        }
        if(total < 0)
            total = 0;

        return total;
    }

    public void playerMorale() {
        if(enemyAttack > 30)
            morale -= 20;
        else if(enemyAttack > 20)
            morale -= 15;
        else if(enemyAttack > 10)
            morale -= 5;
        else if(enemyAttack == 0)
            morale += 1;

        if(playerAttack > 30)
            morale += 15;
        else if(playerAttack > 20)
            morale += 5;
        else if(playerAttack == 0)
            morale -= 1;

        if(morale > 100)
            morale = 100;
        if(morale < 0)
            morale = 0;

        if(morale >= 90)
            moraleLevel = "Your troops are in high hopes, they seem eager to engage in combat!";
        else if(morale > 75)
            moraleLevel = "Your troops are in good spirits, they stand firm!";
        else if(morale > 50)
            moraleLevel = "Your troops look uneasy, murmurs begin to formulate throughout them.";
        else if(morale > 25)
            moraleLevel = "Your troops are growing angry at your leadership";
        else if(morale == 0) {
            System.out.println("Battered and beaten, your remaining troops throw down there armaments and abandon the battle.");
            playerAttack = 0;
            enemyAttack = 0;
            playerSize = 0;
        }
        else
            moraleLevel = "Your troops are becoming discouraged in the battlefield. You can tell that they won't remain loyal if the tide of battle doesn't turn!";

        System.out.println("End of Morale method");
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

        switch(playerFormation) {
            case 0: //Melee Formation
                if(enemyAS == 1)
                    modifier += 10; //Plus 10 to Player if Player is in Archery Form
                break;
            case 1:
                if(enemyAS == 2)
                    modifier += 10; //Plus 10 to Player if Player is in Archery Form
                break;
            case 2:
                if(enemyAS == 0)
                    modifier += 10; //Plus 10 to Player if Player is in Cavalry Form
                break;
            case 3:
                //Half damage if the player is in defensive stance
                modifier /= 2;
                dice /= 2;
                break;
            default:
                break;
        }

        total = dice + modifier;

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
                    //System.out.println("Enemy uses Block");
                    return 3; //Returns 'All Block' if dice is 0
                case 1:
                case 2:
                    //System.out.println("Enemy uses Archers");
                    return attack1; //Returns attack 1 if rolls 1 or 2
                case 3:
                case 4:
                    //System.out.println("Enemy uses Cavalry");
                    return attack2; //Returns attack2 if rolls 3 or 4
                default:
                    //System.out.println("Enemy uses Melee");
                    return attackStyle; //Returns default attack style if rolls 5, 6, 7, 8, or 9
            }
        }
    }

    public static void main(String[] args) {
        //0: Flat, 1: Valley, 2: Hills, 3: Wetlands
        // 0: Melee, 1: Archery, 2: Cavalry, 3: Full Block
        Enemy enemy = new Enemy(100, 0, "Kenobi");
        Army player = new Army(25, 100, 0, 0);

        Combat combat = new Combat(player, enemy, 1, true, 50);
        combat.battle();

    }
}
