package com.zive.dataOut.java;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.entity.ActivityShop;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MaterialInfo;
import com.zive.dataOut.entity.MaterialInventory;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProductInfoDetail;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.ProjectInfoDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.entity.UserAccountModel;

public class BaseDao {

	static SqlSession session = null;
	
	static{
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			session = factory.openSession();//openSession(true)自动提交事务
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public SqlSession getSession(){
		return session;
	}
	
	static public void closeSession(){
		session.close();
	}
	
	
	static public double setDoubleScale(Double price, int number){
		return price = new BigDecimal(price).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	static public double setDoubleScale(Double price){
		return setDoubleScale(price, 4);
	}
	
	//门店----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public List<Shop> getShop(Shop shop){
		List<Shop> list = getSession().selectList("com.zive.dataOut.common.getShop", shop);
		return list;
	}
	
	static public Shop getShopById(String id){
		Shop shop = new Shop();
		shop.setId(id);
		Shop one = getSession().selectOne("com.zive.dataOut.common.getShop", shop);
		return one;
	}
	
	//会员卡----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public MemberCard getMemberCardById(String id){
		MemberCard memberCard = new MemberCard();
		memberCard.setId(id);
		MemberCard one = getSession().selectOne("com.zive.dataOut.common.getMemberCard", memberCard);
		return one;
	}

	static public MemberCard getMemberCardByPhone(String phone){
		MemberCard memberCard = new MemberCard();
		memberCard.setPhone(phone);
		List<MemberCard> list = getSession().selectList("com.zive.dataOut.common.getMemberCard", memberCard);
		if(list.size()>1){
			for (MemberCard one : list) {
				if(one.getStatus()!=-1){
					return one;
				}
			}
		}else if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	static public List<MemberCard> getMemberCard(MemberCard memberCard){
		List<MemberCard> list = getSession().selectList("com.zive.dataOut.common.getMemberCard", memberCard);
		return list;
	}
	
	static public int updateMemberCard(MemberCard memberCard){
		int update = getSession().update("com.zive.dataOut.common.updateMemberCard", memberCard);
		return update;
	}
	
	static public int addMemberCard(MemberCard memberCard){
		int add = getSession().insert("com.zive.dataOut.common.addMemberCard", memberCard);
		return add;
	}
	
	//账户------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public UserAccountModel getAccountById(String id){
		UserAccountModel account = new UserAccountModel();
		account.setId(id);
		UserAccountModel one = getSession().selectOne("com.zive.dataOut.common.getUserAccount", account);
		return one;
	}

	static public List<UserAccountModel> getAccount(UserAccountModel account){
		List<UserAccountModel> list = getSession().selectList("com.zive.dataOut.common.getUserAccount", account);
		return list;
	}
	
