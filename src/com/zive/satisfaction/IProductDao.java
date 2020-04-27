package com.zive.satisfaction;

import java.util.List;
import java.util.Map;

import com.zive.pojo.CustomProductSales;

public interface IProductDao {

	List<CustomProductSales> getCustomProductSalesAnalyzes(Map<String, Object> parameter);
}
