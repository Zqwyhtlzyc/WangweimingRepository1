package com.imooc.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) throws Exception{

        String[] strs = new String[]{"aa","bb","cc"};
        String str = new String("dd");
        Long longStr = Long.valueOf(str);
        List<String> stringList = Arrays.stream(strs).collect(Collectors.toList());


    }


}
