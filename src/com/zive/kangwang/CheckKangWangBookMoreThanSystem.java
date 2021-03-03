package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProductInfoDetail;
import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.ProjectInfoDetail;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
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
public class CheckKangWangBookMoreThanSystem extends BaseKangWangDao{

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\公司数据\\操作数据\\康王店\\整理\\003康王店会员筛选 - 档案剩余次数比系统多.xlsx");
		
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
		ExcelSheet excelSheet = excel.getSheets().get(1);
		
		Map<String, NameToSystemName> kangWangName = getKangWangName();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String name = excelCells.get(3).getValue().toString();
			Integer buyNumber = Double.valueOf(excelCells.get(4).getValue().toString()).intValue();
			Integer doneNumber = Double.valueOf(excelCells.get(8).getValue().toString()).intValue();
			Integer buyNumber2 = Double.valueOf(excelCells.get(14).getValue().toString()).intValue();
			Integer doneNumber2 = Double.valueOf(excelCells.get(18).getValue().toString()).intValue();
			Integer leftNumber = Double.valueOf(excelCells.get(9).getValue().toString()).intValue();
			Integer leftNumber2 = Double.valueOf(excelCells.get(19).getValue().toString()).intValue();
			String buyDate = excelCells.get(10).getValue().toString().length() > 7 ? excelCells.get(10).getValue().toString() : "未知";
			buyDate = buyDate.length()<3 ? buyDate : buyDate.substring(0,buyDate.indexOf(" ")<0?buyDate.length():buyDate.indexOf(" "));
			String buyDate2 = excelCells.get(20).getValue().toString().length() > 7 ? excelCells.get(20).getValue().toString() : "未知";
			buyDate2 = buyDate2.length()<3 ? buyDate2 : buyDate2.substring(0,buyDate2.indexOf(" "));
			
			Double allPrice = Double.valueOf(excelCells.get(5).getValue().toString());
			
			String remark = excelCells.get(13).getValue() == null ? "无" : excelCells.get(13).getValue().toString();
			remark = remark.length()==0 ? "无" : remark;
			String remark2 = excelCells.get(24).getValue() == null ? "无" : excelCells.get(24).getValue().toString();
			remark2 = remark2.length()==0 ? "无" : remark2;
			
			String operation = excelCells.get(31).getValue() == null ? "" : excelCells.get(31).getValue().toString();
			
			if(operation.equals("pass")){
				continue;
			}else if(operation.equals("passDate")){
				buyDate2 = "未知";
			}
			
