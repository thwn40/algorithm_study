package Inhwan.week16;

import java.util.Arrays;

class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        char[] sixteen = "0123456789ABCDEF".toCharArray();
        char[] n_base = Arrays.copyOfRange(sixteen, 0, n);

        String asn = "";

        for (int i = 0; i < t; i++) {
            asn += find(n_base, n, p);
            p += m;
        }

        return asn;
    }

    void addIndex(int[] index, int n, int m) {
        int i = index.length-1;

        index[i] += m;
        while (index[i] >= n) {
            index[i-1] += index[i]/n;
            index[i] %= n;
            i--;
        }
    }

    char find(char[] n_base, int n, int p) {
        p--;
        if (p < n) return n_base[p];

        p -= n;
        int i = 2;
        int k = n*(n-1);

        while (p >= i*k) {
            p -= i*k;
            k *= n;
            i++;
        }

        int[] index = new int[i];
        index[0] = 1;

        addIndex(index, n, p/i);

        return n_base[index[p%i]];
    }
}