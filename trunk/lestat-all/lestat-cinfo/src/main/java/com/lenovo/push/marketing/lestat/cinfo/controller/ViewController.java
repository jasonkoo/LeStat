package com.lenovo.push.marketing.lestat.cinfo.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.push.marketing.lestat.cinfo.vo.d3.D3Node;


/**
 * 
 * @author Hongkai Liu
 *
 */
@Controller
public class ViewController {
	
	private static final Logger logger = Logger.getLogger(ViewController.class);
	
	private final static String DEFAULT_VIEW_NAME = "host";

	@RequestMapping(value = { "/view" }, method = RequestMethod.GET)
	public @ResponseBody D3Node view(@RequestParam(value = "name", required = false) String name) {
		logger.debug("==============view page==================");
		if (StringUtils.isEmpty(name)) {
			name = DEFAULT_VIEW_NAME;
		}
		
		D3Node node = getD3tree(name);
		
		return node;

	}

	private D3Node getD3tree(String name) {
		logger.debug("==============name==================" + name);
		D3Node node = new D3Node(name + "aaaccc");
		D3Node node1 = new D3Node(name + "aaa111");
		D3Node node2 = new D3Node(name + "aaa222");
		node.getChildren().add(node1);
		node.getChildren().add(node2);
		return node;
	}

}