package com.bjsxt.pojo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 * 闂ㄥ簵鑰冩牳
 * @author Lsenrong
 * @date Sep 22, 2017 4:44:52 PM
 * @Description: TODO(鎻忚堪)
 */
public class ShopKPISort {
	static DecimalFormat df = new DecimalFormat(",##0.00");  
	static NumberFormat percent = new DecimalFormat("0.00%");
	static BigDecimal zeroNum = new BigDecimal(0);
	
	public ShopKPISort()
	{
		/*if(percent == null)
		{
			percent = NumberFormat.getPercentInstance();
			percent.setMaximumFractionDigits(2);
		}*/
		
	}
	/**
	 * 鏍囪
	 */
	private String sign;
	/**
	 * 鍞竴id
	 */
	private Integer id;
	/**
	 * 鏈堜唤
	 */
    private String month;
    /**
     * 闂ㄥ簵id
     */
    private String shopId;
    /**
     * 闂ㄥ簵缂栧彿
     */
    private String shopNo;
    /**
     * 闂ㄥ簵鍚嶇О
     */
    private String shopName;
    /**
     * 搴楅暱id
     */
    private String headerId;
    /**
     * 搴楅暱鍚嶇О
     */
    private String headerName;
    /**
     * 鍖哄煙缁忕悊id
     */
    private String areaManagerId;
    /**
     * 鍖哄煙缁忕悊濮撳悕
     */
    private String areaManagerName;
    /**
     * 澶у尯缁忕悊id
     */
    private String regionManagerId;
    /**
     * 澶у尯缁忕悊濮撳悕
     */
    private String regionManagerName;
    /**
     * 鎬荤洃id
     */
    private String directorId;
    /**
     * 鎬荤洃鍚嶇О
     */
    private String directorName;
    /**
     * 鍒嗙粍
     */
    private String group;
    /**
     * 杩涘簵浜烘鐩爣
     */
    private Integer enterTimeTarget;
    /**
     * 杩涘簵浜烘瀹為檯
     */
    private Integer enterTimeActual;
    /**
     * 杩涘簵浜烘鍗犳瘮
     */
    private Double enterTimeRatio;
    /**
     * 棰勬敹鐩爣
     */
    private BigDecimal advancesReceivedTarget;
    /**
     * 棰勬敹鐩爣
     */
    private String advancesReceivedTargetStr;
    /**
     * 棰勬敹鐩爣-椤圭洰
     */
    private BigDecimal advancesReceivedTargetOfProject;
    /**
     * 棰勬敹鐩爣-椤圭洰
     */
    private String advancesReceivedTargetOfProjectStr;
    /**
     * 棰勬敹鐩爣-浜у搧
     */
    private BigDecimal advancesReceivedTargetOfProduct;
    /**
     * 棰勬敹鐩爣-浜у搧
     */
    private String advancesReceivedTargetOfProductStr;
    /**
     * 棰勬敹瀹為檯
     */
    private BigDecimal advancesReceivedActual;
    /**
     * 棰勬敹瀹為檯
     */
    private String advancesReceivedActualStr;
    /**
     * 棰勬敹瀹為檯-椤圭洰
     */
    private BigDecimal advancesReceivedActualOfProject;
    /**
     * 棰勬敹瀹為檯-椤圭洰
     */
    private String advancesReceivedActualOfProjectStr;
    /**
     * 棰勬敹瀹為檯-浜у搧
     */
    private BigDecimal advancesReceivedActualOfProduct;
    /**
     * 棰勬敹瀹為檯-浜у搧
     */
    private String advancesReceivedActualOfProductStr;
    /**
     * 棰勬敹鍗犳瘮
     */
    private Double advancesReceivedRatio;
    /**
     * 棰勬敹鍗犳瘮-浜у搧
     */
    private Double advancesReceivedRatioOfProduct;
    /**
     * 棰勬敹鍗犳瘮-椤圭洰
     */
    private Double advancesReceivedRatioOfProject;
    /**
     * 娑堣�楅噾棰濈洰鏍�
     */
    private BigDecimal accountCostTarget;
    /**
     * 娑堣�楅噾棰濈洰鏍�
     */
    private String accountCostTargetStr;
    /**
     * 娑堣�楅噾棰濆疄闄�
     */
    private BigDecimal accountCostActual;
    /**
     * 娑堣�楅噾棰濆疄闄�
     */
    private String accountCostActualStr;
    /**
     * 娑堣�楀崰姣�
     */
    private Double accountCostRatio;
    /**
     * 涓�杞洰鏍�
     */
    private Integer transformFirTarget;
    /**
     * 涓�杞疄闄�
     */
    private Integer transformFirActual;
    /**
     * 涓�杞崰姣�
     */
    private Double transformFirRatio;
    /**
     * 浜岃浆
     */
    private Integer transformSecTarget;
    /**
     * 浜岃浆
     */
    private Integer transformSecActual;
    /**
     * 浜岃浆鍗犳瘮
     */
    private Double transformSecRatio;
    /**
     * 璐拱椤圭洰鍏呭��
     */
    private BigDecimal buyProject;
    /**
     * 璐拱椤圭洰鍏呭��
     */
    private String buyProjectStr;
    /**
     * 璐拱浜у搧鍏呭��
     */
    private BigDecimal buyProduct;
    /**
     * 璐拱浜у搧鍏呭��
     */
    private String buyProductStr;
    /**
     * 璐拱濂楅鍏呭��
     */
    private BigDecimal buySet;
    /**
     * 璐拱濂楅鍏呭��
     */
    private String buySetStr;
    /**
     * 璐拱鍚堜綔椤圭洰鍏呭��
     */
    private BigDecimal buyCooperation;
    /**
     * 璐拱鍚堜綔椤圭洰鍏呭��
     */
    private String buyCooperationStr;
    /**
     * 椤圭洰杩樻鍏呭��
     */
    private BigDecimal returnProject;
    /**
     * 浜у搧杩樻鍏呭��
     */
    private BigDecimal returnProduct;
    /**
     * 濂楅杩樻鍏呭��
     */
    private BigDecimal returnSet;
    /**
     * 鍚堜綔椤圭洰杩樻鍏呭��
     */
    private BigDecimal returnCooperation;
    /**
     * 椤圭洰杩樻鍏呭��
     */
    private String returnProjectStr;
    /**
     * 浜у搧杩樻鍏呭��
     */
    private String returnProductStr;
    /**
     * 濂楅杩樻鍏呭��
     */
    private String returnSetStr;
    /**
     * 鍚堜綔椤圭洰杩樻鍏呭��
     */
    private String returnCooperationStr;
    
    /**
     * 閫�椤圭洰鍏呭��
     */
    private BigDecimal refundProject;
    /**
     * 閫�浜у搧鍏呭��
     */
    private BigDecimal refundProduct;
    /**
     * 閫�鍚堜綔椤圭洰鍏呭��
     */
    private BigDecimal refundCooperation;
    /**
     * 閫�鎺夊偍鍊�
     */
    private BigDecimal refundFromStore;
    /**
     * 閫�鑷冲偍鍊�
     */
    private BigDecimal refundToStore;
    private BigDecimal refundProjectToStore;
    private BigDecimal refundProjectToStoreOfWenXiu;
    private BigDecimal refundProductToStore;
    private String refundToStoreStr;
    private String refundProjectToStoreStr;
    private String refundProductToStoreStr;
    
