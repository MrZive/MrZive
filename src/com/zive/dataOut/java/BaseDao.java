package com.zive.dataOut.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;

public class BaseDao {

	static SqlSession session = null;
	
	static{
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			session = factory.openSession();
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
	
	//门店----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public List<Shop> getShop(Shop shop){
		List<Shop> list = getSession().selectList("com.bjsxt.dataOut.common.getShop", shop);
		return list;
	}
	
	static public Shop getShopById(String id){
		Shop shop = new Shop();
		shop.setId(id);
		Shop one = getSession().selectOne("com.bjsxt.dataOut.common.getShop", shop);
		return one;
	}
	
	//会员卡----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public MemberCard getMemberCardById(String id){
		MemberCard memberCard = new MemberCard();
		memberCard.setId(id);
		MemberCard one = getSession().selectOne("com.bjsxt.dataOut.common.getMemberCard", memberCard);
		return one;
	}

	static public MemberCard getMemberCardByPhone(String phone){
		MemberCard memberCard = new MemberCard();
		memberCard.setPhone(phone);
		MemberCard one = getSession().selectOne("com.bjsxt.dataOut.common.getMemberCard", memberCard);
		return one;
	}
	
	static public List<MemberCard> getMemberCard(MemberCard memberCard){
		List<MemberCard> list = getSession().selectList("com.bjsxt.dataOut.common.getMemberCard", memberCard);
		return list;
	}
	
	//产品------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProductInfo getProductInfoById(String id){
		ProductInfo ProductInfo = new ProductInfo();
		ProductInfo.setId(id);
		ProductInfo one = getSession().selectOne("com.bjsxt.dataOut.common.getProductInfo", ProductInfo);
		return one;
	}

	static public ProductInfo getProductInfoByNo(String no){
		ProductInfo ProductInfo = new ProductInfo();
		ProductInfo.setNo(no);
		ProductInfo one = getSession().selectOne("com.bjsxt.dataOut.common.getProductInfo", ProductInfo);
		return one;
	}
	
	static public List<ProductInfo> getProductInfo(ProductInfo ProductInfo){
		List<ProductInfo> list = getSession().selectList("com.bjsxt.dataOut.common.getProductInfo", ProductInfo);
		return list;
	}
	
	//項目------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProjectInfo getProjectInfoById(String id){
		ProjectInfo ProjectInfo = new ProjectInfo();
		ProjectInfo.setId(id);
		ProjectInfo one = getSession().selectOne("com.bjsxt.dataOut.common.getProjectInfo", ProjectInfo);
		return one;
	}

	static public ProjectInfo getProjectInfoByNo(String no){
		ProjectInfo ProjectInfo = new ProjectInfo();
		ProjectInfo.setNo(no);
		ProjectInfo one = getSession().selectOne("com.bjsxt.dataOut.common.getProjectInfo", ProjectInfo);
		return one;
	}
	
	static public List<ProjectInfo> getProjectInfo(ProjectInfo ProjectInfo){
		List<ProjectInfo> list = getSession().selectList("com.bjsxt.dataOut.common.getProjectInfo", ProjectInfo);
		return list;
	}
	
	//合作項目------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public CooperationProject getCooperationProjectById(String id){
		CooperationProject CooperationProject = new CooperationProject();
		CooperationProject.setId(id);
		CooperationProject one = getSession().selectOne("com.bjsxt.dataOut.common.getCooperationProject", CooperationProject);
		return one;
	}

	static public CooperationProject getCooperationProjectByNo(String no){
		CooperationProject CooperationProject = new CooperationProject();
		CooperationProject.setNo(no);
		CooperationProject one = getSession().selectOne("com.bjsxt.dataOut.common.getCooperationProject", CooperationProject);
		return one;
	}
	
	static public List<CooperationProject> getCooperationProject(CooperationProject CooperationProject){
		List<CooperationProject> list = getSession().selectList("com.bjsxt.dataOut.common.getCooperationProject", CooperationProject);
		return list;
	}
	
	//消费流水------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public Consumption getConsumptionById(String id){
		Consumption Consumption = new Consumption();
		Consumption.setId(id);
		Consumption one = getSession().selectOne("com.bjsxt.dataOut.common.getConsumption", Consumption);
		return one;
	}
	
	static public List<Consumption> getConsumption(Consumption Consumption){
		List<Consumption> list = getSession().selectList("com.bjsxt.dataOut.common.getConsumption", Consumption);
		return list;
	}
}
