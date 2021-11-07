package com.ua.alevel.gamefirst.hoursegame;

import static ua.com.alevel.StringerUtil.exception;

public class Game {

    private Hourse hourse;
    private Hourse[][] board;

    private int posX;
    private int posY;

    public Game(int posX, int posY) {
        hourse = new Hourse("H");
        this.posX = posX - 1;
        this.posY = posY - 1;
        board = Board.getBoard().getBoardArr();
        startPositionHourse(this.posX, this.posY);
        printBoard();
    }

    private void startPositionHourse(int posX, int posY) {
        try {
            board[posX][posY] = hourse;
        } catch (ArrayIndexOutOfBoundsException e) {
            exception(e.getClass().getName());
        }
    }

    public void stepCoordinatHourse(int newposX, int newposY) {

        int findX = Math.abs(newposX - posX);
        int findY = Math.abs(newposY - posY);

        if (findX == 1 && findY == 2 || findX == 2 && findY == 1) {
            board[posX][posY] = new Hourse("*");
            board[newposX][newposY] = hourse;
            this.posX = newposX;
            this.posY = newposY;
            printBoard();
        } else {
            exception("Went out of the array");
            printBoard();
        }
    }

    public void printBoard() {
        Board.getBoard().printBoard();
    }
}
