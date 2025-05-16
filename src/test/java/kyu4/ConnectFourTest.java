package kyu4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {

    @Test
    public void firstTest()
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(
                "A_Red",
                "B_Yellow",
                "A_Red",
                "B_Yellow",
                "A_Red",
                "B_Yellow",
                "G_Red",
                "B_Yellow"
        ));
        assertEquals(ConnectFour.whoIsWinner(myList), "Yellow","it should return Yellow" );
    }

    @Test
    public void secondTest()
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(
                "C_Yellow",
                "E_Red",
                "G_Yellow",
                "B_Red",
                "D_Yellow",
                "B_Red",
                "B_Yellow",
                "G_Red",
                "C_Yellow",
                "C_Red",
                "D_Yellow",
                "F_Red",
                "E_Yellow",
                "A_Red",
                "A_Yellow",
                "G_Red",
                "A_Yellow",
                "F_Red",
                "F_Yellow",
                "D_Red",
                "B_Yellow",
                "E_Red",
                "D_Yellow",
                "A_Red",
                "G_Yellow",
                "D_Red",
                "D_Yellow",
                "C_Red"
        ));
        assertEquals(ConnectFour.whoIsWinner(myList), "Yellow", "it should return Yellow");
    }

    @Test
    public void thirdTest()
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(
                "A_Yellow",
                "B_Red",
                "B_Yellow",
                "C_Red",
                "G_Yellow",
                "C_Red",
                "C_Yellow",
                "D_Red",
                "G_Yellow",
                "D_Red",
                "G_Yellow",
                "D_Red",
                "F_Yellow",
                "E_Red",
                "D_Yellow"
        ));
        assertEquals(ConnectFour.whoIsWinner(myList), "Red", "it should return Red");
    }

    @Test
    public void fourthTest()
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(
                "F_Yellow",
                "G_Red",
                "D_Yellow",
                "C_Red",
                "A_Yellow",
                "A_Red",
                "E_Yellow",
                "D_Red",
                "D_Yellow",
                "F_Red",
                "B_Yellow",
                "E_Red",
                "C_Yellow",
                "D_Red",
                "F_Yellow",
                "D_Red",
                "D_Yellow",
                "F_Red",
                "G_Yellow",
                "C_Red",
                "F_Yellow",
                "E_Red",
                "A_Yellow",
                "A_Red",
                "C_Yellow",
                "B_Red",
                "E_Yellow",
                "C_Red",
                "E_Yellow",
                "G_Red",
                "A_Yellow",
                "A_Red",
                "G_Yellow",
                "C_Red",
                "B_Yellow",
                "E_Red",
                "F_Yellow",
                "G_Red",
                "G_Yellow",
                "B_Red",
                "B_Yellow",
                "B_Red"
        ));
        assertEquals(ConnectFour.whoIsWinner(myList), "Red", "it should return Red");
    }

    @Test
    public void fifthTest()
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(
                "C_Yellow",
                "B_Red",
                "B_Yellow",
                "E_Red",
                "D_Yellow",
                "G_Red",
                "B_Yellow",
                "G_Red",
                "E_Yellow",
                "A_Red",
                "G_Yellow",
                "C_Red",
                "A_Yellow",
                "A_Red",
                "D_Yellow",
                "B_Red",
                "G_Yellow",
                "A_Red",
                "F_Yellow",
                "B_Red",
                "D_Yellow",
                "A_Red",
                "F_Yellow",
                "F_Red",
                "B_Yellow",
                "F_Red",
                "F_Yellow",
                "G_Red",
                "A_Yellow",
                "F_Red",
                "C_Yellow",
                "C_Red",
                "G_Yellow",
                "C_Red",
                "D_Yellow",
                "D_Red",
                "E_Yellow",
                "D_Red",
                "E_Yellow",
                "C_Red",
                "E_Yellow",
                "E_Red"
        ));
        assertEquals(ConnectFour.whoIsWinner(myList),"Yellow",  "it should return Yellow");
    }

    @Test
    public void randomTest()
    {
        for (int i = 0; i < 100; i++) {
            RgConnectFour rg = new RgConnectFour((int)(Math.random() * 10000));
            List<String> myList = rg.listOfPositions();
            assertEquals(ConnectFour2January.whoIsWinner(myList), ConnectFour.whoIsWinner(myList));
        }
    }

    // I wasn't able to figure out how the preloaded solution for the C# translation worked, so I just used my own solution.
    // The solution used in the C# cases outputs "Draw" even when a simple look at the list indicates that there was a winner. I was confused by that.
    // I can fix anything about the solution that might be wrong if you notice something.
    final static private class ConnectFour2January {
        private static int numRows = 6, numCols = 7, numWin = 4;

        private static int[] dir = {
                0, 1, 1, 0, 1, 1, 1, -1
        };

        private static int[][] grid;

        private static int query(int r, int c) {
            if (r < 0 || r >= numRows || c < 0 || c >= numCols)
                return 0;
            else
                return grid[r][c];
        }

        private static int check(int r, int c, int dr, int dc) {
            if (grid[r][c] == 0)
                return 0;

            int sum = 1;
            for (int d = 1; d < numWin; d++)
                if (grid[r][c] == query(r + d*dr, c + d*dc))
                    sum++;
                else
                    break;
            for (int d = 1; d < numWin; d++)
                if (grid[r][c] == query(r - d*dr, c - d*dc))
                    sum++;
                else
                    break;
            return sum >= numWin ? grid[r][c] : 0;
        }

        private static String whoIsWinner(List<String> pieces) {
            grid = new int[numRows][numCols];
            int[] counts = new int[numCols];

            for (String piece : pieces) {
                int col = piece.charAt(0) - 'A';
                int row = numRows-1 - counts[col]++;
                grid[row][col] = piece.endsWith("Yellow") ? 1 : 2;

                for (int d = 0; d < dir.length; d += 2) {
                    int result = check(row, col, dir[d], dir[d+1]);
                    if (result > 0)
                        return result == 1 ? "Yellow" : "Red";
                }
            }

            return "Draw";
        }
    }


    final static private class RgConnectFour
    {
        private static Random _random;
        private static int _counter;
        private RgConnectFour(int seed)
        {
            _counter = _counter + 1;
            _random = new Random(seed + _counter);
        }

        private List<List<String>> _listOfList = Arrays.asList(
                new ArrayList<String>(Arrays.asList("A", "A", "A", "A", "A", "A")),
                new ArrayList<String>(Arrays.asList("B", "B", "B", "B", "B", "B")),
                new ArrayList<String>(Arrays.asList("C", "C", "C", "C", "C", "C")),
                new ArrayList<String>(Arrays.asList("D", "D", "D", "D", "D", "D")),
                new ArrayList<String>(Arrays.asList("E", "E", "E", "E", "E", "E")),
                new ArrayList<String>(Arrays.asList("F", "F", "F", "F", "F", "F")),
                new ArrayList<String>(Arrays.asList("G", "G", "G", "G", "G", "G"))
        );

        private List<String> listOfPositions()
        {
            int myCounter = 0;
            List<String> myList = new ArrayList<String>();
            do
            {
                int indexer = _random.nextInt(7);
                if (_listOfList.get(indexer).size() > 0)
                {
                    myCounter ++;
                    myList.add(_listOfList.get(indexer).get(0) + (myCounter % 2 == 0?"_Red":"_Yellow"));
                    _listOfList.get(indexer).remove( _listOfList.get(indexer).size()-1 );
                }
            } while (_listOfList.stream().anyMatch(p -> p.size() > 0));

            // System.out.println(myList);

            return myList;
        }
    }
}