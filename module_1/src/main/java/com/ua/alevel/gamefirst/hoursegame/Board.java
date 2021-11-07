package com.ua.alevel.gamefirst.hoursegame;

import static ua.com.alevel.StringerUtil.print;

public final class Board {

    private static Board instance;
    private Hourse[][] board = new Hourse[8][8];

    private Board() {
        fillArrField();
        board = new Hourse[8][8];
    }

    public Hourse[][] getBoardArr() {
        fillArrField();
        return board;
    }

    public static Board getBoard() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    private void fillArrField() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Hourse("*");
            }
        }
    }

    public void printBoard() {
        int count = 1;
        print("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < board.length; i++) {
            System.out.print(count++ + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