	//物料------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public MaterialInfo getMaterialById(String id){
		MaterialInfo materialInfo = new MaterialInfo();
		materialInfo.setId(id);
		MaterialInfo one = getSession().selectOne("com.zive.dataOut.common.getMaterial", materialInfo);
		return one;
	}

	static public MaterialInfo getMaterialByNo(String no){
		MaterialInfo materialInfo = new MaterialInfo();
		materialInfo.setNo(no);
		MaterialInfo one = getSession().selectOne("com.zive.dataOut.common.getMaterial", materialInfo);
		return one;
	}
	
	static public List<MaterialInfo> getMaterial(MaterialInfo materialInfo){
		List<MaterialInfo> list = getSession().selectList("com.zive.dataOut.common.getMaterial", materialInfo);
		return list;
	}
	
	//库存------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public MaterialInventory getMaterialInventoryByIdAndShopId(String materialId,String shopId){
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterialId(materialId);
		materialInventory.setShopId(shopId);
		MaterialInventory one = getSession().selectOne("com.zive.dataOut.common.getMaterialInventory", materialInventory);
		return one;
	}

	static public List<MaterialInventory> getMaterialInventory(MaterialInventory materialInventory){
		List<MaterialInventory> list = getSession().selectList("com.zive.dataOut.common.getMaterialInventory", materialInventory);
		return list;
	}
	
	//产品------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProductInfo getProductInfoById(String id){
		ProductInfo ProductInfo = new ProductInfo();
		ProductInfo.setId(id);
		ProductInfo one = getSession().selectOne("com.zive.dataOut.common.getProductInfo", ProductInfo);
		return one;
	}

	static public ProductInfo getProductInfoByNo(String no){
		ProductInfo ProductInfo = new ProductInfo();
		ProductInfo.setNo(no);
		ProductInfo one = getSession().selectOne("com.zive.dataOut.common.getProductInfo", ProductInfo);
		return one;
	}
	
	static public List<ProductInfo> getProductInfo(ProductInfo ProductInfo){
		List<ProductInfo> list = getSession().selectList("com.zive.dataOut.common.getProductInfo", ProductInfo);
		return list;
	}
	
	static public ProductInfoDetail getProductInfoDetail(String shopId,String productId){
		ProductInfoDetail detail = new ProductInfoDetail();
		detail.setShopId(shopId);
		detail.setProductId(productId);
		ProductInfoDetail one = getSession().selectOne("com.zive.dataOut.common.getProductInfoDetail", detail);
		return one;
	}
	
	//項目------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProjectInfo getProjectInfoById(String id){
		ProjectInfo ProjectInfo = new ProjectInfo();
		ProjectInfo.setId(id);
		ProjectInfo one = getSession().selectOne("com.zive.dataOut.common.getProjectInfo", ProjectInfo);
		return one;
	}

	static public ProjectInfo getProjectInfoByNo(String no){
		ProjectInfo ProjectInfo = new ProjectInfo();
		ProjectInfo.setNo(no);
		ProjectInfo one = getSession().selectOne("com.zive.dataOut.common.getProjectInfo", ProjectInfo);
		return one;
	}
	
	static public List<ProjectInfo> getProjectInfo(ProjectInfo ProjectInfo){
		List<ProjectInfo> list = getSession().selectList("com.zive.dataOut.common.getProjectInfo", ProjectInfo);
		return list;
	}
	
	static public ProjectInfoDetail getProjectInfoDetail(String shopId,String projectId){
		ProjectInfoDetail detail = new ProjectInfoDetail();
		detail.setShopId(shopId);
		detail.setProjectId(projectId);
		ProjectInfoDetail one = getSession().selectOne("com.zive.dataOut.common.getProjectInfoDetail", detail);
		return one;
	}
	
	//合作項目------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public CooperationProject getCooperationProjectById(String id){
		CooperationProject CooperationProject = new CooperationProject();
		CooperationProject.setId(id);
		CooperationProject one = getSession().selectOne("com.zive.dataOut.common.getCooperationProject", CooperationProject);
		return one;
	}

	static public CooperationProject getCooperationProjectByNo(String no){
		CooperationProject CooperationProject = new CooperationProject();
		CooperationProject.setNo(no);
		CooperationProject one = getSession().selectOne("com.zive.dataOut.common.getCooperationProject", CooperationProject);
		return one;
	}
	
	static public List<CooperationProject> getCooperationProject(CooperationProject CooperationProject){
		List<CooperationProject> list = getSession().selectList("com.zive.dataOut.common.getCooperationProject", CooperationProject);
		return list;
	}
	
	//套餐------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	static public Activity getActivityById(String id){
		Activity activity = new Activity();
		activity.setId(id);
		Activity one = getSession().selectOne("com.zive.dataOut.common.getActivityById", activity);
		return one;
	}
	
	static public List<Activity> getActivity(Activity activity){
		List<Activity> list = getSession().selectList("com.zive.dataOut.common.getActivity", activity);
		return list;
	}
	
	static public int addActivityShop(ActivityShop shop){
		shop.setCreateDateTime(new Date());
		int add = getSession().insert("com.zive.dataOut.common.addActivityShop", shop);
		return add;
	}
	
	static public List<ActivityShop> getActivityShop(ActivityShop activity){
		List<ActivityShop> list = getSession().selectList("com.zive.dataOut.common.getActivityShop", activity);
		return list;
	}
	
	
	//消费流水------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public Consumption getConsumptionById(String id){
		Consumption Consumption = new Consumption();
		Consumption.setId(id);
		Consumption one = getSession().selectOne("com.zive.dataOut.common.getConsumption", Consumption);
		return one;
	}
	
	static public List<Consumption> getConsumption(Consumption Consumption){
		List<Consumption> list = getSession().selectList("com.zive.dataOut.common.getConsumption", Consumption);
		return list;
	}
	
	static public int addConsumption(Consumption Consumption){
		int add = getSession().insert("com.zive.dataOut.common.addConsumption", Consumption);
		return add;
	}
	
	static public int updateConsumption(Consumption Consumption){
		int add = getSession().update("com.zive.dataOut.common.updateConsumption", Consumption);
		return add;
	}
	
	//获取会员剩余项目----------------------------------------------------------------------------------------------------------------------------------------------------------
	static public List<Map<String,Object>> getCanDoneMemberProjectNumber(String memberCardId){
		List<Map<String, Object>> memberProjectNumber = getSession().selectList("com.zive.dataOut.common.getCanDoneMemberProjectNumber", memberCardId);
		
		Iterator<Map<String, Object>> iterator = memberProjectNumber.iterator();
		
		while(iterator.hasNext()){
			Map<String, Object> map = iterator.next();
			int isBook = map.get("isBook")==null?0:Integer.valueOf(map.get("isBook").toString());
			double owe = map.get("owe")==null?0d:Double.valueOf(map.get("owe").toString());
			int leftNumber = map.get("leftNumber")==null?0:Integer.valueOf(map.get("leftNumber").toString());
			int buyNumber = map.get("buyNumber")==null?0:Integer.valueOf(map.get("buyNumber").toString());
			int doneNumebr = buyNumber - leftNumber;
			BigDecimal price = map.get("price")==null?BigDecimal.ZERO:new BigDecimal(map.get("price").toString());
			BigDecimal realPayment = map.get("realPayment")==null?BigDecimal.ZERO:new BigDecimal(map.get("realPayment").toString());
			
			if(isBook>0 && owe>0 && price.doubleValue()>0D){
				BigDecimal leftDonePrice = realPayment.subtract(price.multiply(new BigDecimal(doneNumebr)));
				BigDecimal number = leftDonePrice.divide(price, 4, RoundingMode.HALF_UP);
				//BigDecimal number = leftDonePrice.divide(price);
				
				if(number.doubleValue()<1){
					iterator.remove();
				}
			}
		}
		
		return memberProjectNumber;
	}
}
