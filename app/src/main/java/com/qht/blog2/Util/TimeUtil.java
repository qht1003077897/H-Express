package com.qht.blog2.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * 获取的当天时间    例： 几年几月几日*/
	public static String getNowTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());
		return date;
	}
	/**
	 * 获取的当天时间    例： 几年几月几日几点几分几秒*/
	public static String getNowTime2min(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new java.util.Date());
		return date;
	}
	/**
	 * 获取的当天时间      例：几点几分几秒*/
	public static String getNowTimeH2M(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String date = sdf.format(new java.util.Date());
		return date;
	}

	/**
	 * 计算时间差
	 *
	 * @param starTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 *
	 * @return 返回时间差   返回类型 天，时，分。
	 */
	public static String getTimeDifferenceDay(String starTime, String endTime) {
		String timeString = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			Date parse = dateFormat.parse(starTime);
			Date parse1 = dateFormat.parse(endTime);

			long diff = parse1.getTime() - parse.getTime();

			long day = diff / (24 * 60 * 60 * 1000);
			long hour = (diff / (60 * 60 * 1000) - day * 24);
			long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
					- min * 60 * 1000 - s * 1000);
			// 上面为day:Hour:min
			// 下面为Hour：min
			long hour1 = diff / (60 * 60 * 1000);
			String hourString = hour1 + "";
			long min1 = ((diff / (60 * 1000)) - hour1 * 60);
			timeString = day +"天"+hour+ "小时" + min + "分";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeString;

	}

	/**
	 * 计算相差的小时
	 *
	 * @param starTime
	 * @param endTime
	 * @return  时
	 */
	public static String getTimeDifferenceHour(String starTime, String endTime) {
		String timeString = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			Date parse = dateFormat.parse(starTime);
			Date parse1 = dateFormat.parse(endTime);

			long diff = parse1.getTime() - parse.getTime();
			String string = Long.toString(diff);

			float parseFloat = Float.parseFloat(string);

			float hour1 = parseFloat / (60 * 60 * 1000);

			timeString = Float.toString(hour1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeString;
	}

	/**
	 * 把格式化过的时间转换毫秒值
	 *
	 * @param time      时间，某年某月某日
	 * @param formatSrt 时间格式 如 yyyy-MM-dd
	 * @return 当前日期的毫秒值
	 * @throws java.text.ParseException
	 */
	public static long getMillis(String time, String formatSrt) throws ParseException, java.text.ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatSrt);
		return format.parse(time).getTime();
	}
	/**
	 * 将毫秒值格转换为时间 yyyy-MM-dd HH:mm:ss 格式
	 *
	 * @param date   毫秒值
	 * @param format 你要的时间格式 yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd
	 * @return 返回转换后的值
	 */
	public static String formatDate(Long date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}
