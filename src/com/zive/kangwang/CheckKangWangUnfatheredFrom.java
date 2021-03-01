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
import com.zive.dataOut.entity.ProjectInfoDetail;
import com.zive.dataOut.java.BaseDao;
import com.zive.kangwang.entity.NameToSystemName;
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
		
		File file = new File("D:\\公司数据\\操作数据\\康王店\\整理\\003康王店会员筛选 - 不明来源.xlsx");
		
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
			String phone = excelCells.get(2).getValue().toString();
			String name = excelCells.get(3).getValue().toString();
			Integer buyNumber = Double.valueOf(excelCells.get(4).getValue().toString()).intValue();
			Integer leftNumber = Double.valueOf(excelCells.get(9).getValue().toString()).intValue();
			String buyDate = excelCells.get(10).getValue().toString().length() > 2 ? excelCells.get(10).getValue().toString() : "未知";
			String remark = excelCells.get(13).getValue() == null ? "无" : excelCells.get(13).getValue().toString();
			remark = remark.length()==0 ? "无" : remark;
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				throw new RuntimeException("手机号不存在");
			}
			
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
				ProjectInfo info = projectList.get(0);
				ProjectInfoDetail projectInfoDetail = getProjectInfoDetail("110103", info.getId());
				addProjectDetail(memberCard.getId(), info.getId(), "", buyNumber, leftNumber, projectInfoDetail.getServiceTime().intValue(), remark + "，购买日期："+buyDate);
				continue;
			}
			
			CooperationProject cooInfo = new CooperationProject();
			cooInfo.setName(name);
			List<CooperationProject> cooList = getCooperationProject(cooInfo);
			
			if(cooList.size()>0){
				CooperationProject info = cooList.get(0);
				addCooperationConsumption(memberCard.getId(), info.getId(), 0D, buyNumber, leftNumber, 0D, 0D, 0D, 0D, remark + "，购买日期："+buyDate);
				continue;
			}
			
			//查询对应的名称
			if(kangWangName.containsKey(name)){
				NameToSystemName nameToSystemName = kangWangName.get(name);
				String secondName = "";
				Integer time = nameToSystemName.getTime();
				if(nameToSystemName.getNewName().equals("旧项目")){
					secondName = getSecondName(nameToSystemName.getOldName());
				}
				if(nameToSystemName.getType().equals("project")){
					projectInfo.setName(nameToSystemName.getNewName());
					projectList = getProjectInfo(projectInfo);
					if(projectList.size()>0){
						ProjectInfo info = projectList.get(0);
						if(time == null || time == 0){
							ProjectInfoDetail projectInfoDetail = getProjectInfoDetail("110103", info.getId());
							time = projectInfoDetail.getServiceTime().intValue();
						}
						addProjectDetail(memberCard.getId(), info.getId(), secondName, buyNumber, leftNumber, time, remark + "，购买日期："+buyDate);
						continue;
					}
				}
				if(nameToSystemName.getType().equals("coo")){
					cooInfo.setName(nameToSystemName.getNewName());
					cooList = getCooperationProject(cooInfo);
					if(cooList.size()>0){
						CooperationProject info = cooList.get(0);
						addCooperationConsumption(memberCard.getId(), info.getId(), 0D, buyNumber, leftNumber, 0D, 0D, 0D, 0D, remark + "，购买日期："+buyDate);
						continue;
					}
				}
				
				throw new RuntimeException("找不到项目信息");
			}
			
			System.out.println(name);
			throw new RuntimeException("找不到项目信息");
		}
		
		getSession().commit();
	}
}
