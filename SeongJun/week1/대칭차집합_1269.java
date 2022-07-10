package BackJoonAlgorithm.AlgorithmStudy;

import javax.accessibility.AccessibleRelationSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 대칭차집합_1269 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numA = Integer.parseInt(st.nextToken());
        int numB = Integer.parseInt(st.nextToken());


        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();
        HashSet<Integer> aClone = new HashSet<>();

        st=new StringTokenizer(br.readLine());
        int temp =0;
        for (int i = 0; i < numA; i++) {
            temp =Integer.parseInt(st.nextToken());
            A.add(temp);
            aClone.add(temp);
        }

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < numB; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        A.removeAll(B);
        B.removeAll(aClone);



        System.out.println(A.size()+B.size());

    }
}
