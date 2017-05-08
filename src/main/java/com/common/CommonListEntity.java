package com.common;

import java.util.ArrayList;

public class CommonListEntity {
	private String entity;//实体
	private String draw;//获取请求次数
	private String start;//数据起始位置
	private String length;//数据长度
	private String ordercolumn;//那一列排序
	private String orderDir;//排序方式
	private String searchvalue;//查询输入
	private String searchcolumn;//查询的列
	private String cols;//显示哪几列
	private String condition;//第一次显示的条件
	private ArrayList<String> columnsearchvalue;//没列查询输入
	private ArrayList<Integer> searchcol;
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getOrdercolumn() {
		return ordercolumn;
	}
	public void setOrdercolumn(String ordercolumn) {
		this.ordercolumn = ordercolumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	public String getSearchvalue() {
		return searchvalue;
	}
	public void setSearchvalue(String searchvalue) {
		this.searchvalue = searchvalue;
	}
	public String getSearchcolumn() {
		return searchcolumn;
	}
	public void setSearchcolumn(String searchcolumn) {
		this.searchcolumn = searchcolumn;
	}
	public String getCols() {
		return cols;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public ArrayList<String> getColumnsearchvalue() {
		return columnsearchvalue;
	}
	public void setColumnsearchvalue(ArrayList<String> columnsearchvalue) {
		this.columnsearchvalue = columnsearchvalue;
	}
	public ArrayList<Integer> getSearchcol() {
		return searchcol;
	}
	public void setSearchcol(ArrayList<Integer> searchcol) {
		this.searchcol = searchcol;
	}

}
