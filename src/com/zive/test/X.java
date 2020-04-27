package com.zive.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class X {

	public static void main(String[] args) {
		getASCii("A0295673");
	}
	
	private static long getASCii(String aa) {
		String a = aa;
		byte[] i = a.getBytes();
		long num = 0l;
		for (byte b : i) {
			num+=b;
		}
		return num;
	}
}
