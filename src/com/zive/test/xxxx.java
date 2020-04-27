package com.zive.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;

public class xxxx {

//	public static void main(String[] args) throws IOException {
//		String jj = "[53, 321, 326, 385, 446, 465, 521]";
//		String substring = jj.substring(1);
//		String substring2 = substring.substring(0, substring.length()-1);
//		String[] split = substring2.split(",");
//		
//		
//		FileWriter fileWriter = null;
//		try {
//			fileWriter = new FileWriter("D:/a.txt");//�����ı��ļ�
//			
//			for(int i=0;i<split.length;i++){//ѭ��д��
//				fileWriter.write(Integer.valueOf(split[i].trim())+1+"\r\n");//д�� \r\n����
//			}
//			fileWriter.flush();
//			fileWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<>();
		set.add("x");
		set.add("x");
		set.add("a");
		System.out.println(set.toString());
		System.out.println(set.toArray());
	}
}
