package com.qhc.service.inventory.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.common.constant.Common;
import com.qhc.common.entity.MaterialAttribute;
import com.qhc.common.entity.common.JsonResult;
import com.qhc.common.entity.employee.DepartmentChief;
import com.qhc.common.entity.employee.Employee;
import com.qhc.common.entity.exception.BusinessException;
import com.qhc.common.entity.inventory.InventoryReviewProcess;
import com.qhc.common.entity.inventory.MaterialApplyDetail;
import com.qhc.common.entity.inventory.MaterialApplyInfo;
import com.qhc.common.entity.inventory.MaterialApplyOrder;
import com.qhc.common.entity.inventory.MaterialApplyProgress;
import com.qhc.common.entity.inventory.MaterialInventoryDetail;
import com.qhc.common.entity.inventory.MaterialInventoryInfo;
import com.qhc.common.entity.inventory.MaterialInventoryOrder;
import com.qhc.common.entity.inventory.MaterialPurchaseDetail;
import com.qhc.common.entity.inventory.MaterialPurchaseInfo;
import com.qhc.common.entity.inventory.MaterialPurchaseOrder;
import com.qhc.common.entity.inventory.StorageOutPutDetail;
import com.qhc.common.entity.inventory.StorageOutPutInfo;
import com.qhc.common.entity.inventory.StorageOutPutOrder;
import com.qhc.common.entity.inventory.Storehouse;
import com.qhc.common.entity.log.MaterialInventoryChange;
import com.qhc.common.entity.log.MaterialInventoryChange.Status;
import com.qhc.common.entity.material.MaterialInfo;
import com.qhc.common.entity.material.MaterialInventory;
import com.qhc.common.entity.order.OrderBase;
import com.qhc.common.enums.OrderEnum.OrderStatus;
import com.qhc.common.enums.OrderEnum.OrderType;
import com.qhc.common.utils.MessageUtilsYunPian;
import com.qhc.common.utils.SelectItemUtils;
import com.qhc.dao.inventory.IInventoryDao;
import com.qhc.dao.log.ILogDao;
import com.qhc.dao.material.IMaterialDao;
import com.qhc.dao.order.IOrderDao;
import com.qhc.service.base.BaseService;
import com.qhc.service.employee.Impl.EmployeeService;
import com.qhc.service.inventory.IInventoryService;
import com.qhc.service.product.IProductService;

/**
 * 库存物料相关
 * @author Lsenrong
 * @date Jul 6, 2017 4:08:24 PM
 * @Description: TODO(描述)
 */
@Transactional(rollbackFor=Exception.class)
@Service("inventoryService")
public class InventoryService extends BaseService implements IInventoryService {
	@Autowired
    private IInventoryDao inventoryDao;
	@Autowired
	private IOrderDao orderDao;
	@Autowired 
	private IMaterialDao materialDao;
	@Autowired
	private ILogDao logDao;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 新增物料申请单
	 * @Title: addMaterialApplyOrder 
	 * @author: Lsenrong
	 * @date Jul 6, 2017 4:18:10 PM
	 * @Description: TODO(描述)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public Boolean addMaterialApplyOrder(MaterialApplyOrder materialApplyOrder) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		materialApplyOrder.setStatus(OrderStatus.SAVE);
		flag = 1 == orderDao.addOrderBase(materialApplyOrder);
		if(flag){
			flag = 1 == inventoryDao.addMaterialApplyInfo(materialApplyOrder);
		}
		
