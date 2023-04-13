package OOP.ec22819.MP;//import java.util.List;

import java.util.Random;
import java.util.Scanner;

class Room_ec22819 extends Room {
    Scanner scanner = new Scanner(System.in);

    // Items
    static final Item muffin = new Item("Muffin");
    static final Item toiletpaper = new Item("toiletpaper");
    static final Item gold = new Item("gold");

    // Variables
    String message = "Choose your options (a ,b ,c ,d)";
    char[] arrayOfPossibleChoices = {'a', 'b', 'c', 'd'};
    char choice;
    String y_n;
    Item[] visitorStuff = new Item[5];
    Visitor v;

    /*Direction */
    public Direction visit(Visitor v, Direction VisitorArrivesFrom) {
        int counter = 3;
        v.tell("Hi, you have visited my room, find as many golds as you like!");

        choice = v.getChoice(message, arrayOfPossibleChoices);
        ifChoice(choice, v);

        v.tell("Do you want to continue? (y/n)");
        while (v.quitLoop(counter)) {
            choice = v.getChoice(message, arrayOfPossibleChoices);
            ifChoice(choice, v);
            counter--;
        }

        v.tell("You have exited my room");
        return Direction.opposite(VisitorArrivesFrom);
    }

    /* gives options through input */
    public void ifChoice(char choice, Visitor v) {
        if (choice == 'a') {
            v.tell("Wrong place my friend, with that I'll take your gold.");
            v.takeGold(1);
        } else if (choice == 'b') {
            v.tell("looks like you have found something, a MUFFIN");
            v.giveItem(muffin);
        } else if (choice == 'c') {
            v.tell("Do you need to pee?");
            v.giveItem(toiletpaper);
        } else if (choice == 'd') {
            v.tell("You have found 10 pieces of gold !!");
            v.giveGold(10);
        } else {
            v.tell("please input the right options!");
        }
    }

    // Gives Random Character - for searching 
    public char giveRandomChar(char[] arrayOfPossibleChoices) {
        Random random = new Random();
        int random_index = random.nextInt(arrayOfPossibleChoices.length);

        return arrayOfPossibleChoices[random_index];
    }

    // Input String
    public String inputString(String m) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(m);
        answer = scanner.nextLine();
        return answer;
    }
}
