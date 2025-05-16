package kyu4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectFour {
    /*https://www.codewars.com/kata/56882731514ec3ec3d000009/train/java*/
    public static String whoIsWinner(List<String> piecesPositionList) {
        String[][] playerField = new String[7][6];
        Map<String, Integer> columnName = new HashMap<>(Map.of("A", 0,
                "B", 1,
                "C", 2,
                "D", 3,
                "E", 4,
                "F", 5,
                "G", 6));

        int n = 0;
        for (String p : piecesPositionList) {
            int column = columnName.get(p.substring(0, 1));
            String color = p.substring(2, 3);

            int c = 0;
            while (c < 6) {
                if (playerField[column][c] != null) {
                    c++;
                } else {
                    playerField[column][c] = color;
                    break;
                }
            }
            n++;
            if (n >= 7) {
                for (int j = 0; j < 6; j++) {

                    for (int i = 0; i < 4; i++) {
                        int lengthR = 0;
                        int lengthY = 0;
                        for (int k = 0; k < 4; k++) {
                            if (playerField[i + k][j] != null && playerField[i + k][j].equals("R")) {
                                lengthR++;
                            } else if (playerField[i + k][j] != null && playerField[i + k][j].equals("Y")) {
                                lengthY++;
                            }
                        }
                        if (lengthR == 4) {
                            return "Red";
                        } else if (lengthY == 4) {
                            return "Yellow";
                        }
                    }
                }

                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 3; j++) {
                        int lengthR = 0;
                        int lengthY = 0;
                        for (int k = 0; k < 4; k++) {
                            if (playerField[i][j + k] != null && playerField[i][j + k].equals("R")) {
                                lengthR++;
                            } else if (playerField[i][j + k] != null && playerField[i][j + k].equals("Y")) {
                                lengthY++;
                            }
                        }
                        if (lengthR == 4) {
                            return "Red";
                        } else if (lengthY == 4) {
                            return "Yellow";
                        }
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        int lengthR = 0;
                        int lengthY = 0;
                        for (int k = 0; k < 4; k++) {
                            if (playerField[i + k][j + k] != null && playerField[i + k][j + k].equals("R")) {
                                lengthR++;
                            } else if (playerField[i + k][j + k] != null && playerField[i + k][j + k].equals("Y")) {
                                lengthY++;
                            }
                        }
                        if (lengthR == 4) {
                            return "Red";
                        } else if (lengthY == 4) {
                            return "Yellow";
                        }
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 5; j > 2; j--) {
                        int lengthR = 0;
                        int lengthY = 0;
                        for (int k = 0; k < 4; k++) {
                            if (playerField[i + k][j - k] != null && playerField[i + k][j - k].equals("R")) {
                                lengthR++;
                            } else if (playerField[i + k][j - k] != null && playerField[i + k][j - k].equals("Y")) {
                                lengthY++;
                            }
                        }
                        if (lengthR == 4) {
                            return "Red";
                        } else if (lengthY == 4) {
                            return "Yellow";
                        }
                    }
                }
            }
        }
        return "Draw";
    }
}
