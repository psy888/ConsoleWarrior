package com.psy888;

import java.util.Scanner;
import com.psy888.Person;

public class Main {

    static int currentMove; //текущий ход 0 или 1
    static int[] attackCnt; // Кол -во атак
    static int[] successAttackCnt; // Кол -во атак
    static int[] damageCnt; // Общее кол во нанесенного урона


    public static void main(String[] args) {
	// write your code here

        startGame();
    }


    /**
     * определить следующий ход
     */
    public static void whoIsNext(){
        currentMove = (int) (Math.random() * 2);
    }

    /*
        0 - light damage
        1 - max damage
        2 - heal
        ( Math.random() * (b-a) ) + a
     */
    public static int attack(boolean lessThan35){
        int hp = 0;
        int lightDmg = (lessThan35)?20:40;
        int maxDmg = (lessThan35)?20:40;
        int action;

        int chance = (int) (Math.random() * 100);
//        System.out.println("Chance = " + chance);
        if(chance < lightDmg) // light damage 40%
        {
            action = 0;
        }
        else if(chance < maxDmg+lightDmg) // max damage 40%
        {
            action = 1;
        }
        else  // heal 20%
        {
            action = 2;
        }

        int min = 0;
        int max = 0;
        boolean sign = true; //true -> " - " ; false -> " + "
        switch (action)
        {
            case 2: //heal
                sign = false;
            case 0: // avg damage
                min = 18;
                max = 25;
                break;
            case 1: // wide damage
                min = 10;
                max = 35;
                break;
        }

        hp = (int)(Math.random() * (max-min)  + min);


        hp = (sign)?hp*-1:hp; //heal or damage

        return hp;
    }

    public static void startGame(){

        //инициализация игрока Пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name : ");
        String name = scanner.next();
        Person user =new Person(name);

        //инициализация игрока Компютер
        Person pc = new Person("Computer");


        Person[] player = new Person[]{user,pc};
        //инициализация счетчиков
        attackCnt = new int[player.length];
        successAttackCnt = new int[player.length];
        damageCnt = new int[player.length];

        System.out.println("Game started!");
        do {
            //game process here..
            whoIsNext(); // определить за кем следующий ход
//            System.out.println("Player " + player[currentMove].getName()); //1 - pc , 0 - user
            System.out.println("***************************************************************************************");
            System.out.println("cxxxxx][===============>");
            System.out.println("Player " +  player[currentMove].getName() + " attacks!");
            System.out.println("cxxxxx][===============>");
            System.out.println("***************************************************************************************\n\n");

            int attackResult = 0;
            //attack
            if (currentMove>0) //pc
            {
                attackResult = attack(false);
                player[0].setHP(player[0].getHP() + attackResult);
            }else{ // user
                attackResult = attack(player[1].getHP()<35);
                player[1].setHP(player[1].getHP() + attackResult);
            }
            //results
            if(attackResult>0){
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Player " +  player[currentMove].getName() + " miss and spills a healing potion on an adversary\n" +
                        "Opponent gets extra +" + attackResult + " heal points.");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

            }else{
                //общий урон
                damageCnt[currentMove]+=attackResult;
                successAttackCnt[currentMove]++;
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("Player " +  player[currentMove].getName() + " strikes and robs the enemy " + attackResult +
                        " heal points.");
                System.out.println("---------------------------------------------------------------------------------------\n\n");
            }

            attackCnt[currentMove]++;

            //промежуточный результат
            System.out.println("\n******************************** Results : *****************************************\n");
            for (int i = 0; i < player.length; i++) {

                System.out.println("Player :" + player[i].getName() + " | Health : " + player[i].getHP() + " points.");
                System.out.println("\t\t-========-");

            }
            System.out.println("\n***************************************************************************************\n\n");

            //Задержка 3 с
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {/*ignore*/}
        }while (user.getHP()>0&&pc.getHP()>0);
        Person winner = (player[0].getHP()>0)?player[0]:player[1];
        System.out.println("\n******************************** Game Over ! *******************************************\n");
        System.out.println( winner.getName() + "  WINS! \n Health left : " + winner.getHP() + " points.");

        System.out.println("************************************** Stats : *******************************************");
        for (int i = 0; i < player.length; i++) {
            System.out.println("Player " + player[i].getName() +
                    "\n Attacks total : " + attackCnt[i] + " (of which are successful : " + successAttackCnt[i] + ")\n" +
                    "Total damage done : " + (damageCnt[i]*-1) + " points." );
        }

        System.out.println("\n***************************************************************************************\n\n");
    }
}
