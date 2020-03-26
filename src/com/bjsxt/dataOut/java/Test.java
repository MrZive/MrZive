package com.bjsxt.dataOut.java;

import com.bjsxt.dataOut.annotaion.TableName;
import com.bjsxt.dataOut.entity.MyProductGet;

public class Test {

	
	public static void main(String[] args) {
		Class<MyProductGet> clazz = MyProductGet.class;

        if(clazz.isAnnotationPresent(TableName.class)){
            // 获取 "类" 上的注解
        	TableName getAnnotation = clazz.getAnnotation(TableName.class);
            System.out.println("\"类\"上的注解值获取到第一个 ："+getAnnotation.value());
        }
	}
}
