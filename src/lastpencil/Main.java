package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String name1 = "Melanie";
    private static final String name2 = "Wally";

    public static void main(String[] args) {
        System.out.println("How many pencils would you like to use:");
        int totalNumber = startGame();
        System.out.printf("Who will be the first (%s, %s):%n", name1, name2);
        String currentPlayer = getFirstPlayer(name1, name2);
        printPencils(totalNumber);

        while (totalNumber > 0) {
            System.out.printf(currentPlayer.equals(name1) ? "%s's turn!%n" : "%s's turn:%n" , currentPlayer);
            totalNumber = playGame(totalNumber, currentPlayer);
            currentPlayer = currentPlayer.equals(name1) ? name2 : name1;
        }
        scanner.close();
    }

    public static int startGame() {
        int number = 0;
        while (number <= 0) {
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                if (number <= 0) {
                    System.out.println("The number of pencils should be positive");
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        return number;
    }

    public static String getFirstPlayer(String name1, String name2) {
        String input = "";
        while (!input.equalsIgnoreCase(name1) && !input.equalsIgnoreCase(name2)) {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase(name1) && !input.equalsIgnoreCase(name2)) {
                System.out.printf("Choose between '%s' and '%s':%n", name1, name2);
            }
        }
        return input.equalsIgnoreCase(name1) ? name1 : name2;
    }

    public static void printPencils(int number) {
        for (int i = 0; i < number; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    public static int playGame(int number, String currentPlayer) {
        int numInput = currentPlayer.equals(name1) ? playersTurn(number) : botsTurn(number);
        number -= numInput;
        printPencils(number);
        if (number == 0) {
            System.out.printf("%s won!%n", currentPlayer.equals(name1) ? name2 : name1);
        }
        return number;
    }

    public static int playersTurn(int number) {
        int numInput = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine();
            try {
                numInput = Integer.parseInt(input);
                if (numInput >= 1 && numInput <= 3) {
                    if (number - numInput >= 0) {
                        validInput = true;
                    } else {
                        System.out.println("Too many pencils were taken");
                    }
                } else {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
        return numInput;
    }

    public static int botsTurn(int number) {
        int numInput = 0;
        boolean validInput = false;
        Random random = new Random();
        while (!validInput) {
            if ((number - 1)%4 == 0) {
                numInput = random.nextInt(3) + 1;
                System.out.println(numInput);
            } else {
                numInput = number % 4 == 0 && number % 2 == 0 ? 3 : number % 2 == 0 && number % 4 != 0 ? 1 : 2;
                System.out.println(numInput);
            }
            validInput = true;
        }
        return numInput;
    }
}
