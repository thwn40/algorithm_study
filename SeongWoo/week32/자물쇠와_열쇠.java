package SeongWoo.week32;

public class 자물쇠와_열쇠 {

    public boolean check(int slotCount, int firstRow, int firstCol, int[][] lock, int[][] key) {
        int row = firstRow;
        int col = firstCol;
        int count = 0;
        for (int i = 0; i < key.length; i++) {
            row = firstRow + i;
            for (int j = 0; j < key.length; j++) {
                col = firstCol + j;
                if (lock[row][col] == -1) {
                    continue;
                }
                if (lock[row][col] == 1 && key[i][j] == 1) {
                    return false;
                }
                if (lock[row][col] == 0 && key[i][j] == 1) {
                    count++;
                }
            }
        }
        return count == slotCount;
    }

    public boolean getResult(int[][] lock, int[][] key, int slotCount, int lockLength) {
        for (int i = 0; i < key.length + lockLength - 1; i++) {
            for (int j = 0; j < key.length + lockLength - 1; j++) {
                if (check(slotCount, i, j, lock, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] cycle(int[][] key) {
        int[][] result = new int[key.length][key[0].length];

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                result[j][key.length - 1 - i] = key[i][j];
            }
        }
        return result;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int length = key.length * 2 + lock.length - 2;
        int[][] extendedLock = new int[length][length];
        int slotCount = 0;
        for (int i = 0; i < extendedLock.length; i++) {
            for (int j = 0; j < extendedLock.length; j++) {
                extendedLock[i][j] = -1;
            }
        }

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                extendedLock[i + key.length - 1][j + key.length - 1] = lock[i][j];
            }
        }

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    slotCount++;
                }
            }
        }

        for (int c = 0; c < 4; c++) {
            key = cycle(key);
            if (getResult(extendedLock, key, slotCount, lock.length)) {
                return true;
            }
        }
        return false;
    }
}
