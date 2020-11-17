package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.ProductGet;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改项目手工工时
 * @author Administrator
 *
 */
public class ShouGongTest extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\太极健胸手工单.xlsx");
		
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
		
		Map<String,BigDecimal> map = new HashMap<>();
		
		BigDecimal all = BigDecimal.ZERO;
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			Object orderId = excelCells.get(2).getValue();
			String no = String.valueOf(orderId);
			
			System.out.println(no);
			
			ProjectDoneDetail done = new ProjectDoneDetail();
			done.setOrderId(no);
			List<ProjectDoneDetail> projectDone = ProjectDoneDao.getProjectDoneDetail(done);
			
			boolean flag = false;
			for (ProjectDoneDetail projectDoneDetail : projectDone) {
				 
				ProjectDetailConsumption detail = new ProjectDetailConsumption();
				detail.setId(projectDoneDetail.getProjectDetailId());
				detail.setProjectId("08c14ebf-ec19-4b39-a571-aef09d23536d");
				List<ProjectDetailConsumption> list = ProjectSellDao.getProjectDetailConsumption(detail);
				if(list.size()>0){
					if(projectDoneDetail.getDoneServiceTime().equals(60)){
						
						int updateProjectDetailDone = ShouGongTest.updateProjectDetailDone(projectDoneDetail);
						System.out.println(updateProjectDetailDone);
						break;
					}
				}
			}
			System.out.println(i);
			
		}
		getSession().commit();
	}
	
	static public int updateProjectDetailDone(ProjectDoneDetail detail){
		return getSession().update("com.zive.update.updateProjectDetailDone", detail);
	}
}