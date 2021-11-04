package com.ua.alevel.gamefirst.hoursegame;

import static ua.com.alevel.StringerUtil.exception;

public class Game {


    private Hourse hourse;
    private Hourse[][] board;

    private int posX;
    private int posY;


    public Game(int posX, int posY) {
        hourse = new Hourse("H");
        this.posX = posX;
        this.posY = posY;
        board = Board.getBoard().getBoardArr();
        startPositionHourse(posX,posY);
        printBoard();
    }

    private void startPositionHourse(int posX, int posY) {
        try {
            board[posX][posY] = hourse;
            System.out.println(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            exception(e.getClass().getName());
        }
    }


    public void stepCoordinatHourse(int newposX, int newposY) {

        int findX = Math.abs(posX - newposX);
        int findY = Math.abs(posY - newposY);

        if (findX == 1 && findY == 2 || findX == 2 && findY == 1) {
            board[posX][posY] = new Hourse("*");
            board[newposX][newposY] = hourse;
            this.posX = newposX;
            this.posY = newposY;
            System.out.println("Yes");
            printBoard();
        } else {
            exception(new IllegalStateException("No").getClass().getName());
        }

    }

    public void printBoard() {
        Board.getBoard().printBoard();
    }

















}
