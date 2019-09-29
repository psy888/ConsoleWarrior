package com.psy888;

import java.util.Scanner;

public class Main {

    static int currentMove; //текущий ход 0 или 1
    static int[] attackCnt; // Кол -во атак
    static int[] successAttackCnt; // Кол -во атак
    static int[] damageCnt; // Общее кол во нанесенного урона


    public static void main(String[] args) {
	// write your code here

        startGame();
    }

    public void makeMove(){
//        currentMove = whoIsNext();

    }

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

        int chance = (int) (Math.random() * 3);

        if (lessThan35){
            // add 35 % of luck
        }
        int min = 0;
        int max = 0;
        boolean sign = true; //true -> " - " ; false -> " + "
        switch (chance)
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
        //todo процентовка исходов

        hp = (sign)?hp*-1:hp; //heal or damage

        return hp;
    }

    public static void startGame(){

        //инициализация игрока Пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше Имя : ");
        String name = scanner.next();
        Person user =new Person(name);

        //инициализация игрока Компютер
        Person pc = new Person("Computer");


        Person[] player = new Person[]{user,pc};
        //инициализация счетчиков
        attackCnt = new int[player.length];
        successAttackCnt = new int[player.length];
        damageCnt = new int[player.length];

        System.out.println("Игра началась!");
        //todo вставить текстовые картинки
        do {
            //game process here..
            System.out.println("Следующий ход игрока :");
            whoIsNext(); // определить за кем следующий ход
            System.out.println(player[currentMove].getName()); //1 - pc , 0 - user
            System.out.println("***************************************************************************************");
            System.out.println("Игрок " +  player[currentMove].getName() + " атакует!");
            System.out.println("***************************************************************************************");

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
                System.out.println("Игрок " +  player[currentMove].getName() + " промахивается и розливает исцеляющее зелье на противника\n" +
                        "Противник получает дополнительно +" + attackResult + " очков здоровья.");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            }else{
                //общий урон
                damageCnt[currentMove]+=attackResult;
                successAttackCnt[currentMove]++;
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("Игрок " +  player[currentMove].getName() + " наносит удар и отнимает у противника " + attackResult +
                        " очков здоровья.");
                System.out.println("---------------------------------------------------------------------------------------");
            }

            attackCnt[currentMove]++;

            //промежуточный результат
            System.out.println("\n******************************** Результаты : *****************************************\n");
            for (int i = 0; i < player.length; i++) {

                System.out.println("Игрок :" + player[i].getName() + " | Здоровье : " + player[i].getHP() + " очков.");
                System.out.println("\t\t-========-");

            }
            System.out.println("\n***************************************************************************************\n");

            //Задержка 3 с
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {/*ignore*/}
        }while (user.getHP()>0&&pc.getHP()>0);
        Person winner = (player[0].getHP()>0)?player[0]:player[1];
        System.out.println("\n******************************** Игра Окончена ! **************************************\n");
        System.out.println("ПОБЕДИЛ " + winner.getName() + " \n Осталось жизней : " + winner.getHP());

        System.out.println("\n************************************** Статистика : ************************************\n");
        for (int i = 0; i < player.length; i++) {
            System.out.println("Игрок " + player[i].getName() +
                    "\n Общее колличество атак : " + attackCnt[i] + " (из них удачных " + successAttackCnt[i] + ")\n" +
                    "Всего нанесено урона : " + (damageCnt[i]*-1) + " очков." );
        }
        System.out.println("\n***************************************************************************************\n");
    }
}
