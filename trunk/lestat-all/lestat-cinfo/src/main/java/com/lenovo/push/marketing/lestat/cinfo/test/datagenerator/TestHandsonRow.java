package com.lenovo.push.marketing.lestat.cinfo.test.datagenerator;

import com.lenovo.push.marketing.lestat.cinfo.vo.handsontable.HandsonRow;

public class TestHandsonRow implements HandsonRow {
	
	private long id;
	private String name;
	private String address;
	private String save="<button type=\"button\" class=\"btn btn-xs btn-primary\"><span class=\"glyphicon glyphicon-ok\"></span></button>";
	private String remove="<button type=\"button\" class=\"btn btn-xs btn-warning\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public TestHandsonRow(long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public String getRemove() {
		return remove;
	}
	public void setRemove(String remove) {
		this.remove = remove;
	}
	
	public TestHandsonRow() {
		super();
	}


}
