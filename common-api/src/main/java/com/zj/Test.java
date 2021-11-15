package com.zj;

import com.zj.util.FileNameUtil;

public class Test {
    public static void main(String[] args) {
        System.out.println(FileNameUtil.generateFileName("D", "var"));

        int i  = 1;
        int a = 1;
        ++a;
        i++;
        System.out.println(i);
        System.out.println(a);
    }
}
