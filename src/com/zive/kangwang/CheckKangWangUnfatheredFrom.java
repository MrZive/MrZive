package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.BaseDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  未知来源项目
 * @author Administrator
 *
 */
public class CheckKangWangUnfatheredFrom extends BaseKangWangDao{

	public static void main(String[] args) throws IOException {
		
		File file = new File("F:\\公司数据\\操作数据\\康王店\\整理\\003康王店会员筛选 - 不明来源.xlsx");
		
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
		ExcelSheet excelSheet = excel.getSheets().get(1);
		
		Map<String, NameToSystemName> kangWangName = getKangWangName();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ i);
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(3).getValue().toString();
			
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
			
			if(kangWangName.containsKey(name) && kangWangName.get(name).getType() != null){
				continue;
			}
			
			System.out.println(name);
			
		}
		
//		getSession().commit();
	}
}
