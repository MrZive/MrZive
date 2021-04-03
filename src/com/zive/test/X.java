package com.zive.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class X {

	public static void main(String[] args) {
		getASCii("A0295673");
		for(int x = 8000;x<=12000;x++){
			System.out.println(3d*x + (59800 - 3d*x)/3d);
			if(3d*x + (59800 - 3d*x)/3d == 59800d){
				System.out.println(x);
			}
		}
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
