package com.zive.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.util.HSSFColor;

import com.zive.pojo.CustomProductSales;
import com.zive.pojo.CustomProductSalesEmployeeEarn;
import com.zive.pojo.People;
import com.zive.pojo.SatisfactionRatio;
import com.zive.pojo.SatisfactionShopRatio;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ProductDuotaiTest {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		String beginDate = "2019-06-01 00:00:00";
		String endDate = "2019-07-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginDate", sdf.parse(beginDate));
		map.put("endDate", sdf.parse(endDate));
		List<Map<String,Object>> product = session.selectList("com.zive.getDuotai");
		
		file(product);
	}
	
	public static void file(final List<Map<String,Object>> customProductSalesAnalyzes) throws IOException{
		final Map<String,String> orderType = new HashMap<String,String>();
		orderType.put("buy", "消费单");
		orderType.put("refund", "退款单");
		orderType.put("return", "还款单");
		
		Excel excel = new Excel();
		if(customProductSalesAnalyzes != null && customProductSalesAnalyzes.size() > 0){
			excel.setSheets(new ArrayList<ExcelSheet>(){{
				add(new ExcelSheet(){{
					setName("销售情况报表");
					setFreezePane(new int[]{0,1,0,1});
					setRows(new ArrayList<ExcelRow>(){{
						add(new ExcelRow(){{
							setBackground(HSSFColor.BLACK.index);
							setColor(HSSFColor.WHITE.index);
							setAutoSizeColumn(true);
							setCells(new ArrayList<ExcelCell>(){{
								add(new ExcelCell(){{setValue("会员人id");}});
								add(new ExcelCell(){{setValue("会员");}});
								add(new ExcelCell(){{setValue("业务编号");}});
								add(new ExcelCell(){{setValue("单据编号");}});
								add(new ExcelCell(){{setValue("产品id");}});
								add(new ExcelCell(){{setValue("产品");}});
								add(new ExcelCell(){{setValue("购买数量");}});
								add(new ExcelCell(){{setValue("剩余数量");}});
							}});
						}});
						String pattern = "yyyy-MM-dd HH:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						for(int num=0;num<customProductSalesAnalyzes.size();num++){
							final Map<String,Object> sales = customProductSalesAnalyzes.get(num);
							final int colorNum=num;
								add(new ExcelRow(){{
									setCells(new ArrayList<ExcelCell>(){{
										add(new ExcelCell(){{setValue(sales.get("id"));}});
										add(new ExcelCell(){{setValue(sales.get("name"));}});
										add(new ExcelCell(){{setValue(sales.get("consumption_id"));}});
										add(new ExcelCell(){{setValue(sales.get("consumption_project_id"));}});
										add(new ExcelCell(){{setValue(sales.get("project_id"));}});
										add(new ExcelCell(){{setValue(sales.get("productName"));}});
										add(new ExcelCell(){{setValue(sales.get("buy_number"));}});
										add(new ExcelCell(){{setValue(sales.get("number"));}});
								}});
							}});
						}
					}});
				}});
			}});
			String fileName = "多肽剩余情况报表"+new Date().getTime()+".xlsx";
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