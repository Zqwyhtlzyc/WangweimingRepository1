package com.imooc.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用工具类，一般添加为不常指定(如：日期、数字计算等有专门对应的工具类)的工具方法
 * @ClassName CommonUtil
 * @author An Zhongyao
 * @Date 2017年8月8日 下午5:29:30
 * @version 1.0.0
 */
public class CommonUtil {
	/**
	 * 字符串是否只包含a-z、A-Z、0-9、+、-
	 * @param str
	 * @return
	 */
	public static boolean isOnlyContainChar(String str){
		if(StringUtils.isBlank(str))return false;
		Pattern p=Pattern.compile("[A-Za-z0-9\\+-]*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 判断是否以a-z、A-Z开头
	 * @param str
	 * @return
	 */
	public static boolean isStartWithChar(String str){
		if(StringUtils.isBlank(str))return false;
		Pattern p=Pattern.compile("^[A-Za-z]");
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**
	 * 判断是否以数字开头
	 * @param str
	 * @return
	 */
	public static boolean isStartWithNumber(String str){
		if(StringUtils.isBlank(str))return false;
		Pattern p=Pattern.compile("^[0-9]");
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**
	 * 校验字符串中是否含有汉子
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
	
	/**
	 * 校验账户的合法性，该字符串只允许字符、数字、_、@、.-<br/>
	 * 并且只能以字符、数字开头
	 * @param str
	 * @return
	 */
	public static boolean isRightAccount(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern p=Pattern.compile("^[A-Za-z0-9][A-Za-z0-9@_\\.-]*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 密码必须要包含大写字符、小写字符、数字三部分
	 * @param str
	 * @return
	 */
	public static boolean isRightPwd(String str){
		//密码长度
		if(StringUtils.isBlank(str)||str.length()<8||str.length()>20){
			return false;
		}
		return checkPassIsLegal(str);
	}
	
	/**
	 * 校验密码复杂度,密码规则：必须要包含字符、数字两部分，特殊字符目前不做必须要求
	 * @param pwd
	 * @return
	 */
	private static boolean checkPassIsLegal(String pwd){
		 //密码长度
		 int len = 0;
		 //包含字符
		 String reg_1="([A-Za-z])+";
		 Pattern pattern=Pattern.compile(reg_1);
		 Matcher matcher=pattern.matcher(pwd);
		 if(matcher.find()){
			 len++;
		 }
		 //包含数字
		 String reg_2="([0-9])+";
		 Pattern pattern2=Pattern.compile(reg_2);
		 Matcher matcher2=pattern2.matcher(pwd);
		 if(matcher2.find()){
		     len++; 
		 }
		 return len<2?false:true;
	}
	
	public static boolean isChinese(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
	    String reg = "[\\u4e00-\\u9fa5]+" ; 
	    return str.matches(reg);
	}
	public static void main(String[] args) {
		System.out.println(isChinese("中过热阿斯蒂芬亲吻鹅"));
	}
}
