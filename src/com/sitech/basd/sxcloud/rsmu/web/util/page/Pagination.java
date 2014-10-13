package com.sitech.basd.sxcloud.rsmu.web.util.page;

/**
 * @Title:分页处理
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */

public class Pagination {

	private static final long serialVersionUID = 1L;
	public static final String PAGER_KEY = "PAGINATION_PAGER";
	public static final String PAGE_SIZE_KEY = "PAGINATION_PAGE_SIZE";
	public static final String PAGE_COUNT_KEY = "PAGINATION_PAGE_COUNT";
	public static final String CURRENT_PAGE_NO_KEY = "PAGINATION_CURRENT_PAGE_NO";
	public static final String TOTAL_COUNT_KEY = "PAGINATION_TOTAL_COUNT";
	public static final String PAGINATOR_FLAG = "PAGINATOR_FLAG";
	public static final String PGSZIE = "PGSZIE";// 自定义大小--duangh
	public int pageSize;
	public int currentPageNo;
	public int totalCount;

	public Pagination(int totalCount) {
		pageSize = 0;
		currentPageNo = 1;
		this.totalCount = 0;
		this.totalCount = totalCount;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageCount() {
		if (pageSize <= 0)
			return 0;
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFirstRownum() {
		return (currentPageNo - 1) * getPageSize();
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Pagination))
			return false;
		Pagination pagination = (Pagination) obj;
		if (pagination.getTotalCount() != totalCount)
			return false;
		if (pagination.getCurrentPageNo() != currentPageNo)
			return false;
		return pagination.getPageSize() == pageSize;
	}

}
