package com.zive.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;


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
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		if(total==null||total==0){
			return df.format(0);
		}
		double number = 0d;
		number = num==null?0:num;
		scale = scale==null?0:scale;
		total = total==null?0:total;
		df.setMaximumFractionDigits(scale);
		df.setRoundingMode(RoundingMode.HALF_UP);
		double accuracy_num = number / total * 100;
		return df.format(accuracy_num);
	}
	
	public static void main(String[] args) throws ParseException, Exception {
//		double i=0;
//		long j=10;
//		System.out.println(i/j);
		SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
		SimpleDateFormat monthSdf = new SimpleDateFormat("MM-dd");//格式化为年月
		
		String beginDate = "2021-01-18 00:00:00";//开始时间
		String endDate = "2023-01-24 23:59:59";//结束时间
 
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate));
		
		List<String> monthBetween = getYearMonthBetween(beginDate, endDate);
		
		for (String string : monthBetween) {
			System.out.println(string);
		}
	}
	
	
	 /**
     * 获取两个日期之间的所有日期集合["2021-01-01","2021-01-02"]
     * @param minDate
     * @param maxDate
     * @return
     * @throws Exception
     */
    public static List<String> getYearMonthDayBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        max.setTime(sdf.parse(maxDate));
        max.add(Calendar.DATE, +1);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.DAY_OF_MONTH, 1);
        }
        return result;
    }

    /**
     *  获取两个日期间年月集合["2021-01","2021-01"]
     * @param minDate 最小时间
     * @param maxDate 最大时间
     * @return 日期集合 格式为 年-月
     * @throws Exception
     */
    public static List<String> getYearMonthBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }
    
    
    
    public static List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {
    	if (list == null||list.size() == 0) {
    		return new ArrayList<T>(0);
    	}

    	Integer count = list.size(); // 记录总数
    	Integer pageCount = 0; // 页数
    	if (count % pageSize == 0) {
    		pageCount = count / pageSize;
    	} else {
    		pageCount = count / pageSize + 1;
    	}

    	int fromIndex = 0; // 开始索引
    	int toIndex = 0; // 结束索引

    	if (pageNum != pageCount) {
    		fromIndex = (pageNum - 1) * pageSize;
    		toIndex = fromIndex + pageSize;
    	} else {
    		fromIndex = (pageNum - 1) * pageSize;
    		toIndex = count;
    	}
    	List<T> pageList = list.subList(fromIndex, toIndex);
    	return pageList;
    }
    
    
    public static String objectToString(Object value){
    	if(value == null){
    		return null;
    	}
    	return String.valueOf(value);
    }
    
    public static Integer objectToInteger(Object value){
    	if(value == null){
    		return null;
    	}
    	String string = String.valueOf(value);
    	if(string.length() == 0){
    		return null;
    	}
    	return Integer.valueOf(string);
    }
    
    public static Long objectToLong(Object value){
    	if(value == null){
    		return null;
    	}
    	String string = String.valueOf(value);
    	if(string.length() == 0){
    		return null;
    	}
    	return Long.valueOf(string);
    }
    
    public static BigDecimal objectToBigDecimal(Object value){
    	if(value == null){
    		return null;
    	}
    	String string = String.valueOf(value);
    	if(string.length() == 0){
    		return null;
    	}
    	return new BigDecimal(string);
    }
    
    public static Date objectToLongDate(Object value){
    	if(value == null){
    		return null;
    	}
    	String string = String.valueOf(value);
    	if(string.length() == 0){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date parse = null;
		try {
			parse = sdf.parse(string);
		} catch (ParseException e) {
			return parse;
		}
    	return parse;
    }
    
    public static Date objectToShortDate(Object value){
    	if(value == null){
    		return null;
    	}
    	String string = String.valueOf(value);
    	if(string.length() == 0){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date parse = null;
		try {
			parse = sdf.parse(string);
		} catch (ParseException e) {
			return parse;
		}
    	return parse;
    }
    
    public static String dateToShortDateStr(Date value){
    	if(value == null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return sdf.format(value);
    }
    
    public static Date dateToShortDate(Date value){
    	String dateStr = dateToShortDateStr(value);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date parse = null;
		try {
			parse = sdf.parse(dateStr);
		} catch (ParseException e) {
			return parse;
		}
    	return parse;
    }
}
