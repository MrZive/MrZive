package com.zive.dataOut.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.MyProductGet;

public class Test extends BaseDao{

	
	public static void main(String[] args) {
//		Class<MyProductGet> clazz = MyProductGet.class;
//
//        if(clazz.isAnnotationPresent(TableName.class)){
//            // 获取 "类" 上的注解
//        	TableName getAnnotation = clazz.getAnnotation(TableName.class);
//            System.out.println("\"类\"上的注解值获取到第一个 ："+getAnnotation.value());
//        }
		
		int rate = 12;
		System.out.println(rate >= 60 ? rate > 120 ? 120 : rate : 0);
	}
}
