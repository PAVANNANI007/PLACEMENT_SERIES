class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (d > len) return -1;
        int[][] minDifficulty = new int[d][len];
        for (int i = 1; i < d; i++) {
            Arrays.fill(minDifficulty[i], Integer.MAX_VALUE);
        }
        int maxDifficulty = 0;
        for (int i = 0; i <= len - d; i++) {
            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);
            minDifficulty[0][i] = maxDifficulty;
        }
        for (int day = 1; day < d; day++) {
            for (int to = day; to <= len - d + day; to++) {
                int currentDayDifficulty = jobDifficulty[to];
                int result = Integer.MAX_VALUE;
                for (int j = to - 1; j >= day - 1; j--) {
                    result = Math.min(result, minDifficulty[day - 1][j] + currentDayDifficulty);
                    currentDayDifficulty = Math.max(currentDayDifficulty, jobDifficulty[j]);
                }
                minDifficulty[day][to] = result;
            }   
        }
        return minDifficulty[d - 1][len - 1];
    }
}
