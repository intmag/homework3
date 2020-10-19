package ru.geekbrains.homework3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int ATTEMPTS = 3;
    public static final int RANGE = 10;
    public static Random random = new Random();
    public static int randomValue = random.nextInt(RANGE);
    public static boolean exitGame = false;
    public static boolean gameOver = false;
    public static boolean gameWin = false;
    public static int answerCount = 0;

    public static void main(String[] args) throws InterruptedException {
        startTalk();
        guessNumGame();
        bye();
    }

    private static void guessNumGame() throws InterruptedException {

        while (!exitGame && !gameOver) {
            ask();
            typeEnteredValue();
            if (gameOver && !exitGame) {
                stringToChar("Ты использовал все свои попытки", true, true);
                stringToChar("Загаданное число: " + randomValue, true, true);
            }
            if ((gameWin || gameOver) && !exitGame) {
                tryAgain();
            }
        }

    }

    private static void bye() throws InterruptedException {
        if (!gameWin && !exitGame) {
            stringToChar("Не переживай, тебе точно повезет в следующий раз! ;-)", true, true);
        } else if (exitGame && !gameOver && !gameWin) {
            stringToChar("Ну ты заходи если что, поиграем еще...", true, true);
        }
        stringToChar("До встречи!", true, true);
        stringToChar("Пока...........:-)", true, true);
    }

    private static void tryAgain() throws InterruptedException {
        stringToChar("Повторить игру еще раз? 1 – да / 0 – нет", true, true);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            if (scanner.nextInt() == 1) {
                randomValue = random.nextInt(RANGE);
                exitGame = false;
                gameOver = false;
                gameWin = false;
                answerCount = 0;
                guessNumGame();
            } else {
                exitGame = true;
            }
        }
    }


    private static void ask() throws InterruptedException {
        if (answerCount > 0) {
            String str = amountsString(ATTEMPTS - answerCount);
            stringToChar("У тебя осталось " + (ATTEMPTS - answerCount) + " " + str, true, true);
            //stringToChar("Попробуй еще!", true, true);
        }
        stringToChar("Какое число я загадал?", true, true);
    }

    private static String amountsString(int i) {
        String amounts;
        String str = Integer.toString(i);
        str = str.substring(str.length() - 1, str.length());
        switch (Integer.parseInt(str)) {
            case 1:
                amounts = "попытка";
                break;
            case 2:
            case 3:
            case 4:
                amounts = "попытки";
                break;
            default:
                amounts = "попыток";

        }
        return amounts;
    }

    private static void startTalk() throws InterruptedException {
        stringToChar("Привет, меня зовут Ваня!", true, true);
        stringToChar("Сейчас я загадаю число от 0 до "+(RANGE-1)+". У тебя " + ATTEMPTS + ((ATTEMPTS > 4) ? " попыток" : " попытки") + ", чтобы попытаться его отгадать.", true, true);
        stringToChar("Если ты захочешь прекратить играть, напиши моё имя.", true, true);
        stringToChar("Желаю удачи!", true, true);
        stringToChar("Поехали......", true, true);
    }

    private static void chekGameVal(int val, int randomVal) throws InterruptedException {
        if (val == randomVal) {
            stringToChar("Ты победил!!!!!", true, true);
            gameWin = true;
        } else if (answerCount == ATTEMPTS) {
            gameOver = true;
        } else {
            String str = val > randomVal ? " БОЛЬШЕ " : " меньше ";
            stringToChar("Число " + val + str + "чем загаданное.", true, true);
        }
    }

    public static void typeEnteredValue() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            chekValues(scanner.nextInt());
        } else {
            chekValues(scanner.next());
        }
    }

    private static void chekValues(int value) throws InterruptedException {
        if ((value >= 0) && (value <= (RANGE - 1))) {
            answerCount++;
            chekGameVal(value, randomValue);
        } else {
            stringToChar("Введенное число не входит в диапазон от 0 до " + (RANGE - 1) + "! ", true, true);
        }
    }

    private static void chekValues(String value) throws InterruptedException {
        switch (value.toLowerCase()) {
            case "ваня":
                ;
            case "иван":
                ;
            case "ванюша":
                ;
            case "ванечка":
                exitGame = true;
                break;
            default:
                stringToChar("Это не число! :-(", true, true);
                break;
        }
    }

    private static void stringToChar(String str, boolean effects, boolean breakLine) throws InterruptedException {
        if (effects) {
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                System.out.print(ch);
                Thread.sleep(40);
            }
            if (breakLine) {
                System.out.print("\n");
            }
        } else {
            if (breakLine) {
                System.out.println(str);
            } else {
                System.out.print(str);
            }
        }

    }

}