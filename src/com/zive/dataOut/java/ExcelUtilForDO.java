package com.zive.dataOut.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ExcelUtilForDO {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static <T> void toFile(final JSONArray jsonArray, Class<T> clazz) throws IOException{
		
		String tableName = clazz.getAnnotation(TableName.class).value();
		
		Field[] field = clazz.getDeclaredFields();
		LinkedHashMap<String,String> linkedMap = new LinkedHashMap<>();
		
		ArrayList<ExcelCell> excelCellTop = new ArrayList<ExcelCell>();
        for(Field fie : field){
        	FieldName annotation = fie.getAnnotation(FieldName.class);
        	if(annotation==null){
        		continue;
        	}
        	String name = annotation.value();
        	String key = fie.getName();
        	linkedMap.put(name, key);
        	
        	excelCellTop.add(new ExcelCell(){{setValue(name);}});
        }
		
		Excel excel = new Excel();
		if(jsonArray != null && jsonArray.size() > 0){
			excel.setSheets(new ArrayList<ExcelSheet>(){{
				add(new ExcelSheet(){{
					setName(tableName);
//					setFreezePane(new int[]{0,1,0,1});
					setRows(new ArrayList<ExcelRow>(){{
						add(new ExcelRow(){{
//							setBackground(HSSFColor.BLACK.index);
//							setColor(HSSFColor.WHITE.index);
//							setAutoSizeColumn(true);
							setCells(excelCellTop);
						}});
						String pattern = "yyyy-MM-dd HH:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						for(int num=0;num<jsonArray.size();num++){
							final JSONObject jsonObject = jsonArray.getJSONObject(num);
							final int colorNum=num;
							
							ArrayList<ExcelCell> excelCell = new ArrayList<ExcelCell>();
							
							Set<Entry<String, String>> entrySet = linkedMap.entrySet();
							for(Iterator<Entry<String, String>> it=entrySet.iterator();it.hasNext();){
								Entry<String, String> next = it.next();
								String name = next.getKey();
								String key = next.getValue();
								
								String string = jsonObject.getString(key);
								excelCell.add(new ExcelCell(){{setValue(string);}});
							}
							
							add(new ExcelRow(){{
								setCells(excelCell);
							}});
						}
					}});
				}});
			}});
			String fileName = tableName + new Date().getTime() +"当前日期" +df.format(new Date())+".xlsx";
			String virPath = "c:/download/" + fileName;
			byte[] bytes = OfficeUtil.createExcel(OfficeUtil.ExcelVersion.Version2007, excel);
			
			//确定写出文件的位置
			File file = new File(virPath);
			//建立输出字节流
			FileOutputStream fos = new FileOutputStream(file);
			//用FileOutputStream 的write方法写入字节数组
			fos.write(bytes);
			System.out.println("写入成功");
			//为了节省IO流的开销，需要关闭
			fos.close();
		}
	}
}