			if(leftNumber == leftNumber2){
				continue;
			}
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				continue;
//				throw new RuntimeException("手机号不存在");
			}
			//获取拥有的产品
			List<Map<String, Object>> productDetailLeft = getProductDetailLeft(memberCard.getId());
			//获取拥有的项目
			List<Map<String,Object>> projectDetailLeft = getProjectDetailLeft(memberCard.getId());
			//获取拥有的合作项目
			List<Map<String,Object>> cooProjectDetailLeft = getCooProjectDetailLeft(memberCard.getId());
			
			//=======================================================================判断产品==========================================================================
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
			if(productList.size() > 0){//产品处理逻辑
				ProductInfo info = productList.get(0);
				ProductInfoDetail productInfoDetail = getProductInfoDetail("110103", info.getId());
				String unit = "";
				if(info.getNum()!=null && info.getNum()>0){
					if(name.contains(info.getBoxesUnit())){
						unit = info.getBoxesUnit();
					}else if(name.contains(info.getBulkUnit())){
						unit = info.getBulkUnit();
					}else if(name.equals("水润修护原液（非卖品）")){
						unit = "支";
					}else{
						throw new RuntimeException("匹配不到大小单位");
					}
				}
				
				Map<String, Object> left = getProductLeftMap(productDetailLeft, info.getName(), buyNumber2, leftNumber2, buyDate2, operation);
//				if(left == null){
//					left = getProductLeftMap(productDetailLeft, info.getName(), null, leftNumber2, buyDate2, operation);
//				}
				
				if(left != null){
					String detailId = left.get("productDetailId").toString();
					String oldRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
					Double price = Double.valueOf(left.get("price").toString());
					int oldBuyNumber = Double.valueOf(left.get("buy_number").toString()).intValue();
					int oldLeftNumber = Double.valueOf(left.get("left_number").toString()).intValue();
					
					String oldBuyUnit = left.get("buy_unit") == null ? "" : left.get("buy_unit").toString();
					String oldLeftUnit = left.get("left_unit") == null ? "" : left.get("left_unit").toString();
					
					if(!oldBuyUnit.equals(oldLeftUnit)){
						throw new RuntimeException("购买与剩余大小单位不一样，人工排查");
					}
					
					
					//判断是否作废
					if(buyNumber>=0 && leftNumber==0 && buyNumber2 > 0 && leftNumber2 > 0){
						
						double chaPrice = -oldLeftNumber * price;
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setStockBalance(memberCard.getStockBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						newRemark += "作废，";
						newRemark += "用户存货" + memberCard.getStockBalance() + "=" + changeMember.getStockBalance();
						
						
						ProductDetailConsumption detail = new ProductDetailConsumption();
						detail.setId(detailId);
						detail.setIsFail(1);
						detail.setRemark(newRemark);
						int update2 = ProductSellDao.updateProductDetailConsumption(detail);
						System.out.println("执行作废操作"+"，备注"+oldRemark+"，购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber);
						if(update2 == 0){
							throw new RuntimeException("更新失败");
						}
						left.put("isOk", true);
						continue;
					}
					
					//判断是否更新
					if(leftNumber != leftNumber2){
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						int cha = leftNumber - leftNumber2;
						double chaPrice = cha * price;
						
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setStockBalance(memberCard.getStockBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						ProductDetailConsumption detail = new ProductDetailConsumption();
						detail.setId(detailId);
						detail.setLeftNumber(oldLeftNumber + cha);
						newRemark += "剩余数量" + oldLeftNumber + "==》" +detail.getLeftNumber();
						newRemark += "，用户存货" + memberCard.getStockBalance() + "==》" + changeMember.getStockBalance();
						detail.setRemark(newRemark);
						System.out.println("执行更新操作"+"购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber);
						update = ProductSellDao.updateProductDetailConsumption(detail);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						left.put("isOk", true);
						continue;
					}
					
				}else{
					System.out.println("用户财产找不到该产品，执行新增操作");
					String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：无" + "，操作：";
					double onePrice = 0D;
					if(buyNumber>0){
						onePrice = allPrice/buyNumber;
						BigDecimal b = new BigDecimal(onePrice);  
						onePrice  =  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
					}
					
					
					MemberCard changeMember = new MemberCard();
					changeMember.setId(memberCard.getId());
					changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + onePrice*leftNumber);
					int update = updateMemberCard(changeMember);
					if(update == 0){
						throw new RuntimeException("更新失败");
					}
					newRemark += "新增，";
					newRemark += "用户存货" + memberCard.getStockBalance() + "==》" + changeMember.getStockBalance();
					
					
					int addProductDetail = addProductDetail(memberCard.getId(), info.getId(), buyNumber, leftNumber, onePrice, unit, newRemark);
					if(addProductDetail == 0){
						throw new RuntimeException("新增产品失败");
					}
					continue;
				}
			}
			
			
			//=======================================================================判断项目=======================================================================
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setName(name);
			List<ProjectInfo> projectList = getProjectInfo(projectInfo);
			String secondName = "";
			Integer time = null;
			if(projectList.size() == 0){
				//查询对应的名称
				if(kangWangName.containsKey(name) && kangWangName.get(name).getType().equals("project")){
					projectInfo.setName(kangWangName.get(name).getNewName());
					projectList = getProjectInfo(projectInfo);
					
					time = kangWangName.get(name).getTime();
					secondName = getSecondName(kangWangName.get(name).getOldName());
					
					if(projectList.size() == 0){
						throw new RuntimeException("找不到项目名称信息");
					}
				}
			}
			if(projectList.size() > 0){//项目处理逻辑
				ProjectInfo info = projectList.get(0);
				ProjectInfoDetail projectInfoDetail = getProjectInfoDetail("110103", info.getId());
				time = time==null ? kangWangName.containsKey(name) ? kangWangName.get(name).getTime() : time : time;
				time = time==null ? getTimeFromKangWangName(kangWangName,name) : time;
 
				
				
				Map<String, Object> left = getProjectLeftMap(projectDetailLeft, info.getName(), buyNumber2, leftNumber2, buyDate2, operation);
//				if(left == null){
//					left = getProjectLeftMap(projectDetailLeft, info.getName(), null, leftNumber2, buyDate2, operation);
//				}
				
				if(left != null){
					String detailId = left.get("projectDetailId").toString();
					String oldRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
					Double price = Double.valueOf(left.get("price").toString());
					int oldBuyNumber = Double.valueOf(left.get("buy_number").toString()).intValue();
					int oldLeftNumber = Double.valueOf(left.get("number").toString()).intValue();
					int oldDoneNumber = oldBuyNumber - oldLeftNumber;
					
					//判断是否作废
					if((buyNumber==0 && leftNumber==0 && buyNumber2 > 0 && leftNumber2 > 0)
						|| (leftNumber==0 && leftNumber2 > 0)
					){
						double chaPrice = -oldLeftNumber * price;
						
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						newRemark += "作废，";
						newRemark += "用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
						
						
						ProjectDetailConsumption detail = new ProjectDetailConsumption();
						detail.setId(detailId);
						detail.setIsFail(1);
						detail.setRemark(newRemark);
						detail.setSecondName(secondName);
						System.out.println("执行作废操作，备注"+left.get("remark")+"，购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber+"，第二名字："+secondName);
						int update2 = ProjectSellDao.updateProjectDetailConsumption(detail);
						if(update2 == 0){
							throw new RuntimeException("更新项目失败");
						}
						left.put("isOk", true);
						continue;
					}
					
					//判断是否更新
					if(leftNumber != leftNumber2){
						if(leftNumber > leftNumber2){
							int projectDoneNumber = getProjectDoneNumber(detailId);
							int doneCha = oldDoneNumber - projectDoneNumber;
							if((leftNumber-leftNumber2) > doneCha){
								throw new RuntimeException("根据档案增加的次数 大于 系统能找到的次数，系统能找到的购买次数："+oldBuyNumber+"，剩余次数："+oldLeftNumber+"，消耗次数："+projectDoneNumber);
							}
						}
						
						
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						int cha = leftNumber - leftNumber2;
						double chaPrice = cha * price;
						
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						ProjectDetailConsumption detail = new ProjectDetailConsumption();
						detail.setId(detailId);
						detail.setNumber(oldLeftNumber + cha);
						newRemark += "剩余数量" + oldLeftNumber + "==》" +detail.getNumber();
						newRemark += "，用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
						detail.setRemark(newRemark);
						detail.setSecondName(secondName);
						System.out.println("执行更新操作"+"购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber+"，第二名字："+secondName);
						update = ProjectSellDao.updateProjectDetailConsumption(detail);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						left.put("isOk", true);
						continue;
					}
					
				}else{
					String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：无" + "，操作：";
					double onePrice = 0D;
					if(buyNumber>0){
						onePrice = allPrice/buyNumber;
						BigDecimal b = new BigDecimal(onePrice);  
						onePrice  =  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
					}
					MemberCard changeMember = new MemberCard();
					changeMember.setId(memberCard.getId());
					changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + onePrice*leftNumber);
					int update = updateMemberCard(changeMember);
					if(update == 0){
						throw new RuntimeException("更新失败");
					}
					newRemark += "新增，";
					newRemark += "用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
					
					System.out.println("用户财产找不到该项目，执行新增操作"+"，第二名字："+secondName);
					int addProjectDetail = addProjectDetail(memberCard.getId(), info.getId(), secondName, onePrice, buyNumber, leftNumber, time, newRemark);
					if(addProjectDetail == 0){
						throw new RuntimeException("新增项目失败");
					}
					continue;
				}
			}
			
			
			//=======================================================================判断合作 项目=======================================================================
			CooperationProject cooInfo = new CooperationProject();
			cooInfo.setName(name);
			List<CooperationProject> cooList = getCooperationProject(cooInfo);
			if(cooList.size() == 0){
				//查询对应的名称
				if(kangWangName.containsKey(name) && kangWangName.get(name).getType().equals("coo")){
					NameToSystemName nameToSystemName = kangWangName.get(name);
					if(nameToSystemName.getType().equals("coo")){
						cooInfo.setName(nameToSystemName.getNewName());
						cooList = getCooperationProject(cooInfo);
						
						if(cooList.size() == 0){
							throw new RuntimeException("找不到合作项目名称信息");
						}
					}
				}
			}
			if(cooList.size() > 0){
				CooperationProject info = cooList.get(0);
				
				Map<String, Object> left = getCooProjectLeftMap(cooProjectDetailLeft, info.getName(), buyNumber2, leftNumber2, buyDate2, operation);
//				if(left == null){
//					left = getCooProjectLeftMap(projectDetailLeft, info.getName(), null, leftNumber2, buyDate2, operation);
//				}
				
				if(left != null){
					String id = left.get("id").toString();
					String detailId = left.get("projectDetailId").toString();
					String oldRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
					Double price = Double.valueOf(left.get("price").toString());
					int oldBuyNumber = Double.valueOf(left.get("buy_number").toString()).intValue();
					int oldLeftNumber = Double.valueOf(left.get("left_number").toString()).intValue();
				
					//判断是否作废
					if((buyNumber==0 && leftNumber==0 && buyNumber2 > 0 && leftNumber2 > 0)
						|| (leftNumber==0 && leftNumber2 > 0)
					){
						double chaPrice = -oldLeftNumber * price;
						
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						newRemark += "作废，";
						newRemark += "用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
						
						
						ProjectDetailConsumption detail = new ProjectDetailConsumption();
						detail.setId(detailId);
						detail.setIsFail(1);
						detail.setRemark(newRemark);
						detail.setSecondName(secondName);
						System.out.println("执行作废操作，备注"+left.get("remark")+"，购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber);
						int update2 = ProjectSellDao.updateProjectDetailConsumption(detail);
						if(update2 == 0){
							throw new RuntimeException("更新项目失败");
						}
						left.put("isOk", true);
						continue;
					}
					
					//判断是否更新
					if(leftNumber != leftNumber2){
						String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：" + oldRemark + "，操作：";
						int cha = leftNumber - leftNumber2;
						double chaPrice = cha * price;
						
						MemberCard changeMember = new MemberCard();
						changeMember.setId(memberCard.getId());
						changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + chaPrice);
						int update = updateMemberCard(changeMember);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
						detail.setId(detailId);
						detail.setLeftNumber(oldLeftNumber + cha);
						newRemark += "剩余数量" + oldLeftNumber + "==》" +detail.getLeftNumber();
						newRemark += "，用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
						System.out.println("执行更新操作"+"购买数量:"+oldBuyNumber+"，剩余数量:"+oldLeftNumber);
						update = ProjectCooperationSellDao.updateProjectCooperationDetailConsumption(detail);
						if(update == 0){
							throw new RuntimeException("更新失败");
						}
						
						ProjectCooperationConsumption detailInfo = new ProjectCooperationConsumption();
						detailInfo.setId(id);
						detailInfo.setRemark(newRemark);
						int update2 = ProjectCooperationSellDao.updateProjectCooperationConsumption(detailInfo);
						if(update2 == 0){
							throw new RuntimeException("更新失败");
						}
						
						left.put("isOk", true);
						continue;
					}
				}else{
					String newRemark = "康王纸质档案录入系统，分类：" + remark2 +"，备注：" + remark + "，旧备注：无" + "，操作：";
					double onePrice = 0D;
					if(buyNumber>0){
						onePrice = allPrice/buyNumber;
						BigDecimal b = new BigDecimal(onePrice);  
						onePrice  =  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
					}
					MemberCard changeMember = new MemberCard();
					changeMember.setId(memberCard.getId());
					changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + onePrice*leftNumber);
					int update = updateMemberCard(changeMember);
					if(update == 0){
						throw new RuntimeException("更新失败");
					}
					newRemark += "新增，";
					newRemark += "用户疗程" + memberCard.getTreatmentBalance() + "==》" + changeMember.getTreatmentBalance();
					
					System.out.println("用户财产找不到该合作项目，执行新增操作");
					int addProjectDetail = addCooperationConsumption(memberCard.getId(), info.getId(), onePrice, buyNumber, leftNumber, 0D, 0D, 0D, 0D, newRemark + "，购买日期："+buyDate);
					
					if(addProjectDetail == 0){
						throw new RuntimeException("新增合作项目失败");
					}
					continue;
				}
			}
			
			
			throw new RuntimeException("找不到对应的信息:" + name);
		}
//		getSession().commit();
	}
	
	private static Map<String, Object> getCooProjectLeftMap(List<Map<String,Object>> projectDetailLeft,String name, Integer buyNumber2,
			Integer leftNumber2, String buyDate2, String operation) {
		// TODO Auto-generated method stub
		int index = 0;
		Map<String, Object> left = null;
		for (Map<String, Object> leftInfo : projectDetailLeft) {
			String oldName = leftInfo.get("name") == null ? "" : leftInfo.get("name").toString();
			int oldBuyNumber = Double.valueOf(leftInfo.get("buy_number").toString()).intValue();
			int oldLeftNumber = Double.valueOf(leftInfo.get("left_number").toString()).intValue();
			String oldCreateDate = leftInfo.get("create_date").toString();
			oldCreateDate = oldCreateDate.length()<11 ? oldCreateDate : oldCreateDate.substring(0,oldCreateDate.indexOf(" "));
			String oldBuyDate = leftInfo.get("consumption_date")==null?"":leftInfo.get("consumption_date").toString();
			oldBuyDate = oldBuyDate.length()<11 ? oldBuyDate : oldBuyDate.substring(0,oldBuyDate.indexOf(" "));
			String oldRemark = leftInfo.get("remark") == null ? "无" : leftInfo.get("remark").toString();
			
			buyDate2 = buyDate2==null ? null : buyDate2.equals("未知")?null:buyDate2;
			
			if(oldRemark.contains("康王纸质档案录入系统")){
				continue;
			}
			
			boolean isOk = leftInfo.get("isOk")!=null?true:false;
			
			if(!isOk){
				isOk = oldRemark.contains("康王纸质档案录入系统")?true:false;
			}
			
			boolean flag = false;
			if(!isOk && name.equals(oldName)){
				if( buyNumber2!=null && leftNumber2!=null && buyDate2!=null ){
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){//情况1
						flag = true;
					}
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2!=null && leftNumber2!=null && buyDate2==null ){
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2!=null ){//情况2
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){
						flag = true;
					}
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2==null ){
					if(leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else{
					flag = true;
				}
			}
			
			if(flag){
				++index;
				if(index > 1 && !operation.equals("choose")){
					throw new RuntimeException("找到用户多个对应的合作项目：" + name);
				}
				left = leftInfo;
			}
		}
		if(left!=null){
			String leftRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
			if(leftRemark.contains("要求录入")){
				throw new RuntimeException("不可修改");
			}
			int oldLeftNumber = Double.valueOf(left.get("number").toString()).intValue();
			if(oldLeftNumber != leftNumber2){
				throw new RuntimeException("找到的合作项目的剩余数量不等于文档里面的剩余数量");
			}
		}
		return left;
	}

	public static Map<String,Object> getProductLeftMap(List<Map<String,Object>> productDetailLeft, String name, Integer buyNumber2, Integer leftNumber2, String buyDate2, String operation){
		int index = 0;
		Map<String, Object> left = null;
		for (Map<String, Object> leftInfo : productDetailLeft) {
			String oldName = leftInfo.get("name").toString();
			int oldBuyNumber = Double.valueOf(leftInfo.get("buy_number").toString()).intValue();
			int oldLeftNumber = Double.valueOf(leftInfo.get("left_number").toString()).intValue();
			String oldCreateDate = leftInfo.get("create_date").toString();
			oldCreateDate = oldCreateDate.length()<3 ? oldCreateDate : oldCreateDate.substring(0,oldCreateDate.indexOf(" "));
			String oldBuyDate = leftInfo.get("consumption_date")==null?"":leftInfo.get("consumption_date").toString();
			oldBuyDate = oldBuyDate.length()<11 ? oldBuyDate : oldBuyDate.substring(0,oldBuyDate.indexOf(" "));
			String oldRemark = leftInfo.get("remark") == null ? "无" : leftInfo.get("remark").toString();
			
			buyDate2 = buyDate2==null ? null : buyDate2.equals("未知")?null:buyDate2;
			
			if(oldRemark.contains("康王纸质档案录入系统")){
				continue;
			}
			
			boolean isOk = leftInfo.get("isOk")!=null?true:false;
			if(!isOk){
				isOk = oldRemark.contains("康王纸质档案录入系统")?true:false;
			}
			
			boolean flag = false;
			if(!isOk && name.contains(oldName)){
				if( buyNumber2!=null && leftNumber2!=null && buyDate2!=null ){//情况1
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){
						flag = true;
					}
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2!=null && leftNumber2!=null && buyDate2==null ){
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2!=null ){//情况2
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){
						flag = true;
					}
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2==null ){
					if(leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else{
					flag = true;
				}
			}
			
			if(flag){
				++index;
				if(index > 1 && !operation.equals("choose")){
					throw new RuntimeException("找到用户多个对应的产品：" + name);
				}
				left = leftInfo;
			}
		}
		if(left!=null){
			String leftRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
			if(leftRemark.contains("要求录入")){
				throw new RuntimeException("不可修改");
			}
			int oldLeftNumber = Double.valueOf(left.get("left_number").toString()).intValue();
			if(oldLeftNumber != leftNumber2){
				throw new RuntimeException("找到的产品的剩余数量不等于文档里面的剩余数量");
			}
		}
		return left;
	}
	
	public static Map<String,Object> getProjectLeftMap(List<Map<String,Object>> projectDetailLeft, String name, Integer buyNumber2, Integer leftNumber2, String buyDate2, String operation){
		int index = 0;
		Map<String, Object> left = null;
		for (Map<String, Object> leftInfo : projectDetailLeft) {
			String oldName = leftInfo.get("name") == null ? "" : leftInfo.get("name").toString();
			int oldBuyNumber = Double.valueOf(leftInfo.get("buy_number").toString()).intValue();
			int oldLeftNumber = Double.valueOf(leftInfo.get("number").toString()).intValue();
			String oldCreateDate = leftInfo.get("create_date").toString();
			oldCreateDate = oldCreateDate.length()<11 ? oldCreateDate : oldCreateDate.substring(0,oldCreateDate.indexOf(" "));
			String oldBuyDate = leftInfo.get("consumption_date")==null?"":leftInfo.get("consumption_date").toString();
			oldBuyDate = oldBuyDate.length()<11 ? oldBuyDate : oldBuyDate.substring(0,oldBuyDate.indexOf(" "));
			String oldRemark = leftInfo.get("remark") == null ? "无" : leftInfo.get("remark").toString();
			
			buyDate2 = buyDate2==null ? null : buyDate2.equals("未知")?null:buyDate2;
			
			if(oldRemark.contains("康王纸质档案录入系统")){
				continue;
			}
			
			boolean isOk = leftInfo.get("isOk")!=null?true:false;
			
			if(!isOk){
				isOk = oldRemark.contains("康王纸质档案录入系统")?true:false;
			}
			
			boolean flag = false;
			if(!isOk && name.equals(oldName)){
				if( buyNumber2!=null && leftNumber2!=null && buyDate2!=null ){//情况1
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){
						flag = true;
					}
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2!=null && leftNumber2!=null && buyDate2==null ){
					if(buyNumber2 == oldBuyNumber && leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2!=null ){//情况2
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldCreateDate)){
						flag = true;
					}
					if(leftNumber2 == oldLeftNumber && buyDate2.equals(oldBuyDate)){
						flag = true;
					}
				}else if( buyNumber2==null && leftNumber2!=null && buyDate2==null ){
					if(leftNumber2 == oldLeftNumber){
						flag = true;
					}
				}else{
					flag = true;
				}
			}
			
			if(flag){
				++index;
				if(index > 1 && !operation.equals("choose")){
					throw new RuntimeException("找到用户多个对应的项目：" + name);
				}
				left = leftInfo;
			}
		}
		if(left!=null){
			String leftRemark = left.get("remark") == null ? "无" : left.get("remark").toString();
			if(leftRemark.contains("要求录入")){
				throw new RuntimeException("不可修改，备注:"+leftRemark);
			}
			int oldLeftNumber = Double.valueOf(left.get("number").toString()).intValue();
			if(oldLeftNumber != leftNumber2){
				throw new RuntimeException("找到的项目的剩余数量不等于文档里面的剩余数量");
			}
		}
		return left;
	}

	private static Integer getTimeFromKangWangName(Map<String, NameToSystemName> kangWangName, String name) {
		// TODO Auto-generated method stub
		Iterator<Entry<String, NameToSystemName>> iterator = kangWangName.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, NameToSystemName> next = iterator.next();
			if(next.getValue().getNewName().equals(name)){
				return next.getValue().getTime();
			}
		}
		return null;
	}
}
