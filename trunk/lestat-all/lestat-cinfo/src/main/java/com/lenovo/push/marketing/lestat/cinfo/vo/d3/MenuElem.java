package com.lenovo.push.marketing.lestat.cinfo.vo.d3;

import java.util.ArrayList;
import java.util.List;

public class MenuElem {
	public String name;
	public String id;
	public String url;
	public String dataUrl;
	
	
	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuElem> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<MenuElem> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public List<MenuElem> subMenuList = new ArrayList<MenuElem>();
	
	public MenuElem(String name, String id, String url, String dataUrl) {
		super();
		this.name = name;
		this.id = id;
		this.url = url;
		this.dataUrl = dataUrl;
	}
}
