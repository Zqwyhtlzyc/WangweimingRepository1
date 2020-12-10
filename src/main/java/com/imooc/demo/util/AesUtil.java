package com.imooc.demo.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * aes加解密
 */
public class AesUtil { 
	
	protected static Logger logger = LoggerFactory.getLogger(AesUtil.class);
	//正式运行后请勿更改，否则会导致以前加密的数据无法解密  ，如果需要修改，请采用方法aesEncryptByKey
	protected static String KEY="rt!rw%#y9rU8oIajuYrzsu&";
	protected static String IV="9*76kTY(uy&%8p#$";
    protected static String CODE_UTF8 = "UTF-8";
	
    /**
     * 加密后返回十六进制字符串
     * @param content
     * @return
     */
    public static String aesEncryptStr(String content){
    	try{
    		byte[] encrypted=aesEncrypt(content.getBytes(CODE_UTF8));  
    		return byteToHexString(encrypted);
    	}catch(Exception e){
    		logger.error("加密失败【"+content+"】",e);
    		return null;
    	}
    }
    
    /**
     * 解密后返回utf8字符串
     * @param encContent
     * @return
     */
    public static String aesDecryptStr(String encContent){
    	try{
    		byte[] decrypted=aesDecrypt(hexStringToBytes(encContent));
        	return new String(decrypted,CODE_UTF8);
    	}catch(Exception e){
    		logger.error("解密失败【"+encContent+"】",e);
    		return null;
    	}
    }
    
    /**
     * 加密，返回byte
     * @param content
     * @return
     */
    public static byte[] aesEncrypt(byte[] content){  
        try{
        	byte[] keyBytes = KEY.getBytes(CODE_UTF8); 
        	byte[] ivBytes = IV.getBytes(CODE_UTF8);
        	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(keyBytes);
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
            keyGenerator.init(128, random);  
            SecretKey key=keyGenerator.generateKey();  
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));  
            byte[] result=cipher.doFinal(content);  
            return result;  
        }catch (Exception e) {  
        	logger.error("byte加密失败",e);
        	return null; 
        }
    }  
    
    /**
     * 解密，入参为byte
     * @param content
     * @return
     */
    public static byte[] aesDecrypt(byte[] content){ 
        try{  
        	byte[] keyBytes = KEY.getBytes(CODE_UTF8); 
        	byte[] ivBytes = IV.getBytes(CODE_UTF8);
        	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(keyBytes);
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
            keyGenerator.init(128, random);//key长设为128
            SecretKey key=keyGenerator.generateKey();  
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));  
            byte[] result=cipher.doFinal(content);  
            return result;  
        }catch (Exception e) {  
        	logger.error("byte解密失败",e);
            return null;
        }   
    }  
    
    /**
     * 加密，key自定义
     * @param content
     * @param keyBytes
     * @param ivBytes
     * @return
     */
    public static byte[] aesEncryptByKey(byte[] content,byte[] keyBytes,byte[] ivBytes){  
        try{
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
            keyGenerator.init(128, new SecureRandom(keyBytes));  
            SecretKey key=keyGenerator.generateKey();  
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));  
            byte[] result=cipher.doFinal(content);  
            return result;  
        }catch (Exception e) {  
        	logger.error("byte加密失败",e);
        	return null; 
        }
    }  
    
    /**
     * 解密，key自定义
     * @param content
     * @param keyBytes
     * @param ivBytes
     * @return
     */
    public static byte[] aesDecryptByKey(byte[] content,byte[] keyBytes,byte[] ivBytes){ 
        try{  
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");  
            keyGenerator.init(128, new SecureRandom(keyBytes));//key长设为128
            SecretKey key=keyGenerator.generateKey();  
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));  
            byte[] result=cipher.doFinal(content);  
            return result;  
        }catch (Exception e) {  
        	logger.error("byte解密失败",e);
            return null;
        }   
    }
          
    public static String byteToHexString(byte[] bytes) {  
        StringBuffer sb = new StringBuffer(bytes.length);  
        String sTemp;  
        for (int i = 0; i < bytes.length; i++) {  
            sTemp = Integer.toHexString(0xFF & bytes[i]);  
            if (sTemp.length() < 2)  
                sb.append(0);  
            sb.append(sTemp.toUpperCase());  
        }  
        return sb.toString();  
    }  
    
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    
    
    public static void main(String[] args) {    
        String content="234567898asdf 撒打发士大夫士大夫#￥%……&*35643234543245";  
          
//        System.out.println("加密前："+byteToHexString(content.getBytes(CODE_UTF8)));  
//        byte[] encrypted=AesCbcEncrypt(content.getBytes());  
//        System.out.println("加密后："+byteToHexString(encrypted));  
//        byte[] decrypted=AesCbcDecrypt(encrypted);  
//        System.out.println("解密后："+byteToHexString(decrypted));  
//        System.out.println(new String(decrypted,CODE_UTF8));
        String encontent = aesEncryptStr(content);
        System.out.println("加密后："+encontent);
        System.out.println("加密后长度："+encontent.length());
        
        String dec =  aesDecryptStr(encontent);
        System.out.println(dec);
        
    }
}  