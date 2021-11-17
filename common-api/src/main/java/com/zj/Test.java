package com.zj;

import com.zj.util.FileUtil;

public class Test {
    public static void main(String[] args) {
        System.out.println(FileUtil.generateFileName("D", "var"));

        int i  = 1;
        int a = 1;
        ++a;
        i++;
        System.out.println(i);
        System.out.println(a);
    }
}
