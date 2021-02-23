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
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 匹配系统没有的项目名称
 * @author Administrator
 *
 */
public class CheckKangWang extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\003康王店筛选需补录次数及金额.xlsx");
		
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
		
		for(int i = 2; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(3).getValue().toString();
			System.out.println(name);
			
			ProductInfo productInfo = new ProductInfo();
			productInfo.setName(name);
			List<ProductInfo> productList = getProductInfo(productInfo);
			if(productList.size()>0){
				continue;
			}
			
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setName(name);
			List<ProjectInfo> projectList = getProjectInfo(projectInfo);
			
			if(projectList.size()>0){
				continue;
			}
			
			CooperationProject cooInfo = new CooperationProject();
			cooInfo.setName(name);
			List<CooperationProject> cooList = getCooperationProject(cooInfo);
			
			if(cooList.size()>0){
				continue;
			}
			
			setList.add(name);
		}
		
		JSONArray list = new JSONArray();
		Iterator<String> iterator = setList.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			NotInfoQhc not = new NotInfoQhc();
			not.setName(next);
			list.add(not);
		}
		
		ExcelUtilForDO.toFile(list, NotInfoQhc.class);
	}
}


@TableName("没对应的项目名称")
class NotInfoQhc{
	@FieldName("名称")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
