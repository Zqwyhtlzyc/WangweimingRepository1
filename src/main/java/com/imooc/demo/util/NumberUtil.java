package com.imooc.demo.util;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字处理工具类
 */
public class NumberUtil {
	/** 64进制表示字符 */
	final static char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', '+', '-' };
	/** 英文字符数组*/
	final static char[] CHARARRAY={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z'};

	/**
	 * 将10进制转化为64进制
	 * 
	 * @param value
	 * @return
	 */
	public static String to64Encode(long value) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << 6;
		long mask = radix - 1;
		do {
			buf[--charPos] = DIGITS[(int) (value & mask)];
			value >>>= 6;
		} while (value != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 将64进制字符串解析为10进制数字<br/>
	 * 注意:如果64进制字符串超出了Long数据类型的大小就会产生溢出！
	 * 
	 * @param str
	 * @return
	 */
	public static long to64Decode(String str) {
		int fromBase = 64;
		str = str.trim();
		if (StringUtils.isBlank(str)) {
			return 0L;
		}
		String sDigits = new String(DIGITS, 0, fromBase);
		long result = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!sDigits.contains(String.valueOf(ch))) {
				throw new ArithmeticException(String.format("The argument %s is not in %s system", ch, fromBase));
			}
			for (int j = 0; j < DIGITS.length; j++) {
				if (DIGITS[j] == str.charAt(i)) {
					result += ((long) j) << 6 * (str.length() - 1 - i);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取随机数
	 * @param length
	 * @return
	 */
	public static long getRandom(int length) {
		StringBuffer sb = new StringBuffer();
		int num;
		for (int i = 0; i < length; i++) {
			num = (int) (10 * (Math.random()));
			sb.append(num);
		}
		String random = sb.toString();
		if (random.startsWith("0")) {
			return getRandom(length);
		}
		return Long.valueOf(random);
	}

	/**
	 * 生成指定范围的随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInteger(int min, int max) {
		Random random = new Random();
		int value = random.nextInt(max) % (max - min + 1) + min;
		return value;
	}
	
	/**
	 * 获取一个随机字符
	 * @return
	 */
	public static String getRandomChar(){
		int randomValue=getRandomInteger(0, 51);
		return String.valueOf(CHARARRAY[randomValue]);
	}
}
