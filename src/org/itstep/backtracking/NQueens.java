package org.itstep.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 /*
 Сложность - 3
 Задача о ферзях. Написать тест-кейсы. Выполнить функциональное тестирование.
 Написать предложения об изменении SRS (Software Requirements Specification).
 Данные пользователем вводятся корректно.
 */

public class NQueens {
    private static int count; // count of queens

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество ферзей: ");
        int count = scanner.nextInt();
        placeQueens(count);
    }

    public static void placeQueens(final int queens) {
        List<List<String>> arrangements = new ArrayList<List<String>>();
        getSolution(queens, arrangements, new int[queens], 0);
        if (arrangements.isEmpty()) {
            System.out.println("There is no way to place " + queens + " queens on board of size " + queens + "x" + queens);
        } else {
            System.out.println("Arrangement for placing " + queens + " queens");
        }
        arrangements.forEach(arrangement -> {
            arrangement.forEach(row -> System.out.println(row));
            System.out.println();
        });
    }

    /**
     * This is backtracking function which tries to place queen recursively
     *
     * @param boardSize: size of chess board
     * @param solutions: this holds all possible arrangements
     * @param columns: columns[i] = rowId where queen is placed in ith column.
     * @param columnIndex: This is the column in which queen is being placed
     */
    private static void getSolution(int boardSize, List<List<String>> solutions, int[] columns, int columnIndex) {
        if (columnIndex == boardSize) {
            // this means that all queens have been placed
            List<String> sol = new ArrayList<String>();
            for (int i = 0; i < boardSize; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < boardSize; j++) {
                    sb.append(j == columns[i] ? "Q" : ".");
                }
                sol.add(sb.toString());
            }
            solutions.add(sol);
            return;
        }

        // This loop tries to place queen in a row one by one
        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            columns[columnIndex] = rowIndex;
            if (isPlacedCorrectly(columns, rowIndex, columnIndex)) {
                // If queen is placed successfully at rowIndex in column=columnIndex then try placing queen in next column
                getSolution(boardSize, solutions, columns, columnIndex + 1);
            }
        }
    }

    /**
     * This function checks if queen can be placed at row = rowIndex in column =
     * columnIndex safely
     *
     * @param columns: columns[i] = rowId where queen is placed in ith column.
     * @param rowIndex: row in which queen has to be placed
     * @param columnIndex: column in which queen is being placed
     * @return true: if queen can be placed safely false: otherwise
     */
    private static boolean isPlacedCorrectly(int[] columns, int rowIndex, int columnIndex) {
        for (int i = 0; i < columnIndex; i++) {
            int diff = Math.abs(columns[i] - rowIndex);
            if (diff == 0 || columnIndex - i == diff) {
                return false;
            }
        }
        return true;
    }
}