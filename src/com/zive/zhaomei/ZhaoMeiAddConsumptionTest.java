package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

public class ZhaoMeiAddConsumptionTest extends BaseKangWangDao{

public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\部分没时长项目.xlsx");
		
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
		
//		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
//		excelSheet.getRows().size()
		Map<String,Integer> map = new HashMap<>();
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String projectId = excelCells.get(0).getValue().toString();
			String serviceTimeStr = excelCells.get(3).getValue().toString();
			
			map.put(projectId, Double.valueOf(serviceTimeStr).intValue());
			
		}
		ProjectDetailConsumption projectDetailCon = new ProjectDetailConsumption();
		projectDetailCon.setKeyword("zhaoMei");
		
		List<ProjectDetailConsumption> projectDetailConsumption = ProjectSellDao.getProjectDetailConsumption(projectDetailCon);
		for (ProjectDetailConsumption detail : projectDetailConsumption) {
			if(map.containsKey(detail.getProjectId())){
				ProjectDetailConsumption con = new ProjectDetailConsumption();
				con.setId(detail.getId());
				con.setServiceTime(map.get(detail.getProjectId()));
				ProjectSellDao.updateProjectDetailConsumption(con);
			}
		}
		
		getSession().commit();
		getSession().close();
	}


}
