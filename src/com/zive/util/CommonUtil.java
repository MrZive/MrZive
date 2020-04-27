package com.zive.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {

	public static List<String> strToList(String str) {
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		String[] s = str.split(",");
		Long[] array = new Long[s.length];
		List<String> list = new ArrayList<String>(array.length);
		try {
			Arrays.asList(str.split(","));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			list = new ArrayList<String>(0);
		}
		return list;
	}

	public static Long[] strToLongArray(String str) {
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		String[] s = str.split(",");
		Long[] array = new Long[s.length];
		try {
			for (int i = 0; i < array.length; i++) {
				array[i] = Long.parseLong(s[i]);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			array = new Long[0];
		}
		return array;
	}

	public static Integer[] strToIntegerArray(String str) {
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		String[] s = str.split(",");
		Integer[] array = new Integer[s.length];
		try {
			for (int i = 0; i < array.length; i++) {
				array[i] = Integer.parseInt(s[i]);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			array = new Integer[0];
		}
		return array;
	}

	public static String searchListFromKey(List<Map<String, String>> list, String key) {
		if (list == null && list.size() == 0) {
			return null;
		}
		for (Map<String, String> map : list) {
			if (map.containsValue(key)) {
				return map.get("value");
			}
		}
		return null;
	}

	/**
	 * 计算百分百比
	 * @author WangZive
	 * @param num
	 * @param total
	 * @param scale，几位精确度
	 * @return
	 * @date: 2019年5月7日 下午5:00:04
	 */
	public static String calculateAccuracy(Long num, Long total, Integer scale) {
		double number = 0d;
		number = num==null?0:num;
		scale = scale==null?0:scale;
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(scale);
		df.setRoundingMode(RoundingMode.HALF_UP);
		double accuracy_num = number / total * 100;
		return df.format(accuracy_num) + "%";
	}

}
