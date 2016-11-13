package com.lenovo.push.marketing.lestat.cinfo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.push.marketing.lestat.cinfo.test.datagenerator.ModelGenerator;
import com.lenovo.push.marketing.lestat.cinfo.vo.handsontable.HandsonTable;
import com.lenovo.push.marketing.lestat.cinfo.vo.json.BaseJsonEntity;


/**
 * 
 * @author Hongkai Liu
 *
 */
@Controller
public class AdminController {
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	private final static String DEFAULT_VIEW_NAME = "host";

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public @ResponseBody HandsonTable admin(@RequestParam(value = "name", required = false , defaultValue=DEFAULT_VIEW_NAME) String name,@RequestParam(value = "page", required = false, defaultValue="0") long page, @RequestParam(value = "row", required = false, defaultValue="10") long row) {
		logger.debug("==============admin page==================");
		
		HandsonTable ht = getHansonTable(name);

		return ht;

	}

	private HandsonTable getHansonTable(String name) {
		
		return ModelGenerator.getHandsonTable(name);
	}

	@RequestMapping(value = "/admin/remove", method = RequestMethod.GET)
	public @ResponseBody BaseJsonEntity remove(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id) {
		logger.debug("==============remove page==================" + "name:" + name + "; id:" + id);
		
		BaseJsonEntity json = new BaseJsonEntity();
		json.setMessage("id : " + id + " is saved");
		return json;

	}
	
	@RequestMapping(value = "/admin/save")
	public @ResponseBody BaseJsonEntity save() {
//		try {
//			logger.debug("==============save page==================" + "row:" + new ObjectMapper().writeValueAsString(row));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		logger.debug("==============save page=============");
		BaseJsonEntity json = new BaseJsonEntity();
		json.setMessage("id : " + "aaa" + " is saved");
		return json;

	}
}