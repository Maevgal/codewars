package kyu4;

import java.util.Arrays;

public class User {
    private int[] ranks = {-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};
    private int curRank = 0;
    public int progress = 0;
    public int rank = -8;
    public void incProgress(int kRank) {
        kRank = Arrays.binarySearch(ranks, kRank);
        if(kRank < 0) throw new RuntimeException("Invalid rank");
        if(ranks[curRank] == 8) return;
        if(kRank == curRank) progress += 3;
        else if(kRank == curRank - 1) progress++;
        else if(kRank > curRank) {
            int diff = kRank - curRank;
            progress += 10 * diff * diff;
        }
        while(progress >= 100) {
            curRank++;
            updateRank();
            progress -= 100;
            if(ranks[curRank] == 8) {
                progress = 0;
                return;
            }
        }
    }
    private void updateRank() {
        rank = ranks[curRank];
    }
}
