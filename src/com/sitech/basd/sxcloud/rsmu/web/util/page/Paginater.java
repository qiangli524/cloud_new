package com.sitech.basd.sxcloud.rsmu.web.util.page;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * @Title:分页处理
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */
public class Paginater {

	public Pagination initPagination(HttpServletRequest request) {
		Pagination pagination = (Pagination) request
				.getAttribute("PAGINATION_PAGER");
		if (pagination != null)
			return pagination;
		int pageSize = 10;// 默认每页10条记录
		String PGSZIE = request.getParameter("PGSZIE");
		if (PGSZIE == null) {
			PGSZIE = (String) request.getAttribute("PGSZIE");
		}
		int currentPageNo = 1;
		int totalCount = 0;
		pagination = new Pagination(totalCount);
		try {
			String flag = request.getParameter("PAGINATOR_FLAG");
			String pageNoStr = request
					.getParameter("PAGINATION_CURRENT_PAGE_NO");
			currentPageNo = pageNoStr == null ? currentPageNo : Integer
					.parseInt(pageNoStr);
			String totalCountStr = request
					.getParameter("PAGINATION_TOTAL_COUNT");
			totalCount = totalCountStr == null ? totalCount : Integer
					.parseInt(totalCountStr);
			if ("false".equalsIgnoreCase(flag))
				currentPageNo = 1;
		} catch (NumberFormatException nfex) {
			LogHelper.info("分页参数不是有效数字格式，采用默认值！");
		}
		try {
			pageSize = Integer.parseInt(PGSZIE != null ? PGSZIE
					: ContextBeanFactory.getContextBean(
							String.valueOf(pageSize)).getPage());
		} catch (Exception ex) {
			LogHelper.debug("分页参数采用默认值！");
		}
		pagination.setTotalCount(totalCount);
		pagination.setCurrentPageNo(currentPageNo);
		pagination.setPageSize(pageSize);
		request.setAttribute("PAGINATION_PAGER", pagination);
		return pagination;
	}
}
