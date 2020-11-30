package com.imooc.demo.controller;

public class test2 {
    public static void main(String[] args) {
        Integer vle = 100;
        Integer trueVle =0;
        String strVle = "0";
        trueVle  =getTrue( vle);
    }
    private static Integer getTrue(Integer vle){
        int in = (vle-4)/10;
        if(in>0){
            getTrue(in);
        }
        System.out.println(vle);
        return vle;
    }
}
