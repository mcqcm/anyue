package com.common;

import java.util.ArrayList;

public class CommonListEntity {
	private String entity;//ʵ��
	private String draw;//��ȡ�������
	private String start;//������ʼλ��
	private String length;//���ݳ���
	private String ordercolumn;//��һ������
	private String orderDir;//����ʽ
	private String searchvalue;//��ѯ����
	private String searchcolumn;//��ѯ����
	private String cols;//��ʾ�ļ���
	private String condition;//��һ����ʾ������
	private ArrayList<String> columnsearchvalue;//û�в�ѯ����
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
