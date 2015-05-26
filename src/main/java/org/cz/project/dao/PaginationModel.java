package org.cz.project.dao;

public class PaginationModel {
	private int totalRow = 0;

	private int pageSize = 10;

	private int currPage = 1;

	public PaginationModel() {
	}

	public PaginationModel(int pageSize, int currPage) {
		this.pageSize = pageSize;
		this.currPage = currPage;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

}
