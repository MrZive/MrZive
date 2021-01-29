package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.java.BaseDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class AddStorageToCard extends BaseDao{

	public static void main(String[] args) {
		File file = new File("D:\\公司数据\\录储值\\中艾之家转换金额表20201231.xlsx");
		
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
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		BigDecimal all = BigDecimal.ZERO;
		
		boolean flag = true;
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = String.valueOf(excelCells.get(0).getValue());
			String phone = String.valueOf(excelCells.get(1).getValue());
			
			MemberCard memberCardByPhone = BaseDao.getMemberCardByPhone(phone);
			if(memberCardByPhone == null){
				flag = false;
				System.out.println(name + "=========" +phone);
			}
		}
		if(!flag){
			throw new RuntimeErrorException(null, "数据错误");
		}
		
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = String.valueOf(excelCells.get(0).getValue());
			String phone = String.valueOf(excelCells.get(1).getValue());
			String money = String.valueOf(excelCells.get(2).getValue());
			
			MemberCard memberCardByPhone = BaseDao.getMemberCardByPhone(phone);
			if(memberCardByPhone == null){
				System.out.println(name + "=========" +phone);
			}
			
			MemberCard memberCard = new MemberCard();
			memberCard.setId(memberCardByPhone.getId());
			
			Double storeBalance = memberCardByPhone.getStoreBalance();
			System.out.println(phone + "======前" + storeBalance);
			storeBalance += Double.valueOf(money);
			
			System.out.println(phone + "======后" + storeBalance);
			
			memberCard.setStoreBalance(storeBalance);
			
			int updateMemberCard = BaseDao.updateMemberCard(memberCard);
			System.out.println(updateMemberCard);
		}
		getSession().commit();
		
	}
}
