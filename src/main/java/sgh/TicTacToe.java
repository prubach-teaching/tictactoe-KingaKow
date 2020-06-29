package sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicTacToe {

    public enum Result { NOT_FINISHED, NO_WINNER, X_WON, O_WON }

    public static Result checkBoard(String boardFileName) throws FileNotFoundException {
        File boardFile = new File(boardFileName);
        System.out.println(boardFile.getAbsolutePath());

        Scanner scan = new Scanner(boardFile);

        int rows = 3;
        int columns = 5;

        char[][] game = new char[rows][columns];

        for (int a = 0; scan.hasNextLine() && a < rows; a++) {
            char[] g2 = scan.nextLine().toCharArray();
            for (int b = 0; b < columns && b < g2.length; b++) {
                game[a][b] = g2[b];
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (game[r][c] == 'x') {
                    game[r][c] = 1;
                }
                else if (game[r][c] == 'o') {
                    game[r][c] = 100;
                }
                else if (game[r][c] == ';') {
                    game[r][c] = 0;
                }
            }

        }
        // if not finished
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) {

                if (game[r][c] != 1 && game[r][c] != 100 && game[r][c] != 0) {
                    return Result.NOT_FINISHED;
                }
            }
        }

        // if win diagonally
        if ((game[0][0] + game[1][2] + game[2][4]) == 300) {
            return Result.O_WON;
        } else if ((game[0][0] + game[1][2] + game[2][4]) == 3) {
            return Result.X_WON;
        }

        if ((game[0][4] + game[1][2] + game[2][0]) == 300) {
            return Result.O_WON;
        } else if ((game[0][4] + game[1][2] + game[2][0]) == 3) {
            return Result.X_WON;
        }

        // if win across
        for (int r = 0; r < rows; r++) {
            if ((game[r][0] + game[r][2] + game[r][4]) == 300) {
                return Result.O_WON;
            }
            else if ((game[r][0] + game[r][2] + game[r][4]) == 3) {
                return Result.X_WON;
            }
        }

        //if win vertically
        for (int c = 0; c <columns; c++) {
            if ((game[0][c] + game[1][c] + game[2][c]) == 300) {
                return Result.O_WON;
            }
            else if ((game[0][c] + game[1][c] + game[2][c]) == 3) {
                return Result.X_WON;
            }
        }

        return Result.NO_WINNER;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Result res = checkBoard("boards/tick3.csv");
        System.out.println(res);
    }
}