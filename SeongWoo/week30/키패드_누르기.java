package SeongWoo.week30;

public class 키패드_누르기 {

    public static class Hand {
        int number;
        int row;
        int col;
        String get;

        public Hand(int number, int row, int col, String get) {
            this.number = number;
            this.row = row;
            this.col = col;
            this.get = get;
        }

        public int getDist(int row, int col) {
            return Math.abs(this.row - row) + Math.abs(this.col - col);
        }
    }


    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int[] rowNumberArr = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] colNumberArr = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2};

        Hand rightHand = new Hand(-2, 3, 2, "R");
        Hand leftHand = new Hand(-1, 3, 0, "L");

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            Hand resultHand = rightHand;

            if (number == 1 || number == 4 || number == 7) {
                resultHand = leftHand;
            }

            if (number == 2 || number == 5 || number == 8 || number == 0) {
                int rightDist = rightHand.getDist(rowNumberArr[number], colNumberArr[number]);
                int leftDist = leftHand.getDist(rowNumberArr[number], colNumberArr[number]);

                if (leftDist < rightDist) {
                    resultHand = leftHand;
                }
                if (leftDist == rightDist) {
                    if (hand.equals("left")) {
                        resultHand = leftHand;
                    }
                }
            }

            resultHand.number = number;
            resultHand.row = rowNumberArr[number];
            resultHand.col = colNumberArr[number];
            sb.append(resultHand.get);
        }

        return sb.toString();
    }
}
