package com.companyCat;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int N = 3;
    static int[][] cell;
    static Scanner scanner;

    public static void main(String[] args) {
        //Написать игру крестики-нолики. В игре должны быть 2 режима :
        // с компьютером (который выполняет ход в произвольную свободную клетку),
        // и с человеком (выполнят ход поочерёдно).
        //Также, должен быть счетчик поражений и побед, отдельно для игры с компьютером,
        // отдельно для игры с человеком.
        //Краткое пояснение для выполнения задания: для начала нужно создать игровое поле -
        // двумерный массив размером 3х3 (например, символов char).
        //Затем для игроков организовать поочерёдное считывание хода и запись их в массив.
        //Пусть для одного игрока - это будет X, а для второго O.
        //Затем, после каждого хода игрока нужно выполнять проверку наличия 3 одинаковых значений в ряд (три Х или О подряд по горизонтали, вертикали и диагонали). Если такая "тройка" найдена, то выигрывает текущий игрок. Иначе - ход следующего игрока.
        int game;
        int gameForrever;
        int human1Count = 0;
        int human2Count = 0;
        int humanCount = 0;
        int compCount = 0;
        int stepCounter = 0;
        cell = new int[N][N];
        scanner = new Scanner(System.in);
        do {
            System.out.println("Если хотите играть с компьютером введите 1, если с человеком - 2");
            game = scanner.nextInt();
            if (game == 1) {
                boolean isWinner = false;
                do {
                    stepCounter++;
                    System.out.println(stepCounter);
                    if (stepCounter <= N * N) {
                        move(stepCounter);
                    } else
                        break;
                    drawField();
                    isWinner = findWinnerMy();
                } while (!isWinner);
                if (isWinner)
                    if (stepCounter % 2 == 1) {
                        System.out.println("Компьютер X - победитель");
                        compCount++;
                        System.out.println("Счет компьютер:человек " + compCount + " : " + humanCount);

                    } else {
                        System.out.println("Человек O - победитель");
                        humanCount++;
                        System.out.println("Счет компьютер:человек " + compCount + " : " + humanCount);
                    }
                else
                    System.out.println("Ничья");
            } else if (game == 2) {

                boolean isWinner = false;
                do {
                    stepCounter++;
                    System.out.println(stepCounter);
                    if (stepCounter <= N * N) {
                        move2human(stepCounter);
                    } else
                        break;
                    drawField();
                    isWinner = findWinnerMy();
                } while (!isWinner);
                if (isWinner) {
                    if (stepCounter % 2 == 1) {
                        System.out.println("Человек1 X - победитель");
                        human1Count++;
                        System.out.println("Счет человек:человек " + human1Count + " : " + human2Count);
                    } else {
                        System.out.println("Человек2 O - победитель");
                        human2Count++;
                        System.out.println("Счет человек:человек " + human1Count + " : " + human2Count);
                    }
                } else
                    System.out.println("Ничья");
            } else break;

            System.out.println("Хотите еще играть? Да = 1, Нет = 0");
            stepCounter = 0;
            eraseField();
            gameForrever = scanner.nextInt();

        } while (gameForrever == 1);
    }

    private static boolean findWinnerMy() {
        //Не могла не оставить эту фунцию  - написание топорное влоб - но для 3 на 3 игры - очень прозрачно
        //выглядит)))) и с ее перечислений было проще строить циклы для версии 2)
        int sumX = 3;
        int sumO = 15;

        if (cell[0][0] + cell[0][1] + cell[0][2] == sumX || cell[0][0] + cell[0][1] + cell[0][2] == sumO ||
                cell[1][0] + cell[1][1] + cell[1][2] == sumX || cell[1][0] + cell[1][1] + cell[1][2] == sumO ||
                cell[2][0] + cell[2][1] + cell[2][2] == sumX || cell[2][0] + cell[2][1] + cell[2][2] == sumO ||
                cell[0][0] + cell[1][0] + cell[2][0] == sumX || cell[0][0] + cell[1][0] + cell[2][0] == sumO ||
                cell[0][1] + cell[1][1] + cell[2][1] == sumX || cell[0][1] + cell[1][1] + cell[2][1] == sumO ||
                cell[0][2] + cell[1][2] + cell[2][2] == sumX || cell[0][2] + cell[1][2] + cell[2][2] == sumO ||
                cell[0][0] + cell[1][1] + cell[2][2] == sumX || cell[0][0] + cell[1][1] + cell[2][2] == sumO ||
                cell[0][2] + cell[1][1] + cell[2][0] == sumX || cell[0][2] + cell[1][1] + cell[2][0] == sumO)

            return true;
        else return false;
    }

    private static boolean findWinnerMy2() {
        int sumX = 3;
        int sumO = 15;
        int sum = 0;
        int i;
        int row, column;

        i = 1;
        for (row = 0; row < N; row++) {
            for (column = N - i; column <= N - i; ) {
                sum += cell[row][column];
                i++;
                break;
            }
        }
        if (sum == sumX || sum == sumO) {
            return true;
        } else {
            sum = 0;

            for (row = 0; row < N; row++) {
                for (column = 0; column < N; column++) {
                    if (row == column) {
                        sum += cell[row][column];
                    }
                }
            }
            if (sum == sumX || sum == sumO) {
                return true;
            } else {

                for (column = N - 1; column >= 0; column--) {
                    sum = 0;
                    for (row = 0; row < N; row++) {
                        sum += cell[row][column];
                    }
                    if (sum == sumX || sum == sumO) {
                        return true;
                    }
                }
                sum = 0;
                for (row = 0; row < N; row++) {
                    for (column = 0; column < N; column++) {
                        sum += cell[row][column];
                    }
                    if (sum == sumX || sum == sumO) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    static void move(int step) {
        int row = -1, col = -1;

        Random random = new Random();
        System.out.println(step % 2 == 1 ? "Ход компьютера" : "Ходите, Игрок ");
        do {
            if (step % 2 == 1) {
                row = random.nextInt(N);
                col = random.nextInt(N);
            } else {
                System.out.print("Введите новые координаты вашего хода ");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
        } while (row >= N || row < 0 || col >= N || col < 0 || cell[row][col] != 0);
        cell[row][col] = step % 2 == 1 ? 1 : 5;
    }

    static void move2human(int step) {
        int row = -1, col = -1;
        System.out.println(step % 2 == 1 ? "Ваш ход Игрок1 " : "Ваш ход Игрок2 ");
        do {
            if (step % 2 == 1) {
                System.out.print("Введите новые координаты вашего хода");
                row = scanner.nextInt();
                col = scanner.nextInt();
            } else {
                System.out.print("Введите новые координаты вашего хода");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
        } while (row >= N || row < 0 || col >= N || col < 0 || cell[row][col] != 0);
        cell[row][col] = step % 2 == 1 ? 1 : 5;
    }

    static void eraseField() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                cell[row][col] = 0;

            }
        }
    }

    static void drawField() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (cell[row][col] == 1)
                    System.out.print(" X ");
                else if (cell[row][col] == 5)
                    System.out.print(" O ");
                else
                    System.out.print(" * ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
