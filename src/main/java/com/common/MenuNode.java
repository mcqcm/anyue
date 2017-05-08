package com.common;

import java.util.ArrayList;
import java.util.List;

import com.orm.SystemMenu;

/**
 * 菜单节点
 * @author liuhaihua
 *
 */
public class MenuNode extends SystemMenu{
	private  List<SystemMenu> children = new ArrayList<SystemMenu>();

	public List<SystemMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SystemMenu> children) {
		this.children = children;
	}
	
}
