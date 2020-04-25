package com.bjsxt.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class Main {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String,Object> getList() {
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i=1;i<=20;i++){
			Map<String, Object> map = new HashMap<>();
			map.put("id", i);
			map.put("name", "xxxx"+1);
			map.put("age", 18+i);
			map.put("createDate", new Date());
			list.add(map);
		}
//		JSONArray json = JSONArray.parseArray(JSON.toJSONString(list));
		
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("total", 20);
		jsonMap.put("row", list);
		taskExecutor.execute(() -> {
				for(int i=0;i<1000;i++){
					System.out.println("AAAAAAAAAAAAA");
				}
		});
		taskExecutor.execute(() -> {
				for(int i=0;i<1000;i++){
					System.out.println("BBBBBBBBBBBBB");
				}
		});
		return jsonMap;
	}
}