		for(MaterialApplyDetail materialApplyDetail:  materialApplyOrder.getDetails())
		{
			MaterialInfo materialInfo =  materialDao.getMaterialById(materialApplyDetail.getMaterialId());
			materialApplyDetail.setOrderId(materialApplyOrder.getOrderNo());
			materialApplyDetail.setMaterialNo(materialInfo.getNo());
			materialApplyDetail.setMaterialName(materialInfo.getName());
			materialApplyDetail.setMaterialForm(materialInfo.getForm());
			materialApplyDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
			materialApplyDetail.setMaterialBigUnit(materialInfo.getBigUnit());
			materialApplyDetail.setMaterialNumber(materialInfo.getNumber());
			materialApplyDetail.setMaterialPrice(materialInfo.getPrice());
			materialApplyDetail.setMaterialTax(materialInfo.getTax());
			materialApplyDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
			materialApplyDetail.setMaterialDeliverUnit(materialInfo.getDeliverUnit());
			if(flag)
				inventoryDao.addMaterialApplyDetail(materialApplyDetail);
			else
				break;
		}
		if(flag){
			MaterialApplyProgress materialApplyProgress = new MaterialApplyProgress();
			materialApplyProgress.setOrderId(materialApplyOrder.getOrderNo());
			materialApplyProgress.setRemark(materialApplyOrder.getRemark());
			materialApplyProgress.setCreateUserId(materialApplyOrder.getCreateUserId());
			materialApplyProgress.setStatus(materialApplyOrder.getStatus());
			flag = 1 == inventoryDao.addMaterialApplyProgress(materialApplyProgress);
		}
	    if(!flag)
	    	throw new BusinessException("新增物料申请单失败");//触发数据回滚
		return flag;
	}
	/**
	 * 更新物料申请单
	 * @Title: updateMaterialApplyOrder 
	 * @author: Lsenrong
	 * @date Jul 6, 2017 4:18:23 PM
	 * @Description: TODO(描述)
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean updateMaterialApplyOrder(MaterialApplyOrder materialApplyOrder) {
		// TODO Auto-generated method stub
		Boolean flag = true;
		String msg = "保存更新物料申请单失败";
		MaterialApplyInfo oldMaterialApplyInfo = inventoryDao.getMaterialApplyInfoByOrderId(materialApplyOrder.getOrderNo());
		if(materialApplyOrder.getStatus().equals(0) && (oldMaterialApplyInfo.getStatus().equals(0) || oldMaterialApplyInfo.getStatus().equals(2))){
			flag = true;
		} else if(materialApplyOrder.getStatus() <= 2 && oldMaterialApplyInfo.getStatus() > 2){
			flag = false;
			msg = "已是发货状态，请刷新界面！";
		} else if(materialApplyOrder.getStatus() < oldMaterialApplyInfo.getStatus()){
			flag = false;
			msg = "单据状态已改变，请刷新界面！";
		} else if(materialApplyOrder.getStatus().equals(oldMaterialApplyInfo.getStatus())){
			flag = false;
			msg = "单据状态已改变，请刷新界面！";
		}
	
		
		if(flag){
			orderDao.updateOrderBase(materialApplyOrder);
			inventoryDao.updateMaterialApplyInfo(materialApplyOrder);
			
			
			if(materialApplyOrder.getStatus().equals(0)){
				//普通更新
				//执行先删除再更新....
				inventoryDao.deleteMaterialApplyDetailsByOrderId(materialApplyOrder.getOrderNo());
				for(MaterialApplyDetail materialApplyDetail:  materialApplyOrder.getDetails())
				{
					MaterialInfo materialInfo =  materialDao.getMaterialById(materialApplyDetail.getMaterialId());
					materialApplyDetail.setOrderId(materialApplyOrder.getOrderNo());
					materialApplyDetail.setOrderId(materialApplyOrder.getOrderNo());
					materialApplyDetail.setMaterialNo(materialInfo.getNo());
					materialApplyDetail.setMaterialName(materialInfo.getName());
					materialApplyDetail.setMaterialForm(materialInfo.getForm());
					materialApplyDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
					materialApplyDetail.setMaterialBigUnit(materialInfo.getBigUnit());
					materialApplyDetail.setMaterialNumber(materialInfo.getNumber());
					materialApplyDetail.setMaterialPrice(materialInfo.getPrice());
					materialApplyDetail.setMaterialTax(materialInfo.getTax());
					materialApplyDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
					materialApplyDetail.setMaterialDeliverUnit(materialInfo.getDeliverUnit());
					flag = 1 == inventoryDao.addMaterialApplyDetail(materialApplyDetail);
					if(!flag) break;
				}
			} else if(materialApplyOrder.getStatus().equals(1)){
				//已审核
				for(MaterialApplyDetail materialApplyDetail:  materialApplyOrder.getDetails())
				{
					MaterialInfo materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap(){{
						put("materialId", materialApplyDetail.getMaterialId());
						put("shopId", Common.HEAD_OFFICE_ID);//总仓
					}});
					if(materialInventory == null){
						flag = false;
						msg = materialApplyDetail.getMaterialName() + "未盘点初始化或采购入库，无法进行审核操作，请检查！";
						break;
					}else{
						
						if(materialApplyDetail.getAuditNumber() > materialInventory.getUsableInventory())
						{
							flag = false;
							msg = materialInventory.getName() + "物料总仓库存" + materialInventory.getUsableInventory() + materialInventory.getSmallUnit() + ",请检查你的审核通过数量是否填写正确...";
						}else{
							flag = 1 == inventoryDao.updateMaterialApplyDetail(new MaterialApplyDetail(){{
								setAuditNumber(materialApplyDetail.getAuditNumber());
								setAuditRemark(materialApplyDetail.getAuditRemark());
								setId(materialApplyDetail.getId());
							}});
						}
						
					}
					if(!flag) break;
					
				}
			} else if(materialApplyOrder.getStatus().equals(2)){
				//审核退回
				
			} else if(materialApplyOrder.getStatus().equals(3)){
				//已发货
				//拉取基础信息
				MaterialApplyInfo materialApplyInfo = inventoryDao.getMaterialApplyInfoByOrderId(materialApplyOrder.getOrderNo());
				for(MaterialApplyDetail materialApplyDetail:  materialApplyOrder.getDetails())
				{
					//更新出库信息
					flag = 1 == inventoryDao.updateMaterialApplyDetail(new MaterialApplyDetail(){{
						setDeliveryNumber(materialApplyDetail.getDeliveryNumber());
						setDeliveryRemark(materialApplyDetail.getDeliveryRemark());
						setId(materialApplyDetail.getId());
					}});
					if(flag){
						MaterialApplyDetail newDetail = inventoryDao.getMaterialApplyDetailById(materialApplyDetail.getId());
						Double convert = convertToSmallUnit(newDetail.getApplyUnit(),newDetail.getDeliveryNumber(), newDetail);
						//总仓出库
						//现在库存
						MaterialInfo materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap(){{
							put("materialId", newDetail.getMaterialId());
							put("shopId", materialApplyInfo.getAuditStorehouseId());//接受申请仓库id
//							put("shopId", Common.HEAD_OFFICE_ID);//总仓
						}});
						if(materialInventory == null){
							flag = false;
							msg = newDetail.getMaterialName() + "未盘点初始化或采购入库，无法进行发货操作，请检查！";
							break;
						}else{
							if(materialInventory.getUsableInventory() - convert >= 0){
								MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
								materialInventoryChange.setShopId(materialApplyInfo.getAuditStorehouseId());
//								materialInventoryChange.setShopId(Common.HEAD_OFFICE_ID);
								materialInventoryChange.setOrderId(newDetail.getOrderId());
								materialInventoryChange.setOrderType(OrderType.MATERIAL_APPLY);
								materialInventoryChange.setMaterialId(newDetail.getMaterialId());
								materialInventoryChange.setFrom(materialInventory.getUsableInventory());
								materialInventoryChange.setTo(materialInventory.getUsableInventory() - convert);
								materialInventoryChange.setCreateUserId(materialApplyInfo.getCreateUserId());
								materialInventoryChange.setRemark("仓库发货");
								materialInventoryChange.setStatus(Status.COMMON);
								materialInventoryChange.setCreateDateTime(new Date());
								materialInventoryChange.setChangeDate(materialApplyInfo.getRealDate());
								//库存异动日志
								if(flag) flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
								if(flag){
									//更新库存
									MaterialInventory newMaterialInventory = new MaterialInventory();
									newMaterialInventory.setShopId(materialApplyInfo.getAuditStorehouseId());
//									newMaterialInventory.setShopId(Common.HEAD_OFFICE_ID);
									newMaterialInventory.setMaterialId(newDetail.getMaterialId());
									newMaterialInventory.setUsableInventory(-convert);
									flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(newMaterialInventory);
									if(!flag) break;
								}else{
									break;
								}
							}else{
								flag = false;
								msg = newDetail.getMaterialName() + "当前库存不足以发货，请检查！";
								break;
							}
						}
					}
					if(!flag) break;
				}
			} else if(materialApplyOrder.getStatus().equals(4)){
				//已收货
				//拉取基础信息
				MaterialApplyInfo materialApplyInfo = inventoryDao.getMaterialApplyInfoByOrderId(materialApplyOrder.getOrderNo());
				for(MaterialApplyDetail materialApplyDetail:  materialApplyOrder.getDetails())
				{
					flag = 1 == inventoryDao.updateMaterialApplyDetail(new MaterialApplyDetail(){{
						setReceivingNumber(materialApplyDetail.getReceivingNumber());
						setReceivingRemark(materialApplyDetail.getReceivingRemark());
						setId(materialApplyDetail.getId());
					}});
					if(flag){
						MaterialApplyDetail newDetail = inventoryDao.getMaterialApplyDetailById(materialApplyDetail.getId());
						//根据收货数量转换单位
						Double outNumber = newDetail.getDeliveryNumber();//总仓
						Double putNumber = newDetail.getReceivingNumber();//门店
						Double outConvert = convertToSmallUnit(newDetail.getApplyUnit(),outNumber, newDetail);//总仓
						Double putConvert = convertToSmallUnit(newDetail.getApplyUnit(),putNumber, newDetail);//门店;
						String outRemark = "";
						String putRemark = "";
						flag = false;
						//应仓库要求统一设置为正常 
						newDetail.setReceivingRemark("0");
						switch (newDetail.getReceivingRemark()) {
						case "0"://正常单据
							//出入库一致
							if(outNumber.equals(putNumber)){
								flag = true;
								putRemark = "申请门店正常收货";
								outConvert = 0.0;//总部无需变动库存
							}else{
								msg = "为正常出入库数量应该一致！请检查！";
							}
							break;
						case "1"://发错货
							flag = true;
							outConvert = outConvert - putConvert;//总部门店发错货申请门店没收货则回退至总部门店库存；
							putRemark = "总部发错货申请门店仍入库" + putConvert + newDetail.getMaterialSmallUnit();
							outRemark = "出库门店发错货出库门店要回库" + outConvert + newDetail.getMaterialSmallUnit();
							break;
						case "2"://发少货
							if(outNumber > putNumber){
								flag = true;
								outConvert = outConvert - putConvert;//总部发少货；
								putRemark = "总部发少货收货门店实际入库" + putConvert + newDetail.getMaterialSmallUnit();
								outRemark = "总部发少货总部回库" + outConvert + newDetail.getMaterialSmallUnit();
							} else {
								msg = newDetail.getMaterialName() + "入库差异原因选择错误请检查！";
							}
							break;
						case "3"://发多货
							if(outNumber < putNumber){
								flag = true;
								outConvert = outConvert - putConvert;//总部门店发多货入库门店；
								putRemark = "总部发多货收货门店实际入库" + putConvert + newDetail.getMaterialSmallUnit();
								outRemark = "总部发多货总部门店回库" + outConvert + newDetail.getMaterialSmallUnit();
							}else{
								msg = newDetail.getMaterialName() + "入库差异原因选择错误请检查！";
							}
							break;
						case "4"://中途损耗
							if(outNumber > putNumber){
							    flag = true;
							    putRemark = "总部发货中途损耗收货门店实际入库" + putConvert + newDetail.getMaterialSmallUnit() + "库存丢失" + (outConvert - putConvert);
							    outConvert = 0.0;
							}else{
								msg = newDetail.getMaterialName() + "入库差异原因选择错误请检查！";
							}
							break;
						case "5"://货物损坏
							if(outNumber > putNumber){
							    flag = true;
							    putRemark = "出库门店货物损坏入库门店实际入库" + putConvert + newDetail.getMaterialSmallUnit() + "库存丢失" + (outConvert - putConvert);
							    outConvert = 0.0;
							}else{
								msg = newDetail.getMaterialName() + "入库差异原因选择错误请检查！";
							}
							break;
						default:
							msg = "未知的入库原因！";
							break;
						}
						
						
						//收货门店的操作
						if(flag && putConvert > 0){
							//收货门店现在库存
							MaterialInfo materialPutInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap(){{
								put("materialId", newDetail.getMaterialId());
								put("shopId", materialApplyInfo.getShopId());
							}});
							if(materialPutInventory == null){
								//初始化库存
								MaterialInventory materialInventoryInit = new MaterialInventory();
								materialInventoryInit.setInventoryId(UUID.randomUUID().toString());
								materialInventoryInit.setShopId(materialApplyInfo.getShopId());
								materialInventoryInit.setMaterialId(newDetail.getMaterialId());
								materialInventoryInit.setUsableInventory(new Double(0));
								materialInventoryInit.setCheckInventory(new Double(0));
								materialInventoryInit.setAlertInventory(new Double(0));
								materialInventoryInit.setCheckInventory(new Double(0));
								flag = 1 == materialDao.addMaterialInventory(materialInventoryInit);
								materialPutInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
										put("materialId", newDetail.getMaterialId());
										put("shopId", materialApplyInfo.getShopId());
									}});
							}
							MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
							materialInventoryChange.setShopId(materialApplyInfo.getShopId());
							materialInventoryChange.setOrderId(newDetail.getOrderId());
							materialInventoryChange.setOrderType(OrderType.MATERIAL_APPLY);
							materialInventoryChange.setMaterialId(newDetail.getMaterialId());
							materialInventoryChange.setFrom(materialPutInventory.getUsableInventory());
							materialInventoryChange.setTo(materialPutInventory.getUsableInventory() + putConvert);
							materialInventoryChange.setCreateUserId(materialApplyInfo.getCreateUserId());
							materialInventoryChange.setRemark("物料申请单收货");
							materialInventoryChange.setStatus(Status.COMMON);
							materialInventoryChange.setCreateDateTime(new Date());
							materialInventoryChange.setChangeDate(materialApplyInfo.getRealDate());
							//库存异动日志
							if(flag) flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
							if(flag){
								//更新库存
								MaterialInventory materialInventory = new MaterialInventory();
								materialInventory.setShopId(materialApplyInfo.getShopId());
								materialInventory.setMaterialId(newDetail.getMaterialId());
								materialInventory.setUsableInventory(putConvert);
								flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory);
								if(!flag) break;
							}else{
								break;
							}
							
						}
						
						
						//总部仓库的操作
						if(flag && outConvert != 0){
							//收货门店现在库存
							MaterialInfo materialOutInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap(){{
								put("materialId", newDetail.getMaterialId());
								put("shopId", materialApplyInfo.getAuditStorehouseId());
							}});
							if(materialOutInventory == null){
								flag = false;
								msg = "仓库" + materialOutInventory.getName() + "未入库，数据异常....！！";
								break;
							}else{
								MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
								materialInventoryChange.setShopId(materialApplyInfo.getShopId());
								materialInventoryChange.setOrderId(newDetail.getOrderId());
								materialInventoryChange.setOrderType(OrderType.MATERIAL_APPLY);
								materialInventoryChange.setMaterialId(newDetail.getMaterialId());
								materialInventoryChange.setFrom(materialOutInventory.getUsableInventory());
								materialInventoryChange.setTo(materialOutInventory.getUsableInventory() + outConvert);
								materialInventoryChange.setCreateUserId(materialApplyInfo.getCreateUserId());
								materialInventoryChange.setRemark("处理门店物料申请单异常单自动修复总仓库存");
								materialInventoryChange.setStatus(Status.COMMON);
								materialInventoryChange.setCreateDateTime(new Date());
								materialInventoryChange.setChangeDate(materialApplyInfo.getRealDate());
								//库存异动日志
								if(flag) flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
								if(flag){
									//更新库存
									MaterialInventory materialInventory = new MaterialInventory();
									materialInventory.setShopId(materialApplyInfo.getAuditStorehouseId());
//									materialInventory.setShopId(Common.HEAD_OFFICE_ID);
									materialInventory.setMaterialId(newDetail.getMaterialId());
									materialInventory.setUsableInventory(outConvert);
									flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory);
									if(!flag) break;
								}else{
									break;
								}
							}
							
							
						}
						
						
						
					}
					if(!flag) break;
				}
			}
		}
		
		
		
		//MaterialApplyInfo materialApplyInfo = inventoryDao.getMaterialApplyInfoByOrderId(materialApplyOrder.getOrderId());
		
		
		if(flag){
			MaterialApplyProgress materialApplyProgress = new MaterialApplyProgress();
			materialApplyProgress.setOrderId(materialApplyOrder.getOrderNo());
			materialApplyProgress.setRemark(materialApplyOrder.getRemark());
			materialApplyProgress.setCreateUserId(materialApplyOrder.getCreateUserId());
			materialApplyProgress.setStatus(materialApplyOrder.getStatus());
			flag = 1 == inventoryDao.addMaterialApplyProgress(materialApplyProgress);
		}
	    if(!flag)
	    	throw new BusinessException(msg);
		return flag;
	}
    /**
     * 获取物料申请单的列表
     * @Title: getMaterialApplyInfos 
     * @author: Lsenrong
     * @date Jul 10, 2017 2:53:07 PM
     * @Description: TODO(描述)
     */
	@Override
	public List<MaterialApplyInfo> getMaterialApplyInfos(Map<String, Object> params){
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialApplyInfos(params);
	}
     /**
	 * 获取物料申请单记录条数
	 * @Title: getMaterialApplyInfosCount 
	 * @author: Lsenrong
	 * @date Jul 13, 2017 11:17:20 AM
	 * @Description: TODO(描述)
	 */
	@Override
	public Integer getMaterialApplyInfosCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialApplyInfosCount(params);
	}
    /**
     * 获取物料申请单通过单据id
     * @Title: getMaterialApplyOrderByOrderId 
     * @author: Lsenrong
     * @date Jul 14, 2017 11:07:42 AM
     * @Description: TODO(描述) 
     * @param @param orderId
     * @param @return    
     * @return MaterialApplyOrder
     */
	@Override
	public MaterialApplyOrder getMaterialApplyOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		MaterialApplyInfo materialApplyInfo = inventoryDao.getMaterialApplyInfoByOrderId(orderId);
		List<MaterialApplyDetail> materialApplyDetails = inventoryDao.getMaterialApplyDetailByOrderId(new HashMap<String,Object>(){{
			put("orderId", orderId);
			put("shopId", Common.HEAD_OFFICE_ID);
		}});
		List<MaterialApplyProgress> materialApplyProgersses = inventoryDao.getMaterialApplyProgressByOrderId(orderId);
		MaterialApplyOrder materialApplyOrder = new MaterialApplyOrder();
		materialApplyOrder.setOrderNo(materialApplyInfo.getOrderNo());
		materialApplyOrder.setCreateDateTime(materialApplyInfo.getCreateDateTime());
		materialApplyOrder.setRealDate(materialApplyInfo.getRealDate());
		materialApplyOrder.setStatus(materialApplyInfo.getStatus());
		materialApplyOrder.setShopId(materialApplyInfo.getShopId());
		materialApplyOrder.setShopName(materialApplyInfo.getShopName());
		materialApplyOrder.setCreateUserId(materialApplyInfo.getCreateUserId());
		materialApplyOrder.setCreateUserName(materialApplyInfo.getCreateUserName());
		materialApplyOrder.setOrderId(materialApplyInfo.getOrderId());
		materialApplyOrder.setApplicantId(materialApplyInfo.getApplicantId());
		materialApplyOrder.setApplicantName(materialApplyInfo.getApplicantName());
		materialApplyOrder.setAuditDateTime(materialApplyInfo.getAuditDateTime());
		materialApplyOrder.setDeliveryDateTime(materialApplyInfo.getDeliveryDateTime());
		materialApplyOrder.setReceivingDateTime(materialApplyInfo.getReceivingDateTime());
		materialApplyOrder.setReceivingAddress(materialApplyInfo.getReceivingAddress());
		materialApplyOrder.setShopPhone(materialApplyInfo.getShopPhone());
		materialApplyOrder.setAuditStorehouseId(materialApplyInfo.getAuditStorehouseId());
		materialApplyOrder.setAuditStorehouseName(materialApplyInfo.getAuditStorehouseName());
		for(MaterialApplyDetail materialApplyDetail :  materialApplyDetails){
			Object receivingRemarkO = SelectItemUtils.getMaterialPutReasonByKey(materialApplyDetail.getReceivingRemark());
			materialApplyDetail.setReceivingRemark(receivingRemarkO != null ? receivingRemarkO.toString() : "");
		}
		materialApplyOrder.setDetails(materialApplyDetails);
		
		materialApplyOrder.setProgresses(materialApplyProgersses);
		return materialApplyOrder;
	}

	
	
	
	
	
	
	
	/**
	 * 获取库存单据列表
	 * @Title: getMaterialInventoryInfos 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:50:46 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public List<MaterialInventoryInfo> getMaterialInventoryInfos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialInventoryInfos(params);
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Integer getMaterialInventoryInfosCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialInventoryInfosCount(params);
	}

	/**
	 * 新增物料盘点单
	 * @Title: addMaterialInventoryOrder 
	 * @author: Lsenrong
	 * @throws UnsupportedEncodingException 
	 * @date Aug 16, 2017 3:37:03 PM
	 * @Description: TODO(描述)
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean addMaterialInventoryOrder(MaterialInventoryOrder materialInventoryOrder) throws UnsupportedEncodingException {
		if (materialInventoryOrder.getIsAdd() == 0) {
			String orderId = materialInventoryOrder.getOrderId();
			if (StringUtils.isBlank(orderId)) {
				throw new BusinessException("盘点单号不能为空!");
			}
			//删除盘点单
			orderDao.deleteOrderBase(orderId);
			inventoryDao.deleteMaterialInventoryInfo(orderId);
			inventoryDao.deleteMaterialInventoryDetail(orderId);
			inventoryDao.deleteInventoryReviewProcess(orderId);
		}
		//判断是否有未入库的单据
		hasStorageOutPutInfo(materialInventoryOrder.getShopId());
		//<!----以下是以前的处理方式-->
		Boolean flag = true;
		flag = 1 == orderDao.addOrderBase(materialInventoryOrder);
		materialInventoryOrder.setOrderId(materialInventoryOrder.getOrderNo());
		if(flag) flag = 1 == inventoryDao.addMaterialInventoryInfo(materialInventoryOrder);
		if(flag){
			double count = 0;
			for(MaterialInventoryDetail materialInventoryDetail : materialInventoryOrder.getDetails())
			{
				if(!flag) break;
				//获取对应门店物料库存信息
				MaterialInfo materialInfo = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
					put("materialId", materialInventoryDetail.getMaterialId());
					put("shopId", materialInventoryOrder.getShopId());
				}});
				//对于没有盘点过的物料初始化库存
				if(materialInfo == null){
					MaterialInventory materialInventory = new MaterialInventory();
					materialInventory.setInventoryId(UUID.randomUUID().toString());
					materialInventory.setShopId(materialInventoryOrder.getShopId());
					materialInventory.setMaterialId(materialInventoryDetail.getMaterialId());
					materialInventory.setUsableInventory(new Double(0));
					materialInventory.setCheckInventory(new Double(0));
					materialInventory.setAlertInventory(new Double(0));
					materialInventory.setCheckInventory(new Double(0));
					flag = 1 == materialDao.addMaterialInventory(materialInventory);
					materialInfo = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
							put("materialId", materialInventoryDetail.getMaterialId());
							put("shopId", materialInventoryOrder.getShopId());
						}});
				}
				//获取可用库存
				Double usableInventory = materialInventoryDetail.getInventoryUsableNumber();
				//盘点单位
				String inventoryUnit = materialInventoryDetail.getInventoryUnit();
				//获取真实可用库存
				Double usableInventoryTrue = materialInfo.getUsableInventory();
				//真实盘点单位
				String smallUnit = materialInfo.getSmallUnit();
				setMaterialInventoryDetail(materialInventoryDetail, materialInventoryOrder, materialInfo);
				usableInventory = convertToSmallUnit(inventoryUnit, usableInventory, materialInventoryDetail);
				//计算差异库存
				usableInventory = usableInventory - usableInventoryTrue;
				//计算盘亏盘盈金额,假设金额为500
				count = 1900;
				materialInventoryDetail.setOrderId(materialInventoryOrder.getOrderNo());
				materialInventoryDetail.setMaterialNo(materialInfo.getNo());
				materialInventoryDetail.setMaterialName(materialInfo.getName());
				materialInventoryDetail.setMaterialTax(materialInfo.getTax());
				materialInventoryDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
				materialInventoryDetail.setMaterialBigUnit(materialInfo.getBigUnit());
				materialInventoryDetail.setMaterialNumber(materialInfo.getNumber());
				materialInventoryDetail.setMaterialForm(materialInfo.getForm());
				//换算寄存库存
				Double materialCheckInventory = convertToSmallUnit(materialInventoryDetail.getInventoryUnit(),materialInventoryDetail.getInventoryCheckNumber(),materialInventoryDetail);
				//换算可用库存
				Double materialUsableInventory = convertToSmallUnit(materialInventoryDetail.getInventoryUnit(),materialInventoryDetail.getInventoryUsableNumber(),materialInventoryDetail);
				materialInventoryDetail.setStorageCheckFrom(materialInfo.getCheckInventory());
				materialInventoryDetail.setStorageCheckTo(materialCheckInventory);
				materialInventoryDetail.setStorageUsableFrom(materialInfo.getUsableInventory());
				materialInventoryDetail.setStorageUsableTo(materialUsableInventory);
				//插入盘点单详情
				if(flag) flag = 1 == inventoryDao.addMaterialInventoryDetail(materialInventoryDetail);
			}
			//添加审核流程
			if (materialInventoryOrder.getStatus() == 1) {
				bindProcess(count, materialInventoryOrder.getShopId(), materialInventoryOrder.getOrderId());
			}
		}
		if(!flag) throw new BusinessException("保存物料盘点单失败咯");//执行数据回滚
		return flag;
	}
	
	/**
	 * @Title: auditSuccess
	 * @author : Lwf
	 * @param materialInventoryOrder 
	 * @Description: TODO(盘点审核成功)
	 * @DATE :2020年5月22日 
	 * @return :void
	 */
	private void auditSuccess(MaterialInventoryOrder materialInventoryOrder){
		List<MaterialInventoryDetail> details = materialInventoryOrder.getDetails();
		for (MaterialInventoryDetail materialInventoryDetail : details) {
			//写入库存异动日志（库存变化日志不包含寄存库存！！！）
			MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
			materialInventoryChange.setShopId(materialInventoryOrder.getShopId());
			materialInventoryChange.setOrderId(materialInventoryOrder.getOrderNo());
			materialInventoryChange.setCreateUserId(materialInventoryOrder.getCreateUserId());
			materialInventoryChange.setFrom(materialInventoryDetail.getStorageUsableFrom());
			materialInventoryChange.setTo(materialInventoryDetail.getStorageUsableTo());
			materialInventoryChange.setCreateUserId(materialInventoryOrder.getCreateUserId());
			materialInventoryChange.setMaterialId(materialInventoryDetail.getMaterialId());
			materialInventoryChange.setOrderType(OrderType.MATERIAL_CHECK);
			materialInventoryChange.setRemark("盘点单");
			materialInventoryChange.setChangeDate(materialInventoryOrder.getRealDate());
			materialInventoryChange.setStatus(Status.COMMON);
			//插入库存异动日志
			logDao.addMaterialInventoryChange(materialInventoryChange);
			
			//更新库存
			MaterialInventory materialInventory = new MaterialInventory();
			materialInventory.setShopId(materialInventoryOrder.getShopId());
			materialInventory.setMaterialId(materialInventoryDetail.getMaterialId());
			materialInventory.setUsableInventory(materialInventoryChange.getTo());
			materialInventory.setCheckInventory(materialInventoryDetail.getStorageCheckTo());
			//更新库存
			 materialDao.resetInventoryByShopIdAndMaterialId(materialInventory);
		}
	}
	
	/**  
	 * @Title: hasStorageOutPutInfo
	 * @author : Lwf
	 * @param shopId 
	 * @Description: TODO(描述)
	 * @DATE :2020年5月22日 
	 * @return :void 
	 */  
	private void hasStorageOutPutInfo(String shopId) {
		Map<String, Object> params = new HashMap<>();
		params.put("putUserId", shopId);
		params.put("status", 0);
		//查询未入库的出库单
		List<StorageOutPutInfo> storageOutPutInfos = inventoryDao.getStorageOutPutInfos(params);
		//判断是否有未入库的单据
		if (storageOutPutInfos != null && storageOutPutInfos.size() > 0) {
			throw new BusinessException("单据" + storageOutPutInfos.get(0).getOrderId() + "未入库,请到入库模块入库!");
		}
	}
	/**  
	 * @Title: bindProcess
	 * @author : Lwf
	 * @param shopId 
	 * @param count 
	 * @param orderId 
	 * @Description: TODO(描述)
	 * @DATE :2020年5月22日 
	 * @return :void 
	 * @throws UnsupportedEncodingException 
	 */  
	private void bindProcess(double count, String shopId, String orderId) throws UnsupportedEncodingException {
		List<InventoryReviewProcess> list = new ArrayList<InventoryReviewProcess>();
		//判断金额,决定走那个流程
		if (count == 0 || count <= 500) {
			processFirst(list,shopId,orderId);
		}else if (count > 500 && count <= 1000) {
			processTwo(list,shopId,orderId);
		}else if (count > 1000) {
			processThree(list,shopId,orderId);
		}else {
			throw new BusinessException("差异金额为" + count + ",盘点失败!");
		}
		if (list.size() > 0) {
			inventoryDao.addInventoryReviewProcess(list);
			InventoryReviewProcess inventoryReviewProcess = list.get(0);
			//更新为正在审核中
			inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
				put("isCheck", 0);
				put("isCurrentCheck", 1);
				put("orderId", inventoryReviewProcess.getInventoryOrderId());
				put("checkerId", inventoryReviewProcess.getCheckerId());
			}});
			Employee employee = employeeService.getEmployeeInfoById(inventoryReviewProcess.getCheckerId());
			if (employee != null && StringUtils.isNotBlank(employee.getPhone())) {
				HashMap<String, Object> parma = new HashMap<>();
				parma.put("#name#", employee.getName());
				parma.put("#orderId#", orderId);
				MessageUtilsYunPian.tplSingleSend(employee.getPhone(), "3756254", MessageUtilsYunPian.urlencode(parma));
			}
		}
	}
	
	/**  
	 * @Title: processThree
	 * @author : Lwf
	 * @Description: TODO(描述)
	 * @DATE :2020年5月22日 
	 * @return :void 
	 * @param list
	 * @param shopId
	 * @param orderId
	 */  
	private void processThree(List<InventoryReviewProcess> list, String shopId, String orderId) {
		//店长-->经理-->总监
		processTwo(list,shopId,orderId);
		//查询运营结构
		String geParentidById = employeeService.geParentidById(shopId).getParentid();
		DepartmentChief departmentChief = employeeService.getIdByChiefId(geParentidById);
		if (!departmentChief.getParentid().equals("chief")) {
			//运营结构顶级
			departmentChief = employeeService.getIdByChiefId(departmentChief.getParentid());
			geParentidById = departmentChief.getId();
			Employee general = employeeService.getEmployeeInfoById(geParentidById);
			//需要总监审核
			InventoryReviewProcess inventoryReviewProcess = new InventoryReviewProcess();
			inventoryReviewProcess.setCheckerId(general == null ? "9569fbc7-bb75-4db5-83a5-398f7d96d746" : general.getId());
			inventoryReviewProcess.setCheckerName(general == null ? "管理员" : general.getName());
			inventoryReviewProcess.setInventoryOrderId(orderId);
			inventoryReviewProcess.setIsCheck(0);
			inventoryReviewProcess.setSort(1);
			if (!hasExit(list,inventoryReviewProcess)) {
				list.add(inventoryReviewProcess);
			}
		}
	}
	/**  
	 * @Title: processTwo
	 * @author : Lwf
	 * @param orderId 
	 * @param shopId 
	 * @param list 
	 * @Description: TODO(描述)
	 * @DATE :2020年5月22日 
	 * @return :void 
	 */  
	private void processTwo(List<InventoryReviewProcess> list, String shopId, String orderId) {
		//店长-->经理
		processFirst(list, shopId, orderId);
		//查询运营结构
		String geParentidById = employeeService.geParentidById(shopId).getParentid();
		geParentidById = employeeService.getIdByChiefId(geParentidById).getId();
		Employee general = employeeService.getEmployeeInfoById(geParentidById);
		//需要经理审核
		InventoryReviewProcess inventoryReviewProcess = new InventoryReviewProcess();
		inventoryReviewProcess.setCheckerId(general == null ? "9569fbc7-bb75-4db5-83a5-398f7d96d746" : general.getId());
		inventoryReviewProcess.setCheckerName(general == null ? "管理员" : general.getName());
		inventoryReviewProcess.setInventoryOrderId(orderId);
		inventoryReviewProcess.setIsCheck(0);
		inventoryReviewProcess.setSort(1);
		if (!hasExit(list,inventoryReviewProcess)) {
			list.add(inventoryReviewProcess);
		}
	}
	/**  
	 * @Title: processFirst
	 * @author : Lwf
	 * @param orderId 
	 * @param shopId 
	 * @param list 
	 * @Description: TODO(描述)
	 * @DATE :2020年5月22日 
	 * @return :void 
	 */  
	private void processFirst(List<InventoryReviewProcess> list, String shopId, String orderId) {
		//通过部门查询负责人id
		String chiefIdById = employeeService.getChiefIdById(shopId);
		//查询门店的店长
		Employee general = employeeService.getEmployeeInfoById(chiefIdById);
		if (general == null) {
			//查询运营结构
			String geParentidById = employeeService.geParentidById(shopId).getParentid();
			geParentidById = employeeService.getIdByChiefId(geParentidById).getId();
			general = employeeService.getEmployeeInfoById(geParentidById);
		}
		//只需要店长审核
		InventoryReviewProcess inventoryReviewProcess = new InventoryReviewProcess();
		inventoryReviewProcess.setCheckerId(general == null ? "9569fbc7-bb75-4db5-83a5-398f7d96d746" : general.getId());
		inventoryReviewProcess.setCheckerName(general == null ? "管理员" : general.getName());
		inventoryReviewProcess.setInventoryOrderId(orderId);
		inventoryReviewProcess.setIsCheck(0);
		inventoryReviewProcess.setSort(0);
		if (!hasExit(list,inventoryReviewProcess)) {
			list.add(inventoryReviewProcess);
		}
	}
	/**  
	 * @Title: hasExit
	 * @author : Lwf
	 * @Description: TODO(判断是否已存在审核人)
	 * @DATE :2020年5月22日 
	 * @return :boolean 
	 * @param list
	 * @param inventoryReviewProcess
	 */  
	private boolean hasExit(List<InventoryReviewProcess> list, InventoryReviewProcess inventoryReviewProcess) {
		for (InventoryReviewProcess item : list) {
			if (item.getCheckerId().equals(inventoryReviewProcess.getCheckerId())) {
				return true;
			}
		}
		return false;
	}
	/**  
	 * @Title: setMaterialInventoryDetail
	 * @author : Lwf
	 * @param materialInventoryDetail 
	 * @Description: TODO(描述)
	 * @DATE :2020年5月21日 
	 * @return :void 
	 * @param materialInventoryOrder
	 * @param materialInfo
	 */  
	private void setMaterialInventoryDetail(MaterialInventoryDetail materialInventoryDetail, MaterialInventoryOrder materialInventoryOrder, MaterialInfo materialInfo) {
		materialInventoryDetail.setOrderId(materialInventoryOrder.getOrderNo());
		materialInventoryDetail.setMaterialNo(materialInfo.getNo());
		materialInventoryDetail.setMaterialName(materialInfo.getName());
		materialInventoryDetail.setMaterialTax(materialInfo.getTax());
		materialInventoryDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
		materialInventoryDetail.setMaterialBigUnit(materialInfo.getBigUnit());
		materialInventoryDetail.setMaterialNumber(materialInfo.getNumber());
		materialInventoryDetail.setMaterialForm(materialInfo.getForm());
	}
	/**
	 * 获取物料盘点单基础信息通过orderid
	 * @Title: getMaterialInventoryInfoByOrderId 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:52:13 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public MaterialInventoryInfo getMaterialInventoryInfoByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialInventoryInfoByOrderId(orderId);
	}

	/**
	 * 获取物料盘点单物料清单详情单据id
	 * @Title: getMaterialInventoryDetailByOrderId 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:52:45 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public List<MaterialInventoryDetail>  getMaterialInventoryDetailByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialInventoryDetailByOrderId(orderId);
	}

	/**
	 * 获取物料盘点单通过订单id
	 * @Title: getMaterialInventoryOrderByOrderId 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:53:16 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public MaterialInventoryOrder getMaterialInventoryOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		MaterialInventoryOrder materialInventoryOrder = new MaterialInventoryOrder(); 
		MaterialInventoryInfo materialInventoryInfo = inventoryDao.getMaterialInventoryInfoByOrderId(orderId);
		List<MaterialInventoryDetail> materialInventoryDetails = inventoryDao.getMaterialInventoryDetailByOrderId(orderId);
		for (MaterialInventoryDetail item : materialInventoryDetails) {
			Double sub = item.getStorageUsableTo() - item.getStorageUsableFrom();
			//盘点单位是大单位
			if (item.getInventoryUnit().equals(item.getMaterialBigUnit())) {
				if (item.getMaterialNumber() == 0) {
					throw new BusinessException("【物料】" + item.getMaterialName() + "的转化数量不能为0!");
				}
				//差异
				sub = sub / item.getMaterialNumber();
				item.setDifference(sub);
			}else {
				item.setDifference(sub);
			}
		}
		materialInventoryOrder.setOrderId(materialInventoryInfo.getOrderId());
		materialInventoryOrder.setOrderNo(materialInventoryInfo.getOrderId());
		materialInventoryOrder.setCreateDateTime(materialInventoryInfo.getCreateDateTime());
		materialInventoryOrder.setCreateUserId(materialInventoryInfo.getCreateUserId());
		materialInventoryOrder.setCreateUserName(materialInventoryInfo.getCreateUserName());
		materialInventoryOrder.setRealDate(materialInventoryInfo.getRealDate());
		materialInventoryOrder.setShopId(materialInventoryInfo.getShopId());
		materialInventoryOrder.setShopName(materialInventoryInfo.getShopName());
		materialInventoryOrder.setInventoryUserId(materialInventoryInfo.getInventoryUserId());
		materialInventoryOrder.setInventoryUserName(materialInventoryInfo.getInventoryUserName());
		materialInventoryOrder.setDetails(materialInventoryDetails);
		materialInventoryOrder.setRemark(materialInventoryInfo.getRemark());
		return materialInventoryOrder;
	}

	
	
	
	
	
	
	/**
	 * 获取物料出入库单据列表
	 * @Title: getStorageOutPutInfos 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:53:38 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public List<StorageOutPutInfo> getStorageOutPutInfos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getStorageOutPutInfos(params);
	}

	@Override
	public Integer getStorageOutPutInfosCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getStorageOutPutInfosCount(params);
	}

	/**
	 * 新增出入库单据
	 * @Title: addStorageOutPutOrder 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 3:37:52 PM
	 * @Description: TODO(描述)
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean addStorageOutPutOrder(StorageOutPutOrder storageOutPutOrder) {
		// TODO Auto-generated method stub
		Boolean flag = true;
		String msg = "新增出入库单失败！";
		//基础单据
		flag = 1 == orderDao.addOrderBase(storageOutPutOrder);
		storageOutPutOrder.setOrderId(storageOutPutOrder.getOrderNo());
		//基础信息
		storageOutPutOrder.setOutUserId(storageOutPutOrder.getShopId());
		storageOutPutOrder.setOutTime(storageOutPutOrder.getRealDate());
		if(flag) flag = 1 == inventoryDao.addStorageOutPutInfo(storageOutPutOrder);
		if(flag){
			for(StorageOutPutDetail storageOutPutDetail : storageOutPutOrder.getDetails())
			{
				if(flag){
					//查询物料的库存
					MaterialInfo materialInfo = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
						put("shopId", storageOutPutOrder.getShopId());
						put("materialId", storageOutPutDetail.getMaterialId());
					}});
					
					if(materialInfo == null){
						flag = false;
						msg = "本门店有物料没有入库无法进行出库操作！";
						break;
					}else{
				    	//出入库物料详情信息
						storageOutPutDetail.setPutUnit(storageOutPutDetail.getOutUnit());
						storageOutPutDetail.setOrderId(storageOutPutOrder.getOrderId());
						storageOutPutDetail.setMaterialNo(materialInfo.getNo());
						storageOutPutDetail.setMaterialName(materialInfo.getName());
						storageOutPutDetail.setMaterialForm(materialInfo.getForm());
						storageOutPutDetail.setMaterialBigUnit(materialInfo.getBigUnit());
						storageOutPutDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
						storageOutPutDetail.setMaterialNumber(materialInfo.getNumber());
						storageOutPutDetail.setMaterialPrice(materialInfo.getPrice());
						storageOutPutDetail.setMaterialTax(materialInfo.getTax());
						storageOutPutDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
						storageOutPutDetail.setMaterialDeliverUnit(materialInfo.getDeliverUnit());
						
						
						flag = 1 == inventoryDao.addStorageOutPutDetail(storageOutPutDetail);
						
				    	if(flag){
				    		//单位转换
							Double smallUnitOutNumber = convertToSmallUnit(storageOutPutDetail.getOutUnit(),storageOutPutDetail.getOutNumber(), storageOutPutDetail);
				    		if(materialInfo.getUsableInventory() - smallUnitOutNumber < 0){
						    	flag = false;
						    	msg = "物料" + materialInfo.getName() + "您所在的门店库存不足，不足以出库！";
						    	break;
						    }else{
						    	//库存异动日志
								MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
								materialInventoryChange.setShopId(storageOutPutOrder.getShopId());
								materialInventoryChange.setMaterialId(storageOutPutDetail.getMaterialId());
								materialInventoryChange.setChangeDate(storageOutPutOrder.getRealDate());
								materialInventoryChange.setCreateDateTime(new Date());
								materialInventoryChange.setCreateUserId(storageOutPutOrder.getCreateUserId());
								materialInventoryChange.setOrderId(storageOutPutOrder.getOrderId());
								materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
								materialInventoryChange.setFrom(materialInfo.getUsableInventory());
						    	materialInventoryChange.setTo(materialInfo.getUsableInventory() - smallUnitOutNumber);
								materialInventoryChange.setRemark("出库");
								materialInventoryChange.setStatus(Status.COMMON);
								flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
								if(flag){
									//变动库存
									MaterialInventory materialInventory = new MaterialInventory();
									materialInventory.setShopId(storageOutPutOrder.getShopId());
									materialInventory.setMaterialId(storageOutPutDetail.getMaterialId());
									materialInventory.setUsableInventory(- smallUnitOutNumber);
									flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory);
								} else {
									msg = "新增库存异动日志失败";
									break;
								}
						    }
				    	}else{
				    		msg = "新增出入库详情失败！";
				    		break;
				    	}
				    
					}
					
				}else{
					break;
				}
			}
		}
		if(!flag) throw new BusinessException(msg);//数据回滚
		return flag;
	}
    
	/**
	 * 获取出入库库存单据通过单据id
	 * @Title: getStorageOutPutOrderByOrderId 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:53:50 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public StorageOutPutOrder getStorageOutPutOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		StorageOutPutInfo storageOutPutInfo = inventoryDao.getStorageOutPutInfoByOrderId(orderId);
		List<StorageOutPutDetail> storageOutPutDetails = inventoryDao.getStorageOutPutDetailByOrderId(orderId);
		StorageOutPutOrder storageOutPutOrder = new StorageOutPutOrder();
		storageOutPutOrder.setStatus(storageOutPutInfo.getStatus());
		storageOutPutOrder.setRemark(storageOutPutInfo.getRemark());
		storageOutPutOrder.setOrderId(storageOutPutInfo.getOrderId());
		storageOutPutOrder.setCreateUserId(storageOutPutInfo.getCreateUserId());
		storageOutPutOrder.setCreateUserName(storageOutPutInfo.getCreateUserName());
		storageOutPutOrder.setShopId(storageOutPutInfo.getShopId());
		storageOutPutOrder.setShopName(storageOutPutInfo.getShopName());
		storageOutPutOrder.setCreateDateTime(storageOutPutInfo.getCreateDateTime());
		storageOutPutOrder.setRealDate(storageOutPutInfo.getRealDate());
		storageOutPutOrder.setOrderNo(storageOutPutInfo.getOrderNo());
		storageOutPutOrder.setOutRemark(storageOutPutInfo.getOutRemark());
		storageOutPutOrder.setPutRemark(storageOutPutInfo.getPutRemark());
		storageOutPutOrder.setOutUserId(storageOutPutInfo.getOutUserId());
		storageOutPutOrder.setPutUserId(storageOutPutInfo.getPutUserId());
		storageOutPutOrder.setOutUserName(storageOutPutInfo.getOutUserName());
		storageOutPutOrder.setPutUserName(storageOutPutInfo.getPutUserName());
		storageOutPutOrder.setOutTime(storageOutPutInfo.getOutTime());
		storageOutPutOrder.setPutTime(storageOutPutInfo.getPutTime());
		for(StorageOutPutDetail storageOutPutDetail : storageOutPutDetails){
			Object putRemak = SelectItemUtils.getMaterialPutReasonByKey(storageOutPutDetail.getPutRemark());
			storageOutPutDetail.setPutRemark(putRemak == null ? storageOutPutDetail.getPutRemark() : putRemak.toString());
		}
		storageOutPutOrder.setDetails(storageOutPutDetails);
		return storageOutPutOrder;
	}

	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean putStorageOutPutOrder(StorageOutPutOrder storageOutPutOrder) {
		// TODO Auto-generated method stub
		Boolean flag = true;
		String msg = "入库操作失败！";
		int orderStatus = inventoryDao.getStorageOutPutInfoByOrderId(storageOutPutOrder.getOrderId()).getStatus().intValue();
		if(orderStatus == 1 || orderStatus == 2){
			flag = false;
			msg ="已处理的入库单请勿重复操作，请刷新界面！";
		} 
		if(flag){
			//更新单据的状态
			flag = 1 == orderDao.updateOrderBase(storageOutPutOrder);
		}
		
		if(flag){
			//更新单据出入库基本信息
			flag = 1 == inventoryDao.updateStorageOutPutInfo(storageOutPutOrder); 
			//获取要出入库的门店
			StorageOutPutInfo storageOutPutInfo = inventoryDao.getStorageOutPutInfoByOrderId(storageOutPutOrder.getOrderId());
			List<StorageOutPutDetail> storageOutPutDetails = inventoryDao.getStorageOutPutDetailByOrderId(storageOutPutOrder.getOrderId());
			//1拒绝入库2入库
			if(storageOutPutOrder.getStatus().equals(1)){
				for(StorageOutPutDetail storageOutPutDetail : storageOutPutDetails){
					//出库门店所有出库物料均回退
					//获取物料在入库门店的库存信息
					MaterialInfo materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
						put("materialId", storageOutPutDetail.getMaterialId());
						put("shopId", storageOutPutInfo.getPutUserId());
					}});
					Double convertOut = 0.0; 
					if(storageOutPutDetail.getMaterialSmallUnit().equals(storageOutPutDetail.getOutUnit())){
						convertOut =  storageOutPutDetail.getOutNumber();
		            } else{
		            	convertOut = storageOutPutDetail.getOutNumber() * storageOutPutDetail.getMaterialNumber();
		            }
					MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
					materialInventoryChange.setShopId(storageOutPutInfo.getOutUserId());
					materialInventoryChange.setMaterialId(storageOutPutDetail.getMaterialId());
		            materialInventoryChange.setOrderId(storageOutPutDetail.getOrderId());
		            materialInventoryChange.setCreateDateTime(new Date());
		            materialInventoryChange.setChangeDate(storageOutPutOrder.getPutTime());
		            materialInventoryChange.setCreateUserId(storageOutPutOrder.getCreateUserId());
		            materialInventoryChange.setFrom(materialInventory.getUsableInventory());
		            materialInventoryChange.setTo(materialInventory.getUsableInventory() + convertOut);
		            materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
		            materialInventoryChange.setRemark("入库门店拒绝入库出库门店库存回库");
		            materialInventoryChange.setStatus(Status.COMMON);
		            //库存变动日志
		        	flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
		        	if(flag){
		        		//更新门店物料的库存
		        		MaterialInventory materialInventory2 = new MaterialInventory();
		        		materialInventory2.setMaterialId(storageOutPutDetail.getMaterialId());
		        		materialInventory2.setShopId(storageOutPutInfo.getOutUserId());
		        		materialInventory2.setUsableInventory(convertOut);
		        		flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory2);
		        		if(flag){
		        			//更新物料入库单物料清单详情
		        			inventoryDao.updateStorageOutPutDetail(storageOutPutDetail);
		        		}
		        	}
					
				}
			}else if(storageOutPutOrder.getStatus().equals(2)){//入库
				
				if(storageOutPutDetails.size() != storageOutPutOrder.getDetails().size()){
					flag = false;
					msg = "数据异常";
				}else{
					for(StorageOutPutDetail storageOutPutDetail: storageOutPutOrder.getDetails())
				    {
						if(SelectItemUtils.getMaterialPutReasonByKey(storageOutPutDetail.getPutRemark()) == null){
							flag = false;
							msg = "未知入库原因";
							break;
						}else{
							flag = false;
							StorageOutPutDetail detail = null;
							for(StorageOutPutDetail currentDetail : storageOutPutDetails){
								if(currentDetail.getId().equals(storageOutPutDetail.getId())){
									detail = currentDetail;
									break;
								}
							};
							//获取物料在入库门店的库存信息
							Map<String, Object> param = new HashMap<String,Object>();
							param.put("materialId", detail.getMaterialId());
							param.put("shopId", storageOutPutInfo.getPutUserId());
							MaterialInfo materialInventoryPut = materialDao.getMaterialDetailByShopIdAndMaterialId(param);
							
                            if(materialInventoryPut == null){
                            	MaterialInventory intMaterialInventory = new MaterialInventory();
                            	intMaterialInventory.setInventoryId(UUID.randomUUID().toString());
                            	intMaterialInventory.setShopId(storageOutPutInfo.getPutUserId());
                            	intMaterialInventory.setMaterialId(detail.getMaterialId());
                            	intMaterialInventory.setUsableInventory(new Double(0));
                            	intMaterialInventory.setCheckInventory(new Double(0));
                            	intMaterialInventory.setAlertInventory(new Double(0));
                            	intMaterialInventory.setCheckInventory(new Double(0));
            					flag = 1 == materialDao.addMaterialInventory(intMaterialInventory);
            					materialInventoryPut = materialDao.getMaterialDetailByShopIdAndMaterialId(param);
                            }
							
							
							if(materialInventoryPut != null){
								Double convertForPut = 0.0;//入库门店
								Double convertForOut = 0.0;//出库门店
								String inventoryChangeRemarkForOut = "";//入库门店库存变化备注
								String inventoryChangeRemarkForPut = "";//出库门店库存变化备注
								if(detail != null){
									Double outNumber = detail.getOutNumber();
									Double putNumber = storageOutPutDetail.getPutNumber();
									
						            if(detail.getMaterialSmallUnit().equals(detail.getPutUnit())){
						            	convertForPut =  putNumber;
						            } else{
						            	convertForPut = putNumber * detail.getMaterialNumber();
						            }
						            if(detail.getMaterialSmallUnit().equals(detail.getOutUnit())){
						            	convertForOut =  outNumber;
						            } else{
						            	convertForOut = outNumber * detail.getMaterialNumber();
						            }
									switch (storageOutPutDetail.getPutRemark()) {
									case "0"://正常单据
										//出入库一致
										if(outNumber.equals(putNumber)){
											flag = true;
											inventoryChangeRemarkForPut = "门店正常入库";
											convertForOut = 0.0;//出库门店无需变动库存
										}else{
											msg = "为正常出入库数量应该一致！请检查！";
										}
										
										break;
									case "1"://发错货
										flag = true;
										convertForOut = convertForOut - convertForPut;//出货门店发错货入库门店没入库则回退至出库门店库存；
										inventoryChangeRemarkForPut = "出库门店发错货入库门店仍入库" + convertForPut + detail.getMaterialSmallUnit();
										inventoryChangeRemarkForOut = "出库门店发错货出库门店要回库" + convertForOut + detail.getMaterialSmallUnit();
										break;
									case "2"://发少货
										if(outNumber > putNumber){
											flag = true;
											convertForOut = convertForOut - convertForPut;//出货门店发少货入库门店；
											inventoryChangeRemarkForPut = "出库门店发少货入库门店实际入库" + convertForPut + detail.getMaterialSmallUnit();
											inventoryChangeRemarkForOut = "出库门店发少货出库门店实际出库回库" + convertForOut + detail.getMaterialSmallUnit();
										} else {
											msg = detail.getMaterialName() + "入库差异原因选择错误请检查！";
										}
										break;
									case "3"://发多货
										if(outNumber < putNumber){
											flag = true;
											convertForOut = convertForOut - convertForPut;//出货门店发少货入库门店；
											inventoryChangeRemarkForPut = "出库门店发多货入库门店实际入库" + convertForPut + detail.getMaterialSmallUnit();
											inventoryChangeRemarkForOut = "出库门店发多货出库门店实际出库回库" + convertForOut + detail.getMaterialSmallUnit();
										}else{
											msg = detail.getMaterialName() + "入库差异原因选择错误请检查！";
										}
										break;
									case "4"://中途损耗
										if(outNumber > putNumber){
										    flag = true;
											inventoryChangeRemarkForPut = "出库门店中途损耗入库门店实际入库" + convertForPut + detail.getMaterialSmallUnit() + "库存丢失" + (convertForOut - convertForPut);
										    convertForOut = 0.0;
										}else{
											msg = detail.getMaterialName() + "入库差异原因选择错误请检查！";
										}
										break;
									case "5"://货物损坏
										if(outNumber > putNumber){
										    flag = true;
										    inventoryChangeRemarkForPut = "出库门店货物损坏入库门店实际入库" + convertForPut + detail.getMaterialSmallUnit() + "库存丢失" + (convertForOut - convertForPut);
										    convertForOut = 0.0;
										}else{
											msg = detail.getMaterialName() + "入库差异原因选择错误请检查！";
										}
										break;
									default:
										msg = "未知的入库原因！";
										break;
									}
								}
								if(flag){
									//门店出库
									if(convertForOut != 0){
										Map<String,Object> materialInventoryOutParam = new HashMap<String,Object>();
										materialInventoryOutParam.put("materialId", detail.getMaterialId());
										materialInventoryOutParam.put("shopId", storageOutPutInfo.getOutUserId());
										MaterialInfo materialInventoryOut = materialDao.getMaterialDetailByShopIdAndMaterialId(materialInventoryOutParam);
										
										MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
										materialInventoryChange.setShopId(storageOutPutInfo.getOutUserId());
										materialInventoryChange.setMaterialId(detail.getMaterialId());
							            materialInventoryChange.setOrderId(detail.getOrderId());
							            materialInventoryChange.setCreateDateTime(new Date());
							            materialInventoryChange.setChangeDate(storageOutPutOrder.getPutTime());
							            materialInventoryChange.setCreateUserId(storageOutPutOrder.getCreateUserId());
							            materialInventoryChange.setFrom(materialInventoryOut.getUsableInventory());
							            materialInventoryChange.setTo(materialInventoryOut.getUsableInventory() + convertForOut);
							            materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
							            materialInventoryChange.setRemark("回仓");
							            materialInventoryChange.setStatus(Status.COMMON);
							            //库存变动日志
							        	flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
							        	if(flag){
							        		//更新门店物料的库存
							        		MaterialInventory materialInventory2 = new MaterialInventory();
							        		materialInventory2.setMaterialId(detail.getMaterialId());
							        		materialInventory2.setShopId(storageOutPutInfo.getOutUserId());
							        		materialInventory2.setUsableInventory(convertForOut);
							        		flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory2);
							        		
							        		if(flag){
							        			//更新物料入库单物料清单详情
							        			inventoryDao.updateStorageOutPutDetail(storageOutPutDetail);
							        		}
							        	}
									}
									//门店入库
									//库存异动日志
									if(convertForPut > 0){
										MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
										materialInventoryChange.setShopId(storageOutPutInfo.getPutUserId());
										materialInventoryChange.setMaterialId(detail.getMaterialId());
							            materialInventoryChange.setOrderId(detail.getOrderId());
							            materialInventoryChange.setCreateDateTime(new Date());
							            materialInventoryChange.setChangeDate(storageOutPutOrder.getPutTime());
							            materialInventoryChange.setCreateUserId(storageOutPutOrder.getCreateUserId());
							            materialInventoryChange.setFrom(materialInventoryPut.getUsableInventory());
							            materialInventoryChange.setTo(materialInventoryPut.getUsableInventory() + convertForPut);
							            materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
							            materialInventoryChange.setRemark(inventoryChangeRemarkForPut);
							            materialInventoryChange.setStatus(Status.COMMON);
							            //库存变动日志
							        	flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
							        	if(flag){
							        		//更新门店物料的库存
							        		MaterialInventory materialInventory2 = new MaterialInventory();
							        		materialInventory2.setMaterialId(detail.getMaterialId());
							        		materialInventory2.setShopId(storageOutPutInfo.getPutUserId());
							        		materialInventory2.setUsableInventory(convertForPut);
							        		flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory2);
							        		
							        		if(flag){
							        			//更新物料入库单物料清单详情
							        			inventoryDao.updateStorageOutPutDetail(storageOutPutDetail);
							        		}
							        	}
									}
								}
							}else{
								flag = false;
								msg = "入库门店有物料未有过入库操作，请盘点初始化。";
								break;
							}
							
							
						}
				    	if(flag) flag = 1 == inventoryDao.updateStorageOutPutDetail(storageOutPutDetail);
				    	if(!flag) break;
				    }
				}
			}
			
			
			
		}
	    if(!flag) throw new BusinessException(msg);
		return flag;
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean updateStorageOutPutOrder(StorageOutPutOrder storageOutPutOrder) {
		// TODO Auto-generated method stub
	    Boolean flag = true;
	    String msg = "保存失败";
	    //回退 并更新物料出入库单基础信息
	    orderDao.updateOrderBase(storageOutPutOrder);
		inventoryDao.updateStorageOutPutInfo(storageOutPutOrder);  
		//添加库存变动日志且修正库存
		StorageOutPutInfo oldStorageOutPutInfo = inventoryDao.getStorageOutPutInfoByOrderId(storageOutPutOrder.getOrderId());
		List<StorageOutPutDetail> oldStorageOutPutDetails = inventoryDao.getStorageOutPutDetailByOrderId(storageOutPutOrder.getOrderId());
		for(StorageOutPutDetail oldstorageOutPutDetail : oldStorageOutPutDetails){
			MaterialInfo materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
				put("materialId", oldstorageOutPutDetail.getMaterialId());
				put("shopId", oldStorageOutPutInfo.getOutUserId());
			}});
			if(materialInventory != null){
				Double convert = 0.0;
				convert = convertToSmallUnit(oldstorageOutPutDetail.getOutUnit(), oldstorageOutPutDetail.getOutNumber(), oldstorageOutPutDetail);
				MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
				materialInventoryChange.setShopId(oldStorageOutPutInfo.getOutUserId());
				materialInventoryChange.setMaterialId(oldstorageOutPutDetail.getMaterialId());
	            materialInventoryChange.setOrderId(oldStorageOutPutInfo.getOrderId());
	            materialInventoryChange.setCreateDateTime(new Date());
	            materialInventoryChange.setChangeDate(oldStorageOutPutInfo.getRealDate());
	            materialInventoryChange.setCreateUserId(storageOutPutOrder.getCreateUserId());
	            materialInventoryChange.setFrom(materialInventory.getUsableInventory());
	            materialInventoryChange.setTo(materialInventory.getUsableInventory() + convert);
	            materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
	            materialInventoryChange.setRemark("修改出入库单据入库");
	            materialInventoryChange.setStatus(Status.COMMON);
	            //库存变动日志
	        	flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
	        	if(flag){
	        		//库存变动  原来出库的先加上
	        		MaterialInventory  newMaterialInventory = new MaterialInventory();
	        		newMaterialInventory.setShopId(oldStorageOutPutInfo.getOutUserId());
        			newMaterialInventory.setMaterialId(oldstorageOutPutDetail.getMaterialId());
        			newMaterialInventory.setUsableInventory(convert);
	        		flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(newMaterialInventory);
	        		if(!flag) break;
	        	}else{break;}
			}else{
				flag = false;
				msg = "发现未盘点入库物料无法进行出库操作";
				break;
			}
        	
        }
		
		if(flag){
			flag = 1 <= inventoryDao.deleteStorageOutPutDetialByOrderId(storageOutPutOrder.getOrderId());
			if(flag){
				//循环添加
				for(StorageOutPutDetail storageOutPutDetail: storageOutPutOrder.getDetails())
			    {
					//查询物料的库存
					MaterialInfo materialInfo = materialDao.getMaterialDetailByShopIdAndMaterialId(new HashMap<String,Object>(){{
						put("shopId", oldStorageOutPutInfo.getShopId());
						put("materialId", storageOutPutDetail.getMaterialId());
					}});
					
					if(materialInfo == null){
						flag = false;
						msg = "本门店有物料没有入库无法进行出库操作！";
						break;
					}else{
				    	//出入库物料详情信息
						storageOutPutDetail.setOrderId(oldStorageOutPutInfo.getOrderId());
						storageOutPutDetail.setMaterialNo(materialInfo.getNo());
						storageOutPutDetail.setMaterialName(materialInfo.getName());
						storageOutPutDetail.setMaterialForm(materialInfo.getForm());
						storageOutPutDetail.setMaterialBigUnit(materialInfo.getBigUnit());
						storageOutPutDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
						storageOutPutDetail.setMaterialNumber(materialInfo.getNumber());
						storageOutPutDetail.setMaterialPrice(materialInfo.getPrice());
						storageOutPutDetail.setMaterialTax(materialInfo.getTax());
						storageOutPutDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
						storageOutPutDetail.setMaterialDeliverUnit(materialInfo.getDeliverUnit());
						flag = 1 == inventoryDao.addStorageOutPutDetail(storageOutPutDetail);
						
				    	if(flag){
				    		//单位转换
							Double smallUnitOutNumber = convertToSmallUnit(storageOutPutDetail.getOutUnit(),storageOutPutDetail.getOutNumber(), storageOutPutDetail);
				    		if(materialInfo.getUsableInventory() - smallUnitOutNumber < 0){
						    	flag = false;
						    	msg = "您所在的门店库存不足，不足以出库！";
						    	break;
						    }else{
						    	//库存异动日志
								MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
								materialInventoryChange.setShopId(oldStorageOutPutInfo.getShopId());
								materialInventoryChange.setMaterialId(storageOutPutDetail.getMaterialId());
								materialInventoryChange.setChangeDate(oldStorageOutPutInfo.getRealDate());
								materialInventoryChange.setCreateDateTime(new Date());
								materialInventoryChange.setCreateUserId(oldStorageOutPutInfo.getCreateUserId());
								materialInventoryChange.setOrderId(oldStorageOutPutInfo.getOrderId());
								materialInventoryChange.setOrderType(OrderType.MATERIAL_TRANSFER);
								materialInventoryChange.setFrom(materialInfo.getUsableInventory());
						    	materialInventoryChange.setTo(materialInfo.getUsableInventory() - smallUnitOutNumber);
								materialInventoryChange.setRemark("修改出入库单据出库");
								materialInventoryChange.setStatus(Status.COMMON);
								flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
								if(flag){
									//变动库存
									MaterialInventory materialInventory = new MaterialInventory();
									materialInventory.setShopId(oldStorageOutPutInfo.getShopId());
									materialInventory.setMaterialId(storageOutPutDetail.getMaterialId());
									materialInventory.setUsableInventory(- smallUnitOutNumber);
									flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory);
								} else {
									msg = "新增库存异动日志失败";
									break;
								}
						    }
				    	}else{
				    		msg = "新增出入库详情失败！";
				    		break;
				    	}
				    
					}
			    }
			}
			
		}
	    if(!flag) throw new BusinessException(msg);
		return flag;
	}
	
    
	
	
	
	/**
	 * 获取物料采购入库单列表
	 * @Title: getMaterialPurchaseInfos 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:55:36 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public List<MaterialPurchaseInfo> getMaterialPurchaseInfos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialPurchaseInfos(params);
	}

	@Override
	public Integer getMaterialPurchaseInfosCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialPurchaseInfosCount(params);
	}

	/**
	 * 新增物料采购单
	 * @Title: addMaterialPurchaseOrder 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 3:39:03 PM
	 * @Description: TODO(描述)
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean addMaterialPurchaseOrder(MaterialPurchaseOrder materialPurchaseOrder) {
		// TODO Auto-generated method stub
		Boolean flag = true;
		String msg = "新增物料采购入库单失败！";
		//添加基础
		flag = 1 == orderDao.addOrderBase(materialPurchaseOrder);
		//添加单据基础数据
		materialPurchaseOrder.setApplyTime(materialPurchaseOrder.getRealDate());//申请时间
		if(flag) flag = 1 == inventoryDao.addMaterialPurchaseInfo(materialPurchaseOrder);
		//添加单据物料清单
		if(flag) {
			for (MaterialPurchaseDetail materialPurchaseDetail : materialPurchaseOrder.getDetails()){
				//预留
				/*materialPurchaseDetail.setPurchaseNumber(materialPurchaseDetail.getPurchaseNumber());
				materialPurchaseDetail.setAuditNumber(materialPurchaseDetail.getApplyNumber());*/
				
				MaterialInfo materialInfo = materialDao.getMaterialById(materialPurchaseDetail.getMaterialId());
				materialPurchaseDetail.setOrderId(materialPurchaseOrder.getOrderId());
				materialPurchaseDetail.setMaterialNo(materialInfo.getNo());
				materialPurchaseDetail.setMaterialName(materialInfo.getName());
				materialPurchaseDetail.setMaterialForm(materialInfo.getForm());
				materialPurchaseDetail.setMaterialPrice(materialInfo.getPrice());
				materialPurchaseDetail.setMaterialTax(materialInfo.getTax());
				materialPurchaseDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
				materialPurchaseDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
				materialPurchaseDetail.setMaterialBigUnit(materialInfo.getBigUnit());
				materialPurchaseDetail.setMaterialNumber(materialInfo.getNumber());
				flag = 1 == inventoryDao.addMaterialPurchaseDetail(materialPurchaseDetail);
				if(!flag) break;
			}
		}
			
		if(!flag) throw new BusinessException(msg);
		return flag;
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	@Override
	public Boolean updateMaterialPurchaseOrder(MaterialPurchaseOrder materialPurchaseOrder){
		// TODO Auto-generated method stub
		Boolean flag = true;
		String msg = "更新物料采购入库单失败！可能单据信息已改变，请刷新界面重试！";
		MaterialPurchaseInfo oldMaterialPurchaseInfo = inventoryDao.getMaterialPurchaseInfoByOrderId(materialPurchaseOrder.getOrderId());
		//更新基础信息
		orderDao.updateOrderBase(materialPurchaseOrder);
		inventoryDao.updateMaterialPurchaseInfo(materialPurchaseOrder);
		//获取基础信息
		MaterialPurchaseInfo materialPurchaseInfo = inventoryDao.getMaterialPurchaseInfoByOrderId(materialPurchaseOrder.getOrderId());
		//以后会修正  暂时简单处理不可修改
		if(oldMaterialPurchaseInfo.getStatus().intValue() == 0 && materialPurchaseOrder.getStatus() == null){//更新单据
			
			flag = 0 < inventoryDao.deleteMaterialPurchaseDetailByOrderId(materialPurchaseOrder.getOrderId());
			//添加单据物料清单
			if(flag) {
				for (MaterialPurchaseDetail materialPurchaseDetail : materialPurchaseOrder.getDetails()){
					//预留
					/*materialPurchaseDetail.setPurchaseNumber(materialPurchaseDetail.getPurchaseNumber());
					materialPurchaseDetail.setAuditNumber(materialPurchaseDetail.getApplyNumber());*/
					
					MaterialInfo materialInfo = materialDao.getMaterialById(materialPurchaseDetail.getMaterialId());
					materialPurchaseDetail.setOrderId(materialPurchaseOrder.getOrderId());
					materialPurchaseDetail.setMaterialNo(materialInfo.getNo());
					materialPurchaseDetail.setMaterialName(materialInfo.getName());
					materialPurchaseDetail.setMaterialForm(materialInfo.getForm());
					materialPurchaseDetail.setMaterialPrice(materialInfo.getPrice());
					materialPurchaseDetail.setMaterialTax(materialInfo.getTax());
					materialPurchaseDetail.setMaterialTaxPrice(materialInfo.getTaxPrice());
					materialPurchaseDetail.setMaterialSmallUnit(materialInfo.getSmallUnit());
					materialPurchaseDetail.setMaterialBigUnit(materialInfo.getBigUnit());
					materialPurchaseDetail.setMaterialNumber(materialInfo.getNumber());
					flag = 1 == inventoryDao.addMaterialPurchaseDetail(materialPurchaseDetail);
					if(!flag) break;
				}
			}
			
		} else if(oldMaterialPurchaseInfo.getStatus().intValue() != 1 && oldMaterialPurchaseInfo.getStatus().intValue() != 2 && materialPurchaseInfo.getStatus().intValue() == 2){//0申请 1审核退回2审核通过该
			//物料信息暂时不可更改预留...
			List<MaterialPurchaseDetail> materialPurchaseDetails = inventoryDao.getMaterialPurchaseDetailByOrderId(materialPurchaseOrder.getOrderId());
			
			for(MaterialPurchaseDetail materialPurchaseDetail: materialPurchaseDetails){
				//库存变动日志
				Map<String,Object> getInventoryParam = new HashMap<String,Object>(){{
					put("shopId", materialPurchaseInfo.getShopId());
					put("materialId", materialPurchaseDetail.getMaterialId());
				}};
				MaterialInventory materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(getInventoryParam);
				if(materialInventory == null){
					//自动初始化库存
	            	MaterialInventory intMaterialInventory = new MaterialInventory();
	            	intMaterialInventory.setInventoryId(UUID.randomUUID().toString());
//	            	intMaterialInventory.setShopId(Common.HEAD_OFFICE_ID);
	            	intMaterialInventory.setShopId(materialPurchaseInfo.getShopId());
	            	intMaterialInventory.setMaterialId(materialPurchaseDetail.getMaterialId());
	            	intMaterialInventory.setUsableInventory(new Double(0));
	            	intMaterialInventory.setCheckInventory(new Double(0));
	            	intMaterialInventory.setAlertInventory(new Double(0));
	            	intMaterialInventory.setCheckInventory(new Double(0));
					flag = 1 == materialDao.addMaterialInventory(intMaterialInventory);
					materialInventory = materialDao.getMaterialDetailByShopIdAndMaterialId(getInventoryParam);
	            }
				
				MaterialPurchaseDetail detail = new MaterialPurchaseDetail();
				if(flag){
	    			detail.setId(materialPurchaseDetail.getId());
	    			detail.setAuditNumber(materialPurchaseDetail.getApplyNumber());
	    			detail.setAuditUnit(materialPurchaseDetail.getApplyUnit());
	    			flag = 1 == inventoryDao.updateMaterialPurchaseDetial(detail);
	    		}
				
				Double convert = 0.0; 
				if(materialPurchaseDetail.getMaterialSmallUnit().equals(detail.getAuditUnit())){
					convert =  detail.getAuditNumber();
	            } else{
	            	convert = detail.getAuditNumber()  * materialPurchaseDetail.getMaterialNumber();
	            }
				MaterialInventoryChange materialInventoryChange = new MaterialInventoryChange();
				materialInventoryChange.setShopId(materialPurchaseInfo.getShopId());
				materialInventoryChange.setMaterialId(materialPurchaseDetail.getMaterialId());
	            materialInventoryChange.setOrderId(materialPurchaseInfo.getOrderId());
	            materialInventoryChange.setCreateDateTime(new Date());
	            materialInventoryChange.setChangeDate(materialPurchaseInfo.getAuditTime());
	            materialInventoryChange.setCreateUserId(materialPurchaseInfo.getCreateUserId());
	            materialInventoryChange.setFrom(materialInventory.getUsableInventory());
	            materialInventoryChange.setTo(materialInventory.getUsableInventory() + convert);
	            materialInventoryChange.setOrderType(OrderType.MATERIAL_PURCHASE);
	            materialInventoryChange.setRemark("采购入库");
	            materialInventoryChange.setStatus(Status.COMMON);
	            //库存变动日志
	            if(flag) flag = 1 == logDao.addMaterialInventoryChange(materialInventoryChange);
	        	if(flag){
	        		//更新门店物料的库存
	        		MaterialInventory materialInventory2 = new MaterialInventory();
	        		materialInventory2.setMaterialId(materialPurchaseDetail.getMaterialId());
	        		materialInventory2.setShopId(materialPurchaseInfo.getShopId());
//	        		materialInventory2.setShopId(Common.HEAD_OFFICE_ID);
	        		materialInventory2.setUsableInventory(convert);
	        		flag = 1 == materialDao.calculateInventoryByShopIdAndMaterialId(materialInventory2);
	        	}
				if(!flag){break;};
			}
		} else{
			flag = false;
		}
		
		
		if(!flag) throw new BusinessException(msg);
		return flag;
	}

	/**
	 * 获取物料采购入库单通过订单id
	 * @Title: getMaterialPurchaseOrderByOrderId 
	 * @author: Lsenrong
	 * @date Aug 16, 2017 2:55:21 PM
	 * @Description: TODO(描述)
	 */
	@Override
	public MaterialPurchaseOrder getMaterialPurchaseOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		MaterialPurchaseOrder materialPurchaseOrder = new MaterialPurchaseOrder();
		MaterialPurchaseInfo materialPurchaseInfo = inventoryDao.getMaterialPurchaseInfoByOrderId(orderId);
		List<MaterialPurchaseDetail> materialPurchaseDetails = inventoryDao.getMaterialPurchaseDetailByOrderId(orderId);
		materialPurchaseOrder.setOrderId(orderId);
		materialPurchaseOrder.setOrderNo(materialPurchaseInfo.getOrderNo());
		materialPurchaseOrder.setStatus(materialPurchaseInfo.getStatus());
		materialPurchaseOrder.setRealDate(materialPurchaseInfo.getRealDate());
		materialPurchaseOrder.setShopId(materialPurchaseInfo.getShopId());
		materialPurchaseOrder.setShopName(materialPurchaseInfo.getShopName());
		materialPurchaseOrder.setCreateUserId(materialPurchaseInfo.getCreateUserId());
		materialPurchaseOrder.setCreateUserName(materialPurchaseInfo.getCreateUserName());
		materialPurchaseOrder.setCreateDateTime(materialPurchaseInfo.getCreateDateTime());
		materialPurchaseOrder.setApplyUserId(materialPurchaseInfo.getApplyUserId());
		materialPurchaseOrder.setAuditUserId(materialPurchaseInfo.getAuditUserId());
		materialPurchaseOrder.setApplyUserName(materialPurchaseInfo.getApplyUserName());
		materialPurchaseOrder.setAuditUserName(materialPurchaseInfo.getAuditUserName());
		materialPurchaseOrder.setApplyRemark(materialPurchaseInfo.getApplyRemark());
		materialPurchaseOrder.setAuditRemark(materialPurchaseInfo.getAuditRemark());
		materialPurchaseOrder.setApplyTime(materialPurchaseInfo.getApplyTime());
		materialPurchaseOrder.setAuditTime(materialPurchaseInfo.getAuditTime());
		materialPurchaseOrder.setDetails(materialPurchaseDetails);
		return materialPurchaseOrder;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 实现单位转换
	 * @Title: convertToSmallUnit 
	 * @author: Lsenrong
	 * @date Jul 26, 2017 5:10:39 PM
	 * @Description: TODO(描述) 
	 * @param @param unit
	 * @param @param number
	 * @param @param materialAttribute
	 * @param @return    
	 * @return Double
	 */
	private Double convertToSmallUnit(String unit,Double number,MaterialAttribute materialAttribute)
	{
		Boolean flag = true;
		String msg = "";
		Double finalNumber = new Double(0);
		String smallUnit = materialAttribute.getMaterialSmallUnit();
		String bigUnit = materialAttribute.getMaterialBigUnit();
		if(smallUnit == null || bigUnit == null || smallUnit.isEmpty() || bigUnit.isEmpty()){
			flag = false;
			msg = "物料" + materialAttribute.getMaterialName() + "单位未能正确配置，无法进行单位转换！";
		}
		if(flag){
			if(unit.equals(smallUnit))
			{
				flag = true;
				finalNumber = number;
			} else if (unit.equals(bigUnit)){
				flag = true;
				finalNumber = number * materialAttribute.getMaterialNumber();
			} else {
				flag = false;
				msg = "单位无法转换，请检查系统单位配置是否正确";
			}
			if(!flag){
				throw new BusinessException(msg);
			}
		}
		return finalNumber;
	}
	@Override
	public Boolean addStorehouse(Storehouse storehouse) {
		// TODO Auto-generated method stub
		return inventoryDao.addStorehouse(storehouse) == 1;
	}
	@Override
	public Boolean updateStorehouse(Storehouse storehouse) {
		inventoryDao.updateShopAddressByStorehouse(storehouse);
		return inventoryDao.updateStorehouse(storehouse) == 1;
	}
	@Override
	public List<Storehouse> getStorehouseList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inventoryDao.getStorehouseList(map);
	}
	@Override
	public Integer getStorehouseCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inventoryDao.getStorehouseCount(map);
	}
	@Override
	public Storehouse getStorehouseById(String id) {
		// TODO Auto-generated method stub
		return inventoryDao.getStorehouseById(id);
	}
	@Override
	public Boolean deleteStorehouseById(String id) {
		// TODO Auto-generated method stub
		return inventoryDao.deleteStorehouseById(id) == 1;
	}
	/* (non-Javadoc)
	 * @see com.qhc.service.inventory.IInventoryService#selectInventoryReviewProcess(java.lang.String)
	 */
	@Override
	public List<InventoryReviewProcess> selectInventoryReviewProcess(String orderId) {
		// TODO Auto-generated method stub
		return inventoryDao.selectInventoryReviewProcess(orderId);
	}
	/* (non-Javadoc)
	 * @see com.qhc.service.inventory.IInventoryService#auditMaterialInventoryDetail(java.util.Map)
	 */
	@Override
	public boolean auditMaterialInventoryDetail(Map<String, String> params) throws UnsupportedEncodingException {
		String orderId = params.get("orderId");
    	String status = params.get("status");
    	String userId = params.get("userId");
    	//查询审核流程
    	List<InventoryReviewProcess> inventoryReviewProcess = inventoryDao.selectInventoryReviewProcess(orderId);
    	//判断审核状态
    	switch (status) {
		case "0":
			OrderBase orderBase = new OrderBase();
			orderBase.setOrderNo(orderId);
			orderBase.setStatus(3);
			//审核不通过
			orderDao.updateOrderBase(orderBase); 
			//更新审核人状态
			inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
				put("orderId", orderId);
				put("checkerId", userId);
				put("isCheck", status);
				put("isCurrentCheck", 1);
			}});
			break;
		case "1":
			//更新审核人状态
			int j = 0;
			for (int i = 0; i < inventoryReviewProcess.size(); i++) {
				InventoryReviewProcess target = inventoryReviewProcess.get(i);
				if (target.getCheckerId().equals(userId)) {
					//获取下一个审核人
					j = i + 1;
					if (j > inventoryReviewProcess.size() -1) {
						j = 0;
					}
				}
			}
			if (j != 0) {
				//审核终止 审核进行
				orderBase = new OrderBase();
				orderBase.setOrderNo(orderId);
				orderBase.setStatus(1);
				//审核不通过
				orderDao.updateOrderBase(orderBase);
				inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
					put("orderId", orderId);
					put("checkerId", userId);
					put("isCheck", status);
					put("isCurrentCheck", 0);
				}});
				//通知下一个审核人
				InventoryReviewProcess process = inventoryReviewProcess.get(j);
				inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
					put("orderId", process.getInventoryOrderId());
					put("checkerId", process.getCheckerId());
					put("isCheck", status);
					put("isCurrentCheck", 1);
				}});
				Employee employee = employeeService.getEmployeeInfoById(process.getCheckerId());
				if (employee != null && StringUtils.isNotBlank(employee.getPhone())) {
					HashMap<String, Object> parma = new HashMap<>();
					parma.put("#name#", employee.getName());
					parma.put("#orderId#", orderId);
					MessageUtilsYunPian.tplSingleSend(employee.getPhone(), "3756254", MessageUtilsYunPian.urlencode(parma));
				}
			}else {
				//审核终止 审核通过
				orderBase = new OrderBase();
				orderBase.setOrderNo(orderId);
				orderBase.setStatus(2);
				//审核通过
				orderDao.updateOrderBase(orderBase); 
				inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
					put("orderId", orderId);
					put("checkerId", userId);
					put("isCheck", status);
					put("isCurrentCheck", 1);
				}});
				MaterialInventoryOrder materialInventoryOrder = getMaterialInventoryOrderByOrderId(orderId);
				//更新库存
				auditSuccess(materialInventoryOrder);
			}
			break;
		case "2":
			//审核终止 审核进行
			orderBase = new OrderBase();
			orderBase.setOrderNo(orderId);
			orderBase.setStatus(1);
			//审核不通过
			orderDao.updateOrderBase(orderBase);
			//提交审核
			InventoryReviewProcess process = inventoryReviewProcess.get(0);
			inventoryDao.updateInventoryReviewProcess(new HashMap<String,Object>(){{
				put("orderId", process.getInventoryOrderId());
				put("checkerId", process.getCheckerId());
				put("isCheck", status);
				put("isCurrentCheck", 1);
			}});
			Employee employee = employeeService.getEmployeeInfoById(process.getCheckerId());
			if (employee != null && StringUtils.isNotBlank(employee.getPhone())) {
				HashMap<String, Object> parma = new HashMap<>();
				parma.put("#name#", employee.getName());
				parma.put("#orderId#", orderId);
				MessageUtilsYunPian.tplSingleSend(employee.getPhone(), "3756254", MessageUtilsYunPian.urlencode(parma));
			}
			break;
		default:
			throw new BusinessException("审核状态异常!" + status);
		}
    	return true;
	}
	/* (non-Javadoc)
	 * @see com.qhc.service.inventory.IInventoryService#hasMaterialInventory(java.lang.String)
	 */
	@Override
	public void hasMaterialInventory(String shopId) {
		int order = orderDao.getOrder(shopId);
		if (order > 0) {
			throw new BusinessException("有审核中状态的盘点单,操作失败!");
		}
	}
	/* (non-Javadoc)
	 * @see com.qhc.service.inventory.IInventoryService#getMaterialInventorySurplusAndLossesList(java.util.Map)
	 */
	@Override
	public List<MaterialInventoryDetail> getMaterialInventorySurplusAndLossesList(Map<String, Object> params) {
		return inventoryDao.getMaterialInventorySurplusAndLossesList(params);
	}
	/* (non-Javadoc)
	 * @see com.qhc.service.inventory.IInventoryService#getMaterialInventorySurplusAndLossesCount(java.util.Map)
	 */
	@Override
	public int getMaterialInventorySurplusAndLossesCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return inventoryDao.getMaterialInventorySurplusAndLossesCount(params);
	}
}
