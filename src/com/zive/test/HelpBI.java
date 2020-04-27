package com.zive.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.pojo.CustomProductSales;
import com.zive.pojo.CustomProductSalesEmployeeEarn;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;


public class HelpBI {
	
	public static void main(String[] args) throws IOException, ParseException {
//		String list = readTxtFile("c:\\link.txt");
//		
//		List<Map<String,Object>> parseObject = JSON.parseObject(list, List.class);
//		file(parseObject);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse("2020-01-01 00:00:00");
		System.out.println(date.toString());
		System.out.println(df.format(date));
	}

	public static String readTxtFile(String readFilePath) {
		StringBuilder list = new StringBuilder("");
        try
        {
            String encoding = "GBK";
            File file = new File(readFilePath);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null)
            {
            	list.append(lineTxt);
            }
            read.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list.toString();
    }
	
	
	public static void file(final List<Map<String,Object>> parseArray) throws IOException{
		Excel excel = new Excel();
		if(parseArray != null && parseArray.size() > 0){
			excel.setSheets(new ArrayList<ExcelSheet>(){{
				add(new ExcelSheet(){{
					setName("�����������");
					setFreezePane(new int[]{0,1,0,1});
					setRows(new ArrayList<ExcelRow>(){{
						add(new ExcelRow(){{
							setBackground(HSSFColor.BLACK.index);
							setColor(HSSFColor.WHITE.index);
							setAutoSizeColumn(false);
							setCells(new ArrayList<ExcelCell>(){{
								add(new ExcelCell(){{setValue("�����ŵ�");}});
								add(new ExcelCell(){{setValue("����");}});
								add(new ExcelCell(){{setValue("��");}});
								add(new ExcelCell(){{setValue("�Ƿ�����");}});
							}});
						}});
						String pattern = "yyyy-MM-dd HH:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						for(int num=0;num<parseArray.size();num++){
							final Map<String,Object> sales = parseArray.get(num);
							final int colorNum=num;
								add(new ExcelRow(){{
									setCells(new ArrayList<ExcelCell>(){{
										add(new ExcelCell(){{setValue(sales.get("shopName"));}});
										add(new ExcelCell(){{setValue(sales.get("name"));}});
										add(new ExcelCell(){{setValue(sales.get("no"));}});
										add(new ExcelCell(){{setValue(sales.get("isSleep"));}});
									}});
							}});
						}
					}});
				}});
			}});
			String fileName = "�����������"+new Date().getTime()+".xlsx";
			String virPath = "c:/" + fileName;
			byte[] bytes = OfficeUtil.createExcel(OfficeUtil.ExcelVersion.Version2007, excel);
			
			//ȷ��д���ļ���λ��
			File file = new File(virPath);
			//��������ֽ���
			FileOutputStream fos = new FileOutputStream(file);
			//��FileOutputStream ��write����д���ֽ�����
			fos.write(bytes);
			System.out.println("д��ɹ�");
			//Ϊ�˽�ʡIO���Ŀ�������Ҫ�ر�
			fos.close();
		}
	}
}
