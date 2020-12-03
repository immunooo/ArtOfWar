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
    private int playerAS; //Player's current attack style. 0: Melee, 1: Archery, 2: Cavalry
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
    private int goldReward;
    private int foodReward;

    public Combat(Army playerArmy, Enemy enemyArmy, int terrain, boolean hasCover, int ambushThreshold, int goldReward, int foodReward, int goldCost, int foodCost) {
        this.playerArmy = playerArmy;
        this.enemyArmy = enemyArmy;
        this.terrain = terrain;
        this.hasCover = hasCover;
        this.ambushThreshold = ambushThreshold;
        playerSize = playerArmy.getSize();
        morale = playerArmy.getMorale();
        enemySize = enemyArmy.size;
        this.goldReward = goldReward;
        this.foodReward = foodReward;
        generalName = "General " + enemyArmy.general;
        playerMorale();

        //Subtracts from gold and food when initiating combat.
        playerArmy.setGold(playerArmy.getResources().getGold() - goldCost);
        playerArmy.setFood(playerArmy.getResources().getFood() - foodCost);
    }

    /**
     * Simulates a battle between the player and an enemy Army
     * The main method in the Class
     * @return True if the player wins the battle, false if the player loses.
     */
    public boolean battle() {
        int initialArmySize = playerSize;

        switch(terrain) { //Outputs a description of the terrain
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

        ambush(); //Calls the ambush method

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //While both armies have troops remaining, and the player hasn't chosen to flee, rounds keep playing.
        while(playerSize > 0 && enemySize > 0 && !hasFled) {
            playRound();
        }

        if(hasFled)  //If the player has fled, the combat is ended and the players resources is updated.
            return false;

        //Updates the player's Army size and Morale to their respective values at the end of combat.

        double percentLeft = (double)playerSize / (double)initialArmySize * 100; //The percent of troops lost

        if(percentLeft >= 90) //Adds a morale boost based on number of troops remaining
            morale += 25;
        else if(percentLeft >= 75) {
            morale += 10;
        } else if(percentLeft >= 50)
            morale += 5;
        else if(percentLeft < 15) //Loses morale with heavy losses
            morale -= 10;
        if(morale > 100) //Maxes out at 10 of course
            morale = 100;
        if(morale < 5) //Minimum of 5 morale if you win the battle
            morale = 5;

        playerArmy.setSize(playerSize);
        playerArmy.setMorale(morale);

        if(playerSize <= 0 && enemySize <= 0) //Both army = 0. Tie goes to player
            System.out.println("Your army is lost, but you miraculously live by the skin of your teeth!");
        else if(enemySize <= 0) //Enemy has lost all troops and you have won the battle.
            System.out.println("You have defeated " + generalName + " and his army in battle. Congratulations!");
        else {  //Player has lost all troops and looses the battle.
            System.out.println(generalName + "'s army has defeated you in battle. He had the high ground!");
            return false;
        }
        //Displays the amount of food and supplies the player receives for winning
        if(foodReward != 0 && goldReward != 0)
            System.out.println("After scavenging through the battlefield, you find " + goldReward + " gold and "
                    + foodReward +  " supplies from the war torn field.");
        else if(goldReward != 0)
            System.out.println("After scavenging through the battlefield, you find " + goldReward
                    + " gold from the war torn field.");
        else if(foodReward != 0)
            System.out.println("After scavenging through the battlefield, you find " + foodReward
                    + " supplies from the war torn field.");
        else
            System.out.println("After scavenging through the battlefield, you don't manage to find anything in the remains");

        //Updates the player's resources
        int gold = playerArmy.getResources().getGold() + goldReward;
        //System.out.println("New Gold: " + gold); //For testing
        int food = playerArmy.getResources().getFood() + foodReward;
        //System.out.println("New Food: " + food); //For testing

        playerArmy.setGold(gold);
        playerArmy.setFood(food);

        return true; //Returns true if the player wins
    }

    /**
     * Simulates an ambush before the battle begins.
     */
    public void ambush() {
        if(hasCover) { //If the battlefield has foliage to be able to set up an ambush
            System.out.println("You see bushes and trees throughout the region, perfect for a small army to prepare" +
                    " an ambush. ");
            System.out.println("\nWhat would you like to do?");
            System.out.println("------------------------------");
            System.out.println("\n1. Attempt to set up an Ambush");
            System.out.println("2. Stand your ground and fight the enemy head on");
            System.out.print(">>> ");

            userInput = kb.nextInt();

            if(userInput == 1) { //If the user chooses to attempt to set up an ambush
                System.out.println("You order your troops to take cover in the bushes and wait to strike the enemy " +
                        "as they pass");
                if(playerSize > ambushThreshold) //If the player tries to ambush, but army is too big
                    failedCover = true;
                else //Player army is under threshold, therefore will succeed in ambush
                    inCover = true;

            } else { //Player chooses not to ambush
                System.out.println("You order your men to stand their ground and ready their weapons!");
            }

        } else //Ambush is not available for this battle
            System.out.println("The area is pretty barren and open. Looks like you'll have to attack the enemy head on! ");

        System.out.println("You see " + generalName + "'s army approaching. Prepare for battle!");

        if(userInput == 1) { //If player has chosen to ambush
            if (failedCover) { //Ambush has failed, so the Enemy gets a free attack.
                System.out.println("\n" + generalName + " has spotted your troops and swiftly gives an attack order!");

                enemyAttack = enemyAttack(); //Will be a straight d20 roll multiplied by 2
                playerSize -= enemyAttack;
                playerMorale(); //Updates the morale based on attack

                //System.out.println("Enemy Attack for Ambush: " + enemyAttack); //For testing
                System.out.println("You loose " + enemyAttack + " from the failed ambush!");
            } else { //Ambush has succeeded, so the player gets a free attack.
                System.out.println(generalName + " doesn't notice your soldiers in the thicket. A perfect " +
                                                 "opportunity for an attack!");

                playerAttack = playerAttack(); //will be a straight d20 roll multiplied by 2
                enemySize -= playerAttack;
                playerMorale(); //Updates the morale based on attack

                System.out.println("Your troops take out " + playerAttack + " in the ambush!");
            }
        }
    }

    /**
     * Represents one 'round' of combat
     */
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
                    chooseAttack(); //User chooses to attack
                    break;
                case 2: //Formation Menu
                    changeFormation(); //User chooses to update the army formation
                    userInput = 4; //To prevent the doWhile loop from ending
                    break;
                case 3:
                    flee(); //User chooses to flee, ending combat and loosing the battle
            }

        }while(userInput > 3); //While User has not chosen to flee or attack

        if(userInput == 3) //If user chooses to flee
            return;

        enemyAS = enemyArmy.setNextAttack(); //Chooses the attack style the enemy will use. Used for playerAttack()

        playerAttack = playerAttack(); //Sets the damage the player will do
        enemyAttack = enemyAttack(); //Sets the damage the enemy will do

        //Checks to see if either army will kill more troops than the opposing army has
        //Will set the player attack and/or the enemy attack to the size of the opposite army
        //This is to avoid either army sizes having negative numbers.
        if(playerAttack > enemySize)
            playerAttack = enemySize;
        if(enemyAttack > playerSize)
            enemyAttack = playerSize;

        playerMorale(); //Updates the army morale based on both attacks.

        //Armies take damage
        playerSize -= enemyAttack;
        enemySize -= playerAttack;
        if(playerAttack != 0 || enemyAttack != 0)
            System.out.println("Your army defeats " + playerAttack + " opponents! Although, " + enemyAttack + " " +
                "of your own troops have fallen!");
        else
            System.out.println("Through the clashing of metal, it seems as if no soldier on the battlefield has fallen!");
        //System.out.println("Your troops morale are at: " + morale + "%"); //For Testing
    }

    /**
     * Method to choose an attack style
     */
    public void chooseAttack() {
        System.out.println("\nWhat attack would you like to do?");
        System.out.println("1. Melee");
        System.out.println("2. Archery");
        System.out.println("3. Calvary");
        System.out.println("\n5. Go Back");
        System.out.print(">>> ");
        playerAS = kb.nextInt() - 1;
        //-1 in order to align with the int values of each attack. Melee: 0, Archery: 1, Cavalry: 2
    }

    /**
     * Method to change player army formation
     */
    public void changeFormation() {
        System.out.println("What formation would you like your troops to take?");
        System.out.println("1. Melee Attack Formation");
        System.out.println("2. Archery Bombardment Formation");
        System.out.println("3. Cavalry Charge Formation");
        System.out.println("4. Defensive Stance");
        System.out.println("\n5. Go Back");
        System.out.print(">>> ");
        userInput = kb.nextInt();

        //Sets the player's army formation
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

    /**
     * Method to flee and end combat
     */
    public void flee() {
        System.out.println("You have cowardly fled from battle!");
        userInput = 3; //To ensure that the playRound() method is stopped if the player flees
        hasFled = true;
        playerArmy.setSize(playerSize);
        playerArmy.setMorale(morale);
    }

    /**
     * Rolls a dice for damage
     * @return the dice roll
     */
    public int roll() {
        int diceRoll;
        Random rand = new Random();
        diceRoll = rand.nextInt(20) + 1; //'Rolls' a d20
        return diceRoll;
    }

    /**
     * Calculates the damage of the player based on their attack style, terrain, and formation
     * @return the amount of troops the player will slay
     */
    public int playerAttack() {
        int dice, modifier=0, total;

        if(inCover) { //If player is ambushing
            inCover = false;
            return roll() * 2;
        }
        dice = roll();

        switch(playerAS) { //Modifies the damage based on the player's attack style compared to the enemies attack style
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

        switch(terrain) { //Modifies the damage based on the terrain
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

        switch(playerFormation) { //Modifies the damage based on the current formation
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

        total = dice + modifier; // The final damage being delt

        if(enemyAS == 3) { //If enemy uses a 'full block' the damage you do is halved
            total /= 2;
        }
        if(total < 0) //In case the modifier makes the damage a negative number
            total = 0;

        return total;
    }

    /**
     * Method to update the player army's morale
     */
    public void playerMorale() {
        //Decreases the morale based on how much damage the enemy does to the player
        if(enemyAttack > 30)
            morale -= 20;
        else if(enemyAttack > 20)
            morale -= 15;
        else if(enemyAttack > 10)
            morale -= 5;
        else if(enemyAttack == 0)
            morale += 1;

        //Increases the morale based on how much damage the player does to the enemy
        if(playerAttack > 30)
            morale += 15;
        else if(playerAttack > 20)
            morale += 5;
        else if(playerAttack == 0)
            morale -= 1;

        //Makes sure that the morale maxes at 100 and mins at 0
        if(morale > 100)
            morale = 100;
        if(morale < 0)
            morale = 0;

        //Updates what the player will see depending on their current morale level
        if(morale >= 90)
            moraleLevel = "Your troops are in high hopes, they seem eager to engage in combat!";
        else if(morale > 75)
            moraleLevel = "Your troops are in good spirits, they stand firm!";
        else if(morale > 50)
            moraleLevel = "Your troops look uneasy, murmurs begin to formulate throughout them.";
        else if(morale > 25)
            moraleLevel = "Your troops are growing angry at your leadership";
        else if(morale == 0) {
            System.out.println("Battered and beaten, your remaining troops throw down their armaments and abandon the battle.");
            playerAttack = 0;
            enemyAttack = 0;
            playerSize = 0; //Troops leave so the player's army size is set to 0
        }
        else // 25 > x > 0
            moraleLevel = "Your troops are becoming discouraged in the battlefield. You can tell that they won't remain loyal if the tide of battle doesn't turn!";

        //System.out.println("End of Morale method"); //For testing purposes
    }

    /**
     * Method for calculating the enemy attack
     * @return the damage the enemy will do to the player
     */
    public int enemyAttack() {
        int dice, modifier=0, total;

        if(failedCover) { //Specific for the enemies opening attack if the player fails an ambush. Only for ambush
            failedCover = false;
            return roll() * 2;
        }
        dice = roll();

        switch(enemyAS) { //Calculates the modifier based on the enemies attack style compared to the player's
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

        switch(terrain) { //Calculates the enemies modifier based on the terrain
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

        switch(playerFormation) { //Calculates the enemy's modifier based on the player's formation
            case 0: //Melee Formation
                if(enemyAS == 1)
                    modifier += 10; //Plus 10 if Player is in Archery Form
                break;
            case 1:
                if(enemyAS == 2)
                    modifier += 10; //Plus 10 if Player is in Archery Form
                break;
            case 2:
                if(enemyAS == 0)
                    modifier += 10; //Plus 10 if Player is in Cavalry Form
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

        if(total < 0) //In case the modifier makes the total negative
            total = 0;

        return total;
    }

    // Getters&Setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Army getPlayerArmy() {
        return playerArmy;
    }

    public void setPlayerArmy(Army playerArmy) {
        this.playerArmy = playerArmy;
    }

    public Enemy getEnemyArmy() {
        return enemyArmy;
    }

    public void setEnemyArmy(Enemy enemyArmy) {
        this.enemyArmy = enemyArmy;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public String getMoraleLevel() {
        return moraleLevel;
    }

    public void setMoraleLevel(String moraleLevel) {
        this.moraleLevel = moraleLevel;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public int getEnemySize() {
        return enemySize;
    }

    public void setEnemySize(int enemySize) {
        this.enemySize = enemySize;
    }

    public int getPlayerAS() {
        return playerAS;
    }

    public void setPlayerAS(int playerAS) {
        this.playerAS = playerAS;
    }

    public int getEnemyAS() {
        return enemyAS;
    }

    public void setEnemyAS(int enemyAS) {
        this.enemyAS = enemyAS;
    }

    public int getPlayerFormation() {
        return playerFormation;
    }

    public void setPlayerFormation(int playerFormation) {
        this.playerFormation = playerFormation;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public boolean isInCover() {
        return inCover;
    }

    public void setInCover(boolean inCover) {
        this.inCover = inCover;
    }

    public boolean isFailedCover() {
        return failedCover;
    }

    public void setFailedCover(boolean failedCover) {
        this.failedCover = failedCover;
    }

    public int getAmbushThreshold() {
        return ambushThreshold;
    }

    public void setAmbushThreshold(int ambushThreshold) {
        this.ambushThreshold = ambushThreshold;
    }

    public Scanner getKb() {
        return kb;
    }

    public void setKb(Scanner kb) {
        this.kb = kb;
    }

    public int getUserInput() {
        return userInput;
    }

    public void setUserInput(int userInput) {
        this.userInput = userInput;
    }

    public String getGeneralName() {
        return generalName;
    }

    public void setGeneralName(String generalName) {
        this.generalName = generalName;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    public int getEnemyAttack() {
        return enemyAttack;
    }

    public void setEnemyAttack(int enemyAttack) {
        this.enemyAttack = enemyAttack;
    }

    public boolean isHasFled() {
        return hasFled;
    }

    public void setHasFled(boolean hasFled) {
        this.hasFled = hasFled;
    }

    public static class Enemy {
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
        Enemy enemy = new Enemy(10, 0, "Kenobi");
        Army player = new Army(100, 30, 40, 60);
        Army beforeBattle = new Army(player.getSize(), player.getMorale(), player.getResources().getGold(), player.getResources().getFood());

        Combat combat = new Combat(player, enemy, 1, true, 50, 20, 30, 5, 10);
        combat.battle();

        System.out.println("Before Battle: " + beforeBattle);
        System.out.println("After Battle: " + player);

    }
}
