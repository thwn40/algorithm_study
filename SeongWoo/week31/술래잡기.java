package SeongWoo.week31;

public class 술래잡기 {

    public static class Node {
        boolean tagger = false;
        boolean runner = false;
        boolean tree = false;
    }


    public static class Tagger {
        int startRow;
        int startCol;
        int[] fRow = {-1, 0, 1, 0};
        int[] fCol = {0, 1, 0, -1};
        int[] rRow = {-1, 0, 1, 0};
        int[] rCol = {0, 1, 0, -1};

        int row;
        int col;
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        int direct = 0;
        int dist = 1;
        boolean reverse = false;
        int score = 0;

        public Tagger(int row, int col) {
            this.row = row;
            this.col = col;
            this.startRow = row;
            this.startCol = col;
        }

        public void getDirect(int row, int col) {
            int nextRow = row + dRow[direct];
            int nextCol = col + dCol[direct];

            int dist = Math.max(Math.abs(this.row - nextRow), Math.abs(this.col - nextCol));
            if (dist > this.dist) {
                direct = (direct + 1) % 4;
                if (direct == 0 && reverse) {
                    dist++;
                } else if (direct == 0) {
                    dist--;
                }
            }
        }

        public void move(Node[][] nodeArr) {
            int nextRow = this.row + dRow[direct];
            int nextCol = this.col + dCol[direct];

            nodeArr[this.row][this.col].tagger = false;
            nodeArr[nextRow][nextCol].tagger = true;

            getDirect(nextRow, nextCol);

            this.row = nextRow;
            this.col = nextCol;
            if (this.row == 0 && this.col == 0) {
                reverse = true;
                this.dRow = this.rRow;
                this.dCol = this.rCol;
            }
            if (this.row == startRow && this.col == startCol) {
                reverse = false;
                this.dRow = this.fRow;
                this.dCol = this.fCol;
            }
        }

        public void search(Node[][] nodeArr) {

            int searchRow = this.row;
            int searchCol = this.col;


        }
    }
}
