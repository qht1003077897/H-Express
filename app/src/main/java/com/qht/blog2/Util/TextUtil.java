package com.qht.blog2.Util;

import java.util.regex.Pattern;

public class TextUtil {

	/**
	 * 类名：textUtil
	 * 说明：判断字符串是否空
	 */
	public static boolean isEmpty(String s) {
		if (null == s)
			return true;
		if (s.length() == 0)
			return true;
		if (s.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 类名：textUtil
	 * 说明：验证账号 密码是否合法（6-18位数字字母下划线）
	 */
	public static boolean matchAccount(String text) {
		if (Pattern.compile("^[a-z0-9_-]{6,18}$").matcher(text).matches()) {
			return true;
		}
		return false;
	}
}
