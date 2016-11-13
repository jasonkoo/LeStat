package com.lenovo.push.marketing.lestat.cinfo.vo.handsontable;

import java.util.ArrayList;
import java.util.List;

import com.lenovo.push.marketing.lestat.cinfo.vo.page.PageInfo;

public class HandsonTable implements PageInfo {
	
	private int currentPageNumber;
	private int totalPageNumber;

	private List<HandsonRow> data = new ArrayList<HandsonRow>();

	public List<HandsonRow> getData() {
		return data;
	}

	public void setData(List<HandsonRow> data) {
		this.data = data;
	}

	@Override
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	@Override
	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}


	
}
