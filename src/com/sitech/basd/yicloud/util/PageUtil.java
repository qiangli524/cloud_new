package com.sitech.basd.yicloud.util;

import java.util.List;

public class PageUtil {
	
	/**
	 * 数据库总行数
	 */
	private int rowCount;
	
	/**
	 * 一页显示多少行数据
	 */
	private int pageSize  =10;
	
	/**
	 * 当前导航
	 */
	private int num;//
	
	
	/**
	 * 数据查询起始行
	 */
	private int startRow;
	
	
	/**
	 * 导航首页
	 */
	private int first=1;
	
	/**
	 * 导航的尾页
	 */
	private int last;
	
	/**
	 * 导航的上一页
	 */
	private int prev;
	
	/**
	 * 导航下一页
	 */
	private int next;
	
	/**
	 * 导航显示的起始
	 */
	private int begin;
	
	/**
	 * 导航显示的结束
	 */
	private int end;
	
	/**
	 *一共有多少个导航 
	 */
	private int navConut;
	
	/**
	 * 导航页数
	 */
	private int navNum=10;
	
	/**
	 * 页面的数据
	 */
	private List<Object> pageData;
	
	
	public PageUtil(int num,int rowCount ,int pageSize) {
		this.rowCount = rowCount;
		this.pageSize = pageSize;
		this.navConut= (int) Math.ceil((this.rowCount*1.0)/this.pageSize);
		this.last = this.navConut;
		this.num=Math.max(this.first, num);//处理当前导航始终大于等于第一个导航
		this.num=Math.min(this.last, this.num);
		
		this.startRow =(this.num-1)*this.pageSize; 
		
		this.next = Math.min(this.last , this.num+1);
		this.prev = Math.max(this.first, this.num-1);
		
		this.begin= Math.max(this.first, (this.num-this.navNum/2));
		this.end = Math.min(this.last, this.begin+this.navNum-1);
		if (this.end-this.begin<(this.navNum-1)) {
			this.begin= Math.max(this.first, this.end-this.navNum+1);//begin=96
		}
		
	}
	
	public int getNavNum() {
		return navNum;
	}
	
	public void setNavNum(int navNum) {
		this.navNum = navNum;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	public int getFirst() {
		return first;
	}
	
	public void setFirst(int first) {
		this.first = first;
	}
	
	public int getLast() {
		return last;
	}
	
	public void setLast(int last) {
		this.last = last;
	}
	
	public int getPrev() {
		return prev;
	}
	
	public void setPrev(int prev) {
		this.prev = prev;
	}
	
	public int getNext() {
		return next;
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	
	public int getBegin() {
		return begin;
	}
	
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getNavConut() {
		return navConut;
	}
	
	public void setNavConut(int navConut) {
		this.navConut = navConut;
	}
	
	public List<Object> getPageData() {
		return pageData;
	}
	
	public void setPageData(List<Object> pageData) {
		this.pageData = pageData;
	}
	
}
