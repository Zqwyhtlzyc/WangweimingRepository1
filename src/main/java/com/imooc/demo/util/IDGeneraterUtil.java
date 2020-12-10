package com.imooc.demo.util;

import com.imooc.demo.config.Exception.CommException;

/**
 * ID 工具类
 * @ClassName IdGeneraterUtil
 */
public class IDGeneraterUtil {
	private final static long HEX_NUMBER_1=0X3b9ac9ffL;
	//此值不可修改，修改后，需要统一更新新的roomId，不然会无法导入对账明细
//	private static final String IM_NOT_A_PREFIX = "maybe&NdAPrefixConfixThe$";

	/**
	 * 将ID 序列值转化为加密之后的数值
	 * @param number
	 * @return
	 */
	public static String transform2String(long number,boolean isRandom){
		//首先对number取亦或值
		long tNumber=number^HEX_NUMBER_1;
		//获取tNumber1的hashCode值
		long hash1=Math.abs(String.valueOf(tNumber).hashCode());
		//计算64进制结果值
		String tNumberTo64=NumberUtil.to64Encode(tNumber);
		String hash1To64=NumberUtil.to64Encode(hash1);
		//获取hashCode2
		long hash2=Math.abs((tNumberTo64+hash1To64).hashCode());
		String hash2To64=NumberUtil.to64Encode(hash2);
		StringBuilder builder=new StringBuilder();
		builder.append(lengTo64(tNumberTo64.length(),isRandom))
		.append(tNumberTo64)
		.append(lengTo64(hash1To64.length(),isRandom))
		.append(hash1To64)
		.append(hash2To64)
		.append(lengTo64(hash2To64.length(),isRandom));
		return builder.toString();
	}
	
	/**
	 * 校验字符串是否合法
	 * @param str
	 * @return
	 */
	public static long transform2Number(String str,boolean isRandom){
		if(!CommonUtil.isOnlyContainChar(str)){
			throw new CommException("FAIl");
		}
		//对字符串进行解析
		//游标位置
		int cursor=0;
		String headers=null;
		int hlength=0;
		if(isRandom){
			headers=str.substring(1, 3);
			cursor+=1+headers.length();
			//解析ID 64位长度
			if(!CommonUtil.isStartWithNumber(headers)||headers.trim().length()<2){
				throw new CommException("FAIl");
			}
			int hexHeader=(int) NumberUtil.to64Decode(String.valueOf(headers.charAt(1)));
			hlength=(hexHeader^63)-Character.digit(headers.charAt(0), 10);
		}else{
			headers=str.substring(0, 1);
			cursor+=headers.length();
			if(headers.trim().length()!=1){
				throw new CommException("FAIl");
			}
			int hexHeader=(int) NumberUtil.to64Decode(headers);
			hlength=(hexHeader^63);
		}
		String idTo64String=str.substring(cursor, cursor+hlength);
		cursor+=hlength;
		int hashLength=0;
		if(isRandom){
			//获取hash1
			cursor+=1;
			String hashHeaders=str.substring(cursor, cursor+2);
			cursor+=2;
			if(!CommonUtil.isStartWithNumber(hashHeaders)||hashHeaders.trim().length()<2){
				throw new CommException("FAIl");
			}
			int hexHashHeader=(int) NumberUtil.to64Decode(String.valueOf(hashHeaders.charAt(1)));
			hashLength=(hexHashHeader^63)-Character.digit(hashHeaders.charAt(0), 10);
		}else{
			String hashHeaders=str.substring(cursor, cursor+1);
			cursor+=1;
			if(hashHeaders.trim().length()!=1){
				throw new CommException("FAIl");
			}
			int hexHashHeader=(int) NumberUtil.to64Decode(hashHeaders);
			hashLength=(hexHashHeader^63);
		}
		String hash1To64String=str.substring(cursor, cursor+hashLength);
		//获取hash2长度
		cursor=str.length();
		int hash2Length=0;
		if(isRandom){
			String hash2Headers=str.substring(cursor-2, cursor);
			cursor-=2;
			if(!CommonUtil.isStartWithNumber(hash2Headers)||hash2Headers.trim().length()<2){
				throw new CommException("FAIl");
			}
			int hexHash2Header=(int) NumberUtil.to64Decode(String.valueOf(hash2Headers.charAt(1)));
			hash2Length=(hexHash2Header^63)-Character.digit(hash2Headers.charAt(0), 10);
			cursor-=1;
		}else{
			String hash2Headers=str.substring(cursor-1, cursor); 
			cursor-=1;
			if(hash2Headers.trim().length()!=1){
				throw new CommException("FAIl");
			}
			int hexHash2Header=(int) NumberUtil.to64Decode(hash2Headers);
			hash2Length=(hexHash2Header^63);
		}
		//获取hash2
		String hash2To64String=str.substring(cursor-hash2Length, cursor);
		if(Math.abs((idTo64String+hash1To64String).hashCode())!=NumberUtil.to64Decode(hash2To64String)){
			throw new CommException("FAIl");
		}
		long idNumber=NumberUtil.to64Decode(idTo64String);
		if(Math.abs(String.valueOf(idNumber).hashCode())!=NumberUtil.to64Decode(hash1To64String)){
			throw new CommException("FAIl");
		}
		return idNumber^HEX_NUMBER_1;
	}
	
	/**
	 * 校验ID序列值是否合法<br/>
	 * 注意：如果是先检查是否合法，再获取真正的ID序列值操作只需要直接调用：transform2Number方法，捕获异常处理即可（不合法情况下会抛出异常）！
	 * @param s
	 * @throws Exception 
	 */
	public static boolean verify(String str,boolean isRandom) throws Exception {
		try {
			transform2Number(str,isRandom);
		}catch (Exception e) {
			if(!(e instanceof CommException)){
				throw e;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 转换记录长度的数值为64进制,只适合长度不超过50位的计算（目前long转化计算的64进制不可能大于50位）
	 * @param length
	 * @return
	 */
	private static String lengTo64(int length,boolean isRandom){
		StringBuilder builder=new StringBuilder();
		int tmp=0;
		if(isRandom){
			builder.append(NumberUtil.getRandomChar());
			int random=(int) NumberUtil.getRandom(1);
			builder.append(random);
			tmp=(length+random)^63;
		}else{
			tmp=length^63;
		}
		builder.append(NumberUtil.to64Encode(tmp));
		return builder.toString();
	}
	
	/**
	 * 根据小区id,平台楼宇号(buildingNo),平台房屋号(roomNo) 生成唯一roomNo
	 * @return
	 */
	public static String generateRoomId(Long commId,String buildingNo,String roomNo){
		String sourceStr = commId+""+buildingNo+""+roomNo;
		return sourceStr;
//				MD5Encryption.encrypt(sourceStr,"UTF-8");
	}
	
	/**
	 * 导入数据，根据roomId校验数据
	 * @param roomId
	 * @param commId
	 * @param buildingNo
	 * @param roomNo
	 * @return
	 */
	public static boolean checkRoomId(String roomId,Long commId,String buildingNo,String roomNo){
		boolean flag = false;
		String sourceStr = generateRoomId(commId,buildingNo,roomNo);
		if(roomId.equals(sourceStr)){
			flag = true;
		}
		return flag;
	}

}