    private String refundProjectStr;
    private String refundProductStr;
    private String refundCooperationStr;
    private String refundFromStoreStr;
    
    
    /**
     * 鎹㈣喘椤圭洰鍏呭��
     */
    private BigDecimal fillProject;
    /**
     * 鎹㈣喘浜у搧鍏呭��
     */
    private BigDecimal fillProduct;
    /**
     * 鎹㈣喘鍚堜綔椤圭洰鍏呭��
     */
    private BigDecimal fillCooperation;
    /**
     * 鎹㈣喘閫�鍥炲偍鍊煎厖鍊�
     */
    private BigDecimal fillStore;
    
    /**
     * 鎹㈣喘椤圭洰鍏呭��
     */
    private String fillProjectStr;
    /**
     * 鎹㈣喘浜у搧鍏呭��
     */
    private String fillProductStr;
    /**
     * 鎹㈣喘鍚堜綔椤圭洰鍏呭��
     */
    private String fillCooperationStr;
    /**
     * 鎹㈣喘閫�鍥炲偍鍊煎厖鍊�
     */
    private String fillStoreStr;
    
    /**
     * 娑堣�楅」鐩�
     */
    private BigDecimal consumeProject;
    /**
     * 娑堣�楅」鐩�
     */
    private String consumeProjectStr;
    /**
     * 娑堣�椾骇鍝�
     */
    private BigDecimal consumeProduct;
    /**
     * 娑堣�椾骇鍝�
     */
    private String consumeProductStr;
    /**
     * 娑堣�楀悎浣滈」鐩�
     */
    private BigDecimal consumeCooperation;
    /**
     * 娑堣�楀悎浣滈」鐩�
     */
    private String consumeCooperationStr;
    /**
     * 棰勬敹瀹屾垚鐜�
     */
    private BigDecimal advancesReceivedRate;
    private String advancesReceivedRateStr;
    /**
     * 棰勬敹瀹屾垚鐜�-椤圭洰
     */
    private BigDecimal advancesReceivedRateOfProject;
    private String advancesReceivedRateOfProjectStr;
    /**
     * 棰勬敹瀹屾垚鐜�-浜у搧
     */
    private BigDecimal advancesReceivedRateOfProduct;
    private String advancesReceivedRateOfProductStr;
    /**
     * 娑堣�楀畬鎴愮巼
     */
    private BigDecimal accountCostRate;
    private String accountCostRateStr;
    /**
     * 杩涘簵瀹屾垚鐜�
     */
    private BigDecimal enterTimeRate;
    private String enterTimeRateStr;
    /**
     * 涓�杞畬鎴愮巼
     */
    private BigDecimal transformFirRate;
    private String transformFirRateStr;
    /**
     * 浜岃浆瀹屾垚鐜�
     */
    private BigDecimal transformSecRate;
    private String transformSecRateStr;
    /**
     * 缁煎悎瀹屾垚鐜�
     */
    private BigDecimal kpiRate;
    private String kpiRateStr;
    /**
     * 鑰冩牳绫诲瀷
     */
    private String evaluation;
    /**
     * 鑳屾櫙棰滆壊
     */
    private String bgColor;
    /**
     * 寮�搴楁棩鏈�
     */
    private Date openDate;
    /**
     * 鑰冩牳寰楀垎
     */
    private Double score;
    private String scoreStr;
    
    
    /**
     * 鍚堜綔椤圭洰鏀舵
     */
    private BigDecimal cooperationRecharge;
    private String cooperationRechargeStr;
    /**
     * 鏍囧噯鏀舵
     */
    private BigDecimal standardRecharge;
    private String standardRechargeStr;
    /**
     * 鏍囧噯鏀舵涔嬪鍏蜂骇鍝�
     */
    private BigDecimal standardRechargeOfProduct;
    private String standardRechargeOfProductStr;
    /**
     * 鏍囧噯鏀舵涔嬮」鐩�
     */
    private BigDecimal standardRechargeOfProject;
    private String standardRechargeOfProjectStr;
    /**
     * 鍚堜綔椤圭洰閫�娆�
     */
    private BigDecimal cooperationRefund;
    private String cooperationRefundStr;
    /**
     * 鏍囧噯閫�娆�
     */
    private BigDecimal standardRefund;
    private String standardRefundStr;
    /**
     * 鏍囧噯閫�娆句箣浜у搧
     */
    private BigDecimal standardRefundOfProduct;
    private String standardRefundOfProductStr;
    /**
     * 鏍囧噯閫�娆句箣椤圭洰
     */
    private BigDecimal standardRefundOfProject;
    private String standardRefundOfProjectStr;
    /**
     * 鏍囧噯閫�娆句箣绾圭唬椤圭洰
     */
//    private BigDecimal standardRefundOfProjectOfWenXiu;
//    private String standardRefundOfProjectOfWenXiuStr;
	/**
     * 鍚堜綔椤圭洰娑堣��
     */
    private BigDecimal cooperationConsume;
    private String cooperationConsumeStr;
    /**
     * 鏍囧噯閫�娆�
     */
    private BigDecimal standardConsume;
    private String standardConsumeStr;
    
    
    
    /**
     * 鍗栦骇鍝佸厖鍊�
     */
    private BigDecimal sellBuyProduct;
    /**
     * 鍗栦骇鍝佸厖鍊�
     */
    private String sellBuyProductStr;
    /**
     * 杩樹骇鍝佸厖鍊�
     */
    private BigDecimal sellReturnProduct;
    /**
     * 杩樹骇鍝佸厖鍊�
     */
    private String sellReturnProductStr;
    /**
     * 鎹骇鍝佸厖鍊�
     */
    private BigDecimal sellExchangeProduct;
    /**
     * 鎹骇鍝佸厖鍊�
     */
    private String sellExchangeProductStr;
    /**
     * 閫�浜у搧鍏呭��
     */
    private BigDecimal sellRefundProduct;
    /**
     * 閫�浜у搧鍏呭��
     */
    private String sellRefundProductStr;
    
    /**
     * 閿�鍞骇鍝佸厖鍊�
     */
    private BigDecimal sellProduct;
    /**
     * 閿�鍞骇鍝佸厖鍊�
     */
    private String sellProductStr;
    
    /**
     * 鍒濆鍖栨暟鎹�
     */
    private BigDecimal advanceInit;
    private String advanceInitStr;
    private BigDecimal advanceInitOfProduct;
    private String advanceInitOfProductStr;
    private BigDecimal advanceInitOfProject;
    private String advanceInitOfProjectStr;
    private BigDecimal consumeInit;
    private String consumeInitStr;
    private Integer transformFirInit;
    private Integer transformSecInit;
    private Integer enterTimeInit;
    
    /**
     * 閽堝18骞�5鏈�1鏂规...
     */
    private BigDecimal cooperationRechargeWenXiu;
    private BigDecimal cooperationRechargeNotWenXiuBuyPercent;
    private BigDecimal cooperationRechargeNotWenXiuDonePercent;
    private BigDecimal cooperationConsumeWenXiu;
    private BigDecimal cooperationConsumeNotWenXiu;
    
