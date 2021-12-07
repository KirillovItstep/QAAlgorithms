package org.itstep.backtracking;

import java.math.BigDecimal;
import java.util.*;
/*
Сложность - 3
Задача о ходе коня. Написать тест-кейсы. Выполнить функциональное тестирование.
Написать предложения об изменении SRS (Software Requirements Specification).
Данные пользователем вводятся корректно.
 */

public class KnightsTour {

    private  static int base;
    private final static int[][] moves = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}}; // Possible moves by knight on chess
    private static int[][] grid;    // chess grid
    private static int total;   // total squares in chess

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер квадратной шахматной доски (по горизонтали или вертикали): ");
        base = scanner.nextInt()+4;
        System.out.print("Введите начальное положение коня по горизонтали (0..n): ");
        int row = base-scanner.nextInt()-3;
        System.out.print("Введите начальное положение коня по вертикали (0..n): ");
        int col = scanner.nextInt()+2;

        grid = new int[base][base];
        total = (base - 4) * (base - 4);

        for (int r = 0; r < base; r++) {
            for (int c = 0; c < base; c++) {
                if (r < 2 || r > base - 3 || c < 2 || c > base - 3) {
                    grid[r][c] = -1;
                }
            }
        }

        //int row = 2 + (int) (Math.random() * (base - 4));
        //int col = 2 + (int) (Math.random() * (base - 4));

        grid[row][col] = 1;

        if (solve(row, col, 2)) {
            printResult();
        } else {
            System.out.println("no result");
        }

    }

    // Return True when solvable
    private static boolean solve(int row, int column, int count) {
        if (count > total) {
            return true;
        }

        List<int[]> neighbor = neighbors(row, column);

        if (neighbor.isEmpty() && count != total) {
            return false;
        }

        Collections.sort(neighbor, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });

        for (int[] nb : neighbor) {
            row = nb[0];
            column = nb[1];
            grid[row][column] = count;
            if (!orphanDetected(count, row, column) && solve(row, column, count + 1)) {
                return true;
            }
            grid[row][column] = 0;
        }

        return false;
    }

    // Returns List of neighbours
    private static List<int[]> neighbors(int row, int column) {
        List<int[]> neighbour = new ArrayList<>();

        for (int[] m : moves) {
            int x = m[0];
            int y = m[1];
            if (grid[row + y][column + x] == 0) {
                int num = countNeighbors(row + y, column + x);
                neighbour.add(new int[]{row + y, column + x, num});
            }
        }
        return neighbour;
    }

    // Returns the total count of neighbors
    private static int countNeighbors(int row, int column) {
        int num = 0;
        for (int[] m : moves) {
            if (grid[row + m[1]][column + m[0]] == 0) {
                num++;
            }
        }
        return num;
    }

    // Returns true if it is orphan
    private static boolean orphanDetected(int count, int row, int column) {
        if (count < total - 1) {
            List<int[]> neighbor = neighbors(row, column);
            for (int[] nb : neighbor) {
                if (countNeighbors(nb[0], nb[1]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Prints the result grid
    private static void printResult() {
        for (int[] row : grid) {
            for (int i : row) {
                if (i == -1) {
                    continue;
                }
                System.out.printf("%2d ", i);
            }
            System.out.println();
        }
    }
}
