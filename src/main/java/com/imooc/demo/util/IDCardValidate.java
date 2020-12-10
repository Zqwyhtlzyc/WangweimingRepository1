package com.imooc.demo.util;

import org.apache.commons.lang3.StringUtils;
  
/** 
 * 身份证号码验证 
 *  
 */  
public class IDCardValidate {  
  
    public static final int IDENTITYCODE_OLD = 15; // 老身份证15位  
    public static final int IDENTITYCODE_NEW = 18; // 新身份证18位  
    public static int[] Wi = new int[17];  
  
    /** 
     * 判断身份证号码是否正确。 
     *  
     * @param code 
     *            身份证号码。 
     * @return 如果身份证号码正确，则返回true，否则返回false。 
     */  
    public static boolean isIdentityCode(String idCardNo) {  
    	try{
    		// 对身份证号进行长度等简单判断
            if (idCardNo == null )
            {
               return false;
            }else if(idCardNo.length() == IDENTITYCODE_OLD){
            	idCardNo = update2eighteen(idCardNo);
            	return validateIdCardEighteen(idCardNo);
            }else if(idCardNo.length() == IDENTITYCODE_NEW){
            	return validateIdCardEighteen(idCardNo);
            }else{
            	return false;
            }
    	}catch(Exception e){
    		
    		return false;
    	}
    }  
    
    private static boolean validateIdCardEighteen(String idCardEighteen){
    	// 对身份证号进行长度等简单判断
        if (idCardEighteen == null || idCardEighteen.length() != 18 || !idCardEighteen.matches("\\d{17}[0-9X]"))
        {
           return false;
        }
        // 1-17位相乘因子数组
        int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        // 18位随机码数组
        char[] random = "10X98765432".toCharArray();
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < 17; i++)
        {
           total += Character.getNumericValue(idCardEighteen.charAt(i)) * factor[i];
        }
        // 判断随机码是否相等
        return random[total % 11] == idCardEighteen.charAt(17);
    }
  
    /** 
     * 获取新身份证的最后一位:检验位 
     *  
     * @param code 
     *            18位身份证的前17位 
     * @return 新身份证的最后一位 
     */  
    private static String getCheckFlag(String code) {  
        int[] varArray = new int[code.length()];  
        String lastNum = "";  
        int numSum = 0;  
        // 初始化位权值  
        setWiBuffer();  
        for (int i = 0; i < code.length(); i++) {  
            varArray[i] = Integer.parseInt("" + code.charAt(i));  
            varArray[i] = varArray[i] * Wi[i];  
            numSum = numSum + varArray[i];  
        }  
        int checkDigit = 12 - numSum % 11;  
        switch (checkDigit) {  
        case 10:  
            lastNum = "X";  
            break;  
        case 11:  
            lastNum = "0";  
            break;  
        case 12:  
            lastNum = "1";  
            break;  
        default:  
            lastNum = String.valueOf(checkDigit);  
        }  
        return lastNum;  
    }  
  
    /** 
     * 初始化位权值 
     */  
    private static void setWiBuffer() {  
        for (int i = 0; i < Wi.length; i++) {  
            int k = (int) Math.pow(2, (Wi.length - i));  
            Wi[i] = k % 11;  
        }  
    }  
  
  
    /** 
     * 将15位身份证号码升级为18位身份证号码 
     *  
     * @param code 
     *            15位身份证号码 
     * @return 18位身份证号码 
     */  
    private static String update2eighteen(String code) {  	
        if (StringUtils.isEmpty(code)) {  
            return "";  
        }   
        code = code.trim();  
        if (code.length() != IDENTITYCODE_OLD ) {  
            return "";  
        }
        code = code.substring(0, 6) + "19" + code.substring(6);  
        //生成校验位  
        code = code + getCheckFlag(code);  
        return code;  
    }  
      
    public static void main(String[] args){  
        String[] codeArray = new String[]{"330521197411044030","652101810918081","65210119810918081X"};  
        for(int i= 0;i<codeArray.length;i++){  
            if(isIdentityCode(codeArray[i])){  
                System.out.println(codeArray[i]+"：为正确的身份证号码！");  
            }else{  
                System.out.println(codeArray[i]+"：为错误的身份证号码！");  
            }     
        }  
          
        System.out.println("转换后的身份证号码为："+update2eighteen("652101810918081"));  
  
    }  
}  