    /**
     * 閽堝18骞�12鏈�1鏂规
     */
    //涓嶈鍏ユ秷鑰椾笟缁╃殑鍚堜綔椤圭洰
    private BigDecimal cooperationConsumeNotWenXiuOther = new BigDecimal(0);
    
    public BigDecimal getCooperationRechargeWenXiu() {
		return cooperationRechargeWenXiu;
	}
	public void setCooperationRechargeWenXiu(BigDecimal cooperationRechargeWenXiu) {
		this.cooperationRechargeWenXiu = cooperationRechargeWenXiu;
	}
	public BigDecimal getCooperationRechargeNotWenXiuBuyPercent() {
		return cooperationRechargeNotWenXiuBuyPercent;
	}
	public void setCooperationRechargeNotWenXiuBuyPercent(BigDecimal cooperationRechargeNotWenXiuBuyPercent) {
		this.cooperationRechargeNotWenXiuBuyPercent = cooperationRechargeNotWenXiuBuyPercent;
	}
	public BigDecimal getCooperationRechargeNotWenXiuDonePercent() {
		return cooperationRechargeNotWenXiuDonePercent;
	}
	public void setCooperationRechargeNotWenXiuDonePercent(BigDecimal cooperationRechargeNotWenXiuDonePercent) {
		this.cooperationRechargeNotWenXiuDonePercent = cooperationRechargeNotWenXiuDonePercent;
	}
	
	public BigDecimal getCooperationRecharge() {
		return cooperationRecharge;
	}
	public void setCooperationRecharge(BigDecimal cooperationRecharge) {
		this.cooperationRecharge = cooperationRecharge;
	}
	public String getCooperationRechargeStr() {
		if(cooperationRechargeStr == null && cooperationRecharge != null){
			cooperationRechargeStr = df.format(cooperationRecharge);
		}
		return cooperationRechargeStr;
	}
	public void setCooperationRechargeStr(String cooperationRechargeStr) {
		this.cooperationRechargeStr = cooperationRechargeStr;
	}
	public BigDecimal getStandardRecharge() {
		return standardRecharge;
	}
	public void setStandardRecharge(BigDecimal standardRecharge) {
		this.standardRecharge = standardRecharge;
	}
	public String getStandardRechargeStr() {
		if(standardRechargeStr == null && standardRecharge != null){
			standardRechargeStr = df.format(standardRecharge);
		}
		return standardRechargeStr;
	}
	public void setStandardRechargeStr(String standardRechargeStr) {
		this.standardRechargeStr = standardRechargeStr;
	}
	public BigDecimal getCooperationRefund() {
		return cooperationRefund;
	}
	public void setCooperationRefund(BigDecimal cooperationRefund) {
		this.cooperationRefund = cooperationRefund;
	}
	public String getCooperationRefundStr() {
		if(cooperationRefundStr == null && cooperationRefund != null){
			cooperationRefundStr = df.format(cooperationRefund);
		}
		return cooperationRefundStr;
	}
	public void setCooperationRefundStr(String cooperationRefundStr) {
		this.cooperationRefundStr = cooperationRefundStr;
	}
	public BigDecimal getStandardRefund() {
		return standardRefund;
	}
	public void setStandardRefund(BigDecimal standardRefund) {
		this.standardRefund = standardRefund;
	}
	public String getStandardRefundStr() {
		if(standardRefundStr == null && standardRefund != null){
			standardRefundStr = df.format(standardRefund);
		}
		return standardRefundStr;
	}
	public void setStandardRefundStr(String standardRefundStr) {
		this.standardRefundStr = standardRefundStr;
	}
	public BigDecimal getStandardRefundOfProduct() {
		return standardRefundOfProduct;
	}
	public void setStandardRefundOfProduct(BigDecimal standardRefundOfProduct) {
		this.standardRefundOfProduct = standardRefundOfProduct;
	}
	public String getStandardRefundOfProductStr() {
		if(standardRefundOfProductStr == null && standardRefundOfProduct != null){
			standardRefundOfProductStr = df.format(standardRefundOfProduct);
		}
		return standardRefundOfProductStr;
	}
	public void setStandardRefundOfProductStr(String standardRefundOfProductStr) {
		this.standardRefundOfProductStr = standardRefundOfProductStr;
	}
	public BigDecimal getStandardRefundOfProject() {
		return standardRefundOfProject;
	}
	public void setStandardRefundOfProject(BigDecimal standardRefundOfProject) {
		this.standardRefundOfProject = standardRefundOfProject;
	}
	public String getStandardRefundOfProjectStr() {
		if(standardRefundOfProjectStr == null && standardRefundOfProject != null){
			standardRefundOfProjectStr = df.format(standardRefundOfProject);
		}
		return standardRefundOfProjectStr;
	}
	public void setStandardRefundOfProjectStr(String standardRefundOfProjectStr) {
		this.standardRefundOfProjectStr = standardRefundOfProjectStr;
	}
//	public BigDecimal getStandardRefundOfProjectOfWenXiu() {
//		return standardRefundOfProjectOfWenXiu;
//	}
//	public void setStandardRefundOfProjectOfWenXiu(BigDecimal standardRefundOfProjectOfWenXiu) {
//		this.standardRefundOfProjectOfWenXiu = standardRefundOfProjectOfWenXiu;
//	}
//	public String getStandardRefundOfProjectOfWenXiuStr() {
//		if(standardRefundOfProjectOfWenXiuStr == null && standardRefundOfProjectOfWenXiu != null){
//			standardRefundOfProjectOfWenXiuStr = df.format(standardRefundOfProjectOfWenXiu);
//		}
//		return standardRefundOfProjectOfWenXiuStr;
//	}
//	public void setStandardRefundOfProjectOfWenXiuStr(String standardRefundOfProjectOfWenXiuStr) {
//		this.standardRefundOfProjectOfWenXiuStr = standardRefundOfProjectOfWenXiuStr;
//	}
	
