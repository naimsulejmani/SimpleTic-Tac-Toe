package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static String[][] ticTacToeGrid = new String[][]{
            {" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}
    };
    static String[] types = {"X", "O"};
    static int player = 0;


    public static void main(String[] args) {
        // write your code here
        //   initializeTicTacToeGrid();
        printBoard();
        makeMove();
//        if (isImpossible()) {
//            System.out.println("Impossible");
//        } else if (hasWinningLine("O")) {
//            System.out.println("O wins");
//        } else if (hasWinningLine("X")) {
//            System.out.println("X wins");
//        } else if (isGameFinished()) {
//            System.out.println("Draw");
//        } else {
//            System.out.println("Game not finished");
//        }
    }

    private static void makeMove() {
        System.out.print("Enter the coordinates: ");
        String play = scanner.nextLine();
        try {
            player %= 2;
            if (isLegalMove(play, types[player++])) {
                printBoard();
            }
        } catch (IllegalArgumentException ex) {
            player = player == 1 ? 0 : 1;
            makeMove();
        }
        if (isImpossible()) {
            System.out.println("Impossible");
        } else if (hasWinningLine("O")) {
            System.out.println("O wins");
        } else if (hasWinningLine("X")) {
            System.out.println("X wins");
        } else if (isGameFinished()) {
            System.out.println("Draw");
        } else {
            makeMove();
        }


    }

    private static void printBoard() {
        System.out.println("---------");
        for (String[] array : ticTacToeGrid) {
            System.out.print("|");
            for (String play : array) {
                System.out.printf(" %s", play);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    private static void initializeTicTacToeGrid() {
        String playString = scanner.nextLine();
        for (int i = 0, j = 0; i < playString.length(); i++) {
            j = i / 3;
            ticTacToeGrid[j][i % 3] = playString.charAt(i) + "";
        }
    }

    private static boolean hasWinningLine(String element) {
        return hasWinningRow(element) || hasWinningColumn(element) || hasWinningDiagonal(element);
    }

    private static boolean hasWinningRow(String element) {
        for (int i = 0; i < 3; i++) {
            int total = 0;
            for (int j = 0; j < 3; j++) {
                if (element.equals(ticTacToeGrid[i][j])) {
                    total++;
                } else {
                    break;
                }
            }
            if (total == 3) return true;
        }
        return false;
    }

    private static boolean hasWinningColumn(String element) {
        for (int i = 0; i < 3; i++) {
            int total = 0;
            for (int j = 0; j < 3; j++) {
                if (element.equals(ticTacToeGrid[j][i])) {
                    total++;
                } else {
                    break;
                }
            }
            if (total == 3) return true;
        }
        return false;
    }

    private static boolean hasWinningDiagonal(String element) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (element.equals(ticTacToeGrid[i][i])) {
                total++;
            } else break;
        }
        if (total == 3) return true;
        total = 0;
        for (int i = 0, j = 2; i < 3; i++, j--) {
            if (element.equals(ticTacToeGrid[i][j])) {
                total++;
            } else break;
        }
        return total == 3;
    }

    private static boolean isGameFinished() {
        return count("X") + count("O") == 9;
    }

    private static boolean isImpossible() {
        return Math.abs(count("X") - count("O")) > 1 || (hasWinningLine("O") && hasWinningLine("X"));
    }


    private static boolean isLegalMove(String play, String type) {
        try {
            String[] splitter = play.split(" ");
            if (splitter.length < 2) {
                Integer.parseInt(play);
                throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
            }
            int row = Integer.parseInt(splitter[0]);
            int col = Integer.parseInt(splitter[1]);
            if (row <= 0 || row > 3 || col <= 0 || col > 3) {
                throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
            }
            if (!isFreeToPlay(row, col)) {
                throw new IllegalArgumentException("This cell is occupied! Choose another one!");
            }
            makeMove(row, col, type);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("You should enter numbers!");
            throw ex;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    private static void makeMove(int row, int col, String element) {
        ticTacToeGrid[row - 1][col - 1] = element;
    }

    private static boolean isFreeToPlay(int row, int col) {
        String val = ticTacToeGrid[row - 1][col - 1];
        return val.isBlank() || val.equals("_");
    }

    private static int count(String element) {
        int counter = 0;
        for (String[] array : ticTacToeGrid) {
            for (String elem : array) {
                if (elem.equals(element)) {
                    counter++;
                }
            }
        }
        return counter;
    }

}
