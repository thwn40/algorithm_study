package level18_그리디알고리즘_Clear.Q5_13305;

/*
문제) 주유소
    어떤 나라에 N개의 도시가 있다.
    이 도시들은 일직선 도로 위에 있다.
    편의상 일직선을 수평 방향으로 두자.
    제일 왼쪽의 도시에서 제일 오른쪽의 도시로 자동차를 이용하여 이동하려고 한다.
    인접한 두 도시 사이의 도로들은 서로 길이가 다를 수 있다.
    도로 길이의 단위는 km를 사용한다.
    처음 출발할 때 자동차에는 기름이 없어서 주유소에서 기름을 넣고 출발하여야 한다.
    기름통의 크기는 무제한이어서 얼마든지 많은 기름을 넣을 수 있다.
    도로를 이용하여 이동할 때 1km마다 1리터의 기름을 사용한다.
    각 도시에는 단 하나의 주유소가 있으며, 도시 마다 주유소의 리터당 가격은 다를 수 있다.
    가격의 단위는 원을 사용한다.

    예를 들어, 이 나라에 다음 그림처럼 4개의 도시가 있다고 하자.
    원 안에 있는 숫자는 그 도시에 있는 주유소의 리터당 가격이다.
    도로 위에 있는 숫자는 도로의 길이를 표시한 것이다.
    제일 왼쪽 도시에서 6리터의 기름을 넣고, 더 이상의 주유 없이 제일 오른쪽 도시까지 이동하면 총 비용은 30원이다.
    만약 제일 왼쪽 도시에서 2리터의 기름을 넣고(2×5 = 10원) 다음 번 도시까지 이동한 후 3리터의 기름을 넣고(3×2 = 6원)
    다음 도시에서 1리터의 기름을 넣어(1×4 = 4원) 제일 오른쪽 도시로 이동하면, 총 비용은 20원이다.
    또 다른 방법으로 제일 왼쪽 도시에서 2리터의 기름을 넣고(2×5 = 10원) 다음 번 도시까지 이동한 후
    4리터의 기름을 넣고(4×2 = 8원) 제일 오른쪽 도시까지 이동하면, 총 비용은 18원이다.
    각 도시에 있는 주유소의 기름 가격과, 각 도시를 연결하는 도로의 길이를 입력으로 받아
    제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용을 계산하는 프로그램을 작성하시오.

입력)
    표준 입력으로 다음 정보가 주어진다.
    첫 번째 줄에는 도시의 개수를 나타내는 정수 N(2 ≤ N ≤ 100,000)이 주어진다.
    다음 줄에는 인접한 두 도시를 연결하는 도로의 길이가 제일 왼쪽 도로부터 N-1개의 자연수로 주어진다.
    다음 줄에는 주유소의 리터당 가격이 제일 왼쪽 도시부터 순서대로 N개의 자연수로 주어진다.
    제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 1이상 1,000,000,000 이하의 자연수이다.
    리터당 가격은 1 이상 1,000,000,000 이하의 자연수이다.

출력)
    표준 출력으로 제일 왼쪽 도시에서 제일 오른쪽 도시로 가는 최소 비용을 출력한다.

*/

/*
서브태스크)
        1. 17점 : 모든 주유소의 리터당 가격은 1원이다.
        2. 58점 : 2 ≤ N ≤ 1,000, 제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 최대 10,000, 리터 당 가격은 최대 10,000이다.
        3. 100점 : 원래의 제약조건 이외에 아무 제약조건이 없다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*
    Sudo Code)
        1. 입력 값을 받는다
            (
                첫째줄[numOfCity] - int : 도시 개수
                둘째줄[endKmTokenizer] - StringTokenizer : 도시거리
                셋째줄[oilPriceTokenizer] - StringTokenizer : 도시별 기름가격
            )

        2. 거리와 기름가격을 담을 수 있는 City 클래스를 생성해준다.

        3. [cityList] - City 타입의 ArrayList를 만들어준다.

        4. 변수를 선언한다. (서브태스크를 고려해 long 타입으로 선언한다)
            d - long : 도시거리 - 저장용(endKmTokenizer.nextToken())
            p - long : 기름가격 - 저장용(oilPriceTokenizer.nextToken())
            totalPrice - long : 총 비용

            dist - long : 도시거리 - 비교를 위한 변수
            price - long : 기름가격 - 비교를 위한 변수
            구간 별 기름 값 : 도시거리 * 기름가격

        5. 마지막 도시는 주유를 하지 않으므로 numOfCity - 1 만큼 for문으로 cityList에 값을 넣어준다.

        6. price 와 dist를 각각 cityList 0번의 price와 distance를 가져와 초기화해준다.

        7. totalPrice 에 초기화된 price * dist의 계산 값을 넣어준다. (0번 인덱스 City의 구간 지출 비용 계산 값)

        8. i는 1부터  i < numOfCity - 1만큼 for문을 돌려 cityList.get(i).price < price일 경우,
           price의 값을 cityList.get(i).price로 변경해준다.

        9. 변경된 price와 cityList.get(i).distance를 곱해 totalPrice에 값을 누적시킨다.

        10. totalPrice 출력

    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfCity = Integer.parseInt(br.readLine());
        StringTokenizer endKmTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer oilPriceTokenizer = new StringTokenizer(br.readLine());

        ArrayList<City> cityList = new ArrayList<>();
        long dist = 0L;
        long price = 0L;
        long totalPrice = 0L;
        long d,p;

        for (int i = 0; i < numOfCity - 1; i++) {
            d = Long.valueOf(endKmTokenizer.nextToken());
            p = Long.valueOf(oilPriceTokenizer.nextToken());
            cityList.add(new City(d, p));
        }

        price = cityList.get(0).price;
        dist = cityList.get(0).distance;
        totalPrice = price * dist;

        for (int i = 1; i < numOfCity - 1; i++) {

            if (cityList.get(i).price < price) {
                price = cityList.get(i).price;
            }
            dist = cityList.get(i).distance;
            totalPrice += price * dist;
        }
        System.out.println(totalPrice);
    }
}

class City {
    long distance = 0;
    long price = 0;

    public City (long distance, long price) {
        this.distance = distance;
        this.price = price;
    }
}


