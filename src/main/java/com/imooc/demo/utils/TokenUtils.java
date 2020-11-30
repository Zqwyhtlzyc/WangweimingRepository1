package com.imooc.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class TokenUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24; //token过期时间
    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥

    private static JWTVerifier verifier;

    static {
        try{
            verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param fields
     * @return
     */
    public static String creaToken(Map<String,String> fields){
        //生成时间
        Date issueDate = new Date();

        HashMap<String,Object> headerMap = new HashMap<>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");

        JWTCreator.Builder builder = JWT.create().withHeader(headerMap).withIssuedAt(issueDate);

        for(Map.Entry<String,String> next : fields.entrySet()){
            builder.withClaim(next.getKey(),next.getValue());
        }
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verifyToken(String token){
        try{
            verifier.verify(token);
        }catch (Exception ex){
            System.out.println("token解析失败");
            return false;
        }
        return true;
    }

    /**
     * 从payLoad中获取指定的key的值
     * @param token
     * @param key
     * @return
     */
    public static String getClaim(String token,String key){
        return verifier.verify(token).getClaim(key).asString();
    }

    /**
     * 过去payLoad中所有的字段
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaim(String token){
        return verifier.verify(token).getClaims();
    }

    /**
     * 校验并且返回解码后的token
     * 校验失败返回null
     * @param token
     * @return
     */
    public static DecodedJWT decodedToken(String token){
        try{
            return verifier.verify(token);
        }catch (RuntimeException ex){
            System.out.println("token校验失败"+ ex.getMessage());
            return null;
        }
    }
}
