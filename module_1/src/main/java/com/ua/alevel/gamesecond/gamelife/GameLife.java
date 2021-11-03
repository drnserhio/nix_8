package com.ua.alevel.gamesecond.gamelife;

import java.util.Arrays;

public class GameLife {

    // Life - "\uD83D\uDCA9" 💩
    // Dead - "\uD83D\uDC80" 💀

    private String[][] board;
    private String[][] saveBoard;
    private String[][] previouseBoard;

    private int min;
    private int max;
    private int countSame;

    private final String LIFE = "\uD83D\uDCA9";
    private final String DEAD = "\uD83D\uDC80";
    private final String BOX = "\u2B1B";
    private final String BORDER = "\uD83D\uDD3A";

    public GameLife(int n, int m) {
        this.board = new String[n + 10][m + 10];
        this.max = m - 2;
        this.min = n + 7;
    }

    public void startGame() {
        fillArr();
        lifyCycle();
        printBoard();
        updateWorld();
    }


    private void lifyCycle() {
        String[] entity = new String[]{LIFE, DEAD};

        for (int i = 0; i < 20; i++) {
            board[rnd(min, max)][rnd(min, max)] = entity[rnd(0, 1)];
        }
        saveBoard = board.clone();
    }

    private static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }


    private boolean isAlive(int x, int y) {
        if (board[x][y].equals(LIFE)) {
            return true;
        }
        return false;
    }

    private boolean isDead(int x, int y) {
        if (board[x][y].equals(DEAD)) {
            return true;
        }
        return false;
    }


   private int countSurrounding(String[][] board, int a, int b) {
        int count = 0;
        int[][] surrounding = {{a - 1, b - 1},
                {a - 1, b    },
                {a - 1, b + 1},
                {a    , b - 1},
                {a    , b + 1},
                {a + 1, b - 1},
                {a + 1, b    },
                {a + 1, b + 1}};
        for (int i[]: surrounding) {
            try {
                if (board[i[0]][i[1]].equals(LIFE)) {
                    count++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}
        }
        return count;
    }

    private void updateWorld() {
        savePreviouseBoard();
        for (int i = 0; i < saveBoard.length; i++) {
            for (int j = 0; j < saveBoard[i].length; j++) {
                if (isAlive(i, j) && !(countSurrounding(saveBoard, i, j) == 2 || countSurrounding(saveBoard, i, j) == 3)) {
                    board[i][j] = DEAD;
                }
                else if    (isDead(i, j)&& countSurrounding(board, i, j) == 3) {
                    board[i][j] = LIFE;
                }
            }
        }
        printBoard();
        finish();
    }


    private void fillArr() {
        for (int i = 0; i < board.length; i++) {
            board[i][0] = BORDER;
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board.length - 1) {
                    board[i][j] = BORDER;
                } else {
                    board[i][j] = BOX;
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < saveBoard.length; i++) {
            for (int j = 0; j < saveBoard[i].length; j++) {
                System.out.print(saveBoard[i][j] + " ");
            }
            System.out.println();
        }
    }


    private void finish() {
        int countLife = 0;
        for (int i = 0; i < saveBoard.length - 1; i++) {
            for (int j = 0; j < saveBoard[i].length - 1; j++) {
                if(saveBoard[i][j].equals(LIFE)) {
                    countLife++;
                }
            }
        }
        isSameBoard(previouseBoard,saveBoard);
        if (countLife == 0) {
            System.out.println("Finish game");
            System.exit(0);
        }
        else {
            updateWorld();
        }
    }

    private void isSameBoard(String[][] arr, String[][] saveBoard) {
        if (Arrays.deepEquals(arr, saveBoard) && countSame < 1) {
            countSame++;
        } else {
            return;
        }
    }

    private void savePreviouseBoard() {
       previouseBoard = saveBoard.clone();
    }


}
