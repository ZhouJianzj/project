package com.zj.entity;

import com.zj.util.MyPageHelper;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("four");
        strings.add("five");
        strings.add("six");

        System.out.println(MyPageHelper.myPageHelper(new Page(strings, 1, 7)));


    }

}
