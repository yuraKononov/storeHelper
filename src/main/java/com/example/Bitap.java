package com.example;

public class Bitap {

    public static int find(String doc, String pattern, int k) {

        int alphabetRange = 10000;
        int firstMatchedText = -1;

        long[] r = new long[k + 1];
        int res = 0;

        long[] patternMask = new long[alphabetRange];
        for (int i = 0; i <= k; i++) {
            r[i] = 1;
        }

        for (int i = 0; i < pattern.length(); ++i) {
            patternMask[(int) pattern.charAt(i)] |= 1 << i;
        }
        int i = 0;

        while (i < doc.length()) {

            long old = 0;
            long nextOld = 0;

            for (int d = 0; d <= k; ++d) {
                long sub = (old | (r[d] & patternMask[doc.charAt(i)])) << 1;
                long ins = old | ((r[d] & patternMask[doc.charAt(i)]) << 1);
                long del = (nextOld | (r[d] & patternMask[doc.charAt(i)])) << 1;
                old = r[d];
                r[d] = sub | ins | del | 1;
                nextOld = r[d];
            }
            if (0 < (r[k] & (1 << pattern.length()))) {
                if ((firstMatchedText == -1) || (i - firstMatchedText > pattern.length())) {
                    firstMatchedText = i;
                    ++res;
                }
            }
            i++;
        }
        return res;
    }
}