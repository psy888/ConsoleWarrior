package com.psy888;

import java.util.Scanner;

public class Main {

    int currentMove; //текущий ход 0 или 1

    public static void main(String[] args) {
	// write your code here

    }

    public void makeMove(){
//        currentMove = whoIsNext();

    }

    public void whoIsNext(){

        this.currentMove = (int) (Math.random() * 2);
    }

    /*
        0 - light damage
        1 - max damage
        2 - heal
        ( Math.random() * (b-a) ) + a
     */
    public int attack(boolean lessThan35){
        int hp = 0;

        int chance = (int) (Math.random() * 3);

        if (lessThan35){
            // add 35 % of luck
        }
        int min;
        int max;
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

        //todo процентовка исходов

        return hp;
    }

    public void startGame(){

        //инициализация игрока Пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше Имя : ");
        String name = scanner.next();
        Person user =new Person(name);

        //инициализация игрока Компютер
        Person pc = new Person("Computer");


        System.out.println("Игра началась!");
        //todo вставить текстовые картинки
        do {
            //game process here..
            System.out.println("Следующий ход игрока :");
            whoIsNext(); // определить за кем следующий ход
            System.out.println((currentMove>0)?pc.getName():user.getName()); //1 - pc , 0 - user
            System.out.println("\n---------------------------------------------------------------------------");
            if (currentMove>0) //pc
            {

                user.setHP(user.getHP() - attack(pc.getHP()<35));
            }else{ // user
                pc.setHP(pc.getHP() - attack(false));
            }

        }while (user.getHP()>0&&pc.getHP()>0);

    }
}
