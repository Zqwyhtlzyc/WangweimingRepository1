package com.imooc.demo.controller;

import java.util.HashMap;
import java.util.Map;

public class testContriller1 {
    public static void main(String[] args){
//        float fl = 5.5f;
//        String str = String.valueOf(fl);
//        String str1 = "abcd";
//        String str2  = str1.substring(1);
//        String str3  = str1.substring(0,1);
//        String[] strs = str1.split("//");
//        String res1 = strs[0];
//        String res2 = strs[1];
//        Integer in1 = Integer.valueOf(res1);
//        Integer in2 = Integer.valueOf(res2);
//        if(in2>=5){
//            System.out.print(in1+1);
//        }else{
//            System.out.print(in1);
//        }


//        String str  = String.valueOf(2752771);
//        String[] strs = str.split(" ");
//        strs.
//        //String[] strs =null;
//        StringBuffer strB = new StringBuffer(str);
//        String str1= String.valueOf(strB.reverse());
//        Map<String,Integer> map = new HashMap();
//        for(int i=0;i<str1.length();i++){
//            String value = str1.substring(0,1);
//            str1 =str1.substring(1);
//            if(!map.containsKey(value)){
//                map.put(value,Integer.valueOf(value));
//            }
//        }
//        for(String st : map.keySet()){
//            System.out.print(map.get(st));
//        }

//        Integer in = 5;
//        String strstr =Integer.toBinaryString(in,8);
//
//
//        //Scanner sc = new Scanner(System.in);
//        String str ="I am a boy";
//        String[] strs = str.split(" ");
//        int le = strs.length;
//        for(int i=(le-1);i>=0;i--){
//            System.out.print(strs[i]+" ");
//        }

        String str = "A10;S20;W10;D30;X;A1A;B10A11;;A10;";
        String[] strs = str.split(";");
        Map<String,Integer> resultMap =new HashMap();
        resultMap.put("L",0);
        resultMap.put("R",0);
        for(int i=0;i<strs.length;i++){
            String result = strs[i];
            if(result.length()>1&&result.length()<=3){
                String key = result.substring(0,1);
                String val = result.substring(1,result.length());
                String regex = "[0-9]{1,}";
                if(val.matches(regex)){
                    if("A".equals(key)){
                        Integer liftVal = resultMap.get("L");
                        liftVal = liftVal - Integer.valueOf(val);
                        resultMap.put("L",liftVal);
                    }else if("D".equals(key)){
                        Integer liftVal = resultMap.get("L");
                        liftVal = liftVal + Integer.valueOf(val);
                        resultMap.put("L",liftVal);
                    }else if("W".equals(key)){
                        Integer liftVal = resultMap.get("R");
                        liftVal = liftVal + Integer.valueOf(val);
                        resultMap.put("R",liftVal);
                    }else if("S".equals(key)){
                        Integer liftVal = resultMap.get("R");
                        liftVal = liftVal - Integer.valueOf(val);
                        resultMap.put("R",liftVal);
                    }
                }
            }
        }
        System.out.print(resultMap.get("L")+","+resultMap.get("R"));


    }
}
