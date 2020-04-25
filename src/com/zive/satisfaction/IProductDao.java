package com.bjsxt.satisfaction;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.CustomProductSales;

public interface IProductDao {

	List<CustomProductSales> getCustomProductSalesAnalyzes(Map<String, Object> parameter);
}
