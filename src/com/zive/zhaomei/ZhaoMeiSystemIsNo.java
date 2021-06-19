package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.KangWangOperationAdd;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiSystemIsNo extends BaseKangWangDao{
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\找美网\\找美网所有项目产品名称.xlsx");
		
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
		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
		
		for(int i = 0; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(0).getValue().toString();
			
			
			//产品
			ProductInfo productInfo = new ProductInfo();
			productInfo.setName(name);
			List<ProductInfo> productList = getProductInfo(productInfo);
			String unit = null;
			if(productList.size() == 0){
				if(name.contains("(")){
					unit = name.substring(name.lastIndexOf("("), name.length()-1);
					String nameTemp = name.substring(0, name.indexOf("("));
					productInfo.setName(nameTemp);
					productList = getProductInfo(productInfo);
				}
				
				if(productList.size() == 0){
					//查询对应的名称
					if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("product")){
						productInfo.setName(zhaoMeiName.get(name).getNewName());
						productList = getProductInfo(productInfo);
					}
				}
			}
			if(productList.size() > 0){
				continue;
			}
			
			//项目
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setName(name);
			List<ProjectInfo> projectList = getProjectInfo(projectInfo);
			if(projectList.size() == 0){
				//查询对应的名称
				if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("project")){
					projectInfo.setName(zhaoMeiName.get(name).getNewName());
					projectList = getProjectInfo(projectInfo);
				}
			}
			if(projectList.size() > 0){
				continue;
			}
			
			//合作
			CooperationProject cooInfo = new CooperationProject();
			cooInfo.setName(name);
			List<CooperationProject> cooList = getCooperationProject(cooInfo);
			if(projectList.size() == 0){
				//查询对应的名称
				if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("coo")){
					cooInfo.setName(zhaoMeiName.get(name).getNewName());
					cooList = getCooperationProject(cooInfo);
				}
			}
			
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


@TableName("没对应的项目名称-找美网")
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
