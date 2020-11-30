package com.imooc.demo.controller;

public class test3 {
    public static void main(String[] args) {
        Integer vle = 100;
        Integer trueVle =0;
        String strVle = "0";
        for(int i=0;i<=vle;i++){
            strVle=String.valueOf(i);
            if(strVle.contains("5")){
                continue;
            }else {
                trueVle++;
            }
        }
        System.out.println(trueVle-1);
    }

}
