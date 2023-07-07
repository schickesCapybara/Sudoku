import java.util.*;

public class Main {
    public static void main(String[] args) {

        //example Sudoku
        int[][] board = {
                {1, 0, 5, 4, 0, 0, 7, 0, 0},
                {9, 4, 0, 0, 2, 8, 3, 0, 0},
                {6, 2, 3, 0, 0, 0, 0, 0, 4},
                {0, 0, 0, 7, 0, 0, 0, 3, 5},
                {0, 0, 0, 2, 0, 3, 0, 0, 7},
                {0, 0, 1, 0, 5, 0, 0, 4, 0},
                {0, 0, 8, 0, 0, 0, 1, 0, 0},
                {5, 0, 6, 9, 0, 2, 0, 0, 0},
                {7, 0, 4, 3, 0, 0, 6, 0, 9}
        };

        printBoard(board);
        if (solveBoard(board)) {
            System.out.println("Solved Sudoku: ");
            printBoard(board);
        } else {
            System.out.println("Board not solvable");
        }

    }

    public static boolean checkColumn(int[][] board, int y, int x, int n) {
        if (board[0][x] != n && board[1][x] != n && board[2][x] != n && board[3][x] != n && board[4][x] != n && board[5][x] != n && board[6][x] != n && board[7][x] != n && board[8][x] != n) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkRow(int[][] board, int y, int x, int n) {
        if (board[y][0] != n && board[y][1] != n && board[y][2] != n && board[y][3] != n && board[y][4] != n && board[y][5] != n && board[y][6] != n && board[y][7] != n && board[y][8] != n) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAllSquare(int[][] board, int y, int x, int n) {

        //zuerst square finden
        //square LEFT side
        if (x >= 0 && x <= 2) {

            //top square
            if (y >= 0 && y <= 2) {
                if (checkBigSquare(board, 0, 0, n)) {
                    return true;
                } else {
                    return false;
                }
            } else
                //middle square
                if (y >= 3 && y <= 5) {
                    if (checkBigSquare(board, 3, 0, n)) {
                        return true;
                    } else {
                        return false;
                    }
                } else
                    //bottom square
                    if (y <= 8) {
                        if (checkBigSquare(board, 6, 0, n)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
            //square MIDDLE column
        } else if (x >= 3 && x <= 5) {

            //top square
            if (y >= 0 && y <= 2) {
                if (checkBigSquare(board, 0, 3, n)) {
                    return true;
                } else {
                    return false;
                }
            } else
                //middle square
                if (y >= 3 && y <= 5) {
                    if (checkBigSquare(board, 3, 3, n)) {
                        return true;
                    } else {
                        return false;
                    }
                } else
                    //bottom square
                    if (y <= 8) {
                        if (checkBigSquare(board, 6, 3, n)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
            //square RIGHT side
        } else if (x >= 6 && x <= 8) {

            //top square
            if (y >= 0 && y <= 2) {
                if (checkBigSquare(board, 0, 6, n)) {
                    return true;
                } else {
                    return false;
                }
            } else
                //middle square
                if (y >= 3 && y <= 5) {
                    if (checkBigSquare(board, 3, 6, n)) {
                        return true;
                    } else {
                        return false;
                    }
                } else
                    //bottom square
                    if (y <= 8) {
                        if (checkBigSquare(board, 6, 6, n)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
        }
        System.out.println("Error Square");
        return false;
    }

    public static void printBoard(int[][] board) {
        // Darstellung des Sudoku-Spielfelds
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i % 3 == 0) {
                System.out.println("-------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 0 || j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");

            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    public static boolean checkBigSquare(int[][] board, int y, int x, int n) {
        // y and x needed of the top left square in the big square
        if (board[y][x] != n && board[y][x + 1] != n && board[y][x + 2] != n && board[y + 1][x] != n && board[y + 1][x + 1] != n && board[y + 1][x + 2] != n && board[y + 2][x] != n && board[y + 2][x + 1] != n && board[y + 2][x + 2] != n) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPlacement(int[][] board, int y, int x, int n) {
        if (checkAllSquare(board, y, x, n) && checkColumn(board, y, x, n) && checkRow(board, y, x, n)) {
            return true;
        }
        return false;
    }

    private static boolean solveBoard(int[][] board) {
        for (int y = 0; y <= 8; y++) {
            for (int x = 0; x <= 8; x++) {
                if (board[y][x] == 0) {
                    for (int n = 1; n <= 9; n++) {
                        if (checkPlacement(board, y, x, n)) {
                            board[y][x] = n;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[y][x] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}



