package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectInfoMeasure;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  设置冻结会员
 * @author Administrator
 *
 */
public class CheckKangWangErrorMember extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\康王店\\003康王店会员筛选 - 去无用.xlsx");
		
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
		
		Set<String> setList = new LinkedHashSet<String>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String isActive = excelCells.get(25).getValue().toString();
			if(isActive.equals("否")){
				setList.add(phone);
			}
		}
		
		for (String phone : setList) {
			MemberCard memberCard = BaseDao.getMemberCardByPhone(phone);
			if(memberCard == null){
				System.out.println(phone);
			}
			
			MemberCard card = new MemberCard();
			card.setId(memberCard.getId());
			card.setError(1);
			int updateMemberToError = updateMemberToError(card);
			
			if(updateMemberToError == 0){
				throw new RuntimeException();
			}
		}
		
//		getSession().commit();
	}
	
	static public int updateMemberToError(MemberCard memberCard){
		int update = getSession().update("com.zive.kangwang.updateMemberToError", memberCard);
		return update;
	}
	
	
}
