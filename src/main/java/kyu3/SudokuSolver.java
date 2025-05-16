package kyu3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuSolver {

    public static int[][] sudoku(int[][] puzzle) {
        Map<Integer, List<Integer>> numbersSquared = new HashMap<>();
        int emptyNumberSquared = 0;

        while (emptyNumberSquared <= 9) {
            numbersSquared = numbersInSquareForPlacement(puzzle);
            emptyNumberSquared = 0;
            for (int i = 0; i < 9; i++) {
                if (numbersSquared.get(i).isEmpty()) {
                    emptyNumberSquared++;
                }
            }
            if (emptyNumberSquared == 9) {
                return puzzle;
            } else {

                int offsetCol = 0;
                int offsetRow = 0;
                int numberSquared = 0;

                while (offsetCol != 9 && offsetRow != 9) {
                    int count = 0;
                    int col = 0;
                    int row = 0;
                    for (int k = 0; k < numbersSquared.get(numberSquared).size(); k++) {
                        int num = numbersSquared.get(numberSquared).get(k);
                        for (int i = offsetCol; i < 3 + offsetCol; i++) {
                            for (int j = offsetRow; j < 3 + offsetRow; j++) {
                                if (puzzle[i][j] == 0) {
                                    if (checkCol(puzzle, j, num) && checkRow(puzzle, i, num)) {
                                        col = i;
                                        row = j;
                                        count++;
                                    }
                                }
                            }
                        }
                        if (count == 1) {
                            puzzle[col][row] = num;
                        }
                        count = 0;
                    }


                    offsetCol += 3;
                    if (offsetCol == 9) {
                        offsetCol = 0;
                        offsetRow += 3;
                        if (offsetRow == 9) {
                            break;
                        }
                    }
                    numberSquared++;
                }
            }
        }

        System.out.println(Arrays.deepToString(puzzle));
        System.out.println(numbersSquared);
        return puzzle;
    }


    private static boolean checkCol(int[][] puzzle, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (puzzle[x][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRow(int[][] puzzle, int row, int num) {
        for (int x = 0; x < 9; x++) {
            if (puzzle[row][x] == num) {
                return false;
            }
        }
        return true;
    }

    private static Map<Integer, List<Integer>> numbersInSquareForPlacement(int[][] puzzle) {
        Map<Integer, List<Integer>> numbersSquared = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            numbersSquared.put(i, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        }
        int offsetCol = 0;
        int offsetRow = 0;
        int numberSquared = 0;
        while (offsetCol != 9 && offsetRow != 9) {
            for (int i = offsetCol; i < 3 + offsetCol; i++) {
                for (int j = offsetRow; j < 3 + offsetRow; j++) {
                    if (numbersSquared.get(numberSquared).contains(puzzle[i][j])) {
                        Integer removeElem = puzzle[i][j];
                        numbersSquared.get(numberSquared).remove(removeElem);
                    }
                }
            }

            offsetCol += 3;
            if (offsetCol == 9) {
                offsetCol = 0;
                offsetRow += 3;
                if (offsetRow == 9) {
                    break;
                }
            }
            numberSquared++;
        }
        return numbersSquared;
    }
}