	public BigDecimal getCooperationConsume() {
		return cooperationConsume;
	}
	public void setCooperationConsume(BigDecimal cooperationConsume) {
		this.cooperationConsume = cooperationConsume;
	}
	public String getCooperationConsumeStr() {
		if(cooperationConsumeStr == null && cooperationConsume != null){
			cooperationConsumeStr = df.format(cooperationConsume);
		}
		return cooperationConsumeStr;
	}
	public void setCooperationConsumeStr(String cooperationConsumeStr) {
		this.cooperationConsumeStr = cooperationConsumeStr;
	}
	public BigDecimal getStandardConsume() {
		return standardConsume;
	}
	public void setStandardConsume(BigDecimal standardConsume) {
		this.standardConsume = standardConsume;
	}
	public String getStandardConsumeStr() {
		if(standardConsumeStr == null && standardConsume != null){
			standardConsumeStr = df.format(standardConsume);
		}
		return standardConsumeStr;
	}
	public void setStandardConsumeStr(String standardConsumeStr) {
		this.standardConsumeStr = standardConsumeStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getHeaderId() {
		return headerId;
	}
	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getAreaManagerId() {
		return areaManagerId;
	}
	public void setAreaManagerId(String areaManagerId) {
		this.areaManagerId = areaManagerId;
	}
	public String getAreaManagerName() {
		return areaManagerName;
	}
	public void setAreaManagerName(String areaManagerName) {
		this.areaManagerName = areaManagerName;
	}
	public String getRegionManagerId() {
		return regionManagerId;
	}
	public void setRegionManagerId(String regionManagerId) {
		this.regionManagerId = regionManagerId;
	}
	public String getRegionManagerName() {
		return regionManagerName;
	}
	public void setRegionManagerName(String regionManagerName) {
		this.regionManagerName = regionManagerName;
	}
	public String getDirectorId() {
		return directorId;
	}
	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Integer getEnterTimeTarget() {
		return enterTimeTarget;
	}
	public void setEnterTimeTarget(Integer enterTimeTarget) {
		this.enterTimeTarget = enterTimeTarget;
	}
	public Integer getEnterTimeActual() {
		return enterTimeActual;
	}
	public void setEnterTimeActual(Integer enterTimeActual) {
		this.enterTimeActual = enterTimeActual;
	}
	public Double getEnterTimeRatio() {
		return enterTimeRatio;
	}
	public void setEnterTimeRatio(Double enterTimeRatio) {
		this.enterTimeRatio = enterTimeRatio;
	}
	public BigDecimal getAdvancesReceivedTarget() {
		return advancesReceivedTarget;
	}
	public void setAdvancesReceivedTarget(BigDecimal advancesReceivedTarget) {
		this.advancesReceivedTarget = advancesReceivedTarget;
	}
	public String getAdvancesReceivedTargetStr() {
		if(advancesReceivedTargetStr == null && advancesReceivedTarget != null)
			advancesReceivedTargetStr = df.format(advancesReceivedTarget);
		return advancesReceivedTargetStr;
	}
	public void setAdvancesReceivedTargetStr(String advancesReceivedTargetStr) {
		this.advancesReceivedTargetStr = advancesReceivedTargetStr;
	}
	public BigDecimal getAdvancesReceivedTargetOfProduct() {
		return advancesReceivedTargetOfProduct;
	}
	public void setAdvancesReceivedTargetOfProduct(BigDecimal advancesReceivedTargetOfProduct) {
		this.advancesReceivedTargetOfProduct = advancesReceivedTargetOfProduct;
	}
	public String getAdvancesReceivedTargetOfProductStr() {
		if(advancesReceivedTargetOfProductStr == null && advancesReceivedTargetOfProduct != null)
			advancesReceivedTargetOfProductStr = df.format(advancesReceivedTargetOfProduct);
		return advancesReceivedTargetOfProductStr;
	}
	public void setAdvancesReceivedTargetOfProductStr(String advancesReceivedTargetOfProductStr) {
		this.advancesReceivedTargetOfProductStr = advancesReceivedTargetOfProductStr;
	}
	public BigDecimal getAdvancesReceivedTargetOfProject() {
		return advancesReceivedTargetOfProject;
	}
	public void setAdvancesReceivedTargetOfProject(BigDecimal advancesReceivedTargetOfProject) {
		this.advancesReceivedTargetOfProject = advancesReceivedTargetOfProject;
	}
	public String getAdvancesReceivedTargetOfProjectStr() {
		if(advancesReceivedTargetOfProjectStr == null && advancesReceivedTargetOfProject != null)
			advancesReceivedTargetOfProjectStr = df.format(advancesReceivedTargetOfProject);
		return advancesReceivedTargetOfProjectStr;
	}
	public void setAdvancesReceivedTargetOfProjectStr(String advancesReceivedTargetOfProjectStr) {
		this.advancesReceivedTargetOfProjectStr = advancesReceivedTargetOfProjectStr;
	}
	public BigDecimal getAdvancesReceivedActual() {
		return advancesReceivedActual;
	}
	public void setAdvancesReceivedActual(BigDecimal advancesReceivedActual) {
		this.advancesReceivedActual = advancesReceivedActual;
	}
	public String getAdvancesReceivedActualStr() {
		if(advancesReceivedActualStr == null && advancesReceivedActual != null)
			advancesReceivedActualStr = df.format(advancesReceivedActual);
		return advancesReceivedActualStr;
	}
	public void setAdvancesReceivedActualStr(String advancesReceivedActualStr) {
		this.advancesReceivedActualStr = advancesReceivedActualStr;
	}
	public BigDecimal getAdvancesReceivedActualOfProduct() {
		return advancesReceivedActualOfProduct;
	}
	public void setAdvancesReceivedActualOfProduct(BigDecimal advancesReceivedActualOfProduct) {
		this.advancesReceivedActualOfProduct = advancesReceivedActualOfProduct;
	}
	public String getAdvancesReceivedActualOfProductStr() {
		if(advancesReceivedActualOfProductStr == null && advancesReceivedActualOfProduct != null)
			advancesReceivedActualOfProductStr = df.format(advancesReceivedActualOfProduct);
		return advancesReceivedActualOfProductStr;
	}
	public void setAdvancesReceivedActualOfProductStr(String advancesReceivedActualOfProductStr) {
		this.advancesReceivedActualOfProductStr = advancesReceivedActualOfProductStr;
	}
	public BigDecimal getAdvancesReceivedActualOfProject() {
		return advancesReceivedActualOfProject;
	}
	public void setAdvancesReceivedActualOfProject(BigDecimal advancesReceivedActualOfProject) {
		this.advancesReceivedActualOfProject = advancesReceivedActualOfProject;
	}
	public String getAdvancesReceivedActualOfProjectStr() {
		if(advancesReceivedActualOfProjectStr == null && advancesReceivedActualOfProject != null)
			advancesReceivedActualOfProjectStr = df.format(advancesReceivedActualOfProject);
		return advancesReceivedActualOfProjectStr;
	}
	public void setAdvancesReceivedActualOfProjectStr(String advancesReceivedActualOfProjectStr) {
		this.advancesReceivedActualOfProjectStr = advancesReceivedActualOfProjectStr;
	}
	public Double getAdvancesReceivedRatio() {
		return advancesReceivedRatio;
	}
	public void setAdvancesReceivedRatio(Double advancesReceivedRatio) {
		this.advancesReceivedRatio = advancesReceivedRatio;
	}
	public Double getAdvancesReceivedRatioOfProduct() {
		return advancesReceivedRatioOfProduct;
	}
	public void setAdvancesReceivedRatioOfProduct(Double advancesReceivedRatioOfProduct) {
		this.advancesReceivedRatioOfProduct = advancesReceivedRatioOfProduct;
	}
	public Double getAdvancesReceivedRatioOfProject() {
		return advancesReceivedRatioOfProject;
	}
	public void setAdvancesReceivedRatioOfProject(Double advancesReceivedRatioOfProject) {
		this.advancesReceivedRatioOfProject = advancesReceivedRatioOfProject;
	}
	public BigDecimal getAccountCostTarget() {
		return accountCostTarget;
	}
	public void setAccountCostTarget(BigDecimal accountCostTarget) {
		this.accountCostTarget = accountCostTarget;
	}
	public String getAccountCostTargetStr() {
		if(accountCostTargetStr == null && accountCostTarget != null)
			accountCostTargetStr = df.format(accountCostTarget);
		return accountCostTargetStr;
	}
	public void setAccountCostTargetStr(String accountCostTargetStr) {
		this.accountCostTargetStr = accountCostTargetStr;
	}
	public BigDecimal getAccountCostActual() {
		return accountCostActual;
	}
	public void setAccountCostActual(BigDecimal accountCostActual) {
		this.accountCostActual = accountCostActual;
	}
	public String getAccountCostActualStr() {
		if(accountCostActualStr == null && accountCostActual != null)
			accountCostActualStr = df.format(accountCostActual);
		return accountCostActualStr;
	}
	public void setAccountCostActualStr(String accountCostActualStr) {
		this.accountCostActualStr = accountCostActualStr;
	}
	public Double getAccountCostRatio() {
		return accountCostRatio;
	}
	public void setAccountCostRatio(Double accountCostRatio) {
		this.accountCostRatio = accountCostRatio;
	}
	public Integer getTransformFirTarget() {
		return transformFirTarget;
	}
	public void setTransformFirTarget(Integer transformFirTarget) {
		this.transformFirTarget = transformFirTarget;
	}
	public Integer getTransformFirActual() {
		return transformFirActual;
	}
	public void setTransformFirActual(Integer transformFirActual) {
		this.transformFirActual = transformFirActual;
	}
	public Double getTransformFirRatio() {
		return transformFirRatio;
	}
	public void setTransformFirRatio(Double transformFirRatio) {
		this.transformFirRatio = transformFirRatio;
	}
	public Integer getTransformSecTarget() {
		return transformSecTarget;
	}
	public void setTransformSecTarget(Integer transformSecTarget) {
		this.transformSecTarget = transformSecTarget;
	}
	public Integer getTransformSecActual() {
		return transformSecActual;
	}
	public void setTransformSecActual(Integer transformSecActual) {
		this.transformSecActual = transformSecActual;
	}
	public Double getTransformSecRatio() {
		return transformSecRatio;
	}
	public void setTransformSecRatio(Double transformSecRatio) {
		this.transformSecRatio = transformSecRatio;
	}
	public BigDecimal getBuyProject() {
		return buyProject;
	}
	public void setBuyProject(BigDecimal buyProject) {
		this.buyProject = buyProject;
	}
	public String getBuyProjectStr() {
		if(buyProjectStr == null && buyProject != null)
			buyProjectStr = df.format(buyProject);
		return buyProjectStr;
	}
	public void setBuyProjectStr(String buyProjectStr) {
		this.buyProjectStr = buyProjectStr;
	}
	public BigDecimal getBuyProduct() {
		return buyProduct;
	}
	public void setBuyProduct(BigDecimal buyProduct) {
		this.buyProduct = buyProduct;
	}
	public String getBuyProductStr() {
		if(buyProductStr == null && buyProduct != null)
			buyProductStr = df.format(buyProduct);
		return buyProductStr;
	}
	public void setBuyProductStr(String buyProductStr) {
		this.buyProductStr = buyProductStr;
	}
	public BigDecimal getBuySet() {
		return buySet;
	}
	public void setBuySet(BigDecimal buySet) {
		this.buySet = buySet;
	}
	public String getBuySetStr() {
		if(buySetStr == null && buySet != null)
			buySetStr = df.format(buySet);
		return buySetStr;
	}
	public void setBuySetStr(String buySetStr) {
		this.buySetStr = buySetStr;
	}
	public BigDecimal getBuyCooperation() {
		return buyCooperation;
	}
	public void setBuyCooperation(BigDecimal buyCooperation) {
		this.buyCooperation = buyCooperation;
	}
	public String getBuyCooperationStr() {
		if(buyCooperationStr == null && buyCooperation != null)
			buyCooperationStr = df.format(buyCooperation); 
		return buyCooperationStr;
	}
	public void setBuyCooperationStr(String buyCooperationStr) {
		this.buyCooperationStr = buyCooperationStr;
	}
	public BigDecimal getReturnProject() {
		return returnProject;
	}
	public void setReturnProject(BigDecimal returnProject) {
		this.returnProject = returnProject;
	}
	public BigDecimal getReturnProduct() {
		return returnProduct;
	}
	public void setReturnProduct(BigDecimal returnProduct) {
		this.returnProduct = returnProduct;
	}
	public BigDecimal getReturnSet() {
		return returnSet;
	}
	public void setReturnSet(BigDecimal returnSet) {
		this.returnSet = returnSet;
	}
	public BigDecimal getReturnCooperation() {
		return returnCooperation;
	}
	public void setReturnCooperation(BigDecimal returnCooperation) {
		this.returnCooperation = returnCooperation;
	}
	public String getReturnProjectStr() {
		if(returnProjectStr == null && returnProject != null)
			returnProjectStr = df.format(returnProject);
		return returnProjectStr;
	}
	public void setReturnProjectStr(String returnProjectStr) {
		this.returnProjectStr = returnProjectStr;
	}
	public String getReturnProductStr() {
		if(returnProductStr == null && returnProduct != null)
			returnProductStr = df.format(returnProduct);
		return returnProductStr;
	}
	public void setReturnProductStr(String returnProductStr) {
		this.returnProductStr = returnProductStr;
	}
	public String getReturnSetStr() {
		if(returnSetStr == null && returnSet != null)
			returnSetStr = df.format(returnSet);
		return returnSetStr;
	}
	public void setReturnSetStr(String returnSetStr) {
		this.returnSetStr = returnSetStr;
	}
	public String getReturnCooperationStr() {
		if(returnCooperationStr == null && returnCooperation != null)
			returnCooperationStr = df.format(returnCooperation);
		return returnCooperationStr;
	}
	public void setReturnCooperationStr(String returnCooperationStr) {
		this.returnCooperationStr = returnCooperationStr;
	}
	public BigDecimal getConsumeProject() {
		return consumeProject;
	}
	public void setConsumeProject(BigDecimal consumeProject) {
		this.consumeProject = consumeProject;
	}
	public String getConsumeProjectStr() {
		if(consumeProjectStr == null && consumeProject != null)
			consumeProjectStr = df.format(consumeProject);
		return consumeProjectStr;
	}
	public void setConsumeProjectStr(String consumeProjectStr) {
		this.consumeProjectStr = consumeProjectStr;
	}
	public BigDecimal getConsumeProduct() {
		return consumeProduct;
	}
	public void setConsumeProduct(BigDecimal consumeProduct) {
		this.consumeProduct = consumeProduct;
	}
	public String getConsumeProductStr() {
		if(consumeProductStr == null && consumeProduct != null)
			consumeProductStr = df.format(consumeProduct);
		return consumeProductStr;
	}
	public void setConsumeProductStr(String consumeProductStr) {
		this.consumeProductStr = consumeProductStr;
	}
	public BigDecimal getConsumeCooperation() {
		return consumeCooperation;
	}
	public void setConsumeCooperation(BigDecimal consumeCooperation) {
		this.consumeCooperation = consumeCooperation;
	}
	public String getConsumeCooperationStr() {
		if(consumeCooperationStr == null && consumeCooperation != null)
			consumeCooperationStr = df.format(consumeCooperation);
		return consumeCooperationStr;
	}
	public void setConsumeCooperationStr(String consumeCooperationStr) {
		this.consumeCooperationStr = consumeCooperationStr;
	}
	public BigDecimal getAdvancesReceivedRate() {
		if(advancesReceivedRate == null && advancesReceivedActual != null && advancesReceivedTarget != null && advancesReceivedTarget.compareTo(zeroNum)==1)
			advancesReceivedRate = advancesReceivedActual.divide(advancesReceivedTarget,10,BigDecimal.ROUND_HALF_UP);
		return advancesReceivedRate;
	}
	public void setAdvancesReceivedRate(BigDecimal advancesReceivedRate) {
		this.advancesReceivedRate = advancesReceivedRate;
	}
	public BigDecimal getAdvancesReceivedRateOfProduct() {
		if(advancesReceivedRateOfProduct == null && advancesReceivedActualOfProduct != null && advancesReceivedTargetOfProduct != null && advancesReceivedTargetOfProduct.compareTo(zeroNum)==1)
			advancesReceivedRateOfProduct = advancesReceivedActualOfProduct.divide(advancesReceivedTargetOfProduct,10,BigDecimal.ROUND_HALF_UP);
		return advancesReceivedRateOfProduct;
	}
	public void setAdvancesReceivedRateOfProduct(BigDecimal advancesReceivedRateOfProduct) {
		this.advancesReceivedRateOfProduct = advancesReceivedRateOfProduct;
	}
	public BigDecimal getAdvancesReceivedRateOfProject() {
		if(advancesReceivedRateOfProject == null && advancesReceivedActualOfProject != null && advancesReceivedTargetOfProject != null && advancesReceivedTargetOfProject.compareTo(zeroNum)==1)
			advancesReceivedRateOfProject = advancesReceivedActualOfProject.divide(advancesReceivedTargetOfProject,10,BigDecimal.ROUND_HALF_UP);
		return advancesReceivedRateOfProject;
	}
	public void setAdvancesReceivedRateOfProject(BigDecimal advancesReceivedRateOfProject) {
		this.advancesReceivedRateOfProject = advancesReceivedRateOfProject;
	}
	public String getAdvancesReceivedRateStr() {
		if(advancesReceivedRateStr == null && getAdvancesReceivedRate() != null){
			advancesReceivedRateStr =  percent.format(advancesReceivedRate.doubleValue());
		}
		return advancesReceivedRateStr;
	}
	public void setAdvancesReceivedRateStr(String advancesReceivedRateStr) {
		this.advancesReceivedRateStr = advancesReceivedRateStr;
	}
	public String getAdvancesReceivedRateOfProductStr() {
		if(advancesReceivedRateOfProductStr == null && getAdvancesReceivedRateOfProduct() != null){
			advancesReceivedRateOfProductStr =  percent.format(advancesReceivedRateOfProduct.doubleValue());
		}
		return advancesReceivedRateOfProductStr;
	}
	public void setAdvancesReceivedRateOfProductStr(String advancesReceivedRateOfProductStr) {
		this.advancesReceivedRateOfProductStr = advancesReceivedRateOfProductStr;
	}
	public String getAdvancesReceivedRateOfProjectStr() {
		if(advancesReceivedRateOfProjectStr == null && getAdvancesReceivedRateOfProject() != null){
			advancesReceivedRateOfProjectStr =  percent.format(advancesReceivedRateOfProject.doubleValue());
		}
		return advancesReceivedRateOfProjectStr;
	}
	public void setAdvancesReceivedRateOfProjectStr(String advancesReceivedRateOfProjectStr) {
		this.advancesReceivedRateOfProjectStr = advancesReceivedRateOfProjectStr;
	}
	public BigDecimal getAccountCostRate() {
		if(accountCostRate == null && accountCostActual != null && accountCostTarget != null)
			accountCostRate = accountCostActual.divide(accountCostTarget,10,BigDecimal.ROUND_HALF_UP);
		return accountCostRate;
	}
	public void setAccountCostRate(BigDecimal accountCostRate) {
		this.accountCostRate = accountCostRate;
	}
	public String getAccountCostRateStr() {
		if(accountCostRateStr == null && getAccountCostRate() != null){
			accountCostRateStr =  percent.format(accountCostRate.doubleValue());
		}
		return accountCostRateStr;
	}
	public void setAccountCostRateStr(String accountCostRateStr) {
		this.accountCostRateStr = accountCostRateStr;
	}
	public BigDecimal getEnterTimeRate() {
		if(enterTimeRate == null && enterTimeTarget != null && enterTimeActual != null)
			enterTimeRate = new BigDecimal(enterTimeActual.doubleValue() / enterTimeTarget.doubleValue());
		return enterTimeRate;
	}
	public void setEnterTimeRate(BigDecimal enterTimeRate) {
		this.enterTimeRate = enterTimeRate;
	}
	public String getEnterTimeRateStr() {
		if(enterTimeRateStr == null && getEnterTimeRate() != null){
			enterTimeRateStr =  percent.format(enterTimeRate.doubleValue());
		}
		return enterTimeRateStr;
	}
	public void setEnterTimeRateStr(String enterTimeRateStr) {
		this.enterTimeRateStr = enterTimeRateStr;
	}
	public BigDecimal getTransformFirRate() {
		if(transformFirRate == null && transformFirTarget != null && transformFirActual != null)
			transformFirRate = new BigDecimal(transformFirActual.doubleValue() / transformFirTarget.doubleValue());
		return transformFirRate;
	}
	public void setTransformFirRate(BigDecimal transformFirRate) {
		this.transformFirRate = transformFirRate;
	}
	public String getTransformFirRateStr() {
		if(transformFirRateStr == null && getTransformFirRate() != null){
			transformFirRateStr =  percent.format(transformFirRate.doubleValue());
		}
		return transformFirRateStr;
	}
	public void setTransformFirRateStr(String transformFirRateStr) {
		this.transformFirRateStr = transformFirRateStr;
	}
	public BigDecimal getTransformSecRate() {
		if(transformSecRate == null && transformSecTarget != null && transformSecActual != null)
			transformSecRate = new BigDecimal(transformSecActual.doubleValue() / transformSecTarget.doubleValue());
		return transformSecRate;
	}
	public void setTransformSecRate(BigDecimal transformSecRate) {
		this.transformSecRate = transformSecRate;
	}
	public String getTransformSecRateStr() {
		if(transformSecRateStr == null && getTransformSecRate() != null){
			transformSecRateStr =  percent.format(transformSecRate.doubleValue());
		}
		return transformSecRateStr;
	}
	public void setTransformSecRateStr(String transformSecRateStr) {
		this.transformSecRateStr = transformSecRateStr;
	}
	public BigDecimal getKpiRate() {
		return kpiRate;
	}
	public void setKpiRate(BigDecimal kpiRate) {
		this.kpiRate = kpiRate;
	}
	public String getKpiRateStr() {
		if(kpiRateStr == null && kpiRate != null){
			kpiRateStr =  percent.format(kpiRate.doubleValue());
		}
		return kpiRateStr;
	}
	public void setKpiRateStr(String kpiRateStr) {
		this.kpiRateStr = kpiRateStr;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	public BigDecimal getRefundCooperation() {
		return refundCooperation;
	}
	public void setRefundCooperation(BigDecimal refundCooperation) {
		this.refundCooperation = refundCooperation;
	}
	public String getRefundCooperationStr() {
		if(refundCooperationStr == null && refundCooperation != null)
			refundCooperationStr = df.format(refundCooperation);
		return refundCooperationStr;
	}
	public void setRefundCooperationStr(String refundCooperationStr) {
		this.refundCooperationStr = refundCooperationStr;
	}
	public BigDecimal getFillProject() {
		return fillProject;
	}
	public void setFillProject(BigDecimal fillProject) {
		this.fillProject = fillProject;
	}
	public BigDecimal getFillProduct() {
		return fillProduct;
	}
	public void setFillProduct(BigDecimal fillProduct) {
		this.fillProduct = fillProduct;
	}
	public BigDecimal getFillCooperation() {
		return fillCooperation;
	}
	public void setFillCooperation(BigDecimal fillCooperation) {
		this.fillCooperation = fillCooperation;
	}
	public String getFillProjectStr() {
		if(fillProjectStr == null && fillProject != null)
			fillProjectStr = df.format(fillProject);
		return fillProjectStr;
	}
	public void setFillProjectStr(String fillProjectStr) {
		this.fillProjectStr = fillProjectStr;
	}
	public String getFillProductStr() {
		if(fillProductStr == null && fillProduct != null)
			fillProductStr = df.format(fillProduct);
		return fillProductStr;
	}
	public void setFillProductStr(String fillProductStr) {
		this.fillProductStr = fillProductStr;
	}
	public String getFillCooperationStr() {
		if(fillCooperationStr == null && fillCooperation != null)
			fillCooperationStr = df.format(fillCooperation);
		return fillCooperationStr;
	}
	public void setFillCooperationStr(String fillCooperationStr) {
		this.fillCooperationStr = fillCooperationStr;
	}
	public BigDecimal getFillStore() {
		return fillStore;
	}
	public void setFillStore(BigDecimal fillStore) {
		this.fillStore = fillStore;
	}
	public String getFillStoreStr() {
		if(fillStoreStr == null && fillStore != null)
			fillStoreStr = df.format(fillStore);
		return fillStoreStr;
	}
	public void setFillStoreStr(String fillStoreStr) {
		this.fillStoreStr = fillStoreStr;
	}
	public String getRefundProjectStr() {
		if(refundProjectStr == null && refundProject != null)
			refundProjectStr = df.format(refundProject);
		return refundProjectStr;
	}
	public void setRefundProjectStr(String refundProjectStr) {
		this.refundProjectStr = refundProjectStr;
	}
	public String getRefundProductStr() {
		if(refundProductStr == null && refundProduct != null)
			refundProductStr = df.format(refundProduct);
		return refundProductStr;
	}
	public void setRefundProductStr(String refundProductStr) {
		this.refundProductStr = refundProductStr;
	}
	public BigDecimal getRefundProject() {
		return refundProject;
	}
	public void setRefundProject(BigDecimal refundProject) {
		this.refundProject = refundProject;
	}
	public BigDecimal getRefundProduct() {
		return refundProduct;
	}
	public void setRefundProduct(BigDecimal refundProduct) {
		this.refundProduct = refundProduct;
	}
	
	public BigDecimal getSellBuyProduct() {
		return sellBuyProduct;
	}
	public void setSellBuyProduct(BigDecimal sellBuyProduct) {
		this.sellBuyProduct = sellBuyProduct;
	}
	public String getSellBuyProductStr() {
		if(sellBuyProductStr == null && sellBuyProduct != null)
			sellBuyProductStr = df.format(sellBuyProduct);
		return sellBuyProductStr;
	}
	public void setSellBuyProductStr(String sellBuyProductStr) {
		this.sellBuyProductStr = sellBuyProductStr;
	}
	public BigDecimal getSellReturnProduct() {
		return sellReturnProduct;
	}
	public void setSellReturnProduct(BigDecimal sellReturnProduct) {
		this.sellReturnProduct = sellReturnProduct;
	}
	public String getSellReturnProductStr() {
		if(sellReturnProductStr == null && sellReturnProduct != null)
			sellReturnProductStr = df.format(sellReturnProduct);
		return sellReturnProductStr;
	}
	public void setSellReturnProductStr(String sellReturnProductStr) {
		this.sellReturnProductStr = sellReturnProductStr;
	}
	public BigDecimal getSellExchangeProduct() {
		return sellExchangeProduct;
	}
	public void setSellExchangeProduct(BigDecimal sellExchangeProduct) {
		this.sellExchangeProduct = sellExchangeProduct;
	}
	public String getSellExchangeProductStr() {
		if(sellExchangeProductStr == null && sellExchangeProduct != null)
			sellExchangeProductStr = df.format(sellExchangeProduct);
		return sellExchangeProductStr;
	}
	public void setSellExchangeProductStr(String sellExchangeProductStr) {
		this.sellExchangeProductStr = sellExchangeProductStr;
	}
	public BigDecimal getSellRefundProduct() {
		return sellRefundProduct;
	}
	public void setSellRefundProduct(BigDecimal sellRefundProduct) {
		this.sellRefundProduct = sellRefundProduct;
	}
	public String getSellRefundProductStr() {
		if(sellRefundProductStr == null && sellRefundProduct != null)
			sellRefundProductStr = df.format(sellRefundProduct);
		return sellRefundProductStr;
	}
	public void setSellRefundProductStr(String sellRefundProductStr) {
		this.sellRefundProductStr = sellRefundProductStr;
	}
	public BigDecimal getSellProduct() {
		return sellProduct;
	}
	public void setSellProduct(BigDecimal sellProduct) {
		this.sellProduct = sellProduct;
	}
	public String getSellProductStr() {
		if(sellProductStr == null && sellProduct != null)
			sellProductStr = df.format(sellProduct);
		return sellProductStr;
	}
	public void setSellProductStr(String sellProductStr) {
		this.sellProductStr = sellProductStr;
	}
	public String getRefundToStoreStr() {
		if(refundToStoreStr == null && refundToStore != null)
			refundToStoreStr = df.format(refundToStore);
		return refundToStoreStr;
	}
	public void setRefundToStoreStr(String refundToStoreStr) {
		this.refundToStoreStr = refundToStoreStr;
	}
	public String getRefundProductToStoreStr() {
		if(refundProductToStoreStr == null && refundProductToStore != null)
			refundProductToStoreStr = df.format(refundProductToStore);
		return refundProductToStoreStr;
	}
	public void setRefundProductToStoreStr(String refundProductToStoreStr) {
		this.refundProductToStoreStr = refundProductToStoreStr;
	}
	public String getRefundProjectToStoreStr() {
		if(refundProjectToStoreStr == null && refundProjectToStore != null)
			refundProjectToStoreStr = df.format(refundProjectToStore);
		return refundProjectToStoreStr;
	}
	public void setRefundProjectToStoreStr(String refundProjectToStoreStr) {
		this.refundProjectToStoreStr = refundProjectToStoreStr;
	}
	public BigDecimal getRefundFromStore() {
		return refundFromStore;
	}
	public void setRefundFromStore(BigDecimal refundFromStore) {
		this.refundFromStore = refundFromStore;
	}
	public BigDecimal getRefundToStore() {
		return refundToStore;
	}
	public void setRefundToStore(BigDecimal refundToStore) {
		this.refundToStore = refundToStore;
	}
	public BigDecimal getRefundProjectToStore() {
		return refundProjectToStore;
	}
	public void setRefundProjectToStore(BigDecimal refundProjectToStore) {
		this.refundProjectToStore = refundProjectToStore;
	}
	public BigDecimal getRefundProjectToStoreOfWenXiu() {
		return refundProjectToStoreOfWenXiu;
	}
	public void setRefundProjectToStoreOfWenXiu(BigDecimal refundProjectToStoreOfWenXiu) {
		this.refundProjectToStoreOfWenXiu = refundProjectToStoreOfWenXiu;
	}
	public BigDecimal getRefundProductToStore() {
		return refundProductToStore;
	}
	public void setRefundProductToStore(BigDecimal refundProductToStore) {
		this.refundProductToStore = refundProductToStore;
	}
	public String getRefundFromStoreStr() {
		if(refundFromStoreStr == null && refundFromStore != null)
			refundFromStoreStr = df.format(refundFromStore);
		return refundFromStoreStr;
	}
	public void setRefundFromStoreStr(String refundFromStoreStr) {
		this.refundFromStoreStr = refundFromStoreStr;
	}
	public BigDecimal getAdvanceInit() {
		return advanceInit;
	}
	public void setAdvanceInit(BigDecimal advanceInit) {
		this.advanceInit = advanceInit;
	}
	public BigDecimal getAdvanceInitOfProduct() {
		return advanceInitOfProduct;
	}
	public void setAdvanceInitOfProduct(BigDecimal advanceInitOfProduct) {
		this.advanceInitOfProduct = advanceInitOfProduct;
	}
	public BigDecimal getAdvanceInitOfProject() {
		return advanceInitOfProject;
	}
	public void setAdvanceInitOfProject(BigDecimal advanceInitOfProject) {
		this.advanceInitOfProject = advanceInitOfProject;
	}
	public BigDecimal getConsumeInit() {
		return consumeInit;
	}
	public void setConsumeInit(BigDecimal consumeInit) {
		this.consumeInit = consumeInit;
	}
	public Integer getTransformFirInit() {
		return transformFirInit;
	}
	public void setTransformFirInit(Integer transformFirInit) {
		this.transformFirInit = transformFirInit;
	}
	public Integer getTransformSecInit() {
		return transformSecInit;
	}
	public void setTransformSecInit(Integer transformSecInit) {
		this.transformSecInit = transformSecInit;
	}
	public Integer getEnterTimeInit() {
		return enterTimeInit;
	}
	public void setEnterTimeInit(Integer enterTimeInit) {
		this.enterTimeInit = enterTimeInit;
	}
	public String getAdvanceInitStr() {
		if(advanceInit != null){
			advanceInitStr = df.format(advanceInit);
		}
		return advanceInitStr;
	}
	public void setAdvanceInitStr(String advanceInitStr) {
		this.advanceInitStr = advanceInitStr;
	}
	public String getAdvanceInitOfProductStr() {
		if(advanceInitOfProduct != null){
			advanceInitOfProductStr = df.format(advanceInitOfProduct);
		}
		return advanceInitOfProductStr;
	}
	public void setAdvanceInitOfProductStr(String advanceInitOfProductStr) {
		this.advanceInitOfProductStr = advanceInitOfProductStr;
	}
	public String getAdvanceInitOfProjectStr() {
		if(advanceInitOfProject != null){
			advanceInitOfProjectStr = df.format(advanceInitOfProject);
		}
		return advanceInitOfProjectStr;
	}
	public void setAdvanceInitOfProjectStr(String advanceInitOfProjectStr) {
		this.advanceInitOfProjectStr = advanceInitOfProjectStr;
	}
	public String getConsumeInitStr() {
		if(consumeInit != null){
			consumeInitStr = df.format(consumeInit);
		}
		return consumeInitStr;
	}
	public void setConsumeInitStr(String consumeInitStr) {
		this.consumeInitStr = consumeInitStr;
	}
	public BigDecimal getCooperationConsumeWenXiu() {
		return cooperationConsumeWenXiu;
	}
	public void setCooperationConsumeWenXiu(BigDecimal cooperationConsumeWenXiu) {
		this.cooperationConsumeWenXiu = cooperationConsumeWenXiu;
	}
	public BigDecimal getCooperationConsumeNotWenXiu() {
		return cooperationConsumeNotWenXiu;
	}
	public void setCooperationConsumeNotWenXiu(BigDecimal cooperationConsumeNotWenXiu) {
		this.cooperationConsumeNotWenXiu = cooperationConsumeNotWenXiu;
	}
	public BigDecimal getCooperationConsumeNotWenXiuOther() {
		return cooperationConsumeNotWenXiuOther;
	}
	public void setCooperationConsumeNotWenXiuOther(BigDecimal cooperationConsumeNotWenXiuOther) {
		this.cooperationConsumeNotWenXiuOther = cooperationConsumeNotWenXiuOther;
	}
	public String getScoreStr() {
		return scoreStr;
	}
	public void setScoreStr(String scoreStr) {
		this.scoreStr = scoreStr;
	}
	public BigDecimal getStandardRechargeOfProduct() {
		return standardRechargeOfProduct;
	}
	public void setStandardRechargeOfProduct(BigDecimal standardRechargeOfProduct) {
		this.standardRechargeOfProduct = standardRechargeOfProduct;
	}
	public String getStandardRechargeOfProductStr() {
		if(standardRechargeOfProductStr == null && standardRechargeOfProduct != null){
			standardRechargeOfProductStr = df.format(standardRechargeOfProduct);
		}
		return standardRechargeOfProductStr;
	}
	public void setStandardRechargeOfProductStr(String standardRechargeOfProductStr) {
		this.standardRechargeOfProductStr = standardRechargeOfProductStr;
	}
	public BigDecimal getStandardRechargeOfProject() {
		return standardRechargeOfProject;
	}
	public void setStandardRechargeOfProject(BigDecimal standardRechargeOfProject) {
		this.standardRechargeOfProject = standardRechargeOfProject;
	}
	public String getStandardRechargeOfProjectStr() {
		if(standardRechargeOfProjectStr == null && standardRechargeOfProject != null){
			standardRechargeOfProjectStr = df.format(standardRechargeOfProject);
		}
		return standardRechargeOfProjectStr;
	}
	public void setStandardRechargeOfProjectStr(String standardRechargeOfProjectStr) {
		this.standardRechargeOfProjectStr = standardRechargeOfProjectStr;
	}
    
}
