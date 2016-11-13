package com.lenovo.push.marketing.lestat.cinfo.test.datagenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.lenovo.push.marketing.lestat.cinfo.param.Param;
import com.lenovo.push.marketing.lestat.cinfo.vo.d3.MenuElem;
import com.lenovo.push.marketing.lestat.cinfo.vo.handsontable.HandsonRow;
import com.lenovo.push.marketing.lestat.cinfo.vo.handsontable.HandsonTable;

public class ModelGenerator {

	public static ModelAndView getHomeModel(){
		ModelAndView model = new ModelAndView();
		MenuElem subMenu = null;
		
		MenuElem viewMenu = new MenuElem("View","viewTabDrop","#", null);
		subMenu = new MenuElem("Host","viewHost","#view", "view?name=host");
		viewMenu.subMenuList.add(subMenu);
		subMenu = new MenuElem("Kafka","viewKafka","#view", "view?name=kafka");
		viewMenu.subMenuList.add(subMenu);
		subMenu = new MenuElem("Redis","viewRedis","#view", "view?name=redis");
		viewMenu.subMenuList.add(subMenu);
		model.addObject("viewMenu", viewMenu);
		
		MenuElem adminMenu = new MenuElem("Admin","adminTabDrop","#", null);
		subMenu = new MenuElem("Host","adminKafka","#view", "admin/list?name=host");
		adminMenu.subMenuList.add(subMenu);
		subMenu = new MenuElem("Kafka","adminKafka","#view", "admin/list?name=kafka");
		adminMenu.subMenuList.add(subMenu);
		subMenu = new MenuElem("Redis","adminRedis","#view", "admin/list?name=redis");
		adminMenu.subMenuList.add(subMenu);
		model.addObject("adminMenu", adminMenu);
		
		
		MenuElem uiMenu = new MenuElem("Link","link","#", null);
		//subMenu = new MenuElem("Kafka-TJ Monitor","kafka-tj-monitor","#view", null);
		subMenu = new MenuElem("Clouldear Manager","cloudera-manager","#view", Param.CLOUDERA_MANAGER_URL);
		uiMenu.subMenuList.add(subMenu);
		subMenu = new MenuElem("Kafka-TJ Monitor","kafka-tj-monitor","#view", Param.KAFKA_TJ_MONITOR_URL);
		uiMenu.subMenuList.add(subMenu);
		model.addObject("uiMenu", uiMenu);
		return model;
	}
	
	public static HandsonTable getHandsonTable(String name){
		HandsonTable ht = new HandsonTable();
		List<HandsonRow> data = new ArrayList<HandsonRow>();
		TestHandsonRow thr = null;
		// {id: 1, name: "Ted Right", address: ""},
        // {id: 2, name: "Frank Honest", address: ""},
        // {id: 3, name: "Joan Well", address: ""}
		thr =  new TestHandsonRow(1, "Ted Right1", name);
		data.add(thr);
		thr =  new TestHandsonRow(2, "Frank Honest", name);
		data.add(thr);
		thr =  new TestHandsonRow(3, "Joan Well", name);
		data.add(thr);
		ht.setData(data);
		return ht;
	}
}
