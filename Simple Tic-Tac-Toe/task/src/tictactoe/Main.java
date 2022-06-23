package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        char[] cells = { '_', '_', '_', '_', '_', '_', '_', '_', '_' };
        printBoard(cells);
        char player = 'X';
        int blankCounter = cells.length;
        do try {
            System.out.print("Enter the coordinates: ");
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if (x < 0 || x > 2 || y < 0 || y > 2 ) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int index = x * 3 + y;
            if (cells[index] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            cells[index] = player;
            blankCounter--;
            printBoard(cells);
            int winner = getWinner(x, y, cells);
            if (winner != 0) {
                System.out.println((char) winner + " wins");
                break;
            } else if (blankCounter == 0) {
                System.out.println("Draw");
                break;
            }
            player = player == 'X'? 'O': 'X';
        } catch (InputMismatchException ignore) {
            System.out.println("You should enter numbers!");
            sc.nextLine();
        } while (true);
    }
    static void printBoard(char[] cells) {
        System.out.println("---------");
        System.out.println("| " + cells[0] + " " + cells[1] + " " + cells[2] + " |");
        System.out.println("| " + cells[3] + " " + cells[4] + " " + cells[5] + " |");
        System.out.println("| " + cells[6] + " " + cells[7] + " " + cells[8] + " |");
        System.out.println("---------");
    }
    static int getWinner(int row, int col, char[] cells) {
        if (checkDiagonalRight(cells) && cells[0] != '_') {
            return cells[0];
        }
        if (checkDiagonalLeft(cells) && cells[2] != '_') {
            return cells[2];
        }
            if (checkRow(row, cells) && cells[row * 3] != '_') {
                return cells[row * 3];
            }
            if (checkColumn(col, cells) && cells[col] != '_') {
                return cells[col];
            }
        return 0;
    }
    static boolean checkRow(int row, char[] cells) {
        row *= 3;
        return cells[row] == cells[row + 1] && cells[row] == cells[row + 2];
    }
    static boolean checkColumn(int column, char[] cells) {
        return cells[column] == cells[column + 3] && cells[column] == cells[column + 6];
    }
    static boolean checkDiagonalRight(char[] cells) {
        return cells[0] == cells[4] && cells[0] == cells[8];
    }
    static boolean checkDiagonalLeft(char[] cells) {
        return cells[2] == cells[4] && cells[2] == cells[6];
    }
}
