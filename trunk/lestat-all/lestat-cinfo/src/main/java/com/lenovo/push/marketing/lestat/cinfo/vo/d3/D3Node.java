package com.lenovo.push.marketing.lestat.cinfo.vo.d3;

import java.util.ArrayList;
import java.util.List;

public class D3Node {

	private String name;
	private List<D3Node> children = new ArrayList<D3Node>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<D3Node> getChildren() {
		return children;
	}
	public void setChildren(List<D3Node> children) {
		this.children = children;
	}
	public D3Node(String name) {
		super();
		this.name = name;
	}
	
	
}
