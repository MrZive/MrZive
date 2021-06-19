package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiAddSetTest extends BaseKangWangDao{

public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\套餐\\合并.xlsx");
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
//		excelSheet.getRows().size()
		for(int i = 4200; i < 4245;i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String index = excelCells.get(0).getValue().toString();
			String shopName = excelCells.get(1).getValue().toString();
			String phone = excelCells.get(5).getValue().toString();
			String name = excelCells.get(20).getValue().toString();
			String secondName = "";
			
			String isSendStr = excelCells.get(7).getValue().toString();
			Integer isSend = isSendStr.equals("赠送")?1:0;
			
			Double price = Double.valueOf(excelCells.get(19).getValue().toString());
			price = setDoubleScale(price);
			int buyNumber = Double.valueOf(excelCells.get(21).getValue().toString()).intValue();
			Double payment = Double.valueOf(excelCells.get(22).getValue().toString());
			payment = setDoubleScale(payment);
			Double realPayment = Double.valueOf(excelCells.get(25).getValue().toString());
			realPayment = setDoubleScale(realPayment);
			Double owe = Double.valueOf(excelCells.get(24).getValue().toString());
			owe = setDoubleScale(owe);
			Double leftPayment = realPayment;
			
			Integer leftNumber = buyNumber;
			Date createDate = sdf.parse(excelCells.get(28).getValue().toString());
			Date buyDate = sdf.parse(excelCells.get(29).getValue().toString());
			
			String createUser = excelCells.get(30).getValue().toString();
			String remark = excelCells.get(31).getValue()==null?"":excelCells.get(31).getValue().toString();
			
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				continue;
//				throw new RuntimeException("手机号不存在"+phone);
			}
			
			//项目-----------------------------------------------------------------------------------------------------------
			boolean flag = ZhaoMeiAddProjectTest.checkAndAddProjdectInfo(zhaoMeiName, index, shopName, name, secondName, isSend, buyNumber, realPayment, owe,
					leftPayment, leftNumber, createDate, buyDate, createUser, remark, price, memberCard, "set");
			
			//产品-----------------------------------------------------------------------------------------------------------
			boolean flag2 = ZhaoMeiAddProductTest.checkAndAddProductInfo(zhaoMeiName, index, shopName, name, secondName, isSend, buyNumber, realPayment, owe,
					leftPayment, leftNumber, createDate, buyDate, createUser, remark, price, memberCard, "set");

			//合作-----------------------------------------------------------------------------------------------------------
			boolean flag3 = ZhaoMeiAddCooperationTest.checkAndAddPCooperationInfo(zhaoMeiName, index, shopName, name, secondName, isSend, buyNumber, realPayment, owe,
					leftPayment, leftNumber, createDate, buyDate, createUser, remark, price, memberCard, "set");
			
			if(!flag && !flag2 && !flag3){
				System.out.println("找不到对应的信息:" + name);
			}
		}
		getSession().commit();
		getSession().close();
	}


}
