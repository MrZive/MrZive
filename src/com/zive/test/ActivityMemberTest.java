package com.bjsxt.test;

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

import com.bjsxt.pojo.CustomProductSales;
import com.bjsxt.pojo.CustomProductSalesEmployeeEarn;
import com.bjsxt.pojo.People;
import com.bjsxt.pojo.SatisfactionRatio;
import com.bjsxt.pojo.SatisfactionShopRatio;
import com.bjsxt.pub.Excel;
import com.bjsxt.pub.ExcelCell;
import com.bjsxt.pub.ExcelRow;
import com.bjsxt.pub.ExcelSheet;
import com.bjsxt.pub.OfficeUtil;

/**
 * 导出bi活跃度会员报表
 * @author Administrator
 *
 */
public class ActivityMemberTest {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
//		String beginDate = "2019-06-01 00:00:00";
//		String endDate = "2019-06-30 23:59:59";
		String beginDate = "2019-08-01 00:00:00";
		String endDate = "2019-08-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginDate", sdf.parse(beginDate));
		map.put("endDate", sdf.parse(endDate));
		map.put("status", "1");
//		map.put("shopIds", "110105");
		
		List<Map<String,Object>> activityMemberForm = session.selectList("com.bjsxt.getActivityMemberAnalyzes",map);
		file(activityMemberForm);
	}
	

	public static void file(final List<Map<String,Object>> activityMemberForm) throws IOException, ParseException{
		Excel excel = new Excel();
		if(activityMemberForm != null && activityMemberForm.size() > 0){
			excel.setSheets(new ArrayList<ExcelSheet>(){{
				add(new ExcelSheet(){{
					setName("活跃会员情况报表");
					setFreezePane(new int[]{0,1,0,1});
					setRows(new ArrayList<ExcelRow>(){{
						add(new ExcelRow(){{
							setBackground(HSSFColor.BLACK.index);
							setColor(HSSFColor.WHITE.index);
							setAutoSizeColumn(false);
							setColunmWidth(new HashMap<Integer, Integer>() {
								{
									put(0, 16*256);//125
									put(1, 10*256);//80像素--1：8
									put(2, 10*256);//80像素--1：8
									put(3, 20*256);//160
									put(4, 20*256);//160
									put(5, 20*256);//160
									put(6, 20*256);//160
									put(7, 8*256);//60
									put(8, 15*256);//120
									put(9, 10*256);//80
									put(10, 10*256);//80
									put(11, 10*256);//80
									put(12, 10*256);//80
									put(13, 10*256);//80
									put(14, 10*256);//80
									put(15, 15*256);//120
								}
							});
							setCells(new ArrayList<ExcelCell>(){{
								add(new ExcelCell(){{setValue("转换门店");}});
								add(new ExcelCell(){{setValue("会员姓名");}});
								add(new ExcelCell(){{setValue("会员卡号");}});
								add(new ExcelCell(){{setValue("性别");}});
								add(new ExcelCell(){{setValue("开卡日期");}});
								add(new ExcelCell(){{setValue("疗程余额");}});
								add(new ExcelCell(){{setValue("储值余额");}});
								add(new ExcelCell(){{setValue("流失会员");}});
								add(new ExcelCell(){{setValue("休眠会员");}});
								add(new ExcelCell(){{setValue("有效会员");}});
								add(new ExcelCell(){{setValue("会员分类");}});
								add(new ExcelCell(){{setValue("最后一次到店时间");}});
							}});
						}});
						final String pattern = "yyyy-MM-dd HH:mm:ss";
						final String patternSim = "yyyy-MM-dd";
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						for(int num=0;num<activityMemberForm.size();num++){
							final Map<String,Object> activity = activityMemberForm.get(num);
								add(new ExcelRow(){{
									setCells(new ArrayList<ExcelCell>(){{
										add(new ExcelCell(){{setValue(activity.get("shopName"));}});
										add(new ExcelCell(){{setValue(activity.get("name"));}});
										add(new ExcelCell(){{setValue(activity.get("phone"));}});
										add(new ExcelCell(){{setValue(Integer.valueOf(String.valueOf(activity.get("gender")))==1?"男":"女");}});
										add(new ExcelCell(){
											{
												Object date=activity.get("activateDate");
												if(date!=null){
													SimpleDateFormat sf = new SimpleDateFormat(patternSim);
													Date parse = sf.parse(date.toString());
													setValue(sf.format(parse));
												}else{
													setValue("");
												}
											}
										});
										add(new ExcelCell(){{setValue(activity.get("treatmentBalance"));}});
										add(new ExcelCell(){{setValue(activity.get("storeBalance"));}});
										add(new ExcelCell(){
											{
												int abandonint = Integer.valueOf(activity.get("isAbandonNew").toString());
												if(abandonint>0){
													setValue("是");
												}else{
													setValue("否");
												}
											}
										});
										add(new ExcelCell(){
											{
												int sleep = Integer.valueOf(activity.get("isSleepNew").toString());
												if(sleep>0){
													setValue("是");
												}else{
													setValue("否");
												}
											}
										});
										add(new ExcelCell(){
											{
												int active = Integer.valueOf(activity.get("isActiveNew").toString());
												if(active>0){
													setValue("是");
												}else{
													setValue("否");
												}
											}
										});
										add(new ExcelCell(){
											{
												int active=Integer.valueOf(activity.get("isActive").toString());
												switch(active){
												case 1:setValue("一级会员");break;
												case 2:setValue("二级会员");break;
												case 3:setValue("三级会员");break;
												case 4:setValue("四级会员");break;
												case 5:setValue("五级会员");break;
												case 6:setValue("六级会员");break;
												}
											}
										});
										add(new ExcelCell(){
											{
												Object date=activity.get("lastUpdateTime");
												if(date!=null){
													setValue(date.toString());
												}else{
													setValue("");
												}
											}
										});
								}});
							}});
						}
					}});
				}});
			}});
			String fileName = "活跃会员情况报表"+new Date().getTime()+".xlsx";
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
