package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  003康王店会员筛选 - 档案剩余次数比系统多
 * @author Administrator
 *
 */
public class KangWangOperationAdd extends BaseKangWangDao{

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\公司数据\\操作数据\\华碧店\\5.13 资产核对表 - 结果.xlsx");
		
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
		
		Map<String, NameToSystemName> kangWangName = getKangWangName();
		
		for(int i = 2; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(0).getValue().toString();
			String type = excelCells.get(2).getValue().toString();
			String detailId = excelCells.get(3).getValue()==null?"":excelCells.get(3).getValue().toString();
			String name = excelCells.get(4).getValue().toString();
			String operation = excelCells.get(11).getValue() == null ? "" : excelCells.get(11).getValue().toString();
			
			if(operation.equals("跳过") || operation.equals("预作废") || operation.equals("无误") || operation.equals("档案可能有异常")){
				continue;
			}
			
			String serviceTimeStr = excelCells.get(6).getValue()==null?"":excelCells.get(6).getValue().toString();
			serviceTimeStr = serviceTimeStr.equals("/")?"0":serviceTimeStr;
			Integer serviceTime = Double.valueOf(serviceTimeStr).intValue();
			String numberStr = excelCells.get(7).getValue().toString();
			numberStr = numberStr.equals("/")?"0":numberStr;
			String buyDate = excelCells.get(8) == null ? "" : excelCells.get(8).toString();
			String priceStr = excelCells.get(9).getValue().toString();
			priceStr = priceStr.equals("/")?"0":priceStr;
			Double price = Double.valueOf(priceStr);
			
			String remark = excelCells.get(10).getValue() == null ? "无" : excelCells.get(10).getValue().toString();
			remark = remark.length()==0 ? "无" : remark;
			
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				continue;
//				throw new RuntimeException("手机号不存在");
			}
			
			if(type.equals("传统项目")){
				//获取拥有的项目
				List<Map<String,Object>> projectDetailLeft = getProjectDetailLeft(memberCard.getId());
				
				//=======================================================================判断项目=======================================================================
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setName(name);
				List<ProjectInfo> projectList = getProjectInfo(projectInfo);
				if(projectList.size() == 0){
					//查询对应的名称
					if(kangWangName.containsKey(name) && kangWangName.get(name).getType().equals("project")){
						projectInfo.setName(kangWangName.get(name).getNewName());
						projectList = getProjectInfo(projectInfo);
						if(projectList.size() == 0){
							throw new RuntimeException("找不到项目名称信息");
						}
					}
				}
				
				Integer number = Double.valueOf(numberStr).intValue();
				String secondName = "";
				if(projectList.size() > 0){//项目处理逻辑
					ProjectInfo info = projectList.get(0);
					if(info.getName().equals("旧项目")){
						secondName = name;
					}
					
					if(operation.equals("新增")){
						addProjectDetail(memberCard.getId(), info.getId(), secondName, price, number, number, serviceTime, remark);
					}else if(operation.equals("作废")){
						ProjectDetailConsumption projectDetailConsumptionById = ProjectSellDao.getProjectDetailConsumptionById(detailId);
						if(projectDetailConsumptionById!=null && projectDetailConsumptionById.getIsSend() != null && projectDetailConsumptionById.getIsSend() == 1){
							System.out.println(detailId + "是赠送的，操作是作废");
						}
						failProjectDetail(detailId);
					}
					
				}else{
					throw new RuntimeException("找不到对应的信息:" + name);
				}
			}
			
			
			
			if(type.equals("产品")){
				ProductInfo productInfo = new ProductInfo();
				productInfo.setName(name);
				List<ProductInfo> productList = getProductInfo(productInfo);
				if(productList.size() == 0){
					//查询对应的名称
					if(kangWangName.containsKey(name) && kangWangName.get(name).getType().equals("product")){
						productInfo.setName(kangWangName.get(name).getNewName());
						productList = getProductInfo(productInfo);
						if(productList.size() == 0){
							throw new RuntimeException("找不到产品名称信息");
						}
					}
				}
				
				String secondName = "";
				if(productList.size() > 0){//项目处理逻辑
					ProductInfo info = productList.get(0);
					Integer number = Integer.valueOf(numberStr.substring(0, numberStr.length()-1));
					String unit = numberStr.substring(numberStr.length()-1);
					if(StringUtils.isBlank(unit)){
						throw new RuntimeException("excel产品没有单位");
					}
					if(!info.getBoxesUnit().equals(unit) && !info.getBulkUnit().equals(unit)){
						throw new RuntimeException("excel产品单位与系统的不匹配");
					}
					
					if(operation.equals("新增")){
						addProductDetail(memberCard.getId(), info.getId(), number, number, price, unit, remark);
					}else if(operation.equals("作废")){
						failProductDetail(detailId);
					}
					
				}else{
					throw new RuntimeException("找不到对应的信息:" + name);
				}
			}
		}
		getSession().commit();
		getSession().close();
	}
	
}
